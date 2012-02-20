package game;

import java.io.File;
import java.io.IOException;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.geom.AffineTransform;
import java.awt.geom.Dimension2D;
import java.awt.geom.Point2D;
import java.awt.geom.Point2D.Double;

import javax.imageio.ImageIO;

public class SnakeSpriteManager extends Snake {
	
	// instance variables
	
	private String[] snakeNames;
	private Image[] snakeImages;
	private int stageCounter = 3;
	
	
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
	    pos = new Point2D.Double(0,0);
	    
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
	
	@Override
	public void shrink()
	{
		if (stageCounter < NUM_GROWTH_STAGES - 1)
		{
			stageCounter++;
		}
	}
	
	@Override
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

	@Override
	public void draw(Graphics g) {
		Graphics2D g2d = (Graphics2D)g;
		Image img = snakeImages[stageCounter];
		
		AffineTransform at = new AffineTransform();
		//at.translate(pos.x+img.getWidth(null)/2, pos.y+img.getHeight(null)/2);

        at.translate(pos.x, pos.y);
		at.rotate(angle);
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g2d.setTransform(at);
		g2d.drawImage(img, -img.getWidth(null)/2, -img.getHeight(null)/2, img.getWidth(null), img.getHeight(null), null);
		
		Dimension d = new Dimension(img.getWidth(null), img.getHeight(null));
		Point2D.Double tlcoord = toTLCoord(pos, d);
		//g2d.drawImage(img, (int)tlcoord.x, (int)tlcoord.y, d.width, d.height, null);
		outerRadius = img.getWidth(null)/2 - 50;
		innerRadius = img.getWidth(null)/2;
	}
	
	

    @Override
    public Point2D.Double getPosition()
    {
        return pos;
    }

    @Override
    public void moveBy(Point2D.Double p)
    {
        pos.setLocation(pos.x + p.x, pos.y + p.y);
        
    }

    @Override
    public void moveTo(Point2D.Double p)
    {
        pos.setLocation(p);
        
    }
    
    @Override
    public int getStage()
    {
        return stageCounter;
    }
}
