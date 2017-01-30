/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pong;

import java.awt.Color;
import java.awt.Graphics;

/**
 *
 * @author Anmol
 */
public class Block implements Locatable {
    
    //all the instance fields of the Block class are PRIVATE!
    private int xPos;
    private int yPos;
    private int height;
    private int width; 
    private Color color; 
    
    //c) A default constructor that creates a 10 x 10 BLACK Block at (100, 150).
    public Block()
    {
        this.height = this.width = 10; 
        this.xPos = 100;
        this.yPos = 150; 
        this.color = Color.BLACK; 
    }
    
    public Block(int x, int y)
    {
        this.xPos = x;
        this.yPos = y;
        
        //set values for the attributes for which the parameters are not provided!
        this.width = this.height = 10;
        this.color = Color.BLACK;
    }
    
    public Block(int x, int y, int w, int h)
    {
        this.xPos = x;
        this.yPos = y;
        this.width = w;
        this.height = h; 
        //set values for the attributes for which the parameters are not provided!
        this.color = Color.BLACK; 
    }
    
    public Block(int x, int y, int w, int h, Color c)
    {
        this.xPos = x;
        this.yPos = y;
        this.width = w;
        this.height = h; 
        this.color = c;
    }
    //end of all constructor for the block class!

    public int getxPos() { //xPos getter
        return xPos;
    }

    public void setxPos(int xPos) {  //xPos setter
        this.xPos = xPos;
    }

    public int getyPos() { //yPos getter
        return yPos;
    }

    public void setyPos(int yPos) {  //yPos setter
        this.yPos = yPos;
    }

    public int getHeight() {  //height getter
        return height;
    }

    public void setHeight(int height) {  //height setter
        this.height = height;
    }

    public int getWidth() {  //width getter
        return width;
    }

    public void setWidth(int width) {  //width setter
        this.width = width;
    }

    public Color getColor() {  //gets the color of the block
        return color;
    }

    public void setColor(Color color) {  //sets the color of the block
        this.color = color;
    }
    
    
  
    @Override
    public boolean equals(Object arg)  //tests to see if an object is the same as "this" block
    {
        Block other = (Block) arg;  //object type is casted to a Block type
        return (this.xPos == other.xPos) &&
                (this.yPos == other.yPos) &&
                (this.width == other.width) &&
                (this.height == other.height) &&
                (this.color.equals(other.color));  //if all the instance fields are the same, then true will be returned
    }
    
    
 
    @Override
    public String toString()
    {
        //x y w h c
        return String.format("%d %d %d %d %s",this.xPos, this.yPos, this.width, this.height, this.color.toString());
    }

    
    
    //these are the abstract methods in the Locatable interface
    @Override
    public void setPos(int x, int y) {
       this.setX(x);
       this.setY(y);
    }

    @Override
    public void setX(int x) {
        this.xPos = x; 
    }

    @Override
    public void setY(int y) {
        this.yPos =  y; 
    }

    @Override
    public int getX() {
        return this.xPos; 
    }

    @Override
    public int getY() {
        return this.yPos;  
    }
    
    
    public void draw ( Graphics window )
    {
	window.setColor(this.color);  //fills the color in the block
	window.fillRect(getX(), getY(), getWidth(), getHeight());  //the block is being drawn on screen
    }
    
    public void draw ( Graphics window, Color colorParameter )
    {
	window.setColor(colorParameter);  //the block is filled with the desired color
	window.fillRect(getX(), getY(), getWidth(), getHeight());   //the block is being drawn on screen
    }
    
    
}
