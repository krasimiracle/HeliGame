package com.heli.game.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.heli.game.HeliMain;
import com.heli.game.adHandling.AdHandler;

/**
 * Created by B3f0r on 14-Apr-16.
 */
public class EndgameState extends State {

    // TODO: Show banner ads only on EndGame Screen

    // TODO: Show distance traveled and points acquired. Add Play again and Quit buttons

    private Texture background;
    AdHandler handler;
    private boolean toggle = true;


    public EndgameState(GameStateManager gameStateManager) {
        super(gameStateManager);
        //this.handler = handler;
        cam.setToOrtho(false, HeliMain.WIDTH/2, HeliMain.HEIGHT / 2);
        this.background = new Texture("grey_panel.png");
    }

    @Override
    protected void handleInput() {
        if (Gdx.input.justTouched()) {
            gameStateManager.set(new PlayState(gameStateManager));
            //toggle = false;
        }
    }

    @Override
    public void update(float dt) {
        handleInput();
    }

    @Override
    public void render(SpriteBatch spriteBatch) {
        spriteBatch.begin();
        //handler.showAds(this.toggle);
        spriteBatch.setProjectionMatrix(cam.combined);
        spriteBatch.draw(this.background, cam.position.x - this.background.getWidth()/2,cam.position.y);
        spriteBatch.end();
    }

    @Override
    public void dispose() {
        this.background.dispose();
    }
}
