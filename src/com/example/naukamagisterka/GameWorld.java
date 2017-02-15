package com.example.naukamagisterka;

import java.util.HashMap;
import java.util.TreeMap;

public class GameWorld {
	int index;
	
	//Macierz, która posiada wartoœci true(dla zapalonego œwiat³a) i false dla zgaszonego
	public boolean [][]orig_matrix;
	public boolean [][]toogle_matrix;
	
	//Bêd¹ mo¿liwe po 4 rozwi¹zania dla ka¿dego wzoru, które bêd¹ przechowywane w poni¿szych tablicahc:
	public boolean [] vector_matrix, result_matrix_v1, result_matrix_v2, result_matrix_v12;
	boolean [] result_matrix;
	
	//HashMapa przechowuje kolor przycisku: W³¹czony - niebieski, wy³¹czony - bia³y
	public HashMap<Boolean, Integer> col = new HashMap<Boolean, Integer>();
	
	//TreeMap przechowuje 4 mo¿liwe rozwi¹zania. Pierwszy wpis jest najmniejszy
	//(TreeMap jest posortowana). Nie ma wiêc koniecznoœci szukania najmniejszej 
	//(optymalnego rozwi¹zania)
	
	public TreeMap<Integer, boolean[]> tm = new TreeMap<Integer, boolean[]>();
	
	public GameWorld(int level) {
		//Konstruktor do zrobienia
	}
	
	public void pressButton(int row, int col){
		
	}
}
