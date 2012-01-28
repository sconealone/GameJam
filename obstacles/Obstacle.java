package obstacles;

import game.GameOverException;

public abstract class Obstacle {
	
	/**
	 * 
	 */
	public abstract void update() throws GameOverException;
	/**
	 * 
	 */
	public abstract  void draw(Graphics2D g);
	/**
	 * 
	 */
	public abstract boolean haveCollided();
	
	public int timer;
	public int origTimer;

	public void draw() {
		// TODO Auto-generated method stub
		
	}

}
