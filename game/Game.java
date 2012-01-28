package game;

import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.*;


public class Game extends JFrame{
	private static boolean isGameOver = false;
	public Game(){
		JPanel wall = new Wall(20);
		JPanel snake = new Snake(300,300);
		setSize(300,300);
		getContentPane().add(wall);
		getContentPane().add(snake);
	}
	
	public static void main (String [ ] args) throws InterruptedException{
		JFrame frame = new Game();
		int k = 0;
		frame.pack();
		frame.show();
		while (!isGameOver){
			if (k == 100) isGameOver = true;
			frame.repaint();
			Thread.sleep(250);
		}
	}

}
