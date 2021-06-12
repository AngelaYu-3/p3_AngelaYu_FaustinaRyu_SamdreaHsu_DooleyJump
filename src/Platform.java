import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.geom.AffineTransform;
import java.net.URL;
import java.util.ArrayList;


/*
 * The is the class for the normal platform
 * 
 * TODO in driver: 
 * 1) create an arraylist of platforms
 * 2) initialize every platform in driver's constructor(have some logic that will give 20% chance of vine,
 * 		20% chance of broken, and 60% chance of normal)
 * 3) in paint, loop through each platform and call steppedOn to see
 * 
 */
public class Platform {
	
	protected int x;
	protected int y;
	protected int vx;
	protected int vy;

	protected Image img;
	protected boolean isStepped, hasRocket;
	final int WIDTH = 90;
	final int HEIGHT = 60;
	final int WINDOW_WIDTH = 600;
	final int WINDOW_HEIGHT = 800;
	protected boolean shifting = false;
	protected int startingY;
	

	
	/*
	 * constructor for custom platform
	 */
	
	//jetpack: 115, 73
	//platform: 100, 100
	public Platform(String pType, int x, int y, int vx, int vy) {
		//respawn(WINDOW_HEIGHT);
		
		this.x = x;
		this.y = y;
		this.vx = vx;
		this.vy = vy;
		hasRocket = false;

		
		img = getImage(pType);
		img = img.getScaledInstance(WIDTH, HEIGHT, img.SCALE_SMOOTH);
		init(x, y);
	}
	
	public Platform(int x, int y) {
		this("/Graphics/platform.png", x, y, 0, 0);
	}
	
