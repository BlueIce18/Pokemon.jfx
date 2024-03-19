package com.mygdx.game;
import java.util.Random;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * The mewtwo pokemon object
 * 
 * @author Faisal, Jovan, Ryder, Shael
 * @version 1.0 2023-06-09
 */
public class Mewtwo extends Main {
    String attackType; //current attack type
    String name = "Mewtwo"; // Name of pokemon to be displayed
    double health = 106; // base health of pokemon
    double speed = 130; // base speed
    double attack = 55; //base attack 
    double defense = 94; // base defense
    double damage = 0; //damage to be calculated 
    double specialAttack = 70; // base special attack 
    String attackName; // name of current attack 
    String effect; // effectiveness of current move

    SpriteBatch batch;
    Texture img = new Texture("Mewtwo.png"); //Mewtwo image
    Sprite sprite;

    float posX; //position of Mewtwo image
    float posY;

     //lists of information used for calculation damage for different types, attacks, and accuracies
    String[] attackNames = { "Psych", "Ice Beam", "Fire Beam", "Zen Headbutt" };
    String[] attackTypes = { "Psychic", "Ice", "Fire", "Normal" };
    String[] damages = {80 + specialAttack + " dmg", 75 + attack + " dmg", 60 + specialAttack + " dmg", 105 + attack + " dmg"};
    String[] accuracy = {"100% Accuracy", "100% Accuracy", "100% Accuracy", "100% Accuracy"};


    /**
 * The method to inctanciate mewtwo
 * 
 * @param width, the width of the sprite
 * @param height, the height of the sprite
 */
    public Mewtwo(float width, float height) {
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
            attackType = "psychic";
            attackName = "Psych";
            damage = 80 + specialAttack;
        }

        if (currentMove == 2) { //damage if player uses move 2
            attackType = "ice";
            attackName = "Ice Beam";
            damage = 75 + attack; 
        }

        if (currentMove == 3) { //damage if player uses move 3
            attackType = "fire";
            attackName = "Fire Beam";
            damage = 60 + specialAttack;
        }

        if (currentMove == 4) { //damage if player uses move 4
            attackType = "normal";
            attackName = "Zen Headbutt";
            damage = 110 + attack;
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

        if (type == "ghost" || type == "bug" || type == "dark") { //following if statements calculate multiplied damage based on type advantage/disadvantage
            health = health - (attack - defense) * 2;
            effect = "very effective";
        }

        else if (type == "fighting" || type == "psychic") {
            health = health - (attack - defense) * 0.5;
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
