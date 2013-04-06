package com.example.homokaasuthegame;

import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.World;

public class State {
	private World world;
	
	public void addEnemy() {
		BodyDef def = new BodyDef();
		def.type = BodyType.DynamicBody;
	}
}
