package com.capture.game;

import com.badlogic.gdx.Game;
import com.capture.screen.MainGameScreen;

public class MainGame extends Game {
	
	@Override
	public void create () {
		setScreen(new MainGameScreen());
	}

}
