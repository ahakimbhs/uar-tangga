package com.amikomgamedev.ulartangga;

public interface Data {
	public static final String FONT_FILE_LOCATION = "fnt/Droid.ttf";
	public static final int FONT_SIZE_SMALL		= 0;
	public static final int FONT_SIZE_MEDIUM	= 1;
	public static final int FONT_SIZE_BIG		= 2;
	public static final int FONT_SIZE[] = 
		{
			12,
			24,
			36
		};
	
	public static final String IMG_LOGO_LOCATION = "gfx/appopen/";
	public static final String IMG_INGAME_FOLDER_LOCATION = "gfx/ingame/img/";
	public static final String SPR_INGAME_FOLDER_LOCATION = "gfx/ingame/spr/";
	public static final String IMG_INMENU_FOLDER_LOCATION = "gfx/inmenu/img/";
	public static final String SPR_INMENU_FOLDER_LOCATION = "gfx/inmenu/spr/";
	public static final String SND_INGAME_FOLDER_LOCATION = "snd/ingame/";
	public static final String SND_INMENU_FOLDER_LOCATION = "snd/menu/";
	
	//arief
	public static final String MNU_FOLDER_LOCATION = "gfx/mnu/";
	
	public static final String IMG_MENU_BACK = 
			MNU_FOLDER_LOCATION + "background.png";
	public static final String IMG_MENU_BTN_CREDIT =
			MNU_FOLDER_LOCATION + "btn_credits.png";
	public static final String IMG_MENU_BTN_OPTION = 
			MNU_FOLDER_LOCATION + "btn_option.png";
	public static final String IMG_MNU_TITLE =
			MNU_FOLDER_LOCATION + "logo.png";
	public static final String IMG_MENU_BTN_PLAY =
			MNU_FOLDER_LOCATION + "btn_play.png";
	public static final String IMG_SPR_DADU = 
			SPR_INGAME_FOLDER_LOCATION + "sprite_dadu.png" ;
	//
	
	public static final String BGM_INMENU = SND_INMENU_FOLDER_LOCATION + "msc_menu.ogg";
	
	public static final String[] BGM_INGAME_GAMEPLAY = 
		{
			SND_INGAME_FOLDER_LOCATION + "msc_ingame_bg_modern.ogg",
			SND_INGAME_FOLDER_LOCATION + "msc_ingame_bg_modern.ogg",
			SND_INGAME_FOLDER_LOCATION + "msc_ingame_bg_modern.ogg",
			SND_INGAME_FOLDER_LOCATION + "msc_ingame_bg_modern.ogg"
		};

	public static final String IMG_LOGO =
			IMG_LOGO_LOCATION + "agd_logo.png";
	
	public static final String IMG_LOADING =
			IMG_LOGO_LOCATION + "loading.jpg";

	public static final String IMG_INMENU_SELECT_MAP_BG = IMG_INMENU_FOLDER_LOCATION + "img_select_map_bg.jpg";
	public static final String IMG_INMENU_SELECT_MC_BG = IMG_INMENU_FOLDER_LOCATION + "img_select_mc_bg.jpg";
	
	public static final String IMG_INGAME_BACKGROUND_MAP[] =
		{
			IMG_INGAME_FOLDER_LOCATION + "img_bg_gameplay_map1.jpg",
			IMG_INGAME_FOLDER_LOCATION + "img_bg_gameplay_modern.jpg",
			IMG_INGAME_FOLDER_LOCATION + "img_bg_gameplay_klasik.jpg",
			IMG_INGAME_FOLDER_LOCATION + "img_bg_gameplay_galaksi.jpg",
			IMG_INGAME_FOLDER_LOCATION + "img_bg_gameplay_kolosal.jpg"
		};
	
	public static final String IMG_INGAME_BOTTON_DICE =
			IMG_INGAME_FOLDER_LOCATION + "img_game_botton_dice.jpg";
	
	public static final String IMG_INGAME_FOOTER =
			IMG_INGAME_FOLDER_LOCATION + "footer.jpg";
	
	public static final String IMG_INGAME_HEADER =
			IMG_INGAME_FOLDER_LOCATION + "header.jpg";
	
	// texture region main caracter
	public static final int TREG_SPR_MC_WIDTH	= 2048;
	public static final int TREG_SPR_MC_HEIGHT 	= 256;

	public static final int ANI_FRAME_START	= 0;
	public static final int ANI_FRAME_END	= 1;
	
