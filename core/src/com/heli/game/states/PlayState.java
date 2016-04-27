package com.heli.game.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.heli.game.HeliMain;
import com.heli.game.gameObjects.Heli;
import com.heli.game.gameObjects.Tube;

/**
 * Created by B3f0r on 11-Apr-16.
 */
public class PlayState extends State {

    // TODO: Implement Viewport functionality

    // TODO: Add Interstetial Ads counter. The idea is to show them after 10 tries and 2 minutes played

    // TODO: Finish collision detection

    // TODO: Try to use Box2D for Heli physics

    // TODO: Add Ground and Intro "calm" screen (cs idea)

    private static final int TUBE_SPACING = 450;
    private static final int TUBE_COUNT = 6;

    private Heli heli;
    private Texture bg;
    private boolean gameOver;
    public final Viewport viewport;
    private String test;

    private Array<Tube> tubes;

    public PlayState(GameStateManager gameStateManager) {
        super(gameStateManager);
        this.viewport = new ExtendViewport(800,480,cam);
        heli = new Heli(5, 200);
        cam.setToOrtho(false, HeliMain.WIDTH, HeliMain.HEIGHT);
        bg = new Texture("day.png");

        tubes = new Array<Tube>();
        for (int i = 1; i <= TUBE_COUNT; i++) {
            tubes.add(new Tube(i * TUBE_SPACING + Tube.TUBE_WIDTH));
        }
        gameOver = false;
    }

    @Override
    protected void handleInput() {
        if (Gdx.input.isTouched()) {
            heli.hover();
        }
        if (gameOver) {
            gameStateManager.set(new EndgameState(gameStateManager));
        }
    }

    @Override
    public void update(float dt) {
        handleInput();
        heli.update(dt);
        cam.position.x = heli.getPosition().x + 150;
        if (heli.getPosition().y < 0) {
            gameOver = true;
        }
        for(Tube tube : tubes) {
            if (cam.position.x - cam.viewportWidth / 2 > tube.getPosTube().x + tube.getTube().getWidth()) {
                tube.reposition(tube.getPosTube().x + ((Tube.TUBE_WIDTH + TUBE_SPACING) * TUBE_COUNT));
            }
        }

        //  for (Tube obstacle : obstacles){
        //      if((cam.position.x - (cam.viewportWidth/2)) > obstacle.getPosTube())
        //  }
        cam.update();
    }

    @Override
    public void render(SpriteBatch spriteBatch) {
        spriteBatch.setProjectionMatrix(cam.combined);
        spriteBatch.begin();
        spriteBatch.draw(bg, cam.position.x - (cam.viewportWidth/2), 0);
        for(Tube tube : tubes){
            spriteBatch.draw(tube.getTube(), tube.getPosTube().x, tube.getPosTube().y);
        }
        //spriteBatch.draw(bg, 0, 0, HeliMain.WIDTH, HeliMain.HEIGHT);
        spriteBatch.draw(heli.getTexture(), heli.getPosition().x, heli.getPosition().y);
        spriteBatch.end();
    }

    @Override
    public void dispose() {
        bg.dispose();
        heli.dispose();
        for (Tube tube : tubes) {
            tube.dispose();
        }
    }
}
