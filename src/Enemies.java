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
		
//	public boolean isCollidingD(Dooley d) {
//		//represent enemy as a "rectangle"
//		Rectangle eRect = new Rectangle(x, y, width, height);
//		//represent dooley as a rectangle
//		Rectangle dRect = new Rectangle(d.getX(), d.getY(), d.getWidth(), d.getHeight());
//		d.setIsDead(true);
//		return eRect.intersects(dRect);
//	}
//	
//	public boolean equals(Pea p) {
//		
//		//represent enemy as a "rectangle"
//		Rectangle eRect = new Rectangle(x, y, width, height);
//		//represent p as a rectangle
//		Rectangle pRect = new Rectangle(p.getX(), p.getY(), p.getWidth(), p.getHeight());
//		return eRect.intersects(pRect);
//	}
	
	public String toString() {
			return x + " " + y;
	}
	
	public void move() {
		super.move();
	}
	
	public void setvx(int vx) {
		this.vx = vx;
	}

	public void setvy(int vy) {
		this.vy = vy;
	}
	
}