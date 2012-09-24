package com.amikomgamedev.ulartangga.states;

import org.anddev.andengine.engine.Engine;
import org.anddev.andengine.engine.camera.Camera;
import org.anddev.andengine.engine.camera.hud.HUD;
import org.anddev.andengine.engine.handler.IUpdateHandler;
import org.anddev.andengine.engine.options.EngineOptions;
import org.anddev.andengine.engine.options.EngineOptions.ScreenOrientation;
import org.anddev.andengine.engine.options.resolutionpolicy.RatioResolutionPolicy;
import org.anddev.andengine.entity.modifier.MoveByModifier;
import org.anddev.andengine.entity.modifier.MoveModifier;
import org.anddev.andengine.entity.scene.Scene;
import org.anddev.andengine.entity.scene.Scene.IOnSceneTouchListener;
import org.anddev.andengine.entity.scene.background.ColorBackground;
import org.anddev.andengine.entity.text.ChangeableText;
import org.anddev.andengine.entity.util.FPSLogger;
import org.anddev.andengine.input.touch.TouchEvent;
import org.anddev.andengine.input.touch.detector.ClickDetector;
import org.anddev.andengine.input.touch.detector.ClickDetector.IClickDetectorListener;
import org.anddev.andengine.input.touch.detector.ScrollDetector;
import org.anddev.andengine.input.touch.detector.ScrollDetector.IScrollDetectorListener;
import org.anddev.andengine.input.touch.detector.SurfaceScrollDetector;
import org.anddev.andengine.sensor.accelerometer.AccelerometerData;
import org.anddev.andengine.sensor.accelerometer.IAccelerometerListener;
import org.anddev.andengine.ui.activity.BaseGameActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.hardware.SensorManager;
import android.view.KeyEvent;
import android.widget.Toast;

import com.amikomgamedev.ulartangga.Config;
import com.amikomgamedev.ulartangga.Data;
import com.amikomgamedev.ulartangga.Define;
import com.amikomgamedev.ulartangga.Game;
import com.amikomgamedev.ulartangga.Loading;
import com.amikomgamedev.ulartangga.SoundManager;
import com.amikomgamedev.ulartangga.Utils;
import com.amikomgamedev.ulartangga.serverData;
import com.amikomgamedev.ulartangga.entity.Entity_Camera;
import com.amikomgamedev.ulartangga.entity.Entity_Mc;
import com.amikomgamedev.ulartangga.multiplayer.bluetooth.BT_Server_Client;

