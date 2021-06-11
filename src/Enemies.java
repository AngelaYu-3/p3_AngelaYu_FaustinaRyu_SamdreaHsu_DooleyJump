import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.ArrayList;

/**
 * Enemies class
 * deals with random generation/animation of Enemies
 */
public class Enemies extends Character {

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
	
	public void noDooleySpawn(boolean isStart, ArrayList<Enemies> enemy, Graphics g) {
		//make sure enemy doesn't spawn on dooley
		for (int i = 0; i < enemy.size() - 1; i++) {
			if (!isStart && enemy.get(i).getY()<350) {
				enemy.get(i).paint(g);
			}
		}
	}
	
	public void enemiesShot(ArrayList<Enemies> enemy, Pea[] p) {
		//enemies die after getting shot more than 1 time 
				for (int i = enemy.size()-2; i >= 0; i--) {
					for (int j = 0; j < p.length; j++) {
						if (enemy.get(i).isColliding(p[j])) {
							enemy.get(i).setvx(0);
							enemy.remove(i);
							break;
									
		 				} 
					}
				}
	}
	
	public boolean dooleyEnemy(ArrayList<Enemies> enemy, Dooley d, boolean isDead) {
		//dooley dies
				for (int i = 0; i < enemy.size() - 1; i++) {
					if (enemy.get(i).isColliding(d)) {
						enemy.get(i).setvx(0);
						System.out.println("dooley die");
						isDead = true;
					}
				}
				
				return isDead;
	}
	
	public void move(ArrayList<Enemies> enemy) {
		//move back and forth
				for (int i = 0; i < enemy.size() - 1; i++) {
					if (enemy.get(i).getX()<0 || enemy.get(i).getX()>510) {
						//System.out.println("overbounds");
						enemy.get(i).setvx(enemy.get(i).getvx()*(-1));
						
					} 
				}
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