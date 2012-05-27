package com.amikomgamedev.ulartangga;

import org.anddev.andengine.engine.camera.hud.HUD;
import org.anddev.andengine.entity.sprite.Sprite;
import org.anddev.andengine.entity.text.ChangeableText;
import org.anddev.andengine.opengl.font.Font;
import org.anddev.andengine.opengl.font.FontFactory;
import org.anddev.andengine.opengl.texture.Texture;
import org.anddev.andengine.opengl.texture.TextureOptions;
import org.anddev.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlas;
import org.anddev.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlasTextureRegionFactory;
import org.anddev.andengine.opengl.texture.region.TextureRegion;
import org.anddev.andengine.ui.activity.BaseGameActivity;

import android.R.color;
import android.content.Context;

import com.amikomgamedev.ulartangga.states.State_Gameplay;

public class Game implements Data,
							 Define
{
	private static BaseGameActivity activity;

	private static BitmapTextureAtlas[] tex_Font;
	public static Font[] font;
	public static ChangeableText[] change_Text;
	
	private static BitmapTextureAtlas tex_Img_Logo;
	private static TextureRegion reg_Img_Logo;
	public static Sprite spr_Img_Logo;
	
	private static BitmapTextureAtlas tex_Img_Map;
	public static TextureRegion reg_Img_Map;
	public static Sprite spr_Img_Map;
	
	private static BitmapTextureAtlas tex_MC[];
//	public static TiledTextureRegion reg_MC;
//	public static AnimatedSprite spr_MC;
	public static TextureRegion reg_MC[];
	public static Sprite spr_MC[];
	
	private static BitmapTextureAtlas tex_Icon_MC[];
//	public static TiledTextureRegion reg_MC;
//	public static AnimatedSprite spr_MC;
	public static TextureRegion reg_Icon_MC[];
	public static Sprite spr_Icon_MC[];
	
	private static BitmapTextureAtlas tex_Img_Botton_Dice;
	public static TextureRegion reg_Img_Botton_Dice;
	public static Sprite spr_Img_Botton_Dice;
	
	private static BitmapTextureAtlas tex_Img_Informasi;
	public static TextureRegion reg_Img_Informasi_Footer,
								reg_Img_Informasi_Header;
	public static Sprite spr_Img_Informasi_Footer,
						 spr_Img_Informasi_Header;
	
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
		tex_Img_Logo = new BitmapTextureAtlas(512, 512, TextureOptions.BILINEAR_PREMULTIPLYALPHA);
		reg_Img_Logo = BitmapTextureAtlasTextureRegionFactory.
				createFromAsset(tex_Img_Logo, activity, IMG_LOGO, 0, 0);
		loadTexture(tex_Img_Logo);
		
		float centerX = (Config.GAME_SCREEN_WIDTH - reg_Img_Logo.getWidth()) / 2;
		float centerY = (Config.GAME_SCREEN_HEIGHT - reg_Img_Logo.getHeight()) / 2;
		spr_Img_Logo = new Sprite(centerX, centerY, reg_Img_Logo);
	}
	
	public static void loadHud()
	{
		hud = new HUD();
		State_Gameplay.mCamera.setHUD(hud);
	}
	
	public static void loadFont()
	{
		tex_Font = new BitmapTextureAtlas[FONT_SIZE.length];
		font = new Font[FONT_SIZE.length];
		change_Text = new ChangeableText[FONT_SIZE.length];
		
		for(int i = 0; i < FONT_SIZE.length; i++)
		{
			tex_Font[i] = new BitmapTextureAtlas(256, 256, TextureOptions.BILINEAR_PREMULTIPLYALPHA);
			font[i] = FontFactory.createFromAsset(tex_Font[i], activity, FONT_FILE_SLOCATION, 
					FONT_SIZE[i], true, color.black);
			
			activity.getEngine().getFontManager().loadFont(font[i]);
			loadTexture(tex_Font[i]);
		}
	}
	
	public static void loadGameMap(int pMap)
	{
		int map = pMap - 1;
		tex_Img_Map = new BitmapTextureAtlas(1024, 1024, TextureOptions.BILINEAR_PREMULTIPLYALPHA);
		reg_Img_Map = BitmapTextureAtlasTextureRegionFactory.
				createFromAsset(tex_Img_Map, activity, IMG_INGAME_BACKGROUND_MAP[map], 0, 0);
		loadTexture(tex_Img_Map);

		float posX = 0;
		float posY = Config.GAME_SCREEN_HEIGHT - reg_Img_Map.getHeight() - reg_Img_Informasi_Footer.getHeight();
		spr_Img_Map = new Sprite(posX, posY, reg_Img_Map);
	}
	
