package game;

import java.awt.Point;

public interface Snake
{
    /**
     * Gets the coordinate of the centre of the snake
     * @return
     */
    Point getPosition();
    
    /**
     * Sets the position for the centre of the snake
     * @param p
     */
    void setPosition(Point p);
    
    /**
     * Moves the snake by p.x and p.y
     * @param p
     */
    void moveBy(Point p);
    
    /**
     * Moves the snake to position p, 
     * using the centre of the snake
     * @param p
     */
    void moveTo(Point p);
    
    /**
     * Makes the snake grow a stage
     */
    void grow();
    
    /**
     * Makes the snake shrink by a stage
     */
    void shrink();
}
