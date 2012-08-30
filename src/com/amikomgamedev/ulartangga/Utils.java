package com.amikomgamedev.ulartangga;

import java.util.Random;

import org.anddev.andengine.engine.camera.Camera;
import org.anddev.andengine.entity.sprite.AnimatedSprite;
import org.anddev.andengine.entity.sprite.Sprite;
import org.anddev.andengine.input.touch.TouchEvent;
import org.anddev.andengine.opengl.texture.TextureOptions;

public class Utils implements Define
{
	public static int getRandomValuie()
	{
		return getRandomValuie(1, 6);
	}

	public static int getRandomValuie(float min, float max)
	{
		return (int) (min + (max - 1 + min) * new Random().nextFloat());
	}

	public static float getCellCenterX(AnimatedSprite sprite)
	{
		return (Define.GAME_MAP_CELL_WIDTH - sprite.getWidth()) / 2;
	}
	
	public static float getCellCenterY(AnimatedSprite sprite)
	{
		return (Define.GAME_MAP_CELL_HEIGHT - sprite.getHeight()) / 2;
	}

	// ratio posisi untuk hud
	public static int getRatioW(float def)
	{
		return (int) ((def/Define.GAME_RATIO_SCREEN_WIDTH) 
				* Config.GAME_SCREEN_WIDTH);
	}
	// ratio posisi untuk hud
	public static int getRatioH(float def)
	{
		return (int) ((def/Define.GAME_RATIO_SCREEN_HEIGHT) 
				* Config.GAME_SCREEN_HEIGHT);
	}

	public static boolean isOnArea(
			TouchEvent event,
			Sprite sprite)
	{
		float minX = sprite.getX();
		float maxX = sprite.getX() + sprite.getWidth();
		float minY = sprite.getY();
		float maxY = sprite.getY() + sprite.getHeight();
		
		return isOnArea(event, minX, maxX, minY, maxY);
	}

	public static boolean isOnArea(
			TouchEvent event,
			Camera camera,
			Sprite spriteParrent,
			Sprite sprite)
	{
		float minX = camera.getMinX() + spriteParrent.getX() + sprite.getX();
		float maxX = camera.getMinX() + spriteParrent.getX() + sprite.getX() + sprite.getWidth();
		float minY = camera.getMinY() + spriteParrent.getY() + sprite.getY();
		float maxY = camera.getMinY() + spriteParrent.getY() + sprite.getY() + sprite.getHeight();
		
		return isOnArea(event, minX, maxX, minY, maxY);
	}

	public static boolean isOnArea(
			TouchEvent event,
			Sprite spriteParrent,
			Sprite sprite)
	{
		float minX = spriteParrent.getX() + sprite.getX();
		float maxX = spriteParrent.getX() + sprite.getX() + sprite.getWidth();
		float minY = spriteParrent.getY() + sprite.getY();
		float maxY = spriteParrent.getY() + sprite.getY() + sprite.getHeight();
		
		return isOnArea(event, minX, maxX, minY, maxY);
	}

	public static boolean isOnArea(
			TouchEvent event,
			Sprite spriteParrent,
			AnimatedSprite sprite)
	{
		float minX = spriteParrent.getX() + sprite.getX();
		float maxX = spriteParrent.getX() + sprite.getX() + sprite.getWidth();
		float minY = spriteParrent.getY() + sprite.getY();
		float maxY = spriteParrent.getY() + sprite.getY() + sprite.getHeight();
		
		return isOnArea(event, minX, maxX, minY, maxY);
	}

	public static boolean isOnArea(
			TouchEvent event,
			Camera camera,
			Sprite sprite)
	{
		float minX = camera.getMinX() + sprite.getX();
		float maxX = camera.getMinX() + sprite.getX() + sprite.getWidth();
		float minY = camera.getMinY() + sprite.getY();
		float maxY = camera.getMinY() + sprite.getY() + sprite.getHeight();
		
		return isOnArea(event, minX, maxX, minY, maxY);
	}
	
	public static boolean isOnArea(
			TouchEvent event,
			float minX,
			float maxX,
			float minY,
			float maxY)
	{
		boolean bol = false;

		float posX = event.getX();
		float posY = event.getY();
		
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
	
	// ratio posisi untuk scene n ukuran aset
	public static int getRatio(float nil)
	{
		int nil_Ratio = 0;
		
		switch(Config.RATIO_USE)
		{
			case Config.RATIO_WIDTH:
				nil_Ratio = (int) (nil / GAME_RATIO_SCREEN_WIDTH 
						* Config.GAME_SCREEN_WIDTH);
				break;
			case Config.RATIO_HEIGHT:
				nil_Ratio = (int) (nil / GAME_RATIO_SCREEN_HEIGHT 
						* Config.GAME_SCREEN_HEIGHT);
				break;
		}
		
		return nil_Ratio;
	}
}
