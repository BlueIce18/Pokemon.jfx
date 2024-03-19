package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;


/**
 * The screen where the help screen is
 * 
 * @author Faisal, Jovan, Ryder, Shael
 * @version 1.0 2023-06-09
 */
public class InstructionState  implements Screen {
    Texture img;
    SpriteBatch batch;
    Main game;
    BitmapFont font = new BitmapFont(); //creating font
    String currentText = "Select 3 pokemon and battle against 3 enemy pokemon"; //text objects
    String currentText2 = "Defeat all 3 enemies to complete an arena";
    String currentText3 = "Choose one of the attacks listed at the bottom to attack the enemy";
    String currentText6 = "Win all 3 arenas to win the game!";
    String currentText7 = "Arenas are locked after they are completed until you have cleared all 3";
    String currentText4 = "Press Space to return to the start screen";
    String currentText5 = "Make sure to save and load your favourite lineup!";


    /**
     * Instanciating the help screen
     * 
     * @param game, the main screen this class is implementing
     */
    public InstructionState(Main game){
        this.game = game;
        batch = new SpriteBatch();
    }


    /**
     * The method to be run once when game is switched to the help screen
     */
    @Override
    public void show() {
        img = new Texture("Wallpaper.jpg"); //wallpaper image
    }

    @Override

    /**
     * The method that will always be running while help screen is shown, everything is drawn here
     */

    public void render(float delta) {
        batch.begin();

        if (Gdx.input.isKeyJustPressed(Keys.SPACE)){ //going to start screen when space is pressed
			game.screen = true;
			game.gameState = "start";
		}

        batch.draw(img, 0, 0, Gdx.graphics.getWidth() * 1.0001f, Gdx.graphics.getHeight() * 1.000001f);
       
        font.getData().setScale(2.3f, 2.3f); //changing text size
        font.setColor(200, 200, 200, 3); //changing text colour

        font.draw(batch, currentText, 250, 550); //drawing instruction text
        font.draw(batch, currentText2, 350, 500);
        font.draw(batch, currentText3, 175, 450);
        font.draw(batch, currentText6, 350, 400);
        font.draw(batch, currentText7, 175, 350);
        font.draw(batch, currentText4, 375, 200);
        font.draw(batch, currentText5, 300, 250);
        
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