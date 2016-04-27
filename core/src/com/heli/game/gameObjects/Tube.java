package com.heli.game.gameObjects;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

import java.util.Random;


/**
 * Created by B3f0r on 13-Apr-16.
 */
public class Tube {

    // TODO: Fix Tube spawn points

    private static final int LOWEST_OPENING = 120;
    private static final int FLUCTUATION = 130;
    public static final int TUBE_WIDTH = 52;
    private static final int TUBE_GAP = 100;
    private Texture tube;
    private Vector2 posTube;
    private Random rand;
    private Rectangle boundsTube;

    public Vector2 getPosTube() {
        return posTube;
    }

    public Texture getTube() {
        return tube;
    }

    public Tube(float x) {
        this.tube = new Texture("tube.png");
        rand = new Random();

        posTube = new Vector2(x, rand.nextInt(FLUCTUATION) + TUBE_GAP);

        boundsTube = new Rectangle(posTube.x,posTube.y,tube.getWidth(),tube.getHeight());
    }

    public void reposition(float x){
        posTube.set(x, rand.nextInt(FLUCTUATION) + TUBE_GAP + LOWEST_OPENING);
        boundsTube.setPosition(posTube.x,posTube.y);
    }

    public boolean collides(Rectangle player){
        return player.overlaps(boundsTube);
    }

    public void dispose(){
        tube.dispose();
    }

}
