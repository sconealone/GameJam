package game;

import java.io.File;
import java.io.IOException;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.geom.AffineTransform;

import javax.imageio.ImageIO;

public class SnakeSpriteManager {
	
	// instance variables
	
	private String[] snakeNames;
	private Image[] snakeImages;
	private int stageCounter = 3;
	
	/**
	 * x and y are the coordinates of the top left corner of the rectangle?
	 */
	private int x, y; 
	
	/**
	 * dx and dy are how much to change the position by
	 */
	private int dx, dy;
	
	/**
	 * r1 and r2 are the radii of the two circles that make up the boundary
	 * of the snake.
	 * r1 is the outer radius and r2 is the inner radius
	 */
	private int outerRadius, innerRadius;
	
	/**
	 * The angle to rotate by?
	 */
	private float angle = 0f;
	
	
	// constants
	
	private final int NUM_GROWTH_STAGES = 6;
	
	public SnakeSpriteManager()
	{

		x = y = 0;
		dx = dy = 5;
		
		// added from largest to smallest
		snakeNames = new String[NUM_GROWTH_STAGES];
		snakeNames[0] = "test0001.png";
		snakeNames[1] = "test0012.png";
		snakeNames[2] = "test0024.png";
		snakeNames[3] = "test0036.png";
		snakeNames[4] = "test0048.png";
		snakeNames[5] = "test0060.png";
		
		snakeImages = new Image[NUM_GROWTH_STAGES];
		try
		{
			String windowsPath  = "src"+File.separatorChar+"resources"+File.separatorChar;
			for (int i = 0; i < NUM_GROWTH_STAGES; i++)
			{
				snakeImages[i] = ImageIO.read(new File(windowsPath+snakeNames[i]));
			}
		}
		catch (IOException e)
		{	
			
				e.printStackTrace();
			
				
		}
		
	}
	
	public void shrink()
	{
		if (stageCounter < NUM_GROWTH_STAGES - 1)
		{
			stageCounter++;
		}
	}
	
	public void grow()
	{
		if (stageCounter > 0)
		{
			stageCounter--;
		}
	}
	
	public String getSpriteName()
	{
		return snakeNames[stageCounter];
	}
	
	public Image getSpriteImage()
	{
		return snakeImages[stageCounter];
	}
	
	
	/**
	 * spin the image
	 */
	public void spin()
	{
		angle -= 0.4f;
		if (angle <= -2*Math.PI)
		{
			angle = 0f;
		}
	}
	
	/**
	 * spin the image backwards
	 */
	public void spinBack()
	{
		angle += 0.2f;
		if (angle <= -2*Math.PI)
		{
			angle = 0f;
		}
	}

	public void draw(Graphics g) {
		Graphics2D g2d = (Graphics2D)g;
		Image img = snakeImages[stageCounter];
		AffineTransform at = new AffineTransform();
		at.translate(x+img.getWidth(null)/2, y+img.getHeight(null)/2);
		//at.scale(CHANGE_DIAMETER_BY, CHANGE_DIAMETER_BY);
		at.rotate(angle);
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g2d.setTransform(at);
		g2d.drawImage(img, -img.getWidth(null)/2, -img.getHeight(null)/2, img.getWidth(null), img.getHeight(null), null);
		outerRadius = img.getWidth(null)/2 - 50;
		innerRadius = img.getWidth(null)/2;
		/*g2d.fillOval(x, y, r2, r2);
		g2d.setColor(Color.RED);
		g2d.fillOval(x + img.getWidth(null)/2, y + img.getHeight(null)/2, r1, r1);*/
	}
	
	
	public void moveUp()
	{
		y += dy;
	}
	
	public void moveDown()
	{
		y -= dy;
	}
	
	public void moveLeft()
	{
		x -= dx;
	}
	
	public void moveRight()
	{
		x+= dy;
	}
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
}
