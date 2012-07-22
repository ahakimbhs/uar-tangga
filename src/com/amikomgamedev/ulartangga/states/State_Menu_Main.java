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
import org.anddev.andengine.entity.scene.Scene.IOnSceneTouchListener;
import org.anddev.andengine.entity.scene.background.ColorBackground;
import org.anddev.andengine.entity.scene.menu.MenuScene;
import org.anddev.andengine.entity.sprite.Sprite;
import org.anddev.andengine.entity.text.Text;
import org.anddev.andengine.entity.util.FPSLogger;
import org.anddev.andengine.input.touch.TouchEvent;
import org.anddev.andengine.input.touch.detector.ScrollDetector;
import org.anddev.andengine.input.touch.detector.ScrollDetector.IScrollDetectorListener;
import org.anddev.andengine.input.touch.detector.SurfaceScrollDetector;
import org.anddev.andengine.ui.activity.BaseGameActivity;

import android.content.Intent;
import android.view.KeyEvent;

import com.amikomgamedev.ulartangga.Config;
import com.amikomgamedev.ulartangga.Data;
import com.amikomgamedev.ulartangga.Define;
import com.amikomgamedev.ulartangga.Game;
import com.amikomgamedev.ulartangga.Loading;
import com.amikomgamedev.ulartangga.Utils;
import com.amikomgamedev.ulartangga.serverData;

