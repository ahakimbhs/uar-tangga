package com.amikomgamedev.ulartangga;

import android.content.Context;

public class serverData
{
	private static serverData instance = null;
	
	private static int maxPlayer	= -1;
	private static int selectMap 	= 0;
	private static int[] charPlayer 	= 
			{
				-1,
				-1,
				-1,
				-1,
				-1,
				-1,
				-1,
				-1,
				-1,
				-1
			};
	
	private static int[] typePlayer 	= 
			{
				-1,
				-1,
				-1,
				-1,
				-1,
				-1,
				-1,
				-1,
				-1,
				-1
			};
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

	public static int getCharPlayer(int index) {
		return charPlayer[index];
	}

	public static void setCharPlayer(int index, int charPlayer) {
		serverData.charPlayer[index] = charPlayer;
	}

	public static int getTypePlayer(int index) {
		return typePlayer[index];
	}

	public static void setTypePlayer(int index, int typePlayer) {
		serverData.typePlayer[index] = typePlayer;
	}

	public static int getMaxPlayer() {
		return maxPlayer;
	}

	public static void setMaxPlayer(int maxPlayer) {
		serverData.maxPlayer = maxPlayer;
	}
}
