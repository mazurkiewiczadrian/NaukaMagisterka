package com.example.naukamagisterka;

public class UpdateGameObject extends Observator{
	
	GameWorld gw;
	LoginDataBaseAdapter loginDataBaseAdapter;
	
	public UpdateGameObject(GameWorld gameworld, String userName) {
		this.gw=gameworld;
		
		String level = loginDataBaseAdapter.getSingleEntryLevel(userName);
		int levelInt = Integer.parseInt(level);
		this.gw.attach(levelInt, this);
		
		this.gw.attach(levelInt, this);
	}
	
	@Override
	public void update() {
		gw.setMove(gw.getMove()+1);
	}
}
