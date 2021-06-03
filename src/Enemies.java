import java.awt.Rectangle;

public class Enemies extends Character {
	
	private int x;
	private int y;
	private int vx;
	private int vy;
	private int width; 
	private int height;
				
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
		Rectangle enemy = new Rectangle(this.x, this.y, this.width, this.height);
		Rectangle pea = new Rectangle(p.getX(), p.getY(), 38, 38);
		return enemy.intersects(pea);
	}
	
	public boolean isColliding(Dooley d) {
		//represent object as a "rectangle"
		Rectangle enemy = new Rectangle(this.x, this.y, this.width, this.height);
		Rectangle dooley = new Rectangle(d.getX(), d.getY(), 65, 65);
		return enemy.intersects(dooley);
	}
	
	public int getvx() {
		return vx;
	}

	public int getvy() {
		return vy;
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