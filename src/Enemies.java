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
			
			x = 50;
			y = 247;
			width = 65;
			height = 65;
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
		Rectangle enemy = new Rectangle(this.x+10, this.y+10, this.width-20, this.height-20);
		Rectangle pea = new Rectangle(p.getX()+12, p.getY()+13, p.getWidth()+15, p.getHeight()+15);
				
		return enemy.intersects(pea);
	}
	
	public boolean isColliding(Dooley d) {
		//represent object as a "rectangle"
		Rectangle dooley = new Rectangle(d.getX() + 10, d.getY() + 10, 45, 45);
		Rectangle enemy = new Rectangle(this.x + 10, this.y + 10, this.width-20, this.height-20);
			
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