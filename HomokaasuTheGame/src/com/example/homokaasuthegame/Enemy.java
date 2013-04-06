package com.example.homokaasuthegame;


import android.util.DisplayMetrics;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.World;


public class Enemy {
	private Body body;
	
	public Enemy(Body body) {
		this.body = body;
	}
}
