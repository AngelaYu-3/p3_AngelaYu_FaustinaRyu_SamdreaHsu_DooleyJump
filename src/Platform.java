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
	final int WIDTH = 50;
	final int HEIGHT = 20;
	final int WINDOW_WIDTH = 600;
	final int WINDOW_HEIGHT = 800;
	

	
	/*
	 * constructor for generic platform
	 */
	public Platform(String pType) {
		// TODO: generate random x and y according to dimensions of platform and window 
		
		img = getImage(pType);
		img = img.getScaledInstance(WIDTH, HEIGHT, img.SCALE_SMOOTH);
		init(x, y);
	}
	
	/*
	 * Default constructor creates the normal platform
	 */
	public Platform() {
		this("platform");
	}

	private AffineTransform tx = AffineTransform.getTranslateInstance(x, y);

	// draw the affine transform
	public void paint(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		//TODO: move();
		g2.drawImage(img, tx, null);
		
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
	public boolean steppedOn(Dooley d) {
		Rectangle dooley = new Rectangle(d.getX(), d.getY(), d.getWidth(), d.getHeight());
		Rectangle platform = new Rectangle(this.x, this.y, this.WIDTH, this.HEIGHT);
		
		return dooley.intersects(platform);
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
	
	public boolean offScreen() {
		return y > WINDOW_HEIGHT - HEIGHT;
	}
	
	/*
	 * When it goes off the bottom of the screen, it respawns on the top
	 */
	public void respawn() {
		//TODO: Logic to implement random spawning to guarantee no 
		//      overlapping and adequate spacing
	}
}
