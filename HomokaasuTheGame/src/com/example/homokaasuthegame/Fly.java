package com.example.homokaasuthegame;

import org.andengine.opengl.texture.region.ITextureRegion;
import org.andengine.opengl.vbo.VertexBufferObjectManager;

import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;

public class Fly extends Enemy {
	private static ITextureRegion textureRegion;

	public Fly(float pX, float pY, float rot, float pWidth, float pHeight,
			ITextureRegion pTextureRegion,
			VertexBufferObjectManager vertexBufferObjectManager) {
		super(pX, pY, rot, pWidth, pHeight, pTextureRegion, vertexBufferObjectManager);
		// TODO Auto-generated constructor stub
	}

	public static void init(){

	}
}
