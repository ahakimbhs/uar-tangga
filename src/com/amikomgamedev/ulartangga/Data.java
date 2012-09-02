package com.amikomgamedev.ulartangga;

public interface Data {
	public static final String FONT_FILE_LOCATION = "fnt/cinnamon cake.ttf";
	public static final int FONT_SIZE_SMALL		= 0;
	public static final int FONT_SIZE_MEDIUM	= 1;
	public static final int FONT_SIZE_BIG		= 2;
	public static final int FONT_SIZE[] = 
		{
			12,
			24,
			36,
			16,
			20
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
	public static final String IMG_BG_CREDIT = 
			MNU_FOLDER_LOCATION + "bg_credit.png";
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
	
	public static final String IMG_BG_NOTIF =
			MNU_FOLDER_LOCATION + "BACKGROUD SYMBOL.png";
	public static final String IMG_BTN_NO =
			MNU_FOLDER_LOCATION + "NO BUTTON.png";
	public static final String IMG_BTN_YES =
			MNU_FOLDER_LOCATION + "YES BUTTON.png";
	public static final String IMG_BG_TEXT =
			MNU_FOLDER_LOCATION + "ARE YOU SURE.png";
	
	public static final String IMG_BG_OPTION =
			MNU_FOLDER_LOCATION +"bg_option.png";
	public static final String IMG_BTN_SOUND_ON =
			MNU_FOLDER_LOCATION +"btn_sound_on.png";
	public static final String IMG_BTN_SOUND_OFF =
			MNU_FOLDER_LOCATION +"btn_sound_off.png";
	public static final String IMG_BTN_MUSIC_ON =
			MNU_FOLDER_LOCATION +"btn_music_on.png";
	public static final String IMG_BTN_MUSIC_OFF =
			MNU_FOLDER_LOCATION +"btn_music_off.png";
	public static final String IMG_BG_BLACK = 
			MNU_FOLDER_LOCATION +"bg_black.png";
	
	public static final String[] MENU_MAIN_TAMBAHAN = 
		{
			IMG_INMENU_FOLDER_LOCATION + "TANGGA 1.png",
			IMG_INMENU_FOLDER_LOCATION + "TANGGA 2.png",
			IMG_INMENU_FOLDER_LOCATION + "TANGGA 3.png",
			IMG_INMENU_FOLDER_LOCATION + "ULAR 1.png",
			IMG_INMENU_FOLDER_LOCATION + "ULAR 2.png",
			IMG_INMENU_FOLDER_LOCATION + "ULAR 3.png",
		};
	
	public static final float[] MENU_MAIN_TAMBAHAN_DUR = 
		{
			1f, 1f, 1f, 
			1f, 1f, 1f, 
		};
	
	public static final float[] MENU_MAIN_TAMBAHAN_FROM_X = 
		{
			10, 90, 220,
			30, 60, 200
		};
	
	public static final float[] MENU_MAIN_TAMBAHAN_TO_X = 
		{
			10, 90, 220,
			30, 60, 200
		};
	
	public static final float[] MENU_MAIN_TAMBAHAN_FROM_Y = 
		{
			-100, 480, 480,
			-100, 480, 480
		};
	
	public static final float[] MENU_MAIN_TAMBAHAN_TO_Y = 
		{
			480, -100, -100,
			480, -100, -100,
		};
	
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

	public static final String MENU_SELECT_MAP_BG			= MNU_FOLDER_LOCATION + "background.png";
	public static final String MENU_SELECT_MAP_BG_SELECT	= IMG_INMENU_FOLDER_LOCATION + "bg.png";
	public static final String MENU_SELECT_MAP_BTN_BACK		= IMG_INMENU_FOLDER_LOCATION + "btnArrow.png";

	public static final String MENU_SELECT_MC_BG		= MNU_FOLDER_LOCATION + "background.png";
	public static final String MENU_SELECT_MC_ICON_BG	= IMG_INMENU_FOLDER_LOCATION + "Select_Mc_Bg_Icon_Mc.jpg";
	public static final String MENU_SELECT_MC_BTN_ADD	= IMG_INMENU_FOLDER_LOCATION + "ADD PLAYER.png";
	public static final String MENU_SELECT_MC_BTN_DELETE= IMG_INMENU_FOLDER_LOCATION + "btnHapus.png";
	public static final String MENU_SELECT_MC_BTN_ARROW	= IMG_INMENU_FOLDER_LOCATION + "btnArrow.png";
	public static final String MENU_SELECT_MC_BTN_ARROW_MC	= IMG_INMENU_FOLDER_LOCATION + "btnArrowMc.png";
	public static final String[] MENU_SELECT_MC_BTN_TYPE	= 
		{
			IMG_INMENU_FOLDER_LOCATION + "PLAYER.png",
			IMG_INMENU_FOLDER_LOCATION + "AI_COM.png",
		};
	
	public static final String IMG_INGAME_BACKGROUND_MAP[] =
		{
			IMG_INGAME_FOLDER_LOCATION + "img_bg_gameplay_klasik.png",
			IMG_INGAME_FOLDER_LOCATION + "img_bg_gameplay_modern.png",
			IMG_INGAME_FOLDER_LOCATION + "img_bg_gameplay_galaksi.png",
			IMG_INGAME_FOLDER_LOCATION + "img_bg_gameplay_kolosal.png"
		};
	public static final String SELECT_MAP_ICON[] =
		{
			IMG_INMENU_FOLDER_LOCATION + "CLASSIC_.png",
			IMG_INMENU_FOLDER_LOCATION + "MODERN_.png",
			IMG_INMENU_FOLDER_LOCATION + "GALAXI_.png",
			IMG_INMENU_FOLDER_LOCATION + "COLOSAL_.png"
		};

	public static final String[] IMG_INGAME_BUTTON_DICE = 
			{
				IMG_INGAME_FOLDER_LOCATION + "slide bg.jpg",
				IMG_INGAME_FOLDER_LOCATION + "slide.jpg",
			};
	public static final String IMG_INGAME_BUTTON_SLOT_MACHINE = IMG_INGAME_FOLDER_LOCATION + "slot_machine.png";
	public static final String IMG_INGAME_BUTTON_PAUSE = IMG_INGAME_FOLDER_LOCATION + "pause.png";
	public static final String IMG_INGAME_BOTTON_SLOT_MACHINE_NUMBER = IMG_INGAME_FOLDER_LOCATION + "NUMBER OF SLOT MACHINE.png";

	public static final int SPR_BOTTON_DICE_COLUMN	= 2;
	public static final int SPR_BOTTON_DICE_ROW		= 1;
	
	public static final String IMG_INGAME_FOOTER = IMG_INGAME_FOLDER_LOCATION + "BACKGROUND TEXT DOWN.png";
	public static final String IMG_INGAME_HEADER = IMG_INGAME_FOLDER_LOCATION + "BACKGROUND TEXT UP.png";
	
	public static final int [][] SPR_MC_ANIM_FRAME =
		{
			{6,	8},		// IDLE
			{0,	5}		// MOVE
		};
	public static final long[][] SPR_MC_ANIM_SPEED =
		{
			{250,	250,	250},			// IDLE
			{100,	100,	100,	100,	100,	100}	// MOVE
		};
	
	public static final int [][] SPR_DICE_FRAME =
		{
			{
				0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14
			},
			{
				0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 15
			},
			{
				0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 16
			},
			{
				0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 17
			},
			{
				0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 18
			},
			{
				0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 19
			},
		};
	public static final long[] SPR_DICE_SPEED =
		{
			100,	100,	100,	100,	100,
			100,	100,	100,	100,	100,
			100,	100,	100,	100,	100,
		};
	
	public static final String SPR_MC[] =
		{
			SPR_INGAME_FOLDER_LOCATION + "kit.png",
			SPR_INGAME_FOLDER_LOCATION + "pea.png",
			SPR_INGAME_FOLDER_LOCATION + "dew.png",
			SPR_INGAME_FOLDER_LOCATION + "roo.png"
		};
	
	public static final String SPR_ICON_MC[] =
		{
			IMG_INGAME_FOLDER_LOCATION + "ICON KIT.png",
			IMG_INGAME_FOLDER_LOCATION + "ICON PEA.png",
			IMG_INGAME_FOLDER_LOCATION + "ICON DEW.png",
			IMG_INGAME_FOLDER_LOCATION + "ICON ROO.png"	
		};
	
	public static final String SELECT_MC_ICON[] =
		{
			IMG_INMENU_FOLDER_LOCATION + "CHOOSE CHARACTER KIT.png",
			IMG_INMENU_FOLDER_LOCATION + "CHOOSE CHARACTER PEA.png",
			IMG_INMENU_FOLDER_LOCATION + "CHOOSE CHARACTER DEW.png",
			IMG_INMENU_FOLDER_LOCATION + "CHOOSE CHARACTER ROO.png"
		};

	public static final String SPR_GAMEPLAY_DICE =
			SPR_INGAME_FOLDER_LOCATION + "dadu.png";
	
	public static final String GAMEPLAY_SMOKE = SPR_INGAME_FOLDER_LOCATION + "BERANTEM.png";
	
	public static final int[] GAMEPLAY_SMOKE_ANIM_FRAME = {0, 3};
	public static final long[] GAMEPLAY_SMOKE_ANIM_SPEED = {150, 150, 150, 150};
	
	public static final String		GAMEPAUSE_BG	= IMG_INGAME_FOLDER_LOCATION + "PAUSE BACKGROUND.png";
	public static final String[]	GAMEPAUSE_BTN	= 
		{
			IMG_INGAME_FOLDER_LOCATION + "PLAY BUTTON.png",
			IMG_INGAME_FOLDER_LOCATION + "SETTING BUTTON.png",
			IMG_INGAME_FOLDER_LOCATION + "HOME BUTTON.png",
			IMG_INGAME_FOLDER_LOCATION + "RESTART BUTTON.png",
		};
	
	public static final String[] GAMEPAUSE_MC_IDLE =
		{
			SPR_INGAME_FOLDER_LOCATION + "kit.png",
			SPR_INGAME_FOLDER_LOCATION + "pea.png",
			SPR_INGAME_FOLDER_LOCATION + "dew.png",
			SPR_INGAME_FOLDER_LOCATION + "roo.png"
		};
	
	public static final int[]	GAMEPAUSE_MC_IDLE_ANIM_FRAME = {6, 8};
	public static final long[]	GAMEPAUSE_MC_IDLE_ANIM_SPEED = {250, 250, 250};
	
	public static final String GAMEOVER_BG				= IMG_INGAME_FOLDER_LOCATION + "PAUSE BACKGROUND.png";
	public static final String GAMEOVER_BTN_MAINMENU	= IMG_INGAME_FOLDER_LOCATION + "HOME BUTTON.png";
	public static final String GAMEOVER_BTN_RESTART		= IMG_INGAME_FOLDER_LOCATION + "RESTART BUTTON.png";
	
	public static final String[] GAMEOVER_MC_WIN = 
		{
			SPR_INGAME_FOLDER_LOCATION + "KIT WON.png",
			SPR_INGAME_FOLDER_LOCATION + "PEA WON.png",
			SPR_INGAME_FOLDER_LOCATION + "DEW WON.png",
			SPR_INGAME_FOLDER_LOCATION + "ROO WON.png"
		};
	
	public static final String[] GAMEOVER_TEXT = 
		{
			IMG_INGAME_FOLDER_LOCATION + "PLAYER 1 WIN.png",
			IMG_INGAME_FOLDER_LOCATION + "PLAYER 2 WIN.png",
			IMG_INGAME_FOLDER_LOCATION + "PLAYER 3 WIN.png",
			IMG_INGAME_FOLDER_LOCATION + "PLAYER 4 WIN.png",
		};
	
	
	
	public static final int [][] GAMEOVER_MC_ANIM_FRAME =
		{
			{0,	7},		// WIN
			{0,	7}		// LOSE
		};
	public static final long[][] GAMEOVER_MC_ANIM_SPEED =
		{
			{150,	150,	150,	150,	150,	150,	150,	150},	// WIN
			{150,	150,	150,	150,	150,	150,	150,	150}	// LOSE
		};
	
	// texture region main caracter
	public static final int TREG_SPR_MC_WIDTH	= 512;
	public static final int TREG_SPR_MC_HEIGHT 	= 512;
	public static final int MC_WIDTH 	= 60;
	public static final int MC_HEIGHT 	= 60;

	public static final int MC_ICON_TEX_WIDTH 	= 32;
	public static final int MC_ICON_TEX_HEIGHT 	= 32;
	public static final int MC_ICON_WIDTH 	= 32;
	public static final int MC_ICON_HEIGHT 	= 32;

	public static final int ANI_FRAME_START	= 0;
	public static final int ANI_FRAME_END	= 1;
	
	public static final int SPR_MC_COLUMN	= 4;
	public static final int SPR_MC_ROW		= 3;

	public static final int SPR_DICE_TEX_WIDTH	= 512;
	public static final int SPR_DICE_TEX_HEIGHT	= 512;
	public static final int SPR_DICE_WIDTH 		= 90;
	public static final int SPR_DICE_HEIGHT 	= 90;
	public static final int SPR_DICE_COLUMN		= 4;
	public static final int SPR_DICE_ROW		= 5;
	
	public static final int MAP_TEX_WIDTH 	= 1024;
	public static final int MAP_TEX_HEIGHT 	= 1024;
	public static final int MAP_WIDTH 		= 700;
	public static final int MAP_HEIGHT 		= 700;
	
	public static final int MENU_SELECT_MAP_MAP_ICON_WIDTH 	= 250;
	public static final int MENU_SELECT_MAP_MAP_ICON_HEIGHT = 250;

	public static final int MENU_SELECT_MAP_BTN_BACK_TEX_WIDTH	= 128;
	public static final int MENU_SELECT_MAP_BTN_BACK_TEX_HEIGHT	= 32;
	public static final int MENU_SELECT_MAP_BTN_BACK_WIDTH	= 70;
	public static final int MENU_SELECT_MAP_BTN_BACK_HEIGHT	= 30;

	public static final int MENU_SELECT_MC_ICON_BG_TEX_WIDTH	= 128;
	public static final int MENU_SELECT_MC_ICON_BG_TEX_HEIGHT	= 128;
	public static final int MENU_SELECT_MC_ICON_BG_WIDTH		= 90;
	public static final int MENU_SELECT_MC_ICON_BG_HEIGHT		= 100;

	public static final int MENU_SELECT_MC_ICON_TEX_WIDTH	= 128;
	public static final int MENU_SELECT_MC_ICON_TEX_HEIGHT	= 128;
	public static final int MENU_SELECT_MC_ICON_WIDTH		= 90;
	public static final int MENU_SELECT_MC_ICON_HEIGHT		= 100;

	public static final int MENU_SELECT_MC_BTN_ADD_TEX_WIDTH	= 256;
	public static final int MENU_SELECT_MC_BTN_ADD_TEX_HEIGHT	= 64;
	public static final int MENU_SELECT_MC_BTN_ADD_WIDTH		= 150;
	public static final int MENU_SELECT_MC_BTN_ADD_HEIGHT		= 50;

	public static final int MENU_SELECT_MC_BTN_DELETE_TEX_WIDTH		= 64;
	public static final int MENU_SELECT_MC_BTN_DELETE_TEX_HEIGHT	= 64;
	public static final int MENU_SELECT_MC_BTN_DELETE_WIDTH			= 30;
	public static final int MENU_SELECT_MC_BTN_DELETE_HEIGHT		= 30;

	public static final int MENU_SELECT_MC_BTN_TYPE_TEX_WIDTH	= 128;
	public static final int MENU_SELECT_MC_BTN_TYPE_TEX_HEIGHT	= 64;
	public static final int MENU_SELECT_MC_BTN_TYPE_WIDTH		= 90;
	public static final int MENU_SELECT_MC_BTN_TYPE_HEIGHT		= 20;
	public static final int MENU_SELECT_MC_BTN_TYPE_COLUMN		= 1;
	public static final int MENU_SELECT_MC_BTN_TYPE_ROW			= 2;

	public static final int MENU_SELECT_MC_BTN_ARROW_TEX_WIDTH	= 128;
	public static final int MENU_SELECT_MC_BTN_ARROW_TEX_HEIGHT	= 64;
	public static final int MENU_SELECT_MC_BTN_ARROW_WIDTH		= 70;
	public static final int MENU_SELECT_MC_BTN_ARROW_HEIGHT		= 30;
	public static final int MENU_SELECT_MC_BTN_ARROW_MC_WIDTH	= 30;
	public static final int MENU_SELECT_MC_BTN_ARROW_MC_HEIGHT	= 30;
	
	public static final int GAMEPLAY_SMOKE_TEX_WIDTH 	= 1024;
	public static final int GAMEPLAY_SMOKE_TEX_HEIGHT 	= 256;
	public static final int GAMEPLAY_SMOKE_WIDTH 		= 153;
	public static final int GAMEPLAY_SMOKE_HEIGHT 		= 140;
	public static final int GAMEPLAY_SMOKE_COLUMN		= 4;
	public static final int GAMEPLAY_SMOKE_ROW			= 1;
	
	public static final int GAMEPAUSE_BG_TEX_WIDTH 	= 512;
	public static final int GAMEPAUSE_BG_TEX_HEIGHT = 256;
	public static final int GAMEPAUSE_BG_WIDTH 		= 305;
	public static final int GAMEPAUSE_BG_HEIGHT 	= 175;
	
	public static final int GAMEPAUSE_MC_TEX_WIDTH 		= 512;
	public static final int GAMEPAUSE_MC_TEX_HEIGHT 	= 512;
	public static final int GAMEPAUSE_MC_IDLE_WIDTH 	= 90;
	public static final int GAMEPAUSE_MC_IDLE_HEIGHT 	= 90;
	public static final int GAMEPAUSE_MC_IDLE_COLUMN 	= 4;
	public static final int GAMEPAUSE_MC_IDLE_ROW	 	= 3;
	public static final int GAMEPAUSE_MC_ICON_WIDTH 	= 35;
	public static final int GAMEPAUSE_MC_ICON_HEIGHT 	= 35;

	public static final int GAMEPAUSE_BTN_TEX_WIDTH			= 64;
	public static final int GAMEPAUSE_BTN_TEX_HEIGHT		= 64;
	public static final int GAMEPAUSE_BTN_WIDTH				= 40;
	public static final int GAMEPAUSE_BTN_HEIGHT			= 40;

	public static final int GAMEOVER_BG_TEX_WIDTH 	= 512;
	public static final int GAMEOVER_BG_TEX_HEIGHT 	= 256;
	public static final int GAMEOVER_BG_WIDTH 		= 305;
	public static final int GAMEOVER_BG_HEIGHT 		= 175;

	public static final int GAMEOVER_TEXT_TEX_WIDTH 	= 256;
	public static final int GAMEOVER_TEXT_TEX_HEIGHT 	= 128;
	public static final int GAMEOVER_TEXT_WIDTH 		= 130;
	public static final int GAMEOVER_TEXT_HEIGHT 		= 90;
	
	public static final int GAMEOVER_MC_TEX_WIDTH 	= 1024;
	public static final int GAMEOVER_MC_TEX_HEIGHT 	= 256;
	public static final int GAMEOVER_MC_WIN_WIDTH	= 140;
	public static final int GAMEOVER_MC_WIN_HEIGHT	= 140;
	public static final int GAMEOVER_MC_LOSE_WIDTH 	= 35;
	public static final int GAMEOVER_MC_LOSE_HEIGHT = 35;
	public static final int GAMEOVER_MC_WIN_COLUMN	= 8;
	public static final int GAMEOVER_MC_WIN_ROW		= 1;

	public static final int GAMEOVER_BTN_TEX_WIDTH			= 128;
	public static final int GAMEOVER_BTN_TEX_HEIGHT			= 128;
	public static final int GAMEOVER_BTN_MAINMENU_WIDTH		= 40;
	public static final int GAMEOVER_BTN_MAINMENU_HEIGHT	= 40;
	public static final int GAMEOVER_BTN_RESTART_WIDTH		= 40;
	public static final int GAMEOVER_BTN_RESTART_HEIGHT		= 40;

	public static final int _TEX_WIDTH 	= 32;
	public static final int _TEX_HEIGHT 	= 32;
	public static final int _WIDTH 	= 32;
	public static final int _HEIGHT 	= 32;
	
	public static final int NOTIF_BG_HEIGHT		= 140;
	public static final int NOTIF_BG_WIDTH		= 300;
	
	public static final int NOTIF_BTN_WIDTH		= 64;
	public static final int NOTIF_BTN_HEIGHT	= 64;
	
	public static final int OPTION_BG_WIDTH		= 300;
	public static final int OPTION_BG_HEIGHT	= 200;
	
	public static final int OPTION_BTN_WIDTH	= 64;
	public static final int OPTION_BTN_HEIGHT	= 64;
	
	public static final String _TEX_PM = "Project Manager";
	public static final String _TEX_PRD = "Produser";
	public static final String _TEX_GD = "Game Designer";
	public static final String _TEX_PROG = "Programer";
	public static final String _TEX_ART = "Artist";
	public static final String _TEX_SE = "Sound Enginer";
	
	public static final String _TEX_AGS = "Agoes Tri Hariyanto";
	public static final String _TEX_AYU = "Ayu Aprilia";
	public static final String _TEX_MTY = "Mutia Rachmawati";
	public static final String _TEX_DWI = "Dwi Ariyanto";
	public static final String _TEX_ARF = "Arief Maffrudin An Nuur";
	public static final String _TEX_AMR = "Mochammad Amirul H";
	public static final String _TEX_VER = "Verdian Andiansyah";
	public static final String _TEX_FAN = "Moh. Rifandi Lihawa";
	public static final String _TEX_IRF = "Irvan D. Efendi";
}