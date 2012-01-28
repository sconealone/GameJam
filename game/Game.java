package game;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.*;


public class Game extends JFrame implements KeyListener
{
	// models
	SnakeModel snakeModel;
	
	// game commands
	/**
	 * This section will hold whether or not a key was pressed.
	 * Each key is reset to not pressed when 
	 */
	private boolean shrinkKeyPressed;
	private boolean moveLeftKeyPressed;
	private boolean moveRightKeyPressed;
	private boolean moveUpKeyPressed;
	private boolean moveDownKeyPressed;
	
	private static boolean isGameOver = false;
	public Game(){
		Wall wall = new Wall();
		Snake snake = new Snake(300,300);
		final int WINDOW_X = 1024;
		final int WINDOW_Y = 768;
		setSize(WINDOW_X, WINDOW_Y);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		addKeyListener(this);
		setFocusable(true);
		getContentPane().add(wall);
		getContentPane().add(snake);
	}
	
	public static void main (String [ ] args) throws InterruptedException{
		Game frame = new Game();
		int k = 0;
		while (!isGameOver){
			if (k == 100) isGameOver = true;
			
			// check for key presses
			
			// update snake
			
			// check for captures
			
			// check for collisions (game over)
			
			// repaint
			
			frame.repaint();
			Thread.sleep(250); // seconds / frame
		}
	}
	
	@Override
	public void keyPressed(KeyEvent e) {
		char charPressed = e.getKeyChar();
		if (charPressed == 'w' || charPressed == 'W')
		{
				shrinkKeyPressed = true;
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
