package com.amikomgamedev.ulartangga.states;

import org.anddev.andengine.engine.Engine;
import org.anddev.andengine.engine.camera.Camera;
import org.anddev.andengine.engine.camera.hud.HUD;
import org.anddev.andengine.engine.handler.IUpdateHandler;
import org.anddev.andengine.engine.options.EngineOptions;
import org.anddev.andengine.engine.options.EngineOptions.ScreenOrientation;
import org.anddev.andengine.engine.options.resolutionpolicy.FillResolutionPolicy;
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
import org.anddev.andengine.opengl.view.RenderSurfaceView;
import org.anddev.andengine.ui.activity.BaseGameActivity;
import org.anddev.andengine.util.Debug;
import org.anddev.andengine.util.HorizontalAlign;

import android.content.Intent;
import android.view.Gravity;
import android.view.KeyEvent;
import android.widget.FrameLayout;
import android.widget.FrameLayout.LayoutParams;
import android.widget.Toast;

import com.amikomgamedev.ulartangga.Chart_Boost;
import com.amikomgamedev.ulartangga.Config;
import com.amikomgamedev.ulartangga.Data;
import com.amikomgamedev.ulartangga.Define;
import com.amikomgamedev.ulartangga.Game;
import com.amikomgamedev.ulartangga.Loading;
import com.amikomgamedev.ulartangga.SoundManager;
import com.amikomgamedev.ulartangga.Utils;
import com.amikomgamedev.ulartangga.serverData;
import com.amikomgamedev.ulartangga.multiplayer.bluetooth.BT_Send_Received;
import com.google.ads.AdRequest;
import com.google.ads.AdSize;
import com.google.ads.AdView;

