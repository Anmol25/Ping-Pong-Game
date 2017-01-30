/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pong;

import java.awt.Color;
import java.awt.Graphics;

public class Paddle extends Block
{
   //exactly one instance varaible
    //apart from those inhertied by
    //the block class!
   private int speed;

   
   public Paddle()
   //A default constructor that creates a 10 x 10 BLACK Paddle at location(10, 10), with speed of 5.
   {
     super(10,10,10,10,Color.BLACK);
     this.speed = 5;
   }
   
   


   //add the other Paddle constructors

   public Paddle(int x, int y)
   {
       // takes in x,y
      setPos(x,y);
      setWidth(10);
      setHeight(10);
      setColor(Color.BLACK);
      this.speed = 5; 
   }
   
   public Paddle(int x, int y, int w)
   {
       // x y width
       setPos(x,y);
       setWidth(w);
       setHeight(10);
       setColor(Color.BLACK);
       
       this.speed = 5;
   }
   
   public Paddle(int x, int y, int w , int h, int speed)
   {
       // x y w h speed
       setPos(x,y);
       setWidth(w);
       setHeight(h);
       setColor(Color.BLACK);
       this.speed = speed;
   }
   
   public Paddle(int x, int y, int w, int h, Color c, int speed)        
   {
       setPos(x,y);
       setWidth(w);
       setHeight(h);
       setColor(c);
       this.speed = speed;
   }
   //for all the constructors, i am giving values to attributes
   //that do not have a parameter value to avoid a null values.
   //thus not having a compile time error!







   public void moveUpAndDraw(Graphics window)
   {
       draw(window, Color.WHITE);
       setY(getY() - this.speed);
       draw(window, super.getColor());

       //set the color of the paddle to white
       //when the paddle is "invisible" -> change it's y location
       //when the y-location is changed, color it in!
   }

   public void moveDownAndDraw(Graphics window)
   {
       draw(window, Color.WHITE);
       setY(getY() + this.speed);
       draw(window, super.getColor());

        //set the color of the paddle to white
       //when the paddle is "invisible" -> change it's y location
       //when the y-location is changed, color it in!
   }

   //get method for speed
   
   public int getSpeed()
   {
       return this.speed;
   }
   
   //set method for speed
   public void setSpeed(int s)
   {
       this.speed = s;
   }
   
   
   //add a toString() method
   
   public String toString()
   {
       return String.format("%s %d",super.toString(), this.speed); 
   }
   
   //equals method
   
   @Override
   public boolean equals(Object arg)
   {
       //cast the Object to a paddle!
       Paddle x = (Paddle) arg;
       
       
       //make sure their inhertied attributes are the same
       //then make sure that non-inherited attribute (the speed)
       //is also the same
       //if so, then both the paddles truly are exactly "equal"!
       return(super.equals(arg)  && (this.speed == x.speed));
       
   }
}