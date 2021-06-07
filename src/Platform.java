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
	final int WIDTH = 90;
	final int HEIGHT = 60;
	final int WINDOW_WIDTH = 600;
	final int WINDOW_HEIGHT = 800;
	

	
	/*
	 * constructor for custom platform
	 */
	public Platform(String pType) {
		// TODO: generate random x and y according to dimensions of platform and window 
		
		respawn(WINDOW_HEIGHT);
		
		img = getImage(pType);
		img = img.getScaledInstance(WIDTH, HEIGHT, img.SCALE_SMOOTH);
		init(x, y);
	}
	
	/*
	 * Default constructor creates the normal platform
	 */
	public Platform() {
		this("/Graphics/platform.png");
	}

	private AffineTransform tx = AffineTransform.getTranslateInstance(x, y);

	// draw the affine transform
	public void paint(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		move();
		g2.drawImage(img, tx, null);
		
	}
	
	public void move() {
		x += vx;
		y += vy;
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
	
	
	
	/*
	 * Checks to see if Dooley stepped on a platform
	 * Use this method when Dooley's vy < 0 to check if it lands on something
	 */
	public boolean isSteppedOn(Dooley d) {
		Rectangle dooley = new Rectangle(d.getX() + 10, d.getY() + 10, 48, 55);
		Rectangle platform = new Rectangle(this.x + 14, this.y + 26, this.WIDTH - 25, this.HEIGHT - 45);
		
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
	
	public void setX(int x) {
		this.x = x;
		tx.setToTranslation(x, y);
	}
	
	public void setY(int y) {
		this.y = y;
		tx.setToTranslation(x, y);
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
	public int result(Dooley d) {
		d.bounce(10);
		return WINDOW_HEIGHT - this.y - 20;
	}
	
	public boolean isOffScreen() {
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
		x = (int)((double) x / cols * WINDOW_WIDTH);
		y = (int)((double) y / rows * (WINDOW_HEIGHT - HEIGHT));
		
		this.x = x;
		this.y = y;
		
	}
	
	public boolean tooClose(Platform p) {
		Rectangle p1 = new Rectangle(this.x + 14, this.y + 26, this.WIDTH - 25, this.HEIGHT - 45);
		Rectangle p2 = new Rectangle(p.getX() + 14, p.getY() + 26, p.getWidth() - 25, p.getHeight() - 45);
		return p1.intersects(p2);
	}
}
