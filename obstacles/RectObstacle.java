package obstacles;
import game.GameOverException;
import game.SnakeBoundary;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Point2D.Double;
import java.awt.geom.Rectangle2D;



import java.awt.geom.Ellipse2D;

@Deprecated
/**
 * Not working on this, this time.
 *
 */
public class RectObstacle extends Obstacle{
	private final int FRAMES_PER_SECOND = 50;
	public Rectangle2D rect;
	public SnakeBoundary mySnake;
	double originalDiameter;
	public RectObstacle(int duration, double xloc, double yloc, double d, SnakeBoundary snake){
		rect = new Rectangle2D.Double(xloc, yloc, d, d);
		origTimer = duration;
		timer = 0;
		mySnake = snake;
		originalDiameter = d;
	}

	@Override
	public boolean update() throws GameOverException {
		timer++;
		if (timer >= origTimer * FRAMES_PER_SECOND){
			if (haveCollided())
				throw new GameOverException();
			rect.setFrame(0,0,0,0);
			return false;
		}
		else {
			double alpha = 1 + (timer*1.0 / (origTimer * FRAMES_PER_SECOND));
			double x = rect.getX();
			double y = rect.getY();
			double d = rect.getWidth();
			//double h = rect.getHeight();
			double centerX = x + d / 2;
			double centerY = y + d / 2;
			d = originalDiameter * alpha;
			x = centerX - d / 2;
			y = centerY - d / 2;
			rect.setRect(x, y, d, d);
		}
		return false;
	}

	@Override
	public boolean haveCollided() {
		boolean tempresult = false;

		// get dimensions of snake
		double r1 = mySnake.getInnerRadius();
		double r2 = mySnake.getOuterRadius();
		double cx = mySnake.getPosition().x;
		double cy = mySnake.getPosition().y;

		Ellipse2D e = new Ellipse2D.Double(cx-r1, cy-r1, 2*r1, 2*r1);
		Ellipse2D e2 = new Ellipse2D.Double(cx-r2, cy-r2, 2*r2, 2*r2);
		Rectangle2D s2 = e2.getBounds2D();

		if (e.contains(rect))
			return false;
		if (s2.intersects(rect))
			tempresult = true;
		if (tempresult) {
			 for (int i = 0; i < 360; i += 5)
				{
					double testX = cx + (Math.cos(Math.toRadians(i)) *r2);
					double testY = cy + (Math.sin(Math.toRadians(i)) *r2);
					if (rect.contains(testX, testY))
						return true;
				}
		}

		return false;
	}

	@Override
	public void draw(Graphics g) {
		Graphics2D g2d = (Graphics2D)g;
		g2d.draw(rect);
	}

    @Override
    public boolean wasCaptured()
    {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public Double getTLCoord()
    {
        // TODO Auto-generated method stub
        return null;
    }

}
