package com.shr4pnel.casino;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.FitViewport;

import com.crashinvaders.vfx.VfxManager;
import com.crashinvaders.vfx.effects.CrtEffect;
import com.crashinvaders.vfx.effects.FilmGrainEffect;
import com.crashinvaders.vfx.effects.GaussianBlurEffect;

import com.shr4pnel.casino.audio.SoundEffectHelper;
import com.shr4pnel.casino.builders.LabelBuilder;
import com.shr4pnel.casino.console.ConsoleManager;
import com.shr4pnel.casino.scene.*;
import com.shr4pnel.casino.style.StyleManager;
import com.shr4pnel.casino.util.TextureManager;

import java.util.HashMap;
import java.util.Map;

// if you're lost, what you're looking for is probably in here ;)

/**
 * Application entry point
 * {@link com.badlogic.gdx.ApplicationListener} implementation shared by all platforms.
 * @author shrapnelnet
 * @since 0.1.0
 */
public class Casino extends ApplicationAdapter {
    private SpriteBatch batch;
    private FitViewport viewport;
    private static ConsoleManager console;
    private final AssetManager assetManager = new AssetManager();
    private boolean assetsLoaded = false;
    private StyleManager styleManager;
    private Stage introStage, menuStage, blackjackStage;
    private Window introRoot;
    private final LabelBuilder labelBuilder = new LabelBuilder();
    private VfxManager vfxManager;
    private CrtEffect crtEffect;
    private FilmGrainEffect filmGrainEffect;
    private GaussianBlurEffect gaussianBlurEffect;
    private boolean hasIntroLoadSoundPlayed = false;
    private Table menuRoot, blackjackRoot;
    private Blackjack blackjack;
    private Menu menu;
    private final Map<SceneManager.Scene, ManagedButtonScene> sceneInstanceMap = new HashMap<>();
    private TextureManager textureManager;

    @Override
    public void create() {
        batch = new SpriteBatch();
        viewport = new FitViewport(800, 450);
        console = new ConsoleManager();

        introStage = new Stage(viewport);
        menuStage = new Stage(viewport);
        blackjackStage = new Stage(viewport);

        // preload textures
        textureManager = new TextureManager();
        textureManager.preload();

        // post processing
        initialPostProcess();

        // preload all audio files in assets/sound
        SoundEffectHelper.preload();

        // load intro scene2d markup
        introRoot = Intro.get();
        introStage.addActor(introRoot);

        // load menu scene2d markup
        menu = new Menu();
        menuRoot = menu.get();
        menuStage.addActor(menuRoot);

        // load blackjack scene2d markup
        blackjack = new Blackjack();
        blackjackRoot = blackjack.get();
        blackjackStage.addActor(blackjackRoot);

        // store scene instances in map for scenemanager accesses and implicit ownership within Casino
        sceneInstanceMap.put(SceneManager.Scene.MENU, menu);
        sceneInstanceMap.put(SceneManager.Scene.BLACKJACK, blackjack);
    }

    /**
     * Set important post-processing variables before rendering
     */
    private void initialPostProcess() {
        vfxManager = new VfxManager(Pixmap.Format.RGBA8888);
        crtEffect = new CrtEffect();
        filmGrainEffect = new FilmGrainEffect();
        gaussianBlurEffect = new GaussianBlurEffect();
        vfxManager.addEffect(gaussianBlurEffect);
        vfxManager.addEffect(crtEffect);
    }

    @Override
    public void render() {
//        if (!assetsLoaded && assetManager.update())
//            initialRenderAssets();

        startPostProcessing();
        draw();
        renderPostProcessing();
        // console always drawn on top, outside of post-processing (easier to read without effects, the text is small!)
        console.draw();
    }

//    private void initialRenderAssets() {
//        assetManager.load("card/card.jpg", Texture.class);
//        assetsLoaded = true;
//    }

    @Override
    public void dispose() {
        batch.dispose();
        console.destroy();
        assetManager.dispose();
        vfxManager.dispose();
        crtEffect.dispose();
        filmGrainEffect.dispose();
        SoundEffectHelper.dispose();
    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width, height, true);
        vfxManager.resize(width, height);
        console = new ConsoleManager();
    }

    /**
     * Get active scene every frame, render correct scene and pass work to individual rendering method
     */
    private void draw() {
        if (assetManager.update()) {
            batch.setProjectionMatrix(viewport.getCamera().combined);
            viewport.apply();

            switch (SceneManager.getActiveScene()) {
                case INTRO -> renderIntro();
                case BLACKJACK -> renderBlackjack();
                case MENU -> renderMenu();
            }
        }
    }

    /**
     * Perform post-processing tasks that need to be done before anything can be drawn
     */
    private void startPostProcessing() {
        if (StyleManager.getPostProcessingDisabled())
            return;
        vfxManager.cleanUpBuffers();
        vfxManager.beginInputCapture();
    }

    /**
     * Apply post-processing effects
     */
    private void renderPostProcessing() {
        if (StyleManager.getPostProcessingDisabled())
            return;
        vfxManager.endInputCapture();
        vfxManager.applyEffects();
        vfxManager.renderToScreen();
    }

    private void renderIntro() {
        if (!hasIntroLoadSoundPlayed)
            SoundEffectHelper.play("sound/load.ogg");
        hasIntroLoadSoundPlayed = true;
        ScreenUtils.clear(Color.BLACK);
        introStage.act(Gdx.graphics.getDeltaTime());
        introStage.draw();
    }

    private void renderBlackjack() {
        ScreenUtils.clear(Color.BLACK);
        blackjackStage.act(Gdx.graphics.getDeltaTime());
        blackjackStage.draw();
    }

    private void renderMenu() {
        ScreenUtils.clear(Color.BLACK);
        menuStage.act(Gdx.graphics.getDeltaTime());
        menuStage.draw();
    }

    public static ConsoleManager getActiveConsole() {
        return console;
    }

    /**
     * Get the casino as an object, useful for getting important info about active states
     * @return The running instance of casino
     */
    public static Casino getInstance() {
        return (Casino) Gdx.app.getApplicationListener();
    }

    /**
     * Get the instance of a game (stored in sceneInstanceMap)
     * @param s The scene to get
     * @return The scene, as a ManagedButtonGame
     */
    public ManagedButtonGame getGameInstance(SceneManager.Scene s) {
        return (ManagedButtonGame) getSceneInstance(s);
    }

    /**
     * Get the instance of a scene (stored in sceneInstanceMap)
     * @param s The scene to get
     * @return The scene, as a ManagedButtonScene
     */
    public ManagedButtonScene getSceneInstance(SceneManager.Scene s) {
        return sceneInstanceMap.get(s);
    }

    /**
     * @return The active texture manager
     */
    public TextureManager getTextureManagerInstance() {
        return textureManager;
    }
}
