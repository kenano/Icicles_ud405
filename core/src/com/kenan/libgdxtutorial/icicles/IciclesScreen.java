package com.kenan.libgdxtutorial.icicles;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

/**
 * Created by KenanO on 3/23/16.
 */
public class IciclesScreen extends InputAdapter implements Screen {

    public static final String TAG = Icicle.class.getName();

    // IciclesGame the parent which calls this class.
    private IciclesGame icicles_game;

    //ShapeRender handles graphics operations.
    private ShapeRenderer shapeRenderer;

    // Makes the short axis of the world larger to fill the screen, maintaining aspect ratio
    private ExtendViewport icicles_screen_viewport;

    private Player player;

    // Class that implements Icicles Data Structure. Stores multiple instances of the Icicle class.
    private Icicles icicles;

    // ScreenViewport for HUD
    private ScreenViewport hud_viewport;

    // SpriteBatch for text (high score, deaths, etc)
    //text cant be drawn with a shaperender, use sprintbatch
    private SpriteBatch text_render_sprite_batch;

    // BitmapFont user to draw text.
    private BitmapFont bitmap_font;

    //int to hold the top score
    private int top_score;

    //current game difficulty
    private Constants.DIFFICULTY current_difficulty;


    IciclesScreen(Constants.DIFFICULTY difficulty, IciclesGame  _icicles_game){
        //Set Difficulty inside the constructor and save a reference to the class that class
        //that creates IciclesScreen

        //Save the IciclesGame
        icicles_game = _icicles_game;

        current_difficulty = difficulty;
    }

    @Override
    public void show() {

        //register this class to the input processor. This allows InputAdapter methods to be
        //overridden for custom functionality in response to user input.
        Gdx.input.setInputProcessor(this);

        //initialize the game screen area to the world size.
        icicles_screen_viewport = new ExtendViewport(Constants.WORLD_SIZE, Constants.WORLD_SIZE);

        //Initialize the ShapeRenderer
        shapeRenderer = new ShapeRenderer();

        //Set autoShapeType(true) on the ShapeRenderer
        //If true, when drawing a shape cannot be performed with the current shape type, the batch
        //is flushed and the shape type is changed automatically.
        //**why is this done??
        shapeRenderer.setAutoShapeType(true);

        // Initialize the HUD viewport
        hud_viewport = new ScreenViewport();

        //  Initialize the SpriteBatch
        text_render_sprite_batch = new SpriteBatch();

        // Initialize the BitmapFont
        bitmap_font = new BitmapFont();


        //Give the font a linear TextureFilter, this is used to draw the bitmap at a larger size
        //and prevents it from being blurry.
        bitmap_font.getRegion().getTexture().setFilter(Texture.TextureFilter.Linear,
                Texture.TextureFilter.Linear);

        // Initialize the player
        player = new Player(icicles_screen_viewport);

        // Initialize icicles with the difficulty
        icicles = new Icicles(icicles_screen_viewport, current_difficulty);

        // Set top score to zero
        top_score = 0;
    }

    @Override
    public void render(float delta) {

        //Apply the viewport
        icicles_screen_viewport.apply();

        //Clear the screen to the background color
        Gdx.gl.glClearColor(Constants.BACKGROUND_COLOR.r, Constants.BACKGROUND_COLOR.g,
                Constants.BACKGROUND_COLOR.b, 1);

        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        //Set the ShapeRenderer's projection matrix
        shapeRenderer.setProjectionMatrix(icicles_screen_viewport.getCamera().combined);

        //  Call update() on player
        player.update(delta, icicles_screen_viewport);

        // Update Icicles
        icicles.update(delta);

        //if any of the icicles have hit the player, reset icicles.
        if(player.hitByIcicle(icicles)){
            icicles.init();
        }

        //Render Icicles
        icicles.render(shapeRenderer);

        //Call render() on the player
        player.render(shapeRenderer);

        // Set the top score to max(topScore, iciclesDodges)
        // use native java static method from java.lang.Math
        top_score = Math.max(top_score, icicles.getDodgedIciclesCount());

        // Apply the HUD viewport
        hud_viewport.apply();

        // Set the SpriteBatch's projection matrix
        text_render_sprite_batch.setProjectionMatrix(hud_viewport.getCamera().combined);

        // Begin the SpriteBatch
        text_render_sprite_batch.begin();

        // Show Difficulty level in the top left
        //Draw the number of player deaths in the top left
        bitmap_font.draw(text_render_sprite_batch,
                Constants.TEXT_PLAYER_DEATHS + player.getDeathCount() + '\n'
                        + Constants.TEXT_DIFFICULTY + current_difficulty.getText() ,
                Constants.HUD_MARGIN_SIZE,
                hud_viewport.getWorldHeight() - Constants.HUD_MARGIN_SIZE);

        //Draw the score and top score in the top right
        bitmap_font.draw(text_render_sprite_batch,
                Constants.TEXT_SCORE + icicles.getDodgedIciclesCount() + '\n' +
                Constants.TEXT_TOP_SCORE + top_score,
                hud_viewport.getWorldWidth() - Constants.HUD_MARGIN_SIZE,
                hud_viewport.getWorldHeight() - Constants.HUD_MARGIN_SIZE,
                0, Align.right, false);


        //End the SpriteBatch
        text_render_sprite_batch.end();
    }

    @Override
    public void resize(int width, int height) {

        // Update HUD viewport
        hud_viewport.update(width, height, true);


        //Set font scale to min(width, height) / reference screen size
        bitmap_font.getData().setScale(
                (Math.min(width, height) / Constants.HUD_FONT_SCALING_REFERENCE_SCREEN_SIZE) );

        //Ensure that the viewport updates correctly
        icicles_screen_viewport.update(width, height, true);

        // Reset the player (using init())
        player.init();

        // Reset icicles
        icicles.init();
    }

    @Override
    public void pause() {}

    @Override
    public void resume() {}

    @Override
    public void hide() {}

    @Override
    public void dispose() {

        //dispose of shaperender
        shapeRenderer.dispose();

        // Dispose of the SpriteBatch
        text_render_sprite_batch.dispose();

    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        //this method is invoked when user "touchesDown" on an element registered in
        //setInputProcessor. Here displayed view will switch the difficulty screen.

        // Tell IciclesGame to show the difficulty screen
        icicles_game.showDifficultyScreen();

        return true;
    }

}
