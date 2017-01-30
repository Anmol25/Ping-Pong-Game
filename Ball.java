
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pong;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;

/**
 *
 * @author Anmol
 */
public class Ball extends Block{
    
    private int xSpeed;
    private int ySpeed; 
    
    public Ball()
    {
        super(200,200,10,10,Color.black); 
        xSpeed = 3;
        ySpeed = 1; 
    }
    
    public Ball(int x, int y)
    {
        super(x,y,10,10,Color.BLACK);
        xSpeed = 3;
        ySpeed = 1;
    }
    
    public Ball(int x, int y, int w, int h)
    {
        super(x,y,w,h,Color.BLACK);
        xSpeed = 3;
        ySpeed = 1;
    }
    
    public Ball(int x, int y, int w, int h, Color c)
    {
        super(x,y,w,h,c);
        xSpeed = 3;
        ySpeed = 1;
    }
    
    public Ball(int x , int y, int w, int h, Color c, int xS, int yS)
    {
        super(x,y,w,h,c);
        this.xSpeed = xS;
        this.ySpeed = yS; 
    }

    public int getXSpeed() {
        return xSpeed;
    }

    public void setXSpeed(int xSpeed) {
        this.xSpeed = xSpeed;
    }

    public int getYSpeed() {
        return ySpeed;
    }

    public void setYSpeed(int ySpeed) {
        this.ySpeed = ySpeed;
    }
    
    public boolean equals(Object arg) //passed as an Object arguement
    {
        Ball other = (Ball) arg; //object arguement is casted as type Ball
        return (super.equals(other) && 
                (this.xSpeed == other.xSpeed) &&
                 (this.ySpeed == other.ySpeed)); 
        
    }
    
    
    public void moveAndDraw(Graphics window)
    {
        draw(window, Color.WHITE); //make the ball the same color as the back ground
        setX(getX() + xSpeed);//change the xPos of the ball
        setY(getY() + ySpeed);//change the yPos of the ball
        draw(window, super.getColor());//reraw the ball in the new location
    }
    
    @Override
    public String toString()
    {
        return String.format("%s %d %d",super.toString(), this.xSpeed, this.ySpeed); 
    }
}
