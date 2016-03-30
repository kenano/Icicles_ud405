package com.kenan.libgdxtutorial.icicles;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;

/**
 * Created by KenanO on 3/23/16.
 */
public class Icicle {

    public static final String TAG = Icicle.class.getName();

    //a Vector2 position
    private Vector2 position;

    //constructor that sets the position
    public Icicle(Vector2 pos){

        //set the position
        position = new Vector2(pos);
    }

    //a render function that takes a ShapeRenderer
    public void render(ShapeRenderer renderer){

        // Set the ShapeRenderer's color

        renderer.setColor(Constants.ICICLE_COLOR);

        // Set the ShapeType
//        renderer.set(ShapeRenderer.ShapeType.Filled);
        renderer.begin(ShapeRenderer.ShapeType.Filled);

        // Draw the icicle using the size constants
        renderer.triangle(
                position.x, position.y,
                position.x - Constants.ICICLE_WIDTH / 2, position.y + Constants.ICICLE_HEIGHT,
                position.x + Constants.ICICLE_WIDTH / 2, position.y + Constants.ICICLE_HEIGHT);

        renderer.end();
    }
}
