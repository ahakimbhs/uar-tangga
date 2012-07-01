package com.amikomgamedev.ulartangga.entity;

import org.anddev.andengine.engine.handler.physics.PhysicsHandler;
import org.anddev.andengine.entity.sprite.AnimatedSprite;
import org.anddev.andengine.util.Debug;

import com.amikomgamedev.ulartangga.Config;
import com.amikomgamedev.ulartangga.Define;
import com.amikomgamedev.ulartangga.Game;
import com.amikomgamedev.ulartangga.Utils;
import com.amikomgamedev.ulartangga.states.State_Gameplay;

public class Entity_Mc implements Define
{
	private AnimatedSprite spr_Mc;
	private PhysicsHandler handler;

	public int 	POSISI_MC_START = 1;
	public int 	Posisi_Mc_Current;
	private int Posisi_Mc_Current_Row;
	private int Posisi_Mc_Max;
	
	private float 	second = 0;
	
	private float gravitasi;
	private float velocity;
	private float velocityX;
	private float velocityY;

	private int selisihBaris 	= 0;
	private int selisihKolom	= 0;
	private float sisiDepan		= 0;
	private float sisiPanjang	= 0;
	private float sisiMiring 	= 0;
	
	public Entity_Mc(AnimatedSprite spr)
	{
		spr_Mc 	= spr;
		handler = new PhysicsHandler(spr_Mc);
		spr_Mc.registerUpdateHandler(handler);

		Posisi_Mc_Current		= POSISI_MC_START;
		Posisi_Mc_Current_Row	= 1;
		Posisi_Mc_Max			= 0;
	}
	
	public void setMove(int valueDice)
	{
		Posisi_Mc_Max = Posisi_Mc_Current + valueDice;
	}
	
	public void updateMove()
	{
		if(Posisi_Mc_Current_Row > ROW_COUNT)						// move right after finish
		{
			if(Posisi_Mc_Current % COLUMN_COUNT == 0)
				Posisi_Mc_Current--;
			if(spr_Mc.getX() > 
			(COLUMN_COUNT + 1 - Posisi_Mc_Current % COLUMN_COUNT) * GAME_MAP_CELL_WIDTH + Utils.getCellCenterX(spr_Mc))
			{
				Posisi_Mc_Current--;
				Debug.d("ok2");
			}
			handler.setVelocity(SPEED_MOVE, 0);
			Debug.d("ok");
		}
		else if(Posisi_Mc_Current % COLUMN_COUNT != 0) 				// move left or right
		{
			if(Posisi_Mc_Current_Row % 2 == 1)  					// move right
			{
				if(spr_Mc.getX() > 
				Posisi_Mc_Current % COLUMN_COUNT  * GAME_MAP_CELL_WIDTH	+ Utils.getCellCenterX(spr_Mc))
				{
					Posisi_Mc_Current++;
				}
				handler.setVelocity(SPEED_MOVE, 0);
			}
			else 													// move left
			{
				if(spr_Mc.getX() + spr_Mc.getWidth() < 
				(COLUMN_COUNT - Posisi_Mc_Current % COLUMN_COUNT) * GAME_MAP_CELL_WIDTH)
//				- Utils.getCellCenterX(spr_Mc))
				{
					Posisi_Mc_Current++;
				}
				handler.setVelocity(-SPEED_MOVE, 0);
			}
		} 
		else if(Posisi_Mc_Current % COLUMN_COUNT == 0) 				// move up or back
		{
			if(Posisi_Mc_Current_Row == ROW_COUNT) 					// back
			{
				Posisi_Mc_Current_Row++;
				Posisi_Mc_Max = ROW_COUNT * COLUMN_COUNT - Posisi_Mc_Max % ROW_COUNT;
				Debug.d("max " +Posisi_Mc_Max);
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
				handler.setVelocity(0, -SPEED_MOVE);
			}
		}
	}
	
