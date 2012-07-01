package com.amikomgamedev.ulartangga.states;

import org.anddev.andengine.engine.Engine;
import org.anddev.andengine.engine.camera.Camera;
import org.anddev.andengine.engine.handler.IUpdateHandler;
import org.anddev.andengine.engine.options.EngineOptions;
import org.anddev.andengine.engine.options.EngineOptions.ScreenOrientation;
import org.anddev.andengine.engine.options.resolutionpolicy.RatioResolutionPolicy;
import org.anddev.andengine.entity.modifier.FadeInModifier;
import org.anddev.andengine.entity.modifier.FadeOutModifier;
import org.anddev.andengine.entity.scene.Scene;
import org.anddev.andengine.entity.scene.background.ColorBackground;
import org.anddev.andengine.entity.sprite.AnimatedSprite;
import org.anddev.andengine.entity.sprite.Sprite;
import org.anddev.andengine.entity.util.FPSLogger;
import org.anddev.andengine.input.touch.TouchEvent;
import org.anddev.andengine.ui.activity.BaseGameActivity;
import org.anddev.andengine.util.Debug;

import android.content.Intent;

import com.amikomgamedev.ulartangga.Config;
import com.amikomgamedev.ulartangga.Game;
import com.amikomgamedev.ulartangga.Loading;

public class State_Menu_Main extends BaseGameActivity
							 implements	IUpdateHandler
{
	private static float CurrentSecond = 0;
	private static float Second;
	
	public static Camera	camera;
	public static Scene 	scene;
	
	private static final int STATE_MENU_START			= 0;
	private static final int STATE_MENU_LOADING			= 1;
	private static final int STATE_MENU_INMENU			= 2;
	private static final int STATE_MENU_PLAY			= 3;
	private static final int STATE_MENU_OPTIONS			= 4;
	private static final int STATE_MENU_CREDITS			= 5;
	private static final int STATE_MENU_SELECT_MAP		= 6;
	private static final int STATE_MENU_SELECT_CARACTER	= 7;
	private static int State_Game_Current		= STATE_MENU_START;
	

	public Engine onLoadEngine() 
	{
		Game.appInit();
		camera = new Camera(0, 0, Config.GAME_SCREEN_WIDTH, Config.GAME_SCREEN_HEIGHT);
		mEngine = new Engine(
			new EngineOptions(true, ScreenOrientation.PORTRAIT,
			new RatioResolutionPolicy(Config.GAME_SCREEN_WIDTH, Config.GAME_SCREEN_HEIGHT),
			camera).setNeedsMusic(true).setNeedsSound(true));
		return mEngine;
	}

	public void onLoadResources() 
	{
		Game.setContext(this);
	}

	public Scene onLoadScene() 
	{
		mEngine.registerUpdateHandler(new FPSLogger());
		mEngine.registerUpdateHandler(this);
		
		scene = new Scene();
//		scene.setOnSceneTouchListener(this);
//		
//		mScrollDetector = new SurfaceScrollDetector(this);
//		mScrollDetector.setEnabled(true);
//		
		switchState(STATE_MENU_START);
		
		return scene;
	}

	public void onLoadComplete() {}

	public void onUpdate(float pSecondsElapsed) 
	{
		Second = pSecondsElapsed;
		switch(State_Game_Current)
		{
			case STATE_MENU_START:
				if(Loading.isLoading())
				{
					Loading.updateLoading();
				}
				else
				{
					switchState(STATE_MENU_LOADING);
				}
				break;
			case STATE_MENU_LOADING:
				if (Loading.isLoading())
				{
					Loading.updateLoading();
				}
				else 
				{
					Game.spr_Img_Logo.registerEntityModifier(new FadeOutModifier(1f));
					if(timer(1))
					{
						switchState(STATE_MENU_INMENU);
					}
				}
			break;
			case STATE_MENU_INMENU:
				break;
			case STATE_MENU_SELECT_MAP:
				break;
			case STATE_MENU_SELECT_CARACTER:
				break;
		}
	}

	public void reset() {}

	private void switchState(int curState) {
		State_Game_Current = curState;
		switch(State_Game_Current)
		{
			case STATE_MENU_START:
				scene.setBackground(new ColorBackground(1, 1, 1));
				Loading.setLoading(Loading.LOADING_TYPE_APP_OPEN);
				break;
			case STATE_MENU_LOADING :
				scene.attachChild(Game.spr_Img_Logo);
				Game.spr_Img_Logo.registerEntityModifier(new FadeInModifier(1f));
				Loading.setLoading(Loading.LOADING_TYPE_MAIN_MENU_2);
				break;
			case STATE_MENU_INMENU :
				scene.detachChildren();
				scene.attachChild(Game.spr_Img_Back_Menu);
				scene.attachChild(Game.spr_Img_Title_Menu);
				AnimatedSprite spr_Img_Dadu = new AnimatedSprite(40, 150, Game.reg_Img_Dadu);
				AnimatedSprite spr_Img_Dadu2 = new AnimatedSprite(170, 150, Game.reg_Img_Dadu);
				spr_Img_Dadu.animate(200);
				scene.attachChild(spr_Img_Dadu);
				scene.attachChild(spr_Img_Dadu2);
			
				Sprite spr_Img_Btn_Credit = new Sprite(30, 360, 80,64, Game.reg_Img_Btn_Credit)
				{
					public boolean onAreaTouched(TouchEvent pSceneTouchEvent,
						float pTouchAreaLocalX, float pTouchAreaLocalY)
					{
						State_Menu_Select_Map smsm = new State_Menu_Select_Map();
						mEngine.setScene(smsm.stateMenuSelectMap());
//						startActivity(new Intent(State_Menu_Main.this, State_Menu_Select_Map.class));
						return true;
				
					}
					
				};
				scene.registerTouchArea(spr_Img_Btn_Credit);
				
				Sprite spr_Img_Btn_Option = new Sprite(210, 360, 80, 64, Game.reg_Img_Btn_Option){
					public boolean onAreaTouched(TouchEvent pSceneTouchEvent,
						float pTouchAreaLocalX, float pTouchAreaLocalY){
//							Scene_Option option = new Scene_Option();
//							mEngine.setScene(option.option());
						return true;
				
				}

					
				};
				scene.registerTouchArea(spr_Img_Btn_Option);
				
				scene.attachChild(spr_Img_Btn_Option);
				scene.attachChild(spr_Img_Btn_Credit);
				break;
			case STATE_MENU_SELECT_MAP:
//				scene.detachChildren();
				scene.attachChild(Game.spr_Img_Select_Map_Bg);
//				State_Menu_Select_Map.stateMenuSelectMap();
				break;
			case STATE_MENU_SELECT_CARACTER:
				break;
		}
	}
	
	private static boolean timer(float maxSecond)
	{
		CurrentSecond +=Second;
		if(CurrentSecond >= maxSecond)
		{
			CurrentSecond = 0;
			return true;
		}
		
		return false;
	}
}
