package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * The start screen
 * 
 * @author Faisal, Jovan, Ryder, Shael
 * @version 1.0 2023-06-09
 */
public class StartState  implements Screen {
    Texture img;
    SpriteBatch batch;
    Main game;
    BitmapFont font = new BitmapFont(); //creating font
    String currentText = "Press Space to Play!"; //text object 1
    String currentText2 = "Press U to go to Instructions!"; //text object 2

    /**
     * Instanciating the start screen
     * 
     * @param game, the main screen this class is implementing
     */
    public StartState(Main game){
        this.game = game;
        batch = new SpriteBatch();
    }

    /**
     * The method to be run once when game is switched to the start screen
     */
    @Override
    public void show() {
        img = new Texture("StartPhoto.jpg"); //background image
    }

    @Override
    /**
     * The method that will always be running while end screen is shown, everything is drawn here
     */
    public void render(float delta) {
        if (Gdx.input.isKeyJustPressed(Keys.SPACE)){
			game.screen = true;
			game.gameState = "openWrld"; //going to open world when space is pressed
		}
        if (Gdx.input.isKeyJustPressed(Keys.U)){ 
			game.screen = true;
			game.gameState = "instruct"; //going to instructions when U is pressed 
		}

        batch.begin();
        batch.draw(img, 0, 0, Gdx.graphics.getWidth() * 1.0001f, Gdx.graphics.getHeight() * 1.000001f);
        
        font.getData().setScale(2.3f, 2.3f); //changing font size
        font.setColor(0, 0, 0, 3); //changing font colour

        font.draw(batch, currentText, 100, 200); //drawing both text objects
        font.draw(batch, currentText2, 100, 100);
    
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