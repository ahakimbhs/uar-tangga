package com.amikomgamedev.ulartangga;

import java.io.IOException;

import org.anddev.andengine.audio.music.Music;
import org.anddev.andengine.audio.music.MusicFactory;
import org.anddev.andengine.engine.camera.hud.HUD;
import org.anddev.andengine.entity.sprite.AnimatedSprite;
import org.anddev.andengine.entity.sprite.Sprite;
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
	private static int map;
	
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
	public static Sprite[]				spr_Icon_MC;
	
	private static BitmapTextureAtlas 	tex_Img_Button_Dice;
	public static TextureRegion 		reg_Img_Button_Dice;
	public static Sprite 				spr_Img_Botton_Dice;
	
	private static BitmapTextureAtlas 	tex_Img_Informasi;
	public static TextureRegion 		reg_Img_Informasi_Footer,
										reg_Img_Informasi_Header;
	public static Sprite 				spr_Img_Informasi_Footer,
						 				spr_Img_Informasi_Header;
	
	private static BitmapTextureAtlas 	tex_Img_Select_Map_Bg;
	private static TextureRegion		reg_Img_Select_Map_Bg;
	public static Sprite				spr_Img_Select_Map_Bg;
	
	private static BitmapTextureAtlas[]	tex_Img_Select_Map_Icon_Map;
	private static TextureRegion[]		reg_Img_Select_Map_Icon_Map;
	public static Sprite[]				spr_Img_Select_Map_Icon_Map;
	
	private static BitmapTextureAtlas 	tex_Img_Select_Mc_Bg;
	private static TextureRegion		reg_Img_Select_Mc_Bg;
	public static Sprite				spr_Img_Select_Mc_Bg;
	
	private static BitmapTextureAtlas[]	tex_Img_Select_Mc_Icon_Mc;
	private static TextureRegion[]		reg_Img_Select_Mc_Icon_Mc;
	public static Sprite[]				spr_Img_Select_Mc_Icon_Mc;
	
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
	
	public static HUD hud;

	public static int dicePosX;
	public static int dicePosY;
	
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
	
	public static void loadHud()
	{
		hud = new HUD();
		State_Gameplay.camera.setHUD(hud);
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
				1024, 1024, TextureOptions.BILINEAR_PREMULTIPLYALPHA);
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
		tex_MC = new BitmapTextureAtlas[SPR_MC[map].length];
		reg_MC = new TiledTextureRegion[SPR_MC[map].length];
		spr_MC = new AnimatedSprite[SPR_MC[map].length];
		
		for(int i = 0; i < SPR_MC[map].length; i++)
		{
			tex_MC[i] = new BitmapTextureAtlas(
					TREG_SPR_MC_WIDTH, TREG_SPR_MC_HEIGHT, 
					TextureOptions.BILINEAR_PREMULTIPLYALPHA);
			reg_MC[i] = BitmapTextureAtlasTextureRegionFactory.
					createTiledFromAsset(tex_MC[i], activity, SPR_MC[map][i], 
					0, 0, Data.SPR_MC_COLUMN, Data.SPR_MC_ROW);
			
			loadTexture(tex_MC[i]);
			
			spr_MC[i] = new AnimatedSprite(
					0, 0, 
					Utils.getRatioW(60), 
					Utils.getRatioH(60), 
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
			tex_Icon_MC[i] = new BitmapTextureAtlas(32, 32, 
					TextureOptions.BILINEAR_PREMULTIPLYALPHA);
			reg_Icon_MC[i] = BitmapTextureAtlasTextureRegionFactory.
					createFromAsset(tex_Icon_MC[i], activity, 
					SPR_ICON_MC[map][i], 0, 0);
			
			loadTexture(tex_Icon_MC[i]);
			
			spr_Icon_MC[i] = new Sprite(
					0, 0,
					Utils.getRatioW(32), 
					Utils.getRatioH(32),
					reg_Icon_MC[i]);
			
			float posY = (spr_Img_Informasi_Header.getHeight() - spr_Icon_MC[i].getHeight()) / 2;
			
			spr_Icon_MC[i].setPosition(posX, i);

			posX += GAME_MAP_CELL_WIDTH;
		}
	}
	
	public static void loadGameButton()
	{
		tex_Img_Button_Dice = new BitmapTextureAtlas(64, 64, 
				TextureOptions.BILINEAR_PREMULTIPLYALPHA);
		reg_Img_Button_Dice = BitmapTextureAtlasTextureRegionFactory.
				createFromAsset(tex_Img_Button_Dice, activity, 
				IMG_INGAME_BOTTON_DICE, 0, 0);
		
		loadTexture(tex_Img_Button_Dice);

		dicePosX = Config.GAME_SCREEN_WIDTH - 20 - reg_Img_Button_Dice.getWidth();
		dicePosY = Config.GAME_SCREEN_HEIGHT - 20 - 
				reg_Img_Button_Dice.getHeight() - reg_Img_Informasi_Footer.getHeight();
	}
	
	public static void loadGameInformasi()
	{
		tex_Img_Informasi = new BitmapTextureAtlas(512, 128, 
				TextureOptions.BILINEAR_PREMULTIPLYALPHA);
		reg_Img_Informasi_Footer = BitmapTextureAtlasTextureRegionFactory.
				createFromAsset(tex_Img_Informasi, activity, IMG_INGAME_FOOTER, 0, 0);
		reg_Img_Informasi_Header = BitmapTextureAtlasTextureRegionFactory.
				createFromAsset(tex_Img_Informasi, activity, IMG_INGAME_HEADER, 0, 20);
		loadTexture(tex_Img_Informasi);
		
		spr_Img_Informasi_Footer = new Sprite(
				0, 0, 
				Config.GAME_SCREEN_WIDTH, 
				Utils.getRatioH(20), 
				reg_Img_Informasi_Footer);
		spr_Img_Informasi_Header = new Sprite(
				0, 0, 
				Config.GAME_SCREEN_WIDTH, 
				Utils.getRatioH(40), 
				reg_Img_Informasi_Header);
		
		float posY = State_Gameplay.camera.getMaxY() - spr_Img_Informasi_Footer.getHeight();
		spr_Img_Informasi_Footer.setPosition(0, posY);
		
	}
	
	public static void loadSelectMapBg()
	{
		tex_Img_Select_Map_Bg = new BitmapTextureAtlas(1024, 1024, TextureOptions.BILINEAR_PREMULTIPLYALPHA);
		reg_Img_Select_Map_Bg = BitmapTextureAtlasTextureRegionFactory.createFromAsset(tex_Img_Select_Map_Bg, activity, Data.IMG_INMENU_SELECT_MAP_BG, 0, 0);
		loadTexture(tex_Img_Select_Map_Bg);
		spr_Img_Select_Map_Bg = new Sprite(0, 0, Config.GAME_SCREEN_WIDTH, Config.GAME_SCREEN_HEIGHT, reg_Img_Select_Map_Bg);
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
	
	public static void loadSelectMapIconMap()
	{
//		tex_Img_Select_Map_Icon_Map = new BitmapTextureAtlas[IMG_INGAME_BACKGROUND_MAP.length];
//		reg_Img_Select_Map_Icon_Map = new TextureRegion[IMG_INGAME_BACKGROUND_MAP.length];
//		spr_Img_Select_Map_Icon_Map = new Sprite[IMG_INGAME_BACKGROUND_MAP.length];
	}
	
	public static void loadSelectMcBg()
	{
		
	}
	
	public static void loadSelectMcIcon()
	{
//		tex_Img_Select_Mc_Icon_Mc = new BitmapTextureAtlas[SPR_ICON_MC[map].length];
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