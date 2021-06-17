import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.geom.AffineTransform;
import java.net.URL;
import java.util.ArrayList;

public class Platform {
	
	protected int x, xi;
	protected int y, yi;
	protected int vx;
	protected int vy;
	protected int count;
	protected Image img;

	protected boolean isStepped, hasRocket;

    final int WIDTH = 90;
	final int HEIGHT = 60;
	final int WINDOW_WIDTH = 600;
	final int WINDOW_HEIGHT = 800;
	protected boolean shifting, isSteppedOn;
	protected int startingY;
	Platform[] p = new Platform[40];

  
	public Platform(String pType, int x, int y, int vx, int vy) {
		// TODO: generate random x and y according to dimensions of platform and window 
		
		this.x = x;
		this.xi = x;
		this.y = y;
		this.yi = y;
		this.vx = vx;
		this.vy = vy;
		hasRocket = false;
		shifting = true;
		count = 0;
		
		img = getImage(pType);
		img = img.getScaledInstance(WIDTH, HEIGHT, img.SCALE_SMOOTH);
		init(x, y);
	}
	
	public boolean endShift(Platform[] p) {
		for(int i = 0; i < 1; i++) {
			if(p[i].getShift()) return true;
		}
		return false;
	}
	
	public boolean getShift() {
		return shifting;
	}
	
	public void setShift(boolean shifting) {
		this.shifting = shifting;
	}
  
  public Platform(int x, int y) {
		this("/Graphics/platform.png", x, y, 0, 0);
	}
	
	public int jetX() {
		return x + 15;
	}
	
	public int jetY() {
		return y - 27;
	}

	private AffineTransform tx = AffineTransform.getTranslateInstance(x, y);

	// draw the affine transform
	public void paint(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		//TODO: move();
		move();
		g2.drawImage(img, tx, null);
		
	}
	
	public String toString() {
		return "x: " + x + "y: " + y;
	}


	private void move() {
		// TODO Auto-generated method stub
		y += vy;
		x += vx;
		tx.setToTranslation(x, y);
	}

	public void init(double a, double b) {
		tx.setToTranslation(a, b);
		tx.scale(1, 1);
	}

	// converts image to make it drawable in paint
	public Image getImage(String path) {
		Image tempImage = null;
		try {
			URL imageURL = Platform.class.getResource(path);
			tempImage = Toolkit.getDefaultToolkit().getImage(imageURL);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return tempImage;
	}
	
	/**
	 * continuously having dooley bounce on platform 
	 * unless dooley falls off
	 */
	public boolean checkPlat(Dooley d, Platform p, boolean platDiff, int i) {
		if((isSteppedOn(d) || (!isSteppedOn(d) && d.getY() - 55 < y + 26 
				&& (d.getX() >= x && d.getX() < x + WIDTH - 20)))) {
			return false;
		}
		else{
			return true;
		}
	}
	
	public boolean tooClose(Platform p, ArrayList<Platform> pl) {
		for(int i = 0; i < pl.size() - 1; i++) {
			if(pCollision(p, pl, i, 20)) return true;
		}
		return false;
	}
	
	public boolean pCollision(Platform p, ArrayList<Platform> pl, int i, int b) {
		Rectangle p0 = new Rectangle(pl.get(i).getX() + 14, pl.get(i).getY() + 26, pl.get(i).getWidth() - 25 + b, pl.get(i).getHeight() - 45 + (b/2));
		Rectangle p1 = new Rectangle(p.getX() + 14, p.getY() + 26, p.getWidth() - 25 + b, p.getHeight() - 45 + (b/2));
		return p0.intersects(p1);
	}
 
    public Platform[] path() {
    	p[0] = new Platform(300, 600);
    	p[1] = new Platform(350, 550);
    	//p[2] = new Platform(350, );
    	
    	return p;
    }
    
    public Platform[] resetPath() {
    	for(int i = 0; i < 1; i++) {
    		int initial = p[i].getYi();
    		p[i].setY(initial);
    	}
    	
    	return p;
    }
	
	/**
	 * Checks to see if Dooley stepped on a platform
	 * Use this method when Dooley's vy < 0 to check if it lands on something
	 */
	public boolean isSteppedOn(Dooley d) {
		Rectangle dooley = new Rectangle(d.getX() + 10, d.getY() + 10, 48, 55);
		Rectangle platform = new Rectangle(x + 20, y + 26, WIDTH - 25, HEIGHT - 58);
		return platform.intersects(dooley);
	}
	
	public int getWidth() {
		return WIDTH;
	}
	public int getHeight() {
		return HEIGHT;
	}
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
	
	/**
	 * dooley bounces as a result of stepping on platform
	 */

    public void result(Dooley d) {
		d.bounce(100, 3);
	}
	
	public boolean offScreen() {
		return y > WINDOW_HEIGHT - HEIGHT;
	}
	
	public int getYi() {
		return yi;
	}

	public void shiftDown(Platform[] p, int y, int vy, int plat) {
		
		//if(p[plat].getY() > yi + y) {
			
		//}
	}
	
	/*public void shiftDown(int units, int vy) {
		if (!shifting) {
			setVy(vy);
			shifting = true;
			startingY = getY();
		} else {
			if (getY() - startingY >= units) {
				setVy(0);
				shifting = false;
			}
			if(getY() >= WINDOW_HEIGHT) {
				respawn(-10);
				startingY = startingY - WINDOW_HEIGHT;
			}
		}
	}*/
	
	public boolean isShifting() {
		return shifting;
	}
	
	public void setX(int x) {
		this.x = x;
		tx.setToTranslation(x, y);
	}
	
	public void setY(int y) {
		this.y = y;
		tx.setToTranslation(x, y);
	}
	
	public void setVx(int vx) {
		this.vx = vx;
	}
	
	
	public void setVy(int vy) {
		this.vy = vy;
	}
    
}
