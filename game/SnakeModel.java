package game;

import java.awt.geom.Point2D;
import java.awt.geom.Point2D.Double;
import java.awt.geom.Ellipse2D;

/**
 * Keeps track of snake's size and movement
 *
 */
public class SnakeModel {
	
	// instance variables
	Ellipse2D.Double outerEdge;
	
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
	 * Returns the radius of the snake
	 * @return
	 */
	public double getRadius()
	{
		return 0;
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
