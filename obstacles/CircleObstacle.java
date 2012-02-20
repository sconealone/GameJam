package obstacles;

import game.GameOverException;
import game.SnakeBoundary;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Point2D;
import java.io.File;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;


public class CircleObstacle extends Obstacle{

	private final int FRAMES_PER_SECOND = 40; // original value = 50
	//private final int FRAMES_PER_SECOND = 2; // debug
	private float originalDiameter;

	Image circleImage;
	Image explosion;
	
	SnakeBoundary mySnake;

	public Ellipse2D.Float circle = new Ellipse2D.Float();
	
	public CircleObstacle(int duration, float xloc, float yloc, float d, SnakeBoundary snake){
		circle = new Ellipse2D.Float(xloc, yloc, d, d);
		origTimer = duration;
		timer = 0;
		mySnake = snake;
		originalDiameter = d;
		try {
			circleImage = ImageIO.read(new File("src" + File.separatorChar + "resources" + File.separatorChar + "Circle_deep_outerGlow.png"));
			explosion = ImageIO.read(new File("src" + File.separatorChar + "resources" + File.separatorChar + "explosion.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


	///////////////////////////////////////////////////////
	// NOTE: this now returns bool in order to communicate
	//		with Game Dialog (i.e. to notify the wall that
	//		it has passed the snake and should be removed
	//		from collection of obstacles
	///////////////////////////////////////////////////////
	@Override
	public boolean update() throws GameOverException {
		float x, y;
		timer++;

		double centerXdouble = circle.getCenterX();
		float centerX = (float) centerXdouble;

		double centerYdouble = circle.getCenterY();
		float centerY = (float) centerYdouble;

		if (timer >= origTimer * FRAMES_PER_SECOND) {
			return false;
		}
		else
		{
			float alpha = 1 + (timer * 1.0f / (origTimer * FRAMES_PER_SECOND));
			float d = originalDiameter * alpha;
			x = centerX - (float) d / 2;
			y = centerY - (float) d / 2;
			circle.setFrame(x, y, d, d);
		}
		return true;
	}

	@Override
	public boolean haveCollided() {
		// centre of snake
		double snakeX = mySnake.getPosition().x;
		double snakeY = mySnake.getPosition().y;

		/// centre of circle obstacle
		double d = circle.getWidth();

		double circleX = circle.getCenterX();
		double circleY = circle.getCenterY();
		double r1 = mySnake.getInnerRadius();
		double r2 = mySnake.getOuterRadius();
		// smaller and larger radii of snake

		double distance = Math.sqrt( Math.pow( circleX - snakeX, 2 ) + Math.pow( circleY - snakeY, 2 ) );
		if(distance >= r1 && distance <= r2)
			return true;
		else if(distance < r1)
			return (distance + d / 2) >= r1;
		else 
			return (distance - d / 2) <= r2;
	}
	
	
	@Override
	/**
	 * Checks if the distance from the centre of obstacle + its radius is 
	 * less than the inner radius of the snake
	 */
	public boolean wasCaptured()
	{
	    double sx = mySnake.getPosition().x;
	    double sy = mySnake.getPosition().y;
	    double cx = circle.getCenterX();
	    double cy = circle.getCenterY();
	    double cr = circle.getHeight()/2;
	    double deltaX = sx - cx;
	    double deltaY = sy - cy;
	    double distance = Math.sqrt(deltaX*deltaX + deltaY*deltaY); // pythagoras
	    return distance - cr < mySnake.getInnerRadius();
	}

	@Override
	public void draw(Graphics g) {
		Graphics2D g2d = (Graphics2D)g;
		Color color = g2d.getColor();
		g2d.setColor(Color.red);
		int radius = (int) circle.width/2;
		g2d.drawImage(circleImage, (int)circle.getCenterX() - radius, (int)circle.getCenterY() - radius, 
				radius+radius, radius+radius, null);
		//g.drawOval((int)circle.x, (int)circle.y, (int)circle.width, (int)circle.height); // debug
	}
	
	public void explode(Graphics g) {
	    Graphics2D g2 = (Graphics2D) g;
	    circleImage = explosion;
	    draw(g);
	}
	
	@Override
	public Point2D.Double getTLCoord()
	{
	    return new Point2D.Double(circle.getX(), circle.getY());
	}
}
