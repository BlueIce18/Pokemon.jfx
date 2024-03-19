package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * The openworld screen
 * 
 * @author Faisal, Jovan, Ryder, Shael
 * @version 1.0 2023-06-09
 */
public class OpenWorld implements Screen {

    private PlayerController controller;

    private Player player;

    private SpriteBatch batch;

    private Texture moveLeft; //different images for facing different directions
    private Texture moveRight;
    private Texture moveDown;
    private Texture moveUp;

    private int playerX = 675; //starting player position
    private int playerY = 335;

    private Texture img; //background image
    private Texture board; //board image for closed doors
    Sprite doorBlocker;


    Rect bounds1; //collision boundaries
    Rect bounds2;
    Rect bounds3;
    Rect bounds4;
    Rect outBounds1;
    Rect outBounds2;
    Rect outBounds3;
    Rect outBounds4; 

    Rect door1; //doors for entering levels
    Rect door2;
    Rect door3;

    int mouseX;
    int mouseY;
    Sprite sprite;

    private String characterDirection = "down"; //character starts facing down

    Main game;

    /**
     * Instanciating the openworld screen
     * 
     * @param game, the main screen this class is implementing
     */
    public OpenWorld(Main game) {

        moveLeft = new Texture("PlayerMovingLeft.png"); //direction images
        moveRight = new Texture("PlayerMovingRight.png");
        moveDown = new Texture("PlayerMovingDown.png");
        moveUp = new Texture("PlayerMovingUp.png");

        batch = new SpriteBatch();

        player = new Player(10, 20); //create player with dimensions

        controller = new PlayerController(player);

        this.game = game;
    }

    @Override
    /**
     * The method to be run once when game is switched to the openworld screen
     */
    public void show() {
        Gdx.input.setInputProcessor(controller);
        img = new Texture("Freemap2.jpg"); //background image
        Sprite sprite = new Sprite(img);
        sprite.setSize(300, 400); //changing size

        board = new Texture("WoodenBoard.png"); //wooden board image 
        doorBlocker = new Sprite(board);
        doorBlocker.setSize(50,9); //changing size

        

        bounds1 = new Rect(0, 0, 660, 350); //creating boundary rectangles
        bounds2 = new Rect(0, 410, 660, 350);
        bounds3 = new Rect(745, 345, 660, 355);
        bounds4 = new Rect(750, -5, 660, 300);

        outBounds1 = new Rect(0, 0, Gdx.graphics.getWidth(), 30);
        outBounds2 = new Rect(0, 0, 110, Gdx.graphics.getHeight());
        outBounds3 = new Rect(0, 630, Gdx.graphics.getWidth(), 100);
        outBounds4 = new Rect(1200, 0, 100, Gdx.graphics.getHeight());

        door1 = new Rect(270, 410, 60, 80); //creating doors
        door2 = new Rect(930, 350, 60, 80);
        door3 = new Rect(930, 100, 60, 80);


        playerX = 675; playerY = 335;

        characterDirection = "down";
    }

