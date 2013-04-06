package com.example.homokaasuthegame;

import org.andengine.entity.sprite.Sprite;
import org.andengine.extension.physics.box2d.PhysicsConnector;
import org.andengine.extension.physics.box2d.PhysicsFactory;
import org.andengine.opengl.texture.region.ITextureRegion;
import org.andengine.opengl.vbo.VertexBufferObjectManager;

import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.FixtureDef;


public class Enemy extends Sprite {

    protected static BodyDef bd;
	protected static FixtureDef fd;

	static {
	    bd = new BodyDef();
	    fd = new FixtureDef();
	}

	public Enemy(float pX, float pY, float pWidth, float pHeight,
			ITextureRegion pTextureRegion,
			VertexBufferObjectManager vertexBufferObjectManager) {
		super(0, 0, 0, 0, pTextureRegion,
				vertexBufferObjectManager);

		bd.type = BodyType.DynamicBody;
		bd.active = true;
		bd.position.set(1f, 2f);

		fd.density = 1.0f;
		fd.friction = 0.1f;
		fd.restitution = 0.01f;

		//Body body = MainActivity.physicsWorld.createBody(bd);
		Body body = PhysicsFactory.createCircleBody(MainActivity.physicsWorld,
		        this, BodyType.DynamicBody, fd);
		//body.createFixture(fd);

		MainActivity.physicsWorld.registerPhysicsConnector(
		        new PhysicsConnector(this, body, true, false));
		MainActivity.mainScene.attachChild(this);
	}

}
