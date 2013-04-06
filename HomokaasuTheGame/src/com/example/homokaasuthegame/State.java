package com.example.homokaasuthegame;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.World;

public class State {
	private World world;
	private BodyDef bd = new BodyDef();
	private FixtureDef fd = new FixtureDef();
	
	public void addEnemy() {
		bd.type = BodyType.DynamicBody;
		bd.active = true;
		
		bd.position.set(0, 0);
		
		fd.density = 1.0f;
		fd.friction = 0.1f;
		fd.restitution = 0.01f;
		
		world.createBody(bd).createFixture(fd);
	}
}
