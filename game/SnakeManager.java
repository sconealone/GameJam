package game;

import java.awt.Graphics;
import java.awt.geom.Point2D;

public class SnakeManager extends Snake
{
    // instance fields
    SnakeBoundary bounds;
    SnakeSpriteManager sprite;
    
    private static boolean isDebugModeOn = false;
    
    
    
    public SnakeManager()
    {
        bounds = new SnakeBoundary();
        sprite = new SnakeSpriteManager();
        int screenWidth, screenHeight;
        screenWidth = screenHeight = 600;
        moveTo(new Point2D.Double(screenWidth/2, screenHeight/2));
    }
    
    
    public void draw(Graphics g)
    {
        if (isDebugModeOn)
        {
            bounds.draw(g);
        }
        else
        {
            sprite.spin();
            sprite.draw(g);
        }
    }
    
    
    @Override
    /**
     * Returns the position, from the centre of the snake
     */
    public Point2D.Double getPosition()
    {
        return pos;
    }


    @Override
    public void moveBy(Point2D.Double p)
    {
        moveTo(new Point2D.Double(pos.x + p.x, pos.y + p.y));        
    }

    @Override
    public void moveTo(Point2D.Double p)
    {
        pos = new Point2D.Double(p.x, p.y);
        bounds.moveTo(pos);
        sprite.moveTo(pos);
    }

    @Override
    public void grow()
    {
        bounds.grow();
        sprite.grow();
    }

    @Override
    public void shrink()
    {
        bounds.shrink();
        sprite.shrink();
    }
    
    public SnakeBoundary getBoundary()
    {
        return bounds;
    }
    
    public int getStage()
    {
        return bounds.getStage();
    }


}
