package com.amikomgamedev.ulartangga;

public class Loading {
	public static final int LOADING_TYPE_APP_OPEN		= 0;
	public static final int LOADING_TYPE_MAIN_MENU		= 1;
	public static final int LOADING_TYPE_GAMEPLAY_OPEN	= 2;
	public static final int LOADING_TYPE_GAMEPLAY		= 3;
	// << dwi
	public static final int LOADING_TYPE_MAIN_MENU_2		= 4;
	public static final int LOADING_TYPE_MAIN_MENU_OPEN		= 5;
	public static final int LOADING_TYPE_SELECT_MAP			= 6;
	public static final int LOADING_TYPE_SELECT_MC			= 7;
	// >>
	public static final int LOADING_TYPE_CREDIT				= 8;
	public static final int LOADING_TYPE_OPTION				= 9;
	
	public static int Loading_Type_Current				= LOADING_TYPE_APP_OPEN;

	private static final int LOADING_SUB_TYPE_LOGO						= 0;
	private static final int LOADING_SUB_TYPE_WALPAPER					= 1;
	private static final int LOADING_SUB_TYPE_MAIN_MENU_INTERFACE		= 2;
	private static final int LOADING_SUB_TYPE_MAIN_MENU_BUTTON			= 3;
	private static final int LOADING_SUB_TYPE_FONT						= 4;
	private static final int LOADING_SUB_TYPE_LOADING_INTERFACE			= 7;
	private static final int LOADING_SUB_TYPE_MC						= 8;
	private static final int LOADING_SUB_TYPE_MAP_BACKGROUND			= 9;
	private static final int LOADING_SUB_TYPE_GAME_BUTTON				= 10;
	private static final int LOADING_SUB_TYPE_FIELD_INFORMASI			= 11;
	private static final int LOADING_SUB_TYPE_SOUND_GAMEPLAY			= 12;
	private static final int LOADING_SUB_TYPE_HUD						= 13;
	private static final int LOADING_SUB_TYPE_ICON_MC					= 14;
	//dwi
	private static final int LOADING_SUB_TYPE_SELECT_MAP_BACKGROUND		= 15;
	private static final int LOADING_SUB_TYPE_SELECT_MAP_ICON_MAP		= 16;
	private static final int LOADING_SUB_TYPE_SELECT_MC_BACKGROUND		= 17;
	private static final int LOADING_SUB_TYPE_SELECT_MC_ICON_CARARACTER	= 18;
	private static final int LOADING_SUB_TYPE_SOUND_MENU				= 19;
	private static final int LOADING_SUB_TYPE_GAME_OVER					= 20;
	private static final int LOADING_SUB_TYPE_GAME_OVER_MC				= 21;
	private static final int LOADING_SUB_TYPE_GAME_PAUSE				= 22;
	//
	
	private static final int LOADING_SUB_TYPE_CREDIT_BACKGROUND			= 23; 
	private static final int LOADING_SUB_TYPE_OPTION_BACKGROUND			= 24;
	
	private static int Loading_Max_Progress;
	private static int Loading_Cur_Progress;
	
	private static int map;
	
	private static final int LOADING_TYPE_APP_OPEN_LIST_ITEM[] =
		{
			LOADING_SUB_TYPE_LOGO,
//			LOADING_SUB_TYPE_WALPAPER,
//			LOADING_SUB_TYPE_FONT
		};
	private static final int LOADING_TYPE_MAIN_MENU_OPEN_LIST_ITEM[] =
		{
			LOADING_SUB_TYPE_FONT,
			LOADING_SUB_TYPE_LOADING_INTERFACE,
		};
	private static final int LOADING_TYPE_MAIN_MENU_LIST_ITEM[] =
		{
			LOADING_SUB_TYPE_MAIN_MENU_INTERFACE,
			LOADING_SUB_TYPE_MAIN_MENU_BUTTON,
			LOADING_SUB_TYPE_FONT,
			LOADING_SUB_TYPE_SOUND_MENU
		};
	//dwi
	private static final int LOADING_TYPE_MAIN_MENU_2_LIST_ITEM[] =
		{
			LOADING_SUB_TYPE_MAIN_MENU_INTERFACE,
			LOADING_SUB_TYPE_MAIN_MENU_BUTTON,
//			LOADING_SUB_TYPE_SELECT_MAP_BACKGROUND,
//			LOADING_SUB_TYPE_SELECT_MAP_ICON_MAP,
//			LOADING_SUB_TYPE_SELECT_MC_BACKGROUND,
//			LOADING_SUB_TYPE_SELECT_MC_ICON_CARARACTER,
			LOADING_SUB_TYPE_FONT,
			LOADING_SUB_TYPE_SOUND_MENU
		};
	private static final int LOADING_TYPE_SELECT_MAP_LIST_ITEM[] =
		{
			LOADING_SUB_TYPE_SELECT_MAP_BACKGROUND,
			LOADING_SUB_TYPE_SELECT_MAP_ICON_MAP,
		};
	private static final int LOADING_TYPE_SELECT_MC_LIST_ITEM[] =
		{
			LOADING_SUB_TYPE_SELECT_MC_BACKGROUND,
			LOADING_SUB_TYPE_SELECT_MC_ICON_CARARACTER,
			LOADING_SUB_TYPE_FONT,
			LOADING_SUB_TYPE_SOUND_MENU,
			LOADING_SUB_TYPE_CREDIT_BACKGROUND
		};
	//
	
