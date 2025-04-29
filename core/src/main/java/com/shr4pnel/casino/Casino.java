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

import com.rafaskoberg.gdx.typinglabel.TypingLabel;

import com.shr4pnel.casino.audio.SoundEffectHelper;
import com.shr4pnel.casino.builders.LabelBuilder;
import com.shr4pnel.casino.console.ConsoleManager;
import com.shr4pnel.casino.scene.SceneManager;
import com.shr4pnel.casino.style.StyleManager;
import com.shr4pnel.casino.views.Intro;

// if you're lost, what you're looking for is probably in here ;)

/**
 * {@link com.badlogic.gdx.ApplicationListener} implementation shared by all platforms.
 */
public class Casino extends ApplicationAdapter {
    private SpriteBatch batch;
    private Texture background, button;
    private FitViewport viewport;
    private ConsoleManager console;
    private final AssetManager assetManager = new AssetManager();
    private boolean assetsLoaded = false;
    private StyleManager styleManager;
    private Stage introStage, menuStage;
    private TypingLabel asciiLabel;
    private Window introRoot;
    private final LabelBuilder labelBuilder = new LabelBuilder();
    private VfxManager vfxManager;
    private CrtEffect crtEffect;
    private FilmGrainEffect filmGrainEffect;
    private GaussianBlurEffect gaussianBlurEffect;
    private boolean hasIntroLoadSoundPlayed = false;

    @Override
    public void create() {
        batch = new SpriteBatch();
        viewport = new FitViewport(800, 450);
        console = new ConsoleManager();
        introStage = new Stage(viewport);
        menuStage = new Stage(viewport);

        // enqueue assets for loading
        queueAssets();

        // post processing
        initialPostProcess();

        SoundEffectHelper.preload();

        // load intro scene2d markup
        introRoot = Intro.get();
        introStage.addActor(introRoot);

        asciiLabel = labelBuilder
            .start("test")
            .build();

        menuStage.addActor(asciiLabel);
    }

    private void queueAssets() {
        assetManager.load("bg/backdrop.png", Texture.class);
        assetManager.load("buttons/blackjack.jpg", Texture.class);
    }

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
        if (!assetsLoaded && assetManager.update())
            initialRenderAssets();

        startPostProcessing();
        draw();
        renderPostProcessing();
        // console always drawn on top, outside of post-processing (easier to read without effects, the text is small!)
        console.draw();
    }

    private void initialRenderAssets() {
        background = assetManager.get("bg/backdrop.png", Texture.class);
        button = assetManager.get("buttons/blackjack.jpg", Texture.class);
        assetsLoaded = true;
        console.getConsole().log("Asset loading complete");
    }

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
    }

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

    private void startPostProcessing() {
        vfxManager.cleanUpBuffers();
        vfxManager.beginInputCapture();
    }

    private void renderPostProcessing() {
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
        batch.begin();
        ScreenUtils.clear(Color.BLACK);
        float maxWidth = viewport.getWorldWidth();
        float maxHeight = viewport.getWorldHeight();
        batch.draw(background, 0, 0, maxWidth, maxHeight);
        batch.draw(button, 50, 50, 25, 25);
        batch.end();
    }

    private void renderMenu() {
        ScreenUtils.clear(Color.BLACK);
        menuStage.act(Gdx.graphics.getDeltaTime());
        menuStage.draw();
    }
}
