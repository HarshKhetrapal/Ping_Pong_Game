package PongGame;

import java.awt.Graphics;

public class Paddle 
{
	private IPaddle paddle;
	
	public Paddle(IPaddle paddle) {
		// create a strategy new object.
		this.paddle = paddle;
	}
	
	/**
	 * draw the strategy object.
	 * @param graphics
	 */
	public void draw(Graphics graphics)
	{
		// call the draw method of strategy object.
		this.paddle.draw(graphics);
	}
	
	/**
	 * call the move method of strategy object.
	 */
	public void move()
	{
		// call the move method of strategy object.
		this.paddle.move();
	}
	
	/**
	 * call the getY method of strategy object.
	 * @return
	 */
	public int getYLocation()
	{
		// call the getY method of strategy object.
		return this.paddle.getYLocation();
	}
	
	/**
	 * call the setUpAccel method of strategy object.
	 * @param input
	 */
	public void setUpAccel(boolean input)
	{
		// call the setUpAccel method of strategy object.
		this.paddle.setUpAccel(input);
	}
	
	/**
	 * call the setDownAccel method of strategy object.
	 * @param input
	 */
	public void setDownAcel(boolean input)
	{
		// call the setDownAccel method of strategy object.
		this.paddle.setDownAcel(input);
	}
	
	/**
	 * return the specific strategy object.
	 * @return
	 */
	public IPaddle getPaddle()
	{
		// return the specific strategy object.
		return this.paddle;
	}
}