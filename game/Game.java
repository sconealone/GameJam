package game;

import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.*;


public class Game {
	private static int gameTime=0;
	
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
