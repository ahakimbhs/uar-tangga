package com.amikomgamedev.ulartangga.states;

import org.anddev.andengine.engine.Engine;
import org.anddev.andengine.engine.camera.Camera;
import org.anddev.andengine.engine.handler.IUpdateHandler;
import org.anddev.andengine.engine.options.EngineOptions;
import org.anddev.andengine.engine.options.EngineOptions.ScreenOrientation;
import org.anddev.andengine.engine.options.resolutionpolicy.RatioResolutionPolicy;
import org.anddev.andengine.entity.scene.Scene;
import org.anddev.andengine.entity.scene.Scene.IOnSceneTouchListener;
import org.anddev.andengine.entity.scene.background.ColorBackground;
import org.anddev.andengine.entity.scene.menu.MenuScene;
import org.anddev.andengine.entity.sprite.Sprite;
import org.anddev.andengine.entity.text.Text;
import org.anddev.andengine.entity.util.FPSLogger;
import org.anddev.andengine.input.touch.TouchEvent;
import org.anddev.andengine.input.touch.detector.ClickDetector;
import org.anddev.andengine.input.touch.detector.ClickDetector.IClickDetectorListener;
import org.anddev.andengine.input.touch.detector.ScrollDetector;
import org.anddev.andengine.input.touch.detector.ScrollDetector.IScrollDetectorListener;
import org.anddev.andengine.input.touch.detector.SurfaceScrollDetector;
import org.anddev.andengine.ui.activity.BaseGameActivity;
import org.anddev.andengine.util.Debug;

import android.content.Intent;
import android.view.KeyEvent;

import com.amikomgamedev.ulartangga.Config;
import com.amikomgamedev.ulartangga.Data;
import com.amikomgamedev.ulartangga.Define;
import com.amikomgamedev.ulartangga.Game;
import com.amikomgamedev.ulartangga.Loading;
import com.amikomgamedev.ulartangga.SoundManager;
import com.amikomgamedev.ulartangga.Utils;
import com.amikomgamedev.ulartangga.serverData;

