package com.kenan.libgdxtutorial.icicles;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class IciclesGame extends Game {

	public static final String TAG = Icicle.class.getName();
	
	@Override
	public void create () {

		//call setScreen() with a new IciclesScreen()
		setScreen(new IciclesScreen());
	}

}
