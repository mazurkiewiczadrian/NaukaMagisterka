package com.example.naukamagisterka;

import java.util.HashMap;
import java.util.TreeMap;

public class GameWorld {
	int index;
	
	//Macierz, kt�ra posiada warto�ci true(dla zapalonego �wiat�a) i false dla zgaszonego
	public boolean [][]orig_matrix;
	public boolean [][]toogle_matrix;
	
	//B�d� mo�liwe po 4 rozwi�zania dla ka�dego wzoru, kt�re b�d� przechowywane w poni�szych tablicahc:
	public boolean [] vector_matrix, result_matrix_v1, result_matrix_v2, result_matrix_v12;
	boolean [] result_matrix;
	
	//HashMapa przechowuje kolor przycisku: W��czony - niebieski, wy��czony - bia�y
	public HashMap<Boolean, Integer> col = new HashMap<Boolean, Integer>();
	
	//TreeMap przechowuje 4 mo�liwe rozwi�zania. Pierwszy wpis jest najmniejszy
	//(TreeMap jest posortowana). Nie ma wi�c konieczno�ci szukania najmniejszej 
	//(optymalnego rozwi�zania)
	
	public TreeMap<Integer, boolean[]> tm = new TreeMap<Integer, boolean[]>();
	
	public GameWorld(int level) {
		//Konstruktor do zrobienia
	}
	
	public void pressButton(int row, int col){
		
	}
}
