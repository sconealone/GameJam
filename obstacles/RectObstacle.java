package obstacles;
import game.GameOverException;
import java.awt.geom.Rectangle2D;



import java.awt.geom.Ellipse2D;


public class RectObstacle extends Obstacle{
	private final int FRAMES_PER_SECOND = 4;
	public Rectangle2D rect;
	public RectObstacle(int duration, double xloc, double yloc, double r){
		rect = new Rectangle2D.Double(xloc, yloc, r, r);
		origTimer = duration;
		timer = 0;
	}

	@Override
	public void update() throws GameOverException {
		timer++;
		if (timer == origTimer * FRAMES_PER_SECOND){
			if (haveCollided())
				throw new GameOverException();
			rect.setFrame(0,0,0,0);
		}
		else {
			double alpha = 1 + (timer / origTimer * FRAMES_PER_SECOND);
			double x = rect.getX();
			double y = rect.getY();
			double r = rect.getWidth();
			//double h = rect.getHeight();
			double centerX = x + r / 2;
			double centerY = y - r / 2;
			r = r * alpha;
			x = centerX - r;
			y = centerY + r;
			rect.setRect(x, y, r, r);
		}
	}

	@Override
	public void draw() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean haveCollided() {
		// get dimensions of rectangle
		int rx, ry, width, height;

		// get dimensions of snake
		int cx, cy, r1;
		int r2 = r1 + snakeThickness;

		Ellipse2D e = new Ellipse2D.Double(cx-r1, cy-r1, 2*r1, 2*r1);
		Ellipse2D e2 = new Ellipse2D.Double(cx-r2, cy-r2, 2*r2, 2*r2);

		if(e.contains(rx, ry, width, height))
			return false;
		if(e.intersects(rx, ry, width, height) || e2.intersects(rx, ry, width, height)
			return true;
			
		return false;
	}

}
