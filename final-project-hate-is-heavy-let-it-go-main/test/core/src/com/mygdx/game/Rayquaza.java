package com.mygdx.game;
import java.util.Random;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * The rayquaza pokemon object
 * 
 * @author Faisal, Jovan, Ryder, Shael
 * @version 1.0 2023-06-09
 */
public class Rayquaza extends Main {
    String type1 = "Dragon"; // First type of the pokemon
    String type2 = "Flying"; // Second type of the pokemon
    String attackType; //type of current attack 
    String effect = ""; //effectiveness of current attack
    String name = "Rayquaza"; // Name of pokemon to be displayed
    double health = 105; // base health of pokemon
    public double speed = 95; // base speed
    double attack = 65; // base attack 
    double defense = 95; // base defense
    String attackName; // name of current attack 
    double damage = 0; // damage to be calculated
    double specialAttack = 65; // base special attack 

     //lists of information used for calculation damage for different types, attacks, and accuracies
    String[] attackNames = { "Dragon Pulse", "Air Slash", "Aerial Ace", "Huricane" };
    String[] attackTypes = { "Dragon", "Dragon", "Flying", "Flying" };
    String[] damages = {85 + specialAttack + " dmg", 120 + attack + " dmg", 60 + attack + " dmg", 110 + specialAttack + " dmg"};
    String[] accuracy = {"85% Accuracy", "65% Accuracy", "100% Accuracy", "80% Accuracy"};

    SpriteBatch batch;
    Texture img = new Texture("Rayquaza.png"); //Rayquaza image
    Sprite sprite;

    float posX; //position of Rayquaza image 
    float posY;

    /**
 * The method to inctanciate rayquaza
 * 
 * @param width, the width of the sprite
 * @param height, the height of the sprite
 */
    public Rayquaza(float width, float height) {
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
            attackType = "dragon";
            attackName = "Dragon Pulse";
            damage = 85 + specialAttack;
            if (hit(85) == true) {
                damage = 85 + specialAttack;
            } else {
                damage = 0;
            }
        }

        if (currentMove == 2) { //damage if player uses move 2
            attackType = "dragon";
            attackName = "Air Slash";
            if (hit(65) == true) {
                damage = 120 + attack;
            } else {
                damage = 0;
            }
           
        }

        if (currentMove == 3) { //damage if player uses move 3
            attackType = "flying";
            attackName = "Aerial Ace";
            damage = 60 + attack;
        }

        if (currentMove == 4) { //damage if player uses move 4
            attackType = "flying";
            attackName = "Hurricane";
            if (hit(80) == true) {
                damage = 110 + specialAttack;
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
     * @param type, the elemnt of the attack received
     * @return a double of the health remaining
     */
    public double damageTaken(double attack, String type) {
        //calculate damage taken 
        if (attack < defense) {
            attack = defense; // doing 0 damage if attack is not greater than defense
        }

        if (type == "rock" || type == "dragon" || type == "fairy") { //following if statements calculate multiplied damage based on type advantage/disadvantage
            health = health - (attack - defense) * 2;
            effect = "very effective";
        }

        else if (type == "fire" || type == "water" || type == "fighting" || type == "bug") {
            health = health - (attack - defense) * 0.5;
            effect = "not very effective";
        }

        else if (type == "grass") {
            health = health - (attack - defense) * 0.25;
            effect = "not very effective";
        }

        else if (type == "ground") {
            health = health - 0;
            effect = "not very effective";
        }

        else if (type == "ice") {
            health = health - (attack - defense) * 4;
            effect = "really really effective";
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
