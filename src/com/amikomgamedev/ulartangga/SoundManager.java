package com.amikomgamedev.ulartangga;

import java.io.IOException;

import org.anddev.andengine.audio.music.Music;
import org.anddev.andengine.audio.music.MusicFactory;

public class SoundManager
{
	public Music[] bgm = new Music[2];
	public Music[] sfx;

	public final int BGM_MENU_MAIN	= 0;
	public final int BGM_GAMEPLAY 	= 1;
	public final int BGM_TOTAL 		= 2;

	public final String[] BGM_LOCATION =
		{
			"snd/inmenu/msc_menu.ogg", 
			"snd/ingame/classic theme UT.ogg",
			"snd/ingame/msc_ingame_bg_modern.ogg",
			"snd/ingame/galaxy theme UT(press).ogg",
			"snd/ingame/collosal theme UT.ogg",
		};

	public final int SFX_NAIK			= 0;
	public final int SFX_TURUN			= 1;
	public final int SFX_DICE			= 2;
	public final int SFX_BERANTEM		= 3;
	public final int SFX_JALAN			= 4;
	public final int SFX_TERLEMPAR		= 5;
	public final int SFX_SHAKE			= 6;
	
	public final int SFX_TOTAL = 7;

	public final String[][] SFX_LOCATION =
		{
			{
				"snd/ingame/classic/tangga.mp3",
				"snd/ingame/classic/ular.wav",
				"snd/ingame/classic/dice.mp3",
				"snd/ingame/Berantem sfx.ogg",
				"snd/ingame/sound jalan.ogg",
				"snd/ingame/terlempar.ogg",
				"snd/ingame/shake.mp3"
			},
			{
				"snd/ingame/modern/lift.mp3",
				"snd/ingame/modern/lift.mp3",
				"snd/ingame/modern/dadu.ogg",
				"snd/ingame/Berantem sfx.ogg",
				"snd/ingame/sound jalan.ogg",
				"snd/ingame/terlempar.ogg",
				"snd/ingame/shake.mp3"
			},
			{
				"snd/ingame/galaxy/roket2.ogg",
				"snd/ingame/galaxy/blackhole.ogg",
				"snd/ingame/galaxy/dadu.ogg",
				"snd/ingame/Berantem sfx.ogg",
				"snd/ingame/sound jalan.ogg",
				"snd/ingame/terlempar.ogg",
				"snd/ingame/shake.mp3"
			},
			{
				"snd/ingame/collosal/tangga.mp3",
				"snd/ingame/collosal/kayu.mp3",
				"snd/ingame/collosal/dadu.mp3",
				"snd/ingame/Berantem sfx.ogg",
				"snd/ingame/sound jalan.ogg",
				"snd/ingame/terlempar.ogg",
				"snd/ingame/shake.mp3"
			},
		};

	public boolean bgmEnable = true;
	public boolean sfxEnable = true;
	
	public boolean[] isPlayAgain = new boolean[SFX_TOTAL];

	public void loadSoundMenu()
	{
		try
		{
			bgm[BGM_MENU_MAIN] = MusicFactory.createMusicFromAsset(
					Game.activity.getMusicManager(),
					Game.activity, Data.BGM_INMENU);
			bgm[BGM_MENU_MAIN].setLooping(true);
		}
		catch (final IOException e)
		{
			Utils.TRACE(""+e);
		}
	}
	
	
	public void loadSoundGameplay()
	{
		try
		{
			int map = serverData.getSelectMap();
			
			bgm[BGM_GAMEPLAY] =
				MusicFactory.createMusicFromAsset(
						Game.activity.getMusicManager(),
						Game.activity, BGM_LOCATION[map + 1]);
			
			bgm[BGM_GAMEPLAY].setLooping(true);

			sfx = new Music[SFX_TOTAL];
			
			for (int i = 0; i < SFX_TOTAL; i++)
			{
				sfx[i] =
					MusicFactory.createMusicFromAsset(
							Game.activity.getMusicManager(),
							Game.activity, SFX_LOCATION[map][i]);
			}
		}
		catch (Exception e)
		{
			Utils.TRACE(""+e);
		}

	}

	public void playMusic(int index)
	{
		if (!bgmEnable)
		{
			return;
		}
		
		bgm[index].play();
	}

	public void stopMusic(int index)
	{
		if (bgm[index].isPlaying())
		{
			bgm[index].pause();
			bgm[index].seekTo(0);
		}

	}

	public void stopAllMusic()
	{
		for (int i = 0; i < BGM_TOTAL; i++)
		{
			if (bgm[i].isPlaying())
			{
				bgm[i].pause();
				bgm[i].seekTo(0);
			}
		}
	}

	public void playSfx(int index)
	{
		if (!sfxEnable)
		{
			return;
		}
		
		sfx[index].play();
	}
	
	public void stopSfx(int index)
	{
		if (sfx[index].isPlaying())
		{
			sfx[index].pause();
			sfx[index].seekTo(0);
		}
	}
	

	
	public void pauseAllSfx()
	{
		for (int i = 0; i < SFX_TOTAL; i++)
		{
			isPlayAgain[i] = false;
			
			if (sfx[i].isPlaying())
			{
				isPlayAgain[i] = true;
				sfx[i].pause();
			}
		}
	}
	
	public void playAgainAllSfx()
	{
		for (int i = 0; i < SFX_TOTAL; i++)
		{
			if (isPlayAgain[i])
			{
				sfx[i].play();
			}
		}
	}
}