	public boolean isMoving()
	{
		if(Posisi_Mc_Current_Row > ROW_COUNT)
			return Posisi_Mc_Max < Posisi_Mc_Current ? true : false;
		else
			return Posisi_Mc_Max > Posisi_Mc_Current ? true : false;
	}
	
	public void stop() 
	{
		handler.setVelocity(0, 0);
		if(Posisi_Mc_Current_Row > ROW_COUNT)
		{
			Posisi_Mc_Current_Row--;
		}
	}
	
	public PhysicsHandler getHandler()
	{
		return handler;
	}
	
	public float getDistance()
	{
		return spr_Mc.getX();
	}
	
	public void throwToStart(float distance)
	{
//		rumus parabola (fisika)
		second = second+State_Gameplay.Second;;
		int time	= 2;
		velocityX	= distance / time;
		gravitasi	= velocityX;
		velocity	= (float) (velocityX / Math.cos(Math.toRadians(45)));
		velocityY	= (float) (velocity * Math.sin(Math.toRadians(45))) - (gravitasi * second);
		handler.setVelocity(-velocityX, -velocityY);

		if(second >= time)
		{
			float mcPosX	= Utils.getCellCenterX(spr_Mc);
			float mcPosY	= Config.GAME_SCREEN_HEIGHT - GAME_MAP_CELL_HEIGHT + 
					((GAME_MAP_CELL_HEIGHT - spr_Mc.getHeight()) / 2) - Game.reg_Img_Informasi_Footer.getHeight();
			spr_Mc.setPosition(mcPosX, mcPosY);
			stop();
			Posisi_Mc_Current		= POSISI_MC_START;
			Posisi_Mc_Current_Row	= 1;
			second					= 0;
		}
	}

