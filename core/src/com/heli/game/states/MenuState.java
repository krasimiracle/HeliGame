package com.heli.game.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.heli.game.HeliMain;

/**
 * Created by B3f0r on 11-Apr-16.
 */
public class MenuState extends State {

    private Texture background;
    private Texture playButton;


    public MenuState(GameStateManager gameStateManager) {
        super(gameStateManager);
        cam.setToOrtho(false, HeliMain.WIDTH/2, HeliMain.HEIGHT / 2);
        background = new Texture("bg.png");
        playButton = new Texture("playbtn.png");
    }

    @Override
    public void handleInput() {
        if (Gdx.input.justTouched()) {
            gameStateManager.set(new PlayState(gameStateManager));
        }
    }

    @Override
    public void update(float dt) {
        handleInput();
    }

    @Override
    public void render(SpriteBatch spriteBatch) {
        spriteBatch.begin();
        spriteBatch.setProjectionMatrix(cam.combined);
        spriteBatch.draw(background,0,0, HeliMain.WIDTH,HeliMain.HEIGHT);
        spriteBatch.draw(playButton, cam.position.x - playButton.getWidth()/2,cam.position.y);
        spriteBatch.end();
    }

    @Override
    public void dispose() {
        background.dispose();
        playButton.dispose();
    }
}
