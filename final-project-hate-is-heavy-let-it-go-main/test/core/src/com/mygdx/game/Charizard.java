package com.mygdx.game;

import java.util.Random;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * The charizard pokemon object
 * 
 * @author Faisal, Jovan, Ryder, Shael
 * @version 1.0 2023-06-09
 */
public class Charizard extends Main {
    // declare variables
    String attackType;
    String name = "Charizard"; // Name of pokemon to be displayed
    double health = 78; // base pokemon health
    double attack = 42; // base attack
    public double speed = 100; // base speed
    double defense = 85; // base defense
    double damage = 0; // initializing damage to be calculated later
    double specialAttack = 54; // base special attack 

    String attackName; //name of attack
    String effect; // how effective a move is 

    SpriteBatch batch;
    Texture img = new Texture("Charizard.png"); // picture of charizard
    Sprite sprite;

    float posX; //position for image
    float posY;

    //lists of information used for calculation damage for different types, attacks, and accuracies
    String[] attackNames = { "Flamethrower", "Air Slash", "Flare Blitz", "Fly" };
    String[] attackTypes = { "Fire", "Flying", "Fire", "Flying" };
    String[] damages = { 90 + specialAttack + " dmg", 76 + specialAttack + " dmg", 120 + attack + " dmg",108 + attack + " dmg" };
    String[] accuracy = { "100% Accuracy", "95% Accuracy", "90% Accuracy", "85% Accuracy" };

    /**
     * The method to inctanciate charizard
     * 
     * @param width,  the width of the sprite
     * @param height, the height of the sprite
     */
    public Charizard(float width, float height) {
        // create sprite
        sprite = new Sprite(img);
        sprite.setSize(width, height);
    }

    /**
     * The method to draw the sprite
     * 
     * @param batch, the batch to draw the sprite onto
     * @param x,     the sprite's x postion
     * @param y,     the sprite's y position
     */
    public void Draw(SpriteBatch batch, float x, float y) {
        // draw sprite
        sprite.setPosition(x, y);
        sprite.draw(batch);
    }

    /**
     * The method to get the damage done by a selected attack
     * 
     * @param currentMove, the attack to be preformed
     * @return double of the damage done by the attack
     */
    public double attackDamage(int currentMove) {
        //calculate attack damage
        if (currentMove == 1) { //damage if player uses move 1
            attackType = "fire";
            attackName = "Flamethrower";
            damage = 90 + specialAttack; 
        }

        if (currentMove == 2) {//damage if player uses move 2
            attackType = "flying";
            attackName = "Air Slash";
            if (hit(95) == true) {
                damage = 76 + specialAttack; 
            } else {
                damage = 0;
            }
        }

        else if (currentMove == 3) { //damage if player uses move 3
            attackName = "Flare Blitz";
            attackType = "fire";
            if (hit(90) == true) {
                damage = 120 + attack;
            } else {
                damage = 0;
            }
        }

        if (currentMove == 4) { //damage if player uses move 4
            attackType = "flying";
            attackName = "Fly";
            if (hit(85) == true) {
                damage = 108 + attack;
            } else {
                damage = 0;
            }
        }
        return damage;

    }

    /**
     * The method to calculate damage done to the pokemon
     * 
     * @param attack, the damage received
     * @param type,   the elemnt of the attack received
     * @return a double of the health remaining
     */
    public double damageTaken(double attack, String type) {
        // calculate damage taken
        if (attack < defense) {
            attack = defense; // doing 0 damage if attack is not greater than defense
        }

        if (type == "water" || type == "electric") { //following if statements calculate multiplied damage based on type advantage/disadvantage
            health = health - (attack - defense) * 2;
            effect = "very effective";
        }

        else if (type == "rock") {
            health = health - (attack - defense) * 4;
            effect = "really really effective";
        }

        else if (type == "ground") {
            health = health - 0;
            effect = "not very effective";
        }

        else if (type == "fire" || type == "fighting" || type == "steel" || type == "fairy" || type == "ice") {
            health = health - (attack - defense) * 0.5;
            effect = "not very effective";
        }

        else if (type == "bug" || type == "grass") {
            health = health - (attack - defense) * 0.25;
            effect = "not very effective";
        }

        else {
            health = health - (attack - defense);
            effect = "effective";
        }
        return health;
    }

    /**
     * Method to get how effective an attack was
     * 
     * @return a string of the text to display of how effective an attack was
     */
    public String getEffect() {
        // get how effective an attack was
        return effect;
    }

    /**
     * Method to calculate if an attakc hits or not
     * 
     * @param accuracy, the probabiblty of the attack landing
     * @return a boolean of weather the attack hit or not
     */
    public boolean hit(int accuracy) {
        //method called to calculate whether the move hits or not, using a random number from 1-100
        Random random = new Random();
        int result = random.nextInt(1, 101);
        if (result <= accuracy) {
            return true;
        } else {
            return false;
        }
    }
}
