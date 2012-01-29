package obstacles;

import java.awt.Graphics;

import game.GameOverException;

public abstract class Obstacle {
	
	/**
	 * 
	 */
	public abstract void update() throws GameOverException;
	/**
	 * 
	 */
	public abstract  void draw(Graphics g);
	/**
	 * 
	 */
	public abstract boolean haveCollided();
	
	protected int timer;
	protected int origTimer;

}
