package obstacles;

import game.GameOverException;
import java.awt.geom.Ellipse2D;


public class CircleObstacle extends Obstacle{
	private final int FRAMES_PER_SECOND = 4;
	public CircleObstacle(int duration, double xloc, double yloc, double r){
		circle = new Ellipse2D.Double(xloc, yloc, r, r);
		origTimer = duration;
		timer = 0;
	}
	
	public Ellipse2D.Double circle;
	
	@Override
	public void update() throws GameOverException {
		timer++;
		if (timer == origTimer * FRAMES_PER_SECOND){
			if (haveCollided())
				throw new GameOverException();
			circle.setFrame(0,0,0,0);
		}
		else {
			double alpha = 1 + (timer / origTimer * FRAMES_PER_SECOND);
			double x = circle.getX();
			double y = circle.getY();
			double r = circle.getWidth();
			double centerX = x + r / 2;
			double centerY = y - r / 2;
			r = r * alpha;
			x = centerX - r;
			y = centerY + r;
			circle.setFrame(x, y, r, r);
		}
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

	@Override
	public void draw(Graphics2D g) {
		// TODO Auto-generated method stub
		
	}

}
