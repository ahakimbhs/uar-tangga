package com.amikomgamedev.ulartangga.states;

import org.anddev.andengine.engine.Engine;
import org.anddev.andengine.engine.camera.Camera;
import org.anddev.andengine.engine.options.EngineOptions;
import org.anddev.andengine.engine.options.EngineOptions.ScreenOrientation;
import org.anddev.andengine.engine.options.resolutionpolicy.RatioResolutionPolicy;
import org.anddev.andengine.entity.scene.Scene;
import org.anddev.andengine.ui.activity.BaseGameActivity;

import com.amikomgamedev.ulartangga.Config;
import com.amikomgamedev.ulartangga.Game;

public class State_Menu_Select_Map
{
	private Scene scene;
	
	public Scene stateMenuSelectMap()
	{
		scene = new Scene();
//		scene.detachChildren();
		scene.attachChild(Game.spr_Img_Select_Map_Bg);
		return scene;
		
	}

}
