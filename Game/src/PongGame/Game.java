package PongGame;

import javax.swing.JFrame;

public class Game {

	public static void main(String[] args) {
		Pong pong = new Pong();
		JFrame frame = new JFrame("Pong Game");
		// add applet in a frame.
		frame.add(pong);
		// set size of the frame.
		frame.resize(700, 530);
		frame.setResizable(false);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// call the initial method to start the applet.
		pong.init();

	}

}
