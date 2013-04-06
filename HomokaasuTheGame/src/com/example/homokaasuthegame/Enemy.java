package com.example.homokaasuthegame;


import org.andengine.entity.Entity;

import android.util.DisplayMetrics;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.World;


public class Enemy extends Entity {
	private Body body;
	
	public Enemy(Body body) {
		this.body = body;
	}
	
	public Body getBody() {
		return body;
	}
	
	@Override
	protected void onManagedUpdate(final float pSecondsElapsed) {
		super.onManagedUpdate(pSecondsElapsed);
	}
}