	public Platform() {
		this("/Graphics/platform.png", 0, 0, 0, 0);
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
		move();
		g2.drawImage(img, tx, null);
		
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
	
	public boolean checkPlat(Dooley d) {
		if((isSteppedOn(d) || (!isSteppedOn(d) && d.getY() - 55 < y + 26 
				&& (d.getX() + 10 > x + 14 && d.getX() + 10 < x + WIDTH - 20)))) {
			result(d);
			return false;
		}
		else{
			System.out.println("falling");
			d.fall();
			return true;
		}
	}
	
	/*
	 * Checks to see if Dooley stepped on a platform
	 * Use this method when Dooley's vy < 0 to check if it lands on something
	 */
	public boolean isSteppedOn(Dooley d) {
		Rectangle dooley = new Rectangle(d.getX() + 10, d.getY() + 10, 48, 55);
		Rectangle platform = new Rectangle(x + 20, y + 26, WIDTH - 25, HEIGHT - 45);
		return platform.intersects(dooley) && d.getvy() >= 0;
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
	
	/*
	 * This method will give the result of being stepped on
	 * 
	 * It will vary according to what type of platform it is
	 * 
	 * Stepping on a normal platform will
	 * 1) cause dooley to jump
	 * 2) make all the platforms and characters go down (this method returns
	 * the value that all objects need to shift by)
	 * 
	 */
	public void result(Dooley d) {
		d.bounce(100, 4);
	}
	
	public boolean offScreen() {
		return y > WINDOW_HEIGHT - HEIGHT;
	}
	
	/*
	 * When it goes off the bottom of the screen, it respawns on the top
	 * Parameter: the max y the platform can spawn to
	 * 
	 */
	public void respawn(int maxY) {
		//The whole screen is essentially a 10 by 14 grid 
		int rows = 14;
		int cols = 7;
		
		// the max y
		int max = (int)((double)maxY/WINDOW_HEIGHT * rows);
		
		// random indexes for x and y
		int x = (int)(Math.random() * cols);
		int y = (int)(Math.random() * max);
		
		// change x and y to match a cell on the grid
		x = (int)((double) x / cols * WINDOW_WIDTH - 10);
		y = (int)((double) y / rows * (WINDOW_HEIGHT - HEIGHT));
		
		this.x = x;
		this.y = y;
		
	}
	
	public boolean tooClose(Platform p) {
		Rectangle p1 = new Rectangle(this.x + 14, this.y + 26, this.WIDTH - 25, this.HEIGHT - 45);
		Rectangle p2 = new Rectangle(p.getX() + 14, p.getY() + 26, p.getWidth() - 25, p.getHeight() - 45);
		return p1.intersects(p2);
	}
	
	public void shiftDown(int units, int vy) {
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
		
		//return shifting;
	}
	
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
	
	public static void moreRand(ArrayList<Platform> platforms) {

    	
    	int moreRand = (int)(Math.random() * 20);
    	platforms.get(0).setX(platforms.get(0).getX() + moreRand);
		platforms.get(0).setY(platforms.get(0).getY() + moreRand);
    	
    	boolean touching = true;
    	while(touching) {
    		touching = false;
    		for(int j = 0; j < platforms.size(); j++) {
    			if (j != 0 && platforms.get(0).tooClose(platforms.get(j))) {
        			touching = true;
        			
        			// stagger the platforms if they intersect
        			int fix = 10;
        			if ( platforms.get(0).getX() < platforms.get(j).getX()) {
        				// stagger to the left
        				platforms.get(0).setX(platforms.get(0).getX() - fix);
        				platforms.get(0).setY(platforms.get(0).getY() + fix);
        			} else {
        				// stagger to the right
        				platforms.get(0).setX(platforms.get(0).getX() + fix);
        				platforms.get(0).setY(platforms.get(0).getY() + fix);
        			}
    			}
    		}
    	}
	}
	
	public static void fixCollision(ArrayList<Platform> platforms) {
    	
    	boolean touching = true;
    	while(touching) {
    		touching = false;
    		for(int j = 0; j < platforms.size(); j++) {
    			if (j != 0 && platforms.get(0).tooClose(platforms.get(j))) {
        			touching = true;
        			
        			// stagger the platforms if they intersect
        			int fix = 10;
        			if ( platforms.get(0).getX() < platforms.get(j).getX()) {
        				// stagger to the left
        				platforms.get(0).setX(platforms.get(0).getX() - fix);
        				platforms.get(0).setY(platforms.get(0).getY() + fix);
        			} else {
        				// stagger to the right
        				platforms.get(0).setX(platforms.get(0).getX() + fix);
        				platforms.get(0).setY(platforms.get(0).getY() + fix);
        			}
    			}
    		}
    	}
	}
	
	/*
	 * No set number of platforms... because it's based on levels
	 * - can change the number of levels and min/max number of platforms on each level to make game harder
	 * 
	 * The number of levels in parameter is for the entire window 
	 * 
	 * The "y" argument will dictate how many levels the method will generate 
	 * - for example, a y value of 600 dictate to only generate platforms from y values of 0 to 600 
	 * - passing in a negative y value will cause all the platforms to be created at the top, out of frame)
	 * - in Driver, pass in dooley's current y - windowheight to generate more platforms at the top. 
	 */
	public static void generate(ArrayList<Platform> platforms, int levels, int min, int max, int y) {
		
		// determine how many levels will be added to array
		int addedLevels = (int)((double) Math.abs(y) / 800 * levels);
		int eachLevelHeight = (int)((double) 820 / levels);
	
		// in each added level, spawn a random number of random type of platform
		for(int i = 0; i < addedLevels; i++) {
			
			int numPlat = (int)(Math.random() * (max - min + 1)) + min;
			
			for(int j = 0; j < numPlat; j++) {
				//  20% chance of vine,
	        	//	20% chance of broken, and 60% chance of normal
				int type = (int)(Math.random() * 10);
				int randX = (int)(Math.random() * 535) + 10;
	        	int randYAddOn = (int)(Math.random() * (eachLevelHeight - 10));
	        	
	        	if(type < 1) {
	        		platforms.add(0, new Vines(randX, eachLevelHeight * i + randYAddOn));
	        	} else if(type < 2) {
	        		platforms.add(0, new Bones(randX, eachLevelHeight * i + randYAddOn));
	        	} else {
	        		platforms.add(0, new Platform(randX, eachLevelHeight * i + randYAddOn));
	        	}
	        	
	        	if(y < 0) {
	        		platforms.get(0).setY(platforms.get(0).getY() * -1);
	        	}
	        	
	        	//System.out.println("level " + i + " x: " + randX + " y: " + platforms.get(0).getY());
	    		
	    		fixCollision(platforms);
	    		//System.out.println("level " + i + " x: " + randX + " y: " + platforms.get(0).getY() +" <--after fixing collision");
	   
			}
			
			
        	
		}
		
		
	}
}