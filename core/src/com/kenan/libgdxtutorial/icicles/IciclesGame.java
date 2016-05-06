package com.kenan.libgdxtutorial.icicles;

import com.badlogic.gdx.Game;

public class IciclesGame extends Game {

	public static final String TAG = Icicle.class.getName();
	
	@Override
	public void create () {

		showDifficultyScreen();
	}

	public void showDifficultyScreen(){
		// Show the difficulty screen

		setScreen(new DifficultyScreen(this));
	}

	public void showIciclesScreen(Constants.DIFFICULTY difficulty){
		// Show the Icicles screen (the game)

		if(difficulty == Constants.DIFFICULTY.DIFFICULTY_COLD) {
			setScreen(new IciclesScreen(Constants.DIFFICULTY.DIFFICULTY_COLD, this));
		} else if(difficulty == Constants.DIFFICULTY.DIFFICULTY_COLDER){
			setScreen(new IciclesScreen(Constants.DIFFICULTY.DIFFICULTY_COLDER, this));
		}else if(difficulty == Constants.DIFFICULTY.DIFFICULTY_COLDEST){
			setScreen(new IciclesScreen(Constants.DIFFICULTY.DIFFICULTY_COLDEST, this));
		}
	}
}