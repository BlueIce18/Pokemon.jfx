package com.mygdx.game;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.Input.Buttons;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * The screen to select pokemon
 * 
 * @author Faisal, Jovan, Ryder, Shael
 * @version 1.0 2023-06-09
 */
public class SelectState implements Screen {

    //declaring all variables

    private SpriteBatch batch;

    private Texture BackImg;

    float mouseX;
    float mouseY;

    Main game;

    Rect gRect; // greninja rect
    Rect mRect; // mewtwo rect
    Rect cRect; // Charizard rect
    Rect kRect; // kyogre rect
    Rect rRect; // Rayquaza rect
    Rect tRect; // Tinkaton rect
    Greninja gSelect;
    Mewtwo mSelect;
    Charizard cSelect;
    Kyogre kSelect;
    Rayquaza rSelect;
    Tinkaton tSelect;

    public String whichPokemon;
    float x = 930;
    float y = 450;

    public int i;

    // ShapeRenderer shape = new ShapeRenderer();

    boolean mewtwoPicked = false;
    boolean kyogrePicked = false;
    boolean greninjaPicked = false;
    boolean charizardPicked = false;
    boolean TinkatonPicked = false;
    boolean RayquazaPicked = false;
    FileWriter writer;
    FileReader reader;
    String preSelect;
    Scanner scanner;
    File file;

    Rect saveRect;
    Rect loadRect;
    Rect goRect;
    Rect clearRect;

    BitmapFont font = new BitmapFont();
    String saveText = "SAVE";
    String loadText = "LOAD";
    String goText = "GO";
    String clearText = "CLEAR";

    boolean go = false;
    boolean load = false;

    /**
     * Contructor to instanciate the select screen
     * 
     * @param game, the main screen this class is implementing
     */
    public SelectState(Main game) {
        batch = new SpriteBatch();
        this.game = game;
    }

    @Override
    /**
     * The method to be run once when game is switched to the select screen
     */
    public void show() {
        //load background image
        BackImg = new Texture("SelectBackground.png");
        Sprite sprite = new Sprite(BackImg);
        sprite.setSize(400, 400);

        // greninja
        gRect = new Rect(200, 450);
        gSelect = new Greninja(150, 150);

        // Mewtwo
        mRect = new Rect(200, 250);
        mSelect = new Mewtwo(150, 120);

        //make selection rectangles
        rRect = new Rect(550, 450);
        rSelect = new Rayquaza(120, 120);

        kRect = new Rect(550, 250);
        kSelect = new Kyogre(120, 120);

        cRect = new Rect(900, 450);
        cSelect = new Charizard(120, 120);

        tRect = new Rect(900, 250);
        tSelect = new Tinkaton(120, 120);
        i = 0;

        //declare variables
        mewtwoPicked = false;
        kyogrePicked = false;
        greninjaPicked = false;
        charizardPicked = false;
        TinkatonPicked = false;
        RayquazaPicked = false;

        saveRect = new Rect(400, 25, 150, 75);
        loadRect = new Rect(750, 25, 150, 75);
        goRect = new Rect(1100, 25, 100, 75);
        clearRect = new Rect(50, 25, 150, 75);

        go = false;
    }

