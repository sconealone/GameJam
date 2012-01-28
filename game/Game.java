package game;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.*;


public class Game {
	private static int gameTime=0;
	private static int score;
	
	// just a variable to check if the obstacle is
	//inside the snake, change this when you get ur collision methods working
	private boolean hasCaughtInside = false;
	
	public static void main (String [ ] args) throws InterruptedException {
		// TODO are we adding the KeyListener in this class?
		try{
			gameLoop();
			
		}catch(GameOverException e){
			
		}
		
	}
	
	
	
	public int getScore(int start, int end) {
		//actual time elapsed in the game
		if( hasCaughtInside == true) {
			score +=10;
		}
		int actual_time = gameTime*4;
		score += actual_time*10;
		return score;
	}
	
	public static void gameLoop() throws InterruptedException{
		while(true){
			// check for key presses
	
			
	
			 // update snake
	
			
			// check for captures
	
			  
	
			// check for collisions (game over)
	
		     
	
			// repaint
			gameTime++;
			Thread.sleep(250);
		}
	}
	
}
