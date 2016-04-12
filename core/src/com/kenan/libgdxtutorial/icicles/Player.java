package com.kenan.libgdxtutorial.icicles;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.viewport.Viewport;

/**
 * Created by KenanO on 3/23/16.
 */
public class Player {

    public static final String TAG = Icicle.class.getName();

    // Add a position (add constants to Constants.java first)
    //a Vector2 position
    private Vector2 position;
    private Vector2 velocity;


    // Add a viewport
    private Viewport mViewport;


    // Add constructor that accepts and sets the viewport, then calls init()
    Player(Viewport viewport) {
        mViewport = viewport;
        init();
    }

    //  Add init() function that moves the character to the bottom center of the screen
    public void init(){

        position = new Vector2(Constants.WORLD_SIZE/2, 3.5f);
        velocity = new Vector2();
    }

    public void update(float delta, Viewport viewport){
        // Use Gdx.input.isKeyPressed() to move the player in the appropriate direction when an arrow key is pressed

        if (Gdx.input.isKeyPressed(Input.Keys.LEFT)){
            velocity.x -= Constants.PLAYER_SPEED;

        }

        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)){
            velocity.x +=  Constants.PLAYER_SPEED;

        }


        // TODO: Compute accelerometer input = raw input / (gravity * sensitivity)
        float xAxis = -Gdx.input.getAccelerometerY();
        float yAxis = Gdx.input.getAccelerometerX();

        float accelerationX = -  xAxis / (Constants.ACCELEROMETER_SENSITIVITY * Constants.GRAVITY);
        float accelerationY = - yAxis / (Constants.ACCELEROMETER_SENSITIVITY * Constants.GRAVITY);

        // TODO: Use the accelerometer input to move the player
        velocity.x += delta * accelerationX;
        velocity.y += delta * accelerationY;


        position.x += delta * velocity.x;
        position.y += delta * velocity.y;


        ensureInBounds(Constants.PLAYER_HEAD_RADIUS, viewport.getWorldWidth(), viewport.getWorldHeight());

    }

    private void ensureInBounds(float radius, float viewportWidth, float viewportHeight) {
        //  function to ensure the player is within the viewport

        if (position.x - radius < 0) {
            position.x = radius;
            velocity.x = -velocity.x;
        }
        if (position.x + radius > viewportWidth) {
            position.x = viewportWidth - radius;
            velocity.x = -velocity.x;
        }
        if (position.y - radius < 0) {
            position.y = radius;
            velocity.y = -velocity.y;
        }
        if (position.y + radius > viewportHeight) {
            position.y = viewportHeight - radius;
            velocity.y = -velocity.y;
        }

    }


    // Create a render function that accepts a ShapeRenderer and does the actual drawing
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
