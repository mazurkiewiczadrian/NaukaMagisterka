package com.example.naukamagisterka;


public class UpdateColor extends Observator {
	
	GameWorld gameworld;
	
	public UpdateColor(GameWorld gameworld){
		this.gameworld = gameworld;
		//this.gameworld.attach(this);
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
