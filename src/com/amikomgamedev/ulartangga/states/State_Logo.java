package com.amikomgamedev.ulartangga.states;

import org.anddev.andengine.engine.Engine;
import org.anddev.andengine.engine.camera.Camera;
import org.anddev.andengine.engine.handler.timer.ITimerCallback;
import org.anddev.andengine.engine.handler.timer.TimerHandler;
import org.anddev.andengine.engine.options.EngineOptions;
import org.anddev.andengine.engine.options.EngineOptions.ScreenOrientation;
import org.anddev.andengine.engine.options.resolutionpolicy.RatioResolutionPolicy;
import org.anddev.andengine.entity.scene.Scene;
import org.anddev.andengine.entity.scene.background.ColorBackground;
import org.anddev.andengine.ui.activity.BaseGameActivity;

import android.content.Intent;

import com.amikomgamedev.ulartangga.Config;
import com.amikomgamedev.ulartangga.Game;

public class State_Logo extends BaseGameActivity
{

	private Camera camera;
	private Scene scene = new Scene();

	public Engine onLoadEngine()
	{
		Game.appInit();
		camera = new Camera(0, 0, Config.GAME_SCREEN_WIDTH, Config.GAME_SCREEN_HEIGHT);
		mEngine = new Engine(
			new EngineOptions(true, ScreenOrientation.PORTRAIT,
			new RatioResolutionPolicy(Config.GAME_SCREEN_WIDTH, Config.GAME_SCREEN_HEIGHT),camera));
		return mEngine;
	}

	public void onLoadResources()
	{
		Game.setContext(this);
		Game.loadLogo();
	}

	public Scene onLoadScene() {
		scene.setBackground(new ColorBackground(1, 1, 1));
		scene.attachChild(Game.spr_Img_Logo);
		return scene;
	}

	public void onLoadComplete() {
		mEngine.registerUpdateHandler(new TimerHandler(Config.LOGO_DURATION, new ITimerCallback()
		{
			
			public void onTimePassed(TimerHandler pTimerHandler)
			{
				startActivity(new Intent(State_Logo.this, State_Menu_Main.class));
				finish();	
			}
		}));
	}
}
