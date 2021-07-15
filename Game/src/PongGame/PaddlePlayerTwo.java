package PongGame;

import java.awt.Color;
import java.awt.Graphics;

public class PaddlePlayerTwo implements IPaddle
{
	static final double GRAVITY = 0.94;
	double yLocation;
	double yVel;
	boolean upAccel;
	boolean downAccel;
	int xLocation;

	public PaddlePlayerTwo()
	{
		upAccel = false;
		downAccel = false;
		yLocation = 210; 
		yVel = 0;
		// if player 2 then paddle will be on the right side.
		xLocation = 660;
	}
	
	/*
	 * (non-Javadoc)
	 * @see PongGame.IPaddle#draw(java.awt.Graphics)
	 */
	@Override
	public void draw(Graphics graphics) 
	{
		// creating paddle with white color with size of 20*80
		graphics.setColor(new Color(112, 76, 20));
		graphics.fillRect(xLocation, (int)yLocation, 20, 80);
		
		
	}

	/*
	 * (non-Javadoc)
	 * @see PongGame.IPaddle#move()
	 */
	@Override
	public void move() {
		if(upAccel)
		{
			// if up button is pressed, paddle should move upwards with y velocity.
			// 0,0 is at the top left corner so to move paddle upward yvel - 2;
			yVel -= 2;
			
		}
		else if(downAccel)
		{
			// if down button is pressed, paddle should move downwards with y velocity.
			// 0,0 is at the top left corner so to move paddle upward yvel + 2;
			yVel += 2;
			
		}
		else if(!upAccel && !downAccel)
		{
			// if button(up or down) is released so need to slow down.
			// slowing down using the gravity.
			yVel *= GRAVITY;
		}
		
		// update y to move paddle with y velocity as set above based on the condition.
		if(yVel >= 5)
		{
			// to not move paddle too fast in downward direction.
			yVel = 5;
		}
		else if(yVel <= -5)
		{
			// to not move paddle too fast in upward direction.
			yVel = -5;
		}
		yLocation += yVel;
		
		if(yLocation < 0)
		{
			// to not allow paddle to move out of screen while moving towards upward direction.
			yLocation = 0;
		}
		else if(yLocation > 420)
		{
			// to not allow paddle to move out of screen while moving towards downwards direction.
			yLocation = 420; 
		}
	}

	/*
	 * (non-Javadoc)
	 * @see PongGame.IPaddle#getY()
	 */
	@Override
	public int getYLocation() {
		return (int)yLocation;
	}
	
	/*
	 * (non-Javadoc)
	 * @see PongGame.IPaddle#setUpAccel(boolean)
	 */
	@Override
	public void setUpAccel(boolean input)
	{
		// paddle is moving towards upwards.
		upAccel = input;
	}
	
	/*
	 * (non-Javadoc)
	 * @see PongGame.IPaddle#setDownAcel(boolean)
	 */
	@Override
	public void setDownAcel(boolean input)
	{
		// paddle is moving towards down.
		downAccel = input;
	}

}
