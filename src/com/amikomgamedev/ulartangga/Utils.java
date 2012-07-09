package com.amikomgamedev.ulartangga;

import java.util.Random;

import org.anddev.andengine.entity.sprite.AnimatedSprite;

public class Utils
{
	private static float random;
	
	public static void setRandomValue()
	{
		float range = 6;
		random = range * new Random().nextFloat();
	}
	
	public static int getRandomValuie()
	{
		return (int) (1 + random);
	}

	public static float getCellCenterX(AnimatedSprite sprite)
	{
		return (Define.GAME_MAP_CELL_WIDTH - sprite.getWidth()) / 2;
	}
	
	public static float getCellCenterY(AnimatedSprite sprite)
	{
		return (Define.GAME_MAP_CELL_HEIGHT - sprite.getHeight()) / 2;
	}

	public static int getRatioW(int def)
	{
		return (int) ((def/Define.GAME_RATIO_SCREEN_WIDTH) 
				* Config.GAME_SCREEN_WIDTH);
	}

	public static int getRatioH(int def)
	{
		return (int) ((def/Define.GAME_RATIO_SCREEN_HEIGHT) 
				* Config.GAME_SCREEN_HEIGHT);
	}
}
