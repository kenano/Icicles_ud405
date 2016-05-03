package com.kenan.libgdxtutorial.icicles;

import com.badlogic.gdx.graphics.Color;

/**
 * Created by KenanO on 3/23/16.
 */
public class Constants {

    //define the world size. Used by the viewport.
    public static final float WORLD_SIZE = 10.0f;

    //constant for the background color of the world,
    //since the world background is set in method with 3 parameters (RGB values)
    //each of these values can be accessed as a member variable of the Color.
    public static final Color BACKGROUND_COLOR = Color.BLUE;

    //constant for the color of the icicles
    public static final Color ICICLE_COLOR = Color.WHITE;

    //Add a constant for the height of the icicle
    public static final float ICICLE_HEIGHT = 1.0f;

    //Add a constant for the width of the icicle
    public static final float ICICLE_WIDTH = 0.5f;

    //player sprite constants
    //player head radius
    public static final float PLAYER_HEAD_RADIUS = 0.5f;

    // player head height
    public static final float PLAYER_HEAD_HEIGHT = 2.7f;

    // player limb width
    public static final float PLAYER_LIMB_WIDTH = 0.15f;

    // circle segments for the player's head
    public static final int SEGMENTS_FOR_HEAD = 20;

    //  player's color
    public static final Color PLAYER_COLOR = Color.BLACK;

    //  player movement speed
    public static final float PLAYER_SPEED = 0.2f;

    //Acceleration constants
    public static final float GRAVITY = -9.8f;

    public static final float ACCELEROMETER_SENSITIVITY = .006f;

    public static final float ICICLE_SPAWNS_PER_SECOND = 4.0f;

    //screen reference size for scaling the HUD (480 works well)
    public static final float HUD_FONT_SCALING_REFERENCE_SCREEN_SIZE = 480f;

    //constant for the margin between the HUD and screen edge
    public static final float HUD_MARGIN_SIZE = 20.0f;

    //Text for player deaths
    public static final String TEXT_PLAYER_DEATHS = "Deaths: ";
    public static final String TEXT_SCORE = "Score: ";
    public static final String TEXT_TOP_SCORE= "Top Score: ";

    //Constants for difficulty labels ("Cold", "Colder", "Coldest")
    public static final String TEXT_DIFFICULTY_COLD = "Cold";
    public static final String TEXT_DIFFICULTY_COLDER = "Colder";
    public static final String TEXT_DIFFICULTY_COLDEST = "Coldest";
    public static final String TEXT_DIFFICULTY = "Difficulty: ";

    // Constants for the icicle spawn rates for the various difficulties
    public static final float ICICLE_SPAWNS_COLD = 2.0f;
    public static final float ICICLE_SPAWNS_COLDER = 4.0f;
    public static final float ICICLE_SPAWNS_COLDEST = 8.0f;

    // Difficulty enum holding the spawn rate and label for each difficulty
    enum DIFFICULTY{
        DIFFICULTY_COLD(ICICLE_SPAWNS_COLD, TEXT_DIFFICULTY_COLD),
        DIFFICULTY_COLDER(ICICLE_SPAWNS_COLDER, TEXT_DIFFICULTY_COLDER),
        DIFFICULTY_COLDEST(ICICLE_SPAWNS_COLDEST, TEXT_DIFFICULTY_COLDEST);

        DIFFICULTY(float _spawn_rate, String difficulty){
            this.spawn_rate = _spawn_rate;
            this.text = difficulty;
        }

        private float spawn_rate;
        private String text;

        public String getText() {return this.text;}

        public float getSpawnRate(){
            return this.spawn_rate;
        }
    }
}
