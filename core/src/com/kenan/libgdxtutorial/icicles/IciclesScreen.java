package com.kenan.libgdxtutorial.icicles;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.viewport.ExtendViewport;

/**
 * Created by KenanO on 3/23/16.
 */
public class IciclesScreen implements Screen {

    public static final String TAG = Icicle.class.getName();

    //ShapeRender handles graphics operations.
    private ShapeRenderer shapeRenderer;

    // Makes the short axis of the world larger to fill the screen, maintaining aspect ratio
    private ExtendViewport viewport =
            new ExtendViewport(Constants.WORLD_SIZE, Constants.WORLD_SIZE);

    private Player player;
    private Icicles icicles;


    @Override
    public void show() {

        //Initialize the ShapeRenderer
        shapeRenderer = new ShapeRenderer();

        //Set autoShapeType(true) on the ShapeRenderer
        //If true, when drawing a shape cannot be performed with the current shape type, the batch
        //is flushed and the shape type is changed automatically.
        //**why is this done??
        shapeRenderer.setAutoShapeType(true);

        // Initialize the player
        player = new Player(viewport);

        // Initialize icicles
        icicles = new Icicles(viewport);

    }

    @Override
    public void render(float delta) {

        //Apply the viewport
        viewport.apply();

        //Clear the screen to the background color
        Gdx.gl.glClearColor(Constants.BACKGROUND_COLOR.r, Constants.BACKGROUND_COLOR.g,
                Constants.BACKGROUND_COLOR.b, 1);

        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        //Set the ShapeRenderer's projection matrix
        shapeRenderer.setProjectionMatrix(viewport.getCamera().combined);

        //  Call update() on player
        player.update(delta, viewport);

        // Update Icicles
        icicles.update(delta);

        //Render Icicles
        icicles.render(shapeRenderer);

        //Call render() on the player
        player.render(shapeRenderer);
    }

    @Override
    public void resize(int width, int height) {

        //Ensure that the viewport updates correctly
        viewport.update(width, height, true);

        // Reset the player (using init())
        player.init();

        // Reset icicles
        icicles.init();
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

        //dispose of shaperender
        shapeRenderer.dispose();

    }
}
