package game;

import java.awt.geom.Point2D;
import java.awt.geom.Ellipse2D;

/**
 * Keeps track of snake's size and movement
 *
 */
public class SnakeModel {
	
	// instance variables
	Ellipse2D.Double outerEdge;
	Ellipse2D.Double innerEdge;
	
	// constants
	// TODO confirm snake radius
	/**
	 * The distance from the centre of the snake to the outer edge
	 */
	private final int DEFAULT_SNAKE_RADIUS = 250;
	
	// TODO this should be the actual snake width
	private final int SNAKE_WIDTH = 30;
	
	// constructor
	SnakeModel()
	{
		final int DEFAULT_X_POS = 0;
		final int DEFAULT_Y_POS = 0;
		outerEdge = new Ellipse2D.Double(
					DEFAULT_X_POS, 
					DEFAULT_Y_POS,
					DEFAULT_SNAKE_RADIUS,
					DEFAULT_SNAKE_RADIUS);
		double innerRadius = DEFAULT_SNAKE_RADIUS - SNAKE_WIDTH;
		innerEdge = new Ellipse2D.Double(
					DEFAULT_X_POS + SNAKE_WIDTH,
					DEFAULT_Y_POS + SNAKE_WIDTH,
					innerRadius,
					innerRadius);
	}
	
	/**
	 * Increases the radius of the snake
	 * @param radius
	 */
	public void grow(double radius)
	{
		this.outerEdge.width += radius;
		this.outerEdge.height += radius;
	}
	
	/**
	 * Decreases the radius of the snake
	 * @param radius
	 */
	public void shrink(double radius)
	{
		this.outerEdge.width -= radius;
		this.outerEdge.height -= radius;
	}
	
	/**
	 * Gets the upper left hand corner of the snake
	 * @return
	 */
	public Point2D.Double getOrigin()
	{
		return new Point2D.Double(outerEdge.x, outerEdge.y);
	}
	
	/**
	 * Sets the position of the upper left hand corner
	 * of the snake
	 * @param origin
	 */
	public void setOrigin(Point2D.Double origin)
	{
		double outerRadius = outerEdge.height;
		outerEdge.setFrame(origin.x, origin.y, outerRadius, outerRadius);
		double innerRadius = innerEdge.height;
		innerEdge.setFrame(	origin.x + SNAKE_WIDTH,
							origin.y + SNAKE_WIDTH,
							innerRadius,
							innerRadius);
		
	}
	
	/**
	 * Returns the radius of the snake
	 * @return
	 */
	public double getRadius()
	{
		return outerEdge.height;
	}
	
	/**
	 * Moves the origin of the snake to the new point
	 * @param point
	 */
	public void moveTo(Point2D.Double point)
	{
		double radius = outerEdge.getHeight();
		outerEdge.setFrame(point.x, point.y, radius, radius);
	}
	
	
	
	
}
