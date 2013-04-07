package com.example.homokaasuthegame;

import java.io.IOException;

import org.andengine.audio.music.Music;
import org.andengine.audio.music.MusicFactory;
import org.andengine.entity.sprite.AnimatedSprite;
import org.andengine.input.touch.TouchEvent;
import org.andengine.opengl.texture.TextureOptions;
import org.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlas;
import org.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlasTextureRegionFactory;
import org.andengine.opengl.texture.region.ITextureRegion;
import org.andengine.opengl.texture.region.ITiledTextureRegion;
import org.andengine.opengl.vbo.VertexBufferObjectManager;
import org.andengine.util.debug.Debug;

public class Paussi extends AnimatedSprite {
    private static int SPR_COLUMN  = 2;
    private static int SPR_ROWS    = 1;

    private static ITextureRegion textureRegion;
    
    private boolean enabled = false;

    static void init(MainActivity activity) {
        BitmapTextureAtlas textureAtlas;
        textureAtlas = new BitmapTextureAtlas(activity.getTextureManager(),
                1234, 617, TextureOptions.BILINEAR_PREMULTIPLYALPHA);
        textureRegion = BitmapTextureAtlasTextureRegionFactory.createTiledFromAsset(
                textureAtlas, activity.getAssets(),
                "pausebutton_sprsheet.png", 0, 0, SPR_COLUMN, SPR_ROWS);
        textureAtlas.load();
    }

    public Paussi(float pX, float pY,
            VertexBufferObjectManager pVertexBufferObjectManager) {
        super(pX, pY, 617, 617, (ITiledTextureRegion) textureRegion, pVertexBufferObjectManager);
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

            this.animate(new long[] {0}, new int[] {1}, 0);
            MainActivity.mainActivity.paussi();
            MainActivity.mainScene.unregisterTouchArea(this);

            return false;
        }
        return true;
    }
}
