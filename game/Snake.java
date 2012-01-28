package game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.*;

public class Snake extends JPanel implements KeyListener{
	private final int snakeWidth = 20;
	private int panelWidth;
	private int panelHeight;
	private int centerx, centery;
	private int radius;
	
	
	public Snake(int x , int y){
		this.setSize(x, y);
		panelWidth = this.getWidth();
		panelHeight = this.getHeight();
		centerx = panelWidth/2;
		centery = panelHeight/2;
		radius = Math.min(centerx, centery);
		this.addKeyListener(this);
		this.setFocusable(true);
		
	}
	
	
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		g.setColor(Color.blue);
		g.fillOval(centerx-radius, centery-radius, 2*radius, 2*radius);
		g.setColor(Color.lightGray);
		g.fillOval(centerx-radius+snakeWidth, centery-radius+snakeWidth, 2*(radius-snakeWidth), 2*(radius-snakeWidth));
	}


	@Override
	public void keyPressed(KeyEvent e) {
		//radius = radius - 10;
		if (e.getKeyChar() == 'a'){
			radius = radius -10;
		}
		if (e.getKeyChar() == 's'){
			radius = radius +10;
		}
		if (radius < 0 ) radius = 0;
		
		repaint();
	}


	@Override
	public void keyReleased(KeyEvent e) {
		
	}


	@Override
	public void keyTyped(KeyEvent e) {
		
		
	}

	
	
	
	

	
	
	
	

}
