package com.example.homokaasuthegame;

import org.andengine.opengl.texture.TextureOptions;
import org.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlas;
import org.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlasTextureRegionFactory;
import org.andengine.opengl.texture.region.ITextureRegion;
import org.andengine.opengl.vbo.VertexBufferObjectManager;

import com.badlogic.gdx.math.Vector2;

public class Fly extends Enemy {

    private static BitmapTextureAtlas textureAtlas;
	private static ITextureRegion textureRegion;
	//size of the image
	private static float HEIGHT = 108;
	private static float WIDTH = 195;
	private static int SPR_COLUMN = 2;
	private static int SPR_ROWS = 1;

	private final Vector2 target = new Vector2();

	public Fly(float pX, float pY, VertexBufferObjectManager vertexBufferObjectManager) {
		super(pX, pY, 0, WIDTH / 2, HEIGHT, textureRegion,
		        vertexBufferObjectManager, 0.3f, 0.2f);
	    this.animate(50);
	}

	public static void init(MainActivity activity) {
	    textureAtlas = new BitmapTextureAtlas(activity.getTextureManager(),
	            (int) WIDTH, (int) HEIGHT, TextureOptions.BILINEAR_PREMULTIPLYALPHA);
	    textureRegion = BitmapTextureAtlasTextureRegionFactory.createTiledFromAsset(textureAtlas, activity.getAssets(),
	            "fly_sprsheet.png", 0, 0, SPR_COLUMN, SPR_ROWS);
	    textureAtlas.load();
	}

	boolean AIDead = false;

	@Override
    protected void onManagedUpdate(float pSecondsElapsed) {
        super.onManagedUpdate(pSecondsElapsed);

        if (joint != null || AIDead) {
            AIDead = true;
            return;
        }
        if (body.getPosition().x <= target.x) {
            this.setFlippedHorizontal(true);
            body.setLinearVelocity(0.4f + (0.5f - (float)Math.random()) * 5f,
                    (0.5f - (float)Math.random()) * 13f - 0.1f);
        } else {
            this.setFlippedHorizontal(false);
            body.setLinearVelocity(-0.4f + (0.5f - (float)Math.random()) * 5f,
                    (0.5f - (float)Math.random()) * 13f - 0.1f);
        }
    }

    public void setTarget(Vector2 target) {
        this.target.set(target);
    }
}