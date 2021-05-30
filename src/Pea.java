import java.awt.Graphics;
import java.awt.Graphics2D;

public class Pea extends Character{
	private boolean isMoving;
	
	public Pea(String fileName, int width, int height, int x, int y, int vx, int vy) {
		super(fileName, width, height, x, y, vx, vy);
		this.isMoving = false;
	}
	
	public String toString() {
		return "pea";
	}
	
	public void setMoving(boolean isMoving) {
		this.isMoving = isMoving;
	}
	
	public boolean getMoving() {
		return isMoving;
	}

}
