package game;

import java.util.*;

import obstacles.CircleObstacle;
import obstacles.Obstacle;
import obstacles.RectObstacle;




public class Wall {
	private int maxNumObstacles;
	private int maxDiffObstacles = 2;
	private ArrayList<Obstacle> obstacles;
	private SnakeBoundary snake;
	private int size;


	/**
	 * 
	 * @param n the max amount of obstacles on the screen at any given time
	 */
	public Wall(int n, SnakeBoundary snake){
		obstacles = new ArrayList<Obstacle>();
		maxNumObstacles = n;
		this.snake = snake;
	}
	
	/**
	 * Create a random obstacle at a random location on the screen
	 * 
	 */
	public void createObstacle(){
		Random r = new 	Random();
		//int num = r.nextInt(2);
		int num = 0;
		int dur = 5 +  (int) (r.nextFloat() * 5);
		float x =100 + r.nextFloat() * 400;
		float y = 100 + r.nextFloat() * 400;
		float d = 70;
		
		//num =  (((int )Math.random()*100)%maxDiffObstacles);
		if (obstacles.size() < maxNumObstacles)
		{
    		switch(num){
            		case 0: 	obstacles.add(new CircleObstacle(dur, x, y , d, snake));		break;
            		case 1: 	obstacles.add(new RectObstacle(dur, x, y, d, snake));			break;
    		}
    		size++;
		}

	}
	
	/**
	 * Delete an obstacle (after it passes the snake without collision)
	 *
	 */
	public void deleteObstacle( Obstacle o){
		obstacles.remove(o);
		size--;
	}
	
	public ArrayList<Obstacle> getObstacles(){
		return obstacles;
	}
	
	public int getSize(){
	    return size;
	}

}
