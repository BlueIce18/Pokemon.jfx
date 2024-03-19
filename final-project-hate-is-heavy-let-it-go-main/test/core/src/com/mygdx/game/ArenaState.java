package com.mygdx.game;

import java.util.Random;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.Input.Buttons;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;

/**
 * The screen where the arena fight happens
 * 
 * @author Faisal, Jovan, Ryder, Shael
 * @version 1.0 2023-06-09
 */
public class ArenaState implements Screen {
    //declare variables

    private SpriteBatch batch;

    private Texture img;

    Main game;

    int enemyAttack;

    Rect textBox;
    BitmapFont font = new BitmapFont();
    String currentText = "The fight has begun!";

    //instantiating the attack rectangles to display info and use moves
    Rect atk1Rect;  
    Rect atk2Rect;
    Rect atk3Rect;
    Rect atk4Rect;

    Pokemon[] pSelect = new Pokemon[3];

    HealthRect healthbar1[] = new HealthRect[3];
    HealthRect healthbar2[] = new HealthRect[3];

    //variable to store mouse positions
    float mouseX; 
    float mouseY;

    //integers to store info about current and last moves for displays
    int currentMove = 0;
    int enemyMove = 0;
    int lastMove = 0;
    int enemLastMove = 0;

    //boolean for whether the pokemon is currently attacking
    boolean attacking = false;

    Pokemon pokemonP1[] = new Pokemon[3];
    Pokemon enemy[] = new Pokemon[3];

    double p1Attack[] = new double[3];
    double p2Attack[] = new double[3];

    double p1Health[] = new double[3];
    double p2Health[] = new double[3];
    double healthWidth1[] = new double[3];
    double healthWidth2[] = new double[3];

    //timers that are used to track end conditions and play conditions
    double timer;
    double atkTimer;
    double endTimer;

    boolean p1Dead[] = new boolean[3];
    boolean p2Dead[] = new boolean[3];
    
    //boolean for whether the pokemon should attack
    boolean canAttack = true; 

    //creating the random object
    Random random = new Random();
    
    boolean p1Miss; //booleans to track misses
    boolean p2Miss;

    boolean died = true; //boolean to prevent repeat of code in endgame conditions

    boolean p1Faster; //variable to determine which pokemon is faster

    

    int i = 0; //variable for friendly pokemon position
    int n = 0; //variable for friendly pokemon position

    //arrays for atk rects and pokemon rects
    Rect atkRects []= new  Rect[4];
    Rect p1Selects []= new  Rect[3];

    /**
     * Instanciating the arena screen
     * 
     * @param game, the main screen this class is a subclass of
     */
    public ArenaState(Main game) {
        batch = new SpriteBatch(); //start batch
        this.game = game; //creating a variable to access main variables and methods
    }

