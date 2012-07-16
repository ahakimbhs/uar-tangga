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
import org.anddev.andengine.ui.activity.BaseGameActivity;
import org.anddev.andengine.util.DialogUtils;

import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.Dialog;
import android.content.DialogInterface;
import android.view.KeyEvent;

import com.amikomgamedev.ulartangga.Config;
import com.amikomgamedev.ulartangga.Game;
import com.amikomgamedev.ulartangga.Loading;
import com.amikomgamedev.ulartangga.Utils;

public class State_Menu_Main extends BaseGameActivity
							 implements	IUpdateHandler,
							 			IOnSceneTouchListener
{
	private float CurrentSecond = 0;
	private float Second;
	
	public Camera camera;
	protected Scene scene;
	private MenuScene menuScene;
	private Text mText, mText2;
	
	private BaseGameActivity activity = this;
	
	private final int STATE_MENU_START		= 0;
	private final int STATE_MENU_LOADING	= 1;
	private final int STATE_MENU_INMENU		= 2;
	private final int STATE_MENU_SELECT_MAP	= 3;
	private final int STATE_MENU_SELECT_MC	= 4;
	private int State_Game_Current	= -1;
	
	private int[] State_Level 	= new int[4];
	private int State_Level_Cur	= 0;
	
	Sprite spr_Img_Btn_Play;
	private AlertDialog dialog;

	public Engine onLoadEngine() 
	{
		Utils.TRACE("Engine");
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
		
		switchState(STATE_MENU_START);

		scene.setOnSceneTouchListener(this);
		
		return scene;
	}

	public void onLoadComplete() {Utils.TRACE("Complete");}

	public void onUpdate(float pSecondsElapsed) 
	{
//		Log.v("", "State_Level_Cur = " + State_Level_Cur);
//		Log.v("", "State_Level = " + State_Level[State_Level_Cur]);
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
						switchState(STATE_MENU_INMENU);
				}
				
				break;
			
			case STATE_MENU_INMENU:
				
				
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

				State_Level_Cur = 0;
				State_Level[State_Level_Cur] = STATE_MENU_INMENU;

				System.out.println("ok = " + scene.getChildCount());
				scene.detachChildren();

				inMenu();

				break;
				
			case STATE_MENU_SELECT_MAP :

				State_Level_Cur = 1;
				State_Level[State_Level_Cur] = STATE_MENU_SELECT_MAP;
				System.out.println("ok = " + scene.getChildCount());
				scene.detachChildren();

				State_Menu_Select_Map menu_Map = new State_Menu_Select_Map(scene);
				menu_Map.stateMenuSelectMap();

				System.out.println("ok = " + scene.getChildCount());
				break;
				
			case STATE_MENU_SELECT_MC :
				
				break;
		}
	}
	
	private void inMenu()
	{	
		Game.bgm_Menu.play();
		
		int border = 30;
		
		Sprite spr_Img_Btn_Credit = new Sprite(
				0, 0, 
				Utils.getRatioW(50),
				Utils.getRatioH(50),
				Game.reg_Img_Btn_Credit);
		
		Sprite spr_Img_Btn_Option = new Sprite(
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

		try
		{
			scene.attachChild(Game.spr_Img_Back_Menu);
			scene.getLastChild().attachChild(Game.spr_Img_Title_Menu);
			scene.getLastChild().attachChild(spr_Img_Btn_Credit);
			scene.getLastChild().attachChild(spr_Img_Btn_Option);
			scene.getLastChild().attachChild(spr_Img_Btn_Play);
		}
		catch (Exception e) 
		{
			// TODO: handle exception
		}
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
		if(keyCode == KeyEvent.KEYCODE_BACK)
		{
			if(State_Level[State_Level_Cur] == STATE_MENU_INMENU)
			{
				dialog = new AlertDialog.Builder(this)
				.setMessage("Are You Sure?")
				.setPositiveButton("yes", new DialogInterface.OnClickListener() {
					
					public void onClick(DialogInterface dialog, int which) {
						finish();
					}
				})
				.setPositiveButton("no", new DialogInterface.OnClickListener() {
					
					public void onClick(DialogInterface dialog, int which) {
						dialog.cancel();
					}
				}).create();
			}
			else
			{
				State_Level_Cur--;
				switchState(State_Level[State_Level_Cur]);
			}
		}
		
		return false;
	}

	public boolean onSceneTouchEvent(Scene pScene, TouchEvent pSceneTouchEvent) {
		switch (State_Game_Current) 
		{
			case STATE_MENU_INMENU:
				if(Utils.isOnArea(
						pSceneTouchEvent, 
						spr_Img_Btn_Play))
					switchState(STATE_MENU_SELECT_MAP);
				break;
		}
		return false;
	}
}
