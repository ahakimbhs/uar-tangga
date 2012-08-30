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
import org.anddev.andengine.opengl.texture.region.TextureRegionFactory;
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
	
	public static BaseGameActivity activity;

	private static BitmapTextureAtlas[] tex_Font;
	public static Font[] font;

	public static Music bgm_Menu,
						bgm_Gameplay;
	
	private static BitmapTextureAtlas	tex_Img_Logo;
	public static TextureRegion 		reg_Img_Logo;
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
	
	private static BitmapTextureAtlas	tex_Smoke;
	public static TiledTextureRegion 	reg_Smoke;
	public static AnimatedSprite		spr_Smoke;
	
	private static BitmapTextureAtlas[]	tex_Dice;
	public static TiledTextureRegion[] 	reg_Dice;
	public static AnimatedSprite[]		spr_Dice;

	private static BitmapTextureAtlas[] tex_Icon_MC;
	public static TextureRegion[]		reg_Icon_MC;
	public static Sprite[]				spr_Icon_MC;
	
	private static BitmapTextureAtlas 	tex_Img_Button_Slide;
	public static TextureRegion 		reg_Img_Button_Slide_Bg,
										reg_Img_Button_Slide,
										reg_Img_Button_Pause;
	public static Sprite 				spr_Img_Button_Slide_Bg,
										spr_Img_Button_Slide,
										spr_Img_Button_Pause;
	
	private static BitmapTextureAtlas 	tex_Img_Informasi;
	public static TextureRegion 		reg_Img_Informasi_Footer,
										reg_Img_Informasi_Header;
	public static Sprite 				spr_Img_Informasi_Footer,
						 				spr_Img_Informasi_Header;
	
	private static BitmapTextureAtlas 	tex_Img_Select_Map_Bg;
	private static TextureRegion		reg_Img_Select_Map_Bg;
	public static Sprite				spr_Img_Select_Map_Bg,
										spr_Img_Select_Map;
	
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
	
	private static BitmapTextureAtlas	tex_Img_Select_Mc_Icon_Mc_Bg;
	private static TextureRegion		reg_Img_Select_Mc_Icon_Mc_Bg;
	public static Sprite[]				spr_Img_Select_Mc_Icon_Mc_Bg;
	
	private static BitmapTextureAtlas	tex_Img_Select_Mc_Btn_Add;
	private static TextureRegion		reg_Img_Select_Mc_Btn_Add;
	public static Sprite				spr_Img_Select_Mc_Btn_Add;
	
	private static BitmapTextureAtlas	tex_Img_Select_Mc_Btn_Delete;
	private static TextureRegion		reg_Img_Select_Mc_Btn_Delete;
	public static Sprite[]				spr_Img_Select_Mc_Btn_Delete;
	
	private static BitmapTextureAtlas	tex_Img_Select_Mc_Btn_Arrow;
	private static TextureRegion		reg_Img_Select_Mc_Btn_Arrow_Left;
	private static TextureRegion		reg_Img_Select_Mc_Btn_Arrow_Right;
	private static TextureRegion		reg_Img_Select_Mc_Btn_Arrow_Mc_Left;
	private static TextureRegion		reg_Img_Select_Mc_Btn_Arrow_Mc_Right;
	public static Sprite[][]			spr_Img_Select_Mc_Btn_Arrow_Mc;
	public static Sprite[]				spr_Img_Select_Mc_Btn_Arrow;
	
	private static BitmapTextureAtlas	tex_Img_Select_Mc_Btn_Type;
	private static TextureRegion[][]	reg_Img_Select_Mc_Btn_Type;
	public static Sprite[][]			spr_Img_Select_Mc_Btn_Type;
	
	private static BitmapTextureAtlas[]	tex_Img_Select_Mc_Icon_Mc;
	private static TextureRegion[][]	reg_Img_Select_Mc_Icon_Mc;
	public static Sprite[][]			spr_Img_Select_Mc_Icon_Mc;
	
	private static BitmapTextureAtlas 	tex_GamePause_Bg;
	private static TextureRegion		reg_GamePause_Bg;
	public static Sprite				spr_GamePause_Bg;
	
	public static Text					txtPause;
	public static ChangeableText[]		curPositionPause;
	
	private static BitmapTextureAtlas[] tex_GamePause_Mc;
	private static TiledTextureRegion[]	reg_GamePause_Mc_Idle;
	private static TextureRegion[]		reg_GamePause_Mc_Icon;
	public static AnimatedSprite[]		spr_GamePause_Mc_Idle;
	public static Sprite[]				spr_GamePause_Mc_Icon;
	
	private static BitmapTextureAtlas 	tex_GamePause_Btn;
	private static TextureRegion		reg_GamePause_Btn_MainMenu,
										reg_GamePause_Btn_Resume;
	public static Sprite				spr_GamePause_Btn_MainMenu,
										spr_GamePause_Btn_Resume;
	
	private static BitmapTextureAtlas 	tex_GameOver_Bg;
	private static TextureRegion		reg_GameOver_Bg;
	public static Sprite				spr_GameOver_Bg;
	
	private static BitmapTextureAtlas[] 	tex_GameOver_Mc;
	private static TiledTextureRegion[][]	reg_GameOver_Mc;
	public static AnimatedSprite[][]		spr_GameOver_Mc;
	
	private static BitmapTextureAtlas 	tex_GameOver_Btn;
	private static TextureRegion		reg_GameOver_Btn_MainMenu,
										reg_GameOver_Btn_Restart;
	public static Sprite				spr_GameOver_Btn_MainMenu,
										spr_GameOver_Btn_Restart;
	