public class State_Menu_Main extends BaseGameActivity
							 implements	IUpdateHandler,
					 					IScrollDetectorListener,
							 			IOnSceneTouchListener
{
	private float CurrentSecond = 0;
	private float Second;
	
	public Camera camera;
	protected Scene scene;
	private MenuScene menuScene;
	private Text mText, mText2;
	
	private BaseGameActivity activity = this;
	private SurfaceScrollDetector mScrollDetector;
	
	private final int STATE_MENU_START		= 0;
	private final int STATE_MENU_LOADING	= 1;
	private final int STATE_MENU_INMENU		= 2;
	private final int STATE_MENU_SELECT_MAP	= 3;
	private final int STATE_MENU_SELECT_MC	= 4;
	private int State_Game_Current	= -1;
	
	private Sprite spr_Img_Btn_Play;
	private Sprite spr_Img_Btn_Credit;
	private Sprite spr_Img_Btn_Option;
	
	serverData sData = serverData.getInstance();

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

		mScrollDetector = new SurfaceScrollDetector(this);
		scene = new Scene();
		scene.setOnSceneTouchListener(this);
 
		
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
					Loading.updateLoading();
				else
					switchState(STATE_MENU_LOADING);
				
				break;
				
			case STATE_MENU_LOADING:
				
				if (Loading.isLoading())
					Loading.updateLoading();
				else 
				{
					Game.spr_Img_Logo.registerEntityModifier(new FadeOutModifier(0.5f));
					
					if(timer(0.5f))
					{
						scene.detachChild(Game.spr_Img_Logo);
						attachInMenu();
						attachSelectMap();
						switchState(STATE_MENU_INMENU);
					}
				}
				
				break;
				
			case STATE_MENU_INMENU:
				
				
				break;
				
			case STATE_MENU_SELECT_MAP:
				
				for(int i = 0; i < Data.IMG_INGAME_BACKGROUND_MAP.length; i++)
				{
					float minY = Game.spr_Img_Select_Map_Icon_Map[i].getY();
					float maxY = Game.spr_Img_Select_Map_Icon_Map[i].getY() + Game.spr_Img_Select_Map_Icon_Map[i].getHeight();
					float centerY = Game.spr_Img_Select_Map_Bg.getHeight() / 2;
					
					if(minY < centerY && maxY > centerY)
						Game.spr_Img_Select_Map_Icon_Map[i].setScale(1f);
					else
						Game.spr_Img_Select_Map_Icon_Map[i].setScale(0.8f);
						
				}	
				
				break;
				
			case STATE_MENU_SELECT_MC:
				
				
				break;
		}
	}

	public void reset() {}

	private void switchState(int curState) 
	{
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

				Game.spr_Img_Select_Map_Bg.setVisible(false);
				Game.spr_Img_Select_Map_Bg_Select.setVisible(false);
				for(int i = 0; i < Data.IMG_INGAME_BACKGROUND_MAP.length; i++)
				{
					Game.spr_Img_Select_Map_Icon_Map[i].setVisible(false);
				}

				break;
				
			case STATE_MENU_SELECT_MAP :

//				State_Menu_Select_Map menu_Map = new State_Menu_Select_Map(scene);
//				menu_Map.stateMenuSelectMap();

				Game.spr_Img_Select_Map_Bg.setVisible(true);
				Game.spr_Img_Select_Map_Bg_Select.setVisible(true);
				for(int i = 0; i < Data.IMG_INGAME_BACKGROUND_MAP.length; i++)
				{
					Game.spr_Img_Select_Map_Icon_Map[i].setVisible(true);
				}				
				
				break;
				
			case STATE_MENU_SELECT_MC :
				
				break;
		}
	}
	
	private void attachInMenu()
	{
		Game.bgm_Menu.play();
		
		int border = 30;
		
		spr_Img_Btn_Credit = new Sprite(
				0, 0, 
				Utils.getRatioW(50),
				Utils.getRatioH(50),
				Game.reg_Img_Btn_Credit);
		spr_Img_Btn_Option = new Sprite(
				0, 0,
				Utils.getRatioW(50),
				Utils.getRatioH(50),
				Game.reg_Img_Btn_Option);
		spr_Img_Btn_Play = new Sprite(
				0, 0, 
				Utils.getRatioW(140),
				Utils.getRatioH(35),
				Game.reg_Img_Btn_Play);
			
		spr_Img_Btn_Option.setPosition(
				Config.GAME_SCREEN_WIDTH - spr_Img_Btn_Option.getWidth() - Utils.getRatioW(border), 
				Config.GAME_SCREEN_HEIGHT - spr_Img_Btn_Option.getHeight() - Utils.getRatioW(border));
		spr_Img_Btn_Credit.setPosition(
				Utils.getRatioW(border), 
				Config.GAME_SCREEN_HEIGHT - spr_Img_Btn_Credit.getHeight() - border);
		spr_Img_Btn_Play.setPosition(
				(Config.GAME_SCREEN_WIDTH - spr_Img_Btn_Play.getWidth()) / 2, 
				Utils.getRatioH(325)); 

		scene.attachChild(Game.spr_Img_Back_Menu);
		Game.spr_Img_Back_Menu.attachChild(Game.spr_Img_Title_Menu);
		Game.spr_Img_Back_Menu.attachChild(spr_Img_Btn_Credit);
		Game.spr_Img_Back_Menu.attachChild(spr_Img_Btn_Option);
		Game.spr_Img_Back_Menu.attachChild(spr_Img_Btn_Play);
	
	}
	
	private void attachSelectMap()
	{
		scene.attachChild(Game.spr_Img_Select_Map_Bg);
//		Game.spr_Img_Select_Map_Bg.attachChild(Game.spr_Img_Select_Map_Bg_Select);
		for(int i = 0; i < Data.IMG_INGAME_BACKGROUND_MAP.length; i++)
			Game.spr_Img_Select_Map_Bg.attachChild(Game.spr_Img_Select_Map_Icon_Map[i]);
//			Game.spr_Img_Select_Map_Bg_Select.attachChild(Game.spr_Img_Select_Map_Icon_Map[i]);
		Game.spr_Img_Select_Map_Bg.attachChild(Game.spr_Img_Select_Map_Btn_Back);
	}
	
	private boolean timer(float maxSecond)
	{
		CurrentSecond +=Second;
		if(CurrentSecond >= maxSecond)
		{
			CurrentSecond = 0;
			return true;
		}
		
		return false;
	}
	
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) 
	{
		switch (State_Game_Current) 
		{
			case STATE_MENU_START:
				
				break;
				
			case STATE_MENU_LOADING:
				
				break;
				
			case STATE_MENU_INMENU:
				
				if(keyCode == KeyEvent.KEYCODE_BACK)
				{
					finish();
				}
				
				break;
				
			case STATE_MENU_SELECT_MAP:
				
				if(keyCode == KeyEvent.KEYCODE_BACK)
				{					
					switchState(STATE_MENU_INMENU);
				}
				
				break;
				
			case STATE_MENU_SELECT_MC:
				
				break;
		}
		
		return false;
	}

	public boolean onSceneTouchEvent(Scene pScene, TouchEvent pSceneTouchEvent) 
	{
		
		switch (State_Game_Current) 
		{
			case STATE_MENU_START:
				
				break;
				
			case STATE_MENU_LOADING:
				
				break;
			
			case STATE_MENU_INMENU:
				
				if(Utils.isOnArea(pSceneTouchEvent, spr_Img_Btn_Play)
						&& pSceneTouchEvent.isActionUp())
					switchState(STATE_MENU_SELECT_MAP);
				
				break;
				
			case STATE_MENU_SELECT_MAP:
				
				mScrollDetector.onTouchEvent(pSceneTouchEvent);
//				Utils.TRACE("scaleCX = " +Game.spr_Img_Select_Map_Icon_Map[0].getScaleCenterX());
//				Utils.TRACE("scaleCY = " +Game.spr_Img_Select_Map_Icon_Map[0].getScaleCenterY());
//				Utils.TRACE("scaleX = " +Game.spr_Img_Select_Map_Icon_Map[0].getScaleX());
//				Utils.TRACE("scaleY = " +Game.spr_Img_Select_Map_Icon_Map[0].getScaleY());
				
				if(Utils.isOnArea(pSceneTouchEvent, Game.spr_Img_Select_Map_Btn_Back)
						&& pSceneTouchEvent.isActionUp())
					switchState(STATE_MENU_INMENU);
				
				for(int i = 0; i < Data.IMG_INGAME_BACKGROUND_MAP.length; i++)
				{
					// dibatasi 2 map dulu
					if(i < 2)
						if(Utils.isOnArea(pSceneTouchEvent, Game.spr_Img_Select_Map_Icon_Map[i])
								&& pSceneTouchEvent.isActionUp()
								&& Game.spr_Img_Select_Map_Icon_Map[i].getScaleX() == 1)
						{
							if(i == Define.MAP_MODERN)
								sData.setSelectMap(Define.MAP_MODERN);
							else if(i == Define.MAP_KLASIK)
								sData.setSelectMap(Define.MAP_KLASIK);
							
							startActivity(new Intent(State_Menu_Main.this, State_Gameplay.class));
							finish();
						}
				}
				
				break;
				
			case STATE_MENU_SELECT_MC:
				
				break;
		}
		return true;
	}

	public void onScroll(ScrollDetector pScollDetector, TouchEvent pTouchEvent,
			float pDistanceX, float pDistanceY)
	{
		for(int i = 0; i < Data.IMG_INGAME_BACKGROUND_MAP.length; i++)
			Game.spr_Img_Select_Map_Icon_Map[i].setPosition(
					Game.spr_Img_Select_Map_Icon_Map[i].getX(),
					Game.spr_Img_Select_Map_Icon_Map[i].getY() + pDistanceY);
//		Game.spr_Img_Select_Map_Bg_Select.setPosition(
//				Game.spr_Img_Select_Map_Bg_Select.getX(),
//				Game.spr_Img_Select_Map_Bg_Select.getY() + pDistanceY);
	}
}