public class State_Menu_Main extends BaseGameActivity
							 implements	IUpdateHandler,
					 					IScrollDetectorListener,
							 			IOnSceneTouchListener,
							 			IClickDetectorListener,
							 			Define
{
	private float CurrentSecond = 0;
	private float Second;
	
	public Camera camera;
	private MenuScene menuScene;
	private Text mText, mText2;

	protected Scene 				scene = new Scene();
	protected SurfaceScrollDetector	scrollDetector = new SurfaceScrollDetector(this);
	protected ClickDetector			clickDetector = new ClickDetector(this);
//	private State_Menu_Select_Map	select_Map;
	
	private final int STATE_MENU_START		= 0;
	private final int STATE_MENU_LOADING	= 1;
	private final int STATE_MENU_INMENU		= 2;
	private final int STATE_MENU_SELECT_MAP_LOADING	= 3;
	private final int STATE_MENU_SELECT_MAP	= 4;
	private final int STATE_MENU_SELECT_MC_LOADING	= 5;
	private final int STATE_MENU_SELECT_MC	= 6;
	private final int STATE_MENU_SELECT_PLAYER = 7;
	private final int STATE_MENU_CREDIT 	= 8;
	private final int STATE_MENU_OPTION 	= 9;

	private int State_Game_Current	= -1;
	
	private Sprite spr_Img_Btn_Play;
	private Sprite spr_Img_Btn_Credit;
	private Sprite spr_Img_Btn_Option;
	
	private float timeMove = 0.5f;
	private float velocity = 0;
	private int max_Player = 2;
	
	float minY = 0;
	float maxY = 0;
	float centerY = 0;
	float distance = 0;
	
	private boolean isActionUp = false;

	int k = 0;
	
	private Sprite spr_Img_Logo;
	private Sprite spr_Img_Title;
	
	serverData sData = serverData.getInstance();
	
	private float y = 0;

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
		Game.spr_Img_Select_Map_Bg = null;
		Game.spr_Img_Select_Mc_Bg = null;
	}

	public Scene onLoadScene() 
	{
		mEngine.registerUpdateHandler(new FPSLogger());
		mEngine.registerUpdateHandler(this);
		
		scene.setOnSceneTouchListener(this);
 
		switchState(STATE_MENU_START);
		
		return scene;
	}

	public void onLoadComplete() {}

	public void onUpdate(float pSecondsElapsed) 
	{
		Second = pSecondsElapsed;
//		long max	= Runtime.getRuntime().maxMemory();
//		long total	= Runtime.getRuntime().totalMemory();
//		long free	= Runtime.getRuntime().freeMemory();
//		
//		Log.d("Memory", "Max = " +max);
//		Log.d("Memory", "Total = " +total);
//		Log.d("Memory", "Free = " +free);
		
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
					Game.spr_Img_Loading.detachSelf();
					attachInMenu();
					switchState(STATE_MENU_INMENU);
				}
				
				break;
				
			case STATE_MENU_INMENU:
				
				
				break;
				
			case STATE_MENU_SELECT_MAP_LOADING:
				
				if(Loading.isLoading())
					Loading.updateLoading();
				else
				{
					Game.spr_Img_Loading.detachSelf();
					attachSelectMap();
					switchState(STATE_MENU_SELECT_MAP);
				}
				break;
				
			case STATE_MENU_SELECT_MAP:
				
				for(int i = 0; i < Data.IMG_INGAME_BACKGROUND_MAP.length; i++)
				{
					minY = Game.spr_Img_Select_Map_Bg_Select.getY() + Game.spr_Img_Select_Map_Icon_Map[i].getY();
					maxY = Game.spr_Img_Select_Map_Bg_Select.getY() + Game.spr_Img_Select_Map_Icon_Map[i].getY() + Game.spr_Img_Select_Map_Icon_Map[i].getHeight();
					centerY = Game.spr_Img_Select_Map_Bg.getHeight() / 2;
					
					if(minY < centerY && maxY > centerY)
						Game.spr_Img_Select_Map_Icon_Map[i].setScale(1f);
					else
						Game.spr_Img_Select_Map_Icon_Map[i].setScale(0.8f);

					if(isActionUp && Game.spr_Img_Select_Map_Icon_Map[i].getScaleX() == 1)
					{
						centerY	= (Config.GAME_SCREEN_HEIGHT - Game.spr_Img_Select_Map_Icon_Map[i].getHeight()) / 2;
						minY		= Game.spr_Img_Select_Map_Bg_Select.getY() + Game.spr_Img_Select_Map_Icon_Map[i].getY();
						distance	= centerY - minY;
						velocity	= distance / timeMove;
						
						isActionUp = false;
					}
					
					
					centerY	= (Config.GAME_SCREEN_HEIGHT - Game.spr_Img_Select_Map_Icon_Map[0].getHeight()) / 2;
					minY	= Game.spr_Img_Select_Map_Bg_Select.getY() + Game.spr_Img_Select_Map_Icon_Map[i].getY();
					
					if(velocity > 0 && Game.spr_Img_Select_Map_Icon_Map[i].getScaleX() == 1
							&& minY >= centerY)
						velocity = 0;
					else if(velocity < 0 && Game.spr_Img_Select_Map_Icon_Map[i].getScaleX() == 1
							&& minY <= centerY)
						velocity = 0;
				}
				
				minY = Game.spr_Img_Select_Map_Bg_Select.getY();
				maxY = Game.spr_Img_Select_Map_Bg_Select.getY() + Game.spr_Img_Select_Map_Bg_Select.getHeight();
				
				if(minY > centerY)
				{
					distance	= centerY - minY;
					velocity	= distance / timeMove;
				}
				else if(maxY < centerY + Game.spr_Img_Select_Map_Icon_Map[0].getHeight())
				{
					distance	= centerY + Game.spr_Img_Select_Map_Icon_Map[0].getHeight() - maxY;
					velocity	= distance / timeMove;
				}
				
				if(velocity != 0)
				{
					Game.spr_Img_Select_Map_Bg_Select.setPosition(
							Game.spr_Img_Select_Map_Bg_Select.getX(),
							Game.spr_Img_Select_Map_Bg_Select.getY() + velocity * pSecondsElapsed);
				}
				
				break;
				
			case STATE_MENU_SELECT_MC_LOADING:
				
				if(Loading.isLoading())
					Loading.updateLoading();
				else
				{
					Game.spr_Img_Loading.detachSelf();
					attachSelectMc();
					switchState(STATE_MENU_SELECT_MC);
				}
				break;
				
			case STATE_MENU_SELECT_MC:
				
				break;
			case STATE_MENU_CREDIT:
			
				
				Game.spr_Img_Title_Menu.setPosition((Config.GAME_SCREEN_WIDTH - Game.spr_Img_Title_Menu.getWidth()) /2, y);
				Debug.d("Y = "+y);
				y-=3;
				