    @Override
    /**
     * The method to be run once when game is switched to the arena screen
     */
    public void show() {
        //create background
        img = new Texture("arena.jpg");
        Sprite sprite = new Sprite(img);
        sprite.setSize(300, 400);

        //rects for pokemons attacks
        atkRects[0] = new Rect(400, 50, 200, 120);
        atkRects[1] = new Rect(620, 50, 200, 120);
        atkRects[2] = new Rect(840, 50, 200, 120);
        atkRects[3] = new Rect(1060, 50, 200, 120);

        //creating the switch buttons
        p1Selects[0] = new Rect(50, 420, 75, 75);
        p1Selects[1] = new Rect(50, 325, 75, 75);
        p1Selects[2] = new Rect(50, 230, 75, 75);

        //adding the pokemons to the switch buttons
        pSelect[0] = new Pokemon(game.mainPokemon[0], 60, 60);
        pSelect[1] = new Pokemon(game.mainPokemon[1], 60, 60);
        pSelect[2] = new Pokemon(game.mainPokemon[2], 60, 60);

        //adding the friendly and enemy lineups
        pokemonP1[0] = new Pokemon(game.mainPokemon[0], 400, 500);
        pokemonP1[1] = new Pokemon(game.mainPokemon[1], 400, 500);
        pokemonP1[2] = new Pokemon(game.mainPokemon[2], 400, 500);
        enemy[0] = new Pokemon(game.enemPokemon[0], 300, 400);
        enemy[1] = new Pokemon(game.enemPokemon[1], 300, 400);
        enemy[2] = new Pokemon(game.enemPokemon[2], 300, 400);

        //storing the individual pokemons health for either side
        p1Health[0] = pokemonP1[0].health;
        p1Health[1] = pokemonP1[1].health;
        p1Health[2] = pokemonP1[2].health;
        p2Health[0] = enemy[0].health;
        p2Health[1] = enemy[1].health;
        p2Health[2] = enemy[2].health;

        //adding the current attacks to the lists
        p1Attack[0] = 0;
        p1Attack[1] = 0;
        p1Attack[2] = 0;
        p2Attack[0] = 0;
        p2Attack[1] = 0;
        p2Attack[2] = 0;
        
        //timers initialized
        timer = 0;
        endTimer = 0;
        atkTimer = 0;

        //creating the healthbars to display
        healthbar1[0] = new HealthRect(100, 560, 400, 50, p1Health[0]);
        healthbar1[1] = new HealthRect(100, 560, 400, 50, p1Health[1]);
        healthbar1[2] = new HealthRect(100, 560, 400, 50, p1Health[2]);
        healthbar2[0] = new HealthRect(900, 260, 400, 50, p2Health[0]);
        healthbar2[1] = new HealthRect(900, 260, 400, 50, p2Health[1]);
        healthbar2[2] = new HealthRect(900, 260, 400, 50, p2Health[2]);
        
        //setting starting width
        healthWidth1[0] = p1Health[0];
        healthWidth1[1] = p1Health[1];
        healthWidth1[2] = p1Health[2];
        healthWidth2[0] = p2Health[0];
        healthWidth2[1] = p2Health[1];
        healthWidth2[2] = p2Health[2];

        //variables to store whether the pokemon in each line up is dead or not
        p1Dead[0] = false;
        p1Dead[1] = false;
        p1Dead[2] = false;
        p2Dead[0] = false;
        p2Dead[1] = false;
        p2Dead[2] = false;

        //default as no miss (is changed if the pokemon misses an attack)
        p1Miss = false;
        p2Miss = false;

        n = 0; //variable for friendly pokemon position

        died = true; //boolean to prevent repeat of code in endgame conditions
    }

