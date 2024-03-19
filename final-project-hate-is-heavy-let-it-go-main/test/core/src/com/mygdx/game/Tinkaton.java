package com.mygdx.game;
import java.util.Random;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * The tinkaton pokemon object
 * 
 * @author Faisal, Jovan, Ryder, Shael
 * @version 1.0 2023-06-09
 */
public class Tinkaton extends Main {
    String type1 = "Fairy"; // First type of the pokemon
    String type2 = "Steel"; // Second type of the pokemon
    String attackType; // type of current attack 

    String name = "TINKATON"; // Name of pokemon to be displayed
    double health = 85; // base health of pokemon
    public double speed = 94; // base speed
    double attack = 42; // base attack
    double defense = 87; // base defense
    String effect; //effectiveness of current attack 
    String attackName; //name of current attack 
    double damage = 0; //damage to be calculated
    double specialAttack = 50; // base special attack 

    SpriteBatch batch;
    Texture img = new Texture("Tinkaton.png"); //Tinkaton image 
    Sprite sprite;

    float posX; //position of Tinkaton image 
    float posY;

     //lists of information used for calculation damage for different types, attacks, and accuracies
    String[] attackNames = { "Draining Kiss", "Play Rough", "Steel Beam", "Metal Claw" };
    String[] attackTypes = { "Fairy", "Fairy", "Steel", "Steel" };
    String[] damages = {100 + specialAttack + " dmg", 90 + attack + " dmg", 140 + specialAttack + " dmg", 75 + attack + " dmg"};
    String[] accuracy = {"90% Accuracy", "85% Accuracy", "70% Accuracy", "100% Accuracy"};

    /**
 * The method to inctanciate tinkaton
 * 
 * @param width, the width of the sprite
 * @param height, the height of the sprite
 */
    public Tinkaton(float width, float height) {
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
            attackType = "fairy";
            attackName = "Draining Kiss";
            if (hit(90) == true) {
            damage = 100 + specialAttack; 
            } else {
                damage = 0;
            }
        }

        if (currentMove == 2) { //damage if player uses move 2
            attackType = "fairy";
            attackName = "Play Rough";
            if (hit(85) == true) {
                damage = 90 + attack; 
            } else {
                damage = 0;
            }
        }

        if (currentMove == 3) { //damage if player uses move 3
            attackType = "steel";
            attackName = "Steel Beam";
            if (hit(70) == true) {
                damage = 140 + specialAttack;
            } else {
                damage = 0;
            }
        }

        if (currentMove == 4) { //damage if player uses move 4
            attackType = "steel";
            attackName = "Metal Claw";
            damage = 75 + attack;
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

        health = health - (attack - defense) * 0.50; // tinkaton is too strong, always do half damage to tinkaton.
        effect = "weak. Tinkaton too strong!!";

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
