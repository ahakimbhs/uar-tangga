package com.amikomgamedev.ulartangga;

import com.amikomgamedev.ulartangga.states.State_Gameplay;


public interface Define 
{
	public static final int SPEED_MOVE 				= 70;
	public static final int SPEED_TO_START 			= 100;
	public static final int SPEED_SNAKE_N_LADDER 	= 100;

	public static final int ROW_COUNT		= 10;
	public static final int COLUMN_COUNT	= 10;

	public static final int CAMERA_CENTER_X = Config.GAME_SCREEN_WIDTH / 2;
	public static final int CAMERA_CENTER_Y = Config.GAME_SCREEN_HEIGHT / 2;

	public static final int CAMERA_CENTER_MAP_X1 = CAMERA_CENTER_X;
	public static final int CAMERA_CENTER_MAP_X2 = (int) (Game.spr_Img_Map.getWidth() - CAMERA_CENTER_X);
	public static final int CAMERA_CENTER_MAP_Y1 = (int) (CAMERA_CENTER_Y + Config.GAME_SCREEN_HEIGHT - Game.spr_Img_Map.getHeight()
					- Game.reg_Img_Informasi_Header.getHeight() - Game.reg_Img_Informasi_Footer.getHeight());
	public static final int CAMERA_CENTER_MAP_Y2 = CAMERA_CENTER_Y;
	
	public static final int GAME_MAP_CELL_WIDTH		= (int) (Game.spr_Img_Map.getWidth() / COLUMN_COUNT);
	public static final int GAME_MAP_CELL_HEIGHT	= (int) (Game.spr_Img_Map.getHeight() / ROW_COUNT);
	
//	public static final float POS_MC_START_X = 0;//Utils.getCellCenterX(Game.spr_MC[State_Gameplay.Player_Cur]);
//	public static final float POS_MC_START_Y = Config.GAME_SCREEN_HEIGHT - GAME_MAP_CELL_HEIGHT + 
//			((GAME_MAP_CELL_HEIGHT - Game.spr_MC[0].getHeight()) / 2) - Game.reg_Img_Informasi_Footer.getHeight();

	public static final int CARACTER_1	= 0;
	public static final int CARACTER_2	= 1;
	public static final int CARACTER_3	= 2;
	public static final int CARACTER_4	= 3;
	
	public static final int PLAYER_1	= 0;
	public static final int PLAYER_2	= 1;
	public static final int PLAYER_3	= 2;
	public static final int PLAYER_4	= 3;

	public static final int MAP_MODERN	= 0;
	public static final int MAP_KLASIK	= 1;
	public static final int MAP_GALAKSI	= 2;
	public static final int MAP_KOLOSAL	= 3;
	
	public static String[] PLAYER_NAME = 
		{
			"Player One",
			"Player Two",
			"Player Three",
			"Player Four"
		};
}
