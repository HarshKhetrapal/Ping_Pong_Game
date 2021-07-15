package PongGame;

import java.awt.Graphics;

public interface IPaddle {
	/**
	 * Helps in drawing the the paddle.
	 * @param graphics
	 */
	public void draw(Graphics graphics);
	/**
	 * Helps in moving the paddle up or down.
	 */
	public void move();
	/**
	 * give the y location of the paddle.
	 * @return
	 */
	public int getYLocation();
	/**
	 * moves the paddle in the UP direction.
	 * @param input
	 */
	public void setUpAccel(boolean input);
	/**
	 * moves the paddle in the DOWN direction.
	 * @param input
	 */
	public void setDownAcel(boolean input);

}
