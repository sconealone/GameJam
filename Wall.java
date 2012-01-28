import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;


public class Wall extends JPanel{
	private int live = 15;
	public Wall(){
	
	}
	public void paintComponent(Graphics g){
		super.paintComponents(g);
		int sizeScalar = 15-live;
		g.drawOval(150, 150, sizeScalar*10, sizeScalar*10);
		g.setColor(Color.red);
		live --;
	}

}
