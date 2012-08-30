package com.amikomgamedev.ulartangga;

public interface Define 
{
	public static final float SPEED_MOVE 				= Utils.getRatio(70);
	public static final float SPEED_TO_START 			= 100;
	public static final float SPEED_SNAKE_N_LADDER 	= 100;

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

	public static final int MAP_KLASIK	= 0;
	public static final int MAP_MODERN 	= 1;
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
			{// klasik
				{5, 11,	20,	49,	52,	64,	81},		// posisi Tangga Awal
				{27,55,	41,	70,	96,	97,	99},		// posisi Tangga Akhir
				{19,36,	58,	60,	74,	92,	98},		// posisi Ular Awal
				{2,	17,	18,	42,	44,	71,	32}			// posisi Ular Ahir
			},
			{// modern
				{9,	28,	36,	38,	69,	85},		// posisi Tangga Awal
				{27,52,	90,	60,	88,	96},		// posisi Tangga Akhir
				{16,41,	70,	65,	86,	84, 99},		// posisi Ular Awal
				{5,	20,	10,	55,	66, 24, 19}			// posisi Ular Ahir
			},
			{// galaksi
				{2, 7,	13,	26,	42,	50,	74},		// posisi Tangga Awal
				{45,34,	32,	66,	78,	98,	96},		// posisi Tangga Akhir
				{25,41,	61,	75,	88,	92,	99},		// posisi Ular Awal
				{5,	22,	39,	31,	53,	89,	65}			// posisi Ular Ahir
			},
			{// kolosal
				{4, 8,	26,	38,	50,	53,	56},		// posisi Tangga Awal
				{44,27,	52,	81,	90,	72,	96},		// posisi Tangga Akhir
				{35,60,	69,	74,	85,	92,	94},		// posisi Ular Awal
				{6,	19,	12,	47,	42,	58,	88}			// posisi Ular Ahir
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

	public final int MC_WIN		= 0;
	public final int MC_LOSE	= 1;
}
