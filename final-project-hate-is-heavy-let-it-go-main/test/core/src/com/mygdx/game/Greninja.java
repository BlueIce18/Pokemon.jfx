package com.mygdx.game;

import java.util.Random;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * The greninja pokemon object
 * 
 * @author Faisal, Jovan, Ryder, Shael
 * @version 1.0 2023-06-09
 */
public class Greninja extends Main {
    String type1 = "Water"; // First type of the pokemon
    String type2 = "Dark"; // Second type of the pokemon
    String name = "Greninja"; // Name of the pokemon to be displayed
    double health = 72; // base health of pokemon
    public double speed = 200; // base speed
    double attack = 47; // base attack
    double defense = 76; // base defense
    double damage = 0; // initializing damage to be calculated later
    double specialAttack = 51;// base special attack 
    String attackType;
    
    SpriteBatch batch;
    Texture img = new Texture("Greninja.png"); //greninja image 
    Sprite sprite;

    float posX; //position of greninja image 
    float posY;

    String effect;
    String attackName;

     //lists of information used for calculation damage for different types, attacks, and accuracies
    String[] attackNames = { "Dark Pulse", "Waterfall", "Hydro Pump", "Thief" };
    String[] attackTypes = { "Dark", "Water", "Water", "Dark" };
    String[] damages = { 80 + specialAttack + " dmg", 80 + attack + " dmg", 110 + specialAttack + " dmg",
            68 + attack + " dmg" };
    String[] accuracy = { "90% Accuracy", "100% Accuracy", "80% Accuracy", "100% Accuracy" };

    /**
     * The method to inctanciate greninja
     * 
     * @param width,  the width of the sprite
     * @param height, the height of the sprite
     */
    public Greninja(float width, float height) {
        sprite = new Sprite(img);
        sprite.setSize(width, height);
    }

/**
 * The method to draw the sprite
 * 
 * @param batch, the batch to draw the sprite onto
 * @param x, the sprite's x postion
 * @param y, the sprite's y position
 */
    public void Draw(SpriteBatch batch, float x, float y) {
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
        //calculating attack damage 
        if (currentMove == 1) { //damage if player uses move 1
            attackType = "dark";
            attackName = "Dark Pulse";

            if (hit(90) == true) {
                damage = 80 + specialAttack; 
            } else {
                damage = 0;
            }
        }

        if (currentMove == 2) { //damage if player uses move 2
            attackType = "water";
            attackName = "Waterfall";
            damage = 80 + attack; 
        }

        if (currentMove == 3) { //damage if player uses move 3
            attackType = "water";
            attackName = "Hydro Pump";
            if (hit(80) == true) {
                damage = 110 + specialAttack;
            } else {
                damage = 0;
            }
        }

        if (currentMove == 4) { //damage if player uses move 4
            attackType = "dark";
            attackName = "Thief";
            damage = 68 + attack;
        }
        return damage;
    }

    /**
     * The method to calculate damage done to the pokemon
     * 
     * @param attack, the damage received
     * @param type, the elemnt of the attack received
     * @return a double of the health remaining
     */
    public double damageTaken(double attack, String type) {
        //calculate damage taken 
        if (attack < defense) {
            attack = defense; // doing 0 damage if attack is not greater than defense
        }

        if (type == "grass" || type == "electric" || type == "fighting" || type == "bug" || type == "fairy") { //following if statements calculate multiplied damage based on type advantage/disadvantage
            health = health - (attack - defense) * 2;
            effect = "very effective";
        }

        else if (type == "fire" || type == "water" || type == "ice" || type == "ghost" || type == "dark"
                || type == "steel") {
            health = health - (attack - defense) * 0.5;
            effect = "not very effective";
        }

        else if (type == "psychic") {
            health = health - 0;
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
