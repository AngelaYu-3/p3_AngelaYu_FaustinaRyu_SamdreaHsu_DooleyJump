import java.awt.Graphics;
import java.awt.Graphics2D;

public class Pea extends Character{
	private boolean isMoving;
	private int xi, yi;
	
	public Pea(String fileName, int width, int height, int x, int y, int vx, int vy) {
		super(fileName, width, height, x, y, vx, vy);
		//isMoving = false;
		//xi = x;
		//yi = y;
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
	
	public void resetP(Pea[] p) {
		for(int i = 0; i < 4; i++) {
    		if(p[i].getMoving() && p[i].getY() < 0) {
    			p[i].reset();
    		}
    	}
	}
	
	public int findPi(Pea[] p) {
		int pi = -1;
    	for(int i = 0; i < 4; i++) {
    		if(p[i].getMoving() == false && pi == -1) {
    			System.out.println("hi");
    			pi = i;
    			p[pi].setMoving(true);
    			
    		}
    	}
    	return pi;
	}

}
