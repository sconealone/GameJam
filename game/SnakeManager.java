package game;

import java.awt.Graphics;
import java.awt.geom.Point2D;

public class SnakeManager extends Snake
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
    
    
    @Override
    /**
     * Returns the position, from the centre of the snake
     */
    public Point2D.Double getPosition()
    {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    /**
     * Assumes the position is for the centre of the snake
     */
    public void setPosition(Point2D.Double p)
    {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void moveBy(Point2D.Double p)
    {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void moveTo(Point2D.Double p)
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
