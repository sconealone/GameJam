package game;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

import javax.swing.*;

import obstacles.Obstacle;


public class Game{
	private static int gameTime=0;
	private static int score;
	
	// just a variable to check if the obstacle is
	//inside the snake, change this when you get ur collision methods working
	private boolean hasCaughtInside = false;
	
	public void initGame(){
		JFrame frame = new JFrame();
		
	}
	
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
			
			// check for captures
	
			// check for collisions (game over)
			
			// repaint
			
			
			
			
			
			
			// update wall
			
			ArrayList<Obstacle> obstacles = wall.getObstacles();
			for(int i=0; i< obstacles.size(); i++){
				obstacles.get(i).update();
			}
			
			// move snake according to key pressed
			
			if(upKeyPressed){
				snake.moveUp();
			}
			if(downKeyPressed){
				snake.moveDown();
			}
			if(rightKeyPressed){
				snake.moveRight();
			}
			if(leftKeyPressed){
				snake.moveLeft();
			}
			
			// reset key pressed
			upKeyPressed = false;
			downKeyPressed = false;
			rightKeyPressed = false;
			leftKeyPressed = false;
			
			// autogrow snake
			gameTime++;
			Thread.sleep(250);
		}
	}
	
}
