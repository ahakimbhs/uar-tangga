package com.amikomgamedev.ulartangga.entity;

import org.anddev.andengine.engine.handler.physics.PhysicsHandler;
import org.anddev.andengine.entity.sprite.Sprite;
import org.anddev.andengine.util.Debug;

import android.util.Log;

import com.amikomgamedev.ulartangga.Config;
import com.amikomgamedev.ulartangga.Define;
import com.amikomgamedev.ulartangga.Game;
import com.amikomgamedev.ulartangga.Utils;
import com.amikomgamedev.ulartangga.gamelevel.Game_Level_Handler;
import com.amikomgamedev.ulartangga.states.State_Gameplay;

public class Entity_Mc implements Define
{
	private Sprite spr_Mc;
	private PhysicsHandler handler;

	public int POSISI_MC_START = 1;
	public int Posisi_Mc_Current;
	private int Posisi_Mc_Current_Row;
	private int Posisi_Mc_Max;
	private float second = 0;
	
	private float velocityX;
	private float gravitasi;
	private float velocity;
	private float velocityY;
	
	public Entity_Mc(Sprite spr)
	{
		spr_Mc = spr;
		handler = new PhysicsHandler(spr_Mc);
		spr_Mc.registerUpdateHandler(handler);

		Posisi_Mc_Current			= POSISI_MC_START;
		Posisi_Mc_Current_Row	= 1;
		Posisi_Mc_Max					= 0;
	}
	
	public void setMove(int valueDice)
	{
		Posisi_Mc_Max = Posisi_Mc_Current + valueDice;
	}
	
	public void updateMove()
	{	
		if(Posisi_Mc_Current % COLUMN_COUNT != 0) // move left or right
		{
			if(Posisi_Mc_Current_Row % 2 == 1)  // move right
			{
				if(spr_Mc.getX() > 
				Posisi_Mc_Current % COLUMN_COUNT  * GAME_MAP_CELL_WIDTH	+ Utils.getCellCenterX(spr_Mc))
				{
					Posisi_Mc_Current++;
				}
				handler.setVelocity(SPEED, 0);
			}
			else // move left
			{
				if(spr_Mc.getX() + spr_Mc.getWidth() < 
				(COLUMN_COUNT - Posisi_Mc_Current % COLUMN_COUNT) * GAME_MAP_CELL_WIDTH)
//				- Utils.getCellCenterX(spr_Mc))
				{
					Posisi_Mc_Current++;
				}
				handler.setVelocity(-SPEED, 0);
			}
		} 
		else if(Posisi_Mc_Current % COLUMN_COUNT == 0) // move up or finish
		{
			if(Posisi_Mc_Current_Row == ROW_COUNT) // move finish
			{
				stop();
			}
			else //move up
			{
				if(spr_Mc.getY() + spr_Mc.getHeight() < 
						Config.GAME_SCREEN_HEIGHT - Posisi_Mc_Current_Row * 
						GAME_MAP_CELL_HEIGHT - Game.reg_Img_Informasi_Footer.getHeight()
						- Utils.getCellCenterY(spr_Mc))
				{
					Posisi_Mc_Current++;
					Posisi_Mc_Current_Row++;
				}
				handler.setVelocity(0, -SPEED);
			}
		}
	}
	
	public boolean isMoving()
	{
		return Posisi_Mc_Max > Posisi_Mc_Current ? true : false;
	}
	
	public void stop() 
	{
		handler.setVelocity(0, 0);
	}
	
	public float getDistance()
	{
		return spr_Mc.getX();
	}
	
	public void throwToStart(float distance)
	{
//		rumus parabola (fisika) dengan sedikit modifikasi pada gravitasi
//		masih belum fix pada gravitasi
		second = second+State_Gameplay.Second;;
		int time			= 2;
		velocityX	= distance / time;
		gravitasi	= velocityX;
		velocity		= (float) (velocityX / Math.cos(Math.toRadians(45)));
		velocityY	= (float) (velocity * Math.sin(Math.toRadians(45))) - (gravitasi * second);
		handler.setVelocity(-velocityX, -velocityY);

		if(second >= time)
		{
			float mcPosX	= 0;
			float mcPosY	= Config.GAME_SCREEN_HEIGHT - GAME_MAP_CELL_HEIGHT + 
					((GAME_MAP_CELL_HEIGHT - spr_Mc.getHeight()) / 2) - Game.reg_Img_Informasi_Footer.getHeight();
			spr_Mc.setPosition(mcPosX, mcPosY);
			stop();
			Posisi_Mc_Current			= POSISI_MC_START;
			Posisi_Mc_Current_Row	= 1;
			second									= 0;
		}
	}
	
	public void moveLadder(int posisiStart, int posisiEnd)
	{
//				/	|
//		sm/		| sd 
//		/____ |
//			sp
		String string = String.valueOf(posisiEnd - posisiStart);
		float sisiDepan 		= Integer.parseInt(string.substring(0, 1)) * GAME_MAP_CELL_WIDTH;;
		float sisiPanjang 	= (posisiEnd - posisiStart) % COLUMN_COUNT * GAME_MAP_CELL_WIDTH;
		float sisiMiring 	= (int) Math.sqrt(sisiDepan * sisiDepan + sisiPanjang * sisiPanjang);
		
		Debug.d("sd = " +sisiDepan+ ", sp = " +sisiPanjang+ ", sm = " +sisiMiring);
		
		second = second+State_Gameplay.Second;
		int time				= 2;
		velocityX	= sisiPanjang / time;
		if(velocityX == 0) // tangga lurus
		{
			velocityY	= sisiDepan / time;
		}
		else // tangga miring
		{
			velocity		= velocityX / (sisiPanjang / sisiMiring);
			velocityY	= velocity * (sisiDepan / sisiMiring);
		}
		handler.setVelocity(velocityX, -velocityY);
		
		Debug.d("vx = "+velocityX+ ", vy = " +velocityY);
		Debug.d("pos x = "+spr_Mc.getX()+ ", pos y = " +spr_Mc.getY());
//		Debug.d("second = " +second);
		
		if(second >= time)
		{
			stop();
			string = String.valueOf(posisiEnd);
			Posisi_Mc_Current_Row	=Integer.parseInt(string.substring(0, 1)) + 1;
			Posisi_Mc_Current	= posisiEnd;
			second = 0;
		}
	}
}
