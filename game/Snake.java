package game;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.geom.Point2D;

public abstract class Snake
{

    /**
     * The position of the snake, calculated from its centre.
     */
    protected Point2D.Double pos;
    
    /**
     * Converts coordinates to read from the top left, to have the origin at the centre
     * @param topLeftCoord
     * @return
     */
    public static Point2D.Double toCentreCoord(Point2D.Double topLeftCoord, Dimension d)
    {
        return new Point2D.Double(topLeftCoord.x + d.width/2, topLeftCoord.y + d.height/2);
    }
    
    /**
     * Converts coordinates to read from the centre, to have the origin at the top left
     * @param centreCoord
     * @return
     */
    public static Point2D.Double toTLCoord(Point2D.Double centreCoord, Dimension d)
    {
        return new Point2D.Double(centreCoord.x - d.width/2, centreCoord.y - d.height/2);
    }

    /**
     * Gets the coordinate of the centre of the snake
     * @return
     */
    public abstract Point2D.Double getPosition();
    
    
    /**
     * Moves the snake by p.x and p.y
     * @param p
     */
    public abstract void moveBy(Point2D.Double p);
    
    /**
     * Moves the snake to position p, 
     * using the centre of the snake
     * @param p
     */
    public abstract void moveTo(Point2D.Double p);
    
    /**
     * Makes the snake grow a stage
     */
    public abstract void grow();
    
    /**
     * Makes the snake shrink by a stage
     */
    public abstract void shrink();
    
    public abstract void draw(Graphics g);
    
    public abstract int getStage();
}
