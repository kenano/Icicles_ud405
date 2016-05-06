package com.kenan.libgdxtutorial.icicles;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.FitViewport;


/**
 * Created by KenanO on 3/23/16.
 */
public class DifficultyScreen extends InputAdapter implements Screen {

    public static final String TAG = Icicle.class.getName();

    //ShapeRender handles graphics operations.
    private ShapeRenderer shapeRenderer;

    //text cant be drawn with a shaperender, use sprintbatch
    private SpriteBatch text_render_sprite_batch;

    // BitmapFont user to draw text.
    private BitmapFont bitmap_font;

    //game class which starts different game screens
    private IciclesGame icicles_game;

    //viewport usef for this screen.
    private FitViewport fit_viewport;

    DifficultyScreen(IciclesGame _icicles_game){
        icicles_game = _icicles_game;
    }

    @Override
    public void show() {

        //register this class to the input processor. This allows InputAdapter methods to be
        //overridden for custom functionality in response to user input.
        Gdx.input.setInputProcessor(this);

        //Initialize the ShapeRenderer
        shapeRenderer = new ShapeRenderer();

        //drawing text requires a sprint batch.
        text_render_sprite_batch = new SpriteBatch();

        // Initialize the BitmapFont
        bitmap_font = new BitmapFont();

        //Set autoShapeType(true) on the ShapeRenderer
        //If true, when drawing a shape cannot be performed with the current shape type, the batch
        //is flushed and the shape type is changed automatically.
        //**why is this done??
        shapeRenderer.setAutoShapeType(true);


        // Initialize a FitViewport with the difficulty world size constant
        fit_viewport = new FitViewport(Constants.DIFFICULTY_WORLD_SIZE,
                Constants.DIFFICULTY_WORLD_SIZE);

        //  Set the font scale using the constant we defined
        bitmap_font.getData().setScale(Constants.DIFFICULTY_LABEL_SCALE);
        bitmap_font.getRegion().getTexture().setFilter(Texture.TextureFilter.Linear,
                Texture.TextureFilter.Linear);
    }

    @Override
    public void render(float delta) {

        // Apply the viewport
        fit_viewport.apply();

        //clear the screen with the background color.
        Gdx.gl.glClearColor(Constants.BACKGROUND_COLOR.r,
                Constants.BACKGROUND_COLOR.g, Constants.BACKGROUND_COLOR.b, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        // Set the ShapeRenderer's projection matrix
        shapeRenderer.setProjectionMatrix(fit_viewport.getCamera().combined);

        // Use ShapeRenderer to draw the buttons
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);

        //Draw easy button
        shapeRenderer.setColor(Constants.EASY_COLOR);
        shapeRenderer.circle(Constants.EASY_BUTTON_CENTER.x, Constants.EASY_BUTTON_CENTER.y,
                Constants.DIFFICULTY_BUTTON_RADIUS, 40);

        //Draw medium button
        shapeRenderer.setColor(Constants.MEDIUM_COLOR);
        shapeRenderer.circle(Constants.MEDIUM_BUTTON_CENTER.x, Constants.MEDIUM_BUTTON_CENTER.y,
                Constants.DIFFICULTY_BUTTON_RADIUS, 40);

        //Draw hard button
        shapeRenderer.setColor(Constants.HARD_COLOR);
        shapeRenderer.circle(Constants.HARD_BUTTON_CENTER.x, Constants.HARD_BUTTON_CENTER.y,
                Constants.DIFFICULTY_BUTTON_RADIUS, 40);

        shapeRenderer.end();

        //Set the SpriteBatche's projection matrix
        text_render_sprite_batch.setProjectionMatrix(fit_viewport.getCamera().combined);


        // Use SpriteBatch to draw the labels on the buttons
        // HINT: Use GlyphLayout to get vertical centering
        text_render_sprite_batch.begin();

        //cold button
        final GlyphLayout cold_glyph_layout = new GlyphLayout(bitmap_font,
                Constants.TEXT_DIFFICULTY_COLD);
        bitmap_font.draw(text_render_sprite_batch,
                Constants.TEXT_DIFFICULTY_COLD,
                Constants.EASY_BUTTON_CENTER.x,
                Constants.EASY_BUTTON_CENTER.y + cold_glyph_layout.height, 0, Align.center, false);

        //colder button
        final GlyphLayout colder_glyph_layout = new GlyphLayout(bitmap_font,
                Constants.TEXT_DIFFICULTY_COLDER);
        bitmap_font.draw(text_render_sprite_batch,
                Constants.TEXT_DIFFICULTY_COLDER,
                Constants.MEDIUM_BUTTON_CENTER.x,
                Constants.MEDIUM_BUTTON_CENTER.y + colder_glyph_layout.height,
                0, Align.center, false);

        //coldest button
        final GlyphLayout coldest_glyph_layout = new GlyphLayout(bitmap_font,
                Constants.TEXT_DIFFICULTY_COLDEST);
        bitmap_font.draw(text_render_sprite_batch,
                Constants.TEXT_DIFFICULTY_COLDEST,
                Constants.HARD_BUTTON_CENTER.x,
                Constants.HARD_BUTTON_CENTER.y + coldest_glyph_layout.height,
                0, Align.center, false);

        text_render_sprite_batch.end();
    }

    @Override
    public void resize(int width, int height) {
        // Update the viewport
        fit_viewport.update(width, height, true);
    }

    @Override
    public void pause() {}

    @Override
    public void resume() {}

    @Override
    public void hide() {}

    @Override
    public void dispose() {}


    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {

        //vector representing screen coord.
        Vector2 screen_coord = new Vector2(screenX, screenY);

        //vector representing world coord.
        Vector2 world_coord = fit_viewport.unproject(screen_coord);

        //if the point clicked in the world with within the button execute the code associated
        //with the button. Each button should start a screen at a different difficulty.
        if(world_coord.dst(Constants.EASY_BUTTON_CENTER) < Constants.DIFFICULTY_BUTTON_RADIUS){
            icicles_game.showIciclesScreen(Constants.DIFFICULTY.DIFFICULTY_COLD);
        }else if(world_coord.dst(Constants.MEDIUM_BUTTON_CENTER) < Constants.DIFFICULTY_BUTTON_RADIUS){
            icicles_game.showIciclesScreen(Constants.DIFFICULTY.DIFFICULTY_COLDER);
        }else if(world_coord.dst(Constants.HARD_BUTTON_CENTER) < Constants.DIFFICULTY_BUTTON_RADIUS){
            icicles_game.showIciclesScreen(Constants.DIFFICULTY.DIFFICULTY_COLDEST);
        }

        return true;
    }
}