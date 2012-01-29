package obstacles;

import java.awt.Graphics2D;
import java.awt.Polygon;


public class TriangleObstacle extends Obstacle{

	private Polygon tri;
	@Override
	public void update() {
		float alpha = 1 + (1 - timer / origTimer);

	}

	@Override
	public boolean haveCollided() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void draw(Graphics2D g) {
		// TODO Auto-generated method stub
		
	}

}
