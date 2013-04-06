package com.example.homokaasuthegame;

import org.andengine.opengl.texture.TextureOptions;
import org.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlas;
import org.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlasTextureRegionFactory;
import org.andengine.opengl.texture.region.ITextureRegion;
import org.andengine.opengl.vbo.VertexBufferObjectManager;

public class Ant extends Enemy{

    private static BitmapTextureAtlas textureAtlas;
	private static ITextureRegion textureRegion;

	private static int   SPR_COLUMN  = 2;
	private static int   SPR_ROWS  = 1;

	public static void init(MainActivity activity) {
	    textureAtlas = new BitmapTextureAtlas(activity.getTextureManager(),
	            256, 128, TextureOptions.BILINEAR_PREMULTIPLYALPHA);
	    textureRegion = BitmapTextureAtlasTextureRegionFactory.createTiledFromAsset(textureAtlas, activity.getAssets(),
	            "ant_sprsheet.png", 0, 0, SPR_COLUMN, SPR_ROWS);
	    textureAtlas.load();
	}

	public Ant(float pX, float pY, float pWidth, float pHeight,
			VertexBufferObjectManager vertexBufferObjectManager) {
		super(pX, pY, 0, pWidth, pHeight, textureRegion,
		        vertexBufferObjectManager);

		this.animate(100);
	}
}
