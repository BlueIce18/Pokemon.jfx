package com.mygdx.game;

import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.Input.Keys;

/**
 * The class to move the player in openworld
 * 
 * @author Faisal, Jovan, Ryder, Shael
 * @version 1.0 2023-06-09
 */
public class PlayerController extends InputAdapter {

    private Player player;

    /**
     * Contructor for this class
     * 
     * @param p, the player object which will be controlled
     */
    public PlayerController(Player p) {
        this.player = p;
    }

    /**
     * Method to get the movement of a key input
     * 
     * @param keyCode, the key which was pressed
     * @return false always
     */
    public boolean keyDown(int keyCode) {

        if (keyCode == Keys.UP) {
            player.move(0, 1);

        }
        if (keyCode == Keys.DOWN) {
            player.move(0, -1);

        }
        if (keyCode == Keys.RIGHT) {
            player.move(1, 0);

        }
        if (keyCode == Keys.LEFT) {
            player.move(-1, 0);

        }
        return false;
    }
}
