package com.example.homokaasuthegame;

import org.andengine.opengl.texture.region.ITextureRegion;
import org.andengine.opengl.vbo.VertexBufferObjectManager;

import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;

public class Ant extends Enemy{

	private static FixtureDef fd;
	private static BodyDef bd;
	private static ITextureRegion textureRegion;


	public Ant(float pX, float pY, float pWidth, float pHeight,
			ITextureRegion pTextureRegion,
			VertexBufferObjectManager vertexBufferObjectManager) {
		super(pX, pY, 0, pWidth, pHeight, pTextureRegion, vertexBufferObjectManager);
		// TODO Auto-generated constructor stub
	}

	public static void init(){
		fd = new FixtureDef();
		bd = new BodyDef();
		
	}

}
