package game;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;

import javax.swing.*;

public class Game extends JFrame {
	
	private static GameDialog mainDlg = new GameDialog();
	
//	private static int gameTime = 0;
//	private static int score = 0;
//	
//	JFrame parent = new JFrame("Snaketris");
//	JPanel gamePanel = new JPanel();
	
	// true if an obstacle is captured, false otherwise
//	private boolean hasCaughtInside = false;
	
	public static void main (String [] args) throws InterruptedException, IOException {
		// TODO are we adding the KeyListener in this class?
//		try{
			mainDlg.Init();
			
			//mainDlg.update();
//		}catch(GameOverException e){}
		
	}	
		
}
