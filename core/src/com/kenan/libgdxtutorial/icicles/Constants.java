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

}
