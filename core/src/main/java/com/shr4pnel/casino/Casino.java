package com.shr4pnel.casino;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
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
    private Stage introStage, menuStage, blackjackStage, navigationStage,pokerStage, slotStage , rouletteStage, lootboxesStage;
    private Window introRoot;
    private VfxManager vfxManager;
    private CrtEffect crtEffect;
    private FilmGrainEffect filmGrainEffect;
    private GaussianBlurEffect gaussianBlurEffect;
    private boolean hasIntroLoadSoundPlayed = false;
    private Table menuRoot, blackjackRoot, navigationRoot, pokerRoot, slotRoot, rouletteRoot, lootboxesRoot ;
    private Blackjack blackjack;
    private Roulette roulette;
    private Menu menu;
    private Navigation navigation;
    private Poker poker;
    private Lootboxes lootboxes;
    private final Map<SceneManager.Scene, ManagedButtonScene> sceneInstanceMap = new HashMap<>();
    private TextureManager textureManager;
    private Slots slots;

    @Override
    public void create() {
        batch = new SpriteBatch();
        viewport = new FitViewport(800, 450);
        console = new ConsoleManager();

        introStage = new Stage(viewport);
        menuStage = new Stage(viewport);
        blackjackStage = new Stage(viewport);
        navigationStage = new Stage(viewport);
        pokerStage= new Stage(viewport);
        slotStage= new Stage(viewport);
        rouletteStage= new Stage(viewport);
        lootboxesStage= new Stage(viewport);

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

        navigation = new Navigation();
        navigationRoot = navigation.get();
        navigationStage.addActor(navigationRoot);

        poker = new Poker();
        pokerRoot = poker.get();
        pokerStage.addActor(pokerRoot);

        slots = new Slots();
        slotRoot = slots.get();
        slotStage.addActor(slotRoot);

        roulette = new Roulette();
        rouletteRoot = roulette.get();
        rouletteStage.addActor(rouletteRoot);

        lootboxes = new Lootboxes();
        lootboxesRoot = lootboxes.get();
        lootboxesStage.addActor(lootboxesRoot);


        // store scene instances in map for scenemanager accesses and implicit ownership within Casino
        sceneInstanceMap.put(SceneManager.Scene.MENU, menu);
        sceneInstanceMap.put(SceneManager.Scene.BLACKJACK, blackjack);
        sceneInstanceMap.put(SceneManager.Scene.NAVIGATION, navigation);
        sceneInstanceMap.put(SceneManager.Scene.POKER, poker);
        sceneInstanceMap.put(SceneManager.Scene.SLOTS, slots);
        sceneInstanceMap.put(SceneManager.Scene.ROULETTE, roulette);
        sceneInstanceMap.put(SceneManager.Scene.LOOTBOXES, lootboxes);
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
                case NAVIGATION -> renderNavigation();
                case POKER -> renderPoker();
                case SLOTS -> renderSlots();
                case ROULETTE -> renderRoulette();
                case LOOTBOXES -> renderLootboxes();
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

    private void renderNavigation() {
        ScreenUtils.clear(Color.BLACK);
        navigationStage.act(Gdx.graphics.getDeltaTime());
        navigationStage.draw();
    }
    private void renderPoker() {
        ScreenUtils.clear(Color.BLACK);
        pokerStage.act(Gdx.graphics.getDeltaTime());
        pokerStage.draw();
    }
    private void renderSlots() {
        ScreenUtils.clear(Color.BLACK);
        slotStage.act(Gdx.graphics.getDeltaTime());
        slotStage.draw();
    }
    private void renderRoulette() {
        ScreenUtils.clear(Color.BLACK);
        rouletteStage.act(Gdx.graphics.getDeltaTime());
        rouletteStage.draw();
    }
    private void renderLootboxes() {
        ScreenUtils.clear(Color.BLACK);
        lootboxesStage.act(Gdx.graphics.getDeltaTime());
        lootboxesStage.draw();
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