public class State_Gameplay extends 	BaseGameActivity
							implements 	IUpdateHandler,
										IScrollDetectorListener,
										IOnSceneTouchListener,
										IClickDetectorListener,
										Define,
										Data,
										IAccelerometerListener
{
	private final static int STATE_GAME_START		= 0;
	private final int STATE_GAME_LOADING	= 1;
	private final int STATE_GAME_INGAME		= 2;
	private final int STATE_GAME_PAUSE		= 3;
	private final int STATE_GAME_OVER		= 4;
	private final int STATE_GAME_RESTART	= 5;
	private final int STATE_GAME_SETTING	= 6;
	private final int STATE_GAME_FINISH	= 7;
	public static int State_Game_Current			= STATE_GAME_START;
	
	
	private final int CEK_SNAKE		= 0;
	private final int CEK_LADDER	= 1;
	private final int CEK_COLLISION	= 2;
	private final int CEK_IDLE		= 3;
	private int Cek_Current			= -1;
	
	private int cek			= -1;
	private int Player_Max 	= -1;
	protected int Player_Cur 	= -1;
	private int Map 		= -1;
	
	private float CurrentSecond = 0;
	public static float Second;
	public static int randomValue;
	
	public static Camera camera;
	public static Scene scene;
	protected Entity_Mc[] mc;
	
	private SurfaceScrollDetector	mScrollDetector	= new SurfaceScrollDetector(this);
	private ClickDetector			clickDetector	= new ClickDetector(this);
	private Entity_Camera cam;
	protected SoundManager			soundManager = new SoundManager();

	private static ChangeableText playerName;
	public static ChangeableText[] curPosition;

	public static boolean moveCamera = true;
	public static boolean moveSlide = true;
	public static boolean diceEnable = true;
	private boolean moveAgain_1 = true;
	private boolean moveAgain_2 = true;
	private boolean moveAgain_3 = true;
	private boolean move = true;
	public static boolean autoMoveNextPlayer = true;
	private boolean autoMove = true;
	private boolean throwStart = true;
	protected boolean isMultiPlayer = false;
	
	serverData sData = serverData.getInstance();
	private HUD hud;
	public static boolean moveMP = false;

	public static boolean autoSwitch = false;
	public static int autoSwitchState = -1;

	public static boolean isShakeEnable 	= true;
	private boolean isShake 		= false;
	private boolean isActive = false;
	
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
		Player_Max 	= sData.getMaxPlayer();
		Player_Cur 	= Player_Max - 1;
		Map 		= sData.getSelectMap();

		Game.setMap(Map);
		Game.setMaxPlayer(Player_Max);
		
		soundManager.loadSoundGameplay();
	}
	
	public Scene onLoadScene()
	{
		mEngine.registerUpdateHandler(new FPSLogger());
		mEngine.registerUpdateHandler(this);

		hud = new HUD();
		camera.setHUD(hud);
		this.
		scene = new Scene();
		scene.setOnSceneTouchListener(this);
		scene.setOnSceneTouchListenerBindingEnabled(true);

		enableAccelerometerSensor(this);
		switchState(STATE_GAME_START);
		return scene;
	}
		
	public void onLoadComplete(){

//		showDialogChooseTypeDice();
	}
		
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
			case STATE_GAME_FINISH:
				
				finishGP();
				
				break;
				
			case STATE_GAME_START:
				
				if(Loading.isLoading())
					Loading.updateLoading();
				else
					switchState(STATE_GAME_LOADING);
				
				break;
				
			case STATE_GAME_LOADING:
				
				if(Loading.isLoading())
					Loading.updateLoading();
				else
				{
					attachInGame();
					attachGamePause();
					attachGameOver();
					attachOption();

					switchState(STATE_GAME_INGAME);
					switchCek(CEK_IDLE);
				}
				
				break;
				
			case STATE_GAME_INGAME:
				
				cam.backCameraToMap();
				
				for(int i = 0; i < Player_Max; i++)
					curPosition[i].setText("" +mc[i].Posisi_Mc_Current);

				if(mc[Player_Cur].isMoving())
				{
					if(move)
					{
						soundManager.playSfx(soundManager.SFX_JALAN);
						
						mc[Player_Cur].onUpdate();
						
						cam.autoMoveCamera(
								mc[Player_Cur],
								Define.GAME_MAP_CELL_WIDTH);

						autoMove = true;
					}
					else if(timer(2.5f))
					{
						move = true;
						Game.spr_Dice.setVisible(false);
						
						playerName.setText(PLAYER_NAME[Player_Cur] + " Is Moving");
						playerName.setPosition(
								Config.GAME_SCREEN_WIDTH - playerName.getWidth() - Utils.getRatioW(10),
								Utils.getRatioH(2));
					}
				}
				else
				{
					switch (Cek_Current)
					{
						case CEK_SNAKE:
							
							mc[Player_Cur].moveSnakeOrLadder(
									SNAKE_N_LADDER[Map][CELL_SNAKE_START][cek],
									SNAKE_N_LADDER[Map][CELL_SNAKE_END][cek]);

							cam.autoMoveCamera(
									mc[Player_Cur],
									Define.GAME_MAP_CELL_WIDTH);
							
							if(mc[Player_Cur].Posisi_Mc_Current != SNAKE_N_LADDER[Map][CELL_SNAKE_START][cek])
							{
								soundManager.stopSfx(soundManager.SFX_TURUN);
								switchCek(CEK_IDLE);
							}
							
							break;
							
						case CEK_LADDER:
							
							mc[Player_Cur].moveSnakeOrLadder(
									SNAKE_N_LADDER[Map][CELL_LADDER_START][cek],
									SNAKE_N_LADDER[Map][CELL_LADDER_END][cek]);
							
							cam.autoMoveCamera(
									mc[Player_Cur],
									Define.GAME_MAP_CELL_WIDTH);

							if(mc[Player_Cur].Posisi_Mc_Current != SNAKE_N_LADDER[Map][CELL_LADDER_START][cek])
							{
								soundManager.stopSfx(soundManager.SFX_NAIK);
								switchCek(CEK_IDLE);
							}
							break;
							
						case CEK_COLLISION:
							
							if (throwStart)
							{
								mc[cek].throwToStart(mc[Player_Cur].getDistance());
								
								cam.autoMoveCamera(
										mc[cek],
										Utils.getRatioW(((camera.getWidth() - mc[cek].getAnimatedSprite().getWidth()) / 2)));
								
								if(timer(1f))
									Game.spr_Smoke.setVisible(false);
								
								if(mc[Player_Cur].Posisi_Mc_Current != mc[cek].Posisi_Mc_Current)
								{
									switchCek(CEK_IDLE);
								}
								
							}
							else if(timer(1))
							{
								
								soundManager.stopSfx(soundManager.SFX_BERANTEM);
								soundManager.playSfx(soundManager.SFX_TERLEMPAR);
								throwStart = true;
							}

							playerName.setText(PLAYER_NAME[cek] + " Back To Start");
							playerName.setPosition(
									Config.GAME_SCREEN_WIDTH - playerName.getWidth() - Utils.getRatioW(10),
									Utils.getRatioH(2));
							break;
							
						case CEK_IDLE:
							
							playerName.setText(PLAYER_NAME[nextPlayer()] + " Turn");
							playerName.setPosition(
									Config.GAME_SCREEN_WIDTH - playerName.getWidth() - Utils.getRatioW(10),
									Utils.getRatioH(2));
							
							mc[Player_Cur].stop();
							move = false;
							soundManager.stopSfx(soundManager.SFX_JALAN);
							
//							Utils.TRACE("autoMove = " +autoMove);
							
//							valueDice.setText(""+Utils.getRandomValuie());
							if(!autoMoveNextPlayer || Game.spr_Smoke.isVisible())
							{
								
								Game.spr_Img_Button_Slide_Bg.setVisible(false);
								Game.spr_img_Button_Shake.setVisible(false);
								diceEnable = false;
								autoMove = false;
							}
							else
							{
								if(autoMove && !moveMP)
									cam.autoMoveCameraToNextPlayer(
											mc[nextPlayer()],
											 Define.GAME_MAP_CELL_WIDTH);
								else
								{
									if(!isShakeEnable)
										Game.spr_Img_Button_Slide_Bg.setVisible(true);
									else
										Game.spr_img_Button_Shake.setVisible(true);
									diceEnable = true;
								}
							}
							
							// cek 2 pemain 1 cell
							if(mc[Player_Cur].Posisi_Mc_Current != mc[Player_Cur].POSISI_MC_START)
							{
								for(int i = 0; i < Player_Max; i++)
								{
									if(mc[Player_Cur].Posisi_Mc_Current == mc[i].Posisi_Mc_Current && Player_Cur != i)
									{
										cek = i;
										switchCek(CEK_COLLISION);
										break;
									}
									else
									{
										throwStart = false;
										moveAgain_1 = true;
									}
								}
							}
							
							// pengecekan tangga
							for(int i = 0; i < SNAKE_N_LADDER[Map][CELL_LADDER_START].length; i++)
							{
								if(mc[Player_Cur].Posisi_Mc_Current == SNAKE_N_LADDER[Map][CELL_LADDER_START][i])
								{
									cek = i;
									switchCek(CEK_LADDER);
									break;
								}
								else
									moveAgain_2 = true;
							}
							// pengecekan ular
							for(int i = 0; i < SNAKE_N_LADDER[Map][CELL_SNAKE_START].length; i++)
							{
								if(mc[Player_Cur].Posisi_Mc_Current == SNAKE_N_LADDER[Map][CELL_SNAKE_START][i])
								{
									cek = i;
									switchCek(CEK_SNAKE);
									break;
								}
								else
									moveAgain_3 = true;
							}
							
							if(mc[Player_Cur].Posisi_Mc_Current == COLUMN_COUNT * ROW_COUNT) // finish
							{
								switchState(STATE_GAME_OVER);
							}
							else if(randomValue == 6 && moveAgain_1 && moveAgain_2 && moveAgain_3 && !moveMP)
							{
								randomValue = 0;
								Player_Cur = previousPlayer();
							}
							
							if((Game.spr_Img_Button_Slide_Bg.isVisible() || Game.spr_img_Button_Shake.isVisible()) && !Game.spr_Smoke.isVisible())
							{
								
								if(sData.getTypePlayer(nextPlayer()) == TYPE_AI)
								{
//										if(timer(0.5f))
									{

										dice();
									}
								}
								else if(sData.getTypePlayer(nextPlayer()) == TYPE_PLAYER)
								{
									if(isShakeEnable)
									{
										Game.spr_Img_Button_Slide_Bg.setVisible(false);
										if(isShake)
										{
											isShake = false;
											dice();
										}
									}
									else if(Game.spr_Img_Button_Slide.getX() + Game.spr_Img_Button_Slide.getWidth() 
											>= Game.spr_Img_Button_Slide_Bg.getWidth() - Game.spr_Img_Button_Slide.getY())
									{
										Game.spr_img_Button_Shake.setVisible(false);
										if(diceEnable)
										{
											dice();
										}
									}
									else
									{
										Game.spr_img_Button_Shake.setVisible(false);
									}
								}
								else
								{
									Game.spr_Img_Button_Slide_Bg.setVisible(false);
									Game.spr_img_Button_Shake.setVisible(false);
									
									if(moveMP)
									{
										moveMP = false;
										
										soundManager.playSfx(soundManager.SFX_DICE);
										switchPlayer();
										mc[Player_Cur].setMove(randomValue);

										MoveModifier moveModifier = new MoveModifier(
												1, 
												-Game.spr_Dice.getWidth(), 
												(Config.GAME_SCREEN_WIDTH - Game.spr_Dice.getWidth()) / 2,
												(Config.GAME_SCREEN_HEIGHT - Game.spr_Dice.getHeight()) / 2,
												(Config.GAME_SCREEN_HEIGHT - Game.spr_Dice.getHeight()) / 2);
										Game.spr_Dice.registerEntityModifier(moveModifier);
										
										Game.spr_Dice.setVisible(true);
										Game.spr_Dice.setVisible(true);
										Game.spr_Dice.animate(
												SPR_DICE_SPEED,
												SPR_DICE_FRAME[randomValue - 1],
												0);
									}
								}
							}
							
							break;
					}
				}
				
				break;
		}
		
		
		if(autoSwitch)
		{
			autoSwitch = false;
			switchState(autoSwitchState);
		}
	}
	
	public void reset(){}
	
	public boolean onSceneTouchEvent(Scene pScene, TouchEvent pSceneTouchEvent) 
	{
		clickDetector.onTouchEvent(pSceneTouchEvent);	
		
		switch (State_Game_Current)
		{
			case STATE_GAME_START:
				
				break;
			case STATE_GAME_LOADING:
				
				break;
			case STATE_GAME_INGAME:
				
				
				if((pSceneTouchEvent.isActionDown() || pSceneTouchEvent.isActionMove())
						&& (!Utils.isOnArea(pSceneTouchEvent, camera, Game.spr_Img_Button_Slide_Bg)) 
						|| !Game.spr_Img_Button_Slide_Bg.isVisible()
						&& !moveSlide)
				{
					mScrollDetector.onTouchEvent(pSceneTouchEvent);
					autoMoveNextPlayer = false;
					moveCamera = true;
				}
				
				if((pSceneTouchEvent.isActionDown() || pSceneTouchEvent.isActionMove())
						&& (Utils.isOnArea(
								pSceneTouchEvent,
								camera,
								Game.spr_Img_Button_Slide_Bg)
						&& Game.spr_Img_Button_Slide_Bg.isVisible()))
				{
					mScrollDetector.onTouchEvent(pSceneTouchEvent);
					moveCamera = false;
					moveSlide = true;
				}
				
				if(pSceneTouchEvent.isActionUp())
				{
					autoMoveNextPlayer = true;
					moveSlide = false;
					
					MoveByModifier modifier = new MoveByModifier(
							0.5f,
							Game.spr_Img_Button_Slide.getY() - Game.spr_Img_Button_Slide.getX(),
							0);
					Game.spr_Img_Button_Slide.registerEntityModifier(modifier);
				}
				
				break;
	
			case STATE_GAME_PAUSE:
				
				if(Utils.isOnArea(
						pSceneTouchEvent,
						camera,
						Game.spr_GamePause_Bg,
						Game.spr_GamePause_Btn[PLAY])
						&& pSceneTouchEvent.isActionUp())
				{
					switchState(STATE_GAME_INGAME);
				}
				
				if(Utils.isOnArea(
						pSceneTouchEvent,
						camera,
						Game.spr_GamePause_Bg,
						Game.spr_GamePause_Btn[RESTART])
						&& pSceneTouchEvent.isActionUp())
				{
					if(!isMultiPlayer)
					{
						sendMessage("restart,0");
						switchState(STATE_GAME_RESTART);
					}
				}
				
				break;
				
			case STATE_GAME_OVER:
				
				if(Utils.isOnArea(
						pSceneTouchEvent,
						camera,
						Game.spr_GameOver_Bg,
						Game.spr_GameOver_Btn_Restart)
						&& pSceneTouchEvent.isActionUp())
				{
					sendMessage("restart,0");
					switchState(STATE_GAME_RESTART);
				}
				
				break;
				
			case STATE_GAME_RESTART:
				
				break;
				
			case STATE_GAME_SETTING:
				
				break;
		}
		
		return true;
	}

	public void onClick(ClickDetector pClickDetector, TouchEvent pTouchEvent)
	{

		switch (State_Game_Current)
		{
			case STATE_GAME_START:
				
				break;
			case STATE_GAME_LOADING:
				
				break;
			case STATE_GAME_INGAME:
				
				if(Utils.isOnArea(
						pTouchEvent,
						camera,
						Game.spr_Img_Button_Pause))
				{
					switchState(STATE_GAME_PAUSE);
				}
				
				break;
	
			case STATE_GAME_PAUSE:
				
				if(Utils.isOnArea(
						pTouchEvent,
						camera,
						Game.spr_GamePause_Bg,
						Game.spr_GamePause_Btn[HOME]))
				{
					if(isMultiPlayer)
					{
						toast("Disconected");
						sendMessage("disconnect,game");
					}
					startActivity(new Intent(State_Gameplay.this, BT_Server_Client.class));
					finish();
				}
				
				if(Utils.isOnArea(
						pTouchEvent,
						camera,
						Game.spr_GamePause_Bg,
						Game.spr_GamePause_Btn[SETTING]))
				{
					switchState(STATE_GAME_SETTING);
				}
				
				break;
				
			case STATE_GAME_OVER:
				
				if(Utils.isOnArea(
						pTouchEvent,
						camera,
						Game.spr_GameOver_Bg,
						Game.spr_GameOver_Btn_MainMenu))
				{
//					BT_Server_Client.stopBTThread();
					startActivity(new Intent(State_Gameplay.this, BT_Server_Client.class));
					finish();
				}
				
				break;
			case STATE_GAME_RESTART:
				
				break;
			case STATE_GAME_SETTING:
				
				for (int i = 0; i < 3; i++)
				{
					if(Utils.isOnArea(
							pTouchEvent,
							camera,
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
								soundManager.stopMusic(soundManager.BGM_GAMEPLAY);
							}
							else
							{
								soundManager.bgmEnable = true;
								Game.txt_Menu_Setting[i][0].setVisible(true);
								Game.txt_Menu_Setting[i][1].setVisible(false);
								soundManager.playMusic(soundManager.BGM_GAMEPLAY);
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
		}
	}
	
	public void onScroll(ScrollDetector pScollDetector, TouchEvent pTouchEvent,
			float pDistanceX, float pDistanceY)
	{
		if(moveCamera)
			cam.moveCamera(-pDistanceX, -pDistanceY);
		if(moveSlide)
		{
			if((Game.spr_Img_Button_Slide.getX() > Game.spr_Img_Button_Slide.getY() && pDistanceX < 0)
					|| (Game.spr_Img_Button_Slide.getX() + Game.spr_Img_Button_Slide.getWidth() < Game.spr_Img_Button_Slide_Bg.getWidth() - Game.spr_Img_Button_Slide.getY()) && pDistanceX > 0)
			{
				Game.spr_Img_Button_Slide.setPosition(
						Game.spr_Img_Button_Slide.getX() + Utils.getRatio(pDistanceX),
						Game.spr_Img_Button_Slide.getY());
			}
		}
	}
	
	@Override
	protected void onPause() {
		if(soundManager.bgm[soundManager.BGM_GAMEPLAY] != null)
		{
			soundManager.stopMusic(soundManager.BGM_GAMEPLAY);
		}
		if (State_Game_Current == STATE_GAME_INGAME) {
			switchState(STATE_GAME_PAUSE);			
		}
		super.onPause();
	}
	
	@Override
	protected void onResume() {
		if(soundManager.bgm[soundManager.BGM_GAMEPLAY] != null)
		{
			soundManager.playMusic(soundManager.BGM_GAMEPLAY);
		}
		super.onResume();
	}
	
	private void switchState(int curState)
	{
		State_Game_Current = curState;
		switch(State_Game_Current)
		{
			case STATE_GAME_START:
				
				scene.setBackground(new ColorBackground(1, 1, 1));
				Loading.setLoading(Loading.LOADING_TYPE_GAMEPLAY_OPEN);
				
				break;
				
			case STATE_GAME_LOADING:
				
				scene.attachChild(Game.spr_Img_Loading);				
				Loading.setLoading(Loading.LOADING_TYPE_GAMEPLAY);
				
				break;
				
			case STATE_GAME_INGAME:
				
				soundManager.playAgainAllSfx();
				
				autoMoveNextPlayer = true;
				
				Game.spr_Img_Button_Pause.setVisible(true);
				
				Game.spr_GamePause_Bg.setVisible(false);
				Game.txtPause.setVisible(false);
				
				for(int i = 0; i < Player_Max; i++)
				{
					Game.spr_GamePause_Mc_Idle[i].setVisible(false);
//					Game.spr_GamePause_Mc_Icon[i].setVisible(false);
//					Game.curPositionPause[i].setVisible(false);
				}
				
					Game.spr_Dice.setVisible(false);
				
				break;
				
			case STATE_GAME_PAUSE:
				
				soundManager.pauseAllSfx();
				
				Game.spr_Img_Button_Pause.setVisible(false);
				Game.spr_Img_Button_Slide_Bg.setVisible(false);
				Game.spr_img_Button_Shake.setVisible(false);
				
				if(isMultiPlayer)
					Game.spr_GamePause_Btn[RESTART].setVisible(false);
				else
					Game.spr_GamePause_Btn[RESTART].setVisible(true);
				
				mc[Player_Cur].stop();
				for (int i = 0; i < Player_Max; i++)
				{
					mc[i].getAnimatedSprite().stopAnimation();					
				}

				Game.spr_GamePause_Bg.setVisible(true);
				Game.txtPause.setVisible(true);
				Game.spr_GamePause_Mc_Idle[Player_Cur].setVisible(true);
				Game.spr_GamePause_Mc_Idle[Player_Cur].animate(
						GAMEPAUSE_MC_IDLE_ANIM_SPEED,
						GAMEPAUSE_MC_IDLE_ANIM_FRAME[0],
						GAMEPAUSE_MC_IDLE_ANIM_FRAME[1],
						true);
				
//				for(int i = 0; i < Player_Max; i++)
//				{
//					Game.curPositionPause[i].setText(curPosition[i].getText());
//					Game.spr_GamePause_Mc_Icon[i].setVisible(true);
//					Game.curPositionPause[i].setVisible(true);
//					
//					Game.curPositionPause[i].setColor(0, 0, 0);
//				}	
				break;
				
			case STATE_GAME_OVER:

				Game.spr_Img_Button_Pause.setVisible(false);
				Game.spr_Img_Button_Slide_Bg.setVisible(false);
				Game.spr_img_Button_Shake.setVisible(false);
				
				int playerWin	= 0;

				Game.spr_GameOver_Mc[Player_Cur].setVisible(true);
				Game.spr_GameOver_Text[Player_Cur].setVisible(true);
				
				playerName.setText(PLAYER_NAME[Player_Cur] + " Wins!");
				playerName.setPosition(
						Config.GAME_SCREEN_WIDTH - playerName.getWidth() - Utils.getRatioW(10),
						Utils.getRatioH(2));
				
				Game.spr_GameOver_Bg.setVisible(true);
				
				break;
				
			case STATE_GAME_RESTART:
				
				for (int i = 0; i < Player_Max; i++)
				{
					moveMP = false;
					mc[i].reset();
					Player_Cur = Player_Max - 1;

					Game.spr_GameOver_Mc[i].setVisible(false);
					Game.spr_GameOver_Text[i].setVisible(false);
//					Game.spr_GameOver_Mc[i][MC_LOSE].setVisible(false);
				}
				Game.spr_GameOver_Bg.setVisible(false);
				cam.reset();
				
				switchState(STATE_GAME_INGAME);
				
				break;
				
			case STATE_GAME_SETTING:
				
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
				
				break;
		}
	}
	
	private void switchCek(int cek)
	{
		Cek_Current = cek;
		
		switch (Cek_Current)
		{
			case CEK_SNAKE:

				soundManager.playSfx(soundManager.SFX_TURUN);
				
				playerName.setText(PLAYER_NAME[Player_Cur] + " Get Snake");
				playerName.setPosition(
						Config.GAME_SCREEN_WIDTH - playerName.getWidth() - Utils.getRatioW(10),
						Utils.getRatioH(2));
				
				moveAgain_3 = false;
				Game.spr_Img_Button_Slide_Bg.setVisible(false);
				Game.spr_img_Button_Shake.setVisible(false);
				
				break;
				
			case CEK_LADDER:

				soundManager.playSfx(soundManager.SFX_NAIK);
				
				playerName.setText(PLAYER_NAME[Player_Cur] + " Get Ladder");
				playerName.setPosition(
						Config.GAME_SCREEN_WIDTH - playerName.getWidth() - Utils.getRatioW(10),
						Utils.getRatioH(2));
				
				moveAgain_2 = false;
				Game.spr_Img_Button_Slide_Bg.setVisible(false);
				Game.spr_img_Button_Shake.setVisible(false);
				
				break;
				
			case CEK_COLLISION:
				
				soundManager.playSfx(soundManager.SFX_BERANTEM);
				
				
				
				moveAgain_1 = false;
				Game.spr_Img_Button_Slide_Bg.setVisible(false);
				Game.spr_img_Button_Shake.setVisible(false);
				
				Game.spr_Smoke.setPosition(
						mc[Player_Cur].getAnimatedSprite().getX() - 
						(Game.spr_Smoke.getWidth() - mc[Player_Cur].getAnimatedSprite().getWidth()) / 2,
						mc[Player_Cur].getAnimatedSprite().getY() - 
						(Game.spr_Smoke.getHeight() - mc[Player_Cur].getAnimatedSprite().getHeight()) / 2);
				Game.spr_Smoke.animate(
						GAMEPLAY_SMOKE_ANIM_SPEED,
						GAMEPLAY_SMOKE_ANIM_FRAME[ANI_FRAME_START],
						GAMEPLAY_SMOKE_ANIM_FRAME[ANI_FRAME_END],
						true);
				Game.spr_Smoke.setVisible(true);
				
				break;
				
			case CEK_IDLE:
				
				autoMove = true;
				
				playerName.setPosition(
						Config.GAME_SCREEN_WIDTH - playerName.getWidth() - Utils.getRatioW(10),
						Utils.getRatioH(2));
				
				break;
		}
	}

	protected void switchPlayer()
	{
		if(Player_Max == Player_Cur + 1)
			Player_Cur = PLAYER_1;
		else
			Player_Cur++;
	}

	private int nextPlayer()
	{
		int nextPlayer;
		if(Player_Cur == Player_Max - 1)
			nextPlayer = PLAYER_1;
		else
			nextPlayer = Player_Cur + 1;
		
		return nextPlayer;
	}
	
	private int previousPlayer()
	{
		int previousPlayer;
		if(Player_Cur == PLAYER_1)
			previousPlayer = Player_Max - 1;
		else
			previousPlayer = Player_Cur - 1;

		return previousPlayer;
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
			case STATE_GAME_START:
				
				break;
			case STATE_GAME_LOADING:
				
				break;
			case STATE_GAME_INGAME:
				
				if(keyCode == KeyEvent.KEYCODE_BACK)
					switchState(STATE_GAME_PAUSE);
				
				break;
	
			case STATE_GAME_PAUSE:
				
				if(keyCode == KeyEvent.KEYCODE_BACK)
					switchState(STATE_GAME_INGAME);
				
				break;
				
			case STATE_GAME_OVER:
				if(keyCode == KeyEvent.KEYCODE_BACK)
				{
					if(isMultiPlayer)
					{
						toast("Disconected");
						sendMessage("disconnect,game");
					}
					
					startActivity(new Intent(State_Gameplay.this, BT_Server_Client.class));
					finish();
				}
				
				break;
			case STATE_GAME_RESTART:
				
				break;
			case STATE_GAME_SETTING:
				
				if(keyCode == KeyEvent.KEYCODE_BACK)
				{
					Game.spr_Img_Option_Bg.setVisible(false);
					switchState(STATE_GAME_PAUSE);
				}
				
				break;
		}
		
		return super.onKeyUp(keyCode, event);
	}
	
	
	private void attachInGame()
	{
		isActive = true;
		
		soundManager.playMusic(soundManager.BGM_GAMEPLAY);
		
		scene.detachChildren();
		scene.attachChild(Game.spr_Img_Map);
		
		mc = new Entity_Mc[Player_Max];
		cam = new Entity_Camera(
				camera,
				Game.spr_Img_Map.getX(), 
				Game.spr_Img_Map.getX() + Game.spr_Img_Map.getWidth(),
				Game.spr_Img_Map.getY() - Game.spr_Img_Informasi_Header.getHeight(), 
				Game.spr_Img_Map.getY() + Game.spr_Img_Map.getHeight() + Game.spr_Img_Informasi_Footer.getHeight());

		for(int i = 0; i < Player_Max; i++)
		{
			mc[i] = new Entity_Mc(Game.spr_MC[i]);
			scene.attachChild(Game.spr_MC[i]);
		}
		
		scene.attachChild(Game.spr_Smoke);
		Game.spr_Smoke.setVisible(false);
		
		hud.attachChild(Game.spr_Img_Informasi_Footer);
		hud.attachChild(Game.spr_Img_Informasi_Header);
		hud.attachChild(Game.spr_Img_Button_Slide_Bg);
		hud.attachChild(Game.spr_img_Button_Shake);
		hud.attachChild(Game.spr_Img_Button_Pause);
		
		hud.attachChild(Game.spr_Dice);	
		
		
		float posX = Utils.getRatioW(40);
		curPosition = new ChangeableText[Player_Max];
		playerName = new ChangeableText(0, 0, Game.font[Data.FONT_SIZE_SMALL], "", 30);
//		valueDice = new ChangeableText(0, 0, Game.font[Data.FONT_SIZE_BIG], "", 1);
		
		playerName.setColor(0, 0, 0);
//		valueDice.setColor(0, 0, 0);
		
//		valueDice.setPosition(
//				Utils.getRatio(50),
//				(spr_Img_Botton_Slide.getHeight() - valueDice.getHeight()) / 2);
		
		for(int i = 0; i < Player_Max; i++)
		{
			hud.attachChild(Game.spr_Icon_MC[i]);
			curPosition[i] = new ChangeableText(
					posX, Game.spr_Icon_MC[i].getY() + Utils.getRatio(20),
					Game.font[Data.FONT_SIZE_SMALL], "0", 3);
			hud.attachChild(curPosition[i]);
			posX+=Define.GAME_MAP_CELL_WIDTH;
			curPosition[i].setColor(0, 0, 0);
		}
		
//		spr_Img_Botton_Slide.attachChild(valueDice);
		hud.attachChild(playerName);
	}

	private void attachGamePause()
	{
		hud.attachChild(Game.spr_GamePause_Bg);
		Game.spr_GamePause_Bg.attachChild(Game.txtPause);
		for (int i = 0; i < GAMEPAUSE_BTN.length; i++)
		{
			Game.spr_GamePause_Bg.attachChild(Game.spr_GamePause_Btn[i]);			
		}
		
		Game.txtPause.setColor(0, 0, 0);
		
		for(int i = 0; i < Player_Max; i++)
		{
			Game.spr_GamePause_Bg.attachChild(Game.spr_GamePause_Mc_Idle[i]);
//			Game.spr_GamePause_Bg.attachChild(Game.spr_GamePause_Mc_Icon[i]);
//			Game.spr_GamePause_Mc_Icon[i].attachChild(Game.curPositionPause[i]);
		}
	}
	
	private void attachGameOver()
	{
//		txtWin = new ChangeableText(
//				0, 0,
//				Game.font[Data.FONT_SIZE_MEDIUM],
//				PLAYER_NAME[Player_Cur] +" Wins!");
		
		hud.attachChild(Game.spr_GameOver_Bg);
//		Game.spr_GameOver_Bg.attachChild(txtWin);

		Game.spr_GameOver_Bg.setVisible(false);
	}
	
	private void attachOption(){
		
		hud.attachChild(Game.spr_Img_Option_Bg);
		Game.spr_Img_Option_Bg.setVisible(false);

	}
	
	public void sendMessage(String str) {
		// TODO Auto-generated method stub

	}
	
	protected void toast(final String pMessage)
	{
		Toast.makeText(this, pMessage, Toast.LENGTH_LONG).show();
	}
	
	public void finishGP()
	{
		startActivity(new Intent(this, BT_Server_Client.class));
		finish();
	}
	
	private void dice()
	{
		diceEnable = false;
		
		soundManager.playSfx(soundManager.SFX_DICE);
		
		Game.spr_Img_Button_Slide_Bg.setVisible(false);
		Game.spr_img_Button_Shake.setVisible(false);
		
		switchPlayer();
		
		randomValue = Utils.getRandomValuie();
		
		if(isMultiPlayer)
		{
			sendMessage("move,"+randomValue);
		}
			
		mc[Player_Cur].setMove(randomValue);
		
		MoveModifier moveModifier = new MoveModifier(
				1, 
				-Game.spr_Dice.getWidth(), 
				(Config.GAME_SCREEN_WIDTH - Game.spr_Dice.getWidth()) / 2,
				(Config.GAME_SCREEN_HEIGHT - Game.spr_Dice.getHeight()) / 2,
				(Config.GAME_SCREEN_HEIGHT - Game.spr_Dice.getHeight()) / 2);
		Game.spr_Dice.registerEntityModifier(moveModifier);
		
		Game.spr_Dice.setVisible(true);
		Game.spr_Dice.animate(
				SPR_DICE_SPEED,
				SPR_DICE_FRAME[randomValue - 1],
				0);
	}

	int isDice = 0;
	float mAccellCur = SensorManager.GRAVITY_EARTH;
	float mAccellLast = SensorManager.GRAVITY_EARTH;
	
	public void onAccelerometerChanged(AccelerometerData pAccelerometerData)
	{
		float x = pAccelerometerData.getX();
		float y = pAccelerometerData.getY();
		float z = pAccelerometerData.getZ();
		
		mAccellLast = mAccellCur;
		mAccellCur = (float) Math.sqrt(x*x + y*y + z*z);

		
//		Utils.TRACE("DICE = " +mAccellCur);
		if(mAccellCur > 20 && isShakeEnable)
		{
			if(mc != null)
			{
				if(mc[Player_Cur] != null)
					if(!mc[Player_Cur].isMoving() 
							&& Cek_Current == CEK_IDLE
							&& sData.getTypePlayer(nextPlayer()) == TYPE_PLAYER)
					{

						autoMoveNextPlayer = false;
						isDice++;
						if(isDice >= 2)
							soundManager.playSfx(soundManager.SFX_SHAKE);
					}
			}
		}
		else if(isDice >= 2 && mAccellCur > 9.6 && mAccellCur < 9.7)
		{

			autoMoveNextPlayer = true;
			isDice = 0;
			isShake = true;
		}		
	}
	
	private void showDialogChooseTypeDice() {
		new AlertDialog.Builder(this)
		.setMessage("How to Dice?")
		.setPositiveButton("Shake", new OnClickListener() {
			
			public void onClick(DialogInterface dialog, int which) {
				isShakeEnable = true;
				
			}
		})
		.setNegativeButton("Slide", new OnClickListener() {
			
			public void onClick(DialogInterface dialog, int which) {
				isShakeEnable = false;
			}
		})
		.show();
	}
}
