package com.amikomgamedev.ulartangga.entity;

import org.anddev.andengine.engine.handler.physics.PhysicsHandler;
import org.anddev.andengine.entity.sprite.AnimatedSprite;

import com.amikomgamedev.ulartangga.Data;
import com.amikomgamedev.ulartangga.Define;
import com.amikomgamedev.ulartangga.Game;
import com.amikomgamedev.ulartangga.Utils;
import com.amikomgamedev.ulartangga.states.State_Gameplay;

public class Entity_Mc implements Define
{
	private AnimatedSprite mc_Spr;
	private PhysicsHandler handler;

	public int 	POSISI_MC_START = 0;
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
	private int sisiDepan		= 0;
	private int sisiPanjang		= 0;
	private int sisiMiring 		= 0;
	
	private int curState;
	
	public Entity_Mc(AnimatedSprite spr)
	{
		mc_Spr 	= spr;
		handler = new PhysicsHandler(mc_Spr);
		
		mc_Spr.registerUpdateHandler(handler);
		mc_Spr.setPosition(
				-mc_Spr.getWidth(), 
				Game.spr_Img_Map.getHeight() - mc_Spr.getHeight());

		Posisi_Mc_Current		= POSISI_MC_START;
		Posisi_Mc_Current_Row	= 1;
		Posisi_Mc_Max			= POSISI_MC_START;
	}
	
	public void setMove(int valueDice)
	{
		Posisi_Mc_Max = Posisi_Mc_Current + valueDice;
	}
	
	private void setAnim(int anim)
	{
		mc_Spr.animate(
				Data.SPR_MC_ANIM_SPEED[anim], 
				Data.SPR_MC_ANIM_FRAME[anim][Data.ANI_FRAME_START], 
				Data.SPR_MC_ANIM_FRAME[anim][Data.ANI_FRAME_END], 
				true);
	}
	
	public AnimatedSprite getAnimatedSprite()
	{
		return mc_Spr;
	}
	
	public float getVelocityX()
	{
		return handler.getVelocityX();
	}
	
	public float getVelocityY()
	{
		return handler.getVelocityY();
	}
		
 	public void cekMove()
	{
		// move right after finish
		if(Posisi_Mc_Current_Row > ROW_COUNT)						
		{
			if(Posisi_Mc_Current % COLUMN_COUNT == 0
					&& mc_Spr.getX() > GAME_MAP_CELL_WIDTH + Utils.getCellCenterX(mc_Spr))
			{
				Posisi_Mc_Current--;
			}
			
			if(mc_Spr.getX() 
					> (COLUMN_COUNT + 1 - Posisi_Mc_Current % COLUMN_COUNT) 
					* GAME_MAP_CELL_WIDTH + Utils.getCellCenterX(mc_Spr))
			{
				Posisi_Mc_Current--;
			}
			
			if(curState != STATE_MOVE_RIGHT)
			{
				mc_Spr.stopAnimation();
				curState = STATE_MOVE_RIGHT;
			}
		}
		// move left or right
		else if(Posisi_Mc_Current % COLUMN_COUNT != 0 || Posisi_Mc_Current == 0)
		{
			// move right
			if(Posisi_Mc_Current_Row % 2 == 1)  					
			{
				if(mc_Spr.getX() 
						> Posisi_Mc_Current % COLUMN_COUNT 
						* GAME_MAP_CELL_WIDTH 
						+ Utils.getCellCenterX(mc_Spr))
				{
					Posisi_Mc_Current++;
				}
				
				if(curState != STATE_MOVE_RIGHT)
				{
					mc_Spr.stopAnimation();
					curState = STATE_MOVE_RIGHT;
				}
			}
			// move left
			else
			{
				if(mc_Spr.getX() + mc_Spr.getWidth() 
						< (COLUMN_COUNT - Posisi_Mc_Current % COLUMN_COUNT)
						* GAME_MAP_CELL_WIDTH 
						- Utils.getCellCenterX(mc_Spr))
				{
					Posisi_Mc_Current++;
				}
				if(curState != STATE_MOVE_LEFT)
				{
					mc_Spr.stopAnimation();
					curState = STATE_MOVE_LEFT;
				}
			}
		}
		// move up or back
		else if(Posisi_Mc_Current % COLUMN_COUNT == 0)
		{
			// back
			if(Posisi_Mc_Current_Row == ROW_COUNT)
			{
				Posisi_Mc_Current_Row++;
				Posisi_Mc_Max = ROW_COUNT * COLUMN_COUNT 
						- Posisi_Mc_Max % ROW_COUNT;
			}
			//move up
			else
			{
				if(mc_Spr.getY() + mc_Spr.getHeight() 
						< Game.spr_Img_Map.getHeight() - 
						Posisi_Mc_Current_Row * GAME_MAP_CELL_HEIGHT)
				{
					Posisi_Mc_Current++;
					Posisi_Mc_Current_Row++;
				}
				if(curState != STATE_MOVE_UP)
				{
					mc_Spr.stopAnimation();
					curState = STATE_MOVE_UP;
				}
			}
		}
	}
	
