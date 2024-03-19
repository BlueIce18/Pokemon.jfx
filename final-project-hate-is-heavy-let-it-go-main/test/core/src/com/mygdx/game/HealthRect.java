package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * The healthbar rectangle object
 * 
 * @author Faisal, Jovan, Ryder, Shael
 * @version 1.0 2023-06-09
 */
public class HealthRect extends Main {

  SpriteBatch batch;
  Texture img = new Texture("Health.png"); //healthbar picture
  Sprite sprite;

  float posX; //position of healthbar
  float posY;

  float width; //dimensions of healthbar
  float height;

  double fullHealth; //full health of pokemon 

  /**
   * Constructor to instanciate the healthRect
   * 
   * @param x, x position of healthbar
   * @param y, y position of healthbar
   */
  public HealthRect(float x, float y) {

    sprite = new Sprite(img); //setting dimensions
    posX = x;
    posY = y;
    width = 200;
    height = 125;
    sprite.setSize(width, height);
  }

  /**
   * Constructor to instanciate the healthRect
   * 
   * @param x, x position of rectangle
   * @param y, y position of rectangle
   * @param w, width of healthbar
   * @param h, height of healthbar
   * @param hp, total health of pokemon
   */
  public HealthRect(float x, float y, float w, float h, double hp) {
    sprite = new Sprite(img);
    posX = x;
    posY = y;
    fullHealth = hp;
    width = w;
    height = h;
    sprite.setSize(width, height);
  }

  /**
   * Method to draw the healthbar
   * 
   * @param batch, the batch to be drawn on
   * @param hp, the current hp of the pokemon
   */
  public void Draw(SpriteBatch batch, double hp) {
    sprite.setPosition(posX, posY); //drawing the healthbar
    if (hp <= 0) {
      hp = 0;
    }
    sprite.setSize((float) (width * (hp / fullHealth)), height);
    sprite.draw(batch);
  }

}
