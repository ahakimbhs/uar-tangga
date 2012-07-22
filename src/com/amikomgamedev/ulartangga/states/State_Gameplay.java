package com.amikomgamedev.ulartangga.states;

import org.anddev.andengine.engine.Engine;
import org.anddev.andengine.engine.camera.Camera;
import org.anddev.andengine.engine.camera.hud.HUD;
import org.anddev.andengine.engine.handler.IUpdateHandler;
import org.anddev.andengine.engine.options.EngineOptions;
import org.anddev.andengine.engine.options.EngineOptions.ScreenOrientation;
import org.anddev.andengine.engine.options.resolutionpolicy.RatioResolutionPolicy;
import org.anddev.andengine.entity.modifier.FadeInModifier;
import org.anddev.andengine.entity.modifier.FadeOutModifier;
import org.anddev.andengine.entity.primitive.Rectangle;
import org.anddev.andengine.entity.scene.Scene;
import org.anddev.andengine.entity.scene.Scene.IOnSceneTouchListener;
import org.anddev.andengine.entity.scene.background.ColorBackground;
import org.anddev.andengine.entity.sprite.AnimatedSprite;
import org.anddev.andengine.entity.sprite.Sprite;
import org.anddev.andengine.entity.text.ChangeableText;
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
import com.amikomgamedev.ulartangga.entity.Entity_Camera;
import com.amikomgamedev.ulartangga.entity.Entity_Mc;