	private static final int LOADING_TYPE_CREDIT_LIST_ITEM[] = 
		{
			LOADING_SUB_TYPE_CREDIT_BACKGROUND,
			LOADING_SUB_TYPE_FONT
		};
	
	private static final int LOADING_TYPE_OPTION_LIST_ITEM[]=
		{
			LOADING_SUB_TYPE_OPTION_BACKGROUND,
			LOADING_SUB_TYPE_FONT
		};
	
	private static final int LOADING_TYPE_GAMEPLAY_OPEN_LIST_ITEM[] =
		{
			LOADING_SUB_TYPE_FONT,
			LOADING_SUB_TYPE_LOADING_INTERFACE,
		};
	private static final int LOADING_TYPE_GAMEPLAY_LIST_ITEM[] =
		{
			LOADING_SUB_TYPE_FIELD_INFORMASI,
			LOADING_SUB_TYPE_MAP_BACKGROUND,
			LOADING_SUB_TYPE_MC,
			LOADING_SUB_TYPE_ICON_MC,
			LOADING_SUB_TYPE_HUD,
			LOADING_SUB_TYPE_FONT,
			LOADING_SUB_TYPE_GAME_BUTTON,
			LOADING_SUB_TYPE_SOUND_GAMEPLAY,
			LOADING_SUB_TYPE_GAME_OVER,
			LOADING_SUB_TYPE_GAME_OVER_MC,
			LOADING_SUB_TYPE_GAME_PAUSE
		};
	
	public static void setLoading(int loadingType)
	{
		Loading_Type_Current = loadingType;
		Loading_Cur_Progress = 0;
		
		switch (Loading_Type_Current)
		{
			case LOADING_TYPE_APP_OPEN:
				Loading_Max_Progress = LOADING_TYPE_APP_OPEN_LIST_ITEM.length;
				break;
			case LOADING_TYPE_MAIN_MENU:
				Loading_Max_Progress = LOADING_TYPE_MAIN_MENU_LIST_ITEM.length;
				break;
			case LOADING_TYPE_MAIN_MENU_2:
				Loading_Max_Progress = LOADING_TYPE_MAIN_MENU_2_LIST_ITEM.length;
				break;
			case LOADING_TYPE_GAMEPLAY_OPEN:
				Loading_Max_Progress = LOADING_TYPE_GAMEPLAY_OPEN_LIST_ITEM.length;
				break;
			case LOADING_TYPE_GAMEPLAY:
				Loading_Max_Progress = LOADING_TYPE_GAMEPLAY_LIST_ITEM.length;
				break;
			case LOADING_TYPE_MAIN_MENU_OPEN:
				Loading_Max_Progress = LOADING_TYPE_MAIN_MENU_OPEN_LIST_ITEM.length;
				break;
			case LOADING_TYPE_SELECT_MAP:
				Loading_Max_Progress = LOADING_TYPE_SELECT_MAP_LIST_ITEM.length;
				break;
			case LOADING_TYPE_SELECT_MC:
				Loading_Max_Progress = LOADING_TYPE_SELECT_MC_LIST_ITEM.length;
				break;
			case LOADING_TYPE_CREDIT:
				Loading_Max_Progress = LOADING_TYPE_CREDIT_LIST_ITEM.length;
				break;
			case LOADING_TYPE_OPTION:
				Loading_Max_Progress = LOADING_TYPE_OPTION_LIST_ITEM.length;
				break;
		}
	}
	
	public static void setLoading(int loadingType, int pMap)
	{
		map = pMap;
		setLoading(loadingType);
	}
	
	public static boolean isLoading()
	{
		return (Loading_Max_Progress > Loading_Cur_Progress)
				? true : false;
	}
	
