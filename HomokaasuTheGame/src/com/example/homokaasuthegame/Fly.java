package com.example.homokaasuthegame;

import org.andengine.entity.sprite.vbo.ITiledSpriteVertexBufferObject;
import org.andengine.opengl.texture.region.ITextureRegion;
import org.andengine.opengl.texture.region.ITiledTextureRegion;
import org.andengine.opengl.vbo.VertexBufferObjectManager;

import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;

public class Fly extends Enemy {
	
	private static BodyDef bd;
	private static FixtureDef fd;
	private static ITextureRegion textureRegion;
	
	static {
		bd = new BodyDef();
		fd = new FixtureDef();
	}

	public Fly(float pX, float pY, float pWidth, float pHeight,
			ITextureRegion pTextureRegion,
			VertexBufferObjectManager vertexBufferObjectManager) {
		super(pX, pY, pWidth, pHeight, pTextureRegion, vertexBufferObjectManager);
		// TODO Auto-generated constructor stub
	}

	

}
