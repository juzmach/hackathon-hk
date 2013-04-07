package com.example.homokaasuthegame;

import org.andengine.entity.sprite.AnimatedSprite;
import org.andengine.extension.physics.box2d.PhysicsConnector;
import org.andengine.extension.physics.box2d.PhysicsFactory;
import org.andengine.opengl.texture.TextureOptions;
import org.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlas;
import org.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlasTextureRegionFactory;
import org.andengine.opengl.texture.region.ITextureRegion;
import org.andengine.opengl.texture.region.ITiledTextureRegion;
import org.andengine.opengl.vbo.VertexBufferObjectManager;

import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;


/**
 * A delicious pastry.
 * @author kviiri
 *
 */
public class Pie extends AnimatedSprite {
    private static final float WIDTH = 453;
    private static final float HEIGHT = 145;
    private static int SPR_COLUMN  = 3;
    private static int SPR_ROWS    = 2;

    private static ITextureRegion pieTextureRegion;

    private static final float maxHP = 10;
    private float hp = maxHP;

    static void init(MainActivity activity) {
        BitmapTextureAtlas textureAtlas;
        textureAtlas = new BitmapTextureAtlas(activity.getTextureManager(),
                1500, 342, TextureOptions.BILINEAR_PREMULTIPLYALPHA);
        pieTextureRegion = BitmapTextureAtlasTextureRegionFactory.createTiledFromAsset(
                textureAtlas, activity.getAssets(),
                "pie_sprsheet.png", 0, 0, SPR_COLUMN, SPR_ROWS);
        textureAtlas.load();
    }

	public Pie(float pX, float pY,
	        VertexBufferObjectManager pVertexBufferObjectManager) {
		super(pX, pY, 453f, 145f, (ITiledTextureRegion)pieTextureRegion,
		        pVertexBufferObjectManager);

		BodyDef bd = new BodyDef();
		bd.type = BodyDef.BodyType.StaticBody;
		bd.position.set(pX, pY);

		FixtureDef fd = new FixtureDef();
		//fd.isSensor = true;
		Body body = PhysicsFactory.createBoxBody(MainActivity.physicsWorld,
		        WIDTH / 2f, HEIGHT, WIDTH - 240f, 80f, bd.type, fd);

		MainActivity.physicsWorld.registerPhysicsConnector(
		        new PhysicsConnector(this, body, true, false));
		body.setTransform(pX, pY, 0);
		MainActivity.mainScene.attachChild(this);
	}

	public boolean eat() {
	    if (hp > 0) {
	        hp--;
	        this.animate(new long[] {0},
	        		new int[] { 5 - (int)Math.ceil(hp / maxHP * 5)}, 0);
	        return true;
	    } else {
	        return false;
	    }
	}

	float spawnTimer = 0;
	float spawnInterval = 4;

	@Override
    protected void onManagedUpdate(float pSecondsElapsed) {
        super.onManagedUpdate(pSecondsElapsed);

        spawnTimer += pSecondsElapsed;
        if (spawnTimer > spawnInterval) {
            spawnTimer = 0;
            if (spawnInterval > 0.3f)
                spawnInterval -= 0.01f;

            float xSpawn = (Math.random() > 0.5f) ? 30f : 5f;
            if (Math.random() < 0.6f) {
                MainActivity.mainActivity.spawnAnt(xSpawn, 15);
            } else {
                MainActivity.mainActivity.spawnFly(xSpawn, 5);
            }
        }
    }
}
