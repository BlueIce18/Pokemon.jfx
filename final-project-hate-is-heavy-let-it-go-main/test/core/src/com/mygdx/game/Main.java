package com.mygdx.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;

/**
 * The class to manage and switch between all the game screens
 * 
 * @author Faisal, Jovan, Ryder, Shael
 * @version 1.0 2023-06-09
 */
public class Main extends Game {
	static Sprite sprite;
	Texture img;

	SpriteBatch batch;
	boolean bool = false;

	// public ArenaState Arena = new ArenaState(this);

	private ArenaState arena; // referring to files
	private OpenWorld openWrld;
	private SelectState select;
	private StartState start;
	private EndState end;
	private InstructionState instruct;

	public String gameState = "start"; //initializing gamestate as start screen

	public int whichArena; //int for which level the player is playing

	public boolean arena1Win = false; //indicates if the level has been completed or not to close off door 
	public boolean arena2Win = false;
	public boolean arena3Win = false;
	public boolean tinkatonDone = false; //indicates if the level has been completed or not to close off door 
	public boolean rayquazaDone = false;
	public boolean mewtwoDone = false;
	

	public String mainPokemon[] = new String[3]; //starting pokemon of team
	//mainPokemon[0] = "charizard";

	public String enemPokemon[] = new String[3]; //starting pokemon of enemy team

	public boolean screen = true;

	public boolean playerWon; // when player beats the level

	@Override
	/**
	 * The method to be run once when the program is launched
	 */
	public void create() {
		batch = new SpriteBatch();
		setScreen(new OpenWorld(this)); //calling the different screens
		arena = new ArenaState(this);
		openWrld = new OpenWorld(this);
		select = new SelectState(this);
		start = new StartState(this);
		end = new EndState(this);
		instruct = new InstructionState(this);
	}

	/**
	 * Method to load the assets to be used
	 * 
	 */
	public void load() {
		sprite = new Sprite(img);
		sprite.setSize(300, 400);
	}

	@Override
	/**
	 * Method which will always be running
	 * 
	 */
	public void render() {
		ScreenUtils.clear(1, 0, 0, 1);

		//setting screen to show which screen the player should be on
		if (gameState == "arena" && screen == true) {
			this.setScreen(arena);
			screen = false;
		} else if (gameState == "openWrld" && screen == true) {
			this.setScreen(openWrld);
			screen = false;
		} else if (gameState == "select" && screen == true) {
			this.setScreen(select);
			screen = false;
		 } else if (gameState == "end" && screen == true) {
			this.setScreen(end);
			screen = false;
		 }
		else if (gameState == "start" && screen == true){
			this.setScreen(start);
			screen = false;
		}
		else if (gameState == "instruct" && screen == true){
			this.setScreen(instruct);
			screen = false;
		}

		super.render();
	}



	@Override
	/**
	 * Method to be run when the class is disposed
	 * 
	 */
	public void dispose() {
		batch.dispose();
	}
}
