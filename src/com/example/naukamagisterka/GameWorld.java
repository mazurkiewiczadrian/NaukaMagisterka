package com.example.naukamagisterka;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Observer;
import java.util.TreeMap;

import org.w3c.dom.Node;

import android.widget.Button;

public class GameWorld {
	private ArrayList<Observator> observators; 
	int index;
	Button[] btn;
	final int row_org_matrix;
	final int col_org_matrix;
	final int tog_row_matrix;
	final int tog_col_matrix;
	final int n;
	
	
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
		observators = new ArrayList<Observator>();
		index = (level-1)/5;
		orig_matrix = new boolean[Global.MATRIX_SIZE[index][0]][Global.MATRIX_SIZE[index][1]];
		row_org_matrix = orig_matrix.length;
		col_org_matrix = orig_matrix[0].length;
		
		toogle_matrix = new boolean[row_org_matrix*col_org_matrix][row_org_matrix*col_org_matrix];
		tog_row_matrix = toogle_matrix.length;
		tog_col_matrix = toogle_matrix[0].length;
		
		vector_matrix = new boolean[row_org_matrix*col_org_matrix];
		result_matrix = new boolean [row_org_matrix*col_org_matrix];
		result_matrix_v1 = new boolean [row_org_matrix*col_org_matrix];
		result_matrix_v2 = new boolean [row_org_matrix*col_org_matrix];
		result_matrix_v12 = new boolean [row_org_matrix*col_org_matrix];
		