    @Override
    /**
     * The method that will always be running while arena screen is shown, everything is drawn here
     */
    public void render(float delta) {
        ScreenUtils.clear(0, 0, 0, 1);

        batch.begin();
        //draw background
        batch.draw(img, 0, 0, Gdx.graphics.getWidth() * 1.0001f, Gdx.graphics.getHeight() * 1.000001f);

        //get mouse x and y
        mouseX = Gdx.input.getX();
        mouseY = 700 - Gdx.input.getY();

        checkAttack(mouseX, mouseY); //calls check attack method to determine whether the mouse is interacting with any of the attacks

        //check if player is dead (currently selected pokemon only)
        if (p1Health[i] <= 0) { 
            p1Dead[i] = true;
        }

        //check for faster pokemon (who goes first)
        if (pokemonP1[i].speed >= enemy[n].speed) {
            p1Faster = true;
        } else {
            p1Faster = false;
        }


        if (p2Health[n] <= 0) {
            p2Dead[n] = true;
        }

        //check to see if all pokemons knocked out, if true start the end timer
        if ((p1Health[0] <= 0) && (p1Health[1] <= 0) && (p1Health[2] <= 0)){
            if (died == true) {
                endTimer = 0;
                died = false;
                game.playerWon = false;
            }

            //end game after 5 seconds of end timer starting
            if (endTimer == 5 * 60) {
                game.screen = true;
                game.gameState = "end";
            }
        }

        //check to see if enemy is all knocked out
        if ((p2Health[0] <= 0) && (p2Health[1] <= 0) && (p2Health[2] <= 0)){
            if (game.enemPokemon[0] == "Rayquaza") {
                game.rayquazaDone = true;
           } else if (game.enemPokemon[0] == "Tinkaton") {
               game.tinkatonDone = true;
           } else if (game.enemPokemon[0] == "Mewtwo") {
               game.mewtwoDone = true;
           }

            if (died == true) {
                endTimer = 0;
                died = false;
                game.playerWon = true;
            }

            if (game.whichArena == 1){
                game.arena1Win = true;
            }
            else if (game.whichArena == 2){
                game.arena2Win = true;
            }
            else if (game.whichArena == 3){
                game.arena3Win = true;
            }


            if (endTimer == 5 * 60) {
                game.screen = true;
                game.gameState = "end";
            }
        }

        //add to end timer
        endTimer += 1;

        if (attacking == false){ //prevents the user from switching pokemon mid attack
            checkPokemon(mouseX, mouseY); //calls the method to see whether the user is trying to switch pokemon
        }

        //Do attacks for both pokemon
        if (attacking == true && p1Dead[i] == false && p2Dead[n] == false) {
            p1Miss = false;
            p2Miss = false;
            //check to see if pokemon missed
            p1Attack[i] = pokemonP1[i].Attack(currentMove);
            if (p1Attack[i] == 0) {
                p1Miss = true;
            }

            enemyMove = random.nextInt(1, 5); //generates a random attack for the enemy from 1 to 4

            enemyAttack = enemyMove; //seperating the variables to make referencing easier
            p2Attack[n] = enemy[n].Attack(enemyAttack);

            //check if enemy missed
            if (p2Attack[n] == 0) {
                p2Miss = true;
            }

            attacking = false;

            //see who attacks first based on speed
            if (canAttack == true) {
                if (enemy[n].speed > pokemonP1[i].speed) {
                    //calculate players health
                    p1Health[i] = pokemonP1[i].HitTaken(p2Attack[n], enemy[n].getType());
                    if (p1Health[i] > 0) {
                        //calculate enemies health
                        p2Health[n] = enemy[n].HitTaken(p1Attack[i], pokemonP1[i].getType());
                    } else {
                        p1Dead[i] = true;
                    }

                } //if enemy is faster, they attack first
                else {
                    //calculate enemy health
                    p2Health[n] = enemy[n].HitTaken(p1Attack[i], pokemonP1[i].getType());
                    if (p2Health[n] >= 0) {
                        //calculate player health
                        p1Health[i] = pokemonP1[i].HitTaken(p2Attack[n], enemy[n].getType());
                    } else {
                        p2Dead[n] = true;
                    }

                }
                canAttack = false;
            }

            lastMove = currentMove; enemLastMove = enemyMove;  //stores the player and enemies last moves
            
            currentMove = 0; //reset current move
            //reset attacks so doesn't continiously damage
            p1Attack[i] = 0;
            p2Attack[n] = 0;

            //set up atkTimer for animation
            atkTimer = timer + (4 * 60);

        } else {
            attacking = false;
        }



        // 2 seconds animation of player attacking (0-2 seconds)
        if (atkTimer > timer + (2 * 60)) {

            //if player attaks first
            if (p1Faster == true) {
                pokemonP1[i].BigDraw(batch, 500, 150);
                enemy[n].BigDraw(batch, 1000, 300);

                if (p1Miss == true) {
                    font.getData().setScale(3f, 3f);
                    font.setColor(1, 0, 0, 1);
                    font.draw(batch, "MISS!", 830, 475);
                } else {
                    font.getData().setScale(2f, 2f);
                    font.setColor(255, 255, 0, 1);
                    font.draw(batch, "Attack was " + enemy[n].getEffects(), 800, 475);
                }
                font.getData().setScale(1.4f, 1.4f);
                font.setColor(0, 0, 1, 1);
                font.draw(batch, game.mainPokemon[i] + " used " + pokemonP1[i].attacks[lastMove - 1], 100, 100);

                healthWidth2[n] = p2Health[n];
            }
            //if player attacks
            else {

                pokemonP1[i].BigDraw(batch, 100, 100);
                enemy[n].BigDraw(batch, 600, 200);

                if (p2Miss == true) {
                    font.getData().setScale(3f, 3f);
                    font.setColor(1, 0, 0, 1);
                    font.draw(batch, "MISS!", 630, 275);
                } else {
                    font.getData().setScale(2f, 2f);
                    font.setColor(255, 255, 0, 1);
                    font.draw(batch, "Attack was " + pokemonP1[i].getEffects(), 400, 475);
                }
                font.getData().setScale(1.4f, 1.4f);
                font.setColor(0, 0, 1, 1);
                font.draw(batch, game.enemPokemon[n] + " used " + enemy[n].attacks[enemLastMove - 1], 100, 100);

                healthWidth1[i] = p1Health[i];
            }
        }

        // 2 second animation of enemy attacking (2-4 seconds)

        else if (((atkTimer > timer) && (atkTimer <= timer + (2 * 60)))
                && ((p1Dead[i] == false) || (p2Dead[n] == false))) {
            if (p1Faster == true) {
                pokemonP1[i].BigDraw(batch, 100, 100);
                if (p2Dead[n] != true) {
                    enemy[n].BigDraw(batch, 600, 200);
                    if (p2Miss == true) {
                        font.getData().setScale(3f, 3f);
                        font.setColor(1, 0, 0, 1);
                        font.draw(batch, "MISS!", 630, 275);
                    } else {
                        font.getData().setScale(2f, 2f);
                        font.setColor(255, 255, 0, 1);
                        font.draw(batch, "Attack was " + pokemonP1[i].getEffects(), 400, 475);
                    }
                    
                    font.getData().setScale(1.4f, 1.4f);
                    font.setColor(0, 0, 1, 1);
                    font.draw(batch, game.enemPokemon[n] + " used " + enemy[n].attacks[enemLastMove - 1], 100, 100);
                } else {
                    enemy[n].BigDraw(batch, 1000, 300);
                    timer = atkTimer - 1;
                }
                healthWidth1[i] = p1Health[i];
            } else {
                enemy[n].BigDraw(batch, 1000, 300);
                if (p1Dead[i] != true) {
                    pokemonP1[i].BigDraw(batch, 500, 150);

                    if (p1Miss == true) {
                        font.getData().setScale(3f, 3f);
                        font.setColor(1, 0, 0, 1);
                        font.draw(batch, "MISS!", 830, 475);
                    } else {
                        font.getData().setScale(2f, 2f);
                        font.setColor(255, 255, 0, 1);
                        font.draw(batch, "Attack was " + enemy[n].getEffects(), 800, 475);
                    }
                    font.getData().setScale(1.4f, 1.4f);
                    font.setColor(0, 0, 1, 1);
                    font.draw(batch, game.mainPokemon[i] + " used " + pokemonP1[i].attacks[lastMove - 1], 100, 100);
                } else {
                    timer = atkTimer - 1;
                    pokemonP1[i].BigDraw(batch, 100, 100);
                }
                healthWidth2[n] = p2Health[n];
            }
        } else if (atkTimer == timer) {
            if (p2Health[n] <= 0 && n < 2) {
                n++;
            } else if (p1Health[i] <= 0 && i < 2) {
                i++;
            }
        }

        else {
            pokemonP1[i].BigDraw(batch, 100, 100);
            enemy[n].BigDraw(batch, 1000, 300);
        }

        if (timer % 120 == 0) { //timer to prevent spam attacking
            canAttack = true;
        }

        for (int j = 0; j < 4; j++){ //draw attack boxes
            atkRects[j].Draw(batch);
        }

        for (int j = 0; j < 3; j++){ //draw select boxes
            p1Selects[j].Draw(batch);
        }

        for (int j = 0; j < 3; j++){ //draw pokemon onto select boxes
            pSelect[j].BigDraw(batch, p1Selects[j].posX + 5, p1Selects[j].posY + 5);
        }

        font.getData().setScale(1.3f, 1.3f);

        font.setColor(1, 0, 0, 1);

        if (lastMove == 0 && enemyAttack == 0){ //if no moves have been done then set default message
            font.draw(batch, currentText, 100, 100);
        }

        // attack name fonts
        font.setColor(1, 0, 1, 1);

        for (int j = 0; j <4; j++){  //draw the stats of each attack at once
            font.setColor(1, 0, 1, 1);
            font.draw(batch, pokemonP1[i].attacks[j], atkRects[j].posX + 45, atkRects[j].posY + 110);

            font.setColor(2, 1, 0, 1);
            font.draw(batch, pokemonP1[i].types[j], atkRects[j].posX + 45, atkRects[j].posY + 85);

            font.setColor(30, 0, 0, 1);
            font.draw(batch, pokemonP1[i].damages[j], atkRects[j].posX + 45, atkRects[j].posY + 60);

            font.setColor(0, 0, 100, 1);
            font.draw(batch, pokemonP1[i].accuracy[j], atkRects[j].posX + 45, atkRects[j].posY + 35);
        }

        healthbar1[i].Draw(batch, healthWidth1[i]); //update healthbars to scale
        healthbar2[n].Draw(batch, healthWidth2[n]);

        batch.end();

        timer += 1;

        if (Gdx.input.isKeyPressed(Keys.I)) { //keys to change pokemon
            i = 0;
        }

        else if (Gdx.input.isKeyPressed(Keys.O)) {
            i = 1;
        }

        else if (Gdx.input.isKeyPressed(Keys.P)) {
            i = 2;
        }
    }

