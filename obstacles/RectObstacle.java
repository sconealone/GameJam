package obstacles;

import java.awt.geom.Ellipse2D;


public class RectObstacle extends Obstacle{

	@Override
	public void update() {
		// TODO Auto-generated method stub
		
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