    @Override
    /**
     * The method that will always be running while select screen is shown, everything is drawn here
     */
    public void render(float delta) {
        //begin batch
        batch.begin();

        //get mouse input
        mouseX = Gdx.input.getX();
        mouseY = Gdx.input.getY();

        //draw selection rects and pokemons
        batch.draw(BackImg, 0, 0, Gdx.graphics.getWidth() * 1.0001f, Gdx.graphics.getHeight() * 1.000001f);
        gRect.Draw(batch);
        gSelect.Draw(batch, gRect.posX + 30, gRect.posY - 5);
        mRect.Draw(batch);
        mSelect.Draw(batch, mRect.posX + 30, mRect.posY);
        rRect.Draw(batch);
        rSelect.Draw(batch, rRect.posX + 50, rRect.posY);
        kRect.Draw(batch);
        kSelect.Draw(batch, kRect.posX + 45, kRect.posY);
        cRect.Draw(batch);
        cSelect.Draw(batch, cRect.posX + 30, cRect.posY);
        tRect.Draw(batch);
        tSelect.Draw(batch, tRect.posX + 40, tRect.posY);

        //draw save, load, clear, and go rects
        saveRect.Draw(batch);
        loadRect.Draw(batch);
        goRect.Draw(batch);
        clearRect.Draw(batch);

        //mouse clicking
        if (Gdx.input.isButtonJustPressed(Buttons.LEFT)) {
            mouseContained(mouseX, mouseY);
        }

        try {
            checkSaveOrLoadOrGoOrClear(mouseX, mouseY);
        } catch (FileNotFoundException e) {}

        //switching to arena once go is clicked
        if ((go == true) && (i >= 2)) {
            game.screen = true;
            game.gameState = "arena";

        }

        //drawing selected pokemon
        if (i > 0) {
            Pokemon p1 = new Pokemon(game.mainPokemon[0], 100, 100);
            p1.BigDraw(batch, 400, 100);
        }

        if (i > 1) {
            Pokemon p2 = new Pokemon(game.mainPokemon[1], 100, 100);
            p2.BigDraw(batch, 600, 100);
        }
        if (i > 2) {
            Pokemon p3 = new Pokemon(game.mainPokemon[2], 100, 100);
            p3.BigDraw(batch, 800, 100);
        }

        //drawing text
        font.getData().setScale(2.3f, 2.3f);
        font.setColor(0, 0, 0, 3);

        font.draw(batch, saveText, saveRect.posX + saveRect.width / 5, saveRect.posY + 50);
        font.draw(batch, loadText, loadRect.posX + loadRect.width / 5, loadRect.posY + 50);
        font.draw(batch, goText, goRect.posX + goRect.width / 5, goRect.posY + 50);
        font.draw(batch, clearText, clearRect.posX + clearRect.width / 8, clearRect.posY + 50);

        //end batch
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
    }

    /**
     * Method to read an external file
     * 
     * @throws FileNotFoundException
     */
    public void readFile() throws FileNotFoundException {
        //open favpokemon text file
        File file = new File("FavPokemon.txt");
        //open scanner
        Scanner scanner = new Scanner(file);
        //scan for which pokemon picked
        for (int j = 0; j < 3; j++) {
            game.mainPokemon[j] = scanner.nextLine();
            game.mainPokemon[j] = game.mainPokemon[j].trim();
            //set mainPokemon to have identifiable strings
            if (game.mainPokemon[j].equals("Mewtwo") == true) {
                game.mainPokemon[j] = "Mewtwo";
            } else if (game.mainPokemon[j].equals("Kyogre") == true) {
                game.mainPokemon[j] = "Kyogre";
            } else if (game.mainPokemon[j].equals("Greninja") == true) {
                game.mainPokemon[j] = "Greninja";
            } else if (game.mainPokemon[j].equals("Charizard") == true) {
                game.mainPokemon[j] = "Charizard";
            } else if (game.mainPokemon[j].equals("Rayquaza") == true) {
                game.mainPokemon[j] = "Rayquaza";
            } else if (game.mainPokemon[j].equals("Tinkaton") == true) {
                game.mainPokemon[j] = "Tinkaton";
            }
        }


        i = 3;

        //close scanner
        scanner.close();

    }

