package com.shr4pnel.casino;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.crashinvaders.vfx.VfxManager;
import com.crashinvaders.vfx.effects.CrtEffect;
import com.crashinvaders.vfx.effects.FilmGrainEffect;
import com.crashinvaders.vfx.effects.GaussianBlurEffect;
import com.rafaskoberg.gdx.typinglabel.TypingAdapter;
import com.rafaskoberg.gdx.typinglabel.TypingLabel;
import com.shr4pnel.casino.audio.SoundEffect;
import com.shr4pnel.casino.builders.LabelBuilder;
import com.shr4pnel.casino.console.ConsoleManager;
import com.shr4pnel.casino.scene.SceneManager;
import com.shr4pnel.casino.style.StyleManager;

/** {@link com.badlogic.gdx.ApplicationListener} implementation shared by all platforms. */
public class Casino extends ApplicationAdapter {
    private SpriteBatch batch;
    private Texture background;
    private FitViewport viewport;
    private Texture button;
    private ConsoleManager console;
    private final AssetManager assetManager = new AssetManager();
    private boolean assetsLoaded = false;
    private StyleManager styleManager;
    private Skin skin;
    private Stage stage;
    private TypingLabel topLabel;
    private TypingLabel ramLabel;
    private TypingLabel middleLabel;
    private Window rootWindow;
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
        stage = new Stage(viewport);

        // enqueue assets for loading
        assetManager.load("bg/backdrop.png", Texture.class);
        assetManager.load("buttons/blackjack.jpg", Texture.class);

        // get active skin
        skin = StyleManager.getSkin();

        // post processing
        vfxManager = new VfxManager(Pixmap.Format.RGBA8888);
        crtEffect = new CrtEffect();
        filmGrainEffect = new FilmGrainEffect();
        gaussianBlurEffect = new GaussianBlurEffect();
        vfxManager.addEffect(gaussianBlurEffect);
        vfxManager.addEffect(crtEffect);

        // todo fix input processing in intro (console has method for this)
        if (SceneManager.getActiveScene() == SceneManager.scene.INTRO) {
            Gdx.input.setInputProcessor(stage);
            console.getConsole().resetInputProcessing();
        }

        // todo view in seperate file
        rootWindow = new Window("", skin);
        rootWindow.setSize(800, 450);
        rootWindow.align(Align.top);
        topLabel = labelBuilder
            .start("**** COMMODORE 64 BASIC V2 ****\n64K  RAM  SYSTEM  38911  BASIC  BYTES  FREE")
            .alignCenter()
            .noDelay()
            .build();

        ramLabel = labelBuilder
            .start("READY.")
            .noDelay()
            .build();

        middleLabel = labelBuilder
            .start("{SPEED=0.3}LOAD \"CASINO.PRG\",8{RESET}")
            .build();

        middleLabel.addTypingListener(new TypingAdapter() {
            public void end() {
                SoundEffect.play("sound/read_alt.ogg", 0.1f);
            }
        });

        rootWindow.add(topLabel);
        rootWindow.add().height(50).row();
        rootWindow.add(ramLabel).left().row();
        rootWindow.add().height(50).row();
        rootWindow.add(middleLabel).left().row();
        stage.addActor(rootWindow);
    }

    @Override
    public void render() {
        if (!assetsLoaded && assetManager.update()) {
            background = assetManager.get("bg/backdrop.png", Texture.class);
            button = assetManager.get("buttons/blackjack.jpg", Texture.class);
            assetsLoaded = true;
            console.getConsole().log("Asset loading complete");
        }

        startPostProcessing();
        draw();
        renderPostProcessing();
        console.getConsole().draw();
    }

    @Override
    public void dispose() {
        batch.dispose();
        console.destroy();
        assetManager.dispose();
        vfxManager.dispose();
        crtEffect.dispose();
        filmGrainEffect.dispose();
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
                case INTRO -> {
                    if (!hasIntroLoadSoundPlayed)
                        SoundEffect.play("sound/load.ogg");
                    hasIntroLoadSoundPlayed = true;
                    ScreenUtils.clear(Color.BLACK);
                    stage.act(Gdx.graphics.getDeltaTime());
                    stage.draw();
                }
                case BLACKJACK -> {
                    batch.begin();
                    ScreenUtils.clear(Color.BLACK);
                    float maxWidth = viewport.getWorldWidth();
                    float maxHeight = viewport.getWorldHeight();
                    batch.draw(background, 0, 0, maxWidth, maxHeight);
                    batch.draw(button, 50, 50, 25, 25);
                    batch.end();
                }
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
}
