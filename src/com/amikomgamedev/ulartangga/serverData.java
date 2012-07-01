package com.amikomgamedev.ulartangga;

import android.content.Context;

public class serverData {
	private static serverData instance = null;
		
	private static int selectMap 	= -1;
	private static int charPlayer1 	= -1;
	private static int charPlayer2 	= -1;
	private static int charPlayer3 	= -1;
	private static int charPlayer4 	= -1;
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

	public static int getSelectMap() {
		return selectMap;
	}


	public static void setSelectMap(int selectMap) {
		serverData.selectMap = selectMap;
	}


	public static int getCharPlayer1() {
		return charPlayer1;
	}


	public static void setCharPlayer1(int charPlayer1) {
		serverData.charPlayer1 = charPlayer1;
	}


	public static int getCharPlayer2() {
		return charPlayer2;
	}


	public static void setCharPlayer2(int charPlayer2) {
		serverData.charPlayer2 = charPlayer2;
	}


	public static int getCharPlayer3() {
		return charPlayer3;
	}


	public static void setCharPlayer3(int charPlayer3) {
		serverData.charPlayer3 = charPlayer3;
	}


	public static int getCharPlayer4() {
		return charPlayer4;
	}


	public static void setCharPlayer4(int charPlayer4) {
		serverData.charPlayer4 = charPlayer4;
	}	
	
	
	
}
