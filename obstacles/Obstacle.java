package obstacles;

<<<<<<< HEAD
import game.GameOverException;

public abstract class Obstacle {
	
>>>>>>> 06fef1554299010cedc25a6a830ab0a08052ffaf
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