    /**
     * Method to check if the save, load, clear, or go buttons were pressed
     * 
     * @param x, x position of mouse
     * @param y, y position of mouse
     * @throws FileNotFoundException
     */
    public void checkSaveOrLoadOrGoOrClear(float x, float y) throws FileNotFoundException {
        //if clicked save
        if ((x >= saveRect.posX) && (x <= saveRect.posX + saveRect.width)) {
            if ((y <= 700 - saveRect.posY) && (y >= 700 - saveRect.posY - saveRect.height) && i > 2) {
                try {
                    //open writer
                    writer = new FileWriter("FavPokemon.txt");
                    //save pokemon to file
                    for (int j = 0; j < 3; j++) {
                        writer.write(game.mainPokemon[j] + "\n");
                    }
                    writer.close();
                } catch (IOException e) {}
            }
        }

        //if clicked load
        if ((x >= loadRect.posX) && (x <= loadRect.posX + loadRect.width)) {
            if ((y <= 700 - loadRect.posY) && (y >= 700 - loadRect.posY - loadRect.height)) {
                if (Gdx.input.isButtonJustPressed(Buttons.LEFT)) {
                    readFile();
                }
            }
        }

        //if clicked go
        if ((x >= goRect.posX) && (x <= goRect.posX + goRect.width)) {
            if ((y <= 700 - goRect.posY) && (y >= 700 - goRect.posY - goRect.height) && (i > 2)) {
                if (Gdx.input.isButtonJustPressed(Buttons.LEFT)) {
                    go = true;
                }
            }
        }

        //if clicked clear
        if ((x >= clearRect.posX) && (x <= clearRect.posX + clearRect.width)) {
            if ((y <= 700 - clearRect.posY) && (y >= 700 - clearRect.posY - clearRect.height)) {
                if (Gdx.input.isButtonJustPressed(Buttons.LEFT)) {
                    //reset variables
                    mewtwoPicked = false;
                    kyogrePicked = false;
                    greninjaPicked = false;
                    charizardPicked = false;
                    TinkatonPicked = false;
                    RayquazaPicked = false;
                    i = 0;
                }
            }
        }
    //    }
    }

    /**
     * Method to check what select box the user clicked
     * 
     * @param x, x position of the mouse
     * @param y, y position of the mouse
     */
    public void mouseContained(float x, float y) {
        if (i < 3){
            //if picked mewtwo
            if ((x >= gRect.posX) && (x <= gRect.posX + gRect.width)) {
                if ((y <= gRect.posY) && (y >= gRect.posY - gRect.height) && (mewtwoPicked != true)) {
                    game.mainPokemon[i] = "Mewtwo";
                    mewtwoPicked = true;
                    i++;
                }
            }
            //if picked tinkaton
            if ((x >= cRect.posX) && (x <= cRect.posX + cRect.width)) {
                if ((y <= cRect.posY) && (y >= cRect.posY - cRect.height) && (TinkatonPicked != true)) {
                    game.mainPokemon[i] = "Tinkaton";
                    TinkatonPicked = true;
                    i++;
                }
            }
            //if picked greninja
            if ((x >= mRect.posX) && (x <= mRect.posX + mRect.width)) {
                if ((y <= mRect.posY) && (y >= mRect.posY - mRect.height) && (greninjaPicked != true)) {
                    game.mainPokemon[i] = "Greninja";
                    greninjaPicked = true;
                    i++;
                }
            }
            //if picked kyogre
            if ((x >= rRect.posX) && (x <= rRect.posX + rRect.width)) {
                if ((y <= rRect.posY) && (y >= rRect.posY - rRect.height) && (kyogrePicked != true)) {
                    game.mainPokemon[i] = "Kyogre";
                    kyogrePicked = true;
                    i++;
                }
            }
            //if picked charizard
            if ((x >= tRect.posX) && (x <= tRect.posX + tRect.width)) {
                if ((y <= tRect.posY) && (y >= tRect.posY - tRect.height) && (charizardPicked != true)) {
                    game.mainPokemon[i] = "Charizard";
                    charizardPicked = true;
                    i++;
                }
            }
            //if picked rayquaza
            if ((x >= kRect.posX) && (x <= kRect.posX + kRect.width)) {
                if ((y <= kRect.posY) && (y >= kRect.posY - kRect.height) && (RayquazaPicked != true)) {
                    game.mainPokemon[i] = "Rayquaza";
                    RayquazaPicked = true;
                    i++;
                }
            }
        }
    }

}
