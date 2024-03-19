package com.mygdx.game;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * The class that creates the pokemon objects
 * 
 * @author Faisal, Jovan, Ryder, Shael
 * @version 1.0 2023-06-09
 */
public class Pokemon extends Main {

    Charizard charizard; //instantiating pokemon 
    Greninja greninja;
    Kyogre kyogre;
    Mewtwo mewtwo;
    Rayquaza rayquaza;
    Tinkaton tinkaton;
    String pClass;
    double health;
    String attackType;
    String[] attacks; // list of each pokemons attacks
    String[] types; // list of pokemon types
    double speed;
    String[] damages; // list of how much damage each move does once calculations are done 
    String[] accuracy; // list of accuracies
    String effect; // how effective the move is 

    /**
     * Constructor for the pokemon class
     * 
     * @param pokemon, type of the pokemon this pokemon will be
     * @param width, the width of the pokemon sprite
     * @param height, the height of the pokemon sprite
     */
    public Pokemon(String pokemon, int width, int height) {

        pClass = pokemon;

        if (pokemon == "Charizard") { //setting variables to specific pokemon currently being used 
            charizard = new Charizard(width, height);
            this.health = charizard.health;
            this.attacks = charizard.attackNames;
            this.types = charizard.attackTypes;
            this.speed = charizard.speed;
            this.damages = charizard.damages;
            this.accuracy = charizard.accuracy;
            this.effect = charizard.effect;
        } else if (pokemon == "Greninja") {
            greninja = new Greninja(width, height);
            this.health = greninja.health;
            this.attacks = greninja.attackNames;
            this.types = greninja.attackTypes;
            this.speed = greninja.speed;
            this.damages = greninja.damages;
            this.accuracy = greninja.accuracy;
            this.effect = greninja.effect;
        } else if (pokemon == "Kyogre") {
            kyogre = new Kyogre(width , height);
            this.health = kyogre.health;
            this.attacks = kyogre.attackNames;
            this.types = kyogre.attackTypes;
            this.speed = kyogre.speed;
            this.damages = kyogre.damages;
            this.accuracy = kyogre.accuracy;
            this.effect = kyogre.effect;
        } else if (pokemon == "Mewtwo") {
            mewtwo = new Mewtwo(width, height);
            this.health = mewtwo.health;
            this.attacks = mewtwo.attackNames;
            this.types = mewtwo.attackTypes;
            this.speed = mewtwo.speed;
            this.damages = mewtwo.damages;
            this.accuracy = mewtwo.accuracy;
            this.effect = mewtwo.effect;
        } else if (pokemon == "Rayquaza") {
            rayquaza = new Rayquaza(width, height);
            this.health = rayquaza.health;
            this.attacks = rayquaza.attackNames;
            this.types = rayquaza.attackTypes;
            this.speed = rayquaza.speed;
            this.damages = rayquaza.damages;
            this.accuracy = rayquaza.accuracy;
            this.effect = rayquaza.effect;
        } else if (pokemon == "Tinkaton") {
            tinkaton = new Tinkaton(width, height);
            this.health = tinkaton.health;
            this.attacks = tinkaton.attackNames;
            this.types = tinkaton.attackTypes;
            this.speed = tinkaton.speed;
            this.damages = tinkaton.damages;
            this.accuracy = tinkaton.accuracy;
            this.effect = tinkaton.effect;
        }
    }

    /**
     * The method to draw sprite assosiated with this object
     * 
     * @param batch, the batch/screen to draw the sprite onto
     * @param x, x position of where the sprite will be drawn
     * @param y, y position of where the sprite will be drawn
     */
    public void BigDraw(SpriteBatch batch, float x, float y) {
        // drawing whichever pokemon is being used 
        if (pClass == "Charizard") {
            charizard.Draw(batch, x, y);
        } else if (pClass == "Greninja") {
            greninja.Draw(batch, x, y);
        } else if (pClass == "Kyogre") {
            kyogre.Draw(batch, x, y);
        } else if (pClass == "Mewtwo") {
            mewtwo.Draw(batch, x, y);
        } else if (pClass == "Rayquaza") {
            rayquaza.Draw(batch, x, y);
        } else if (pClass == "Tinkaton") {
            tinkaton.Draw(batch, x, y);
        }
    }

    /**
     * Method to get the damage done by an attack
     * 
     * @param currentMove, the move the player selected
     * @return a double of the damage the attack does
     */
    public double Attack(int currentMove) {
        // returning attack damage
        if (pClass == "Charizard") {
            this.attackType = charizard.attackType;
            return charizard.attackDamage(currentMove);
        } else if (pClass == "Greninja") {
            this.attackType = greninja.attackType;
            return greninja.attackDamage(currentMove);
        } else if (pClass == "Kyogre") {
            this.attackType = kyogre.attackType;
            return kyogre.attackDamage(currentMove);
        } else if (pClass == "Mewtwo") {
            this.attackType = mewtwo.attackType;
            return mewtwo.attackDamage(currentMove);
        } else if (pClass == "Rayquaza") {
            this.attackType = rayquaza.attackType;
            return rayquaza.attackDamage(currentMove);
        } else if (pClass == "Tinkaton") {
            this.attackType = tinkaton.attackType;
            return tinkaton.attackDamage(currentMove);
        } else {
            return 0;
        }
    }

    /**
     * Method to get the elemntal type of an attack
     * 
     * @return a string of the elemental type of the attack last preformed
     */
    public String getType() {
        // classifying attack type by pokemon
        if (pClass == "Charizard") {
            this.attackType = charizard.attackType;
        } else if (pClass == "Greninja") {
            this.attackType = greninja.attackType;
        } else if (pClass == "Kyogre") {
            this.attackType = kyogre.attackType;
        } else if (pClass == "Mewtwo") {
            this.attackType = mewtwo.attackType;
        } else if (pClass == "Rayquaza") {
            this.attackType = rayquaza.attackType;
        } else if (pClass == "Tinkaton") {
            this.attackType = tinkaton.attackType;
        }
        return this.attackType;
    }

    /**
     * Method to calculate the health of a pokemon after an attack
     * 
     * @param attack, the damage done by an incoming attack
     * @param type, the elemental type of the attack
     * @return a double of the health of the pokemon after the attack is preformed accounting for defence and elemental resistance/weakness
     */
    public double HitTaken(double attack, String type) {
        //returning how much damage is taken
        if (pClass == "Charizard") {
            return charizard.damageTaken(attack, type);
        } else if (pClass == "Greninja") {
            return greninja.damageTaken(attack, type);
        } else if (pClass == "Kyogre") {
            return kyogre.damageTaken(attack, type);
        } else if (pClass == "Mewtwo") {
            return mewtwo.damageTaken(attack, type);
        } else if (pClass == "Rayquaza") {
            return rayquaza.damageTaken(attack, type);
        } else if (pClass == "Tinkaton") {
            return tinkaton.damageTaken(attack, type);
        } else {
            return 0;
        }
    }

    /**
     * Method to get effectivness of an attack
     * 
     * @return a string of how effective an attack preformed on a pokemon was
     */
    public String getEffects(){
        // returning how effective the move was 
        if (pClass == "Charizard") {
            return charizard.getEffect();
        } else if (pClass == "Greninja") {
            return greninja.getEffect();
        } else if (pClass == "Kyogre") {
            return kyogre.getEffect();
        } else if (pClass == "Mewtwo") {
            return mewtwo.getEffect();
        } else if (pClass == "Rayquaza") {
            return rayquaza.getEffect();
        } else if (pClass == "Tinkaton") {
            return tinkaton.getEffect();
        } else {
            return "";
        }
    }

}
