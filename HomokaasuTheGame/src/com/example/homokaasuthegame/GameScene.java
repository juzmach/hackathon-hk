package com.example.homokaasuthegame;

import java.util.LinkedList;

import org.andengine.engine.camera.Camera;
import org.andengine.entity.scene.Scene;
import org.andengine.opengl.util.GLState;
import org.andengine.opengl.vbo.VertexBufferObjectManager;

import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;

public class GameScene extends Scene {
	
	private BodyDef bd = new BodyDef();
	private FixtureDef fd = new FixtureDef();
	LinkedList<Enemy> enemies = new LinkedList<Enemy>();
	
	public GameScene(VertexBufferObjectManager vbom) {
		super();
		this.vbom = vbom;
	}
	
	public void addEnemy() {
		bd.type = BodyType.DynamicBody;
		bd.active = true;
		
		bd.position.set(0, 0);
		
		fd.density = 1.0f;
		fd.friction = 0.1f;
		fd.restitution = 0.01f;
		
		/*Enemy n = new Enemy(world.createBody(bd));
		n.getBody().createFixture(fd);*/
		
		Enemy n = new Enemy(0, 0, null, this.vbom);
		attachChild(n);
	}
}
