package com.example.homokaasuthegame;

import org.andengine.entity.scene.IOnSceneTouchListener;
import org.andengine.entity.scene.Scene;
import org.andengine.input.touch.TouchEvent;

import com.badlogic.gdx.physics.box2d.JointDef;
import com.badlogic.gdx.physics.box2d.joints.MouseJoint;
import com.badlogic.gdx.physics.box2d.joints.MouseJointDef;

public class DragDropListener implements IOnSceneTouchListener {

	@Override
	public boolean onSceneTouchEvent(Scene pScene, final TouchEvent pSceneTouchEvent) {	
		MouseJoint joint = null;
    	switch (pSceneTouchEvent.getAction()) {
    	case TouchEvent.ACTION_DOWN:
    		joint = (MouseJoint) MainActivity.physicsWorld.createJoint(new MouseJointDef());
    		break;
    	case TouchEvent.ACTION_MOVE:
    		// change x and y of object
    		break;
    	case TouchEvent.ACTION_UP:
    		MainActivity.physicsWorld.destroyJoint(joint);
    		return false;
    	}
		return true;
	}
}