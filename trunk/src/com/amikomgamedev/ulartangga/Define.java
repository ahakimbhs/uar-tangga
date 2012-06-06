package com.amikomgamedev.ulartangga;


public interface Define 
{
	public static final int SPEED = 70;

	public static final int CAMERA_CENTER_X = Config.GAME_SCREEN_WIDTH / 2;
	public static final int CAMERA_CENTER_Y = Config.GAME_SCREEN_HEIGHT / 2;

	public static final int CAMERA_CENTER_MAP_X1 = CAMERA_CENTER_X;
	public static final int CAMERA_CENTER_MAP_X2 = Game.reg_Img_Map.getWidth() - CAMERA_CENTER_X;
	public static final int CAMERA_CENTER_MAP_Y1 = 
			CAMERA_CENTER_Y + Config.GAME_SCREEN_HEIGHT - Game.reg_Img_Map.getHeight()
			- Game.reg_Img_Informasi_Header.getHeight() - Game.reg_Img_Informasi_Footer.getHeight();
	public static final int CAMERA_CENTER_MAP_Y2 = CAMERA_CENTER_Y;

	public static int GAME_MAP_CELL_WIDTH	= Game.reg_Img_Map.getWidth() / 10;
	public static int GAME_MAP_CELL_HEIGHT	= Game.reg_Img_Map.getHeight() / 10;
	
	public static final float POS_MC_START_X = 0;//(-GAME_MAP_CELL_WIDTH);
	public static final float POS_MC_START_Y = Config.GAME_SCREEN_HEIGHT - GAME_MAP_CELL_HEIGHT + 
			((GAME_MAP_CELL_HEIGHT - Game.reg_MC[0].getHeight()) / 2) - Game.reg_Img_Informasi_Footer.getHeight();

	public static final int ROW_NEW	= 0;
	public static final int ROW_END	= 1;
	
	public static final int ROW[][] = 
		{
			{1, 11, 21, 31, 41, 51, 61, 71, 81, 91},
			{10, 20, 30, 40, 50, 60, 70, 80, 90, 100}
		};
	
}