package PongGame;

import java.applet.Applet;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Pong extends Applet implements Runnable, KeyListener 
{
	/**
	 * 
	 */
	private static final long serialVersionUID = -7639630515030291975L;
	final int WIDTH = 700;
	final int HEIGHT = 500;
	Thread thread;
	Paddle paddle1;
	Paddle paddle2;
	Paddle pcPaddle;
	Ball ball;
	boolean gameStartedWithPC;
	boolean gameStartedWithTwoPlayer;
	Graphics secondaryGraphics;
	Image image;
	Level level = new Level();
	
	/*
	 * (non-Javadoc)
	 * @see java.applet.Applet#init()
	 */
	@Override
	public void init() 
	{
		this.resize(WIDTH, HEIGHT);
		this.addKeyListener(this);
		// initializing paddle one on the left side(1) 
		paddle1 = new Paddle(new PaddlePlayerOne());
		// initializing paddle one on the right side(2) 
		paddle2 = new Paddle(new PaddlePlayerTwo());
		// initializing ball.
		ball = new Ball();
		// initializing PC paddle on the right side.
		pcPaddle = new Paddle(new PaddlePlayerPC(ball));
		// initialize high score
		HighScoreHelper.initHighScore();
		// initializing image will create everything in image using secondary graphics
		image = createImage(WIDTH, HEIGHT);
		// initializing secondary graphics will use image graphics to draw / recreate things on image
		secondaryGraphics = image.getGraphics();
		gameStartedWithPC = false;
		gameStartedWithTwoPlayer = false;
		thread = new Thread(this);
		thread.start();
		
	}
	
	/*
	 * (non-Javadoc)
	 * @see java.awt.Container#paint(java.awt.Graphics)
	 */
	@Override
	public void paint(Graphics graphics)
	{
		secondaryGraphics.setColor(new Color(23, 139, 26));
		secondaryGraphics.fillRect(0, 0, WIDTH, HEIGHT);
		if(ball.getXLocation() < 10  || ball.getXLocation() > 710)
		{
			// checking if ball collide on left or right side then game over.
			secondaryGraphics.setColor(Color.white);
			secondaryGraphics.drawString("Game Over", 325, 220);
			secondaryGraphics.drawString(this.getWinner(), 275, 250);
			//check highScore break
			if(HighScoreHelper.isHighScoreBroke(ball.paddleLeftScore.values().stream().mapToInt(Integer :: intValue).sum(), 
					ball.paddleRighttScore.values().stream().mapToInt(Integer :: intValue).sum()))
			{
				secondaryGraphics.drawString("New High Score Conratulations  !!!!!!!!!", 260, 280);
			}
			// drawing image on primary graphics of applet
			graphics.drawImage(image, 0, 0, this);	
			try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				Thread.currentThread().interrupt();
			}
			this.initiForRerun();
		}
		else
		{
			// with appllet also create a paddle on the applet.
			paddle1.draw(secondaryGraphics);
			// with appllet also create a ball on the applet.
			ball.draw(secondaryGraphics);
			if(gameStartedWithPC)
			{
				// with appllet also create a paddle on the applet.
				pcPaddle.draw(secondaryGraphics);
			}
			
			else if(gameStartedWithTwoPlayer)
			{
				// with appllet also create a paddle on the applet.
				paddle2.draw(secondaryGraphics);
			}
			
		}
		
		if(!gameStartedWithPC && !gameStartedWithTwoPlayer)
		{
			secondaryGraphics.setColor(Color.BLACK);
			secondaryGraphics.drawString("Press 1 to play with Computer!", 300, 130);
			secondaryGraphics.drawString("Press 2 for two player!", 320, 150);
			
		}
		if(gameStartedWithPC || gameStartedWithTwoPlayer)
		{
			secondaryGraphics.setColor(Color.BLACK);
			secondaryGraphics.drawString(String.valueOf(ball.paddleLeftScore.values().stream().mapToInt(Integer :: intValue).sum()), 175, 125);
			secondaryGraphics.drawString(String.valueOf(ball.paddleRighttScore.values().stream().mapToInt(Integer :: intValue).sum()), 525, 125);
			secondaryGraphics.setColor(Color.WHITE);
			secondaryGraphics.drawLine(350, 0, 350, 500);
			secondaryGraphics.drawLine(30, 0, 30, 500);
			secondaryGraphics.drawLine(660, 0, 660, 500);
			secondaryGraphics.drawLine(30, 250, 660, 250);
		}
		// drawing image on primary graphics of applet
		graphics.drawImage(image, 0, 0, this);	
	}
	
	/**
	 * re initializes the variable to rerun the game.
	 */
	private void initiForRerun() {
		// re setting the variables to rerun after game over.
		ball.xLocation =350;
		ball.yLocation=250;
		ball.paddleOneHit = 0;
		ball.paddleTwoHit = 0;
		level.setLevel(ball, level.LevelOne);
		ball.ballHitByLeftPaddle = true;
		ball.ballHitByRightPaddle = true;
		ball.paddleLeftScore.clear();
		ball.paddleRighttScore.clear();
		gameStartedWithPC = false;
		gameStartedWithTwoPlayer = false;
	}
	
	/**
	 * decide the winner based on the score/points
	 * @return
	 */
	private String getWinner() 
	{
		// get the winner.
		String winner;
		if(ball.paddleLeftScore.size() > ball.paddleRighttScore.size())
		{
			// left paddle is a winner.
			winner = "Left Side player wins by " + ball.paddleLeftScore.values().stream().mapToInt(Integer :: intValue).sum() + " points.";
		}
		else if(ball.paddleLeftScore.size() < ball.paddleRighttScore.size())
		{
			// right paddle is a winner.
			winner = "Right Side player wins by " + ball.paddleRighttScore.values().stream().mapToInt(Integer :: intValue).sum() + " points.";
		}
		else
		{
			// it's a draw.
			winner = "Draw between players point " + ball.paddleRighttScore.values().stream().mapToInt(Integer :: intValue).sum() + " points.";
		}
		return winner;
	}

	/*
	 * (non-Javadoc)
	 * @see java.awt.Container#update(java.awt.Graphics)
	 */
	@Override
	public void update(Graphics graphics)
	{
		paint(graphics);
		
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Runnable#run()
	 */
	@Override
	public void run() 
	{
		while	(true)
		{
			// start the game once the user select single or multi player option is hit.
			if(gameStartedWithPC)
			{
				//game starting for single player.
				// move the paddle on the applet.
				paddle1.move();
				// move the paddle on the applet.
				pcPaddle.move();
				// move ball on the applet.
				ball.move();
				ball.checkBallHittingPaddle(paddle1.getPaddle(), pcPaddle.getPaddle());
			}
			else if(gameStartedWithTwoPlayer)
			{
				// game starting for multi player.
				// move the paddle on the applet.
				paddle1.move();
				// move the paddle on the applet.
				paddle2.move();
				// move ball on the applet.
				ball.move();
				ball.checkBallHittingPaddle(paddle1.getPaddle(), paddle2.getPaddle());
			}
			repaint();
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	/*
	 * (non-Javadoc)
	 * @see java.awt.event.KeyListener#keyPressed(java.awt.event.KeyEvent)
	 */
	@Override
	public void keyPressed(KeyEvent e) 
	{
		if(e.getKeyCode() == KeyEvent.VK_W)
		{
			// once the up button is pressed set in paddle that it needs to move in upward direction.
			paddle1.setUpAccel(true);
		}
		else if(e.getKeyCode() == KeyEvent.VK_S)
		{
			// once the up button is pressed set in paddle that it needs to move in downwards direction.
			paddle1.setDownAcel(true);
		}
		else if(e.getKeyCode() == KeyEvent.VK_1)
		{
			// Start the game when enter is pressed.
			gameStartedWithPC = true;
		}
		else if(e.getKeyCode() == KeyEvent.VK_2)
		{
			// Start the game when enter is pressed.
			gameStartedWithTwoPlayer = true;
		}
		else if(e.getKeyCode() == KeyEvent.VK_UP)
		{
			// once the up button is pressed set in paddle that it needs to move in upward direction.
			paddle2.setUpAccel(true);
		}
		else if(e.getKeyCode() == KeyEvent.VK_DOWN)
		{
			// once the up button is pressed set in paddle that it needs to move in downwards direction.
			paddle2.setDownAcel(true);
		}
		
	}

	/*
	 * (non-Javadoc)
	 * @see java.awt.event.KeyListener#keyReleased(java.awt.event.KeyEvent)
	 */
	@Override
	public void keyReleased(KeyEvent e) 
	{
		if(e.getKeyCode() == KeyEvent.VK_W)
		{
			// once the up button is released set in paddle that it does not need to move in upward direction.
			paddle1.setUpAccel(false);
		}
		else if(e.getKeyCode() == KeyEvent.VK_S)
		{
			// once the up button is released set in paddle that it does not need to move in downwards direction.
			paddle1.setDownAcel(false);
		}
		else if(e.getKeyCode() == KeyEvent.VK_UP)
		{
			// once the up button is pressed set in paddle that it needs to move in upward direction.
			paddle2.setUpAccel(false);
		}
		else if(e.getKeyCode() == KeyEvent.VK_DOWN)
		{
			// once the up button is pressed set in paddle that it needs to move in downwards direction.
			paddle2.setDownAcel(false);
		}
	}

	/*
	 * (non-Javadoc)
	 * @see java.awt.event.KeyListener#keyTyped(java.awt.event.KeyEvent)
	 */
	@Override
	public void keyTyped(KeyEvent e) {

		// not needed.
	}
}