//				y = y -pSecondsElapsed;
				if(y < 0)
				Game.spr_Img_Title_Menu.setPosition(Config.GAME_SCREEN_WIDTH - Game.spr_Img_Title_Menu.getWidth() /2,y);
				
				break;
								
		}
		
		
	}

	public void reset() {}

	public void switchState(int curState) 
	{
		State_Game_Current = curState;

		switch(State_Game_Current)
		{
			case STATE_MENU_START:
				
				scene.setBackground(new ColorBackground(1, 1, 1));
				Loading.setLoading(Loading.LOADING_TYPE_MAIN_MENU_OPEN);
				
				break;
				
			case STATE_MENU_LOADING :

				scene.attachChild(Game.spr_Img_Loading);
				Loading.setLoading(Loading.LOADING_TYPE_MAIN_MENU_2);
				
				break;
				
			case STATE_MENU_INMENU :

				SoundManager.playMusic(SoundManager.BGM_MENU_MAIN);
				Game.spr_Img_Logo.setVisible(false);
				
				break;

			case STATE_MENU_SELECT_MAP_LOADING:

				SoundManager.stopMusic(SoundManager.BGM_MENU_MAIN);
				
				scene.attachChild(Game.spr_Img_Loading);
				Loading.setLoading(Loading.LOADING_TYPE_SELECT_MAP);
				
				break;
				
			case STATE_MENU_SELECT_MAP :

				SoundManager.playMusic(SoundManager.BGM_MENU_MAIN);
				Game.spr_Img_Select_Map_Bg.setVisible(true);			
				
				break;

			case STATE_MENU_SELECT_MC_LOADING:

				SoundManager.stopMusic(SoundManager.BGM_MENU_MAIN);

				scene.attachChild(Game.spr_Img_Loading);
				Game.setMap(sData.getSelectMap());
				Loading.setLoading(Loading.LOADING_TYPE_SELECT_MC);
				
				break;
				
			case STATE_MENU_SELECT_MC :

				SoundManager.playMusic(SoundManager.BGM_MENU_MAIN);
				Game.spr_Img_Select_Mc_Bg.setVisible(true);
				
				break;
			case STATE_MENU_CREDIT:
				
				spr_Img_Btn_Play.setVisible(false);
				spr_Img_Btn_Option.setVisible(false);
				spr_Img_Btn_Credit.setVisible(false);
				y = Config.GAME_SCREEN_HEIGHT;
				Game.spr_Img_Title_Menu.setPosition((Config.GAME_SCREEN_WIDTH - Game.spr_Img_Title_Menu.getWidth()) /2, y);
				
				
				break;
		}
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
					Game.spr_Img_Select_Map_Bg.setVisible(false);
					switchState(STATE_MENU_SELECT_MC);
				}
				break;
				
			case STATE_MENU_SELECT_MC:
				
				if(keyCode == KeyEvent.KEYCODE_BACK)
				{
					Game.spr_Img_Select_Mc_Bg.setVisible(false);
					switchState(STATE_MENU_INMENU);
				}
				
				break;
			case STATE_MENU_CREDIT:
				if(keyCode == KeyEvent.KEYCODE_BACK)
				{	
					
					switchState(STATE_MENU_INMENU);
					Game.spr_Img_Title_Menu.setPosition((Config.GAME_SCREEN_WIDTH - Game.spr_Img_Title_Menu.getWidth()) /2, 50);
					spr_Img_Btn_Play.setVisible(true);
					spr_Img_Btn_Option.setVisible(true);
					spr_Img_Btn_Credit.setVisible(true);
				}
				break;
		}
		
		return false;
	}

	public boolean onSceneTouchEvent(Scene pScene, TouchEvent pSceneTouchEvent) 
	{
		scrollDetector.onTouchEvent(pSceneTouchEvent);
		clickDetector.onTouchEvent(pSceneTouchEvent);	
		
		switch (State_Game_Current) 
		{
			case STATE_MENU_START:
				
				break;
				
			case STATE_MENU_LOADING:
				
				break;
			
			case STATE_MENU_INMENU:
				
				if(Utils.isOnArea(pSceneTouchEvent, spr_Img_Btn_Play)
						&& pSceneTouchEvent.isActionUp())
				{
					// sData bawah ni gx perlu d perhatikan
					// konsep yg ada d pikiran q
					// ternyata beda sama yg d inginkan game designer
					sData.setSelectMap(MAP_KLASIK);
					
					if(Game.spr_Img_Select_Mc_Bg == null)
						switchState(STATE_MENU_SELECT_MC_LOADING);
					else
						switchState(STATE_MENU_SELECT_MC);
				}
				else if(Utils.isOnArea(pSceneTouchEvent, spr_Img_Btn_Credit)
						&& pSceneTouchEvent.isActionUp())
					switchState(STATE_MENU_CREDIT);
				
				break;
				
			case STATE_MENU_SELECT_MAP:
				
				if(pSceneTouchEvent.isActionUp())
					isActionUp = true;
				
				if(Utils.isOnArea(
						pSceneTouchEvent,
						Game.spr_Img_Select_Map_Btn_Back)
						&& pSceneTouchEvent.isActionUp())
				{
					Game.spr_Img_Select_Map_Bg.setVisible(false);
					switchState(STATE_MENU_SELECT_MC);
				}
				
				break;
				
			case STATE_MENU_SELECT_MC:
				
			if(Utils.isOnArea(
					pSceneTouchEvent,
					Game.spr_Img_Select_Mc_Btn_Arrow[0])
					&& pSceneTouchEvent.isActionUp())
			{
				Game.spr_Img_Select_Mc_Bg.setVisible(false);
				switchState(STATE_MENU_INMENU);
			}
				
				break;
		}
		return true;
	}

	public void onScroll(ScrollDetector pScollDetector, TouchEvent pTouchEvent,
			float pDistanceX, float pDistanceY)
	{
		switch (State_Game_Current) 
		{
			case STATE_MENU_SELECT_MAP:
		
				Game.spr_Img_Select_Map_Bg_Select.setPosition(
						Game.spr_Img_Select_Map_Bg_Select.getX(),
						Game.spr_Img_Select_Map_Bg_Select.getY() + pDistanceY);
				
				break;
		}
	}

	public void onClick(ClickDetector pClickDetector, TouchEvent pTouchEvent) {

		switch (State_Game_Current) 
		{
			case STATE_MENU_START:
				
				break;
				
			case STATE_MENU_LOADING:
				
				break;
			
			case STATE_MENU_INMENU:
				
				break;
				
			case STATE_MENU_SELECT_MAP:
				
				for(int i = 0; i < Data.IMG_INGAME_BACKGROUND_MAP.length; i++)
				{
					// dibatasi 2 map dulu
					if(i < 2)
					{
						if(Utils.isOnArea(
								pTouchEvent,
								Game.spr_Img_Select_Map_Bg_Select,
								Game.spr_Img_Select_Map_Icon_Map[i])
								&& Game.spr_Img_Select_Map_Icon_Map[i].getScaleX() == 1)
						{
							sData.setSelectMap(i);
							startActivity(new Intent(State_Menu_Main.this, State_Gameplay.class));
							finish();
						}
					}
				}
				
				break;
				
			case STATE_MENU_SELECT_MC:

				if(Utils.isOnArea(pTouchEvent, Game.spr_Img_Select_Mc_Btn_Arrow[1]))
				{
					sData.setMaxPlayer(max_Player);
					
					if(Game.spr_Img_Select_Map_Bg == null)
						switchState(STATE_MENU_SELECT_MAP_LOADING);
					else
						switchState(STATE_MENU_SELECT_MAP);
				}
				
				if(Utils.isOnArea(
						pTouchEvent,
						Game.spr_Img_Select_Mc_Btn_Add)
						&& max_Player < 4)
				{
					sData.setCharPlayer(max_Player, CARACTER_1);
					sData.setTypePlayer(max_Player, TYPE_AI);

					Game.spr_Img_Select_Mc_Icon_Mc[max_Player][sData.getCharPlayer(max_Player)].setVisible(true);
					Game.spr_Img_Select_Mc_Btn_Type[max_Player][sData.getTypePlayer(max_Player)].setVisible(true);
					
					max_Player+=1;
					
					if(max_Player == 3)
					{
						Game.spr_Img_Select_Mc_Icon_Mc_Bg[2].setPosition(
								(Config.GAME_SCREEN_WIDTH - Game.spr_Img_Select_Mc_Icon_Mc_Bg[2].getWidth()) / 2,
								Game.spr_Img_Select_Mc_Icon_Mc_Bg[0].getY() + Game.spr_Img_Select_Mc_Icon_Mc_Bg[0].getHeight() + Utils.getRatioH(50));
						
						Game.spr_Img_Select_Mc_Btn_Add.setPosition(
								Game.spr_Img_Select_Mc_Btn_Add.getX(),
								Game.spr_Img_Select_Mc_Icon_Mc_Bg[2].getY() + Game.spr_Img_Select_Mc_Icon_Mc_Bg[2].getHeight() + Utils.getRatioH(50));
						
						Game.spr_Img_Select_Mc_Icon_Mc_Bg[2].setVisible(true);
					}
					else if(max_Player == 4)
					{
						Game.spr_Img_Select_Mc_Icon_Mc_Bg[2].setVisible(false);
						
						Game.spr_Img_Select_Mc_Icon_Mc_Bg[2].setPosition(
								(Config.GAME_SCREEN_WIDTH / 2 - Game.spr_Img_Select_Mc_Icon_Mc_Bg[2].getWidth()) / 2,
								Game.spr_Img_Select_Mc_Icon_Mc_Bg[0].getY() + Game.spr_Img_Select_Mc_Icon_Mc_Bg[0].getHeight() + Utils.getRatioH(50));
						Game.spr_Img_Select_Mc_Icon_Mc_Bg[3].setPosition(
								Config.GAME_SCREEN_WIDTH / 2 + (Config.GAME_SCREEN_WIDTH / 2 - Game.spr_Img_Select_Mc_Icon_Mc_Bg[3].getWidth()) / 2,
								Game.spr_Img_Select_Mc_Icon_Mc_Bg[0].getY() + Game.spr_Img_Select_Mc_Icon_Mc_Bg[0].getHeight() + Utils.getRatioH(50));

						Game.spr_Img_Select_Mc_Icon_Mc_Bg[2].setVisible(true);
						Game.spr_Img_Select_Mc_Icon_Mc_Bg[3].setVisible(true);
					}
				}
				
				for (int i = 0; i < max_Player; i++)
				{
					if(i < max_Player - 2)
						if(Utils.isOnArea(
								pTouchEvent,
								Game.spr_Img_Select_Mc_Icon_Mc_Bg[i + 2],
								Game.spr_Img_Select_Mc_Btn_Delete[i]))
						{
							
							if(max_Player > 2)
								max_Player-=1;
							
							if(max_Player == 3)
							{
								Game.spr_Img_Select_Mc_Icon_Mc[3][sData.getCharPlayer(3)].setVisible(false);
								Game.spr_Img_Select_Mc_Icon_Mc[2][sData.getCharPlayer(2)].setVisible(false);
								Game.spr_Img_Select_Mc_Btn_Type[3][sData.getTypePlayer(3)].setVisible(false);
								Game.spr_Img_Select_Mc_Btn_Type[2][sData.getTypePlayer(2)].setVisible(false);
								Game.spr_Img_Select_Mc_Icon_Mc_Bg[3].setVisible(false);
								Game.spr_Img_Select_Mc_Icon_Mc_Bg[2].setVisible(false);

								if(i == 0)
								{
									sData.setTypePlayer(2, sData.getTypePlayer(3));
									sData.setCharPlayer(2, sData.getCharPlayer(3));
								}
								
								Game.spr_Img_Select_Mc_Icon_Mc_Bg[2].setPosition(
										(Config.GAME_SCREEN_WIDTH - Game.spr_Img_Select_Mc_Icon_Mc_Bg[2].getWidth()) / 2,
										Game.spr_Img_Select_Mc_Icon_Mc_Bg[0].getY() + Game.spr_Img_Select_Mc_Icon_Mc_Bg[0].getHeight() + Utils.getRatioH(50));

								Game.spr_Img_Select_Mc_Btn_Type[2][sData.getTypePlayer(2)].setVisible(true);
								Game.spr_Img_Select_Mc_Icon_Mc[2][sData.getCharPlayer(2)].setVisible(true);
								Game.spr_Img_Select_Mc_Icon_Mc_Bg[2].setVisible(true);
							}
							else
							{
								Game.spr_Img_Select_Mc_Icon_Mc[2][sData.getCharPlayer(2)].setVisible(false);
								Game.spr_Img_Select_Mc_Btn_Type[2][sData.getTypePlayer(2)].setVisible(false);
								Game.spr_Img_Select_Mc_Icon_Mc_Bg[2].setVisible(false);
								
								Game.spr_Img_Select_Mc_Btn_Add.setPosition(
										Game.spr_Img_Select_Mc_Btn_Add.getX(),
										Game.spr_Img_Select_Mc_Icon_Mc_Bg[0].getY() + Game.spr_Img_Select_Mc_Icon_Mc_Bg[0].getHeight() + Utils.getRatioH(50));
							}
						}
					
					if(Utils.isOnArea(
							pTouchEvent, 
							Game.spr_Img_Select_Mc_Icon_Mc_Bg[i], 
							Game.spr_Img_Select_Mc_Btn_Type[i][0]))
					{
						Game.spr_Img_Select_Mc_Btn_Type[i][sData.getTypePlayer(i)].setVisible(false);
						
						if(sData.getTypePlayer(i) == TYPE_PLAYER)
							sData.setTypePlayer(i, TYPE_AI);
						else
							sData.setTypePlayer(i, TYPE_PLAYER);
						
						Game.spr_Img_Select_Mc_Btn_Type[i][sData.getTypePlayer(i)].setVisible(true);
					}
					
					for (int j = 0; j < 2; j++)
					{
						if(Utils.isOnArea(
								pTouchEvent,
								Game.spr_Img_Select_Mc_Icon_Mc_Bg[i],
								Game.spr_Img_Select_Mc_Btn_Arrow_Mc[i][j]))
						{
							Game.spr_Img_Select_Mc_Icon_Mc[i][sData.getCharPlayer(i)].setVisible(false);
							
							if(j == 0)
								if(sData.getCharPlayer(i) == CARACTER_1)
									sData.setCharPlayer(i, CARACTER_4);
								else
									sData.setCharPlayer(i, sData.getCharPlayer(i) - 1);
							else
								if(sData.getCharPlayer(i) == CARACTER_4)
									sData.setCharPlayer(i, CARACTER_1);
								else
									sData.setCharPlayer(i, sData.getCharPlayer(i) + 1);
							
							Game.spr_Img_Select_Mc_Icon_Mc[i][sData.getCharPlayer(i)].setVisible(true);
						}
					}
				}
				
				break;
			case STATE_MENU_CREDIT:
				
				break;
		}
	}
	
	protected Scene getScene()
	{
		return scene;
	}
	
	private void attachInMenu()
	{
		
		int border = 30;
		
		spr_Img_Btn_Credit = new Sprite(
				0, 0, 
				Utils.getRatio(50),
				Utils.getRatio(50),
				Game.reg_Img_Btn_Credit);
		spr_Img_Btn_Option = new Sprite(
				0, 0,
				Utils.getRatio(50),
				Utils.getRatio(50),
				Game.reg_Img_Btn_Option);
		spr_Img_Btn_Play = new Sprite(
				0, 0, 
				Utils.getRatio(140),
				Utils.getRatio(35),
				Game.reg_Img_Btn_Play);
			
		spr_Img_Btn_Option.setPosition(
				Config.GAME_SCREEN_WIDTH - spr_Img_Btn_Option.getWidth() - Utils.getRatioW(border), 
				Config.GAME_SCREEN_HEIGHT - spr_Img_Btn_Option.getHeight() - Utils.getRatioH(border));
		spr_Img_Btn_Credit.setPosition(
				Utils.getRatioW(border), 
				Config.GAME_SCREEN_HEIGHT - spr_Img_Btn_Credit.getHeight() - Utils.getRatioH(border));
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
		Game.spr_Img_Select_Map_Bg.attachChild(Game.spr_Img_Select_Map_Bg_Select);
		Game.spr_Img_Select_Map_Bg.attachChild(Game.spr_Img_Select_Map_Btn_Back);
		
		for(int i = 0; i < Data.IMG_INGAME_BACKGROUND_MAP.length; i++)
			Game.spr_Img_Select_Map_Bg_Select.attachChild(Game.spr_Img_Select_Map_Icon_Map[i]);
	}
	
	private void attachSelectMc()
	{
		max_Player = 2;

		sData.setCharPlayer(PLAYER_1, CARACTER_1);
		sData.setCharPlayer(PLAYER_2, CARACTER_2);
		
		sData.setTypePlayer(PLAYER_1, TYPE_PLAYER);
		sData.setTypePlayer(PLAYER_2, TYPE_AI);
		
		scene.attachChild(Game.spr_Img_Select_Mc_Bg);
		Game.spr_Img_Select_Mc_Bg.setVisible(false);
		
		for(int i = 0; i < 4; i++)
		{
			Game.spr_Img_Select_Mc_Bg.attachChild(Game.spr_Img_Select_Mc_Icon_Mc_Bg[i]);
			
			for (int j = 0; j < Data.SPR_MC[sData.getSelectMap()].length; j++)
			{
				Game.spr_Img_Select_Mc_Icon_Mc_Bg[i].attachChild(Game.spr_Img_Select_Mc_Icon_Mc[i][j]);
				Game.spr_Img_Select_Mc_Icon_Mc[i][j].setVisible(false);
				
				if(j < 2)
				{
					Game.spr_Img_Select_Mc_Icon_Mc_Bg[i].attachChild(Game.spr_Img_Select_Mc_Btn_Arrow_Mc[i][j]);
					Game.spr_Img_Select_Mc_Icon_Mc_Bg[i].attachChild(Game.spr_Img_Select_Mc_Btn_Type[i][j]);
					Game.spr_Img_Select_Mc_Btn_Type[i][j].setVisible(false);
				}
					
			}
			
			if(i < 2)
			{
				Game.spr_Img_Select_Mc_Bg.attachChild(Game.spr_Img_Select_Mc_Btn_Arrow[i]);
				Game.spr_Img_Select_Mc_Icon_Mc_Bg[i].setVisible(true);
				Game.spr_Img_Select_Mc_Icon_Mc[i][sData.getCharPlayer(i)].setVisible(true);
				Game.spr_Img_Select_Mc_Btn_Type[i][sData.getTypePlayer(i)].setVisible(true);
			}
			else
			{
				Game.spr_Img_Select_Mc_Icon_Mc_Bg[i].attachChild(Game.spr_Img_Select_Mc_Btn_Delete[i - 2]);
				Game.spr_Img_Select_Mc_Icon_Mc_Bg[i].setVisible(false);
			}
		}
		Game.spr_Img_Select_Mc_Bg.attachChild(Game.spr_Img_Select_Mc_Btn_Add);
		Game.spr_Img_Select_Mc_Btn_Add.setVisible(true);
	}
	
	private void attachCredit(){
		int Margin = 20;
		spr_Img_Logo = new Sprite(
				0, 0, 
				Utils.getRatioW(100),
				Utils.getRatioH(50),
				Game.reg_Img_Logo);
		spr_Img_Title = new Sprite(
				0, 0,
				Utils.getRatioW(100),
				Utils.getRatioW(50),
				Game.reg_Img_Title_Menu);
			
		spr_Img_Logo.setPosition(
				Config.GAME_SCREEN_WIDTH - Game.reg_Img_Logo.getWidth() / 2,
				Margin
				);
		spr_Img_Title.setPosition(
				Config.GAME_SCREEN_WIDTH - Game.reg_Img_Title_Menu.getWidth() / 2,
				Margin
				);
//		scene.attachChild(Game.spr_Img_Back_Menu);
		scene.attachChild(spr_Img_Title);
		scene.attachChild(spr_Img_Logo);
		
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
}
