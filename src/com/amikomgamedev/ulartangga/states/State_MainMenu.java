package com.amikomgamedev.ulartangga.states;

import org.anddev.andengine.engine.Engine;
import org.anddev.andengine.entity.scene.Scene;
import org.anddev.andengine.ui.activity.BaseGameActivity;

import com.amikomgamedev.ulartangga.Game;

public class State_MainMenu extends BaseGameActivity{

	public Engine onLoadEngine() {
		State_Gameplay gameplay = new State_Gameplay();
		gameplay.onLoadEngine();
		return null;
	}

	public void onLoadResources() {
		Game.setContext(this);
		
	}

	public Scene onLoadScene() {
	
		return null;
	}

	public void onLoadComplete() {
			
	}

}
