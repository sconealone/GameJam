package obstacles;

import game.Wall;


public class CircleObstacle extends Obstacle{

	public CircleObstacle(int eventualX, int eventualY, int speed, Wall wall) {
		super(eventualX, eventualY, speed, wall);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void draw() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean haveCollided() {
		 int snakeThickness;

		// get two centres somehow

		// centre of snake
		int cx1, cy1;

		// centre of circle obstacle
		int x, y;

		// smaller and larger radii of snake
		int r1, r2;

		// radius of obstacle
		int rObstacle;

		double distance = Math.sqrt(Math.pow(cx3-cx1, 2)+Math.pow(cy3-cy1, 2));
		if(distance >= r1 && distance <= r2)
			return true;
		if(distance < r1)
			return (distance+rObstacle) < r1;
		if(distance > r2)
			return (distance-rObstacle) > r2;
	}

}