    @Override
    /**
     * Method to resize window
     */
    public void resize(int width, int height) {
    }

    @Override
    /**
     * Method to be called when game is paused
     */
    public void pause() {
    }

    @Override
    /**
     * Method to be called when game is resumed
     */
    public void resume() {
    }


    @Override
    /**
     * Method to be called when this screen is switched to another screen
     */
    public void hide() {
    }

    @Override
    /**
     * Method to be called when this screen is disposed
     */
    public void dispose() {
    }

    /**
     * Method to allow clicking on rectangles to switch pokemon
     * 
     * @param x, the x position of the mouse
     * @param y, the y position of the mouse
     */
    public void checkPokemon(float x, float y){ //if there is no attack happening and the user is interacting with one of the switch pokemon buttons switch it

        if (atkTimer <= timer) {
            if ((x >= p1Selects[0].posX) && (x <= p1Selects[0].posX + p1Selects[0].width)){ 
                if ((y >= p1Selects[0].posY) && (y <= p1Selects[0].posY + p1Selects[0].height) && (p1Health[0] >= 0)){
                    if (Gdx.input.isButtonJustPressed(Buttons.LEFT)) { //if the mouse is between the borders of the button and the left mouse button is clicked and the pokemon to switch to is alive, switch pokemon
                        i = 0;
                    }
                }
            }

            if ((x >= p1Selects[1].posX) && (x <= p1Selects[1].posX + p1Selects[1].width)){
                if ((y >= p1Selects[1].posY) && (y <= p1Selects[1].posY + p1Selects[1].height) && (p1Health[1] >= 0)){
                    if (Gdx.input.isButtonJustPressed(Buttons.LEFT)) {
                        i = 1;
                    }
                }
            }

            if ((x >= p1Selects[2].posX) && (x <= p1Selects[2].posX + p1Selects[2].width)){
                if ((y >= p1Selects[2].posY) && (y <= p1Selects[2].posY + p1Selects[2].height) && (p1Health[2] >= 0)){
                    if (Gdx.input.isButtonJustPressed(Buttons.LEFT)) {
                        i = 2;
                    }
                }
            }
        }
    }

