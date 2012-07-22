package com.amikomgamedev.ulartangga;

import java.io.IOException;

import org.anddev.andengine.audio.music.Music;
import org.anddev.andengine.audio.music.MusicFactory;
import org.anddev.andengine.entity.sprite.AnimatedSprite;
import org.anddev.andengine.entity.sprite.Sprite;
import org.anddev.andengine.entity.text.ChangeableText;
import org.anddev.andengine.entity.text.Text;
import org.anddev.andengine.opengl.font.Font;
import org.anddev.andengine.opengl.font.FontFactory;
import org.anddev.andengine.opengl.texture.Texture;
import org.anddev.andengine.opengl.texture.TextureOptions;
import org.anddev.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlas;
import org.anddev.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlasTextureRegionFactory;
import org.anddev.andengine.opengl.texture.region.TextureRegion;
import org.anddev.andengine.opengl.texture.region.TiledTextureRegion;
import org.anddev.andengine.ui.activity.BaseGameActivity;
import org.anddev.andengine.util.Debug;

import android.content.Context;
import android.graphics.Color;

import com.amikomgamedev.ulartangga.states.State_Gameplay;

public class Game implements Data,
							 Define
{
	private static int map = -1;
	private static int maxPlayer = -1;
	
	private static BaseGameActivity activity;

	private static BitmapTextureAtlas[] tex_Font;
	public static Font[] font;

	public static Music bgm_Menu,
						bgm_Gameplay;
	
	private static BitmapTextureAtlas	tex_Img_Logo;
	private static TextureRegion 		reg_Img_Logo;
	public static Sprite 				spr_Img_Logo;
	
	private static BitmapTextureAtlas	tex_Img_Loading;
	private static TextureRegion 		reg_Img_Loading;
	public static Sprite 				spr_Img_Loading;
	
	private static BitmapTextureAtlas 	tex_Img_Map;
	public static TextureRegion 		reg_Img_Map;
	public static Sprite 				spr_Img_Map;
	
	private static BitmapTextureAtlas[]	tex_MC;
	public static TiledTextureRegion[] 	reg_MC;
	public static AnimatedSprite[] 		spr_MC;

	private static BitmapTextureAtlas[] tex_Icon_MC;
	public static TextureRegion[]		reg_Icon_MC;
	public static Sprite[]				spr_Icon_MC,
										spr_Icon_MC_Game_Pause;
	
	private static BitmapTextureAtlas 	tex_Img_Button_Dice;
	public static TiledTextureRegion 	reg_Img_Button_Dice;
	public static TextureRegion			reg_Img_Button_Pause;
	public static AnimatedSprite 		spr_Img_Button_Dice;
	public static Sprite				spr_Img_Button_Pause;
	
	private static BitmapTextureAtlas 	tex_Img_Informasi;
	public static TextureRegion 		reg_Img_Informasi_Footer,
										reg_Img_Informasi_Header;
	public static Sprite 				spr_Img_Informasi_Footer,
						 				spr_Img_Informasi_Header;
	
	private static BitmapTextureAtlas 	tex_Img_Select_Map_Bg;
	private static TextureRegion		reg_Img_Select_Map_Bg;
	public static Sprite				spr_Img_Select_Map_Bg;
	
	private static BitmapTextureAtlas 	tex_Img_Select_Map_Btn_Back;
	private static TextureRegion		reg_Img_Select_Map_Btn_Back;
	public static Sprite				spr_Img_Select_Map_Btn_Back;
	
	private static BitmapTextureAtlas 	tex_Img_Select_Map_Bg_Select;
	private static TextureRegion		reg_Img_Select_Map_Bg_Select;
	public static Sprite				spr_Img_Select_Map_Bg_Select;
	
	private static BitmapTextureAtlas[]	tex_Img_Select_Map_Icon_Map;
	private static TextureRegion[]		reg_Img_Select_Map_Icon_Map;
	public static Sprite[]				spr_Img_Select_Map_Icon_Map;
	
	private static BitmapTextureAtlas 	tex_Img_Select_Mc_Bg;
	private static TextureRegion		reg_Img_Select_Mc_Bg;
	public static Sprite				spr_Img_Select_Mc_Bg;
	
	private static BitmapTextureAtlas[]	tex_Img_Select_Mc_Icon_Mc;
	private static TextureRegion[]		reg_Img_Select_Mc_Icon_Mc;
	public static Sprite[]				spr_Img_Select_Mc_Icon_Mc;
	
	private static BitmapTextureAtlas 	tex_GamePause_Bg;
	private static TextureRegion		reg_GamePause_Bg;
	public static Sprite				spr_GamePause_Bg;
	
	public static Text					txtPause;
	public static ChangeableText[]		curPositionPause;
	
	private static BitmapTextureAtlas[] tex_GamePause_Mc;
	private static TiledTextureRegion[]	reg_GamePause_Mc;
	public static AnimatedSprite[]		spr_GamePause_Mc;
	
	private static BitmapTextureAtlas 	tex_GamePause_Btn;
	private static TextureRegion		reg_GamePause_Btn_MainMenu,
										reg_GamePause_Btn_Resume;
	public static Sprite				spr_GamePause_Btn_MainMenu,
										spr_GamePause_Btn_Resume;
	
	private static BitmapTextureAtlas 	tex_GameOver_Bg;
	private static TextureRegion		reg_GameOver_Bg;
	public static Sprite				spr_GameOver_Bg;
	
	private static BitmapTextureAtlas[] tex_GameOver_Mc;
	private static TiledTextureRegion[]	reg_GameOver_Mc;
	public static AnimatedSprite[]		spr_GameOver_Mc;
	
	private static BitmapTextureAtlas 	tex_GameOver_Btn;
	private static TextureRegion		reg_GameOver_Btn_MainMenu,
										reg_GameOver_Btn_Restart;
	public static Sprite				spr_GameOver_Btn_MainMenu,
										spr_GameOver_Btn_Restart;
	
//	arief
	private static BitmapTextureAtlas tex_Img_Back_Menu;
	public static TextureRegion reg_Img_Back_Menu;
	public static Sprite spr_Img_Back_Menu;
	
	private static BitmapTextureAtlas tex_Img_Title;
	public static TextureRegion reg_Img_Title_Menu;
	public static Sprite spr_Img_Title_Menu;
	
	private static BitmapTextureAtlas tex_Img_Hud_Menu;
	public static TextureRegion reg_Img_Btn_Credit,
								reg_Img_Btn_Option,
								reg_Img_Btn_Play;
	
	public static Sprite 	spr_Img_Btn_Credit,
							spr_Img_Btn_Option,
							spr_Img_Btn_Play;

	private static BitmapTextureAtlas tex_Img_Sprt_Dadu;
	public static TiledTextureRegion reg_Img_Dadu;
	public static AnimatedSprite spr_Img_Dadu;

	public static float dicePosX;
	public static float dicePosY;
	
	public static void appInit()
	{
		switch(Config.GAME_RES_USE)
		{
		case Config.GAME_RES_MINI:
			Config.GAME_SCREEN_WIDTH = 240;
			Config.GAME_SCREEN_HEIGHT = 320;
			break;
		case Config.GAME_RES_MID:
			Config.GAME_SCREEN_WIDTH = 320;
			Config.GAME_SCREEN_HEIGHT = 480;
			break;
		case Config.GAME_RES_HD:
			Config.GAME_SCREEN_WIDTH = 480;
			Config.GAME_SCREEN_HEIGHT = 800;
			break;
		}
	}
	
	public static void setContext(Context context)
	{
		activity = (BaseGameActivity)context;
	}
	
	public static void setGameplay(int map, int maxPlayer)
	{
		Game.map = map;
		Game.maxPlayer = maxPlayer;
	}
	
	private static void loadTexture(Texture texture)
	{
		activity.getEngine().getTextureManager().loadTexture(texture);
	}
	
	public static void loadLogo()
	{
		tex_Img_Logo = new BitmapTextureAtlas(512, 256, 
				TextureOptions.BILINEAR_PREMULTIPLYALPHA);
		reg_Img_Logo = BitmapTextureAtlasTextureRegionFactory.
				createFromAsset(tex_Img_Logo, activity, IMG_LOGO, 0, 0);
		
		loadTexture(tex_Img_Logo);
		
		spr_Img_Logo = new Sprite(
				0, 0, 
				Utils.getRatioW(300), 
				Utils.getRatioH(147), 
				reg_Img_Logo);
		
		spr_Img_Logo.setPosition(
				(Config.GAME_SCREEN_WIDTH	- spr_Img_Logo.getWidth()) / 2, 
				(Config.GAME_SCREEN_HEIGHT 	- spr_Img_Logo.getHeight()) / 2);
	}
	
	public static void loadLoadingBg()
	{
		tex_Img_Loading = new BitmapTextureAtlas(512, 512, 
				TextureOptions.BILINEAR_PREMULTIPLYALPHA);
		reg_Img_Loading = BitmapTextureAtlasTextureRegionFactory.
				createFromAsset(tex_Img_Loading, activity, IMG_LOADING, 0, 0);
		
		loadTexture(tex_Img_Loading);
		
		spr_Img_Loading = new Sprite(
				0, 0,
				Config.GAME_SCREEN_WIDTH,
				Config.GAME_SCREEN_HEIGHT,
				reg_Img_Loading);
	}
	
	public static void loadFont()
	{
		tex_Font	= new BitmapTextureAtlas[FONT_SIZE.length];
		font		= new Font[FONT_SIZE.length];
		
		for(int i = 0; i < FONT_SIZE.length; i++)
		{
			tex_Font[i] = new BitmapTextureAtlas(256, 256, 
					TextureOptions.BILINEAR_PREMULTIPLYALPHA);
			font[i] = FontFactory.createFromAsset(tex_Font[i], activity, 
					FONT_FILE_LOCATION,	Utils.getRatioH(FONT_SIZE[i]),
					true, Color.BLACK);
			
			activity.getEngine().getFontManager().loadFont(font[i]);
			loadTexture(tex_Font[i]);
		}
	}
	
	public static void loadGameMap(int pMap)
	{
		map = pMap;
		
		tex_Img_Map = new BitmapTextureAtlas(
				MAP_TEX_WIDTH, MAP_TEX_HEIGHT, Utils.getTextureOption());
		reg_Img_Map = BitmapTextureAtlasTextureRegionFactory.
				createFromAsset(tex_Img_Map, activity, IMG_INGAME_BACKGROUND_MAP[map], 0, 0);
		
		loadTexture(tex_Img_Map);
		
		spr_Img_Map = new Sprite(
				0, 0, 
				Utils.getRatioW(MAP_WIDTH), 
				Utils.getRatioH(MAP_HEIGHT), 
				reg_Img_Map);
	}
	
	public static void loadMC()
	{
		tex_MC = new BitmapTextureAtlas[maxPlayer];
		reg_MC = new TiledTextureRegion[maxPlayer];
		spr_MC = new AnimatedSprite[maxPlayer];
		
		for(int i = 0; i < maxPlayer; i++)
		{
			tex_MC[i] = new BitmapTextureAtlas(
					TREG_SPR_MC_WIDTH, TREG_SPR_MC_HEIGHT, 
					Utils.getTextureOption());
			reg_MC[i] = BitmapTextureAtlasTextureRegionFactory.
					createTiledFromAsset(tex_MC[i], activity, SPR_MC[map][i], 
					0, 0, Data.SPR_MC_COLUMN, Data.SPR_MC_ROW);
			
			loadTexture(tex_MC[i]);
			
			spr_MC[i] = new AnimatedSprite(
					0, 0, 
					Utils.getRatioW(MC_WIDTH), 
					Utils.getRatioH(MC_HEIGHT), 
					reg_MC[i]);
		}
	}
	
	public static void loadIconMC()
	{
		tex_Icon_MC = new BitmapTextureAtlas[SPR_ICON_MC[map].length];
		reg_Icon_MC = new TextureRegion[SPR_ICON_MC[map].length];
		spr_Icon_MC = new Sprite[SPR_ICON_MC[map].length];

		float posX = 5;
		
		for(int i = 0; i < SPR_ICON_MC[map].length; i++)
		{
			tex_Icon_MC[i] = new BitmapTextureAtlas(MC_ICON_TEX_WIDTH, MC_ICON_TEX_HEIGHT, 
					Utils.getTextureOption());
			reg_Icon_MC[i] = BitmapTextureAtlasTextureRegionFactory.
					createFromAsset(tex_Icon_MC[i], activity, 
					SPR_ICON_MC[map][i], 0, 0);
			
			loadTexture(tex_Icon_MC[i]);
			
			spr_Icon_MC[i] = new Sprite(
					0, 0,
					Utils.getRatioW(MC_ICON_WIDTH), 
					Utils.getRatioH(MC_ICON_HEIGHT),
					reg_Icon_MC[i]);

			float posY = spr_Img_Informasi_Footer.getY() + 
					(spr_Img_Informasi_Footer.getHeight() - spr_Icon_MC[i].getHeight()) / 2;
			
			spr_Icon_MC[i].setPosition(posX, posY);

			posX += GAME_MAP_CELL_WIDTH;
		}
	}
	
	public static void loadGameButton()
	{
		tex_Img_Button_Dice = new BitmapTextureAtlas(512, 256, 
				Utils.getTextureOption());
		reg_Img_Button_Dice = BitmapTextureAtlasTextureRegionFactory.
				createTiledFromAsset(tex_Img_Button_Dice, activity, 
				IMG_INGAME_BUTTON_SLOT_MACHINE, 0, 0, SPR_BOTTON_DICE_COLUMN,SPR_BOTTON_DICE_ROW);
		reg_Img_Button_Pause = BitmapTextureAtlasTextureRegionFactory.
				createFromAsset(tex_Img_Button_Dice, activity, 
						IMG_INGAME_BUTTON_PAUSE, 334, 0);
		
		loadTexture(tex_Img_Button_Dice);

		dicePosX = Config.GAME_SCREEN_WIDTH - 20 - reg_Img_Button_Dice.getWidth();
		dicePosY = spr_Img_Informasi_Footer.getY() - 20 - 
				reg_Img_Button_Dice.getHeight();
		
	}
	
	public static void loadGameInformasi()
	{
		tex_Img_Informasi = new BitmapTextureAtlas(512, 128, 
				Utils.getTextureOption());
		reg_Img_Informasi_Footer = BitmapTextureAtlasTextureRegionFactory.
				createFromAsset(tex_Img_Informasi, activity, IMG_INGAME_FOOTER, 0, 0);
		reg_Img_Informasi_Header = BitmapTextureAtlasTextureRegionFactory.
				createFromAsset(tex_Img_Informasi, activity, IMG_INGAME_HEADER, 0, 20);
		loadTexture(tex_Img_Informasi);
		
		spr_Img_Informasi_Footer = new Sprite(
				0, 0, 
				Config.GAME_SCREEN_WIDTH, 
				Utils.getRatioH(40), 
				reg_Img_Informasi_Footer);
		spr_Img_Informasi_Header = new Sprite(
				0, 0, 
				Config.GAME_SCREEN_WIDTH, 
				Utils.getRatioH(20), 
				reg_Img_Informasi_Header);
		
		float posY = State_Gameplay.camera.getMaxY() - spr_Img_Informasi_Footer.getHeight();
		spr_Img_Informasi_Footer.setPosition(0, posY);
		
	}
	
	public static void loadSoundGamePlay()
	{
		try {
			bgm_Gameplay = MusicFactory.createMusicFromAsset(activity.getMusicManager(), activity, Data.BGM_INGAME_GAMEPLAY[map]);
			bgm_Gameplay.setLooping(true);
		} catch (final IOException e) {
			Debug.e(e);
		}
	}
	
	public static void loadSoundMenu()
	{
		try {
			bgm_Menu = MusicFactory.createMusicFromAsset(activity.getMusicManager(), activity, Data.BGM_INMENU);
			bgm_Menu.setLooping(true);
		} catch (final IOException e) {
			Debug.e(e);
		}
	}

	public static void loadSelectMapBg()
	{
		tex_Img_Select_Map_Bg = new BitmapTextureAtlas(1024, 1024, Utils.getTextureOption());
		reg_Img_Select_Map_Bg = BitmapTextureAtlasTextureRegionFactory.createFromAsset(tex_Img_Select_Map_Bg, activity, Data.MENU_SELECT_MAP_BG, 0, 0);
		loadTexture(tex_Img_Select_Map_Bg);
		spr_Img_Select_Map_Bg = new Sprite(0, 0, Config.GAME_SCREEN_WIDTH, Config.GAME_SCREEN_HEIGHT, reg_Img_Select_Map_Bg);
	}

	public static void loadSelectMapBtn()
	{
		tex_Img_Select_Map_Btn_Back = new BitmapTextureAtlas(
				MENU_SELECT_MAP_BTN_BACK_TEX_WIDTH,
				MENU_SELECT_MAP_BTN_BACK_TEX_HEIGHT,
				Utils.getTextureOption());
		
		reg_Img_Select_Map_Btn_Back = BitmapTextureAtlasTextureRegionFactory.
				createFromAsset(tex_Img_Select_Map_Btn_Back, activity,
				MENU_SELECT_MAP_BTN_BACK, 0, 0);
		
		loadTexture(tex_Img_Select_Map_Btn_Back);
		
		spr_Img_Select_Map_Btn_Back = new Sprite(0, 0,
				MENU_SELECT_MAP_BTN_BACK_WIDTH,
				MENU_SELECT_MAP_BTN_BACK_HEIGHT,
				reg_Img_Select_Map_Btn_Back);
		
		spr_Img_Select_Map_Btn_Back.setPosition(
				Utils.getRatioW(25),
				Config.GAME_SCREEN_HEIGHT - Utils.getRatioH(25) - spr_Img_Select_Map_Btn_Back.getHeight());
	}
	
	public static void loadSelectMapBgSelect()
	{
		tex_Img_Select_Map_Bg_Select = new BitmapTextureAtlas(
				1024, 1024, Utils.getTextureOption());
		reg_Img_Select_Map_Bg_Select = BitmapTextureAtlasTextureRegionFactory.
				createFromAsset(tex_Img_Select_Map_Bg_Select, activity,
				Data.MENU_SELECT_MAP_BG_SELECT, 0, 0);
		loadTexture(tex_Img_Select_Map_Bg_Select);
		spr_Img_Select_Map_Bg_Select = new Sprite(0, 0,
				MENU_SELECT_MAP_MAP_ICON_WIDTH + Utils.getRatioW(20),
				(MENU_SELECT_MAP_MAP_ICON_HEIGHT + Utils.getRatioH(20)) * IMG_INGAME_BACKGROUND_MAP.length,
				reg_Img_Select_Map_Bg_Select);
		
		spr_Img_Select_Map_Bg_Select.setPosition(
				(Config.GAME_SCREEN_WIDTH - MENU_SELECT_MAP_MAP_ICON_WIDTH - Utils.getRatioW(20)) / 2,
				(Config.GAME_SCREEN_HEIGHT - MENU_SELECT_MAP_MAP_ICON_HEIGHT - Utils.getRatioH(20)) / 2);
	}
	
	public static void loadSelectMapIconMap()
	{
		tex_Img_Select_Map_Icon_Map = new BitmapTextureAtlas[IMG_INGAME_BACKGROUND_MAP.length];
		reg_Img_Select_Map_Icon_Map = new TextureRegion[IMG_INGAME_BACKGROUND_MAP.length];
		spr_Img_Select_Map_Icon_Map = new Sprite[IMG_INGAME_BACKGROUND_MAP.length];

//		float pY = 10;
		float pY = (spr_Img_Select_Map_Bg.getHeight() - Utils.getRatioH(MENU_SELECT_MAP_MAP_ICON_HEIGHT)) / 2;
		
		for(int i = 0; i < IMG_INGAME_BACKGROUND_MAP.length; i++)
		{
			tex_Img_Select_Map_Icon_Map[i] = new BitmapTextureAtlas(
					MAP_TEX_WIDTH, MAP_TEX_HEIGHT, 
					Utils.getTextureOption());
			reg_Img_Select_Map_Icon_Map[i] = BitmapTextureAtlasTextureRegionFactory.
					createFromAsset(tex_Img_Select_Map_Icon_Map[i], activity, IMG_INGAME_BACKGROUND_MAP[i], 
					0, 0);
			
			loadTexture(tex_Img_Select_Map_Icon_Map[i]);
			
			spr_Img_Select_Map_Icon_Map[i] = new Sprite(
					0, 0, 
					Utils.getRatioW(MENU_SELECT_MAP_MAP_ICON_WIDTH), 
					Utils.getRatioH(MENU_SELECT_MAP_MAP_ICON_HEIGHT), 
					reg_Img_Select_Map_Icon_Map[i]);
			
			spr_Img_Select_Map_Icon_Map[i].setPosition(
					(spr_Img_Select_Map_Bg.getWidth() - spr_Img_Select_Map_Icon_Map[i].getWidth()) / 2, pY);
			pY+= spr_Img_Select_Map_Icon_Map[i].getHeight();
			
//			spr_Img_Select_Map_Icon_Map[i].setPosition(
//					Utils.getRatioW(10), pY);
//			pY = pY + spr_Img_Select_Map_Icon_Map[i].getHeight() + Utils.getRatioH(20);
		}
	}
	
	public static void loadSelectMcBg()
	{
		tex_Img_Select_Mc_Bg = new BitmapTextureAtlas(
				1024, 1024, TextureOptions.BILINEAR_PREMULTIPLYALPHA);
		reg_Img_Select_Mc_Bg = BitmapTextureAtlasTextureRegionFactory.
				createFromAsset(tex_Img_Select_Mc_Bg, activity, Data.MENU_SELECT_MC_BG, 0, 0);
		loadTexture(tex_Img_Select_Mc_Bg);
		spr_Img_Select_Mc_Bg = new Sprite(0, 0, 
				Config.GAME_SCREEN_WIDTH >> 1, Config.GAME_SCREEN_HEIGHT >> 1,
				reg_Img_Select_Mc_Bg);
	}
	
	public static void loadSelectMcIcon()
	{
//		tex_Img_Select_Mc_Icon_Mc = new BitmapTextureAtlas[SPR_ICON_MC[map].length];
	}

	public static void loadGamePauseBackground()
	{
		tex_GamePause_Bg = new BitmapTextureAtlas(
				GAMEPAUSE_BG_TEX_WIDTH, 
				GAMEPAUSE_BG_TEX_HEIGHT, 
				Utils.getTextureOption());
		
		reg_GamePause_Bg = BitmapTextureAtlasTextureRegionFactory.
				createFromAsset(tex_GamePause_Bg, activity, 
				Data.GAMEPAUSE_BG, 0, 0);
		
		loadTexture(tex_GamePause_Bg);
		
		spr_GamePause_Bg = new Sprite(
				0, 0, 
				Utils.getRatioW(GAMEPAUSE_BG_WIDTH), 
				Utils.getRatioH(GAMEPAUSE_BG_HEIGHT), 
				reg_GamePause_Bg);
		
		spr_GamePause_Bg.setPosition(
				(Config.GAME_SCREEN_WIDTH - spr_GamePause_Bg.getWidth()) / 2,
				(Config.GAME_SCREEN_HEIGHT - spr_GamePause_Bg.getHeight()) / 2);
	}
	
	public static void loadGamePauseText()
	{
		txtPause = new Text(0, 0, font[FONT_SIZE_BIG], "PAUSE");
		txtPause.setPosition(
				(spr_GamePause_Bg.getWidth() / 2 - txtPause.getWidth()) / 2,
				Utils.getRatioH(10));
	}
	
	public static void loadGamePauseMc()
	{
		spr_Icon_MC_Game_Pause = new Sprite[maxPlayer];
		spr_GamePause_Mc = new AnimatedSprite[maxPlayer];
		curPositionPause = new ChangeableText[maxPlayer];
		
		float[] pX = new float[4];
		float[] pY = new float[4];
		
		float posMcX = (spr_GamePause_Bg.getWidth() / 2 - GAMEPAUSE_MC_ICON_WIDTH * 2) / 3;
		float posMcY = Utils.getRatioH(20);

		for(int i = 0; i < maxPlayer; i++)
		{
			spr_Icon_MC_Game_Pause[i] = new Sprite(
					0, 0,
					Utils.getRatioW(GAMEPAUSE_MC_ICON_WIDTH), 
					Utils.getRatioH(GAMEPAUSE_MC_ICON_HEIGHT),
					reg_Icon_MC[i]);
			
			spr_GamePause_Mc[i] = new AnimatedSprite(
					0, 0,
					Utils.getRatioW(GAMEPAUSE_MC_IDLE_WIDTH), 
					Utils.getRatioH(GAMEPAUSE_MC_IDLE_HEIGHT),
					reg_MC[i]);
			
			curPositionPause[i] = new ChangeableText(
					0, 0, Game.font[Data.FONT_SIZE_SMALL], "0", 3);
			
			spr_Icon_MC_Game_Pause[i].setPosition(spr_GamePause_Bg.getWidth() / 2 + posMcX  - Utils.getRatioW(10), posMcY);
			
			spr_GamePause_Mc[i].setPosition(
					(spr_GamePause_Bg.getWidth() / 2 - spr_GamePause_Mc[i].getWidth()) / 2,
					txtPause.getY() + txtPause.getHeight() + Utils.getRatioH(20));
			
			curPositionPause[i].setPosition(
					Game.spr_Icon_MC_Game_Pause[i].getWidth() + Utils.getRatioW(10),
					Game.spr_Icon_MC_Game_Pause[i].getHeight() - curPositionPause[i].getHeight());

			if(i == 1 && maxPlayer == 3)
				posMcX = (spr_GamePause_Bg.getWidth() / 2 - GAMEPAUSE_MC_ICON_WIDTH) / 2;
			else if(i % 2 == 0)
				posMcX = posMcX * 2 + spr_Icon_MC_Game_Pause[i].getWidth();
			else
				posMcX = (spr_GamePause_Bg.getWidth() / 2 - GAMEPAUSE_MC_ICON_WIDTH * 2) / 3;
			
			if(i % 2 == 1)
				posMcY+= posMcY + spr_Icon_MC_Game_Pause[i].getHeight();
		}
	}
	
	public static void loadGamePauseButton()
	{
		tex_GamePause_Btn = new BitmapTextureAtlas(
				GAMEPAUSE_BTN_TEX_WIDTH, GAMEPAUSE_BTN_TEX_HEIGHT,
				Utils.getTextureOption());

		reg_GamePause_Btn_Resume = BitmapTextureAtlasTextureRegionFactory.
				createFromAsset(tex_GamePause_Btn, activity, GAMEPAUSE_BTN_RESUME, 0, 0);
		reg_GamePause_Btn_MainMenu = BitmapTextureAtlasTextureRegionFactory.
				createFromAsset(tex_GamePause_Btn, activity, GAMEPAUSE_BTN_MAINMENU, 40, 0);
		
		loadTexture(tex_GamePause_Btn);

		spr_GamePause_Btn_Resume = new Sprite(0, 0,
				GAMEPAUSE_BTN_RESUME_WIDTH, GAMEPAUSE_BTN_RESUME_HEIGHT,
				reg_GamePause_Btn_Resume);
		spr_GamePause_Btn_MainMenu = new Sprite(0, 0,
				GAMEPAUSE_BTN_MAINMENU_WIDTH, GAMEPAUSE_BTN_MAINMENU_HEIGHT,
				reg_GamePause_Btn_MainMenu);
		
		float disX = (spr_GamePause_Bg.getWidth() / 2 - spr_GamePause_Btn_Resume.getWidth() * 2) / 3;
		
		spr_GamePause_Btn_Resume.setPosition(
				spr_GamePause_Bg.getWidth() / 2 + disX,
				spr_GamePause_Bg.getHeight() - Utils.getRatioH(10) - spr_GamePause_Btn_Resume.getHeight());
		
		spr_GamePause_Btn_MainMenu.setPosition(
				spr_GamePause_Bg.getWidth() / 2 + disX * 2 + spr_GamePause_Btn_Resume.getWidth(),
				spr_GamePause_Btn_Resume.getY());
	}
	
	public static void loadGameOverBackground()
	{
		tex_GameOver_Bg = new BitmapTextureAtlas(
				GAMEOVER_BG_TEX_WIDTH, 
				GAMEOVER_BG_TEX_HEIGHT, 
				Utils.getTextureOption());
		
		reg_GameOver_Bg = BitmapTextureAtlasTextureRegionFactory.
				createFromAsset(tex_GameOver_Bg, activity, 
				Data.GAMEOVER_BG, 0, 0);
		
		loadTexture(tex_GameOver_Bg);
		
		spr_GameOver_Bg = new Sprite(
				0, 0, 
				Utils.getRatioW(GAMEOVER_BG_WIDTH), 
				Utils.getRatioH(GAMEOVER_BG_HEIGHT), 
				reg_GameOver_Bg);
		
		spr_GameOver_Bg.setPosition(
				(Config.GAME_SCREEN_WIDTH - spr_GameOver_Bg.getWidth()) / 2,
				(Config.GAME_SCREEN_HEIGHT - spr_GameOver_Bg.getHeight()) / 2);
		
	}
	
	public static void loadGameOverMC()
	{
		tex_GameOver_Mc = new BitmapTextureAtlas[maxPlayer];
		reg_GameOver_Mc = new TiledTextureRegion[maxPlayer];
		spr_GameOver_Mc = new AnimatedSprite[maxPlayer];
		
		for(int i = 0; i < maxPlayer; i++)
		{
			tex_GameOver_Mc[i] = new BitmapTextureAtlas(
					GAMEOVER_MC_TEX_WIDTH, GAMEOVER_MC_TEX_HEIGHT, 
					Utils.getTextureOption());
			reg_GameOver_Mc[i] = BitmapTextureAtlasTextureRegionFactory.
					createTiledFromAsset(tex_GameOver_Mc[i], activity, GAMEOVER_MC[map][i], 
					0, 0, GAMEOVER_MC_COLUMN, GAMEOVER_MC_ROW);
			
			loadTexture(tex_GameOver_Mc[i]);
			
			spr_GameOver_Mc[i] = new AnimatedSprite(
					0, 0, 
					Utils.getRatioW(GAMEOVER_MC_WIN_WIDTH), 
					Utils.getRatioH(GAMEOVER_MC_WIN_HEIGHT), 
					reg_GameOver_Mc[i]);
		}
	}
	
	public static void loadGameOverButton()
	{
		tex_GameOver_Btn = new BitmapTextureAtlas(
				GAMEOVER_BTN_TEX_WIDTH, GAMEOVER_BTN_TEX_HEIGHT,
				Utils.getTextureOption());

		reg_GameOver_Btn_Restart = BitmapTextureAtlasTextureRegionFactory.
				createFromAsset(tex_GameOver_Btn, activity, GAMEOVER_BTN_RESTART, 0, 0);
		reg_GameOver_Btn_MainMenu = BitmapTextureAtlasTextureRegionFactory.
				createFromAsset(tex_GameOver_Btn, activity, GAMEOVER_BTN_MAINMENU, 40, 0);
		
		loadTexture(tex_GameOver_Btn);

		spr_GameOver_Btn_Restart = new Sprite(0, 0,
				GAMEOVER_BTN_RESTART_WIDTH, GAMEOVER_BTN_RESTART_HEIGHT,
				reg_GameOver_Btn_Restart);
		spr_GameOver_Btn_MainMenu = new Sprite(0, 0,
				GAMEOVER_BTN_MAINMENU_WIDTH, GAMEOVER_BTN_MAINMENU_HEIGHT,
				reg_GameOver_Btn_MainMenu);
		
		float disX = (spr_GameOver_Bg.getWidth() / 2 - spr_GameOver_Btn_Restart.getWidth() * 2) / 3;
		
		spr_GameOver_Btn_Restart.setPosition(
				spr_GameOver_Bg.getWidth() / 2 + disX,
				spr_GameOver_Bg.getHeight() - Utils.getRatioH(10) - spr_GameOver_Btn_Restart.getHeight());
		
		spr_GameOver_Btn_MainMenu.setPosition(
				spr_GameOver_Bg.getWidth() / 2 + disX * 2 + spr_GameOver_Btn_Restart.getWidth(),
				spr_GameOver_Btn_Restart.getY());
	}
	
	//arief
 	public static void loadBackgroundMenu ()
	{
		tex_Img_Back_Menu = new BitmapTextureAtlas(512, 512, TextureOptions.BILINEAR_PREMULTIPLYALPHA);
		reg_Img_Back_Menu = BitmapTextureAtlasTextureRegionFactory
				.createFromAsset(tex_Img_Back_Menu, activity, IMG_MENU_BACK, 0, 0);
		loadTexture(tex_Img_Back_Menu);
		spr_Img_Back_Menu = new Sprite(
				0, 0, 
				Config.GAME_SCREEN_WIDTH,
				Config.GAME_SCREEN_HEIGHT,
				reg_Img_Back_Menu);
	}
	
	public static void loadTitle()
	{
		tex_Img_Title = new BitmapTextureAtlas(512, 128, TextureOptions.BILINEAR_PREMULTIPLYALPHA);
		reg_Img_Title_Menu = BitmapTextureAtlasTextureRegionFactory
				.createFromAsset(tex_Img_Title, activity, IMG_MNU_TITLE, 0, 0);
		loadTexture(tex_Img_Title);
		float y = 50;
		float x = (Config.GAME_SCREEN_WIDTH - reg_Img_Title_Menu.getWidth()) / 2;
		spr_Img_Title_Menu = new Sprite( 
				0, 0, 
				Utils.getRatioW(195), 
				Utils.getRatioH(95), 
				reg_Img_Title_Menu);
		
		spr_Img_Title_Menu.setPosition(
				(Config.GAME_SCREEN_WIDTH - spr_Img_Title_Menu.getWidth()) / 2, 
				Utils.getRatioH(50));
		
	}
	
	public static void loadButtonMenu()
	{
		tex_Img_Hud_Menu = new BitmapTextureAtlas(256, 256, TextureOptions.BILINEAR_PREMULTIPLYALPHA);
		reg_Img_Btn_Credit = BitmapTextureAtlasTextureRegionFactory
				.createFromAsset(tex_Img_Hud_Menu, activity, IMG_MENU_BTN_CREDIT, 0, 0);
		reg_Img_Btn_Option = BitmapTextureAtlasTextureRegionFactory
				.createFromAsset(tex_Img_Hud_Menu, activity, IMG_MENU_BTN_OPTION, 50 , 0);
		reg_Img_Btn_Play = BitmapTextureAtlasTextureRegionFactory
				.createFromAsset(tex_Img_Hud_Menu, activity, IMG_MENU_BTN_PLAY, 0 , 50);
		loadTexture(tex_Img_Hud_Menu);
	}

	public static void loadDadu() {
		tex_Img_Sprt_Dadu = new BitmapTextureAtlas(1024, 128, TextureOptions.BILINEAR_PREMULTIPLYALPHA);
		reg_Img_Dadu = BitmapTextureAtlasTextureRegionFactory.
				createTiledFromAsset(tex_Img_Sprt_Dadu, activity, IMG_SPR_DADU, 0, 0 ,9 , 1);
		loadTexture(tex_Img_Sprt_Dadu);
		
	}
	
	//
	
}
