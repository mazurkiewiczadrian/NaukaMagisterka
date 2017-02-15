package com.example.naukamagisterka;

public class Global {
	public static boolean PAUSE = false;
	public static long START_TIME = 01;
	public static long CURRENT_TIME = 01;
	public static final int RANDOM_CLICK = 5;
	
	//WIelkoœci macierzy dla leveli 1-5, 5-10, 11-15, 16-20
	public static final int [][] MATRIX_SIZE = {{5,5},{6,6},{5,6},{6,5}};
	
	//WYmagania punktowe na levele 1-5, 5-10, 11-15, 16-20
	public static final int[] SCORE_REQ = {60, 70, 80, 90};
	
	//Pusta macierz 5x5 dla  5x6, 6x5, 6x6
	public static final boolean NULL_SPACE[][][] = 
		{
				{
					{true,false,true,false,true,true,false,true,false,true,false,false,false,false,false,true,false,true,false,true,true,false,true,false,true},
					{false,true,true,true,false,true,false,true,false,true,true,true,false,true,true,true,false,true,false,true,false,true,true,true,false}
				}
		};
	
	public static final int FIVE_BY_FIVE_PATT[][]=
		{
		};
	
	public static final int SIX_BY_SIX_PATT[][]=
		{
		};
	
	public static final int FIVE_BY_SIX_PATT[][]=
		{
		};
	
	public static final int SIX_BY_FIVE_PATT[][]=
		{
		};
	
	public static final int [][][]PATTERNS = new int[][][]
			{FIVE_BY_FIVE_PATT, SIX_BY_SIX_PATT, FIVE_BY_SIX_PATT, SIX_BY_FIVE_PATT};
	
	
		
}
	

