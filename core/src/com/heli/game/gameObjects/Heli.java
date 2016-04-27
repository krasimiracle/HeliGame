package com.heli.game.gameObjects;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector3;

/**
 * Created by B3f0r on 11-Apr-16.
 */
public class Heli {

    // TODO: Change Heli png and add animation.

    private static final int GRAVITY = -40;
    private static final int MOVEMENT = 200;
    private Vector3 position;
    private Vector3 velocity;
    private Texture heli;


    public Heli(int x, int y) {
        this.position = new Vector3(x, y, 0);
        velocity = new Vector3(0, 0, 0);
        heli = new Texture("heli.png");
    }

    public void update(float dt) {

        if (position.y > 0) {
            velocity.add(5, GRAVITY, 0);
        }
        velocity.scl(dt);
        position.add(MOVEMENT * dt, velocity.y, 0);
        if (position.y < 0) {
            position.y = 0;
        }

        velocity.scl(1 / dt);
    }

    public Vector3 getPosition() {
        return position;
    }

    public Texture getTexture() {
        return heli;
    }

    public void hover() {
        this.velocity.y = 250;

    }

    public void dispose() {
        heli.dispose();
    }

}
