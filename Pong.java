/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pong;

//Name - 
//Date - 
//Class -
//Lab  - 

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Canvas;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;
import static java.lang.Character.*;
import java.awt.image.BufferedImage;
import java.awt.event.ActionListener;
import javax.swing.Timer;


public class Pong extends Canvas implements KeyListener, Runnable
{
	private Ball ball;
	private Paddle leftPaddle;
	private Paddle rightPaddle;
        
        //these are the booleans for the key listener!
	private boolean leftUp, leftDown, rightUp, rightDown; 
	
        
        private BufferedImage back;
  
	//add variables for scorekeeping
     
        private int leftPlayerScore;  
        private int rightPlayerScore; 
        
        private boolean intro = true; //this will start the "welcome screen" for the Pong game
        
        private boolean timer = false; 
        
        private boolean playing = false; //when this becomes true, the game will start
        
        private boolean gameOver = false; //this becomes true when someone wins
        
        private boolean rightWon = false;//if right player won, this becomes true
        
        private boolean leftWon = false; //if left player won, this becomes true

	public Pong()
	{
		//set up all variables related to the game

                ball = new Ball(900/2,250);
                ball.setColor(Color.red);
                
               
             
                leftPaddle = new Paddle(90,225,10,50,5);
                rightPaddle = new Paddle(800,225,10,50,5);
                
                


		leftUp = leftDown = rightUp = rightDown = false; 

    		setBackground(Color.WHITE);
		setVisible(true);
		
		new Thread(this).start();
		addKeyListener(this);	//starts the key thread to log key strokes
	}
	
   public void update(Graphics window){
	   paint(window);
   }

