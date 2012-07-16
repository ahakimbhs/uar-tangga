package com.amikomgamedev.ulartangga;

import java.util.Random;

import org.anddev.andengine.entity.sprite.AnimatedSprite;
import org.anddev.andengine.entity.sprite.Sprite;
import org.anddev.andengine.input.touch.TouchEvent;
import org.anddev.andengine.opengl.texture.TextureOptions;

public class Utils
{
	public static int getRandomValuie()
	{
		return (int) (1 + 6 * new Random().nextFloat());
	}

	public static float getCellCenterX(AnimatedSprite sprite)
	{
		return (Define.GAME_MAP_CELL_WIDTH - sprite.getWidth()) / 2;
	}
	
	public static float getCellCenterY(AnimatedSprite sprite)
	{
		return (Define.GAME_MAP_CELL_HEIGHT - sprite.getHeight()) / 2;
	}

	public static float getRatioW(float def)
	{
		return ((def/Define.GAME_RATIO_SCREEN_WIDTH) 
				* Config.GAME_SCREEN_WIDTH);
	}

	public static float getRatioH(float def)
	{
		return ((def/Define.GAME_RATIO_SCREEN_HEIGHT) 
				* Config.GAME_SCREEN_HEIGHT);
	}
	
	public static boolean isOnArea(
			TouchEvent event,
			Sprite sprite)
	{
		boolean bol = false;

		float posX = event.getX();
		float minX = sprite.getX();
		float maxX = sprite.getX() + sprite.getWidth();
		float posY = event.getY();
		float minY = sprite.getY();
		float maxY = sprite.getY() + sprite.getHeight();
		
		if(posX > minX && posX < maxX
				&& posY > minY && posY < maxY)
			bol = true;
		
		return bol;
	}

	public static void TRACE(String arg)
	{
//		if(Game_Config.DEBUG)
		{
			System.out.println(arg);
		}
	}
	
	public static TextureOptions getTextureOption()
	{
		return Config.USE_AA 
				? TextureOptions.REPEATING_BILINEAR
				: Config.GAME_RES_USE == Config.GAME_RES_MINI
						? TextureOptions.DEFAULT
						:TextureOptions.REPEATING_NEAREST;
	}
}
