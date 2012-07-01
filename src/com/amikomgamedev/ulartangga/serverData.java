package com.amikomgamedev.ulartangga;

import android.content.Context;

public class serverData {
	private static serverData instance = null;
	
	private static final int CHAR_PLAYER_A = 0;
	private static final int CHAR_PLAYER_B = 1;
	private static final int CHAR_PLAYER_C = 2;
	private static final int CHAR_PLAYER_D = 3;
	
	private static String selectMap 	= "";
	private static String charPlayer1 	= "";
	private int jobOrderId = 0;
	Context context;
	
	protected serverData()
	{
		
	}	
	

	public static serverData getInstance()
	{
		if(instance==null)
			instance = new serverData();
		return instance;
	}

	public static String getUsername() {
		return username;
	}

	public static void setUsername(String username) {
		serverData.username = username;
	}	
	
}
