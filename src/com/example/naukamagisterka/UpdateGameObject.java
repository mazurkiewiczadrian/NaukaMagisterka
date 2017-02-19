package com.example.naukamagisterka;

public class UpdateGameObject extends Observator{
	
	GameWorld gw;
	
	public UpdateGameObject(GameWorld gameworld) {
		this.gw=gameworld;
		this.gw.attach(0, this);
	}
}