	public void updateMove()
	{
		switch(curState)
		{
			case STATE_IDLE:
				
				mc_Spr.stopAnimation();
				
				break;
				
			case STATE_MOVE_RIGHT:

				mc_Spr.getTextureRegion().setFlippedHorizontal(false);
				
				if(!mc_Spr.isAnimationRunning())
					setAnim(STATE_MOVE_RIGHT);
				
				handler.setVelocity(SPEED_MOVE, 0);
				
				break;
			case STATE_MOVE_UP:
				
				handler.setVelocity(0, -SPEED_MOVE);
				
				break;
			case STATE_MOVE_LEFT:
				
				mc_Spr.getTextureRegion().setFlippedHorizontal(true);
				
				if(!mc_Spr.isAnimationRunning())
					setAnim(STATE_MOVE_RIGHT);
				
				handler.setVelocity(-SPEED_MOVE, 0);
				
				break;
			case STATE_SNAKE:
				break;
			case STATE_LEADDER:
				break;
			case STATE_MOVE_TO_START:
				break;
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
		curState = STATE_IDLE;
		
		handler.setVelocity(0, 0);
		
		if(Posisi_Mc_Current_Row > ROW_COUNT)
			Posisi_Mc_Current_Row--;
	}
	
	public float getDistance()
	{
		return mc_Spr.getX() + mc_Spr.getWidth();
	}
	
	public void throwToStart(float distance)
	{
//		rumus parabola (fisika)
		second += State_Gameplay.Second;;
		int time	= 2;
		velocityX	= distance / time;
		gravitasi	= velocityX;
		velocity	= (float) (velocityX / Math.cos(Math.toRadians(45)));
		velocityY	= (float) (velocity * Math.sin(Math.toRadians(45))) - (gravitasi * second);
		handler.setVelocity(-velocityX, -velocityY);

		if(second >= time)
		{
			mc_Spr.setPosition(
					-mc_Spr.getWidth(), 
//					MAP_HEIGHT - GAME_MAP_CELL_HEIGHT + Utils.getCellCenterY(mc_Spr));
					Game.spr_Img_Map.getHeight() - mc_Spr.getHeight());
			
			stop();
			Posisi_Mc_Current		= POSISI_MC_START;
			Posisi_Mc_Max			= POSISI_MC_START;
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
						selisihKolom = posisiTanggaAkhir  % COLUMN_COUNT 
								- posisiTanggaAwal  % COLUMN_COUNT;
					}
					else 																	// selisih baris ganjil
					{
						selisihKolom = (COLUMN_COUNT + 1 - posisiTanggaAkhir  % COLUMN_COUNT) 
								- posisiTanggaAwal  % COLUMN_COUNT;
					}
				}
				else 																		// arah jalan kiri ( <== )
				{
					if(selisihBaris % 2 == 0) 												// selisih baris genap
					{
						selisihKolom = posisiTanggaAwal  % COLUMN_COUNT 
								- posisiTanggaAkhir  % COLUMN_COUNT;
					}
					else 																	// selisih baris ganjil
					{
						selisihKolom = posisiTanggaAwal  % COLUMN_COUNT 
								- (COLUMN_COUNT + 1 - posisiTanggaAkhir  % COLUMN_COUNT);
					}
				}
			}
			else 																			// ujung dari tiap baris untuk posisi cell tanggal akhir
			{
				selisihBaris = posisiTanggaAkhir / COLUMN_COUNT 
						- posisiTanggaAwal / COLUMN_COUNT - 1;
				
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
			
			if(posisiTanggaAkhir % COLUMN_COUNT == 0)
				Posisi_Mc_Current_Row	= posisiTanggaAkhir / COLUMN_COUNT;
			else
				Posisi_Mc_Current_Row	= posisiTanggaAkhir / COLUMN_COUNT + 1;
				
			Posisi_Mc_Current		= posisiTanggaAkhir;
			Posisi_Mc_Max			= posisiTanggaAkhir;
			second = 0;
		}
	}
}
