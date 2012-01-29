package game;

import java.awt.image.BufferedImage;

import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class GameDialog {
	
	public JFrame parent = new JFrame("Snaketris");
	public JPanel gamePanel = new JPanel();
	
	private int gameTime = 0;
	private int score = 0;
	
	// true if an obstacle is captured, false otherwise
	private boolean hasCaughtInside = false;
		
	public GameDialog() {
	}
	
	public void Init() throws IOException {
		parent.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		parent.getContentPane().add(gamePanel);
		parent.setSize(600, 600);
		parent.setVisible(true);
		
		try {
			gameLoop();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void gameLoop() throws InterruptedException, IOException{
			
			while(true){
				// check for key presses
				
		
				// update snake
				BufferedImage img = null;
				try{
					img=ImageIO.read(new File("~/workspace/GameJam/bin/snake.gif"));
				}catch (IOException e){}
				JLabel picLabel = new JLabel(new ImageIcon( img ));
				gamePanel.add( picLabel );
				
				// check for captures
		
				  
		
				// check for collisions (game over)
		
			     
		
				// repaint
				gameTime++;
				Thread.sleep(250);
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


}
