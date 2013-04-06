package com.example.homokaasuthegame;

import org.andengine.entity.sprite.Sprite;
import org.andengine.extension.physics.box2d.PhysicsConnector;
import org.andengine.extension.physics.box2d.PhysicsFactory;
import org.andengine.opengl.texture.region.ITextureRegion;
import org.andengine.opengl.vbo.VertexBufferObjectManager;

import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;


/**
 * A delicious pastry.
 * @author kviiri
 *
 */
public class Pie extends Sprite {

	public Pie(float pX, float pY, float pWidth, float pHeight,
			ITextureRegion pTextureRegion,
			VertexBufferObjectManager pVertexBufferObjectManager) {
		super(pX, pY, pWidth, pHeight, pTextureRegion, pVertexBufferObjectManager);
		BodyDef bd = new BodyDef();
		bd.type = BodyDef.BodyType.StaticBody;
		bd.position.set(pX, pY);		
		FixtureDef fd = new FixtureDef(); 
		fd.isSensor = true;
		Body body = PhysicsFactory.createCircleBody(MainActivity.physicsWorld,
		        this, BodyType.DynamicBody, fd);
		
		MainActivity.physicsWorld.registerPhysicsConnector(
		        new PhysicsConnector(this, body, true, false));
		MainActivity.mainScene.attachChild(this);
	}

}
