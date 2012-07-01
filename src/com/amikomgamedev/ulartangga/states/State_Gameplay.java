package com.amikomgamedev.ulartangga.states;

import org.anddev.andengine.engine.Engine;
import org.anddev.andengine.engine.camera.Camera;
import org.anddev.andengine.engine.handler.IUpdateHandler;
import org.anddev.andengine.engine.options.EngineOptions;
import org.anddev.andengine.engine.options.EngineOptions.ScreenOrientation;
import org.anddev.andengine.engine.options.resolutionpolicy.RatioResolutionPolicy;
import org.anddev.andengine.entity.IEntity;
import org.anddev.andengine.entity.modifier.FadeInModifier;
import org.anddev.andengine.entity.modifier.FadeOutModifier;
import org.anddev.andengine.entity.scene.Scene;
import org.anddev.andengine.entity.scene.Scene.IOnSceneTouchListener;
import org.anddev.andengine.entity.scene.background.ColorBackground;
import org.anddev.andengine.entity.sprite.Sprite;
import org.anddev.andengine.entity.text.ChangeableText;
import org.anddev.andengine.entity.text.Text;
import org.anddev.andengine.entity.util.FPSLogger;
import org.anddev.andengine.input.touch.TouchEvent;
import org.anddev.andengine.input.touch.detector.ScrollDetector;
import org.anddev.andengine.input.touch.detector.ScrollDetector.IScrollDetectorListener;
import org.anddev.andengine.input.touch.detector.SurfaceScrollDetector;
import org.anddev.andengine.ui.activity.BaseGameActivity;
import org.anddev.andengine.util.Debug;
import org.anddev.andengine.util.modifier.IModifier;
import org.anddev.andengine.util.modifier.ease.EaseBackIn;
import org.anddev.andengine.util.modifier.ease.EaseBackOut;
import org.anddev.andengine.util.modifier.ease.EaseLinear;
import org.anddev.andengine.util.modifier.ease.EaseStrongIn;

import com.amikomgamedev.ulartangga.Config;
import com.amikomgamedev.ulartangga.Data;
import com.amikomgamedev.ulartangga.Define;
import com.amikomgamedev.ulartangga.Game;
import com.amikomgamedev.ulartangga.Loading;
import com.amikomgamedev.ulartangga.Utils;
import com.amikomgamedev.ulartangga.serverData;
import com.amikomgamedev.ulartangga.entity.Entity_Camera;
import com.amikomgamedev.ulartangga.entity.Entity_Mc;
import com.amikomgamedev.ulartangga.gamelevel.Game_Level_Handler;

