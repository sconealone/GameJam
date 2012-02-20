
package game;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Point2D;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Point2D.Double;

/**
 * Keeps track of snake's size and movement
 * The old SnakeModel that used two ellipses
 * Used now so we can still test CircleObstacle
 * and GameProcessing
 *
 */
public class SnakeBoundary extends Snake {
	
	// instance variables
	Ellipse2D.Double outerEdge;
	Ellipse2D.Double innerEdge;
	
	
	// constants
	
	
	private final int INNER = 0;
	private final int OUTER = 1;
	
	private Dimension[][] stageSizes;
	
	// synced with SnakeSpriteManager stage counter
	private int stageCounter;
	private final int NUM_STAGES = 6;
	
	// constructor
	SnakeBoundary()
	{
	    stageCounter = 3;
		
		stageSizes = new Dimension[NUM_STAGES][];
		for (int i = 0; i < NUM_STAGES; i++)
		{
			stageSizes[i] = new Dimension[2];
		}
		stageSizes[0][INNER] = new Dimension(370, 370);
		stageSizes[0][OUTER] = new Dimension(475, 475);
		stageSizes[1][INNER] = new Dimension(253, 253);
		stageSizes[1][OUTER] = new Dimension(363, 363);
		stageSizes[2][INNER] = new Dimension(235,235);
		stageSizes[2][OUTER] = new Dimension(339,339);
		stageSizes[3][INNER] = new Dimension(163,163);
		stageSizes[3][OUTER] = new Dimension(280,280);
		stageSizes[4][INNER] = new Dimension(100,100);
		stageSizes[4][OUTER] = new Dimension(210,210);
		stageSizes[5][INNER] = new Dimension(100,100);
		stageSizes[5][OUTER] = new Dimension(210,210);
		
		// batch correction of radii
		int correctionAmount = 35;
		for (int i = 0; i < NUM_STAGES; i++)
		{
		    for (int j = INNER; j <= OUTER; j++)
		    {
		        int d = (j == INNER) ? stageSizes[i][j].width + correctionAmount : stageSizes[i][j].width - correctionAmount;
		        stageSizes[i][j] = new Dimension(d, d);
		    }
		}
		
		final int DEFAULT_X_POS = 300;
		final int DEFAULT_Y_POS = 300;
		pos = new Point2D.Double(DEFAULT_X_POS, DEFAULT_Y_POS);
		double diameter = stageSizes[stageCounter][OUTER].width;
		outerEdge = new Ellipse2D.Double(0, 0,diameter,	diameter);
		double innerDiameter = stageSizes[stageCounter][INNER].width;
		innerEdge = new Ellipse2D.Double(0,	0,innerDiameter,innerDiameter);
	}
	
	/**
	 * Getter method for 2D array of snake image properties
	 * 
	 */
	public Dimension[][] getDimArray() {
		return stageSizes;
	}
	
	/**
	 * Getter method for stage counter
	 * 
	 */
	public int getCounter() {
		return stageCounter;
	}
	
	/**
	 * Increases the radius of the snake
	 */
	@Override
	public void grow()
	{
		if (stageCounter > 0 && stageCounter <= 5)
		{
			stageCounter--;
		}
		this.outerEdge.width = stageSizes[stageCounter][OUTER].width;
		this.outerEdge.height = stageSizes[stageCounter][OUTER].height;
		this.innerEdge.width = stageSizes[stageCounter][INNER].width;
		this.innerEdge.height = stageSizes[stageCounter][INNER].height;
		
        outerEdge.setFrameFromCenter(pos, toTLCoord(pos, new Dimension((int)outerEdge.getWidth(), (int)outerEdge.getHeight())));
        innerEdge.setFrameFromCenter(pos, toTLCoord(pos, new Dimension((int)innerEdge.getWidth(), (int)innerEdge.getHeight())));
	}
	
	/**
	 * Decreases the radius of the snake
	 */
	@Override
	public void shrink()
	{
		if (stageCounter >= 0 && stageCounter < 5)
		{
			stageCounter++;
		}
		this.outerEdge.width = stageSizes[stageCounter][OUTER].width;
		this.outerEdge.height = stageSizes[stageCounter][OUTER].height;
		this.innerEdge.width = stageSizes[stageCounter][INNER].width;
		this.innerEdge.height = stageSizes[stageCounter][INNER].height;

        outerEdge.setFrameFromCenter(pos, toTLCoord(pos, new Dimension((int)outerEdge.getWidth(), (int)outerEdge.getHeight())));
        innerEdge.setFrameFromCenter(pos, toTLCoord(pos, new Dimension((int)innerEdge.getWidth(), (int)innerEdge.getHeight())));
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
	
	@Override
	public void draw(Graphics g) {
		Graphics2D g2d = (Graphics2D)g;
		g2d.draw(outerEdge);
		g2d.draw(innerEdge);
	}
		

    @Override
    /**
     * Position of the snake's centre
     */
    public Double getPosition()
    {
        return pos;
    }


    @Override
    public void moveBy(Point2D.Double p)
    {
        pos.x += p.x;
        pos.y += p.y;
        outerEdge.setFrameFromCenter(pos, toTLCoord(pos, new Dimension((int)outerEdge.getWidth(), (int)outerEdge.getHeight())));
        innerEdge.setFrameFromCenter(pos, toTLCoord(pos, new Dimension((int)innerEdge.getWidth(), (int)innerEdge.getHeight())));
    }

    @Override
    public void moveTo(Point2D.Double p)
    {
        pos = p;
        outerEdge.setFrameFromCenter(pos, toTLCoord(pos, new Dimension((int)outerEdge.getWidth(), (int)outerEdge.getHeight())));
        innerEdge.setFrameFromCenter(pos, toTLCoord(pos, new Dimension((int)innerEdge.getWidth(), (int)innerEdge.getHeight())));
        
    }
    
    @Override
    public int getStage()
    {
        return stageCounter;
    }

		
}


