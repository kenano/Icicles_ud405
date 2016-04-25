package com.kenan.libgdxtutorial.icicles;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.viewport.Viewport;

/**
 * Created by KenanO on 3/23/16.
 */
public class Icicle {

    public static final String TAG = Icicle.class.getName();

    private Vector2 position;
    private Vector2 velocity;

    //constructor that sets the position
    public Icicle(Vector2 pos){

        //set the position
        position = new Vector2(pos);

        // Initialize velocity
        velocity = new Vector2(0,0);
    }

    public void update(float delta, Viewport viewport){

        // update velocity using icicle accelration constant
        velocity.y += delta * Constants.ICICLE_ACCELERATION;

        // Update position using velocity
        position.y += delta * velocity.y;
    }

    //a render function that takes a ShapeRenderer
    public void render(ShapeRenderer renderer){

        // Set the ShapeRenderer's color
        renderer.setColor(Constants.ICICLE_COLOR);

        // Set the ShapeType
        renderer.begin(ShapeRenderer.ShapeType.Filled);

        // Draw the icicle using the size constants
        renderer.triangle(
                position.x, position.y,
                position.x - Constants.ICICLE_WIDTH / 2, position.y + Constants.ICICLE_HEIGHT,
                position.x + Constants.ICICLE_WIDTH / 2, position.y + Constants.ICICLE_HEIGHT);
        renderer.end();
    }
}
