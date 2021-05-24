import java.awt.event.KeyEvent;

import javax.swing.Timer;

public class Dooley extends Character{
	private int currY = getY();
	private boolean isUp = true;

	public Dooley(String fileName, int width, int height, int x, int y, int vx, int vy) {
		super(fileName, width, height, x, y, vx, vy);
		// TODO Auto-generated constructor stub
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