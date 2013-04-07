package com.example.homokaasuthegame;

import org.andengine.entity.sprite.Sprite;
import org.andengine.input.touch.TouchEvent;
import org.andengine.opengl.texture.region.ITextureRegion;
import org.andengine.opengl.vbo.VertexBufferObjectManager;

public class Paussi extends Sprite {
    private static ITextureRegion textureRegion;
    private boolean enabled = false;

    static void init(MainActivity activity) {
        textureRegion = activity.loadTexture("pausebutton.png", 617, 617, 0, 0);
    }

    public Paussi(float pX, float pY,
            VertexBufferObjectManager pVertexBufferObjectManager) {
        super(pX, pY, 617, 617, textureRegion, pVertexBufferObjectManager);
        this.setScale(0.1f);
        MainActivity.mainScene.attachChild(this);
        this.setZIndex(100);
        MainActivity.mainScene.registerTouchArea(this);
    }

    @Override
    public boolean onAreaTouched(final TouchEvent pSceneTouchEvent,
            final float touchAreaX, final float touchAreaY) {
        switch (pSceneTouchEvent.getAction()) {
        case TouchEvent.ACTION_DOWN:
            enabled = true;
            break;
        case TouchEvent.ACTION_MOVE:
            break;
        case TouchEvent.ACTION_UP:
            if (!enabled)
                return false;

            MainActivity.mainActivity.paussi();
            MainActivity.mainScene.unregisterTouchArea(this);
            this.detachSelf();
            return false;
        }
        return true;
    }
}
