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

    //Add an ExtendViewport
    // Makes the short axis of the world larger to fill the screen, maintaining aspect ratio
    private ExtendViewport viewport =
            new ExtendViewport(Constants.WORLD_SIZE, Constants.WORLD_SIZE);

    //add an icicle
    private Icicle icicle;


    @Override
    public void show() {

        //Initialize the ShapeRenderer
        shapeRenderer = new ShapeRenderer();

        //Set autoShapeType(true) on the ShapeRenderer
        //If true, when drawing a shape cannot be performed with the current shape type, the batch
        //is flushed and the shape type is changed automatically.
        //**why is this done??
        shapeRenderer.setAutoShapeType(true);

        //Create a new Icicle in the middle of the world
        icicle = new Icicle(new Vector2(Constants.WORLD_SIZE/2, Constants.WORLD_SIZE/2));

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

        //Draw the Icicle
        icicle.render(shapeRenderer);

    }

    @Override
    public void resize(int width, int height) {

        //Ensure that the viewport updates correctly
        viewport.update(width, height, true);
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
