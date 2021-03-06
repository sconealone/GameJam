package obstacles;

import java.awt.Graphics;
import java.awt.geom.Point2D;

import game.GameOverException;

public abstract class Obstacle {
	
	/**
	 * 
	 */
	public abstract boolean update() throws GameOverException;
	/**
	 * 
	 */
	public abstract  void draw(Graphics g);
	/**
	 * 
	 */
	public abstract boolean haveCollided();
	
	/**
	 * True if the obstacle was captured
	 * @return
	 */
	public abstract boolean wasCaptured();
	
	protected int timer;
	protected int origTimer;
	
	public abstract Point2D.Double getTLCoord();
	
	

}
