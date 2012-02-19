package game;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;

public class SnakeManager implements Snake
{
    // instance fields
    SnakeBoundary bounds;
    SnakeSpriteManager sprite;
    
    private static boolean isDebugModeOn = true;
    
    
    
    public SnakeManager()
    {
        bounds = new SnakeBoundary();
        sprite = new SnakeSpriteManager();
    }
    
    
    public void draw(Graphics g)
    {
        sprite.spin();
        sprite.draw(g);
        if (isDebugModeOn)
        {
            bounds.draw(g);
        }
    }
    
    /**
     * Converts coordinates to read from the top left, to have the origin at the centre
     * @param topLeftCoord
     * @return
     */
    public static Point toCentreCoord(Point topLeftCoord, Dimension d)
    {
        return new Point(topLeftCoord.x + d.width/2, topLeftCoord.y + d.height/2);
    }
    
    /**
     * Converts coordinates to read from the centre, to have the origin at the top left
     * @param centreCoord
     * @return
     */
    public static Point toTopLeftCoord(Point centreCoord, Dimension d)
    {
        return new Point(centreCoord.x - d.width/2, centreCoord.y - d.height/2);
    }
    
    @Override
    /**
     * Returns the position, from the centre of the snake
     */
    public Point getPosition()
    {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    /**
     * Assumes the position is for the centre of the snake
     */
    public void setPosition(Point p)
    {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void moveBy(Point p)
    {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void moveTo(Point p)
    {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void grow()
    {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void shrink()
    {
        // TODO Auto-generated method stub
        
    }

}
