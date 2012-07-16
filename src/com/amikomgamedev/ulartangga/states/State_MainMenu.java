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
import org.anddev.andengine.entity.text.Text;
import org.anddev.andengine.entity.util.FPSLogger;
import org.anddev.andengine.input.touch.TouchEvent;
import org.anddev.andengine.ui.activity.BaseGameActivity;

import android.content.Intent;

import com.amikomgamedev.ulartangga.Config;
import com.amikomgamedev.ulartangga.Game;
import com.amikomgamedev.ulartangga.Loading;
import com.amikomgamedev.ulartangga.Utils;
import com.amikomgamedev.ulartangga.scene.Scene_Credits;
import com.amikomgamedev.ulartangga.scene.Scene_Option;

public class State_MainMenu extends BaseGameActivity 
							implements IUpdateHandler{
	
	private static final int STATE_GAME_START		= 0;
	private static final int STATE_GAME_LOADING		= 1;
	private static final int STATE_GAME_MENU		= 2;
	private static final int STATE_GAME_OPTION		= 3;

	private static int State_Game_Current			= 0;
	public static float Second;
	private static float CurrentSecond = 0;
	private Scene mScene;
	private Camera mCamera;
	private Text mText;
	
	//	public static final float x = (Config.GAME_SCREEN_WIDTH - Game.font[1].getTexture().getWidth()) / 2; 
	private BaseGameActivity activity;
	
	public Engine onLoadEngine() {
		Game.appInit();
		mCamera = new Camera(0, 0, Config.GAME_SCREEN_WIDTH, Config.GAME_SCREEN_HEIGHT);
		mEngine = new Engine(
			new EngineOptions(true, ScreenOrientation.PORTRAIT,
			new RatioResolutionPolicy(Config.GAME_SCREEN_WIDTH, Config.GAME_SCREEN_HEIGHT),
			mCamera).setNeedsMusic(true).setNeedsSound(true));
		return mEngine;
		
	}

	public void onLoadResources() {
		Game.setContext(this);
	
	}

	public Scene onLoadScene() {
		mEngine.registerUpdateHandler(new FPSLogger());
		mEngine.registerUpdateHandler(this);
	
		sceneMenu();
		return mScene;

		
	}
	
	public Scene sceneMenu () {
		mScene = new Scene();
		switchState(STATE_GAME_START);
		return mScene;
	}
	
	public void onLoadComplete() {}
	
	private void switchState(int state) {
		State_Game_Current = state;
		switch(State_Game_Current) {
			case STATE_GAME_START :
				mScene.setBackground(new ColorBackground(1, 1, 1));
				Loading.setLoading(Loading.LOADING_TYPE_APP_OPEN);
				break;
			case STATE_GAME_LOADING :
				mScene.attachChild(Game.spr_Img_Logo);
				Game.spr_Img_Logo.registerEntityModifier(new FadeInModifier(1f));
				Loading.setLoading(Loading.LOADING_TYPE_MAIN_MENU);
				break;
			case STATE_GAME_MENU :
				Game.bgm_Menu.play();
				mScene.detachChildren();
				mScene.attachChild(Game.spr_Img_Back_Menu);
				mScene.attachChild(Game.spr_Img_Title_Menu);
				
				AnimatedSprite spr_Img_Dadu = new AnimatedSprite(40, 150, Game.reg_Img_Dadu);
				AnimatedSprite spr_Img_Dadu2 = new AnimatedSprite(170, 150, Game.reg_Img_Dadu);
				spr_Img_Dadu.animate(200);
//				mScene.attachChild(spr_Img_Dadu);
//				mScene.attachChild(spr_Img_Dadu2);
				
				int border = 30;
				
					Sprite spr_Img_Btn_Credit = new Sprite(0, 0, 
							Utils.getRatioW(50),
							Utils.getRatioH(50),
							Game.reg_Img_Btn_Credit){
						public boolean onAreaTouched(TouchEvent pSceneTouchEvent,
							float pTouchAreaLocalX, float pTouchAreaLocalY){
							Scene_Credits sCredits = new Scene_Credits();
							mEngine.setScene(sCredits.credits(activity, mEngine));
							return true;
					
						}
					
				};
				spr_Img_Btn_Credit.setPosition(
						Utils.getRatioW(border), 
						Config.GAME_SCREEN_HEIGHT - spr_Img_Btn_Credit.getHeight() - border);
				mScene.registerTouchArea(spr_Img_Btn_Credit);
				
				Sprite spr_Img_Btn_Option = new Sprite(210, 360,
						Utils.getRatioW(50),
						Utils.getRatioH(50),
						Game.reg_Img_Btn_Option){
					
					public boolean onAreaTouched(TouchEvent pSceneTouchEvent,
						float pTouchAreaLocalX, float pTouchAreaLocalY){
						Scene_Option option = new Scene_Option();
						mEngine.setScene(option.option(activity, mEngine));
						return true;
					}
				};
				spr_Img_Btn_Option.setPosition(
						Config.GAME_SCREEN_WIDTH - spr_Img_Btn_Option.getWidth() - Utils.getRatioW(border), 
						Config.GAME_SCREEN_HEIGHT - spr_Img_Btn_Option.getHeight() - Utils.getRatioW(border));
				mScene.registerTouchArea(spr_Img_Btn_Option);
				
				mScene.attachChild(spr_Img_Btn_Option);
				mScene.attachChild(spr_Img_Btn_Credit);
//				mText = new Text(100, 300, Game.font[1],"Let's Play" ){
				Sprite spr_Img_Btn_Play = new Sprite(
						0, 0, 
						Utils.getRatioW(140),
						Utils.getRatioH(35),
						Game.reg_Img_Btn_Play)
				{
					@Override
					public boolean onAreaTouched(TouchEvent pSceneTouchEvent,
							float pTouchAreaLocalX, float pTouchAreaLocalY) {
						startActivity(new Intent(State_MainMenu.this, State_Gameplay.class));
//					mEngine.setScene(chosePlayerType());
						finish();
						return true;
								
					}
				};

//				mScene.registerTouchArea(mText);
				spr_Img_Btn_Play.setPosition(
						(Config.GAME_SCREEN_WIDTH - spr_Img_Btn_Play.getWidth()) / 2, 
						Utils.getRatioH(325)); 
				mScene.registerTouchArea(spr_Img_Btn_Play);
				
//				mText.setColor(0, 0, 0);
//				mScene.attachChild(mText);
				mScene.attachChild(spr_Img_Btn_Play);
				break;
			case STATE_GAME_OPTION :
				
		}
		
		
	}
	
	public void onUpdate(float secondUpdate) {
			Second = secondUpdate;
			
			switch (State_Game_Current) {
			case STATE_GAME_START:

				if (Loading.isLoading()){
					Loading.updateLoading();
				}
				else {
					switchState(STATE_GAME_LOADING);
				}
				break;

			case STATE_GAME_LOADING:
				if (Loading.isLoading()){
					Loading.updateLoading();
				}
				else {
					Game.spr_Img_Logo.registerEntityModifier(new FadeOutModifier(1f));
					if(timer(1))
					switchState(STATE_GAME_MENU);
				}
			break;

			case STATE_GAME_MENU:
				if (Loading.isLoading()){
					Loading.updateLoading();
				}
				else {
					switchState(STATE_GAME_OPTION);
				}
			break;
				
			}
	}

	public void reset() {}
	
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
