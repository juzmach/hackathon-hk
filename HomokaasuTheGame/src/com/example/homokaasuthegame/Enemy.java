package com.example.homokaasuthegame;


import org.andengine.entity.Entity;
import org.andengine.entity.sprite.AnimatedSprite;
import org.andengine.opengl.texture.region.ITiledTextureRegion;
import org.andengine.opengl.texture.region.TiledTextureRegion;
import org.andengine.opengl.vbo.VertexBufferObjectManager;

import android.util.DisplayMetrics;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.World;


public class Enemy extends AnimatedSprite {
	public Enemy(float pX, float pY, float pWidth, float pHeight,
			ITiledTextureRegion pTiledTextureRegion,
			VertexBufferObjectManager pVertexBufferObjectManager) {
		super(pX, pY, pWidth, pHeight, pTiledTextureRegion, pVertexBufferObjectManager);
		// TODO Auto-generated constructor stub
	}
	
	public Enemy(float pX, float pY, float pWidth, float pHeight,
			ITiledTextureRegion pTiledTextureRegion,
			VertexBufferObjectManager pVertexBufferObjectManager, Body b) {
		super(pX, pY, pWidth, pHeight, pTiledTextureRegion, pVertexBufferObjectManager);
		// TODO Auto-generated constructor stub
	}



	private Body body;
	
	
	
	@Override
	protected void onManagedUpdate(final float pSecondsElapsed) {
		super.onManagedUpdate(pSecondsElapsed);
	}
	
	
}
