package PongGame;

import java.awt.Color;
import java.awt.Graphics;
import java.util.HashMap;
import java.util.LinkedHashMap;

public class Ball {
	double xVel;
	double yVel;
	double xLocation;
	double yLocation;
	int paddleOneHit;
	int paddleTwoHit;
	LinkedHashMap<Integer, Integer> paddleLeftScore;
	LinkedHashMap<Integer, Integer> paddleRighttScore;
	boolean ballHitByLeftPaddle = true;
	boolean ballHitByRightPaddle = true;
	Level level = new Level();
	
	public Ball() {
		// initial position for ball on the applet screen.
		xLocation = 350;
		yLocation = 250;
		// initial movement of ball on the applet.
		xVel = -2 * getRabdomDirection();
		yVel = 1 * getRabdomDirection();
		paddleOneHit = 0;
		paddleTwoHit = 0;
		paddleLeftScore = new LinkedHashMap<>();
		paddleRighttScore = new LinkedHashMap<>();
	}
	
	/**
	 * gives the random direction to the ball to move.
	 * @return
	 */
	public int getRabdomDirection()
	{
		// * 2 gives random value either 0 or 1
		int rand = (int)(Math.random() *2);
		if(rand == 1)
		{
			return 1;
		}
		else
		{
			return -1;
		}
		
	}
	
	/**
	 * draws the ball in the application.
	 * @param graphics
	 */
	public void draw(Graphics graphics)
	{
		// drawing / creating a ball on applet.
		graphics.setColor(Color.WHITE);
		// subtracted 10 from x and y because x and y represents the top left corner but we want x and y 
		// to represent at the center of the circle so subtracting 10 because 20 is the diameter of the circle.
		graphics.fillOval((int)xLocation-10, (int)yLocation-10, 20, 20);
	}
	
	/**
	 * Checks if the ball hit any paddle or not.
	 * Checks the level and set the score accordingly.
	 * @param p1
	 * @param p2
	 */
	public void checkBallHittingPaddle(IPaddle p1, IPaddle p2)
	{
		// check if ball hit right paddle
		if(xLocation <= 40)
		{
			//check if ball hitting the circumference of the paddle
			if(yLocation >= p1.getYLocation() && yLocation <= p1.getYLocation()+80)
			{
				xVel = -xVel;
				if(this.ballHitByLeftPaddle)
				{
					paddleOneHit++;
					level.getLevel(this);
					level.setScore(this, true, false);
					//paddleLeftScore.put(paddleOneHit, 1);
					this.ballHitByLeftPaddle = false;
					this.ballHitByRightPaddle = true;
				}
				
			}
		}
		// check if ball hit left paddle
		else if(xLocation >= 650)
		{
			//check if ball hitting the circumference of the paddle
			if(yLocation >= p2.getYLocation() && yLocation <= p2.getYLocation()+80)
			{
				xVel = -xVel;
				if(this.ballHitByRightPaddle)
				{
					paddleTwoHit++;
					level.getLevel(this);
					level.setScore(this, false, true);
					//paddleRighttScore.put(paddleTwoHit, 1);
					this.ballHitByRightPaddle = false;
					this.ballHitByLeftPaddle = true;
				}
			}
		}
	}
	
	/**
	 * move the ball.
	 */
	public void move()
	{
		xLocation += xVel;
		yLocation += yVel;
		// Not allow ball to go off the screen(applet).
		// comparing with 10 and 490 because radius of circle is 10.
		if(yLocation < 10)
		{
			yVel = -yVel;
		}
		if(yLocation > 490)
		{
			yVel = -yVel;
		}
	}
	
	/**
	 * returns the Y direction of the ball.
	 * @return
	 */
	public int getXLocation()
	{
		// returns int value of x position(coordinate) of ball
		return (int)xLocation;
	}

	/**
	 * get the X direction of the ball.
	 * @return
	 */
	public int getYLocation()
	{
		// returns int value of y position(coordinate) of ball
		return (int)yLocation;
	}
}
