package com.amikomgamedev.ulartangga;

import java.io.IOException;

import org.anddev.andengine.audio.music.Music;
import org.anddev.andengine.audio.music.MusicFactory;

public class SoundManager
{
	public static Music[] bgm = new Music[2];
	public static Music[] sfx;

	public static final int BGM_MENU_MAIN	= 0;
	public static final int BGM_GAMEPLAY 	= 1;
	public static final int BGM_TOTAL 		= 2;

	public static final String[] BGM_LOCATION =
		{
			"snd/inmenu/msc_menu.ogg", 
			"snd/ingame/classic theme UT.ogg",
			"snd/ingame/msc_ingame_bg_modern.ogg",
			"snd/ingame/galaxy theme UT(press).ogg",
			"snd/ingame/collosal theme UT.ogg",
		};

	public static final int SFX_NAIK			= 0;
	public static final int SFX_TURUN			= 1;
	public static final int SFX_DICE			= 2;
	public static final int SFX_BERANTEM		= 3;
	public static final int SFX_JALAN			= 4;
	public static final int SFX_TERLEMPAR		= 5;
	
	public static final int SFX_TOTAL = 6;

	public static final String[][] SFX_LOCATION =
		{
			{
				"snd/ingame/classic/tangga.mp3",
				"snd/ingame/classic/ular.wav",
				"snd/ingame/classic/dadu.mp3",
				"snd/ingame/Berantem sfx.ogg",
				"snd/ingame/sound jalan.ogg",
				"snd/ingame/terlempar.ogg"
			},
			{
				"snd/ingame/modern/lift.mp3",
				"snd/ingame/modern/lift.mp3",
				"snd/ingame/classic/dadu.mp3",
				"snd/ingame/Berantem sfx.ogg",
				"snd/ingame/sound jalan.ogg",
				"snd/ingame/terlempar.ogg"
			},
			{
				"snd/ingame/galaxy/roket2.ogg",
				"snd/ingame/galaxy/blackhole.ogg",
				"snd/ingame/galaxy/dadu.mp3",
				"snd/ingame/Berantem sfx.ogg",
				"snd/ingame/sound jalan.ogg",
				"snd/ingame/terlempar.ogg"
			},
			{
				"snd/ingame/collosal/tangga.mp3",
				"snd/ingame/collosal/kayu.mp3",
				"snd/ingame/collosal/dadu.mp3",
				"snd/ingame/Berantem sfx.ogg",
				"snd/ingame/sound jalan.ogg",
				"snd/ingame/terlempar.ogg"
			},
		};
	
	public static boolean isSoundOn = true;
	public static boolean[] isPlayAgain = new boolean[SFX_TOTAL];

	public static void loadSoundMenu()
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
	
	
	public static void loadSoundGameplay()
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

	public static void playMusic(int index)
	{
		if (!isSoundOn)
		{
			return;
		}
		
		bgm[index].play();
	}

	public static void stopMusic(int index)
	{
		if (bgm[index].isPlaying())
		{
			bgm[index].pause();
			bgm[index].seekTo(0);
		}

	}

	public static void stopAllMusic()
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

	public static void playSfx(int index)
	{
		if (!isSoundOn)
		{
			return;
		}
		
		sfx[index].play();
	}
	
	public static void stopSfx(int index)
	{
		if (sfx[index].isPlaying())
		{
			sfx[index].pause();
			sfx[index].seekTo(0);
		}
	}
	

	
	public static void pauseAllSfx()
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
	
	public static void playAgainAllSfx()
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
