
public class Pea extends Character{

	private int x; // Position of character
	private int y;
	private int vx, vy;
	private int width; // the size of frog
	private int height;
	
	private String fileName;
	
	public Pea(String fileName, int width, int height, int x, int y, int vx, int vy) {
		super(fileName, width, height, x, y, vx, vy);
		// TODO Auto-generated constructor stub
	}
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
	
	public int getvx(int vx) {
		return vx;
	}
	
	public int getvy(int vy) {
		return vy;
	}
	
	
	public int getWidth() {
		return width;
	}
	
	public int getHeight() {
		return height;
	}
	
	public void setvx(int vx) {
		this.vx = vx;
	}

	public void setvy(int vy) {
		this.vy = vy;
		
	}
	
	public void setfileName(String fileName) {
		this.fileName = fileName;
	}
	
 	public void setStepY(int y) {
		stepy += y;
	}

}

import java.awt.Graphics;
import java.awt.Graphics2D;

public class Pea extends Character{
	private boolean isMoving;
	private int xi, yi;
	
	public Pea(String fileName, int width, int height, int x, int y, int vx, int vy) {
		super(fileName, width, height, x, y, vx, vy);
		//isMoving = false;
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
	
	public void reset(Pea[] p) {
		for(int i = 0; i < 4; i++) {
	    	if(p[i].getMoving() && p[i].getY() < 0) {
	    		p[i].reset();
	    	}
	    }
	}
	
	public void setMoving(boolean isMoving) {
		this.isMoving = isMoving;
	}
	
	public boolean getMoving() {
		return isMoving;
	}
	
	public void newShot(Graphics g, Pea[] p) {
		boolean pfound = false;
		for(int i = 0; i < 4; i++) {
	    	if(!p[i].getMoving() && !pfound) {
	    		p[i].setMoving(true);
	    		pfound = true;
	    	}
	    }
	    
	}
	
	public void shoot(Graphics g, Pea[] p, Dooley d) {
		for(int i = 0; i < 4; i++) {
    		if(p[i].getMoving()) {	
    			p[i].setX(d.getX() + 17);
    			p[i].paint(g);
    		}
    	}
	}

}