public class State_Menu_Main extends BaseGameActivity
							 implements	IUpdateHandler,
					 					IScrollDetectorListener,
							 			IOnSceneTouchListener,
							 			IClickDetectorListener,
							 			Define,
							 			Data
{
	private float CurrentSecond = 0;
	private float Second;
	
	public Camera camera;
	private MenuScene menuScene;
	private Text mText, mText2;

	protected Scene 				scene = new Scene();
	protected SurfaceScrollDetector	scrollDetector = new SurfaceScrollDetector(this);
	protected ClickDetector			clickDetector = new ClickDetector(this);
	protected SoundManager			soundManager = new SoundManager();
//	private State_Menu_Select_Map	select_Map;
	
	private final int STATE_MENU_START				= 0;
	private final int STATE_MENU_LOADING			= 1;
	private final int STATE_MENU_INMENU				= 2;
	private final int STATE_MENU_SELECT_MAP_LOADING	= 3;
	private final int STATE_MENU_SELECT_MAP			= 4;
	private final int STATE_MENU_SELECT_MC_LOADING	= 5;
	private final int STATE_MENU_SELECT_MC			= 6;
	private final int STATE_MENU_SELECT_PLAYER 		= 7;
	private final int STATE_MENU_CREDIT_LOADING 	= 8;
	private final int STATE_MENU_CREDIT 			= 9;
	private final int STATE_MENU_OPTION_LOADING 	= 10;
	private final int STATE_MENU_OPTION 			= 11;
	private final int STATE_MENU_CLOSE_NOTIF		= 12;
	public final int STATE_MENU_SELECT_MC_MULTI_SERVER	= 13;
	public final int STATE_MENU_WAITING_CLIENT_SERVER	= 14;
	public final int STATE_MENU_SELECT_TYPE				= 15;

	private final int DIALOG_CHOOSE_SINGLE_OR_MULTI 	= 0;
	private final int DIALOG_CHOOSE_SERVER_OR_CLIENT 	= 1;

	protected int State_Game_Current	= -1;
	
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
	private  Text txt_PM, txt_PRD, txt_GD, txt_PROG, txt_ART, txt_SE;
	private Text txt_AGUS,txt_AYU,txt_MUTIA,txt_DWI,txt_ARIEF,txt_AAN,txt_AMIRUL, txt_FANDI, txt_IRFAN, txt_CR; 
	
	private Text txt_Musik, txt_Sfx;
	
	private HUD hud;
	protected serverData sData = serverData.getInstance();
	
	private float y = 0;
	private boolean touch = false;
	
	float timerAsli = 0;
	float milisecond = 0;
	private boolean stopMoveCredits = true;
	protected boolean isRunning = true;
	protected boolean isServer 	= false;
	private boolean isWaiting = false;
	private boolean isActive = false;

	public Thread menuThread;
	
	private AdView adView;
	private AdRequest adRequest;
	private String ID = "a1504c944115cfb";
	
	private Chart_Boost	m_Chart_Boost;
	
	private LayoutParams adViewLayoutParams;
	
	public Engine onLoadEngine() 
	{
		Game.appInit();
		
		camera = new Camera(0, 0, Config.GAME_SCREEN_WIDTH, Config.GAME_SCREEN_HEIGHT);
		
		mEngine = new Engine(
			new EngineOptions(true, ScreenOrientation.PORTRAIT,
					new FillResolutionPolicy(),
			camera).setNeedsMusic(true).setNeedsSound(true));
		
		return mEngine;
	}

	public void onLoadResources() 
	{
//		m_Chart_Boost = new Chart_Boost(this);
//		m_Chart_Boost.createChartBoost();
		createAds();
		
		Game.setContext(this);
		Game.spr_Img_Select_Map_Bg = null;
		Game.spr_Img_Select_Mc_Bg = null;
		
		soundManager.loadSoundMenu();
	}

	public Scene onLoadScene() 
	{
		mEngine.registerUpdateHandler(new FPSLogger());
		mEngine.registerUpdateHandler(this);

		scene.setOnSceneTouchListener(this);
		scene.setOnSceneTouchListenerBindingEnabled(true);
 
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
					attachCredit();
					attachOption();
					attachNotif();
					scene.attachChild(Game.spr_Img_Select_Type_Bg);
					attachSelectMc();
					attachSelectMap();
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
			case STATE_MENU_CREDIT_LOADING:
				if(Loading.isLoading())
					Loading.updateLoading();
				else
				{
//					attachCredit();
					switchState(STATE_MENU_CREDIT);
				}
				break;
			case STATE_MENU_CREDIT:
				
				if(stopMoveCredits)
				{
				Game.spr_Img_Logo.setScale(0.5f);
				y-=2;
				Debug.d("Y = "+y);
				if(y + Game.spr_Img_Logo.getY() + Game.spr_Img_Logo.getHeight() < 0)
				y = Config.GAME_SCREEN_HEIGHT;
				hud.setPosition(hud.getScaleCenterX()/2, y);
				}			
				break;
								
			case STATE_MENU_OPTION_LOADING:
				if(Loading.isLoading())
					Loading.updateLoading();
				else
				{
//					attachOption();
					switchState(STATE_MENU_OPTION);
				}
				break;				
		
			case STATE_MENU_SELECT_MC_MULTI_SERVER:
				
				if(sData.getSelectMap()!=-1 && isWaiting)
				{
					isWaiting = false;
					sData.setTypePlayer(0, TYPE_MP);
					sData.setTypePlayer(1, TYPE_PLAYER);
					sendMessage("play,0");
					startActivity(new Intent(State_Menu_Main.this, BT_Send_Received.class));
					finish();
				}
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

//				adViewLayoutParams.gravity = Gravity.BOTTOM;
				adView.setVisibility(adView.INVISIBLE);
//				m_Chart_Boost.showChartBoost();
				
				soundManager.playMusic(soundManager.BGM_MENU_MAIN);
				Game.spr_Img_Logo.setVisible(false);
				Game.spr_Img_Close_Notif_Bg.setVisible(false);
				
				break;

			case STATE_MENU_SELECT_MAP_LOADING:

				soundManager.stopMusic(soundManager.BGM_MENU_MAIN);
				
				scene.attachChild(Game.spr_Img_Loading);
				Loading.setLoading(Loading.LOADING_TYPE_SELECT_MAP);
				
				break;
				
			case STATE_MENU_SELECT_MAP :


				adView.setVisibility(adView.VISIBLE);
				
				soundManager.playMusic(soundManager.BGM_MENU_MAIN);
				Game.spr_Img_Select_Map_Bg.setVisible(true);			
				
				break;

			case STATE_MENU_SELECT_MC_LOADING:

				soundManager.stopMusic(soundManager.BGM_MENU_MAIN);

				scene.attachChild(Game.spr_Img_Loading);
				Loading.setLoading(Loading.LOADING_TYPE_SELECT_MC);
				
				break;
				
			case STATE_MENU_SELECT_MC :


//			    adView.loadAd(adRequest);
//				adViewLayoutParams.gravity = Gravity.NO_GRAVITY;
//		        adViewLayoutParams.topMargin = (int) (getWindowManager().getDefaultDisplay().getHeight() -  Utils.getRatioH(125));

				
		        adView.setVisibility(AdView.VISIBLE);
		        
				soundManager.playMusic(soundManager.BGM_MENU_MAIN);
				Game.spr_Img_Select_Mc_Bg.setVisible(true);
				visibleSelectMc();
				
				break;
				
			case STATE_MENU_CREDIT_LOADING:
				
//				Loading.setLoading(Loading.LOADING_TYPE_CREDIT);
				break;
				
			case STATE_MENU_CREDIT:
				
				Game.spr_Img_Back_Credit.setVisible(true);
				hud.setVisible(true);
				
//				Game.spr_Img_Back_Menu.setVisible(false);
//				Game.spr_Img_Close_Notif_Bg.setVisible(false);
				
				y = Config.GAME_SCREEN_HEIGHT;
				hud.setPosition(hud.getScaleCenterX() / 2, y);
				break;
				
			case STATE_MENU_OPTION_LOADING:
//				Loading.setLoading(Loading.LOADING_TYPE_OPTION);
				break;
			case STATE_MENU_OPTION:
				
				Game.spr_Img_Option_Bg.setVisible(true);
				
				if(soundManager.sfxEnable)
					Game.txt_Menu_Setting[0][0].setVisible(true);
				else
					Game.txt_Menu_Setting[0][1].setVisible(true);
				
				if(soundManager.bgmEnable)
					Game.txt_Menu_Setting[1][0].setVisible(true);
				else
					Game.txt_Menu_Setting[1][1].setVisible(true);
				
				if(State_Gameplay.isShakeEnable)
					Game.txt_Menu_Setting[2][0].setVisible(true);
				else
					Game.txt_Menu_Setting[2][1].setVisible(true);
//				Game.spr_Img_Back_Menu.setAlpha(200);
	
				break;
			case STATE_MENU_CLOSE_NOTIF:
				Game.spr_Img_Close_Notif_Bg.setVisible(true);
				break;
				
			case STATE_MENU_SELECT_MC_MULTI_SERVER:
				
				soundManager.playMusic(soundManager.BGM_MENU_MAIN);

				sData.setCharPlayer(PLAYER_1, CARACTER_1);
				sData.setCharPlayer(PLAYER_2, CARACTER_1);
				
				sData.setSelectMap(-1);
				
				Game.spr_Img_Select_Mc_Bg.setVisible(true);

				Game.spr_Img_Select_Mc_Btn_Add.setVisible(false);
				
				for(int i = 0; i < 4; i++)
				{
					Game.spr_Img_Select_Mc_Icon_Mc_Bg[i].setVisible(false);
					
					for (int j = 0; j < Data.SPR_MC.length; j++)
					{
						Game.spr_Img_Select_Mc_Icon_Mc[i][j].setVisible(false);
						if(j < 2)
						{
							Game.spr_Img_Select_Mc_Btn_Type[i][j].setVisible(false);
							if(isServer)
								Game.spr_Img_Select_Mc_Btn_Arrow_Mc[1][j].setVisible(false);
							else
								Game.spr_Img_Select_Mc_Btn_Arrow_Mc[0][j].setVisible(false);
						}
					}
					
					if(i < 2)
					{
						Game.spr_Img_Select_Mc_Icon_Mc_Bg[i].setVisible(true);
						Game.spr_Img_Select_Mc_Icon_Mc[i][sData.getCharPlayer(i)].setVisible(true);
					}
				}
				
				break;
				
				case STATE_MENU_WAITING_CLIENT_SERVER:
					
					toast("Waiting Server...");
					
					break;
					
				case STATE_MENU_SELECT_TYPE:
					
					adView.setVisibility(adView.VISIBLE);

					Game.spr_Img_Select_Mc_Bg.setVisible(false);
					Game.spr_Img_Select_Type_Bg.setVisible(true);
					
					break;
		}
	}
	
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
//					finish();
//					Game.spr_Img_Bg_Notif.setVisible(true);
//					Game.spr_Img_Back_Menu.setVisible(false);
					switchState(STATE_MENU_CLOSE_NOTIF);
				}
				break;
				
			case STATE_MENU_SELECT_MAP:
				
				if(keyCode == KeyEvent.KEYCODE_BACK)
				{
					Game.spr_Img_Select_Map_Bg.setVisible(false);
					Game.spr_Img_Back_Menu.setVisible(true);

					if(isServer)
						switchState(STATE_MENU_SELECT_MC_MULTI_SERVER);	
					else
						switchState(STATE_MENU_SELECT_MC);
				}
				break;
				
			case STATE_MENU_SELECT_MC:
				
				if(keyCode == KeyEvent.KEYCODE_BACK)
				{

			        adView.setVisibility(AdView.INVISIBLE);
					
					Game.spr_Img_Select_Mc_Bg.setVisible(false);
					switchState(STATE_MENU_SELECT_TYPE);
				}
				
				break;
			case STATE_MENU_CREDIT:
				if(keyCode == KeyEvent.KEYCODE_BACK)
				{	
					Game.spr_Img_Back_Credit.setVisible(false);
					Game.spr_Img_Back_Menu.setVisible(true);
					switchState(STATE_MENU_INMENU);
					hud.setVisible(false);
//					Game.spr_Img_Title_Menu.setPosition((Config.GAME_SCREEN_WIDTH - Game.spr_Img_Title_Menu.getWidth()) /2, 50);
//					spr_Img_Btn_Play.setVisible(true);
//					spr_Img_Btn_Option.setVisible(true);
//					spr_Img_Btn_Credit.setVisible(true);
				}
				break;
			case STATE_MENU_OPTION:
				if(keyCode == KeyEvent.KEYCODE_BACK)
				{
					Game.spr_Img_Option_Bg.setVisible(false);
					Game.spr_Img_Back_Menu.setVisible(true);
					switchState(STATE_MENU_INMENU);
				}
				break;
			case STATE_MENU_CLOSE_NOTIF:
		
				if(keyCode == KeyEvent.KEYCODE_BACK)
				{
					switchState(STATE_MENU_INMENU);
				}
				break;
				
			case STATE_MENU_SELECT_MC_MULTI_SERVER:
				
				if(keyCode == KeyEvent.KEYCODE_BACK)
				{
					toast("Disconected");
					sendMessage("disconnect,menu");
					switchState(STATE_MENU_SELECT_TYPE);
				}
				
				break;
				
			case STATE_MENU_SELECT_TYPE:
				
				if(keyCode == KeyEvent.KEYCODE_BACK)
				{
					Game.spr_Img_Select_Type_Bg.setVisible(false);
					switchState(STATE_MENU_INMENU);
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
					
//					new AlertDialog.Builder(this)
//					.setTitle("Select Mode")
//					.setPositiveButton("Offline", new OnClickListener() {
//						public void onClick(final DialogInterface pDialog, final int pWhich)
//						{
//							sData.setSelectMap(MAP_MODERN);
//							
//							if(Game.spr_Img_Select_Mc_Bg == null)
//								switchState(STATE_MENU_SELECT_MC_LOADING);
//							else
//								switchState(STATE_MENU_SELECT_MC);
//						}
//					})
//					.setNegativeButton("Bluetooth", new OnClickListener() {
//						public void onClick(final DialogInterface pDialog, final int pWhich) {
//							bluetoothMode();
//						}
//					})
//					.show();
					
					switchState(STATE_MENU_SELECT_TYPE);
				}
				else if(Utils.isOnArea(pSceneTouchEvent, spr_Img_Btn_Credit)
					&& pSceneTouchEvent.isActionUp()){
					switchState(STATE_MENU_CREDIT_LOADING);
				}
				else if(Utils.isOnArea(pSceneTouchEvent, spr_Img_Btn_Option)
						&& pSceneTouchEvent.isActionUp()){
					switchState(STATE_MENU_OPTION_LOADING);
				}
					
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
					if(isServer)
						switchState(STATE_MENU_SELECT_MC_MULTI_SERVER);	
					else
						switchState(STATE_MENU_SELECT_MC);
				}
				
				break;
				
			case STATE_MENU_SELECT_MC:
				
				if(Utils.isOnArea(
						pSceneTouchEvent,
						Game.spr_Img_Select_Mc_Btn_Arrow[0])
						&& pSceneTouchEvent.isActionUp())
				{

			        adView.setVisibility(AdView.INVISIBLE);
					
					Game.spr_Img_Select_Mc_Bg.setVisible(false);
					switchState(STATE_MENU_SELECT_TYPE);
				}
				break;
			
			case STATE_MENU_SELECT_MC_MULTI_SERVER:
				
				if(Utils.isOnArea(
						pSceneTouchEvent,
						Game.spr_Img_Select_Mc_Btn_Arrow[0])
						&& pSceneTouchEvent.isActionUp())
				{
					toast("Disconected");
					sendMessage("disconnect,menu");
					switchState(STATE_MENU_SELECT_TYPE);
				}
				
				break;
				
			case STATE_MENU_CREDIT:
				
				if(pSceneTouchEvent.isActionUp())
				{
					stopMoveCredits = true;
				}
				else
				{
					stopMoveCredits = false;
					
				}
				
				break;
		}
		return true;
	}

	public void onScroll(ScrollDetector pScollDetector, final TouchEvent pTouchEvent,
			float pDistanceX, final float pDistanceY)
	{
		switch (State_Game_Current) 
		{
			case STATE_MENU_SELECT_MAP:
		
				Game.spr_Img_Select_Map_Bg_Select.setPosition(
						Game.spr_Img_Select_Map_Bg_Select.getX(),
						Game.spr_Img_Select_Map_Bg_Select.getY() + pDistanceY);
				
				break;
			case STATE_MENU_CREDIT:
				this.runOnUpdateThread(new Runnable() {
					
					public void run() {

							mEngine.clearUpdateHandlers();
							y =  hud.getY() + pDistanceY;
							hud.setPosition(hud.getX(), y);

							mEngine.registerUpdateHandler(State_Menu_Main.this);	
						}
			
	
				});
				
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
				
				if(Utils.isOnArea(pTouchEvent, Game.spr_Img_Bg_Notif, Game.spr_btn_yes)){
					finish();
				}
				else if (Utils.isOnArea(pTouchEvent,  Game.spr_Img_Bg_Notif, Game.spr_btn_no)){
					Game.spr_Img_Close_Notif_Bg.setVisible(false);
					Game.spr_Img_Back_Menu.setVisible(true);
				}
					
				
				break;
				
			case STATE_MENU_SELECT_MAP:
				
				for(int i = 0; i < Data.IMG_INGAME_BACKGROUND_MAP.length; i++)
				{
					// dibatasi 2 map dulu
//					if(i < 2)
					{
						if(Utils.isOnArea(
								pTouchEvent,
								Game.spr_Img_Select_Map_Bg_Select,
								Game.spr_Img_Select_Map_Icon_Map[i])
								&& Game.spr_Img_Select_Map_Icon_Map[i].getScaleX() == 1)
						{
							
							sData.setSelectMap(i);
							
							if(isServer)
							{
								sendMessage("map,"+i);
								toast("Waiting Client...");
								sData.setTypePlayer(1, TYPE_MP);
								sData.setTypePlayer(0, TYPE_PLAYER);
							}
							else
							{
								startActivity(new Intent(State_Menu_Main.this, State_Gameplay.class));
								finish();
								
							}
						}
					}
				}
				
				break;
				
			case STATE_MENU_SELECT_MC:
				
				

				if(Utils.isOnArea(pTouchEvent, Game.spr_Img_Select_Mc_Btn_Arrow[1]))
				{

			        adView.setVisibility(AdView.INVISIBLE);
					
					sData.setMaxPlayer(max_Player);
//					
//					if(Game.spr_Img_Select_Map_Bg == null)
//						switchState(STATE_MENU_SELECT_MAP_LOADING);
//					else
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
						if(Config.GAME_RES_USE != Config.GAME_RES_HD)
							adView.setVisibility(AdView.INVISIBLE);
						
						Game.spr_Img_Select_Mc_Icon_Mc_Bg[2].setPosition(
								(Config.GAME_SCREEN_WIDTH - Game.spr_Img_Select_Mc_Icon_Mc_Bg[2].getWidth()) / 2,
								Game.spr_Img_Select_Mc_Icon_Mc_Bg[0].getY() + Game.spr_Img_Select_Mc_Icon_Mc_Bg[0].getHeight() + Utils.getRatioH(50));
						
						Game.spr_Img_Select_Mc_Btn_Add.setPosition(
								Game.spr_Img_Select_Mc_Btn_Add.getX(),
								Game.spr_Img_Select_Mc_Icon_Mc_Bg[2].getY() + Game.spr_Img_Select_Mc_Icon_Mc_Bg[2].getHeight() + Utils.getRatioH(40));
						
						Game.spr_Img_Select_Mc_Icon_Mc_Bg[2].setVisible(true);
					}
					else if(max_Player == 4)
					{
						adView.setVisibility(AdView.VISIBLE);
						
						Game.spr_Img_Select_Mc_Icon_Mc_Bg[2].setVisible(false);
						
						Game.spr_Img_Select_Mc_Icon_Mc_Bg[2].setPosition(
								(Config.GAME_SCREEN_WIDTH / 2 - Game.spr_Img_Select_Mc_Icon_Mc_Bg[2].getWidth()) / 2,
								Game.spr_Img_Select_Mc_Icon_Mc_Bg[0].getY() + Game.spr_Img_Select_Mc_Icon_Mc_Bg[0].getHeight() + Utils.getRatioH(50));
						Game.spr_Img_Select_Mc_Icon_Mc_Bg[3].setPosition(
								Config.GAME_SCREEN_WIDTH / 2 + (Config.GAME_SCREEN_WIDTH / 2 - Game.spr_Img_Select_Mc_Icon_Mc_Bg[3].getWidth()) / 2,
								Game.spr_Img_Select_Mc_Icon_Mc_Bg[0].getY() + Game.spr_Img_Select_Mc_Icon_Mc_Bg[0].getHeight() + Utils.getRatioH(50));

						Game.spr_Img_Select_Mc_Icon_Mc_Bg[2].setVisible(true);
						Game.spr_Img_Select_Mc_Icon_Mc_Bg[3].setVisible(true);
						
						Game.spr_Img_Select_Mc_Btn_Add.setVisible(false);
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
								if(Config.GAME_RES_USE != Config.GAME_RES_HD)
									adView.setVisibility(AdView.INVISIBLE);
								
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
								adView.setVisibility(AdView.VISIBLE);
								
								Game.spr_Img_Select_Mc_Icon_Mc[2][sData.getCharPlayer(2)].setVisible(false);
								Game.spr_Img_Select_Mc_Btn_Type[2][sData.getTypePlayer(2)].setVisible(false);
								Game.spr_Img_Select_Mc_Icon_Mc_Bg[2].setVisible(false);
								
								Game.spr_Img_Select_Mc_Btn_Add.setPosition(
										Game.spr_Img_Select_Mc_Btn_Add.getX(),
										Game.spr_Img_Select_Mc_Icon_Mc_Bg[0].getY() + Game.spr_Img_Select_Mc_Icon_Mc_Bg[0].getHeight() + Utils.getRatioH(40));
							}
							
							Game.spr_Img_Select_Mc_Btn_Add.setVisible(true);
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
									sData.setCharPlayer(i, SELECT_MC_ICON.length - 1);
								else
									sData.setCharPlayer(i, sData.getCharPlayer(i) - 1);
							else
								if(sData.getCharPlayer(i) == SELECT_MC_ICON.length - 1)
									sData.setCharPlayer(i, CARACTER_1);
								else
									sData.setCharPlayer(i, sData.getCharPlayer(i) + 1);
							
							Game.spr_Img_Select_Mc_Icon_Mc[i][sData.getCharPlayer(i)].setVisible(true);
						}
					}
					
					if(Utils.isOnArea(pTouchEvent, Game.spr_Img_Select_Mc_Icon_Mc_Bg[i]))
					{
						Game.spr_Img_Select_Mc_Icon_Mc[i][sData.getCharPlayer(i)].setVisible(false);
						
						if(sData.getCharPlayer(i) == SELECT_MC_ICON.length - 1)
							sData.setCharPlayer(i, CARACTER_1);
						else
							sData.setCharPlayer(i, sData.getCharPlayer(i) + 1);
					
						Game.spr_Img_Select_Mc_Icon_Mc[i][sData.getCharPlayer(i)].setVisible(true);
					}
				}
				
				break;
			case STATE_MENU_OPTION:

				for (int i = 0; i < 3; i++)
				{
					if(Utils.isOnArea(
							pTouchEvent,
							Game.spr_Menu_Setting_Bg_Text,
							Game.spr_Menu_Setting_Bg_Sound_Icon[i]))
					{
						if(i == 0)
						{
							if(soundManager.sfxEnable)
							{
								soundManager.sfxEnable = false;
								Game.txt_Menu_Setting[i][0].setVisible(false);
								Game.txt_Menu_Setting[i][1].setVisible(true);
							}
							else
							{
								soundManager.sfxEnable = true;
								Game.txt_Menu_Setting[i][0].setVisible(true);
								Game.txt_Menu_Setting[i][1].setVisible(false);
							}
						}
						else if(i == 1)
						{
							if(soundManager.bgmEnable)
							{
								soundManager.bgmEnable = false;
								Game.txt_Menu_Setting[i][0].setVisible(false);
								Game.txt_Menu_Setting[i][1].setVisible(true);
								soundManager.stopMusic(soundManager.BGM_MENU_MAIN);
							}
							else
							{
								soundManager.bgmEnable = true;
								Game.txt_Menu_Setting[i][0].setVisible(true);
								Game.txt_Menu_Setting[i][1].setVisible(false);
								soundManager.playMusic(soundManager.BGM_MENU_MAIN);
							}
						}
						else
						{
							if(State_Gameplay.isShakeEnable)
							{
								State_Gameplay.isShakeEnable = false;
								Game.txt_Menu_Setting[i][0].setVisible(false);
								Game.txt_Menu_Setting[i][1].setVisible(true);
							}
							else
							{
								State_Gameplay.isShakeEnable = true;
								Game.txt_Menu_Setting[i][0].setVisible(true);
								Game.txt_Menu_Setting[i][1].setVisible(false);
							}
						}
					}
					
				}
				
				break;
			case STATE_MENU_CLOSE_NOTIF:
				if(Utils.isOnArea(pTouchEvent, Game.spr_Img_Bg_Notif, Game.spr_btn_yes)){
					finish();
				}
				else if (Utils.isOnArea(pTouchEvent,  Game.spr_Img_Bg_Notif, Game.spr_btn_no)){
					Game.spr_Img_Close_Notif_Bg.setVisible(false);
					switchState(STATE_MENU_INMENU);
				}
				break;
				
			case STATE_MENU_SELECT_MC_MULTI_SERVER:

				if(Utils.isOnArea(pTouchEvent, Game.spr_Img_Select_Mc_Btn_Arrow[1]))
				{
					sData.setMaxPlayer(max_Player);
					
					if(isServer)
					{
						sendMessage("notif,Server Choose Map");
						
						if(Game.spr_Img_Select_Map_Bg == null)
							switchState(STATE_MENU_SELECT_MAP_LOADING);
						else
							switchState(STATE_MENU_SELECT_MAP);
					}
					else
					{
						toast("Waiting Server...");
						isWaiting = true;
//						switchState(STATE_MENU_WAITING_CLIENT_SERVER);
					}
				}
				
				for (int j = 0; j < 2; j++)
				{
					int i;
					if(isServer)
						i = 0;
					else
						i = 1;
						
					if(Utils.isOnArea(
							pTouchEvent,
							Game.spr_Img_Select_Mc_Icon_Mc_Bg[i],
							Game.spr_Img_Select_Mc_Btn_Arrow_Mc[i][j]))
					{
						Game.spr_Img_Select_Mc_Icon_Mc[i][sData.getCharPlayer(i)].setVisible(false);
						
						if(j == 0)
						{
							if(sData.getCharPlayer(i) == CARACTER_1)
								sData.setCharPlayer(i, CARACTER_4);
							else
								sData.setCharPlayer(i, sData.getCharPlayer(i) - 1);
						}
						else
						{
							if(sData.getCharPlayer(i) == CARACTER_4)
								sData.setCharPlayer(i, CARACTER_1);
							else
								sData.setCharPlayer(i, sData.getCharPlayer(i) + 1);
						}
						sendMessage("change,"+sData.getCharPlayer(i));
						Game.spr_Img_Select_Mc_Icon_Mc[i][sData.getCharPlayer(i)].setVisible(true);
					}
				}
				break;
				
			case STATE_MENU_SELECT_TYPE:

				for (int i = 0; i < 2; i++)
				{
					if(Utils.isOnArea(pTouchEvent, Game.spr_Img_Select_Type_Btn[i]))
					{
						if(i == 0)
						{
							switchState(STATE_MENU_SELECT_MC);
						}
						else
						{
							bluetoothMode();
						}
					}
				}
				
				break;
		}
	}
	
	protected void createAds()
	{	
		final FrameLayout frameLayout = new FrameLayout(this);
		
        final LayoutParams frameLayoutLayoutParams =
                new LayoutParams(
                		getWindowManager().getDefaultDisplay().getWidth(),
                		getWindowManager().getDefaultDisplay().getHeight());
        
        adViewLayoutParams =
                new LayoutParams(LayoutParams.WRAP_CONTENT,
                               LayoutParams.WRAP_CONTENT,
                               Gravity.CENTER_HORIZONTAL|Gravity.NO_GRAVITY);
        
        adView 		= new AdView(this, AdSize.BANNER, ID);
        adRequest 	= new AdRequest();
        
        
        adView.refreshDrawableState();
        adView.setVisibility(AdView.INVISIBLE);
        adView.loadAd(adRequest);
        
        this.mRenderSurfaceView = new RenderSurfaceView(this);
        mRenderSurfaceView.setRenderer(mEngine);
 
        frameLayout.addView(mRenderSurfaceView, frameLayoutLayoutParams);
        frameLayout.addView(adView, adViewLayoutParams);
        
        adViewLayoutParams.topMargin = (int) (getWindowManager().getDefaultDisplay().getHeight() -  Utils.getRatioH(125));

        setContentView(frameLayout);
	}
	
	
	
	protected void onPause() {
		if(soundManager.bgm[soundManager.BGM_MENU_MAIN] != null)
		{
			soundManager.stopMusic(soundManager.BGM_MENU_MAIN);
		}
		super.onPause();
	}
	
	
	protected void onResume() {
		if(soundManager.bgm[soundManager.BGM_MENU_MAIN] != null)
		{
			soundManager.playMusic(soundManager.BGM_MENU_MAIN);
		}
		super.onResume();
	}
	
	
	protected void onDestroy() {
		super.onDestroy();
		
		if (adView != null) {
	      adView.destroy();
	    }
	}
	
	protected Scene getScene()
	{
		return scene;
	}
	
	private void attachInMenu()
	{
		isActive = true;
		
		
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
				Utils.getRatio(70),
				Utils.getRatio(70),
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
		Game.spr_Img_Back_Menu.attachChild(Game.spr_Img_Title_Menu[0]);
		Game.spr_Img_Back_Menu.attachChild(spr_Img_Btn_Credit);
		Game.spr_Img_Back_Menu.attachChild(spr_Img_Btn_Option);
		Game.spr_Img_Back_Menu.attachChild(spr_Img_Btn_Play);
	}
	
	private void attachSelectMap()
	{
		scene.attachChild(Game.spr_Img_Select_Map_Bg);
		Game.spr_Img_Select_Map_Bg.attachChild(Game.spr_Img_Select_Map_Bg_Select);
		Game.spr_Img_Select_Map_Bg.attachChild(Game.spr_Img_Select_Map_Btn_Back);
		Game.spr_Img_Select_Map_Bg.attachChild(Game.spr_Img_Select_Map);
		
		for(int i = 0; i < Data.IMG_INGAME_BACKGROUND_MAP.length; i++)
			Game.spr_Img_Select_Map_Bg_Select.attachChild(Game.spr_Img_Select_Map_Icon_Map[i]);
		
		Game.spr_Img_Select_Map_Bg.setVisible(false);
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
			
			for (int j = 0; j < Data.SPR_MC.length; j++)
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
	
private void attachNotif(){
	scene.attachChild(Game.spr_Img_Close_Notif_Bg);
	Game.spr_Img_Close_Notif_Bg.attachChild(Game.spr_Img_Bg_Notif);
	Game.spr_Img_Bg_Notif.attachChild(Game.spr_btn_yes);
	Game.spr_Img_Bg_Notif.attachChild(Game.spr_btn_no);
	Game.spr_Img_Bg_Notif.attachChild(Game.spr_Img_Bg_Text);
	
	Game.spr_Img_Bg_Notif.setPosition(
			(Config.GAME_SCREEN_WIDTH - Game.spr_Img_Bg_Notif.getWidth()) /2,
			(Config.GAME_SCREEN_HEIGHT - Game.spr_Img_Bg_Notif.getHeight() / 2 ) /2);
	
	Game.spr_btn_yes.setPosition(
			(Game.spr_Img_Bg_Notif.getWidth() / 2 - Game.spr_btn_yes.getWidth()) / 2,
			(Game.spr_Img_Bg_Notif.getHeight() - Game.spr_btn_yes.getHeight()) / 2
			);
	
	Game.spr_btn_no.setPosition(
			Game.spr_Img_Bg_Notif.getWidth() / 2 + (Game.spr_Img_Bg_Notif.getWidth() / 2 - Game.spr_btn_no.getWidth()) / 2,
			(Game.spr_Img_Bg_Notif.getHeight() - Game.spr_btn_no.getHeight()) / 2
			);
	
	Game.spr_Img_Bg_Text.setPosition(
			(Game.spr_Img_Bg_Notif.getWidth() - Game.spr_Img_Bg_Text.getWidth()) / 2,
			- Utils.getRatio(30) - Game.spr_Img_Bg_Text.getHeight());
	
	}
	
	private void attachCredit(){
		
		float MARGIN = Utils.getRatio(20);
		
		scene.attachChild(Game.spr_Img_Back_Credit);
		hud = new HUD();
		camera.setHUD(hud);
		
		hud.attachChild(Game.spr_Img_Title_Menu[1]);
		Game.spr_Img_Title_Menu[1].setPosition(Game.spr_Img_Title_Menu[1].getX(), 0);
			
		txt_PM = new  Text((Config.GAME_SCREEN_WIDTH - Game.font[3].getStringWidth(_TEX_PM)) / 2,
				Game.spr_Img_Title_Menu[1].getY()+Game.spr_Img_Title_Menu[1].getHeight() + MARGIN , Game.font[3], _TEX_PM);
		
		txt_AGUS = new  Text((Config.GAME_SCREEN_WIDTH - Game.font[4].getStringWidth(_TEX_AGS)) / 2,
				txt_PM.getY() + MARGIN, Game.font[4], _TEX_AGS);
		
		txt_PRD = new  Text((Config.GAME_SCREEN_WIDTH - Game.font[3].getStringWidth(_TEX_PRD)) / 2,
				txt_AGUS.getY()+ txt_AGUS.getHeight() + MARGIN , Game.font[3], _TEX_PRD);
		
		txt_AYU = new  Text((Config.GAME_SCREEN_WIDTH - Game.font[4].getStringWidth(_TEX_AYU)) / 2,
				txt_PRD.getY() + MARGIN, Game.font[4], _TEX_AYU);
		
		txt_GD = new  Text((Config.GAME_SCREEN_WIDTH - Game.font[3].getStringWidth(_TEX_GD)) / 2,
				txt_AYU.getY()+ txt_AYU.getHeight() + MARGIN , Game.font[3], _TEX_GD);
		
		txt_MUTIA = new  Text((Config.GAME_SCREEN_WIDTH - Game.font[4].getStringWidth(_TEX_MTY)) / 2,
				txt_GD.getY() + MARGIN, Game.font[4], _TEX_MTY);
		
		txt_PROG = new  Text((Config.GAME_SCREEN_WIDTH - Game.font[3].getStringWidth(_TEX_PROG)) / 2,
				txt_MUTIA.getY()+ txt_MUTIA.getHeight()+ MARGIN , Game.font[3], _TEX_PROG);
		
		txt_DWI = new  Text((Config.GAME_SCREEN_WIDTH - Game.font[4].getStringWidth(_TEX_DWI)) / 2,
				txt_PROG.getY() + MARGIN, Game.font[4], _TEX_DWI);
		
		txt_ARIEF = new  Text((Config.GAME_SCREEN_WIDTH - Game.font[4].getStringWidth(_TEX_ARF)) / 2,
				txt_DWI.getY() + MARGIN + 5 , Game.font[4], _TEX_ARF);
		
		txt_ART = new  Text((Config.GAME_SCREEN_WIDTH - Game.font[3].getStringWidth(_TEX_ART)) / 2,
				txt_ARIEF.getY()+ txt_ARIEF.getHeight()+ MARGIN , Game.font[3], _TEX_ART);
		
		txt_AMIRUL = new  Text((Config.GAME_SCREEN_WIDTH - Game.font[4].getStringWidth(_TEX_AMR)) / 2,
				txt_ART.getY() + MARGIN, Game.font[4], _TEX_AMR);
		
		txt_AAN = new  Text((Config.GAME_SCREEN_WIDTH - Game.font[4].getStringWidth(_TEX_VER)) / 2,
				txt_AMIRUL.getY() + MARGIN + 5, Game.font[4], _TEX_VER);
		
		txt_SE = new  Text((Config.GAME_SCREEN_WIDTH - Game.font[3].getStringWidth(_TEX_SE)) / 2,
				txt_AAN.getY()+ txt_AAN.getHeight()+ MARGIN , Game.font[3], _TEX_SE);
		
		txt_FANDI = new  Text((Config.GAME_SCREEN_WIDTH - Game.font[4].getStringWidth(_TEX_FAN)) / 2,
				txt_SE.getY() + MARGIN, Game.font[4], _TEX_FAN);
		
		txt_IRFAN = new  Text((Config.GAME_SCREEN_WIDTH - Game.font[4].getStringWidth(_TEX_IRF)) / 2,
				txt_FANDI.getY()+MARGIN + 5 , Game.font[4], _TEX_IRF);
		
		Text txt_Versi = new Text((Config.GAME_SCREEN_WIDTH - Game.font[3].getStringWidth(Config.VERSI)) / 2,
				txt_IRFAN.getY()+txt_IRFAN.getHeight() + MARGIN,
				Game.font[3], Config.VERSI);

	txt_CR = new Text((Config.GAME_SCREEN_WIDTH - Game.font[3].getStringWidth("copyright (c)")) / 2,
			txt_Versi.getY() + txt_Versi.getHeight() + MARGIN  , Game.font[3], "copyright (c)\n 2012", HorizontalAlign.CENTER);
		
		
		txt_AGUS.setColor(1,0, 0);
		txt_AYU.setColor(1, 0, 0);
		txt_MUTIA.setColor(1, 0, 0);
		txt_ARIEF.setColor(1, 0, 0);
		txt_DWI.setColor(1, 0, 0);
		txt_AAN.setColor(1, 0, 0);
		txt_AMIRUL.setColor(1, 0, 0);
		txt_FANDI.setColor(1, 0, 0);
		txt_IRFAN.setColor(1, 0, 0);
		
		txt_Versi.setColor(0, 0, 0);
		txt_PM.setColor(0, 0, 0);
		txt_PRD.setColor(0, 0, 0);
		txt_PROG.setColor(0, 0, 0);
		txt_GD.setColor(0, 0, 0);
		txt_ART.setColor(0, 0, 0);
		txt_SE.setColor(0, 0, 0);
		
		txt_CR.setColor(0, 0, 0);
		
		hud.attachChild(txt_Versi);
		hud.attachChild(txt_PM);
		hud.attachChild(txt_PRD);
		hud.attachChild(txt_PROG);
		hud.attachChild(txt_GD);
		hud.attachChild(txt_ART);
		hud.attachChild(txt_SE);
		
		hud.attachChild(txt_AGUS);
		hud.attachChild(txt_AYU);
		hud.attachChild(txt_MUTIA);
		hud.attachChild(txt_DWI);
		hud.attachChild(txt_ARIEF);
		hud.attachChild(txt_AMIRUL);
		hud.attachChild(txt_AAN);
		hud.attachChild(txt_FANDI);
		hud.attachChild(txt_IRFAN);
		hud.attachChild(txt_CR);
	
	
		hud.attachChild(Game.spr_Img_Logo);
		Game.spr_Img_Logo.setPosition(Game.spr_Img_Logo.getX(), 
				txt_CR.getY() + MARGIN);
		
		Game.spr_Img_Back_Credit.setVisible(false);
		hud.setVisible(false);
	
	}
	
	private void attachOption(){
		
		scene.attachChild(Game.spr_Img_Option_Bg);
		Game.spr_Img_Option_Bg.setVisible(false);

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
	
	public void bluetoothMode() {

	}
	
	private void visibleSelectMc()
	{
		sData.setCharPlayer(PLAYER_1, CARACTER_1);
		sData.setCharPlayer(PLAYER_2, CARACTER_2);
		
		sData.setTypePlayer(PLAYER_1, TYPE_PLAYER);
		sData.setTypePlayer(PLAYER_2, TYPE_AI);
		
		Game.spr_Img_Select_Mc_Bg.setVisible(true);
		Game.spr_Img_Select_Mc_Btn_Add.setVisible(true);
		
		for(int i = 0; i < 4; i++)
		{
			Game.spr_Img_Select_Mc_Icon_Mc_Bg[i].setVisible(false);
			
			for (int j = 0; j < Data.SPR_MC.length; j++)
			{
				Game.spr_Img_Select_Mc_Icon_Mc[i][j].setVisible(false);
				if(j < 2)
				{
					Game.spr_Img_Select_Mc_Btn_Type[i][j].setVisible(false);
					Game.spr_Img_Select_Mc_Btn_Arrow_Mc[1][j].setVisible(true);
				}
			}
		}
		
		for(int i = 0; i < max_Player; i++)
		{
			Game.spr_Img_Select_Mc_Icon_Mc_Bg[i].setVisible(true);
			Game.spr_Img_Select_Mc_Icon_Mc[i][sData.getCharPlayer(i)].setVisible(true);
			Game.spr_Img_Select_Mc_Btn_Type[i][sData.getTypePlayer(i)].setVisible(true);
		}
	}

	public void sendMessage(String str) {
		// TODO Auto-generated method stub
		
	}

	public String receiveMessage() {
		// TODO Auto-generated method stub
		return null;
	}
	
	protected void startReceiverThread()
	{
		isRunning = true;
		menuThread = new Thread()
		{
			public void run()
			{
				while (true)
				{
					if(isRunning)
					{
						String[] msgArray = new String[1];
						msgArray = receiveMessage().split(",", 2);
	
						handleReceivedMessage(msgArray);
						
					}
					else break;
				}
			}
		};
		menuThread.start();
	}
	
	private void handleReceivedMessage(String[] msgArray) {
		if(msgArray[0].contains("change"))
		{
			int i;
			
			if(isServer)
			{
				i = 1;
				sData.setCharPlayer(1, (int) Float.parseFloat(msgArray[1]));
			}
			else
			{
				i = 0;
				sData.setCharPlayer(0, (int) Float.parseFloat(msgArray[1]));
			}
			
			for (int j = 0; j < 4; j++) {
				Game.spr_Img_Select_Mc_Icon_Mc[i][j].setVisible(false);	
			}
			Game.spr_Img_Select_Mc_Icon_Mc[i][(int) Float.parseFloat(msgArray[1])].setVisible(true);
		}
		if(msgArray[0].contains("map"))
		{
			String map = msgArray[1];
			sData.setSelectMap((int) Float.parseFloat(map));
		}
		if(msgArray[0].contains("play"))
		{
//			menuThread.stop();
			Utils.TRACE("INTEN GAMEPLAY MP");
			startActivity(new Intent(State_Menu_Main.this, BT_Send_Received.class));
			finish();
		}
		
		if(msgArray[0].contains("restart"))
		{
			State_Gameplay.autoSwitch = true;
			State_Gameplay.autoSwitchState = 5;
		}
		if(msgArray[0].contains("move"))
		{
			
			State_Gameplay.randomValue = (int) Float.parseFloat(msgArray[1]);
			State_Gameplay.moveMP = true;
		}
		if(msgArray[0].contains("disconnect"))
		{
			if(msgArray[1].contains("menu"))
			{
				switchState(STATE_MENU_SELECT_TYPE);
			}
			else
			{
				State_Gameplay.State_Game_Current = 7;
			}
//			BT_Server_Client.stopBTThread();
		}
	}
	
	protected void toast(final String pMessage)
	{
		Toast.makeText(this, pMessage, Toast.LENGTH_LONG).show();
	}
}