	public void moveSnakeOrLadder(int posisiTanggaAwal, int posisiTanggaAkhir)
	{
//			/|
//		sm / | sd 
//		  /__|
//		   sp
		
		if(posisiTanggaAwal % COLUMN_COUNT != 0) 											// bukan ujung dari tiap baris untuk posisi cell tanggal awal
		{
			if(posisiTanggaAkhir % COLUMN_COUNT != 0) 										// bukan ujung dari tiap baris untuk posisi cell tanggal akhir
			{
				selisihBaris = posisiTanggaAkhir / COLUMN_COUNT - posisiTanggaAwal / COLUMN_COUNT;
				if((posisiTanggaAwal / COLUMN_COUNT) % 2 == 0) 								// arah jalan kanan
				{
					if(selisihBaris % 2 == 0) 												// selisih baris genap
					{
						selisihKolom = posisiTanggaAkhir  % COLUMN_COUNT - posisiTanggaAwal  % COLUMN_COUNT;
					}
					else 																	// selisih baris ganjil
					{
						selisihKolom = (COLUMN_COUNT + 1 - posisiTanggaAkhir  % COLUMN_COUNT) - posisiTanggaAwal  % COLUMN_COUNT;
					}
				}
				else 																		// arah jalan kiri ( <== )
				{
					if(selisihBaris % 2 == 0) 												// selisih baris genap
					{
						selisihKolom = posisiTanggaAwal  % COLUMN_COUNT - posisiTanggaAkhir  % COLUMN_COUNT;
					}
					else 																	// selisih baris ganjil
					{
						selisihKolom = posisiTanggaAwal  % COLUMN_COUNT - (COLUMN_COUNT + 1 - posisiTanggaAkhir  % COLUMN_COUNT);
					}
				}
			}
			else 																			// ujung dari tiap baris untuk posisi cell tanggal akhir
			{
				selisihBaris = posisiTanggaAkhir / COLUMN_COUNT - posisiTanggaAwal / COLUMN_COUNT - 1;
				if((posisiTanggaAwal / COLUMN_COUNT) % 2 == 0) 								// arah jalan kanan ( ==> )
				{
					if(selisihBaris % 2 == 0) 												// selisih baris genap
					{
						selisihKolom = COLUMN_COUNT - posisiTanggaAwal  % COLUMN_COUNT;
					}
					else 																	// selisih baris ganjil
					{
						selisihKolom = 1 - posisiTanggaAwal  % COLUMN_COUNT;
					}
				}
				else 																		// arah jalan kiri
				{
					if(selisihBaris % 2 == 0) 												// selisih baris genap
					{
						selisihKolom = posisiTanggaAwal  % COLUMN_COUNT - COLUMN_COUNT;
					}
					else 																	// selisih baris ganjil
					{
						selisihKolom = posisiTanggaAwal  % COLUMN_COUNT - 1;
					}
				}
			}
		}
		else																				// ujung dari tiap baris untuk posisi cell tanggal awal
		{
			if(posisiTanggaAkhir % COLUMN_COUNT != 0) 										// bukan ujung dari tiap baris untuk posisi cell tanggal akhir
			{
				selisihBaris = posisiTanggaAkhir / COLUMN_COUNT - posisiTanggaAwal / COLUMN_COUNT + 1;
				if((posisiTanggaAwal / COLUMN_COUNT) % 2 == 1) 								// arah jalan kanan
				{
					if(selisihBaris % 2 == 0) 												// selisih baris genap
					{
						selisihKolom = posisiTanggaAkhir  % COLUMN_COUNT - COLUMN_COUNT;
					}
					else 																	// selisih baris ganjil
					{
						selisihKolom = 1 - posisiTanggaAkhir  % COLUMN_COUNT;
					}
				}
				else 																		// arah jalan kiri
				{
					if(selisihBaris % 2 == 0) 												// selisih baris genap
					{
						selisihKolom = COLUMN_COUNT - posisiTanggaAkhir  % COLUMN_COUNT;
					}
					else 																	// selisih baris ganjil
					{
						selisihKolom = posisiTanggaAkhir  % COLUMN_COUNT - 1;
					}
				}
			}
			else 																			// ujung dari tiap baris untuk posisi cell tanggal akhir
			{
				selisihBaris = (posisiTanggaAkhir / COLUMN_COUNT - posisiTanggaAwal / COLUMN_COUNT);
				if((posisiTanggaAwal / COLUMN_COUNT) % 2 == 1) 								// arah jalan kanan
				{
					if(selisihBaris % 2 == 0) 												// selisih baris genap
					{
						selisihKolom = 0;
					}
					else 																	// selisih baris ganjil
					{
						selisihKolom = 1 - COLUMN_COUNT;
					}
				}
				else 																		// arah jalan kiri
				{
					if(selisihBaris % 2 == 0) 												// selisih baris genap
					{
						selisihKolom = 0;
					}
					else 																	// selisih baris ganjil
					{
						selisihKolom = COLUMN_COUNT - 1;
					}
				}
			}
		}

		sisiDepan 	= selisihBaris * GAME_MAP_CELL_WIDTH;;
		sisiPanjang	=  selisihKolom * GAME_MAP_CELL_WIDTH;
		sisiMiring 	= (int) Math.sqrt(sisiDepan * sisiDepan + sisiPanjang * sisiPanjang);		
		second 		= second+State_Gameplay.Second;
		int time	= 2;
		velocityX	= sisiPanjang / time;
		
		if(velocityX == 0) 																	// tangga lurus
		{
			velocityY	= sisiDepan / time;
		}
		else 																				// tangga miring
		{
			velocity	= velocityX / (sisiPanjang / sisiMiring);
			velocityY	= velocity * (sisiDepan / sisiMiring);
		}
		handler.setVelocity(velocityX, -velocityY);
				
		if(second >= time)
		{
			stop();
			Posisi_Mc_Current_Row	= posisiTanggaAkhir / COLUMN_COUNT + 1;
			Posisi_Mc_Current		= posisiTanggaAkhir;
			Posisi_Mc_Max			= posisiTanggaAkhir;
			second = 0;
		}
	}
}