public class State_Gameplay extends 	BaseGameActivity
							implements 	IUpdateHandler,
										IScrollDetectorListener,
										IOnSceneTouchListener,
										Define
{
	
	private final int STATE_GAME_START		= 0;
	private final int STATE_GAME_LOADING	= 1;
	private final int STATE_GAME_INGAME		= 2;
	private final int STATE_GAME_PAUSE		= 3;
	private final int STATE_GAME_OVER		= 4;
	private final int STATE_GAME_RESTART	= 5;
	private final int STATE_GAME_UNLOADING	= 6;
	private int State_Game_Current			= STATE_GAME_START;
	
	private int Player_Max = 4;
	private int Player_Cur = Player_Max - 1;

	private int Map = -1;
	private float CurrentSecond = 0;
	public static float Second;
	private int randomValue;
	
	public static Camera camera;
	public static Scene scene;
	public static Entity_Mc[] mc;
	private SurfaceScrollDetector mScrollDetector;
	private Entity_Camera cam;
	
	public static AnimatedSprite spr_Img_Botton_Dice;
	private Sprite spr_Img_Button_Pause;
	private Rectangle rect_slot_machine;
	private static ChangeableText valueDice;
	private static ChangeableText playerName;
	public static ChangeableText[] curPosition;
	public static Text txtWin; 
	
	public static boolean moveCamera = true;
	public static boolean diceEnable = true;
	private boolean singlePlayer = false;
	private boolean moveAgain_1 = true;
	private boolean moveAgain_2 = true;
	private boolean moveAgain_3 = true;
	private boolean move = true;
	private boolean autoMoveNextPlayer = true;
	
	serverData sData = serverData.getInstance();
	private HUD hud;
	
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
		sData.setCharPlayer1(CARACTER_1);
		sData.setCharPlayer2(CARACTER_2);
		sData.setCharPlayer3(CARACTER_3);
		sData.setCharPlayer4(CARACTER_4);
		
		Map = sData.getSelectMap();

		Game.setGameplay(Map, Player_Max);
	}
	
	public Scene onLoadScene()
	{
		mEngine.registerUpdateHandler(new FPSLogger());
		mEngine.registerUpdateHandler(this);

		hud = new HUD();
		camera.setHUD(hud);
		
		scene = new Scene();
		scene.setOnSceneTouchListener(this);
 
		mScrollDetector = new SurfaceScrollDetector(this);
		
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
					Loading.updateLoading();
				else
					switchState(STATE_GAME_LOADING);
				
				break;
				
			case STATE_GAME_LOADING:
				
				if(Loading.isLoading())
					Loading.updateLoading();
				else
				{
					Game.spr_Img_Loading.registerEntityModifier(new FadeOutModifier(0.5f));
					if(timer(0.5f))
					{
						attachInGame();
						attachGamePause();
						attachGameOver();
						
						switchState(STATE_GAME_INGAME);
					}
				}
				
				break;
				
			case STATE_GAME_INGAME:
				
				cam.backCameraToMap();
				
				if(mc != null)
				{
					for(int i = 0; i < Player_Max; i++)
					{
						curPosition[i].setText("" +mc[i].Posisi_Mc_Current);
						mc[i].updateMove();
					}

					if(mc[Player_Cur].isMoving())
					{
						if(move)
						{
							mc[Player_Cur].cekMove();
							playerName.setText(PLAYER_NAME[Player_Cur] + " Is Moving");

							playerName.setPosition(
									Config.GAME_SCREEN_WIDTH - playerName.getWidth() - Utils.getRatioW(10),
									Utils.getRatioH(2));
						}
						else if(timer(1))
						{
							move = true;
							spr_Img_Botton_Dice.setVisible(false);
						}
						cam.autoMoveCamera(
								mc[Player_Cur],
								Define.GAME_MAP_CELL_WIDTH);
						
					}
					else
					{
						move = false;
						mc[Player_Cur].stop();
						moveCamera = true;
						
						valueDice.setText(""+Utils.getRandomValuie());
						playerName.setText(PLAYER_NAME[nextPlayer()] + " Move");
						
						playerName.setPosition(
								Config.GAME_SCREEN_WIDTH - playerName.getWidth() - Utils.getRatioW(10),
								Utils.getRatioH(2));
						
						if(!autoMoveNextPlayer)
						{
							spr_Img_Botton_Dice.setVisible(false);
							diceEnable = false;
						}
						else
						{
							cam.autoMoveCameraToNextPlayer(
									mc[nextPlayer()],
									(int) Define.GAME_MAP_CELL_WIDTH);
						}
						
						// cek 2 pemain 1 cell
						if(mc[Player_Cur].Posisi_Mc_Current != mc[Player_Cur].POSISI_MC_START)
						{
							for(int i = 0; i < Player_Max; i++)
							{
								if(mc[Player_Cur].Posisi_Mc_Current == mc[i].Posisi_Mc_Current && Player_Cur != i)
								{
									mc[i].throwToStart(mc[Player_Cur].getDistance());
//									Entity_Camera.autoMoveCamera(i);
									cam.autoMoveCamera(
											mc[i],
											Utils.getRatioW(((camera.getWidth() - mc[i].getAnimatedSprite().getWidth()) / 2)));
									playerName.setText(PLAYER_NAME[i] + " Back To Start");
									
									playerName.setPosition(
											Config.GAME_SCREEN_WIDTH - playerName.getWidth() - Utils.getRatioW(10),
											Utils.getRatioH(2));
									
									moveAgain_1 = false;
									spr_Img_Botton_Dice.setVisible(false);
									break;
								}
								else
								{
									moveAgain_1 = true;
								}
							}
						}
						
						// pengecekan tangga
						for(int i = 0; i < SNAKE_N_LADDER[Map][CELL_LADDER_START].length; i++)
						{
							if(mc[Player_Cur].Posisi_Mc_Current == SNAKE_N_LADDER[Map][CELL_LADDER_START][i])
							{
								mc[Player_Cur].moveSnakeOrLadder(SNAKE_N_LADDER[Map][CELL_LADDER_START][i], SNAKE_N_LADDER[Map][CELL_LADDER_END][i]);

//								Entity_Camera.autoMoveCamera(Player_Cur);
								cam.autoMoveCamera(
										mc[Player_Cur],
										Define.GAME_MAP_CELL_WIDTH);
								playerName.setText(PLAYER_NAME[Player_Cur] + " Get Ladder");
								
								playerName.setPosition(
										Config.GAME_SCREEN_WIDTH - playerName.getWidth() - Utils.getRatioW(10),
										Utils.getRatioH(2));
								moveAgain_2 = false;
								spr_Img_Botton_Dice.setVisible(false);
								break;
							}
							else
							{
								moveAgain_2 = true;
							}
						}
						
						// pengecekan ular
						for(int i = 0; i < SNAKE_N_LADDER[Map][CELL_SNAKE_START].length; i++)
						{
							if(mc[Player_Cur].Posisi_Mc_Current == SNAKE_N_LADDER[Map][CELL_SNAKE_START][i])
							{
								mc[Player_Cur].moveSnakeOrLadder(SNAKE_N_LADDER[Map][CELL_SNAKE_START][i], SNAKE_N_LADDER[Map][CELL_SNAKE_END][i]);

//								Entity_Camera.autoMoveCamera(Player_Cur);
								cam.autoMoveCamera(
										mc[Player_Cur],
										Define.GAME_MAP_CELL_WIDTH);
								playerName.setText(PLAYER_NAME[Player_Cur] + " Get Snake");
								
								playerName.setPosition(
										Config.GAME_SCREEN_WIDTH - playerName.getWidth() - Utils.getRatioW(10),
										Utils.getRatioH(2));
								moveAgain_3 = false;
								spr_Img_Botton_Dice.setVisible(false);
								break;
							}
							else
							{
								moveAgain_3 = true;
							}
						}
						
						//	acak otomatis (singgle player) 
						if(singlePlayer && Player_Cur != Player_Max - 1 && moveCamera)
						{
							if(timer(2))
							{
								switchPlayer();
								mc[Player_Cur].setMove(Utils.getRandomValuie());
							}
						}
						
						if(mc[Player_Cur].Posisi_Mc_Current == COLUMN_COUNT * ROW_COUNT) // finish
						{
							switchState(STATE_GAME_OVER);
						}
						// random ulang ketika dapat nilai 6
						else if(randomValue == 6 && moveAgain_1 && moveAgain_2 && moveAgain_3)
						{
							randomValue = 0;
							Player_Cur = previousPlayer();
						}
					}
				}
				break;
		}
	}
	
	public void reset(){}
	
	public boolean onSceneTouchEvent(Scene pScene, TouchEvent pSceneTouchEvent) 
	{
		switch (State_Game_Current)
		{
			case STATE_GAME_START:
				
				break;
			case STATE_GAME_LOADING:
				
				break;
			case STATE_GAME_INGAME:
				
				mScrollDetector.onTouchEvent(pSceneTouchEvent);
				
				if(pSceneTouchEvent.isActionDown())
					autoMoveNextPlayer = false;
				
				if(pSceneTouchEvent.isActionUp())
					autoMoveNextPlayer = true;
				
				if(Utils.isOnArea(
						pSceneTouchEvent,
						camera.getMinX() + spr_Img_Botton_Dice.getX()+ rect_slot_machine.getX(),
						camera.getMinX() + spr_Img_Botton_Dice.getX()+ rect_slot_machine.getX() + rect_slot_machine.getWidth(),
						camera.getMinY() + spr_Img_Botton_Dice.getY()+ rect_slot_machine.getY(),
						camera.getMinY() + spr_Img_Botton_Dice.getY()+ rect_slot_machine.getY() + rect_slot_machine.getHeight()))
				{
					if(moveCamera && diceEnable)
					{
						spr_Img_Botton_Dice.animate(
								new long[] {200, 200, 200}, 
								new int[] {0, 1, 0}, 
								0);
						
						switchPlayer();
						
						randomValue = Utils.getRandomValuie();
						
						mc[Player_Cur].setMove(randomValue);
						valueDice.setText(""+randomValue);
					}
				}
				
				if(Utils.isOnArea(
						pSceneTouchEvent,
						camera,
						spr_Img_Button_Pause))
				{
					switchState(STATE_GAME_PAUSE);
				}
				
				break;
	
			case STATE_GAME_PAUSE:
				
				if(Utils.isOnArea(
						pSceneTouchEvent,
						camera,
						Game.spr_GamePause_Bg,
						Game.spr_GamePause_Btn_Resume)
						&& pSceneTouchEvent.isActionUp())
				{
					switchState(STATE_GAME_INGAME);
				}
				
				if(Utils.isOnArea(
						pSceneTouchEvent,
						camera,
						Game.spr_GamePause_Bg,
						Game.spr_GamePause_Btn_MainMenu))
				{
					startActivity(new Intent(State_Gameplay.this, State_Menu_Main.class));
					finish();
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
					switchState(STATE_GAME_RESTART);
				}
				
				if(Utils.isOnArea(
						pSceneTouchEvent,
						camera,
						Game.spr_GameOver_Bg,
						Game.spr_GameOver_Btn_MainMenu))
				{
					startActivity(new Intent(State_Gameplay.this, State_Menu_Main.class));
					finish();
				}
				
				break;
			case STATE_GAME_RESTART:
				
				break;
			case STATE_GAME_UNLOADING:
				
				break;
		}
		
		return true;
	}
	
	public void onScroll(ScrollDetector pScollDetector, TouchEvent pTouchEvent,
			float pDistanceX, float pDistanceY)
	{
		if(moveCamera)
		{
			cam.moveCamera(-pDistanceX, -pDistanceY);
		}
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
				Game.spr_Img_Loading.registerEntityModifier(new FadeInModifier(0.5f));
				
				Loading.setLoading(Loading.LOADING_TYPE_GAMEPLAY, Map);
				
				break;
				
			case STATE_GAME_INGAME:
				
				autoMoveNextPlayer = true;
				
				spr_Img_Button_Pause.setVisible(true);
				
				Game.spr_GamePause_Bg.setVisible(false);
				Game.txtPause.setVisible(false);
				Game.spr_GamePause_Mc[0].setVisible(false);
				
				for(int i = 0; i < Player_Max; i++)
				{
					Game.spr_Icon_MC_Game_Pause[i].setVisible(false);
					Game.curPositionPause[i].setVisible(false);
				}
				
				Game.spr_GameOver_Bg.setVisible(false);
				txtWin.setVisible(false);
				Game.spr_GameOver_Btn_MainMenu.setVisible(false);
				Game.spr_GameOver_Btn_Restart.setVisible(false);
				
				for(int i = 0; i < Player_Max; i++) 
				{
					Game.spr_GameOver_Mc[i].setVisible(false);
				}
				
				break;
				
			case STATE_GAME_PAUSE:
				
				spr_Img_Button_Pause.setVisible(false);
				spr_Img_Botton_Dice.setVisible(false);
				
				mc[Player_Cur].stop();
				mc[Player_Cur].getAnimatedSprite().stopAnimation();

				Game.spr_GamePause_Bg.setVisible(true);
				Game.txtPause.setVisible(true);
				Game.spr_GamePause_Mc[0].setVisible(true);
				
				for(int i = 0; i < Player_Max; i++)
				{
					Game.curPositionPause[i].setText(curPosition[i].getText());
					Game.spr_Icon_MC_Game_Pause[i].setVisible(true);
					Game.curPositionPause[i].setVisible(true);
				}	
				break;
				
			case STATE_GAME_OVER:

				spr_Img_Button_Pause.setVisible(false);
				spr_Img_Botton_Dice.setVisible(false);

				mc[Player_Cur].updateMove();
				
				Game.spr_GameOver_Bg.setVisible(true);
				txtWin.setVisible(true);
				Game.spr_GameOver_Btn_MainMenu.setVisible(true);
				Game.spr_GameOver_Btn_Restart.setVisible(true);
				for(int i = 0; i < Player_Max; i++) 
				{
					Game.spr_GameOver_Mc[i].setVisible(true);
				}
				
				break;
				
			case STATE_GAME_RESTART:
				for (int i = 0; i < Player_Max; i++) {
					mc[i].restart();
				}
//				cam.restart();
				
				switchState(STATE_GAME_INGAME);
				
				break;
				
			case STATE_GAME_UNLOADING:
				
				break;
		}
	}

	private void switchPlayer()
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
				{
					switchState(STATE_GAME_PAUSE);
				}
				
				break;
	
			case STATE_GAME_PAUSE:
				
				if(keyCode == KeyEvent.KEYCODE_BACK)
				{
					switchState(STATE_GAME_INGAME);
				}
				
				break;
				
			case STATE_GAME_OVER:
				
				break;
			case STATE_GAME_RESTART:
				
				break;
			case STATE_GAME_UNLOADING:
				
				break;
		}
		
		return super.onKeyUp(keyCode, event);
	}
	
	
	private void attachInGame()
	{
		Game.bgm_Gameplay.play();
		
		scene.detachChildren();
		scene.attachChild(Game.spr_Img_Map);
		
		mc = new Entity_Mc[Player_Max];
		cam = new Entity_Camera(
				camera,
				Game.spr_Img_Map.getX(), 
				Game.spr_Img_Map.getX() + Game.spr_Img_Map.getWidth(),
				Game.spr_Img_Map.getY() - Game.spr_Img_Informasi_Header.getHeight(), 
				Game.spr_Img_Map.getY() + Game.spr_Img_Map.getHeight() + Game.spr_Img_Informasi_Footer.getHeight());
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
		
		spr_Img_Botton_Dice = new AnimatedSprite(0, 0, Game.reg_Img_Button_Dice);
		spr_Img_Button_Pause =  new Sprite(0, 0, 20, 20, Game.reg_Img_Button_Pause);
		rect_slot_machine = new Rectangle(0, 0, 35, 60);
		
		spr_Img_Botton_Dice.setPosition(
				(camera.getWidth() - spr_Img_Botton_Dice.getWidth()) / 2,
				(camera.getHeight() - spr_Img_Botton_Dice.getHeight()) / 2);
		
		rect_slot_machine.setVisible(false);
		
		spr_Img_Botton_Dice.attachChild(rect_slot_machine);
		rect_slot_machine.setPosition(
				spr_Img_Botton_Dice.getWidth() - 25,
				60);
		rect_slot_machine.setColor(0, 0, 0);
		spr_Img_Button_Pause.setPosition(
				camera.getWidth() - spr_Img_Button_Pause.getWidth() - 10,
				camera.getHeight() - spr_Img_Button_Pause.getHeight() - 10);
		
		hud.attachChild(Game.spr_Img_Informasi_Footer);
		hud.attachChild(Game.spr_Img_Informasi_Header);
		hud.attachChild(spr_Img_Botton_Dice);
		hud.attachChild(spr_Img_Button_Pause);
		
		
		float posX = Utils.getRatioW(40);
		curPosition = new ChangeableText[Player_Max];
		playerName = new ChangeableText(0, 0, Game.font[Data.FONT_SIZE_SMALL], "", 30);
		valueDice = new ChangeableText(0, 0, Game.font[Data.FONT_SIZE_BIG], "", 1);
		
		valueDice.setPosition(
				50,
				(spr_Img_Botton_Dice.getHeight() - valueDice.getHeight()) / 2);
		
		for(int i = 0; i < Player_Max; i++)
		{
			hud.attachChild(Game.spr_Icon_MC[i]);
			curPosition[i] = new ChangeableText(
					posX, Game.spr_Icon_MC[i].getY() + Utils.getRatioH(20),
					Game.font[Data.FONT_SIZE_SMALL], "0", 3);
			hud.attachChild(curPosition[i]);
			posX+=70;
		}
		
		spr_Img_Botton_Dice.attachChild(valueDice);
		hud.attachChild(playerName);
	}

	private void attachGamePause()
	{
		hud.attachChild(Game.spr_GamePause_Bg);
		Game.spr_GamePause_Bg.attachChild(Game.txtPause);
		Game.spr_GamePause_Bg.attachChild(Game.spr_GamePause_Mc[0]);
		Game.spr_GamePause_Bg.attachChild(Game.spr_GamePause_Btn_Resume);
		Game.spr_GamePause_Bg.attachChild(Game.spr_GamePause_Btn_MainMenu);
		
		for(int i = 0; i < Player_Max; i++)
		{
			Game.spr_GamePause_Bg.attachChild(Game.spr_Icon_MC_Game_Pause[i]);
			Game.spr_Icon_MC_Game_Pause[i].attachChild(Game.curPositionPause[i]);
		}
	}
	
	private void attachGameOver()
	{
		
		txtWin = new Text(
				0, 0,
				Game.font[Data.FONT_SIZE_MEDIUM],
				PLAYER_NAME[Player_Cur] +" Wins!");
		txtWin.setPosition(
				(Game.spr_GameOver_Bg.getWidth() - txtWin.getWidth()) / 2,
				Utils.getRatioH(10));
		
		float[] pX = new float[4];
		float[] pY = new float[4];
		
		int playerWin	= 0;
		int playerLose	= 1;
		
		if(Player_Max == 2)
		{
			pX[1] = (Game.spr_GameOver_Bg.getWidth() / 2 - Data.GAMEOVER_MC_LOSE_WIDTH) / 2;
			pY[1] = txtWin.getY() + txtWin.getHeight() + Utils.getRatioH(10);
		}
		else
		{
			pX[1] = (Game.spr_GameOver_Bg.getWidth() / 2 - Data.GAMEOVER_MC_LOSE_WIDTH * 2) / 3;
			pY[1] = txtWin.getY() + txtWin.getHeight() + Utils.getRatioH(10);
			
			pX[2] = pX[1] * 2 + Data.GAMEOVER_MC_LOSE_WIDTH;
			pY[2] = pY[1];
		
			pX[3] = (Game.spr_GameOver_Bg.getWidth() / 2 - Data.GAMEOVER_MC_LOSE_WIDTH) / 2;
			pY[3] = pY[1] + Data.GAMEOVER_MC_LOSE_HEIGHT + Utils.getRatioH(10);
		}
		hud.attachChild(Game.spr_GameOver_Bg);
		
		for(int i = 0; i < Player_Max; i++) 
		{
			if(i == Player_Cur)
			{	
				Game.spr_GameOver_Mc[i].setPosition(
						(Game.spr_GameOver_Bg.getWidth() / 2 - Game.spr_GameOver_Mc[i].getWidth()) / 2,
						Game.spr_GameOver_Bg.getBaseHeight() - Game.spr_GameOver_Mc[i].getHeight() - Utils.getRatioH(10));
				Game.spr_GameOver_Mc[i].animate(
						Data.GAMEOVER_MC_ANIM_SPEED[playerWin],
						Game.GAMEOVER_MC_ANIM_FRAME[playerWin][Game.ANI_FRAME_START],
						Game.GAMEOVER_MC_ANIM_FRAME[playerWin][Game.ANI_FRAME_END],
						true);
			}
			else
			{
				Game.spr_GameOver_Mc[i].setWidth(Data.GAMEOVER_MC_LOSE_WIDTH);
				Game.spr_GameOver_Mc[i].setHeight(Data.GAMEOVER_MC_LOSE_HEIGHT);
				Game.spr_GameOver_Mc[i].setPosition(
						Game.spr_GameOver_Bg.getWidth() / 2 + pX[playerLose],
						pY[playerLose]);
				Game.spr_GameOver_Mc[i].animate(
						Data.GAMEOVER_MC_ANIM_SPEED[1],
						Game.GAMEOVER_MC_ANIM_FRAME[1][Game.ANI_FRAME_START],
						Game.GAMEOVER_MC_ANIM_FRAME[1][Game.ANI_FRAME_END],
						true);
				
				playerLose++;
			}
			Game.spr_GameOver_Bg.attachChild(Game.spr_GameOver_Mc[i]);
		}

		Game.spr_GameOver_Bg.attachChild(txtWin);
		Game.spr_GameOver_Bg.attachChild(Game.spr_GameOver_Btn_MainMenu);
		Game.spr_GameOver_Bg.attachChild(Game.spr_GameOver_Btn_Restart);
		
		playerName.setText(PLAYER_NAME[Player_Cur] + " Wins!");
		playerName.setPosition(
				Config.GAME_SCREEN_WIDTH - playerName.getWidth() - Utils.getRatioW(10),
				Utils.getRatioH(2));
	}
}
