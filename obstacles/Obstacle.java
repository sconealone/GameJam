package obstacles;

import game.Game;
import game.Wall;

import java.awt.Graphics2D;

public abstract class Obstacle {
	
	public Obstacle(int eventualX, int eventualY, int speed, Wall wall) {
		
	}
	
	/**
	 * 
	 */
	public abstract void update();
	/**
	 * 
	 */
	public abstract  void draw(Graphics2D g);
	/**
	 * 
	 */
	public abstract boolean haveCollided();

	public void draw() {
		// TODO Auto-generated method stub
		
	}

}
