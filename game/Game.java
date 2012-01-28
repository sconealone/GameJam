package game;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.*;


public class Game //implements KeyListener
{
	private static int gameTime=0;
	
	public static void main (String [ ] args) throws InterruptedException {
		// TODO are we adding the KeyListener in this class?
		try{
			gameLoop();
			
		}catch(GameOverException e){
			
		}
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
	
	  @Override

	  public void keyPressed(KeyEvent e) {

	    char charPressed = e.getKeyChar();

	    if (charPressed == 'w' || charPressed == 'W')

	    {


	     }

	    

	  }

	

	  @Override
	  public void keyReleased(KeyEvent e) {

	    // do nothing

	  }

	

	  @Override

	  public void keyTyped(KeyEvent e) {

	    // do nothing

	    

	  }

	  

	  public boolean isShrinkKeyPressed()

	  {

	    return shrinkKeyPressed;

	   }

	 

}