    @Override
    /**
     * The method that will always be running while openworld screen is shown, everything is drawn here
     */
    public void render(float delta) {
        batch.begin();
        mouseX = Gdx.input.getX();
        mouseY = Gdx.input.getY();

        if ((playerX >= door1.posX) && (playerX <= door1.posX + door1.width) && (playerY >= door1.posY) //level 1 door collision
                && (playerY <= door1.posY + door1.height)) {
            game.enemPokemon[0] = "Tinkaton"; //enemy team
            game.enemPokemon[1] = "Greninja";
            game.enemPokemon[2] = "Charizard";
            game.whichArena = 1;
            game.screen = true;
            game.gameState = "select"; // setting screen to select screen 
        }

        else if ((playerX >= door2.posX) && (playerX <= door2.posX + door2.width) && (playerY >= door2.posY) //level 2 door collision
                && (playerY <= door2.posY + door2.height)) {
            game.enemPokemon[0] = "Rayquaza"; //enemy team
            game.enemPokemon[1] = "Kyogre";
            game.enemPokemon[2] = "Charizard";
            game.whichArena = 3;
            game.screen = true;
            game.gameState = "select"; // setting screen to select screen 
        }

        else if ((playerX >= door3.posX) && (playerX <= door3.posX + door3.width) && (playerY >= door3.posY) //level 3 door collision
                && (playerY <= door3.posY + door3.height)) {
            game.enemPokemon[0] = "Mewtwo"; //enemy team
            game.enemPokemon[1] = "Greninja";
            game.enemPokemon[2] = "Rayquaza";
            game.whichArena = 2;
            game.screen = true;
            game.gameState = "select"; // setting screen to select screen 
        }

        // moving detection
        if ((Gdx.input.isKeyPressed(Keys.W)) || (Gdx.input.isKeyPressed(Keys.UP))){
            playerY = playerY + 3;
            characterDirection = "up";
        }

        if ((Gdx.input.isKeyPressed(Keys.S)) || (Gdx.input.isKeyPressed(Keys.DOWN))){
            playerY = playerY - 3;
            characterDirection = "down";
        }

        if ((Gdx.input.isKeyPressed(Keys.A)) || (Gdx.input.isKeyPressed(Keys.LEFT))){
            playerX = playerX - 3;
            characterDirection = "left";
        }

        if ((Gdx.input.isKeyPressed(Keys.D)) || (Gdx.input.isKeyPressed(Keys.RIGHT))){
            playerX = playerX + 3;
            characterDirection = "right";
        }

        // collision detection on path
        if (playerX < 638){ //top left path
            //stopping getting stuck
            if (game.arena1Win == false){
                if (playerX < 250 || playerX > 310){
                    if (playerY >= 410){playerY = 409;} //top
                }
            } else if (playerY >= 410){playerY = 409;}
            if (playerX <= 91){playerX = 92;} //left
            if (playerY <= 350){playerY = 351;} //bottom
            
        }

        if (playerX > 638 && playerX < 730){ //middle path
            if (playerY < 30){playerY = 31;} //top
            if (playerY > 630){playerY = 629;} //bottom
            if ((playerY < 350 || playerY > 410) && playerX <= 641){playerX = 642;}
            if (((playerY < 30 || playerY > 100) && (playerY > 345 || playerY < 295)) && playerX >= 726){playerX = 725;}
        }

        if (playerX > 730 && playerY < 110){ //right bottom path
            if (playerX >= 1160){playerX = 1159;} //right
            if (playerY <= 30){playerY = 31;} //bottom
            if (game.arena2Win == false){
                if (playerX < 910 || playerX > 970){
                    if (playerY >= 100){playerY = 99;}//top
                }
            } else if (playerY >= 100){playerY = 99;}
        }

        if (playerX > 730 && playerY > 285){ //right top path
            if (playerX >= 1160){playerX = 1159;} //right
            if (playerY <= 295){playerY = 296;} //bottom
            if (game.arena3Win == false){
                if (playerX < 910 || playerX > 970){
                    if (playerY >= 345){playerY = 344;} //top 
                }
            } else if (playerY >= 345){playerY = 344;}
            
        }
        
        batch.draw(img, 0, 0, Gdx.graphics.getWidth() * 1.0001f, Gdx.graphics.getHeight() * 1.000001f);

        //set door size
        doorBlocker.setSize(65, 55);
       
        //if player has completed an arena already, block the arena door
        if (game.tinkatonDone == true){
            doorBlocker.setPosition(door1.posX + 5, door1.posY + 35);  
            doorBlocker.draw(batch);
        }
        if (game.rayquazaDone == true){
            doorBlocker.setPosition(door2.posX , door2.posY + 30);  
            doorBlocker.draw(batch);
        }
        if (game.mewtwoDone == true){
            doorBlocker.setPosition(door3.posX, door3.posY + 32);  
            doorBlocker.draw(batch);
        }

        if (characterDirection == "down") { // drawing images for which way the player is facing
            batch.draw(moveDown, playerX, playerY, 50, 60);
        } else if (characterDirection == "up") {
            batch.draw(moveUp, playerX, playerY, 50, 60);
        } else if (characterDirection == "left") {
            batch.draw(moveLeft, playerX, playerY, 50, 60);
        } else if (characterDirection == "right") {
            batch.draw(moveRight, playerX, playerY, 50, 60);
        }

        batch.end();
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
       img.dispose();
    }

}
