package game;

import java.util.*;

import obstacles.CircleObstacle;
import obstacles.Obstacle;
import obstacles.RectObstacle;




public class Wall {
	private int maxNumObstacles;
	private int maxDiffObstacles = 2;
	private ArrayList<Obstacle> obstacles;
	/**
	 * 
	 * @param n the max amount of obstacles on the screen at any given time
	 */
	public Wall(int n){
		obstacles = new ArrayList<Obstacle>();
		maxNumObstacles = n;
	}
	/**
	 * Create a random obstacle at a random location on the screen
	 * 
	 */
	public void createObstacle(){
		int num = 0;
		int dur = 5;
		double x= 50, y= 50 , r = 20;
		num =  (((int )Math.random()*100)%maxDiffObstacles);
		switch(num){
		case 0: 	obstacles.add(new CircleObstacle(dur, x, y , r));		break;
		case 1: 	obstacles.add(new RectObstacle());			break;
		}
		
	}

}
