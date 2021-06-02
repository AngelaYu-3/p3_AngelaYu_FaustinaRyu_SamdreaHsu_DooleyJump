import java.awt.Rectangle;
import java.awt.event.KeyEvent;

import javax.swing.Timer;

public class Dooley extends Character{
	private int currY = getY();
	private boolean isUp = true;
	private boolean isDead = false;

	public Dooley(String fileName, int width, int height, int x, int y, int vx, int vy) {
		super(fileName, width, height, x, y, vx, vy);
		// TODO Auto-generated constructor stub
	}
	
	//collision between dooley and enemy
	public boolean isColliding(Enemies e) {
		//represent enemy as a "rectangle"
		Rectangle dRect = new Rectangle(x, y, width, height);
		Rectangle eRect = new Rectangle(e.getX(), e.getY(), e.getWidth(), e.getHeight());
		return dRect.intersects(eRect);
	}
	
	public void bounce(int height) {
		if(isUp) {
			setvy(-1);
			if(getY() <= currY - height) isUp = false;
		}
		if(!isUp) {
			setvy(1);
			if(getY() >= currY) isUp = true;
		}
	}

}
