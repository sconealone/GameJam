package obstacles;

import game.GameOverException;
import game.SnakeBoundary;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;


public class CircleObstacle extends Obstacle{
	private final int FRAMES_PER_SECOND = 4;
	private float originalDiameter;
	
	SnakeBoundary mySnake;

	public Ellipse2D.Float circle;
	public CircleObstacle(int duration, float xloc, float yloc, float d, SnakeBoundary snake){
		circle = new Ellipse2D.Float(xloc, yloc,d,d);
		origTimer = duration;
		timer = 0;
		mySnake = snake;
		originalDiameter = d;
	}
	

	
	@Override
	public void update() throws GameOverException {
		float x, y;
		timer++;
		
		double centerXdouble = circle.getCenterX();
		float centerX = (float) centerXdouble;
		
		double centerYdouble = circle.getCenterY();
		float centerY = (float) centerYdouble;
		
		if (timer >= origTimer * FRAMES_PER_SECOND) {
			if (haveCollided())
				throw new GameOverException();
			circle.setFrame(0,0,0,0);
		}
		else {
			float alpha = 1 + (timer*1.0f / (origTimer * FRAMES_PER_SECOND));
			float d = originalDiameter * alpha;
			x = centerX - (float) d/2;
			y = centerY - (float) d/2;
			circle.setFrame(x, y, d, d);
		}
	}

	@Override
	public boolean haveCollided() {

		// centre of snake
		double cx1 = mySnake.getOrigin().getX();
		double cy1 = mySnake.getOrigin().getY();

		// centre of circle obstacle
		double topx = circle.getX();
		double topy = circle.getY();
		double d = circle.getWidth();
		double x = topx + d / 2;
		double y = topy - d / 2;
		

		// smaller and larger radii of snake
		double r1 = mySnake.getInnerRadius(); 
		double r2 = mySnake.getOuterRadius();


		double distance = Math.sqrt(Math.pow(x-cx1, 2)+Math.pow(y-cy1, 2));
		if(distance >= r1 && distance <= r2)
			return true;
		else if(distance < r1)
			return (distance + d / 2) >= r1;
		else 
			return (distance - d / 2) <= r2;
		
	}

	@Override
	public void draw(Graphics g) {
		Graphics2D g2d = (Graphics2D)g;
		g2d.draw(circle);
	}

}