	public static void updateLoading()
	{
		switch (Loading_Type_Current)
		{
			case LOADING_TYPE_APP_OPEN:
				loadItem(LOADING_TYPE_APP_OPEN_LIST_ITEM[Loading_Cur_Progress]);
				break;
			case LOADING_TYPE_MAIN_MENU:
				loadItem(LOADING_TYPE_MAIN_MENU_LIST_ITEM[Loading_Cur_Progress]);
				break;
			case LOADING_TYPE_GAMEPLAY_OPEN:
				loadItem(LOADING_TYPE_GAMEPLAY_OPEN_LIST_ITEM[Loading_Cur_Progress]);
				break;
			case LOADING_TYPE_GAMEPLAY:
				loadItem(LOADING_TYPE_GAMEPLAY_LIST_ITEM[Loading_Cur_Progress]);
				break;
			case LOADING_TYPE_MAIN_MENU_2:
				loadItem(LOADING_TYPE_MAIN_MENU_2_LIST_ITEM[Loading_Cur_Progress]);
				break;
			case LOADING_TYPE_MAIN_MENU_OPEN:
				loadItem(LOADING_TYPE_MAIN_MENU_OPEN_LIST_ITEM[Loading_Cur_Progress]);
				break;
			case LOADING_TYPE_SELECT_MAP:
				loadItem(LOADING_TYPE_SELECT_MAP_LIST_ITEM[Loading_Cur_Progress]);
				break;
			case LOADING_TYPE_SELECT_MC:
				loadItem(LOADING_TYPE_SELECT_MC_LIST_ITEM[Loading_Cur_Progress]);
				break;
			case LOADING_TYPE_CREDIT :
				loadItem(LOADING_TYPE_CREDIT_LIST_ITEM[Loading_Cur_Progress]);
				break;
			case LOADING_TYPE_OPTION :
				loadItem(LOADING_TYPE_OPTION_LIST_ITEM[Loading_Cur_Progress]);
				break;
			
		}
	}
	
	private static void loadItem(int itemType)
	{
		Loading_Cur_Progress++;
		switch(itemType)
		{
			case LOADING_SUB_TYPE_LOGO:
				Game.loadLogo();
				break;
			case LOADING_SUB_TYPE_WALPAPER:
				break;
			case LOADING_SUB_TYPE_MAIN_MENU_INTERFACE:
				Game.loadTitle();
				Game.loadBackgroundMenu();
				Game.loadDadu();
				break;
			case LOADING_SUB_TYPE_MAIN_MENU_BUTTON:
				Game.loadButtonMenu();
				break;
			case LOADING_SUB_TYPE_FONT:
				Game.loadFont();
				break;
			case LOADING_SUB_TYPE_LOADING_INTERFACE:
				Game.loadLoadingBg();
				Game.load_Close_Notif();
				break;
			case LOADING_SUB_TYPE_MC:
				Game.loadMC();
				Game.loadSmoke();
				break;
			case LOADING_SUB_TYPE_MAP_BACKGROUND:
				Game.loadGameMap(map);
				break;
			case LOADING_SUB_TYPE_CREDIT_BACKGROUND:
				Game.load_BG_Credit();	
				Game.loadTitle();
				Game.loadLogo();
				break;
			case LOADING_SUB_TYPE_OPTION_BACKGROUND:
				Game.load_Option();
				break;
			case LOADING_SUB_TYPE_GAME_BUTTON:
				Game.loadGamePlayDice();
				Game.loadGameButton();
				break;
			case LOADING_SUB_TYPE_SOUND_GAMEPLAY:
				SoundManager.loadSoundGameplay();
				break;
			case LOADING_SUB_TYPE_HUD:
				break;
			case LOADING_SUB_TYPE_FIELD_INFORMASI:
				Game.loadGameInformasi();
				break;
			case LOADING_SUB_TYPE_ICON_MC:
				Game.loadIconMC();
				break;
			case LOADING_SUB_TYPE_SELECT_MAP_BACKGROUND:
				Game.loadSelectMapBg();
				Game.loadSelectMapBtn();
				Game.loadSelectMapBgSelect();
				break;
			case LOADING_SUB_TYPE_SELECT_MAP_ICON_MAP:
				Game.loadSelectMapIconMap();
				break;
			case LOADING_SUB_TYPE_SELECT_MC_BACKGROUND:
				Game.loadSelectMcBg();
				Game.loadSelectMcIconBg();
				Game.loadSelectMcIcon();
				Game.loadSelectMcBtnDelete();
				Game.loadSelectMcBtnType();
				Game.loadSelectMcBtnAdd();
				Game.loadSelectMcBtnArrow();
				break;
			case LOADING_SUB_TYPE_SELECT_MC_ICON_CARARACTER:
				break;
			case LOADING_SUB_TYPE_SOUND_MENU:
				SoundManager.loadSoundMenu();
				break;
			case LOADING_SUB_TYPE_GAME_OVER:
				Game.loadGameOverBackground();
				Game.loadGameOverButton();
				break;
			case LOADING_SUB_TYPE_GAME_OVER_MC:
				Game.loadGameOverMC();
				break;
			case LOADING_SUB_TYPE_GAME_PAUSE:
				Game.loadGamePauseBackground();
				Game.loadGamePauseText();
				Game.loadGamePauseMc();
				Game.loadGamePauseButton();
				break;
		}
	}
}
