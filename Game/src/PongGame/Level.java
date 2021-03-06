package PongGame;

public class Level {
	
	String LevelOne = "LEVEL_ONE";
	String LevelTwo = "LEVEL_TWO";
	String LevelThree = "LEVEL_THREE";
	
	/**
	 * get the LEVEL at which the game is.
	 * @param ball
	 */
	public void getLevel(Ball ball)
	{
		// check the level at which the game is.
		// Initially game is at LEVEL 1.
		if(ball.paddleOneHit == 5 && ball.paddleTwoHit == 5)
		{
			// game is at LEVEL 2.
			this.setLevel(ball, this.LevelTwo);
		}
		else if(ball.paddleOneHit == 10 && ball.paddleTwoHit == 10)
		{
			// game is at LEVEL 3.
			this.setLevel(ball, this.LevelThree);
		}
	}
	
	/**
	 * set the ball speed based on the LEVEL.
	 * @param ball
	 * @param level
	 */
	public void setLevel(Ball ball, String level)
	{
		// set the LEVEL.
		if(level.equals(LevelOne))
		{
			// set the ball speed for LEVEL 1.
			ball.xVel = -2 * ball.getRabdomDirection();
			ball.yVel = 1 * ball.getRabdomDirection();
		}
		if(level.equals(LevelTwo))
		{
			// set the ball speed for LEVEL 2.
			ball.xVel = ball.xVel*1.5;
			ball.yVel = ball.yVel*1.5;
		}
		else if(level.equals(LevelThree))
		{
			// set the ball speed for LEVEL 3.
			ball.xVel = ball.xVel*1.5;
			ball.yVel = ball.yVel*1.5;
		}
	}
	
	/**
	 * set the score based on the level.
	 * @param ball
	 * @param hitByleftPaddle
	 * @param hitByRightPaddle
	 */
	public void setScore(Ball ball, boolean hitByleftPaddle, boolean hitByRightPaddle)
	{
		// give the score based on the Level.
		if(ball.paddleOneHit + ball.paddleTwoHit <=10)
		{
			// game is at LEVEL 1 set the score as 1.
			if(hitByleftPaddle)
			{
				ball.paddleLeftScore.put(ball.paddleOneHit, 1);
			}
			else if(hitByRightPaddle)
			{
				ball.paddleRighttScore.put(ball.paddleTwoHit, 1);
			}
		}
		else if(ball.paddleOneHit + ball.paddleTwoHit <= 20)
		{
			// game is at LEVEL 2 set the score as 5.
			if(hitByleftPaddle)
			{
				ball.paddleLeftScore.put(ball.paddleOneHit, 5);
			}
			else if(hitByRightPaddle)
			{
				ball.paddleRighttScore.put(ball.paddleTwoHit, 5);
			}
		}
		else if(ball.paddleOneHit + ball.paddleTwoHit >20)
		{
			// game is at LEVEL 3 set the score as 10.
			if(hitByleftPaddle)
			{
				ball.paddleLeftScore.put(ball.paddleOneHit, 10);
			}
			else if(hitByRightPaddle)
			{
				ball.paddleRighttScore.put(ball.paddleTwoHit, 10);
			}
		}
	}

}
