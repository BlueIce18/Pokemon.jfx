package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * The rectangle object
 * 
 * @author Faisal, Jovan, Ryder, Shael
 * @version 1.0 2023-06-09
 */
public class Rect extends Main {

   SpriteBatch batch;
   Texture img = new Texture("GreyRectangle.png"); // image of grey rectangle 
   Sprite sprite;

   float posX; //position and dimensions of rectangle
   float posY;
   float width;
   float height;

   /**
    *Contructor to create rect object 
    *
    * @param x, the x coordinate of the rectangle
    * @param y, the y coordinate of the rectangle
    */
   public Rect(float x, float y) {
      sprite = new Sprite(img);
      posX = x;
      posY = y;
      width = 200;
      height = 125;
      sprite.setSize(width, height);
   }

   /**
    * Contructor to create rect object 
    *
    * @param x, the x coordinate of the rectangle
    * @param y, the y coordinate of the rectangle
    * @param w, the width of the rectangle
    * @param h, the height of the rectangle
    */
   public Rect(float x, float y, float w, float h) {
      sprite = new Sprite(img);
      posX = x;
      posY = y;
      width = w;
      height = h;
      sprite.setSize(width, height);
   }

   /**
    * Method to draw the rectangle
    *
    * @param batch, the batch/canvas to draw te rectangle on
    */
   public void Draw(SpriteBatch batch) {
      sprite.setPosition(posX, posY);
      sprite.draw(batch);
   }


}
