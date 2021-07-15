package PongGame;

import java.awt.Color;
import java.awt.Graphics;

public class PaddlePlayerPC implements IPaddle
{
	static final double GRAVITY = 0.94;
	double yLocation;
	int xLocation;
	Ball ball;

	public PaddlePlayerPC(Ball ball)
	{
		yLocation = 210; 
		this.ball = ball;
			// else for player 2 then paddle will be on the right side.
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
		// automatically moving the paddle based on the y position of ball and allowing to
		// hit the ball at the center of the paddle.
		yLocation = (double) (ball.getYLocation()-40);
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
	public void setUpAccel(boolean input) {
		// PC paddle gets automatically accelerated.
	}
	
	/*
	 * (non-Javadoc)
	 * @see PongGame.IPaddle#setDownAcel(boolean)
	 */
	@Override
	public void setDownAcel(boolean input) {
		// PC paddle gets automatically accelerated.
		
	}
	
}
