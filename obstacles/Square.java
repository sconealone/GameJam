package obstacles;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;

import javax.swing.*;


public class Square extends JPanel{
//	private int squareWidth;
//	private int panelWidth;
//	private int panelHeight;
//	private int centerx, centery;
//	private int radius;
	private int panelWidth;
	private int panelHeight;
	private int x = 50;
	private int y = 50;
	private int center_x;
	private int center_y;
	
	public Square(){
		this.setSize(x,y);
		panelWidth = this.getWidth();
		panelHeight = this.getHeight();
		center_x = panelWidth/2;
		center_y = panelHeight/2;
	
	}
	
	public Random randomSpawn(){
		Random ran = new Random();
		return ran;
	}
	

	
	
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		g.setColor(Color.green);
		g.fillRect(x, y, 5,5 );
	}



	

}