	public static final int SPR_MC_COLUMN	= 6;
	public static final int SPR_MC_ROW		= 1;
	
	public static final int [][] SPR_MC_ANIM_FRAME =
		{
			{0,	1},		// IDLE0
			{0,	5}		// MOVE
		};
	
	public static final long[][] SPR_MC_ANIM_SPEED =
		{
			{100,	100},			// IDLE
			{100,	100,	100,	100,	100,	100}	// MOVE
		};
	
	public static final String SPR_MC[][] =
		{
			{
				SPR_INGAME_FOLDER_LOCATION + "kucing.png",
				SPR_INGAME_FOLDER_LOCATION + "anjing.png",
				SPR_INGAME_FOLDER_LOCATION + "anjing 2.png",
				SPR_INGAME_FOLDER_LOCATION + "anjing 3.png"
			},
			{
				SPR_INGAME_FOLDER_LOCATION + "kit_modern.png",
				SPR_INGAME_FOLDER_LOCATION + "pea_modern.png",
				SPR_INGAME_FOLDER_LOCATION + "dew_modern.png",
				SPR_INGAME_FOLDER_LOCATION + "row_modern.png"
			},
			{
				SPR_INGAME_FOLDER_LOCATION + "kit_klasik.png",
				SPR_INGAME_FOLDER_LOCATION + "pea_klasik.png",
				SPR_INGAME_FOLDER_LOCATION + "dew_klasik.png",
				SPR_INGAME_FOLDER_LOCATION + "row_klasik.png"
			},
			{
				SPR_INGAME_FOLDER_LOCATION + "kit_galaksi.png",
				SPR_INGAME_FOLDER_LOCATION + "pea_galaksi.png",
				SPR_INGAME_FOLDER_LOCATION + "dew_galaksi.png",
				SPR_INGAME_FOLDER_LOCATION + "row_galaksi.png"
			},
			{
				SPR_INGAME_FOLDER_LOCATION + "kit_kolosal.png",
				SPR_INGAME_FOLDER_LOCATION + "pea_kolosal.png",
				SPR_INGAME_FOLDER_LOCATION + "dew_kolosal.png",
				SPR_INGAME_FOLDER_LOCATION + "row_kolosal.png"
			}
		};
	
	public static final String SPR_ICON_MC[][] =
		{
			{
				IMG_INGAME_FOLDER_LOCATION + "kucing.png",
				IMG_INGAME_FOLDER_LOCATION + "anjing.png",
				IMG_INGAME_FOLDER_LOCATION + "kucing.png",
				IMG_INGAME_FOLDER_LOCATION + "kucing.png"
			},
			{
				IMG_INGAME_FOLDER_LOCATION + "kucing.png",
				IMG_INGAME_FOLDER_LOCATION + "anjing.png",
				IMG_INGAME_FOLDER_LOCATION + "kucing.png",
				IMG_INGAME_FOLDER_LOCATION + "kucing.png"
			},
			{
				IMG_INGAME_FOLDER_LOCATION + "kit_modern.png",
				IMG_INGAME_FOLDER_LOCATION + "pea_modern.png",
				IMG_INGAME_FOLDER_LOCATION + "dew_modern.png",
				IMG_INGAME_FOLDER_LOCATION + "row_modern.png"
			},
			{
				IMG_INGAME_FOLDER_LOCATION + "kit_klasik.png",
				IMG_INGAME_FOLDER_LOCATION + "pea_klasik.png",
				IMG_INGAME_FOLDER_LOCATION + "dew_klasik.png",
				IMG_INGAME_FOLDER_LOCATION + "row_klasik.png"
			},
			{
				IMG_INGAME_FOLDER_LOCATION + "kit_galaksi.png",
				IMG_INGAME_FOLDER_LOCATION + "pea_galaksi.png",
				IMG_INGAME_FOLDER_LOCATION + "dew_galaksi.png",
				IMG_INGAME_FOLDER_LOCATION + "row_galaksi.png"
			},
			{
				IMG_INGAME_FOLDER_LOCATION + "kit_kolosal.png",
				IMG_INGAME_FOLDER_LOCATION + "pea_kolosal.png",
				IMG_INGAME_FOLDER_LOCATION + "dew_kolosal.png",
				IMG_INGAME_FOLDER_LOCATION + "row_kolosal.png"
			}
		};
}
