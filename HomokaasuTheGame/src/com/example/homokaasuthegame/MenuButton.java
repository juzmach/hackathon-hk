package com.example.homokaasuthegame;

import org.andengine.entity.scene.Scene;
import org.andengine.entity.sprite.Sprite;
import org.andengine.input.touch.TouchEvent;
import org.andengine.opengl.vbo.VertexBufferObjectManager;

public class MenuButton extends Sprite {
    private final IAction action;

    public interface IAction {
        public void run();
    }

    public MenuButton(float pX, float pY, String filename,
            VertexBufferObjectManager pVertexBufferObjectManager, Scene scene,
            IAction action) {
        super(pX, pY, 305, 94,
                MainActivity.mainActivity.loadTexture(filename, 305, 94, 0, 0),
                pVertexBufferObjectManager);

        this.action = action;
        scene.attachChild(this);
        this.setZIndex(100);
        scene.registerTouchArea(this);
    }

    @Override
    public boolean onAreaTouched(final TouchEvent pSceneTouchEvent,
            final float touchAreaX, final float touchAreaY) {
        switch (pSceneTouchEvent.getAction()) {
        case TouchEvent.ACTION_DOWN:
            break;
        case TouchEvent.ACTION_MOVE:
            break;
        case TouchEvent.ACTION_UP:
            action.run();
            return false;
        }
        return true;
    }
}
