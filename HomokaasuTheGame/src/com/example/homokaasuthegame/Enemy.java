package com.example.homokaasuthegame;

import org.andengine.entity.Entity;
import org.andengine.entity.sprite.AnimatedSprite;
import org.andengine.entity.sprite.vbo.ITiledSpriteVertexBufferObject;
import org.andengine.extension.physics.box2d.PhysicsConnector;
import org.andengine.opengl.texture.region.ITiledTextureRegion;
import org.andengine.opengl.texture.region.TiledTextureRegion;
import org.andengine.opengl.vbo.VertexBufferObjectManager;

import android.util.DisplayMetrics;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;


public class Enemy extends AnimatedSprite {

    private static BodyDef bd = new BodyDef();
	private static FixtureDef fd = new FixtureDef();
	
	public Enemy(float pX, float pY, float pWidth, float pHeight,
			ITiledTextureRegion pTiledTextureRegion,
			ITiledSpriteVertexBufferObject pTiledSpriteVertexBufferObject) {
		super(pX, pY, pWidth, pHeight, pTiledTextureRegion,
				pTiledSpriteVertexBufferObject);
		
		bd.type = BodyType.DynamicBody;
		bd.active = true;
		
		bd.position.set(0, 0);
		
		fd.density = 1.0f;
		fd.friction = 0.1f;
		fd.restitution = 0.01f;
		Body b = MainActivity.world.createBody(bd);	
		Fixture f = b.createFixture(fd);
		
		MainActivity.physicsWorld.registerPhysicsConnector(new PhysicsConnector(this,
                b, true, false));
		
		
		/*Enemy n = new Enemy(world.createBody(bd));
		n.getBody().createFixture(fd);*/
	}
	//Body and Fixture definitions for things
	
	
	
	
}
