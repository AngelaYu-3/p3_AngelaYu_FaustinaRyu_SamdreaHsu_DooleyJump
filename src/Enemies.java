import java.awt.Rectangle;

public class Enemies extends Character {
	
	private int x;
	private int y;
	private int vx;
	private int vy;
	private int width; 
	private int height;
	private boolean isDead;
				
	// default constructor, sets all to zero
	public Enemies(String filename, int w, int h, int x, int y, int vx, int vy) {
			super(filename, w, h, x, y, vx, vy);
	}
	
	public void sideToside() {
		if (x<60) {
			vx*=-1;
		} else if (x>540) {
			vx*=-1;
		}
	}
	
	
	//collision between enemy and pea
	public boolean isColliding(Pea p) {
		//represent object as a "rectangle"
		Rectangle tRect = new Rectangle(x, y, width, height);
		Rectangle pRect = new Rectangle(p.getX(), p.getY(), p.getWidth(), p.getHeight());
		return tRect.intersects(pRect);
	}
	
	public void setvx(int vx) {
		this.vx = vx;
	}

	public void setvy(int vy) {
		this.vy = vy;
	}
	
	public String toString() {
		return x + " " + y;
	}
	
}