//	public static void loadMC()
//	{
//		tex_MC = new BitmapTextureAtlas(128, 256, TextureOptions.BILINEAR_PREMULTIPLYALPHA);
//		reg_MC = BitmapTextureAtlasTextureRegionFactory.
//				createTiledFromAsset(tex_MC, activity, SPR_MC, 0, 0, 5, 5);
//		loadTexture(tex_MC);
//		
//		float posX = (Config.GAME_MAP_CELL_WIDTH - reg_MC.getTileWidth()) / 2;
//		float posY = Config.GAME_SCREEN_HEIGHT - Config.GAME_MAP_CELL_HEIGHT + 
//				((Config.GAME_MAP_CELL_HEIGHT - reg_MC.getTileHeight()) / 2) - reg_Img_Informasi_Footer.getHeight();
//		spr_MC = new AnimatedSprite(posX, posY, reg_MC);
//	}
	
	public static void loadMC()
	{
		tex_MC = new BitmapTextureAtlas[SPR_MC.length];
		reg_MC = new TextureRegion[SPR_MC.length];
		spr_MC = new Sprite[SPR_MC.length];
		
		for(int i = 0; i < SPR_MC.length; i++)
		{
			tex_MC[i] = new BitmapTextureAtlas(64, 64, TextureOptions.BILINEAR_PREMULTIPLYALPHA);
			reg_MC[i] = BitmapTextureAtlasTextureRegionFactory.
					createFromAsset(tex_MC[i], activity, SPR_MC[i], 0, 0);
			loadTexture(tex_MC[i]);
			
			float posX = 0;//(-GAME_MAP_CELL_WIDTH);
			float posY = Config.GAME_SCREEN_HEIGHT - GAME_MAP_CELL_HEIGHT + 
					((GAME_MAP_CELL_HEIGHT - reg_MC[i].getHeight()) / 2) - reg_Img_Informasi_Footer.getHeight();
			spr_MC[i] = new Sprite(posX, posY, reg_MC[i]);
		}
	}
	
	public static void loadIconMC()
	{
		tex_Icon_MC = new BitmapTextureAtlas[SPR_ICON_MC.length];
		reg_Icon_MC = new TextureRegion[SPR_ICON_MC.length];
		spr_Icon_MC = new Sprite[SPR_ICON_MC.length];

		int posX = 5;
		
		for(int i = 0; i < SPR_ICON_MC.length; i++)
		{
			tex_Icon_MC[i] = new BitmapTextureAtlas(32, 32, TextureOptions.BILINEAR_PREMULTIPLYALPHA);
			reg_Icon_MC[i] = BitmapTextureAtlasTextureRegionFactory.
					createFromAsset(tex_Icon_MC[i], activity, SPR_ICON_MC[i], 0, 0);
			loadTexture(tex_Icon_MC[i]);
			
			int posY = (reg_Img_Informasi_Header.getHeight() - reg_Icon_MC[i].getHeight()) / 2;
			
			spr_Icon_MC[i] = new Sprite(posX, posY, reg_Icon_MC[i]);

			posX += GAME_MAP_CELL_WIDTH;
		}
	}
	
	public static void loadGameBotton()
	{
		tex_Img_Botton_Dice = new BitmapTextureAtlas(64, 64, TextureOptions.BILINEAR_PREMULTIPLYALPHA);
		reg_Img_Botton_Dice = BitmapTextureAtlasTextureRegionFactory.
				createFromAsset(tex_Img_Botton_Dice, activity, IMG_INGAME_BOTTON_DICE, 0, 0);
		loadTexture(tex_Img_Botton_Dice);

		dicePosX = Config.GAME_SCREEN_WIDTH - 20 - reg_Img_Botton_Dice.getWidth();
		dicePosY = Config.GAME_SCREEN_HEIGHT - 20 - 
				reg_Img_Botton_Dice.getHeight() - reg_Img_Informasi_Footer.getHeight();
	}
	
	public static void loadGameInformasi()
	{
		tex_Img_Informasi = new BitmapTextureAtlas(512, 128, TextureOptions.BILINEAR_PREMULTIPLYALPHA);
		reg_Img_Informasi_Footer = BitmapTextureAtlasTextureRegionFactory.
				createFromAsset(tex_Img_Informasi, activity, IMG_INGAME_FOOTER, 0, 0);
		reg_Img_Informasi_Header = BitmapTextureAtlasTextureRegionFactory.
				createFromAsset(tex_Img_Informasi, activity, IMG_INGAME_HEADER, 0, 20);
		loadTexture(tex_Img_Informasi);
		
		float posX = 0;
		float posY = Config.GAME_SCREEN_HEIGHT - reg_Img_Informasi_Footer.getHeight();
		spr_Img_Informasi_Footer = new Sprite(posX, posY, reg_Img_Informasi_Footer);
		
		posX = 0;
		posY = 0;
		spr_Img_Informasi_Header = new Sprite(posX, posY, reg_Img_Informasi_Header);
	}
}
