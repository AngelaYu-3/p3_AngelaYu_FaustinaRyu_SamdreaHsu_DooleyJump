import java.awt.Rectangle;

public class Enemies extends Character {
				
	// default constructor, sets all to zero
	public Enemies(String filename, int w, int h, int x, int y, int vx, int vy) {
			super(filename, w, h, x, y, vx, vy);
	}
	
	public boolean isColliding(Dooley d) {
		Rectangle dooley = new Rectangle(d.getX() + 10, d.getY() + 10, 48, 55);
		Rectangle enemy = new Rectangle(x + 10, y + 10, 40, 40);
			
		return enemy.intersects(dooley);
	}
		
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
