import java.awt.Rectangle;

public class Enemies extends Character {
	
	Rectangle world = new Rectangle(0, 0, 600, 800);
	private int xBound;
	// default constructor, sets all to zero
	public Enemies(String filename, int w, int h, int x, int y, int vx, int vy) {
			super(filename, w, h, x, y, vx, vy);
			
			
			this.vx = -5;
			width = 65;
			height = 65;
	}
		
	public void sideToside() {
			vx*=-1;
		
	}
	
	
	//collision between enemy and pea
	public boolean isColliding(Pea p) {
		//represent object as a "rectangle"
		Rectangle enemy = new Rectangle(this.x+10, this.y+10, 40, 40);
		Rectangle pea = new Rectangle(p.getX()+12, p.getY()+13, p.getWidth()-25, p.getHeight()-25);
		

		return enemy.intersects(pea);
	}
	
	public boolean isColliding(Dooley d) {
		//represent object as a "rectangle"
		Rectangle dooley = new Rectangle(d.getX() + 10, d.getY() + 10, 48, 55);
		Rectangle enemy = new Rectangle(this.x + 10, this.y + 10, 40, 40);

		return enemy.intersects(dooley);
	}
	
//	public void respawn(int maxY) {
//		//The whole screen is essentially a 10 by 14 grid 
//		int rows = 14;
//		int cols = 7;
//		
//		// the max y
//		int max = (int)((double)maxY/800 * rows);
//		
//		// random indexes for x and y
//		int x = (int)(Math.random() * cols);
//		int y = (int)(Math.random() * max);
//		
//		// change x and y to match a cell on the grid
//		x = (int)((double) x / cols * 600);
//		y = (int)((double) y / rows * (800 - 60));
//		
//		this.x = x;
//		this.y = y;
//		
//	}
	
	public int getvx() {
		return vx;
	}

	public int getvy() {
		return vy;
	}
	
		
	public String toString() {
		return x + " " + y;
	}

	
	public void setvx(int vx) {
		this.vx = vx;
	}

	public void setvy(int vy) {
		this.vy = vy;
	}
	
}