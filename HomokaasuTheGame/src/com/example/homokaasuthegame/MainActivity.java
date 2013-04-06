package com.example.homokaasuthegame;

import java.util.LinkedList;

import javax.microedition.khronos.opengles.GL10;

import org.andengine.engine.camera.Camera;
import org.andengine.engine.handler.timer.ITimerCallback;
import org.andengine.engine.handler.timer.TimerHandler;
import org.andengine.engine.options.EngineOptions;
import org.andengine.engine.options.ScreenOrientation;
import org.andengine.engine.options.resolutionpolicy.RatioResolutionPolicy;
import org.andengine.entity.primitive.Rectangle;
import org.andengine.entity.scene.Scene;
import org.andengine.entity.scene.background.Background;
import org.andengine.entity.sprite.AnimatedSprite;
import org.andengine.entity.sprite.Sprite;
import org.andengine.entity.text.Text;
import org.andengine.extension.physics.box2d.PhysicsConnector;
import org.andengine.extension.physics.box2d.PhysicsFactory;
import org.andengine.extension.physics.box2d.PhysicsWorld;
import org.andengine.opengl.font.Font;
import org.andengine.opengl.font.FontFactory;
import org.andengine.opengl.texture.TextureOptions;
import org.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlas;
import org.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlasTextureRegionFactory;
import org.andengine.opengl.texture.region.ITextureRegion;
import org.andengine.opengl.util.GLState;
import org.andengine.ui.activity.BaseGameActivity;

import android.graphics.Color;
import android.hardware.SensorManager;
import android.view.KeyEvent;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.FixtureDef;

public class MainActivity extends BaseGameActivity {
    protected static final int CAMERA_WIDTH = 1024;
    protected static final int CAMERA_HEIGHT = 550;

    
    
	
	//The world
	static World world;
	
	//List of enemies
	LinkedList<Enemy> enemies = new LinkedList<Enemy>();
    
    BitmapTextureAtlas playerTexture;
    ITextureRegion playerTextureRegion;
    static PhysicsWorld physicsWorld;
    private BitmapTextureAtlas mFontTexture;
    private Font mFont;

    /* Scenes */
    private Scene splashScene;
    private Scene mainScene;

    /* Splash screen resources */
    private BitmapTextureAtlas splashTexture;
    private ITextureRegion splashTextureRegion;

    private Text text;

    private enum SceneType
    {
        SPLASH,
        MAIN,
        OPTIONS,
        WORLD_SELECTION,
        LEVEL_SELECTION,
        CONTROLLER
    }
    private SceneType currentScene = SceneType.SPLASH;

    @Override
    public EngineOptions onCreateEngineOptions() {
        Camera mCamera = new Camera(0, 0, CAMERA_WIDTH, CAMERA_HEIGHT);

        EngineOptions options = new EngineOptions(
                true,
                ScreenOrientation.LANDSCAPE_FIXED,
                new RatioResolutionPolicy(CAMERA_WIDTH, CAMERA_HEIGHT),
                // new FillResolutionPolicy(),
                mCamera);

        return options;
    }

    @Override
    public void onCreateResources(
            OnCreateResourcesCallback pOnCreateResourcesCallback)
            throws Exception {
        loadSplashScreen();
        pOnCreateResourcesCallback.onCreateResourcesFinished();
    }

    private void loadSplashScreen() {
        BitmapTextureAtlasTextureRegionFactory.setAssetBasePath("gfx/");
        splashTexture = new BitmapTextureAtlas(this.getTextureManager(),
                318, 500, TextureOptions.BILINEAR_PREMULTIPLYALPHA);
        splashTextureRegion = BitmapTextureAtlasTextureRegionFactory.createFromAsset(splashTexture, this, "player.png", 0, 0);
        splashTexture.load();
    }

    @Override
    public void onCreateScene(OnCreateSceneCallback pOnCreateSceneCallback)
            throws Exception {
        initSplashScene();

        pOnCreateSceneCallback.onCreateSceneFinished(this.mainScene);
    }

    @Override
    public void onPopulateScene(Scene pScene,
            OnPopulateSceneCallback pOnPopulateSceneCallback) throws Exception {

        mEngine.registerUpdateHandler(new TimerHandler(5.0f, new ITimerCallback()
        {
            @Override
            public void onTimePassed(final TimerHandler pTimerHandler)
            {
                mEngine.unregisterUpdateHandler(pTimerHandler);
                loadResources();
                loadScenes();
                populateMainScene();
                //splash.detachSelf();
                splashScene.detachSelf();
                mEngine.setScene(mainScene);
                currentScene = SceneType.MAIN;
            }
        }));

        pOnPopulateSceneCallback.onPopulateSceneFinished();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK
                && event.getAction() == KeyEvent.ACTION_DOWN)
        {
            switch (currentScene)
            {
                case SPLASH:
                    break;
                case MAIN:
                    System.exit(0);
                    break;
            }
        }
        return false;
    }


