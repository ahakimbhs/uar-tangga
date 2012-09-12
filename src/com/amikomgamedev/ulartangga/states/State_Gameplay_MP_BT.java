package com.amikomgamedev.ulartangga.states;

import org.anddev.andengine.entity.modifier.MoveModifier;
import org.anddev.andengine.entity.scene.Scene;

import com.amikomgamedev.ulartangga.Config;
import com.amikomgamedev.ulartangga.Game;
import com.amikomgamedev.ulartangga.SoundManager;
import com.amikomgamedev.ulartangga.Utils;

public class State_Gameplay_MP_BT extends State_Gameplay
{
	protected boolean isRunning = true;
	
	@Override
	public Scene onLoadScene()
	{
		isMultiPlayer = true;
		startReceiverThread();
		return super.onLoadScene();
	}
	
	public void sendMessage(String str)
	{
		// sendMessage stub
	}

	public String receiveMessage()
	{
		return null;
	}
	
	private void startReceiverThread()
	{
		isRunning = true;
		new Thread()
		{
			public void run()
			{
				while (true)
				{
					if(isRunning)
					{
						String[] msgArray = new String[12];
						msgArray = receiveMessage().split(",", 3);
	
						handleReceivedMessage(msgArray);
						
						
					}
					else break;
				}
			}
		}.start();
	}

	private void handleReceivedMessage(String[] msgArray)
	{
		Utils.TRACE("Game");
		if(msgArray[0].contains("move"))
		{
			randomValue = Integer.parseInt(msgArray[1]);
				
			soundManager.playSfx(soundManager.SFX_DICE);
			switchPlayer();
			mc[Player_Cur].setMove(randomValue);

			MoveModifier moveModifier = new MoveModifier(
					1, 
					-Game.spr_Dice.getWidth(), 
					(Config.GAME_SCREEN_WIDTH - Game.spr_Dice.getWidth()) / 2,
					(Config.GAME_SCREEN_HEIGHT - Game.spr_Dice.getHeight()) / 2,
					(Config.GAME_SCREEN_HEIGHT - Game.spr_Dice.getHeight()) / 2);
			Game.spr_Dice.registerEntityModifier(moveModifier);
			
			Game.spr_Dice.setVisible(true);
			Game.spr_Dice.setVisible(true);
			Game.spr_Dice.animate(
					SPR_DICE_SPEED,
					SPR_DICE_FRAME[randomValue - 1],
					0);

		}
	}
}