   public void paint(Graphics window)
   {
		//set up the double buffering to make the game animation nice and smooth
		Graphics2D twoDGraph = (Graphics2D)window;

		//take a snapshot of the current screen and save it as an image
		//that is the exact same width and height as the current screen
		if(back==null)
		   back = (BufferedImage)(createImage(getWidth(),getHeight()));

		//create a graphics reference to the back ground image
		//we will draw all changes on the background image
		Graphics backWindow = back.createGraphics();
                
                
                if(intro)
                {
                    //these lines of code sets up the welcome screen for the game!
                   backWindow.setFont(new Font(Font.DIALOG, Font.BOLD, 20));
                   backWindow.setColor(Color.red);
                    
                    backWindow.fillRect(0, 0, 900, 600);
                    
                    backWindow.setColor(Color.yellow);
                   
                   backWindow.drawString("Welcome to Anmol's Pong game!", 320, 150);
                   backWindow.drawString("First Player to score 5 points, WINS!", 295, 260);
                   backWindow.drawString("Press 'P' to play!", 370, 390);
                   
                   
                   //start the game with both players having o score!
                   rightPlayerScore = leftPlayerScore = 0; 
                }
                
                
                
                
                if(playing)
                {
                
               
                backWindow.clearRect(0, 0, 900, 600);
                
                backWindow.setColor(Color.red);
                backWindow.fillRect(0, 0, 90,510);
                backWindow.fillRect(810, 0, 90, 510);
                backWindow.fillRect(0, 510, 900, 100);
                
                
                
                    
                backWindow.setColor(Color.yellow);
                backWindow.fillRect(90, 0, 720, 510);
                 backWindow.setFont(new Font(Font.DIALOG, Font.BOLD, 36));
               
                backWindow.drawString(String.valueOf(leftPlayerScore), 50, 500/2);
                backWindow.drawString("L", 50, 100);
                backWindow.drawString(String.valueOf(rightPlayerScore), 850, 500/2);
                backWindow.drawString("R", 850, 100);
                
                
                backWindow.drawLine(0, 510, 900, 510);
                backWindow.drawLine(90,0, 90, 600);
                backWindow.drawLine(810, 0, 810, 600);
                
                
                
		ball.moveAndDraw(backWindow);
		leftPaddle.draw(backWindow);
		rightPaddle.draw(backWindow);
                
                
                if(leftUp) //if W is pressed, the left paddle will go up
                    {
                        if (leftPaddle.getY()-leftPaddle.getSpeed() > 0)
                        {
                            leftPaddle.moveUpAndDraw(backWindow);
                        }
                    }
                    
                    if(leftDown) //if Z is pressed, the left paddle will go down
                    {
                        if (leftPaddle.getY()+leftPaddle.getSpeed() + leftPaddle.getHeight() <= 510)
                        {
                            leftPaddle.moveDownAndDraw(backWindow);
                        }
                    }
                    
                    
                     if(rightUp) //if I is pressed, the right paddle will go up
                    {
                        if (rightPaddle.getY()-rightPaddle.getSpeed() > 0)
                        {
                            rightPaddle.moveUpAndDraw(backWindow);
                        }
                    }
                    
                    if(rightDown)  //if M is pressed, the right paddle will go down
                    {
                        if (rightPaddle.getY()+rightPaddle.getSpeed() + rightPaddle.getHeight() <= 510)
                        {
                            rightPaddle.moveDownAndDraw(backWindow);
                        }
                    }
                    
                    
                    
             //see if the ball hits the top or bottom wall 
                if(!(ball.getY()>=0 && ball.getY()<=500))
                {
                    ball.setYSpeed(-ball.getYSpeed());
                    
                }
                
                //the location of the ball and paddles in the game
            int ballLeft = ball.getX() + ball.getXSpeed();
            int ballRight = ball.getX() + ball.getWidth() + ball.getXSpeed();
            int ballTop = ball.getY() + ball.getYSpeed();
            int ballBottom = ball.getY() + ball.getHeight() + ball.getYSpeed();

            int leftPaddleRight = leftPaddle.getX() + leftPaddle.getWidth();
            int leftPaddleTop = leftPaddle.getY();
            int leftPaddleBottom = leftPaddle.getY() + leftPaddle.getHeight();

            float rightPaddleLeft = rightPaddle.getX();
            float rightPaddleTop = rightPaddle.getY();
            float rightPaddleBottom = rightPaddle.getY() + rightPaddle.getHeight();


            

            //will the ball go off the left side?
            if (ballLeft < leftPaddleRight) { 
                //is it going to miss the paddle?
                if (ballTop > leftPaddleBottom || ballBottom < leftPaddleTop) {

                    rightPlayerScore++;

                    if (rightPlayerScore == 5) {
                        playing = false;
                        gameOver = true;
                        rightWon = true;
                    }

                    ball.setPos(400, 250);
                }
                else {
                    
                    ball.setXSpeed(-ball.getXSpeed());
                }
            }

            //will the ball go off the right side?
            if (ballRight > rightPaddleLeft) {
                //is it going to miss the paddle?
                if (ballTop > rightPaddleBottom || ballBottom < rightPaddleTop) {

                    leftPlayerScore++;

                    if (leftPlayerScore == 5) {
                        playing = false;
                        gameOver = true;
                        leftWon = true;
                    }

                    ball.setPos(400, 250);
                }
                else {
                    
                    ball.setXSpeed(-ball.getXSpeed());
                }
            }
            
            
            

            
        }

              
               
                
                if(gameOver)  //when one of the player wins, gameOver will become true
                {
                   backWindow.clearRect(0, 0, 1000, 1000);
                    
                  
                    
                    if(leftWon)
                    {
                        int tempLeftPlayerScore = leftPlayerScore;
                        int tempRightPlayerScore = rightPlayerScore; 
                         backWindow.setColor(Color.red);
                    
                    backWindow.fillRect(0, 0, 900, 600);
                    
                    backWindow.setColor(Color.yellow);
                        backWindow.setFont(new Font(Font.DIALOG, Font.BOLD, 20));
                        
                        backWindow.drawString("THE LEFT PLAYER HAS WON!", 300, 200);
                        backWindow.drawString("Press 'P' to play again!", 350, 300); 
                        
                        
                    }
                    if(rightWon)
                    {
                        int tempLeftPlayerScore = leftPlayerScore;
                        int tempRightPlayerScore = rightPlayerScore; 
                         backWindow.setColor(Color.red);
                    
                    backWindow.fillRect(0, 0, 900, 600);
                    
                    backWindow.setColor(Color.yellow);
                        backWindow.setFont(new Font(Font.DIALOG, Font.BOLD, 20));
                       
                        backWindow.drawString("THE RIGHT PLAYER HAS WON!", 300, 200);
                        backWindow.drawString("Press 'P' to play again!", 350, 300); 
                        
                        
                    }
                    //after displaying result, set the score for both players to 0!
                    rightPlayerScore = leftPlayerScore = 0;
                }



		
		twoDGraph.drawImage(back, null, 0, 0);
	}

	public void keyPressed(KeyEvent e)
	{
		switch(toUpperCase(e.getKeyChar()))
		{
			case 'W' : leftUp=true;  break;
			case 'Z' : leftDown=true;  break;
			case 'I' : rightUp=true;  break;
			case 'M' : rightDown=true;  break;
                        case 'P' : intro = false; playing = true;  gameOver = false; break; 
                         
		}
	}

	public void keyReleased(KeyEvent e)
	{
		switch(toUpperCase(e.getKeyChar()))
		{
			case 'W' : leftUp=false; break;
			case 'Z' : leftDown=false; break;
			case 'I' : rightUp=false; break;
			case 'M' : rightDown=false; break;
		}
	}

	public void keyTyped(KeyEvent e){}
	
   public void run()
   {
   	try{
   		while(true)
   		{
   		   Thread.currentThread().sleep(8);
            	   repaint();
        	}
        }catch(Exception e)
             	{
     		}
  	}	
}