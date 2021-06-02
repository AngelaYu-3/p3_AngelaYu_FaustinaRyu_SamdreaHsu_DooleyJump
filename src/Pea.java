import java.awt.Graphics;
import java.awt.Graphics2D;

public class Pea extends Character{
	private boolean isMoving;
	private int xi, yi;
	
	public Pea(String fileName, int width, int height, int x, int y, int vx, int vy) {
		super(fileName, width, height, x, y, vx, vy);
		xi = x;
		yi = y;
	}
	
	public String toString() {
		return "pea" + isMoving;
	}
	
	public void reset() {
		x = xi;
		y = yi;
		isMoving = false;
	}
	public void setMoving(boolean isMoving) {
		this.isMoving = isMoving;
	}
	
	public boolean getMoving() {
		return isMoving;
	}
	
	public void newShot(Graphics g, Pea[] p) {
		for(int i = 0; i < 10; i++) {
	    	if(!p[i].getMoving() ) {
	    		p[i].setMoving(true);
	    		break;
	    	}
	    }
	    
	}
	
	public void shoot(Graphics g, Pea[] p, Dooley d) {
		for(int i = 0; i < 10; i++) {
    		if(p[i].getMoving()) {	
    			p[i].setX(d.getX() + 17);
    			p[i].paint(g);
    		}
    	}
	}

}
