package com.example.homokaasuthegame;

import org.andengine.opengl.texture.TextureOptions;
import org.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlas;
import org.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlasTextureRegionFactory;
import org.andengine.opengl.texture.region.ITextureRegion;
import org.andengine.opengl.vbo.VertexBufferObjectManager;

import com.badlogic.gdx.math.Vector2;

public class Ant extends Enemy{
	private static ITextureRegion textureRegion;

	private static int SPR_COLUMN  = 2;
	private static int SPR_ROWS    = 1;

	private final Vector2 target = new Vector2();

	public static void init(MainActivity activity) {
	    BitmapTextureAtlas textureAtlas;
	    textureAtlas = new BitmapTextureAtlas(activity.getTextureManager(),
	            200, 40, TextureOptions.BILINEAR_PREMULTIPLYALPHA);
	    textureRegion = BitmapTextureAtlasTextureRegionFactory.createTiledFromAsset(
	            textureAtlas, activity.getAssets(),
	            "ant_sprsheet.png", 0, 0, SPR_COLUMN, SPR_ROWS);
	    textureAtlas.load();
	}

	public Ant(float pX, float pY, boolean flip,
	        VertexBufferObjectManager vertexBufferObjectManager) {
		super(pX, pY, 0, 100f, 48f, textureRegion, vertexBufferObjectManager,
		        1f, 1f);
		this.animate(100);
		this.setFlippedHorizontal(flip);
	}

	@Override
    protected void onManagedUpdate(float pSecondsElapsed) {
	    super.onManagedUpdate(pSecondsElapsed);

	    if (joint != null || body.getPosition().y < 15f)
	        return;
	    if (body.getPosition().x <= target.x) {
	        this.setFlippedHorizontal(true);
	        body.setLinearVelocity(0.7f, 0f);
	    } else {
	        this.setFlippedHorizontal(false);
	        body.setLinearVelocity(-0.7f, 0f);
	    }
	}

	public void setTarget(Vector2 target) {
	    this.target.set(target);
	}
}