    /**
     * Method for user to pick attack
     * 
     * @param x, x position of mouse
     * @param y, y position of mouse
     */
    public void checkAttack(float x, float y) { //if there is no attack happening and the user is interacting with one of the attack buttons use that attack
        if (atkTimer <= timer) {

            if ((x >= atkRects[0].posX) && (x <= atkRects[0].posX + atkRects[0].width)) { 
                if ((y >= atkRects[0].posY) && (y <= atkRects[0].posY + atkRects[0].height)) {
                    if (Gdx.input.isButtonJustPressed(Buttons.LEFT)) { //if the mouse is between the borders of the button and the left mouse button is clicked set the move and indicate an attack is being made
                        currentMove = 1;
                        attacking = true;
                    }
                }
            }

            else if ((x >= atkRects[1].posX) && (x <= atkRects[1].posX + atkRects[1].width)) {
                if ((y >= atkRects[1].posY) && (y <= atkRects[1].posY + atkRects[1].height)) {
                    if (Gdx.input.isButtonJustPressed(Buttons.LEFT)) {  
                        currentMove = 2;
                        attacking = true;
                    }
                }
            }

            else if ((x >= atkRects[2].posX) && (x <= atkRects[2].posX + atkRects[2].width)) {
                if ((y >= atkRects[2].posY) && (y <= atkRects[2].posY + atkRects[2].height)) {
                    if (Gdx.input.isButtonJustPressed(Buttons.LEFT)) {
                        currentMove = 3;
                        attacking = true;
                    }
                }
            }

            else if ((x >= atkRects[3].posX) && (x <= atkRects[3].posX + atkRects[3].width)) {
                if ((y >= atkRects[3].posY) && (y <= atkRects[3].posY + atkRects[3].height)) {
                    if (Gdx.input.isButtonJustPressed(Buttons.LEFT)) {
                        currentMove = 4;
                        attacking = true;
                    }
                }
            }
        }
    }
}
