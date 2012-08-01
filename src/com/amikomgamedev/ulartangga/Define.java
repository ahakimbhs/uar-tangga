package com.amikomgamedev.ulartangga;

public interface Define 
{
	public static final int SPEED_MOVE 				= 70;
	public static final int SPEED_TO_START 			= 100;
	public static final int SPEED_SNAKE_N_LADDER 	= 100;

	public static final int ROW_COUNT		= 10;
	public static final int COLUMN_COUNT	= 10;

	public static final int CAMERA_CENTER_X = Config.GAME_SCREEN_WIDTH >> 1;
	public static final int CAMERA_CENTER_Y = Config.GAME_SCREEN_HEIGHT >> 1;

	public static final int CAMERA_CENTER_MAP_X1 = CAMERA_CENTER_X;
	public static final int CAMERA_CENTER_MAP_X2 = (int) (Game.spr_Img_Map.getWidth() - CAMERA_CENTER_X);
	public static final int CAMERA_CENTER_MAP_Y1 = (int) (CAMERA_CENTER_Y - Game.reg_Img_Informasi_Footer.getHeight());
	public static final int CAMERA_CENTER_MAP_Y2 = (int) (Game.spr_Img_Map.getHeight() - CAMERA_CENTER_Y);
	
	public static final int GAME_MAP_CELL_WIDTH		= (int) (Game.spr_Img_Map.getWidth() / COLUMN_COUNT);
	public static final int GAME_MAP_CELL_HEIGHT	= (int) (Game.spr_Img_Map.getHeight() / ROW_COUNT);

	public static final int CARACTER_1	= 0;
	public static final int CARACTER_2	= 1;
	public static final int CARACTER_3	= 2;
	public static final int CARACTER_4	= 3;
	
	public static final int PLAYER_1	= 0;
	public static final int PLAYER_2	= 1;
	public static final int PLAYER_3	= 2;
	public static final int PLAYER_4	= 3;
	
	public static final int TYPE_PLAYER	= 0;
	public static final int TYPE_AI		= 1;

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

	public static float GAME_RATIO_SCREEN_WIDTH = 320;
	public static float GAME_RATIO_SCREEN_HEIGHT = 480;
	
	public static final int CELL_LADDER_START	= 0;
	public static final int CELL_LADDER_END		= 1;
	public static final int CELL_SNAKE_START	= 2;
	public static final int CELL_SNAKE_END		= 3;
	
	public static final int[][][] SNAKE_N_LADDER =
		{
			{// modern
				{2, 5,	8,	11,	21,	38,	50,	55,	60},		// posisi Tangga Awal
				{24,48,	28,	52,	41,	65,	71,	93, 78},		// posisi Tangga Akhir
				{33,54,	58,	63,	76,	84,	89,	95,	98},		// posisi Ular Awal
				{7,	17,	25,	35,	22,	45,	9,	68,	80}			// posisi Ular Ahir
			},
			{// klasik
				{9,	38,	58},		// posisi Tangga Awal
				{52,78,	78},		// posisi Tangga Akhir
				{41,99,	84,	16,	86,	70},		// posisi Ular Awal
				{20,19,	24,	5,	66, 10}			// posisi Ular Ahir
			},
			{// galaksi
				{2, 5,	8,	11,	21,	38,	50,	55,	60},		// posisi Tangga Awal
				{24,48,	28,	52,	41,	65,	71,	93, 78},		// posisi Tangga Akhir
				{33,54,	58,	63,	76,	84,	89,	95,	98},		// posisi Ular Awal
				{7,	17,	25,	35,	22,	45,	9,	68,	80}			// posisi Ular Ahir
			},
			{// kolosal
				{2, 5,	8,	11,	21,	38,	50,	55,	60},		// posisi Tangga Awal
				{24,48,	28,	52,	41,	65,	71,	93, 78},		// posisi Tangga Akhir
				{33,54,	58,	63,	76,	84,	89,	95,	98},		// posisi Ular Awal
				{7,	17,	25,	35,	22,	45,	9,	68,	80}			// posisi Ular Ahir
			}
		};

	public static final int STATE_IDLE			= 0;
	public static final int STATE_MOVE_RIGHT	= 1;
	public static final int STATE_MOVE_UP		= 2;
	public static final int STATE_MOVE_LEFT		= 3;
	public static final int STATE_MOVE_BACK		= 4;

	public final int SCENE_GAME_LOADING		= 0;
	public final int SCENE_GAME_INGAME		= 1;
	public final int SCENE_GAME_PAUSE		= 2;
	public final int SCENE_GAME_OVER		= 3;
	public final int SCENE_GAME_COUNT		= 4;
}
