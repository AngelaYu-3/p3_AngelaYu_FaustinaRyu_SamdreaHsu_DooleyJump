import java.awt.Graphics;
/**
 * Pea class
 * deals with pea animation including shooting
 */
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
	
	public void reset(Pea[] p, int numPeas) {
			for(int i = 0; i < numPeas; i++) {
		    	if(p[i].getMoving() && p[i].getY() < 0) {
		    		p[i].reset();
		    	}
		    }
	}
	
	/**
	 * sets/returns if a pea is moving
	 */
	public void setMoving(boolean isMoving) {
		this.isMoving = isMoving;
	}	
	public boolean getMoving() {
		return isMoving;
	}
	
	/**
	 * first finds first pea in array that is not already moving and
	 * creates a new shot by setting a new pea to be moving
	 */
	public void newShot(Graphics g, Pea[] p, int numPeas, int px, int py) {
		for(int i = 0; i < numPeas; i++) {
	    	if(!p[i].getMoving() ) {
	    		p[i].setMoving(true);
	    		p[i].setX(px);
	    		p[i].setY(py);
	    		break;
	    	}
	    }
	    
	}
	
	/**
	 * shoots all peas with isMoving set to true
	 */
	public void shoot(Graphics g, Pea[] p, Dooley d, int numPeas) {
		for(int i = 0; i < numPeas; i++) {
    		if(p[i].getMoving()) {	
    			p[i].setX(d.getX() + 17);
    			p[i].paint(g);
    		}
    	}
	}

}
