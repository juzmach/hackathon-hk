package com.example.homokaasuthegame;

import org.andengine.engine.camera.Camera;
import org.andengine.engine.options.EngineOptions;
import org.andengine.engine.options.ScreenOrientation;
import org.andengine.engine.options.resolutionpolicy.RatioResolutionPolicy;
import org.andengine.entity.primitive.Rectangle;
import org.andengine.entity.scene.Scene;
import org.andengine.entity.scene.background.Background;
import org.andengine.entity.sprite.Sprite;
import org.andengine.extension.physics.box2d.PhysicsConnector;
import org.andengine.extension.physics.box2d.PhysicsFactory;
import org.andengine.extension.physics.box2d.PhysicsWorld;
import org.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlas;
import org.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlasTextureRegionFactory;
import org.andengine.opengl.texture.region.ITextureRegion;
import org.andengine.ui.activity.BaseGameActivity;
import org.andengine.util.color.Color;

import android.hardware.SensorManager;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.FixtureDef;

public class GameActivity extends BaseGameActivity {
    Scene scene;
    protected static final int CAMERA_WIDTH = 800;
    protected static final int CAMERA_HEIGHT = 480;
    BitmapTextureAtlas playerTexture;
    ITextureRegion playerTexureRegion;
    PhysicsWorld physicsWorld;

    @Override
    public EngineOptions onCreateEngineOptions() {
        Camera mCamera = new Camera(0, 0, CAMERA_WIDTH, CAMERA_HEIGHT);

        EngineOptions options = new EngineOptions(
                true,
                ScreenOrientation.LANDSCAPE_FIXED,
                new RatioResolutionPolicy(
                        CAMERA_WIDTH, CAMERA_HEIGHT),
                mCamera);

        return options;
    }

    @Override
    public void onCreateResources(
            OnCreateResourcesCallback pOnCreateResourcesCallback)
            throws Exception {
        loadGfx();
        pOnCreateResourcesCallback.onCreateResourcesFinished();
    }

    private void loadGfx() {
        // TODO Auto-generated method stub
       BitmapTextureAtlasTextureRegionFactory.setAssetBasePath("gfx/");
       // width and height power of 2^x
       playerTexture = new BitmapTextureAtlas(getTextureManager(), 64, 64);
       playerTexureRegion = BitmapTextureAtlasTextureRegionFactory.createFromAsset(playerTexture, this, "player.png", 0, 0);
       playerTexture.load();
    }

    @Override
    public void onCreateScene(OnCreateSceneCallback pOnCreateSceneCallback)
            throws Exception {
        this.scene = new Scene();
        this.scene.setBackground(new Background(0, 125, 58));
        physicsWorld = new PhysicsWorld(new Vector2(0,
                SensorManager.GRAVITY_EARTH), false);
        this.scene.registerUpdateHandler(physicsWorld);
        createWalls();

        pOnCreateSceneCallback.onCreateSceneFinished(this.scene);
    }

    private void createWalls() {
        // TODO Auto-generated method stub
        FixtureDef WALL_FIX = PhysicsFactory.createFixtureDef(0.0f, 0.0f, 0.0f);
        Rectangle ground = new Rectangle(0, CAMERA_HEIGHT - 15, CAMERA_WIDTH,
                15, this.mEngine.getVertexBufferObjectManager());
            ground.setColor(new Color(15, 50, 0));
        PhysicsFactory.createBoxBody(physicsWorld, ground, BodyType.StaticBody,
                WALL_FIX);
        this.scene.attachChild(ground);
    }

    @Override
    public void onPopulateScene(Scene pScene,
            OnPopulateSceneCallback pOnPopulateSceneCallback) throws Exception {
        Sprite sPlayer = new Sprite(CAMERA_WIDTH / 2, CAMERA_HEIGHT / 2,
                playerTexureRegion, this.mEngine.getVertexBufferObjectManager());
        sPlayer.setRotation(45.0f);
        final FixtureDef PLAYER_FIX = PhysicsFactory.createFixtureDef(10.0f,
                1.0f, 0.0f);
        Body body = PhysicsFactory.createCircleBody(physicsWorld, sPlayer,
                BodyType.DynamicBody, PLAYER_FIX);
        this.scene.attachChild(sPlayer);
        physicsWorld.registerPhysicsConnector(new PhysicsConnector(sPlayer,
                body, true, false));

        pOnPopulateSceneCallback.onPopulateSceneFinished();
    }

}