		n = row_org_matrix*col_org_matrix;
		btn = new Button[n+1];
		col.put(true, R.drawable.blueflsh);
		col.put(false, R.drawable.whiteflsh);
		
	}
	public void addObservator(Observator o){
		observators.add(o);
	}
	
	public void deleteObservator(Observator o){
		int index = observators.indexOf(o);
		observators.remove(index);
	}
	
	public void notifyAllObservators(){
		for (Observator observator : observators) {
			observator.update();
		}
	}
	
	/* 
	 * 
	public void notifyAllObservers(){
		for (Observer observer : observers){
			observer.update();
		}
	}
	*/

	public void buttonNumber(int c){
		pressButton((c-1)/col_org_matrix, (c-1)%col_org_matrix);
		pressButton((c-1)/col_org_matrix-1, (c-1)%col_org_matrix);
		pressButton((c-1)/col_org_matrix-1, (c-1)%col_org_matrix);
		pressButton((c-1)/col_org_matrix, (c-1)%col_org_matrix-1);
		pressButton((c-1)/col_org_matrix, (c-1)%col_org_matrix-1);
	}
	
	public void pressButton(int row, int col){
		if (row >= 0 && row < row_org_matrix && col >=0 && col < col_org_matrix) {
			orig_matrix[row][col] = orig_matrix[row][col]^true;
		}
	}
	
	public void click(int row, int col, int cnt){
		if (row >= 0 && row < row_org_matrix && col >= 0 && col < col_org_matrix) {
			toogle_matrix[(row)*col_org_matrix+(col)][cnt] = true;
		}
	}
	
	public void buildToggleMatrix(){
		int cnt = 0;
		for (int i = 1; i <= row_org_matrix * col_org_matrix; i++) {
			click((i-1)/col_org_matrix, (i-1)%col_org_matrix, cnt);
			click((i-1)/col_org_matrix+1, (i-1)%col_org_matrix, cnt);
			click((i-1)/col_org_matrix-1, (i-1)%col_org_matrix, cnt);
			click((i-1)/col_org_matrix, (i-1)%col_org_matrix+1, cnt);
			click((i-1)/col_org_matrix, (i-1)%col_org_matrix-1, cnt);
			cnt++;
		}
	}
	
	public boolean allButtonOff(){
		for (int i = 0; i < row_org_matrix; i++) {
			for (int j = 0; j < col_org_matrix; j++) {
				if (orig_matrix[i][j]) {
					return false;
				}
			}
		}
		return true;
	}
	
	int findPivot(int startRow, int pivotColumn){
		for (int row = startRow; row < tog_row_matrix; ++row) {
			if (toogle_matrix[row][pivotColumn]) {
				return row;
			}
		}return -1;
	}
	
	public void Reduce(){
		int cnt = 0;
		for (int i = 0; i < row_org_matrix; i++) {
			for (int j = 0; j < col_org_matrix; j++) {
				vector_matrix[cnt]=orig_matrix[i][j];
				cnt++;
			}
		}
		//Wype³nia ka¿dy rz¹d 1.0
		for (boolean[] row : toogle_matrix) {
			Arrays.fill(row, false);
		}
		buildToggleMatrix();
		
		for (int i = 0; i < n; i++) {
			result_matrix[i]=false;
			result_matrix_v1[i]=false;
			result_matrix_v2[i]=false;
			result_matrix_v12[i]=false;
		}
		int nextFreeRow = 0;
		
		for (int col = 0; col < n; ++col) {
			int pivotRow = findPivot(nextFreeRow, col);
			if (pivotRow == (-1)) {
				continue;
			}
			for (int i = 0; i < tog_row_matrix; i++) {
				boolean temp = toogle_matrix[pivotRow][i];
				toogle_matrix[pivotRow][i] = toogle_matrix[nextFreeRow][i];
				toogle_matrix[nextFreeRow][i] = temp;
			}
			
			boolean temp2 = vector_matrix[pivotRow];
			vector_matrix[pivotRow]=vector_matrix[nextFreeRow];
			vector_matrix[nextFreeRow]=temp2;
			
			for (int row = pivotRow+1; row < n; ++row) {
				if (toogle_matrix[row][col]) {
					for (int i = 0; i < tog_col_matrix; i++) {
						toogle_matrix[row][i]=toogle_matrix[row][i]^toogle_matrix[nextFreeRow][i];
					}
					vector_matrix[row] ^= vector_matrix[nextFreeRow];
				}
			}
			++nextFreeRow;
		}
	}
	
	public int substitute(){
		for (int row = n-1; row >= 0; row--) {
			int pivot = -1;
				for (int col = 0; col < n; ++col) {
					if (toogle_matrix[row][col]) {
						pivot = col;
						break;
					}
				}
				
				if (pivot == (-1)) {
					if (vector_matrix[row]) {
						System.out.println("Brak rozwi¹zania");
						return -1;
					}
				}else{
					result_matrix[row] = vector_matrix[row];
					for (int col = pivot + 1; col < n; ++col) {
						result_matrix[row] = 
						(result_matrix[row]^(toogle_matrix[row][col]&result_matrix[col]));
					}
				}
				
		}return 0;
	}
	
	public void optimalButtons(){
		int var1 =0, var2=0, var3=0,var4=0;
		tm.clear();
		
		for (int i = 0; i < n; i++) {
			result_matrix_v1[i] = result_matrix[i]^Global.NULL_SPACE[index][0][i];
			result_matrix_v2[i]=result_matrix[i]^Global.NULL_SPACE[index][1][i];
			result_matrix_v12[i] = result_matrix[i]^Global.NULL_SPACE[index][0][i]^
					Global.NULL_SPACE[index][1][i];
			
				if (result_matrix[i]) {
					++var1;
				}
				if (result_matrix_v1[i]) {
					++var2;
				}
				if (result_matrix_v2[i]) {
					++var3;
				}
				if (result_matrix_v12[i]) {
					++var4;
				}
		}
		
		tm.put(var1, result_matrix);
		tm.put(var2, result_matrix_v1);
		tm.put(var3, result_matrix_v2);
		tm.put(var4, result_matrix_v12);
	}
	
	public int getRow_org_matrix(){
		return row_org_matrix;
	}
	
	public int getCol_org_matrix(){
		return col_org_matrix;
	}
	
	public HashMap<Boolean, Integer> getCol(){
		return col;
	}
	
	public Button[] getBtn(){
		return btn;
	}
	
	public void attach(int level, Observator o){
		GameWorld gameworld = new GameWorld(1);
		gameworld.addObservator(o);
	}
	
	
	
	
}