//	arief
	private static BitmapTextureAtlas tex_Img_Back_Menu;
	public static TextureRegion reg_Img_Back_Menu;
	public static Sprite spr_Img_Back_Menu;
	
	private static BitmapTextureAtlas tex_Img_Back_Credit;
	public static TextureRegion reg_Img_Back_Credit;
	public static Sprite spr_Img_Back_Credit;
	
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

	private static BitmapTextureAtlas tex_Img_Close_Notif;
	public static TextureRegion reg_Img_Bg_Notif
								,reg_btn_no
								,reg_btn_yes;	
	public static Sprite spr_Img_Bg_Notif,
						spr_btn_no,
						spr_btn_yes;
	
	private static BitmapTextureAtlas tex_Img_Option;
	private static BitmapTextureAtlas tex_Btn_Option;
	public static TextureRegion reg_Img_Bg_Option,
								reg_btn_sound_on,
								reg_btn_sound_off,
								reg_btn_music_on,
								reg_btn_music_off;
	public static Sprite spr_Img_Bg_Option,
						spr_btn_sound_on,
						spr_btn_sound_off,
						spr_btn_music_on,
						spr_btn_music_off;
	
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

	public static void setMap(int map)
	{
		Game.map = map;
	}
	
	public static void setMaxPlayer(int maxPlayer)
	{
		Game.maxPlayer = maxPlayer;
	}
	
	private static void loadTexture(Texture texture)
	{
		activity.getEngine().getTextureManager().loadTexture(texture);
	}
	
	public static void loadLogo()
	{
		tex_Img_Logo = new BitmapTextureAtlas(512, 256,
				Utils.getTextureOption());
		reg_Img_Logo = BitmapTextureAtlasTextureRegionFactory.
				createFromAsset(tex_Img_Logo, activity, IMG_LOGO, 0, 0);
		
		loadTexture(tex_Img_Logo);
		
		spr_Img_Logo = new Sprite(
				0, 0, 
				Utils.getRatio(300),
				Utils.getRatio(147), 
				reg_Img_Logo);
		
		spr_Img_Logo.setPosition(
				(Config.GAME_SCREEN_WIDTH	- spr_Img_Logo.getWidth()) / 2, 
				(Config.GAME_SCREEN_HEIGHT 	- spr_Img_Logo.getHeight()) / 2);
	}
	
	public static void loadLoadingBg()
	{
		tex_Img_Loading = new BitmapTextureAtlas(512, 512, 
				Utils.getTextureOption());
		reg_Img_Loading = BitmapTextureAtlasTextureRegionFactory.
				createFromAsset(tex_Img_Loading, activity, IMG_LOADING, 0, 0);
		
		loadTexture(tex_Img_Loading);
		
		spr_Img_Loading = new Sprite(
				0, 0,
				Config.GAME_SCREEN_WIDTH,
				Config.GAME_SCREEN_HEIGHT,
				reg_Img_Loading);
		
		Text txt_Loading = new Text(0, 0, font[1], "Loading...");
		txt_Loading.setPosition(
				Config.GAME_SCREEN_WIDTH - Utils.getRatioW(20) - txt_Loading.getWidth(),
				Config.GAME_SCREEN_HEIGHT - Utils.getRatioW(20) - txt_Loading.getHeight());
		txt_Loading.setColor(0, 0, 0);
		spr_Img_Loading.attachChild(txt_Loading);
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
					true, Color.WHITE);
			
			activity.getEngine().getFontManager().loadFont(font[i]);
			loadTexture(tex_Font[i]);
		}
	}
	
	public static void loadGameMap(int pMap)
	{
		map = pMap;
		
		tex_Img_Map = new BitmapTextureAtlas(
				MAP_TEX_WIDTH,
				MAP_TEX_HEIGHT,
				Utils.getTextureOption());
		reg_Img_Map = BitmapTextureAtlasTextureRegionFactory.
				createFromAsset(tex_Img_Map, activity,
				IMG_INGAME_BACKGROUND_MAP[map], 0, 0);
		
		loadTexture(tex_Img_Map);
		
		spr_Img_Map = new Sprite(
				0, 0, 
				Utils.getRatio(MAP_WIDTH), 
				Utils.getRatio(MAP_HEIGHT), 
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
					TREG_SPR_MC_WIDTH,
					TREG_SPR_MC_HEIGHT, 
					Utils.getTextureOption());
			reg_MC[i] = BitmapTextureAtlasTextureRegionFactory.
					createTiledFromAsset(tex_MC[i], activity,
					SPR_MC[serverData.getCharPlayer(i)], 
					0, 0, Data.SPR_MC_COLUMN, Data.SPR_MC_ROW);
			
			loadTexture(tex_MC[i]);
			
			spr_MC[i] = new AnimatedSprite(
					0, 0, 
					Utils.getRatio(MC_WIDTH), 
					Utils.getRatio(MC_HEIGHT), 
					reg_MC[i]);
		}
	}
	
	public static void loadSmoke()
	{
			tex_Smoke = new BitmapTextureAtlas(
					GAMEPLAY_SMOKE_TEX_WIDTH,
					GAMEPLAY_SMOKE_TEX_HEIGHT, 
					Utils.getTextureOption());
			
			reg_Smoke = BitmapTextureAtlasTextureRegionFactory
					.createTiledFromAsset(tex_Smoke, activity,
					GAMEPLAY_SMOKE, 0, 0,
					GAMEPLAY_SMOKE_COLUMN,
					GAMEPLAY_SMOKE_ROW);
			
			loadTexture(tex_Smoke);
			
			spr_Smoke = new AnimatedSprite(
					0, 0, 
					Utils.getRatio(GAMEPLAY_SMOKE_WIDTH), 
					Utils.getRatio(GAMEPLAY_SMOKE_HEIGHT), 
					reg_Smoke);
	}
	
	public static void loadGamePlayDice()
	{
		tex_Dice = new BitmapTextureAtlas[6];
		reg_Dice = new TiledTextureRegion[6];
		spr_Dice = new AnimatedSprite[6];
		
		for(int i = 0; i < 6; i++)
		{
			tex_Dice[i] = new BitmapTextureAtlas(
					SPR_DICE_TEX_WIDTH,
					SPR_DICE_TEX_HEIGHT, 
					Utils.getTextureOption());
			
			reg_Dice[i] = BitmapTextureAtlasTextureRegionFactory.
					createTiledFromAsset(tex_Dice[i], activity,
					SPR_GAMEPLAY_DICE[i], 0, 0,
					Data.SPR_DICE_COLUMN, Data.SPR_DICE_ROW);
			
			loadTexture(tex_Dice[i]);
			
			spr_Dice[i] = new AnimatedSprite(
					0, 0, 
					Utils.getRatio(SPR_DICE_WIDTH), 
					Utils.getRatio(SPR_DICE_HEIGHT), 
					reg_Dice[i]);
		}
	}
	
	public static void loadIconMC()
	{
		tex_Icon_MC = new BitmapTextureAtlas[maxPlayer];
		reg_Icon_MC = new TextureRegion[maxPlayer];
		spr_Icon_MC = new Sprite[maxPlayer];

		float posX = 5;
		
		for(int i = 0; i < maxPlayer; i++)
		{
			tex_Icon_MC[i] = new BitmapTextureAtlas(
					MC_ICON_TEX_WIDTH,
					MC_ICON_TEX_HEIGHT, 
					Utils.getTextureOption());
			reg_Icon_MC[i] = BitmapTextureAtlasTextureRegionFactory.
					createFromAsset(tex_Icon_MC[i], activity, 
					SPR_ICON_MC[serverData.getCharPlayer(i)], 0, 0);
			
			loadTexture(tex_Icon_MC[i]);
			
			spr_Icon_MC[i] = new Sprite(
					0, 0,
					Utils.getRatio(MC_ICON_WIDTH), 
					Utils.getRatio(MC_ICON_HEIGHT),
					reg_Icon_MC[i]);

			float posY = spr_Img_Informasi_Footer.getY() + 
					(spr_Img_Informasi_Footer.getHeight() - spr_Icon_MC[i].getHeight()) / 2;
			
			spr_Icon_MC[i].setPosition(posX, posY);

			posX += GAME_MAP_CELL_WIDTH;
		}
	}
	
	public static void loadGameButton()
	{
		tex_Img_Button_Slide = new BitmapTextureAtlas(
				512, 256, 
				Utils.getTextureOption());
		reg_Img_Button_Slide_Bg = BitmapTextureAtlasTextureRegionFactory.
				createFromAsset(tex_Img_Button_Slide, activity, 
				IMG_INGAME_BUTTON_DICE[0], 0, 0);
		reg_Img_Button_Slide = BitmapTextureAtlasTextureRegionFactory.
				createFromAsset(tex_Img_Button_Slide, activity, 
				IMG_INGAME_BUTTON_DICE[1], 0, 100);
		reg_Img_Button_Pause = BitmapTextureAtlasTextureRegionFactory.
				createFromAsset(tex_Img_Button_Slide, activity, 
				IMG_INGAME_BUTTON_PAUSE, 200, 0);
		
		loadTexture(tex_Img_Button_Slide);
		
		spr_Img_Button_Slide_Bg = new Sprite(0, 0,
				Utils.getRatio(270),
				Utils.getRatio(75),
				Game.reg_Img_Button_Slide_Bg);
		spr_Img_Button_Slide = new Sprite(0, 0,
				Utils.getRatio(75),
				Utils.getRatio(50),
				Game.reg_Img_Button_Slide);
		spr_Img_Button_Pause =  new Sprite(
				0, 0, 
				Utils.getRatio(30), 
				Utils.getRatio(30), 
				Game.reg_Img_Button_Pause);
		
		spr_Img_Button_Slide_Bg.setPosition(
				(Config.GAME_SCREEN_WIDTH - spr_Img_Button_Slide_Bg.getWidth()) / 2,
				(Config.GAME_SCREEN_HEIGHT - spr_Img_Button_Slide_Bg.getHeight()) / 2);
		
		spr_Img_Button_Slide.setPosition(
				(spr_Img_Button_Slide_Bg.getHeight() - spr_Img_Button_Slide.getHeight()) / 2,
				(spr_Img_Button_Slide_Bg.getHeight() - spr_Img_Button_Slide.getHeight()) / 2);

		spr_Img_Button_Pause.setPosition(
				Config.GAME_SCREEN_WIDTH - spr_Img_Button_Pause.getWidth() - Utils.getRatioW(10),
				Config.GAME_SCREEN_HEIGHT - spr_Img_Button_Pause.getHeight() - (spr_Img_Informasi_Footer.getHeight() - spr_Img_Button_Pause.getHeight()) / 2);
		
		spr_Img_Button_Slide_Bg.attachChild(spr_Img_Button_Slide);
		spr_Img_Button_Slide_Bg.setAlpha(0.6f);
		spr_Img_Button_Slide.setAlpha(0.6f);
	}
	
	public static void loadGameInformasi()
	{
		tex_Img_Informasi = new BitmapTextureAtlas(
				512, 128, 
				Utils.getTextureOption());
		reg_Img_Informasi_Footer = BitmapTextureAtlasTextureRegionFactory.
				createFromAsset(tex_Img_Informasi, activity, IMG_INGAME_FOOTER, 0, 0);
		reg_Img_Informasi_Header = BitmapTextureAtlasTextureRegionFactory.
				createFromAsset(tex_Img_Informasi, activity, IMG_INGAME_HEADER, 0, 40);
		loadTexture(tex_Img_Informasi);
		
		spr_Img_Informasi_Footer = new Sprite(
				0, 0, 
				Config.GAME_SCREEN_WIDTH, 
				Utils.getRatioW(40), 
				reg_Img_Informasi_Footer);
		spr_Img_Informasi_Header = new Sprite(
				0, 0, 
				Config.GAME_SCREEN_WIDTH, 
				Utils.getRatioW(20), 
				reg_Img_Informasi_Header);
		
		float posY = State_Gameplay.camera.getMaxY() - spr_Img_Informasi_Footer.getHeight();
		spr_Img_Informasi_Footer.setPosition(0, posY);
		
	}
	
	public static void loadSoundGamePlay()
	{
		try {
			bgm_Gameplay = MusicFactory.createMusicFromAsset(
					activity.getMusicManager(), activity,
					Data.BGM_INGAME_GAMEPLAY[map]);
			bgm_Gameplay.setLooping(true);
		} catch (final IOException e) {
			Debug.e(e);
		}
	}
	
	public static void loadSoundMenu()
	{
		try {
			bgm_Menu = MusicFactory.createMusicFromAsset(
					activity.getMusicManager(), activity,
					Data.BGM_INMENU);
			bgm_Menu.setLooping(true);
		} catch (final IOException e) {
			Debug.e(e);
		}
	}

	public static void loadSelectMapBg()
	{
		tex_Img_Select_Map_Bg = new BitmapTextureAtlas(
				512, 512,
				Utils.getTextureOption());
		
		reg_Img_Select_Map_Bg = BitmapTextureAtlasTextureRegionFactory.
				createFromAsset(tex_Img_Select_Map_Bg, activity,
						Data.MENU_SELECT_MAP_BG, 0, 0);
		
		loadTexture(tex_Img_Select_Map_Bg);
		
		spr_Img_Select_Map_Bg = new Sprite(0, 0,
				Config.GAME_SCREEN_WIDTH,
				Config.GAME_SCREEN_HEIGHT,
				reg_Img_Select_Map_Bg);
		
		BitmapTextureAtlas atlas = new BitmapTextureAtlas(
				512, 64,
				Utils.getTextureOption());
		TextureRegion region = BitmapTextureAtlasTextureRegionFactory
				.createFromAsset(atlas, activity,
				IMG_INMENU_FOLDER_LOCATION + "map select.png", 0, 0);
		
		loadTexture(atlas);
		
		spr_Img_Select_Map = new Sprite(0, 0,
				Config.GAME_SCREEN_WIDTH,
				Utils.getRatioW(40), region);
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
				Utils.getRatio(MENU_SELECT_MAP_BTN_BACK_WIDTH),
				Utils.getRatio(MENU_SELECT_MAP_BTN_BACK_HEIGHT),
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
				Utils.getRatio(MENU_SELECT_MAP_MAP_ICON_WIDTH) + Utils.getRatioW(20),
				Utils.getRatio(MENU_SELECT_MAP_MAP_ICON_HEIGHT) * IMG_INGAME_BACKGROUND_MAP.length + Utils.getRatioH(20),
				reg_Img_Select_Map_Bg_Select);
		
		spr_Img_Select_Map_Bg_Select.setPosition(
				(Config.GAME_SCREEN_WIDTH - Utils.getRatio(MENU_SELECT_MAP_MAP_ICON_HEIGHT) - Utils.getRatio(20)) / 2,
				(Config.GAME_SCREEN_HEIGHT - Utils.getRatio(MENU_SELECT_MAP_MAP_ICON_HEIGHT) - Utils.getRatio(20)) / 2);
	}
	
	public static void loadSelectMapIconMap()
	{
		tex_Img_Select_Map_Icon_Map = new BitmapTextureAtlas[SELECT_MAP_ICON.length];
		reg_Img_Select_Map_Icon_Map = new TextureRegion[SELECT_MAP_ICON.length];
		spr_Img_Select_Map_Icon_Map = new Sprite[SELECT_MAP_ICON.length];

		float pY = 10;
		
		for(int i = 0; i < SELECT_MAP_ICON.length; i++)
		{
			tex_Img_Select_Map_Icon_Map[i] = new BitmapTextureAtlas(
					MAP_TEX_WIDTH,
					MAP_TEX_HEIGHT, 
					Utils.getTextureOption());
			reg_Img_Select_Map_Icon_Map[i] = BitmapTextureAtlasTextureRegionFactory.
					createFromAsset(tex_Img_Select_Map_Icon_Map[i], activity, SELECT_MAP_ICON[i], 
					0, 0);
			
			loadTexture(tex_Img_Select_Map_Icon_Map[i]);
			
			spr_Img_Select_Map_Icon_Map[i] = new Sprite(
					0, 0, 
					Utils.getRatio(MENU_SELECT_MAP_MAP_ICON_WIDTH), 
					Utils.getRatio(MENU_SELECT_MAP_MAP_ICON_HEIGHT), 
					reg_Img_Select_Map_Icon_Map[i]);
			
			spr_Img_Select_Map_Icon_Map[i].setPosition(
					Utils.getRatioW(10), pY);
			pY = pY + spr_Img_Select_Map_Icon_Map[i].getHeight();
		}
	}
	
	public static void loadSelectMcBg()
	{
		tex_Img_Select_Mc_Bg = new BitmapTextureAtlas(
				1024, 1024,
				Utils.getTextureOption());
		reg_Img_Select_Mc_Bg = BitmapTextureAtlasTextureRegionFactory.
				createFromAsset(tex_Img_Select_Mc_Bg, activity, Data.MENU_SELECT_MC_BG, 0, 0);
		loadTexture(tex_Img_Select_Mc_Bg);
		spr_Img_Select_Mc_Bg = new Sprite(
				0, 0, 
				Config.GAME_SCREEN_WIDTH,
				Config.GAME_SCREEN_HEIGHT,
				reg_Img_Select_Mc_Bg);
		
		BitmapTextureAtlas atlas = new BitmapTextureAtlas(
				512, 64,
				Utils.getTextureOption());
		TextureRegion region = BitmapTextureAtlasTextureRegionFactory
				.createFromAsset(atlas, activity,
				IMG_INMENU_FOLDER_LOCATION + "CHOOSE CHARACTER.png", 0, 0);
		
		loadTexture(atlas);
		
		Sprite sprite = new Sprite(0, 0,
				Config.GAME_SCREEN_WIDTH,
				Utils.getRatioW(40), region);
		
		spr_Img_Select_Mc_Bg.attachChild(sprite);
	}
	
	public static void loadSelectMcIconBg()
	{
		tex_Img_Select_Mc_Icon_Mc_Bg = new BitmapTextureAtlas(
				MENU_SELECT_MC_ICON_BG_TEX_WIDTH,
				MENU_SELECT_MC_ICON_BG_TEX_HEIGHT, 
				Utils.getTextureOption());
		reg_Img_Select_Mc_Icon_Mc_Bg = BitmapTextureAtlasTextureRegionFactory.
				createFromAsset(tex_Img_Select_Mc_Icon_Mc_Bg, activity,
				MENU_SELECT_MC_ICON_BG, 0, 0);
		
		loadTexture(tex_Img_Select_Mc_Icon_Mc_Bg);
		
		spr_Img_Select_Mc_Icon_Mc_Bg = new Sprite[4];
		
		for (int i = 0; i < 4; i++)
		{
			spr_Img_Select_Mc_Icon_Mc_Bg[i] = new Sprite(
					0, 0,
					Utils.getRatio(MENU_SELECT_MC_ICON_BG_WIDTH),
					Utils.getRatio(MENU_SELECT_MC_ICON_BG_HEIGHT),
					reg_Img_Select_Mc_Icon_Mc_Bg);
		}
		
		float pX = (Config.GAME_SCREEN_WIDTH / 2 - spr_Img_Select_Mc_Icon_Mc_Bg[0].getWidth()) / 2;
		float pY = Utils.getRatioH(50);

		spr_Img_Select_Mc_Icon_Mc_Bg[0].setPosition(pX, pY);
		spr_Img_Select_Mc_Icon_Mc_Bg[1].setPosition(
				Config.GAME_SCREEN_WIDTH / 2 + pX, pY);
	}
	
	public static void loadSelectMcIcon()
	{
		tex_Img_Select_Mc_Icon_Mc = new BitmapTextureAtlas[SELECT_MC_ICON.length];
		reg_Img_Select_Mc_Icon_Mc = new TextureRegion[4][SELECT_MC_ICON.length];
		spr_Img_Select_Mc_Icon_Mc = new Sprite[4][SELECT_MC_ICON.length];
		
		for(int i = 0; i < SELECT_MC_ICON.length; i++)
		{
			tex_Img_Select_Mc_Icon_Mc[i] = new BitmapTextureAtlas(
					MENU_SELECT_MC_ICON_TEX_WIDTH,
					MENU_SELECT_MC_ICON_TEX_HEIGHT, 
					Utils.getTextureOption());
		}
		
		for (int i = 0; i < 4; i++)
		{
			for(int j = 0; j < SELECT_MC_ICON.length; j++)
			{
				reg_Img_Select_Mc_Icon_Mc[i][j] = BitmapTextureAtlasTextureRegionFactory.
						createFromAsset(tex_Img_Select_Mc_Icon_Mc[j], activity,
						SELECT_MC_ICON[j], 0, 0);
				
				loadTexture(tex_Img_Select_Mc_Icon_Mc[j]);
				
				spr_Img_Select_Mc_Icon_Mc[i][j] = new Sprite(
						0, 0, 
						Utils.getRatio(MENU_SELECT_MC_ICON_WIDTH), 
						Utils.getRatio(MENU_SELECT_MC_ICON_HEIGHT), 
						reg_Img_Select_Mc_Icon_Mc[i][j]);
				
				spr_Img_Select_Mc_Icon_Mc[i][j].setPosition(
						(spr_Img_Select_Mc_Icon_Mc_Bg[i].getWidth() - spr_Img_Select_Mc_Icon_Mc[i][j].getWidth()) / 2,
						(spr_Img_Select_Mc_Icon_Mc_Bg[i].getHeight() - spr_Img_Select_Mc_Icon_Mc[i][j].getHeight()) / 2);
			}
		}
	}
	
	public static void loadSelectMcBtnDelete()
	{
		tex_Img_Select_Mc_Btn_Delete = new BitmapTextureAtlas(
				MENU_SELECT_MC_BTN_DELETE_TEX_WIDTH,
				MENU_SELECT_MC_BTN_DELETE_TEX_HEIGHT, 
				Utils.getTextureOption());
		reg_Img_Select_Mc_Btn_Delete = BitmapTextureAtlasTextureRegionFactory.
				createFromAsset(tex_Img_Select_Mc_Btn_Delete, activity,
				MENU_SELECT_MC_BTN_DELETE, 0, 0);
		
		loadTexture(tex_Img_Select_Mc_Btn_Delete);
		
		spr_Img_Select_Mc_Btn_Delete = new Sprite[2];
		
		for (int i = 0; i < 2; i++)
		{
			spr_Img_Select_Mc_Btn_Delete[i] = new Sprite(
					0, 0,
					Utils.getRatio(MENU_SELECT_MC_BTN_DELETE_WIDTH),
					Utils.getRatio(MENU_SELECT_MC_BTN_DELETE_HEIGHT),
					reg_Img_Select_Mc_Btn_Delete);
			
			spr_Img_Select_Mc_Btn_Delete[i].setPosition(
					spr_Img_Select_Mc_Icon_Mc_Bg[i+2].getX() + spr_Img_Select_Mc_Icon_Mc_Bg[i+2].getWidth()
					- spr_Img_Select_Mc_Btn_Delete[i].getWidth() / 2,
					spr_Img_Select_Mc_Icon_Mc_Bg[i+2].getY() - spr_Img_Select_Mc_Btn_Delete[i].getHeight() / 2);
		}
	}
	
	public static void loadSelectMcBtnType()
	{
		tex_Img_Select_Mc_Btn_Type = new BitmapTextureAtlas(
				MENU_SELECT_MC_BTN_TYPE_TEX_WIDTH,
				MENU_SELECT_MC_BTN_TYPE_TEX_HEIGHT, 
				Utils.getTextureOption());
		
		loadTexture(tex_Img_Select_Mc_Btn_Type);
		
		reg_Img_Select_Mc_Btn_Type = new TextureRegion[4][2];
		spr_Img_Select_Mc_Btn_Type = new Sprite[4][2];
		
		for (int i = 0; i < 4; i++)
		{
			reg_Img_Select_Mc_Btn_Type[i][TYPE_PLAYER] = BitmapTextureAtlasTextureRegionFactory.
					createFromAsset(tex_Img_Select_Mc_Btn_Type, activity,
					MENU_SELECT_MC_BTN_TYPE[TYPE_PLAYER], 0, 0);
			reg_Img_Select_Mc_Btn_Type[i][TYPE_AI] = BitmapTextureAtlasTextureRegionFactory.
					createFromAsset(tex_Img_Select_Mc_Btn_Type, activity,
					MENU_SELECT_MC_BTN_TYPE[TYPE_AI], 0, 30);
			
			spr_Img_Select_Mc_Btn_Type[i][TYPE_PLAYER] = 
					new Sprite(0, 0,
					Utils.getRatio(MENU_SELECT_MC_BTN_TYPE_WIDTH),
					Utils.getRatio(MENU_SELECT_MC_BTN_TYPE_HEIGHT),
					reg_Img_Select_Mc_Btn_Type[i][TYPE_PLAYER]);
			
			spr_Img_Select_Mc_Btn_Type[i][TYPE_AI] = 
					new Sprite(0, 0,
					Utils.getRatio(MENU_SELECT_MC_BTN_TYPE_WIDTH),
					Utils.getRatio(MENU_SELECT_MC_BTN_TYPE_HEIGHT),
					reg_Img_Select_Mc_Btn_Type[i][TYPE_AI]);
			
			spr_Img_Select_Mc_Btn_Type[i][TYPE_PLAYER].setPosition(0,
					spr_Img_Select_Mc_Icon_Mc_Bg[i].getHeight()
					+ Utils.getRatioH(10));
			
			spr_Img_Select_Mc_Btn_Type[i][TYPE_AI].setPosition(0,
					spr_Img_Select_Mc_Icon_Mc_Bg[i].getHeight()
					+ Utils.getRatioH(10));
		}
	}
	
	public static void loadSelectMcBtnArrow()
	{
		tex_Img_Select_Mc_Btn_Arrow = new BitmapTextureAtlas(
				MENU_SELECT_MC_BTN_ARROW_TEX_WIDTH,
				MENU_SELECT_MC_BTN_ARROW_TEX_HEIGHT, 
				Utils.getTextureOption());
		reg_Img_Select_Mc_Btn_Arrow_Mc_Left = BitmapTextureAtlasTextureRegionFactory.
				createFromAsset(tex_Img_Select_Mc_Btn_Arrow, activity,
				MENU_SELECT_MC_BTN_ARROW_MC, 75, 0);
		reg_Img_Select_Mc_Btn_Arrow_Mc_Right = BitmapTextureAtlasTextureRegionFactory.
				createFromAsset(tex_Img_Select_Mc_Btn_Arrow, activity,
				MENU_SELECT_MC_BTN_ARROW_MC, 75, 0);
		reg_Img_Select_Mc_Btn_Arrow_Mc_Right.setFlippedHorizontal(true);

		reg_Img_Select_Mc_Btn_Arrow_Left = BitmapTextureAtlasTextureRegionFactory.
				createFromAsset(tex_Img_Select_Mc_Btn_Arrow, activity,
				MENU_SELECT_MC_BTN_ARROW, 0, 0);
		reg_Img_Select_Mc_Btn_Arrow_Right = BitmapTextureAtlasTextureRegionFactory.
				createFromAsset(tex_Img_Select_Mc_Btn_Arrow, activity,
				MENU_SELECT_MC_BTN_ARROW, 0, 0);
		reg_Img_Select_Mc_Btn_Arrow_Right.setFlippedHorizontal(true);
		
		loadTexture(tex_Img_Select_Mc_Btn_Arrow);
		
		spr_Img_Select_Mc_Btn_Arrow_Mc = new Sprite[4][2];
		spr_Img_Select_Mc_Btn_Arrow = new Sprite[4];
		
		for (int i = 0; i < 4; i++)
		{
			spr_Img_Select_Mc_Btn_Arrow_Mc[i][0] = 
					new Sprite(0, 0,
					Utils.getRatio(MENU_SELECT_MC_BTN_ARROW_MC_WIDTH),
					Utils.getRatio(MENU_SELECT_MC_BTN_ARROW_MC_HEIGHT),
					reg_Img_Select_Mc_Btn_Arrow_Mc_Left);
			
			spr_Img_Select_Mc_Btn_Arrow_Mc[i][1] = 
					new Sprite(0, 0,
					Utils.getRatio(MENU_SELECT_MC_BTN_ARROW_MC_WIDTH),
					Utils.getRatio(MENU_SELECT_MC_BTN_ARROW_MC_HEIGHT),
					reg_Img_Select_Mc_Btn_Arrow_Mc_Right);
		}
		
		spr_Img_Select_Mc_Btn_Arrow[0] = 
				new Sprite(0, 0,
				Utils.getRatio(MENU_SELECT_MC_BTN_ARROW_WIDTH),
				Utils.getRatio(MENU_SELECT_MC_BTN_ARROW_HEIGHT),
				reg_Img_Select_Mc_Btn_Arrow_Left);
		
		spr_Img_Select_Mc_Btn_Arrow[1] = 
				new Sprite(0, 0,
				Utils.getRatio(MENU_SELECT_MC_BTN_ARROW_WIDTH),
				Utils.getRatio(MENU_SELECT_MC_BTN_ARROW_HEIGHT),
				reg_Img_Select_Mc_Btn_Arrow_Right);
		
		float pX = Utils.getRatioW(5);
		float pY = (spr_Img_Select_Mc_Icon_Mc_Bg[0].getHeight() - spr_Img_Select_Mc_Btn_Arrow_Mc[0][0].getHeight()) / 2;

		for (int i = 0; i < 4; i++)
		{
			spr_Img_Select_Mc_Btn_Arrow_Mc[i][0].setPosition(
					-pX - spr_Img_Select_Mc_Btn_Arrow_Mc[i][0].getWidth(),
					pY);
			spr_Img_Select_Mc_Btn_Arrow_Mc[i][1].setPosition(
					pX + spr_Img_Select_Mc_Icon_Mc_Bg[i].getWidth(),
					pY);
		}

		spr_Img_Select_Mc_Btn_Arrow[0].setPosition(
				Utils.getRatioW(25),
				Config.GAME_SCREEN_HEIGHT - Utils.getRatioH(25) - spr_Img_Select_Mc_Btn_Arrow[0].getHeight());
		spr_Img_Select_Mc_Btn_Arrow[1].setPosition(
				Config.GAME_SCREEN_WIDTH - Utils.getRatioW(25) - spr_Img_Select_Mc_Btn_Arrow[1].getWidth(),
				Config.GAME_SCREEN_HEIGHT - Utils.getRatioH(25) - spr_Img_Select_Mc_Btn_Arrow[1].getHeight());
	}
	
	public static void loadSelectMcBtnAdd()
	{
		tex_Img_Select_Mc_Btn_Add = new BitmapTextureAtlas(
				MENU_SELECT_MC_BTN_ADD_TEX_WIDTH,
				MENU_SELECT_MC_BTN_ADD_TEX_HEIGHT, 
				Utils.getTextureOption());
		reg_Img_Select_Mc_Btn_Add = BitmapTextureAtlasTextureRegionFactory.
				createFromAsset(tex_Img_Select_Mc_Btn_Add, activity,
				MENU_SELECT_MC_BTN_ADD, 0, 0);
		
		loadTexture(tex_Img_Select_Mc_Btn_Add);
		
		spr_Img_Select_Mc_Btn_Add = new Sprite(0, 0,
				Utils.getRatio(MENU_SELECT_MC_BTN_ADD_WIDTH),
				Utils.getRatio(MENU_SELECT_MC_BTN_ADD_HEIGHT),
				reg_Img_Select_Mc_Btn_Add);
		
		float pX = (Config.GAME_SCREEN_WIDTH - spr_Img_Select_Mc_Btn_Add.getWidth()) / 2;
		float pY = spr_Img_Select_Mc_Icon_Mc_Bg[0].getY() + spr_Img_Select_Mc_Icon_Mc_Bg[0].getHeight()
				+ Utils.getRatioH(40);
		
		spr_Img_Select_Mc_Btn_Add.setPosition(pX, pY);
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
				Utils.getRatio(GAMEPAUSE_BG_WIDTH), 
				Utils.getRatio(GAMEPAUSE_BG_HEIGHT), 
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
				Utils.getRatio(10));
	}
	
	public static void loadGamePauseMc()
	{
		tex_GamePause_Mc 		= new BitmapTextureAtlas[maxPlayer];
		reg_GamePause_Mc_Idle 	= new TiledTextureRegion[maxPlayer];
		reg_GamePause_Mc_Icon 	= new TextureRegion[maxPlayer];
		spr_GamePause_Mc_Icon 	= new Sprite[maxPlayer];
		spr_GamePause_Mc_Idle 	= new AnimatedSprite[maxPlayer];
		curPositionPause 		= new ChangeableText[maxPlayer];
		
		float posMcX = (spr_GamePause_Bg.getWidth() / 2 - Utils.getRatio(GAMEPAUSE_MC_ICON_WIDTH) * 2) / 3;
		float posMcY = Utils.getRatio(20);

		for(int i = 0; i < maxPlayer; i++)
		{
			tex_GamePause_Mc[i] = new BitmapTextureAtlas(
					GAMEPAUSE_MC_TEX_WIDTH,
					GAMEPAUSE_MC_TEX_HEIGHT,
					Utils.getTextureOption());
			
			reg_GamePause_Mc_Idle[i] = BitmapTextureAtlasTextureRegionFactory
					.createTiledFromAsset(tex_GamePause_Mc[i], activity,
					GAMEPAUSE_MC_IDLE[serverData.getCharPlayer(i)], 0, 0,
					GAMEPAUSE_MC_IDLE_COLUMN, GAMEPAUSE_MC_IDLE_ROW);
			
			reg_GamePause_Mc_Icon[i] = BitmapTextureAtlasTextureRegionFactory
					.createFromAsset(tex_GamePause_Mc[i], activity,
					SPR_ICON_MC[serverData.getCharPlayer(i)],
					0, 95);
			
			loadTexture(tex_GamePause_Mc[i]);
			
			spr_GamePause_Mc_Icon[i] = new Sprite(
					0, 0,
					Utils.getRatio(GAMEPAUSE_MC_ICON_WIDTH), 
					Utils.getRatio(GAMEPAUSE_MC_ICON_HEIGHT),
					reg_GamePause_Mc_Icon[i]);
			
			spr_GamePause_Mc_Idle[i] = new AnimatedSprite(
					0, 0,
					Utils.getRatio(GAMEPAUSE_MC_IDLE_WIDTH), 
					Utils.getRatio(GAMEPAUSE_MC_IDLE_HEIGHT),
					reg_GamePause_Mc_Idle[i]);
			
			curPositionPause[i] = new ChangeableText(
					0, 0, Game.font[Data.FONT_SIZE_SMALL], "0", 3);
			
			spr_GamePause_Mc_Icon[i].setPosition(spr_GamePause_Bg.getWidth() / 2 + posMcX  - Utils.getRatioW(10), posMcY);
			
			spr_GamePause_Mc_Idle[i].setPosition(
					(spr_GamePause_Bg.getWidth() / 2 - spr_GamePause_Mc_Idle[i].getWidth()) / 2,
					txtPause.getY() + txtPause.getHeight() + Utils.getRatio(20));
			
			curPositionPause[i].setPosition(
					Game.spr_GamePause_Mc_Icon[i].getWidth() + Utils.getRatio(8),
					Game.spr_GamePause_Mc_Icon[i].getHeight() - curPositionPause[i].getHeight());

			if(i == 1 && maxPlayer == 3)
				posMcX = (spr_GamePause_Bg.getWidth() / 2 - Game.spr_GamePause_Mc_Icon[i].getWidth()) / 2;
			else if(i % 2 == 0)
				posMcX = posMcX * 2 + spr_GamePause_Mc_Icon[i].getWidth();
			else
				posMcX = (spr_GamePause_Bg.getWidth() / 2 - Game.spr_GamePause_Mc_Icon[i].getWidth() * 2) / 3;
			
			if(i % 2 == 1)
				posMcY+= posMcY + spr_GamePause_Mc_Icon[i].getHeight();
		}
	}
	
	public static void loadGamePauseButton()
	{
		tex_GamePause_Btn = new BitmapTextureAtlas(
				GAMEPAUSE_BTN_TEX_WIDTH,
				GAMEPAUSE_BTN_TEX_HEIGHT,
				Utils.getTextureOption());

		reg_GamePause_Btn_Resume = BitmapTextureAtlasTextureRegionFactory.
				createFromAsset(tex_GamePause_Btn, activity,
				GAMEPAUSE_BTN_RESUME, 0, 0);
		reg_GamePause_Btn_MainMenu = BitmapTextureAtlasTextureRegionFactory.
				createFromAsset(tex_GamePause_Btn, activity,
				GAMEPAUSE_BTN_MAINMENU, 40, 0);
		
		loadTexture(tex_GamePause_Btn);

		spr_GamePause_Btn_Resume = new Sprite(0, 0,
				Utils.getRatio(GAMEPAUSE_BTN_RESUME_WIDTH),
				Utils.getRatio(GAMEPAUSE_BTN_RESUME_HEIGHT),
				reg_GamePause_Btn_Resume);
		spr_GamePause_Btn_MainMenu = new Sprite(0, 0,
				Utils.getRatio(GAMEPAUSE_BTN_MAINMENU_WIDTH),
				Utils.getRatio(GAMEPAUSE_BTN_MAINMENU_HEIGHT),
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
		
		reg_GameOver_Bg = BitmapTextureAtlasTextureRegionFactory
				.createFromAsset(tex_GameOver_Bg, activity, 
				Data.GAMEOVER_BG, 0, 0);
		
		loadTexture(tex_GameOver_Bg);
		
		spr_GameOver_Bg = new Sprite(
				0, 0, 
				Utils.getRatio(GAMEOVER_BG_WIDTH), 
				Utils.getRatio(GAMEOVER_BG_HEIGHT), 
				reg_GameOver_Bg);
		
		spr_GameOver_Bg.setPosition(
				(Config.GAME_SCREEN_WIDTH - spr_GameOver_Bg.getWidth()) / 2,
				(Config.GAME_SCREEN_HEIGHT - spr_GameOver_Bg.getHeight()) / 2);
		
	}
	
	public static void loadGameOverMC()
	{
		tex_GameOver_Mc 	= new BitmapTextureAtlas[maxPlayer];
		reg_GameOver_Mc		= new TiledTextureRegion[maxPlayer][2];
		spr_GameOver_Mc		= new AnimatedSprite[maxPlayer][2];
		
		for(int i = 0; i < maxPlayer; i++)
		{
			tex_GameOver_Mc[i] = new BitmapTextureAtlas(
					GAMEOVER_MC_TEX_WIDTH,
					GAMEOVER_MC_TEX_HEIGHT, 
					Utils.getTextureOption());
			
			reg_GameOver_Mc[i][MC_WIN] = BitmapTextureAtlasTextureRegionFactory.
					createTiledFromAsset(tex_GameOver_Mc[i], activity,
					GAMEOVER_MC_WIN[serverData.getCharPlayer(i)], 0, 0,
					GAMEOVER_MC_WIN_COLUMN, GAMEOVER_MC_WIN_ROW);
			
			reg_GameOver_Mc[i][MC_LOSE] = BitmapTextureAtlasTextureRegionFactory.
					createTiledFromAsset(tex_GameOver_Mc[i], activity,
					GAMEOVER_MC_LOSE[serverData.getCharPlayer(i)],
					0, GAMEOVER_MC_WIN_HEIGHT,
					GAMEOVER_MC_WIN_COLUMN, GAMEOVER_MC_WIN_ROW);
			
			loadTexture(tex_GameOver_Mc[i]);
			
			spr_GameOver_Mc[i][MC_WIN] = new AnimatedSprite(
					0, 0, 
					Utils.getRatio(GAMEOVER_MC_WIN_WIDTH), 
					Utils.getRatio(GAMEOVER_MC_WIN_HEIGHT), 
					reg_GameOver_Mc[i][MC_WIN]);
			
			spr_GameOver_Mc[i][MC_LOSE] = new AnimatedSprite(
					0, 0, 
					Utils.getRatio(GAMEOVER_MC_LOSE_WIDTH), 
					Utils.getRatio(GAMEOVER_MC_LOSE_HEIGHT), 
					reg_GameOver_Mc[i][MC_LOSE]);
			

			Game.spr_GameOver_Bg.attachChild(Game.spr_GameOver_Mc[i][MC_WIN]);
			Game.spr_GameOver_Bg.attachChild(Game.spr_GameOver_Mc[i][MC_LOSE]);

			Game.spr_GameOver_Mc[i][MC_WIN].setVisible(false);
			Game.spr_GameOver_Mc[i][MC_LOSE].setVisible(false);
		}
	}
	
	public static void loadGameOverButton()
	{
		tex_GameOver_Btn = new BitmapTextureAtlas(
				GAMEOVER_BTN_TEX_WIDTH,
				GAMEOVER_BTN_TEX_HEIGHT,
				Utils.getTextureOption());

		reg_GameOver_Btn_Restart = BitmapTextureAtlasTextureRegionFactory.
				createFromAsset(tex_GameOver_Btn, activity,
				GAMEOVER_BTN_RESTART, 0, 0);
		reg_GameOver_Btn_MainMenu = BitmapTextureAtlasTextureRegionFactory.
				createFromAsset(tex_GameOver_Btn, activity,
				GAMEOVER_BTN_MAINMENU, 40, 0);
		
		loadTexture(tex_GameOver_Btn);

		spr_GameOver_Btn_Restart = new Sprite(0, 0,
				Utils.getRatio(GAMEOVER_BTN_RESTART_WIDTH),
				Utils.getRatio(GAMEOVER_BTN_RESTART_HEIGHT),
				reg_GameOver_Btn_Restart);
		
		spr_GameOver_Btn_MainMenu = new Sprite(0, 0,
				Utils.getRatio(GAMEOVER_BTN_MAINMENU_WIDTH),
				Utils.getRatio(GAMEOVER_BTN_MAINMENU_HEIGHT),
				reg_GameOver_Btn_MainMenu);
		
		float disX = (spr_GameOver_Bg.getWidth() / 2 - spr_GameOver_Btn_Restart.getWidth() * 2) / 3;
		
		spr_GameOver_Btn_Restart.setPosition(
				spr_GameOver_Bg.getWidth() / 2 + disX,
				spr_GameOver_Bg.getHeight() - Utils.getRatio(10) - spr_GameOver_Btn_Restart.getHeight());
		
		spr_GameOver_Btn_MainMenu.setPosition(
				spr_GameOver_Bg.getWidth() / 2 + disX * 2 + spr_GameOver_Btn_Restart.getWidth(),
				spr_GameOver_Btn_Restart.getY());
		
		Game.spr_GameOver_Bg.attachChild(Game.spr_GameOver_Btn_MainMenu);
		Game.spr_GameOver_Bg.attachChild(Game.spr_GameOver_Btn_Restart);
	}
	
	//arief
 	public static void loadBackgroundMenu ()
	{
		tex_Img_Back_Menu = new BitmapTextureAtlas(
				512, 512, 
				Utils.getTextureOption());
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
		tex_Img_Title = new BitmapTextureAtlas(
				512, 128, 
				Utils.getTextureOption());
		reg_Img_Title_Menu = BitmapTextureAtlasTextureRegionFactory
				.createFromAsset(tex_Img_Title, activity, IMG_MNU_TITLE, 0, 0);
		loadTexture(tex_Img_Title);
		float y = 50;
		float x = (Config.GAME_SCREEN_WIDTH - reg_Img_Title_Menu.getWidth()) / 2;
		spr_Img_Title_Menu = new Sprite( 
				0, 0, 
				Utils.getRatio(195), 
				Utils.getRatio(95), 
				reg_Img_Title_Menu);
		
		spr_Img_Title_Menu.setPosition(
				(Config.GAME_SCREEN_WIDTH - spr_Img_Title_Menu.getWidth()) / 2, 
				Utils.getRatioH(50));
		
	}
	
	public static void loadButtonMenu()
	{
		tex_Img_Hud_Menu = new BitmapTextureAtlas(
				256, 256, 
				Utils.getTextureOption());
		reg_Img_Btn_Credit = BitmapTextureAtlasTextureRegionFactory
				.createFromAsset(tex_Img_Hud_Menu, activity, IMG_MENU_BTN_CREDIT, 0, 0);
		reg_Img_Btn_Option = BitmapTextureAtlasTextureRegionFactory
				.createFromAsset(tex_Img_Hud_Menu, activity, IMG_MENU_BTN_OPTION, 50 , 0);
		reg_Img_Btn_Play = BitmapTextureAtlasTextureRegionFactory
				.createFromAsset(tex_Img_Hud_Menu, activity, IMG_MENU_BTN_PLAY, 0 , 50);
		loadTexture(tex_Img_Hud_Menu);
	}

	public static void loadDadu() {
		tex_Img_Sprt_Dadu = new BitmapTextureAtlas(
				1024, 128, 
				Utils.getTextureOption());
		reg_Img_Dadu = BitmapTextureAtlasTextureRegionFactory.
				createTiledFromAsset(tex_Img_Sprt_Dadu, activity, IMG_SPR_DADU, 0, 0 ,9 , 1);
		loadTexture(tex_Img_Sprt_Dadu);
		
	}
	
	public static void load_BG_Credit(){
		tex_Img_Back_Credit = new BitmapTextureAtlas(512, 512, TextureOptions.BILINEAR_PREMULTIPLYALPHA);
		reg_Img_Back_Credit = BitmapTextureAtlasTextureRegionFactory
				.createFromAsset(tex_Img_Back_Credit, activity, IMG_BG_CREDIT, 0, 0);
		loadTexture(tex_Img_Back_Credit);
		spr_Img_Back_Credit = new Sprite(
				0, 0, 
				Config.GAME_SCREEN_WIDTH,
				Config.GAME_SCREEN_HEIGHT,
				reg_Img_Back_Credit);
	}
	
	public static void load_Close_Notif() {
		tex_Img_Close_Notif = new BitmapTextureAtlas(512, 512, TextureOptions.BILINEAR_PREMULTIPLYALPHA);
		reg_Img_Bg_Notif = BitmapTextureAtlasTextureRegionFactory
				.createFromAsset(tex_Img_Close_Notif, activity, IMG_BG_NOTIF, 0, 0);
		
		reg_btn_no = BitmapTextureAtlasTextureRegionFactory
				.createFromAsset(tex_Img_Close_Notif, activity, IMG_BTN_NO, NOTIF_BG_WIDTH, NOTIF_BG_HEIGHT);
		reg_btn_yes = BitmapTextureAtlasTextureRegionFactory
				.createFromAsset(tex_Img_Close_Notif, activity, IMG_BTN_YES, NOTIF_BG_WIDTH + NOTIF_BTN_WIDTH, NOTIF_BG_HEIGHT + NOTIF_BTN_HEIGHT);

		loadTexture(tex_Img_Close_Notif);
		
		spr_Img_Bg_Notif = new Sprite(
				(Config.GAME_SCREEN_WIDTH - NOTIF_BG_WIDTH ) /2,
				(Config.GAME_SCREEN_HEIGHT - NOTIF_BG_HEIGHT ) /2 ,
				reg_Img_Bg_Notif);
		
		spr_btn_no = new Sprite(
				0, 0, 
				reg_btn_no);
		
		spr_btn_yes = new Sprite(
				0, 0 ,
				reg_btn_yes);
		
	}
	
	public static void load_Option (){
		tex_Img_Option = new BitmapTextureAtlas(512, 512, Utils.getTextureOption());
		reg_Img_Bg_Option = BitmapTextureAtlasTextureRegionFactory
				.createFromAsset(tex_Img_Option, activity, IMG_BG_OPTION, 0, 0);
		
		spr_Img_Bg_Option = new Sprite(
				(Config.GAME_SCREEN_WIDTH - OPTION_BG_WIDTH) /2, (Config.GAME_SCREEN_HEIGHT - OPTION_BG_HEIGHT )/ 2,
				reg_Img_Bg_Option);
		
		tex_Btn_Option = new BitmapTextureAtlas(512, 512, Utils.getTextureOption());
		
		reg_btn_sound_on = BitmapTextureAtlasTextureRegionFactory
				.createFromAsset(tex_Btn_Option, activity, IMG_BTN_SOUND_ON, 0, 0);
		reg_btn_sound_off =  BitmapTextureAtlasTextureRegionFactory
		.createFromAsset(tex_Btn_Option, activity, IMG_BTN_SOUND_OFF, OPTION_BTN_WIDTH, OPTION_BTN_HEIGHT);
		reg_btn_music_on = BitmapTextureAtlasTextureRegionFactory
				.createFromAsset(tex_Btn_Option, activity, IMG_BTN_MUSIC_ON, 2 * OPTION_BTN_WIDTH, 2 * OPTION_BTN_HEIGHT);
		reg_btn_music_off =  BitmapTextureAtlasTextureRegionFactory
		.createFromAsset(tex_Btn_Option, activity, IMG_BTN_MUSIC_OFF, 3 * OPTION_BTN_WIDTH, 3 * OPTION_BTN_HEIGHT);
		
		
		spr_btn_music_on = new Sprite(
				Utils.getRatioW(180),
				Utils.getRatioH(20), 
				reg_btn_music_on);
		spr_btn_music_off = new Sprite(
				Utils.getRatioW(110), 
				Utils.getRatioH(20),
				reg_btn_music_off);
		
		spr_btn_sound_on = new Sprite(
				Utils.getRatioW(180),
				Utils.getRatioW(110),
				reg_btn_sound_on);
		
		spr_btn_sound_off = new Sprite(
				Utils.getRatioW(110),
				Utils.getRatioH(110),
				reg_btn_sound_off);
		
		
		loadTexture(tex_Img_Option);
		loadTexture(tex_Btn_Option);

	}
	
	
	//
	
}
