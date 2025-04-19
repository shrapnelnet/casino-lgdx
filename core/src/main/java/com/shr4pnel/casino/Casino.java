package com.shr4pnel.casino;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.shr4pnel.casino.console.ConsoleManager;

/** {@link com.badlogic.gdx.ApplicationListener} implementation shared by all platforms. */
public class Casino extends ApplicationAdapter {
    private SpriteBatch batch;
    private Texture background;
    private FitViewport viewport;
    private Texture button;
    private ConsoleManager console;

    @Override
    public void create() {
        batch = new SpriteBatch();
        background = new Texture("bg/backdrop.png");
        viewport = new FitViewport(160, 90);
        button = new Texture("buttons/blackjack.jpg");
        console = new ConsoleManager();
        console.getExecutor().help();
    }

    @Override
    public void render() {
        draw();
    }

    @Override
    public void dispose() {
        batch.dispose();
        console.destroy();
    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width, height, true);
    }

    private void draw() {
        ScreenUtils.clear(Color.BLACK);
        batch.setProjectionMatrix(viewport.getCamera().combined);
        viewport.apply();
        batch.begin();
        float maxWidth = viewport.getWorldWidth();
        float maxHeight = viewport.getWorldHeight();
        batch.draw(background, 0, 0, maxWidth, maxHeight);
        batch.draw(button, 50, 50, 25, 25);
        batch.end();
        console.getConsole().draw();
    }
}