public class State_Gameplay extends 	BaseGameActivity
							implements 	IUpdateHandler,
										IScrollDetectorListener,
										IOnSceneTouchListener,
										Define,
										Game_Level_Handler
{
	
	private static final int STATE_GAME_START		= 0;
	private static final int STATE_GAME_LOADING		= 1;
	private static final int STATE_GAME_INGAME		= 2;
	private static final int STATE_GAME_MENU		= 3;
	private static final int STATE_GAME_OVER		= 4;
	private static final int STATE_GAME_SUMMARY		= 5;
	private static final int STATE_GAME_UNLOADING	= 6;
	private static int State_Game_Current			= STATE_GAME_START;
	
	private static int Player_Max = 4;
	public static int Player_Cur = Player_Max - 1;

	private static int Map = -1;
	private static float CurrentSecond = 0;
	public static float Second;
	
	public static Camera camera;
	public static Scene scene;
	public static Entity_Mc[] mc;
	private static SurfaceScrollDetector mScrollDetector;
	
	private static ChangeableText valueDice;
	private static ChangeableText playerName;
	public static ChangeableText[] curPosition;
	public static Text text; 
	
	public static boolean moveCamera = true;
	private static boolean diceEnable = true;
	private static boolean singlePlayer = false;
	
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
		sData.setSelectMap(MAP_KLASIK);
		sData.setCharPlayer1(CARACTER_1);
		sData.setCharPlayer2(CARACTER_2);
		sData.setCharPlayer3(CARACTER_3);
		sData.setCharPlayer4(CARACTER_4);
		
		Map = sData.getSelectMap();
	}
	
	public Scene onLoadScene()
	{
		mEngine.registerUpdateHandler(new FPSLogger());
		mEngine.registerUpdateHandler(this);
		
		scene = new Scene();
		scene.setOnSceneTouchListener(this);
		
		mScrollDetector = new SurfaceScrollDetector(this);
		mScrollDetector.setEnabled(true);
		
		switchState(STATE_GAME_START);
		
		return scene;
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
					Game.spr_Img_Logo.registerEntityModifier(new FadeOutModifier(1f));
					if(timer(1))
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
						playerName.setText(PLAYER_NAME[Player_Cur] + " Is Moving");
					}
					else
					{
						mc[Player_Cur].stop();
						moveCamera = true;

						Utils.setRandomValue();
						valueDice.setText(""+Utils.getRandomValuie());
						playerName.setText(PLAYER_NAME[nextPlayer()] + " Move");
						
						// cek 2 pemain 1 cell
						if(mc[Player_Cur].Posisi_Mc_Current != mc[Player_Cur].POSISI_MC_START)
						{
							for(int i = 0; i < Player_Max; i++)
							{
								if(mc[Player_Cur].Posisi_Mc_Current == mc[i].Posisi_Mc_Current && Player_Cur != i)
								{
									mc[i].throwToStart(mc[i].getDistance());
									Entity_Camera.autoMoveCamera(i);
									playerName.setText(PLAYER_NAME[i] + " Back To Start");
								}
							}
						}
						
						// pengecekan tangga
						for(int i = 0; i < SNAKE_N_LADDER[Map][CELL_LADDER_START].length; i++)
						{
							if(mc[Player_Cur].Posisi_Mc_Current == SNAKE_N_LADDER[Map][CELL_LADDER_START][i])
							{
								mc[Player_Cur].moveSnakeOrLadder(SNAKE_N_LADDER[Map][CELL_LADDER_START][i], SNAKE_N_LADDER[Map][CELL_LADDER_END][i]);
								Entity_Camera.autoMoveCamera(Player_Cur);
								playerName.setText(PLAYER_NAME[Player_Cur] + " Get Ladder");
							}
						}
						
						// pengecekan ular
						for(int i = 0; i < SNAKE_N_LADDER[Map][CELL_SNAKE_START].length; i++)
						{
							if(mc[Player_Cur].Posisi_Mc_Current == SNAKE_N_LADDER[Map][CELL_SNAKE_START][i])
							{
								mc[Player_Cur].moveSnakeOrLadder(SNAKE_N_LADDER[Map][CELL_SNAKE_START][i], SNAKE_N_LADDER[Map][CELL_SNAKE_END][i]);
								Entity_Camera.autoMoveCamera(Player_Cur);
								playerName.setText(PLAYER_NAME[Player_Cur] + " Get Snake");
							}
						}
						
						//	acak otomatis (singgle player) 
						if(singlePlayer && Player_Cur != Player_Max - 1 && moveCamera)
						{
							if(timer(2))
							{
								switchPlayer();
								Utils.setRandomValue();
								mc[Player_Cur].setMove(Utils.getRandomValuie());
							}
						}
						if(mc[Player_Cur].Posisi_Mc_Current == COLUMN_COUNT * ROW_COUNT) // finish
						{
							text = new Text(100, 200, Game.font[Data.FONT_SIZE_BIG], PLAYER_NAME[Player_Cur] +"\nWin");
							Game.hud.attachChild(text);
							playerName.setText(PLAYER_NAME[Player_Cur] + " Win");
							diceEnable = false;
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
		if(moveCamera)
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
				scene.setBackground(new ColorBackground(0, 0, 0));
				
				Loading.setLoading(Loading.LOADING_TYPE_GAMEPLAY_OPEN);
				break;
			case STATE_GAME_LOADING:
				scene.detachChildren();
				scene.setBackground(new ColorBackground(1, 1, 1));
				scene.attachChild(Game.spr_Img_Logo);

				Game.spr_Img_Logo.registerEntityModifier(new FadeInModifier(1f));
				
				Loading.setLoading(Loading.LOADING_TYPE_GAMEPLAY, Map);
				break;
			case STATE_GAME_INGAME:
				Game.msc_Gameplay.play();
				
				scene.detachChildren();
				scene.setBackground(new ColorBackground(0, 0, 0));
				scene.attachChild(Game.spr_Img_Map);
				
				mc = new Entity_Mc[Player_Max];
/*
				for(int i = 0; i < Player_Max; i++)
				{
					mc[i] = new Entity_Mc(Game.spr_MC[i]);
					scene.attachChild(Game.spr_MC[i]);
				}
*/		
				mc[0] = new Entity_Mc(Game.spr_MC[sData.getCharPlayer1()]);
				mc[1] = new Entity_Mc(Game.spr_MC[sData.getCharPlayer2()]);
				mc[2] = new Entity_Mc(Game.spr_MC[sData.getCharPlayer3()]);
				mc[3] = new Entity_Mc(Game.spr_MC[sData.getCharPlayer4()]);

				scene.attachChild(Game.spr_MC[0]);
				scene.attachChild(Game.spr_MC[1]);
				scene.attachChild(Game.spr_MC[2]);
				scene.attachChild(Game.spr_MC[3]);
				Sprite spr_Img_Botton_Dice = new Sprite(Game.dicePosX, Game.dicePosY, Game.reg_Img_Button_Dice)
				{
					public boolean onAreaTouched(TouchEvent pSceneTouchEvent,
							float pTouchAreaLocalX, float pTouchAreaLocalY) {
						if(pSceneTouchEvent.isActionDown())
						{
							if(moveCamera && diceEnable)
							{
								switchPlayer();
								
								Utils.setRandomValue();
								int value = Utils.getRandomValuie();
								
								mc[Player_Cur].setMove(value);
								valueDice.setText(""+value);
							}
						}
						return true;
					}
				};
				spr_Img_Botton_Dice.setAlpha(0.5f);
				
				Game.hud.attachChild(Game.spr_Img_Informasi_Footer);
				Game.hud.attachChild(Game.spr_Img_Informasi_Header);
				Game.hud.attachChild(spr_Img_Botton_Dice);
				
				int posX = 40;
				curPosition = new ChangeableText[Game.spr_Icon_MC.length];
				valueDice = new ChangeableText(280, 1, Game.font[Data.FONT_SIZE_BIG], "", 1);
				playerName = new ChangeableText(5, Config.GAME_SCREEN_HEIGHT - 20, Game.font[Data.FONT_SIZE_SMALL], "", 30);
				
				for(int i = 0; i < Game.spr_Icon_MC.length; i++)
				{
					Game.hud.attachChild(Game.spr_Icon_MC[i]);
					curPosition[i] = new ChangeableText(posX, 20, Game.font[Data.FONT_SIZE_SMALL], "1", 3);
					Game.hud.attachChild(curPosition[i]);
					posX+=70;
				}
				
				Game.hud.attachChild(valueDice);
				Game.hud.attachChild(playerName);
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

	private void switchPlayer()
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
	
	private int nextPlayer()
	{
		int nextPlayer;
		if(Player_Max == Player_Cur + 1)
		{
			nextPlayer = PLAYER_1;
		}
		else
		{
			nextPlayer = Player_Cur + 1;
		}
		return nextPlayer;
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
