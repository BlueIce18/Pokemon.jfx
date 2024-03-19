package com.mygdx.game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * The screen where the end screen is
 * 
 * @author Faisal, Jovan, Ryder, Shael
 * @version 1.0 2023-06-09
 */
public class EndState  implements Screen {
    Texture img;
    SpriteBatch batch;
    Main game;
    BitmapFont font = new BitmapFont(); // creating font for text
    String currentText = "press Space to continue playing"; //first text object
    String currentText2; //second text object

    /**
     * Instanciating the end screen
     * 
     * @param game, the main screen this class is implementing
     */
    public EndState(Main game){
        this.game = game; 
        batch = new SpriteBatch();
    }

    @Override
    /**
     * The method to be run once when game is switched to the end screen
     */
    public void show() {
        img = new Texture("EndPhoto.jpg"); //create background image
    }

    @Override
    /**
     * The method that will always be running while end screen is shown, everything is drawn here
     */
    public void render(float delta) {
        //unlock the arenas again after they are all cleared
        if (game.tinkatonDone == true && game.rayquazaDone == true && game.mewtwoDone == true){
            game.tinkatonDone = false;
            game.rayquazaDone = false;
            game.mewtwoDone = false;
            game.arena1Win = false;
            game.arena2Win = false;
            game.arena3Win = false;
        }

        if (Gdx.input.isKeyJustPressed(Keys.SPACE)){
			game.screen = true;
			game.gameState = "start";
		}

        if (game.playerWon == true){ //display "you won" 
            currentText2 = "You Won This Arena!";
        }
        else{ //display "you lost"
            currentText2 = "You Lost!";
        }

        batch.begin();
        batch.draw(img, 0, 0, Gdx.graphics.getWidth() * 1.0001f, Gdx.graphics.getHeight() * 1.000001f);
        
        font.getData().setScale(2.3f, 2.3f);
        font.setColor(0, 0, 0, 3);
        font.draw(batch, currentText, 425, 400); //draw our first text object
        
        font.draw(batch, currentText2, 520, 450); //draw our second text objects
        
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