package com.mygdx.game;
import com.badlogic.gdx.graphics.g2d.Sprite;

/**
 * The player object in the openworld
 * 
 * @author Faisal, Jovan, Ryder, Shael
 * @version 1.0 2023-06-09
 */
public class Player extends Sprite {
    private int x; // current X position of player 
    private int y; // current Y position of player

    /**
     * Constructor of the player object
     * 
     * @param x, x position of the player
     * @param y, y position of the player
     */
    public Player(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Method to move player
     * 
     * @param dx, change in x position of player
     * @param dy, change in y position of player
     */
    public void move(int dx, int dy) {
        x += dx; //moving the player left and right
        y += dy; //moving the player up and down

    }

    /**
     * Method to get the x position of the player
     * 
     * @return int of the x psoition of the player
     */
    public float getX() {
        return x;

    }

    /**
     * Method to get the y position of the player
     * 
     * @return int of the y psoition of the player
     */
    public float getY() {
        return y;

    }
}
