package com.kenan.libgdxtutorial.icicles;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.viewport.Viewport;

/**
 * Created by KenanO on 3/23/16.
 */
public class Player {

    public static final String TAG = Icicle.class.getName();

    // TODO: Add a position (add constants to Constants.java first)
    //a Vector2 position
    private Vector2 position;


    // TODO: Add a viewport
    private Viewport mViewport;


    // TODO: Add constructor that accepts and sets the viewport, then calls init()
    Player(Viewport viewport) {
        mViewport = viewport;
        init();
    }

    // TODO: Add init() function that moves the character to the bottom center of the screen
    public void init(){

        position = new Vector2(Constants.WORLD_SIZE/2, 3.5f);
    }


    // TODO: Create a render function that accepts a ShapeRenderer and does the actual drawing
    public void render(ShapeRenderer renderer){

        mViewport.apply();

        renderer.setColor(Constants.PLAYER_COLOR);

        renderer.begin(ShapeRenderer.ShapeType.Filled);

        // Head
        renderer.circle(position.x, Constants.PLAYER_HEAD_HEIGHT, Constants.PLAYER_HEAD_RADIUS,
                Constants.SEGMENTS_FOR_HEAD);

        // Torso
        renderer.rectLine(position.x, Constants.PLAYER_HEAD_HEIGHT - Constants.PLAYER_HEAD_RADIUS,
                position.x, Constants.PLAYER_HEAD_HEIGHT - Constants.PLAYER_HEAD_RADIUS - 1.7f,
                Constants.PLAYER_LIMB_WIDTH);

        // Legs
        renderer.rectLine(position.x, Constants.PLAYER_HEAD_HEIGHT - Constants.PLAYER_HEAD_RADIUS - 1.7f,
                position.x - 0.6f, Constants.PLAYER_HEAD_HEIGHT - Constants.PLAYER_HEAD_RADIUS -1.7f - 0.6f,
                Constants.PLAYER_LIMB_WIDTH);
        renderer.rectLine(position.x, Constants.PLAYER_HEAD_HEIGHT - Constants.PLAYER_HEAD_RADIUS - 1.7f,
                position.x + 0.6f, Constants.PLAYER_HEAD_HEIGHT - Constants.PLAYER_HEAD_RADIUS - 1.7f - 0.6f,
                Constants.PLAYER_LIMB_WIDTH);

        // Arms
        renderer.rectLine(position.x, Constants.PLAYER_HEAD_HEIGHT - Constants.PLAYER_HEAD_RADIUS,
                position.x - 0.6f, Constants.PLAYER_HEAD_HEIGHT - Constants.PLAYER_HEAD_RADIUS - 0.6f,
                Constants.PLAYER_LIMB_WIDTH);
        renderer.rectLine(position.x, Constants.PLAYER_HEAD_HEIGHT - Constants.PLAYER_HEAD_RADIUS,
                position.x + 0.6f, Constants.PLAYER_HEAD_HEIGHT - Constants.PLAYER_HEAD_RADIUS - 0.6f,
                Constants.PLAYER_LIMB_WIDTH);

        renderer.end();

    }
}
