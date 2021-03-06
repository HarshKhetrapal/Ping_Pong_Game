package PongGame;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class HighScoreHelper {

	private static Integer highScore;
	private static String storingPath = "C:\\nexus\\GameProject\\highScore.ser";
	
	/**
	 * Get the persisted value of high score.
	 */
	public static void initHighScore()
	{
		// get the high score persisted value, when application starts.
		try (FileInputStream input = new FileInputStream(HighScoreHelper.storingPath);
			ObjectInputStream objectInput = new ObjectInputStream(input);) 
		{
			
			HighScoreHelper.highScore = (Integer) objectInput.readObject();
		} 
		catch (ClassNotFoundException | IOException e1) {
			HighScoreHelper.highScore = Integer.valueOf(0);
		}
	}
	
	/**
	 * Persist the new high score value.
	 */
	public static void writeNewHighScore()
	{
		// if new high score then persist this value.
		try (FileOutputStream fout = new FileOutputStream(HighScoreHelper.storingPath);
			ObjectOutputStream oos = new ObjectOutputStream(fout);)
		{
			oos.writeObject(HighScoreHelper.highScore);
		} 
		catch (IOException e1) {
			
			e1.printStackTrace();
		}
	}
	
	/**
	 * check if the high score is broken or not
	 * @param leftPaddleScore score of left paddle.
	 * @param rightPaddleScore score of right paddle.
	 * @return
	 */
	public static boolean isHighScoreBroke(int leftPaddleScore, int rightPaddleScore) 
	{
		// check is the high score is broken or not.
		if(leftPaddleScore > rightPaddleScore && leftPaddleScore > HighScoreHelper.highScore)
		{
			// High score is broken by left paddle.
			
			// Assign new high score.
			HighScoreHelper.highScore = leftPaddleScore;
			HighScoreHelper.writeNewHighScore();
			return true;
		}
		else if(leftPaddleScore < rightPaddleScore && rightPaddleScore > HighScoreHelper.highScore)
		{
			// High score is broken by right player.
			
			// Assign new high score.
			HighScoreHelper.highScore = rightPaddleScore;
			HighScoreHelper.writeNewHighScore();
			return true;
		}
		// High score not broken.
		return false;
	}
}
