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
import org.anddev.andengine.entity.sprite.Sprite;
import org.anddev.andengine.entity.text.ChangeableText;
import org.anddev.andengine.entity.util.FPSLogger;
import org.anddev.andengine.input.touch.TouchEvent;
import org.anddev.andengine.input.touch.detector.ScrollDetector;
import org.anddev.andengine.input.touch.detector.ScrollDetector.IScrollDetectorListener;
import org.anddev.andengine.input.touch.detector.SurfaceScrollDetector;
import org.anddev.andengine.ui.activity.BaseGameActivity;

import android.util.Log;

import com.amikomgamedev.ulartangga.Config;
import com.amikomgamedev.ulartangga.Define;
import com.amikomgamedev.ulartangga.Game;
import com.amikomgamedev.ulartangga.Loading;
import com.amikomgamedev.ulartangga.Utils;
import com.amikomgamedev.ulartangga.entity.Entity_Camera;
import com.amikomgamedev.ulartangga.entity.Entity_Mc;

public class State_Gameplay extends BaseGameActivity
												implements IUpdateHandler,
											   IScrollDetectorListener,
											   IOnSceneTouchListener,
											   Define
{
	
	public static Camera mCamera;
	private static Scene mScene;
	private static SurfaceScrollDetector mScrollDetector;
	private static Entity_Mc[] mc;
	private static ChangeableText curPosition;
	
	private static final int STATE_GAME_START		= 0;
	private static final int STATE_GAME_LOADING		= 1;
	private static final int STATE_GAME_INGAME		= 2;
	private static final int STATE_GAME_MENU		= 3;
	private static final int STATE_GAME_OVER		= 4;
	private static final int STATE_GAME_SUMMARY		= 5;
	private static final int STATE_GAME_UNLOADING	= 6;
	private static int State_Game_Current			= 0;

	private static final int CARACTER_1	= 0;
	private static final int CARACTER_2	= 1;
	private static final int CARACTER_3	= 2;
	private static final int CARACTER_4	= 3;
	
	private static final int PLAYER_1	= 0;
	private static final int PLAYER_2	= 1;
	private static final int PLAYER_3	= 2;
	private static final int PLAYER_4	= 3;
	private static int Player_Max		= 4;
	private static int Player_Cur		= Player_Max - 1;
	
	private static int Map = 1;
	public static float Second;
	private static float CurrentSecond = 0;
	private static boolean playerNotMoving = true;
	private static boolean singlePlayer = false;
	
	public Engine onLoadEngine()
	{
		Game.appInit();
		mCamera = new Camera(0, 0, Config.GAME_SCREEN_WIDTH, Config.GAME_SCREEN_HEIGHT);
		mEngine = new Engine(
			new EngineOptions(true, ScreenOrientation.PORTRAIT,
			new RatioResolutionPolicy(Config.GAME_SCREEN_WIDTH, Config.GAME_SCREEN_HEIGHT),
			mCamera).setNeedsMusic(true).setNeedsSound(true));
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
		mScene = new Scene();
		mScene.setOnSceneTouchListener(this);
		mScrollDetector = new SurfaceScrollDetector(this);
		mScrollDetector.setEnabled(true);
		switchState(STATE_GAME_START);
		
		return mScene;
	}
		
	public void onLoadComplete(){}
		
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
			case STATE_GAME_START:
				if(Loading.isLoading())
				{
					Loading.updateLoading();
				}
				else
				{
					switchState(STATE_GAME_LOADING);
				}
				break;
			case STATE_GAME_LOADING:
				if(Loading.isLoading())
				{
					Loading.updateLoading();
				}
				else
				{
					switchState(STATE_GAME_INGAME);
				}
				break;
			case STATE_GAME_INGAME:
				if(mc != null)
				{
					if(mc[Player_Cur].isMoving())
					{
						mc[Player_Cur].updateMove();
						Entity_Camera.autoMoveCamera(Player_Cur);
						playerNotMoving = false;
					}
					else
					{
						mc[Player_Cur].stop();
						playerNotMoving = true;
						
						if(mc[Player_Cur].Posisi_Mc_Current != mc[Player_Cur].POSISI_MC_START)
						{
							if(mc[Player_Cur].Posisi_Mc_Current == mc[PLAYER_1].Posisi_Mc_Current)
							{
								if(Player_Cur != PLAYER_1)
								{
									mc[PLAYER_1].throwToStart(mc[Player_Cur].getDistance());
									Entity_Camera.autoMoveCamera(PLAYER_1);
								}
							}
							
							if(mc[Player_Cur].Posisi_Mc_Current == mc[PLAYER_2].Posisi_Mc_Current)
							{
								if(Player_Cur != PLAYER_2)
								{
									mc[PLAYER_2].throwToStart(mc[Player_Cur].getDistance());
									Entity_Camera.autoMoveCamera(PLAYER_2);
								}
							}
							
							if(mc[Player_Cur].Posisi_Mc_Current == mc[PLAYER_3].Posisi_Mc_Current)
							{
								if(Player_Cur != PLAYER_3)
								{
									mc[PLAYER_3].throwToStart(mc[Player_Cur].getDistance());
									Entity_Camera.autoMoveCamera(PLAYER_3);
								}
							}
							
							if(mc[Player_Cur].Posisi_Mc_Current == mc[PLAYER_4].Posisi_Mc_Current)
							{
								if(Player_Cur != PLAYER_4)
								{
									mc[PLAYER_4].throwToStart(mc[Player_Cur].getDistance());
									Entity_Camera.autoMoveCamera(PLAYER_4);
								}
							}
						}
						
						//	acak otomatis (singgle player) 
						if(singlePlayer)
						{
							if(Player_Cur != Player_Max - 1)
							{
								if(timer(2))
								{
									switchPlayer();
									Utils.setRandomValue();
									mc[Player_Cur].setMove(Utils.getRandomValuie());
								}
							}
						}
					}
				}
				break;
		}
	}
	
	public void reset(){}
	
	public boolean onSceneTouchEvent(Scene pScene, TouchEvent pSceneTouchEvent) 
	{
		mScrollDetector.onTouchEvent(pSceneTouchEvent);
		return true;
	}
	
	public void onScroll(ScrollDetector pScollDetector, TouchEvent pTouchEvent,
			float pDistanceX, float pDistanceY)
	{
		if(playerNotMoving)
		{
			Entity_Camera.moveCamera(pDistanceX, pDistanceY);
		}
	}
	
	private void switchState(int curState)
	{
		State_Game_Current = curState;
		switch(State_Game_Current)
		{
			case STATE_GAME_START:
				mScene.setBackground(new ColorBackground(0, 0, 0));
				Loading.setLoading(Loading.LOADING_TYPE_GAMEPLAY_OPEN);
				break;
			case STATE_GAME_LOADING:
				mScene.detachChildren();
				mScene.setBackground(new ColorBackground(1, 1, 1));
				mScene.attachChild(Game.spr_Img_Logo);
				Loading.setLoading(Loading.LOADING_TYPE_GAMEPLAY, Map);
				break;
			case STATE_GAME_INGAME:
				mScene.detachChildren();
				mScene.attachChild(Game.spr_Img_Map);
				mc = new Entity_Mc[Player_Max];
				for(int i = 0; i < Player_Max; i++)
				{
					mScene.attachChild(Game.spr_MC[i]);
					mc[i] = new Entity_Mc(Game.spr_MC[i]);
				}
				
				Sprite spr_Img_Botton_Dice = new Sprite(Game.dicePosX, Game.dicePosY, Game.reg_Img_Botton_Dice)
				{
					public boolean onAreaTouched(TouchEvent pSceneTouchEvent,
							float pTouchAreaLocalX, float pTouchAreaLocalY) {
						if(pSceneTouchEvent.isActionDown())
						{
							if(playerNotMoving)
							{
								State_Gameplay.switchPlayer();
								Utils.setRandomValue();
								State_Gameplay.mc[State_Gameplay.Player_Cur].setMove(Utils.getRandomValuie());
								Log.d("Value", "Random = " +Utils.getRandomValuie());
							}
						}
						return true;
					}
				};
				spr_Img_Botton_Dice.setAlpha(0.5f);
				
				Game.hud.attachChild(Game.spr_Img_Informasi_Footer);
				Game.hud.attachChild(Game.spr_Img_Informasi_Header);
				Game.hud.attachChild(spr_Img_Botton_Dice);
				for(int i = 0; i < Game.spr_Icon_MC.length; i++)
				{
					Game.hud.attachChild(Game.spr_Icon_MC[i]);
				}
//				Game.hud.attachChild(curPosition);
//				curPosition.setText("" +mc.Posisi_Mc_Current);
				
				Game.hud.registerTouchArea(spr_Img_Botton_Dice);
				break;
			case STATE_GAME_MENU:
				break;
			case STATE_GAME_OVER:
				break;
			case STATE_GAME_SUMMARY:
				break;
			case STATE_GAME_UNLOADING:
				break;
		}
	}

	private static void switchPlayer()
	{
		if(Player_Max == Player_Cur + 1)
		{
			Player_Cur = PLAYER_1;
		}
		else
		{
			Player_Cur++;
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
	
	public static void setMaxPlayer(int max)
	{
		Player_Max = max;
	}
}
