import java.awt.Rectangle;
import java.util.ArrayList;

public class Enemies extends Character {
	
	
	private boolean shot;
	// default constructor, sets all to zero
	public Enemies(String filename, int w, int h, int x, int y, int vx, int vy) {
			super(filename, w, h, x, y, vx, vy);
			
			
			this.vx = 1;
			width = 65;
			height = 65;
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