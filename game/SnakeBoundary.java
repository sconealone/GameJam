
package game;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;
import java.awt.geom.Ellipse2D;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

/**
 * Keeps track of snake's size and movement
 * The old SnakeModel that used two ellipses
 * Used now so we can still test CircleObstacle
 * and GameProcessing
 *
 */
public class SnakeBoundary {
	
	// instance variables
	Ellipse2D.Double outerEdge;
	Ellipse2D.Double innerEdge;
	
	private Image img;
	
	// constants
	// TODO confirm snake radius
	/**
	 * The distance from the centre of the snake to the outer edge
	 */
	private final int DEFAULT_SNAKE_RADIUS = 250;
	
	// TODO this should be the actual snake width
	private final int SNAKE_WIDTH = 30;
	
	private final int MAX_SNAKE_RADIUS = 600;
	
	private final int MOVE_BY_AMOUNT = 10; // pixels
	
	private final int CHANGE_DIAMETER_BY = 50; // pixels
	
	// constructor
	SnakeBoundary()
	{
		final int DEFAULT_X_POS = 300;
		final int DEFAULT_Y_POS = 300;
		outerEdge = new Ellipse2D.Double(
					DEFAULT_X_POS, 
					DEFAULT_Y_POS,
					DEFAULT_SNAKE_RADIUS + DEFAULT_SNAKE_RADIUS,
					DEFAULT_SNAKE_RADIUS + DEFAULT_SNAKE_RADIUS);
		double innerRadius = DEFAULT_SNAKE_RADIUS - SNAKE_WIDTH;
		innerEdge = new Ellipse2D.Double(
					DEFAULT_X_POS + SNAKE_WIDTH,
					DEFAULT_Y_POS + SNAKE_WIDTH,
					innerRadius + innerRadius,
					innerRadius + innerRadius);
		/*try {
			img = ImageIO.read(new File("src/resources/snakes2.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
	}
	
	/**
	 * Increases the radius of the snake
	 * @param growRadiusBy
	 */
	public void grow()
	{
		float change = changeDiameterBy(true);
		this.outerEdge.width += change;
		this.outerEdge.height += change;
		this.innerEdge.width += change;
		this.innerEdge.height += change;
	}
	
	/**
	 * Decreases the radius of the snake
	 * @param shrinkRadiusBy
	 */
	public void shrink()
	{
		float change = changeDiameterBy(false);
		if (innerEdge.width - change < 0)
		{
			innerEdge.width = 0;
			innerEdge.height = 0;
			double snakeDiameter = SNAKE_WIDTH + SNAKE_WIDTH;
			outerEdge.width = snakeDiameter;
			outerEdge.height = snakeDiameter;
			return;
		}
		this.innerEdge.width -= change;
		this.innerEdge.height -= change;
		this.outerEdge.width -= change;
		this.outerEdge.height -= change;
	}
	/*
	public void draw(Graphics2D g) {
		AffineTransform at = new AffineTransform();
		g.drawImage(img, at, null);
	}*/
	
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
	public double getOuterRadius()
	{
		return outerEdge.height / 2;
	}
	
	/**
	 * Returns the inner radius of the snake
	 * @return
	 */
	public double getInnerRadius()
	{
		return innerEdge.height / 2;
	}
	
	
	/**
	 * Moves the snake to the up by a certain amount
	 */
	public void moveUp(){
			
			outerEdge.setFrame(	outerEdge.x,
								outerEdge.y+MOVE_BY_AMOUNT,
								outerEdge.width,
								outerEdge.height);
			innerEdge.setFrame(	innerEdge.x,
					innerEdge.y+MOVE_BY_AMOUNT,
					innerEdge.width,
					innerEdge.height);
		
	}
	/**
	 * Moves the snake to the down by a certain amount
	 */
	public void moveDown(){
		
		outerEdge.setFrame(	outerEdge.x,
							outerEdge.y-MOVE_BY_AMOUNT,
							outerEdge.width,
							outerEdge.height);
		innerEdge.setFrame(	innerEdge.x,
				innerEdge.y-MOVE_BY_AMOUNT,
				innerEdge.width,
				innerEdge.height);
	
	}
	/**
	 * Moves the snake to the right by a certain amount
	 */
	public void moveRight(){
		
		outerEdge.setFrame(	outerEdge.x+MOVE_BY_AMOUNT,
							outerEdge.y,
							outerEdge.width,
							outerEdge.height);
		innerEdge.setFrame(	innerEdge.x+MOVE_BY_AMOUNT,
				innerEdge.y,
				innerEdge.width,
				innerEdge.height);
	
	}

	/**
	 * Moves the snake to the left by a certain amount
	 */
	public void moveLeft(){
		
		outerEdge.setFrame(	outerEdge.x-MOVE_BY_AMOUNT,
							outerEdge.y,
							outerEdge.width,
							outerEdge.height);
		innerEdge.setFrame(	innerEdge.x-MOVE_BY_AMOUNT,
				innerEdge.y,
				innerEdge.width,
				innerEdge.height);
	
	}
	
	

	/**
	 * Returns the amount to change the diameter of the
	 * snake by based on the stage and if it is growing or shrinking
	 * @param growing whether or not the snake is growing
	 * @return
	 */
	public float changeDiameterBy(boolean growing)
	{
		// TODO implement
		return 0f;
	}
	
	
	@Override
	public String toString(){
		String self = "Origin of outer circ = (" + outerEdge.x + ", " 
					+ outerEdge.y 
					+ ")\n";
		self +="Inner Origin: = ("+innerEdge.x + ", " + innerEdge.y +
				")\n";
		self += "Outer radius = "+outerEdge.height/2+"\n";
		self += "Inner radius = "+innerEdge.height/2+"\n";
		
		self += "difference between heights = " + (outerEdge.height - innerEdge.height)
				+"\n";
		self += "difference between widths = " + (outerEdge.width - innerEdge.width)
				+"\n";
		self += "Snake width (should be half the above) = " + SNAKE_WIDTH;
				
		return self;
		
	}

	public static void main(String[] args)
	{
		System.out.println("Default snake");
		SnakeModel s1 = new SnakeModel();
		System.out.println(s1);
		System.out.println();
		
		System.out.println("Testing move up");
		s1.moveUp();
		System.out.println(s1);

		System.out.println();
		System.out.println("Testing move down");
		s1.moveDown();
		System.out.println(s1);
		System.out.println();
		System.out.println("Testing move left");
		s1.moveLeft();
		System.out.println(s1);
		System.out.println();
		System.out.println("Testing move right");
		s1.moveRight();
		System.out.println(s1);
		
		System.out.println("Testing grow");
		s1.grow();
		System.out.println(s1);
		
		System.out.println("\nTesting shrink");
		s1.shrink();
		System.out.println(s1);
		System.out.println();
		s1.shrink();
		System.out.println(s1);
		System.out.println("\nTesting that you can't shrink inner circle past 0");
		for (int i = 0; i < 10; i++)
		{
			s1.shrink();
		}
		System.out.println(s1);
			
			
	}
		
}


