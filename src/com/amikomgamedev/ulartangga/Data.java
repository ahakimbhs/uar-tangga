package com.amikomgamedev.ulartangga;

public interface Data {
	public static final String FONT_FILE_SLOCATION = "fnt/Droid.ttf";
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
	//arief
	public static final String MNU_FOLDER_LOCATION = "gfx/mnu/";
	
	public static final String IMG_MENU_BACK = 
			MNU_FOLDER_LOCATION + "background.png";
	public static final String IMG_MENU_BTN_CREDIT =
			MNU_FOLDER_LOCATION + "btn_credit.png";
	public static final String IMG_MENU_BTN_OPTION = 
			MNU_FOLDER_LOCATION + "btn_option.png";
	public static final String IMG_MNU_TITLE =
			MNU_FOLDER_LOCATION + "title.png";
	//
	public static final String IMG_LOGO =
			IMG_LOGO_LOCATION + "agd_logo.png";
	
	public static final String IMG_INGAME_BACKGROUND_MAP[] =
		{
		IMG_INGAME_FOLDER_LOCATION + "img_bg_gameplay_map1.jpg",
		IMG_INGAME_FOLDER_LOCATION + "img_bg_gameplay_map1.jpg"
		};
	
	public static final String IMG_INGAME_BOTTON_DICE =
			IMG_INGAME_FOLDER_LOCATION + "img_game_botton_dice.jpg";
	
	public static final String IMG_INGAME_FOOTER =
			IMG_INGAME_FOLDER_LOCATION + "footer.jpg";
	
	public static final String IMG_INGAME_HEADER =
			IMG_INGAME_FOLDER_LOCATION + "header.jpg";

	public static final int ANI_FRAME_START	= 0;
	public static final int ANI_FRAME_END		= 1;
	
	public static final int SPR_MC_COLUMN	= 5;
	public static final int SPR_MC_ROW			= 5;
	
	public static final String SPR_MC[] =
		{
		SPR_INGAME_FOLDER_LOCATION + "kucing.png",
		SPR_INGAME_FOLDER_LOCATION + "anjing.png",
		SPR_INGAME_FOLDER_LOCATION + "anjing 2.png",
		SPR_INGAME_FOLDER_LOCATION + "anjing 3.png"
		};
	
	public static final String SPR_ICON_MC[] =
		{
		IMG_INGAME_FOLDER_LOCATION + "kucing.png",
		IMG_INGAME_FOLDER_LOCATION + "anjing.png",
		IMG_INGAME_FOLDER_LOCATION + "kucing.png",
		IMG_INGAME_FOLDER_LOCATION + "kucing.png"
		};
}