/* Load Resources *************************************************************/

    public void loadResources() {
        loadFonts();
        loadGfx();
    }

    private void loadFonts() {
        /* Load the font we are going to use. */
        FontFactory.setAssetBasePath("fonts/");
        this.mFontTexture = new BitmapTextureAtlas(this.getTextureManager(),
                1024, 1024,
                TextureOptions.BILINEAR_PREMULTIPLYALPHA);
        this.mFont = FontFactory.createFromAsset(
                this.getFontManager(),
                mFontTexture,
                this.getAssets(),
                "BistroSketch.ttf",
                80.0f, true, Color.WHITE);
        this.mEngine.getTextureManager().loadTexture(this.mFontTexture);
        this.getFontManager().loadFont(this.mFont);
    }

    private void loadGfx() {
        // TODO Auto-generated method stub
       BitmapTextureAtlasTextureRegionFactory.setAssetBasePath("gfx/");
       // width and height power of 2^x
       playerTexture = new BitmapTextureAtlas(getTextureManager(), 64, 64);
       playerTextureRegion = BitmapTextureAtlasTextureRegionFactory.createFromAsset(
               playerTexture, this, "player.png", 0, 0);
       playerTexture.load();
    }


/* Load Scenes ****************************************************************/

    private void loadScenes() {
        this.mainScene = new Scene();
        this.mainScene.setBackground(new Background(0, 125, 58));
        physicsWorld = new PhysicsWorld(new Vector2(0,
                SensorManager.GRAVITY_EARTH), false);
        this.mainScene.registerUpdateHandler(physicsWorld);
        createWalls();
    }

    private void createWalls() {
        // TODO Auto-generated method stub
        FixtureDef WALL_FIX = PhysicsFactory.createFixtureDef(0.0f, 0.0f, 0.0f);
        Rectangle ground = new Rectangle(0, CAMERA_HEIGHT - 15, CAMERA_WIDTH,
                15, this.mEngine.getVertexBufferObjectManager());
            ground.setColor(new org.andengine.util.color.Color(15, 50, 0));
        PhysicsFactory.createBoxBody(physicsWorld, ground, BodyType.StaticBody,
                WALL_FIX);
        this.mainScene.attachChild(ground);
    }

    private void populateMainScene() {
        Sprite sPlayer = new Sprite(CAMERA_WIDTH / 2, CAMERA_HEIGHT / 2,
                playerTextureRegion, this.mEngine.getVertexBufferObjectManager());
        sPlayer.setRotation(45.0f);
        final FixtureDef PLAYER_FIX = PhysicsFactory.createFixtureDef(10.0f,
                1.0f, 0.0f);
        Body body = PhysicsFactory.createCircleBody(physicsWorld, sPlayer,
                BodyType.DynamicBody, PLAYER_FIX);
        this.mainScene.attachChild(sPlayer);
        physicsWorld.registerPhysicsConnector(new PhysicsConnector(sPlayer,
                body, true, false));

        text = new Text(0, 0, mFont, "PIIRAKKA    PELI",
                this.getVertexBufferObjectManager());
        this.mainScene.attachChild(text);
    }


/* Init Splash ****************************************************************/

    private void initSplashScene() {
        splashScene = new Scene();/*
        Sprite splash = new Sprite(
                splashTextureRegion.getWidth(),
                splashTextureRegion.getHeight(),
                splashTextureRegion,
                this.mEngine.getVertexBufferObjectManager());*/

        Sprite splash = new Sprite(splashTextureRegion.getWidth(),
                splashTextureRegion.getHeight(), splashTextureRegion,
                mEngine.getVertexBufferObjectManager())
        {
            @Override
            protected void preDraw(GLState pGLState, Camera pCamera)
            {
                super.preDraw(pGLState, pCamera);
                pGLState.enableDither();
            }
        };
        splashScene.setVisible(false);

        splash.setScale(0.5f);
        splash.setBlendFunction(GL10.GL_SRC_ALPHA, GL10.GL_ONE_MINUS_SRC_ALPHA);
        //float[] coord = splash.getSceneCenterCoordinates();
        //splash.setPosition(coord[0], coord[1]);
        splashScene.attachChild(splash);
    }
    
    
}
