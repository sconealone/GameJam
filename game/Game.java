package game;

import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.*;


<<<<<<< HEAD
public class Game extends JFrame{
	private static boolean isGameOver = false;
	public Game(){
		Wall wall = new Wall(20);
		JPanel snake = new Snake(300,300);
		setSize(300,300);
		getContentPane().add(wall);
		getContentPane().add(snake);
	}
=======
public class Game {
	private static int gameTime=0;
>>>>>>> 06fef1554299010cedc25a6a830ab0a08052ffaf
	
	public static void main (String [ ] args) throws InterruptedException {
		try{
			gameLoop();
		}catch(GameOverException e){
			
		}
	}
	public static void gameLoop() throws InterruptedException{
		while(true){
			gameTime++;
			Thread.sleep(250);
		}
	}

}
