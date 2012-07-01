package com.amikomgamedev.ulartangga.gamelevel;

public interface Game_Level_Handler
{
	public static final int CELL_LADDER_START	= 0;
	public static final int CELL_LADDER_END		= 1;
	public static final int CELL_SNAKE_START	= 2;
	public static final int CELL_SNAKE_END		= 3;
	
	public static final int[][][] SNAKE_N_LADDER =
		{
			{// modern
				{2, 5,	8,	11,	21,	38,	50,	55,	60},		// posisi Tangga Awal
				{24,48,	28,	52,	41,	65,	71,	93, 78},		// posisi Tangga Akhir
				{33,54,	58,	63,	76,	84,	89,	95,	98},		// posisi Ular Awal
				{7,	17,	25,	35,	22,	45,	9,	68,	80}			// posisi Ular Ahir
			},
			{// klasik
				{2, 5,	8,	11,	21,	38,	50,	55,	60},		// posisi Tangga Awal
				{24,48,	28,	52,	41,	65,	71,	93, 78},		// posisi Tangga Akhir
				{33,54,	58,	63,	76,	84,	89,	95,	98},		// posisi Ular Awal
				{7,	17,	25,	35,	22,	45,	9,	68,	80}			// posisi Ular Ahir
			},
			{// galaksi
				{2, 5,	8,	11,	21,	38,	50,	55,	60},		// posisi Tangga Awal
				{24,48,	28,	52,	41,	65,	71,	93, 78},		// posisi Tangga Akhir
				{33,54,	58,	63,	76,	84,	89,	95,	98},		// posisi Ular Awal
				{7,	17,	25,	35,	22,	45,	9,	68,	80}			// posisi Ular Ahir
			},
			{// kolosal
				{2, 5,	8,	11,	21,	38,	50,	55,	60},		// posisi Tangga Awal
				{24,48,	28,	52,	41,	65,	71,	93, 78},		// posisi Tangga Akhir
				{33,54,	58,	63,	76,	84,	89,	95,	98},		// posisi Ular Awal
				{7,	17,	25,	35,	22,	45,	9,	68,	80}			// posisi Ular Ahir
			}
		};
}
