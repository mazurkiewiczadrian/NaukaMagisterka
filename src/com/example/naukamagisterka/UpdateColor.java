package com.example.naukamagisterka;

import android.database.sqlite.SQLiteDatabase;
import android.widget.EditText;

public class UpdateColor extends Observator {
	
	GameWorld gameworld;
	LoginDataBaseAdapter loginDataBaseAdapter;
	SQLiteDatabase db;
	
	public UpdateColor(GameWorld gameworld, String userName){
		this.gameworld = gameworld;
		loginDataBaseAdapter.open();
		
		String level = loginDataBaseAdapter.getSingleEntryLevel(userName);
		int levelInt = Integer.parseInt(level);
		
		this.gameworld.attach(levelInt, this);
	}
	
	@Override
	public void update() {
		int count = 1;
		for (int i = 0; i < gameworld.getRow_org_matrix(); i++) {
			for (int j = 0; j < gameworld.getCol_org_matrix(); j++) {
				gameworld.getBtn()[count++].setBackgroundResource(gameworld.getCol().
						get(gameworld.orig_matrix[i][j]));
			}
		}
	}
}
