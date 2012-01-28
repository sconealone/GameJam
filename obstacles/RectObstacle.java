package obstacles;
import game.GameOverException;
import java.awt.geom.Rectangle2D;




public class RectObstacle extends Obstacle{
	private final int FRAMES_PER_SECOND = 4;
	public Rectangle2D rect;
	public RectObstacle(int duration, double xloc, double yloc, double d){
		rect = new Rectangle2D.Double(xloc, yloc, d, d);
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
			double d = rect.getWidth();
			//double h = rect.getHeight();
			double centerX = x + d / 2;
			double centerY = y - d / 2;
			d = d * alpha;
			x = centerX - d;
			y = centerY + d;
			rect.setRect(x, y, d, d);
		}
		draw();
	}

	@Override
	public void draw() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean haveCollided() {
		// TODO Auto-generated method stub
		return false;
	}

}
