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
	protected int count;
	protected Image img;
	protected boolean isStepped, hasRocket;
	final int WIDTH = 90;
	final int HEIGHT = 60;
	final int WINDOW_WIDTH = 600;
	final int WINDOW_HEIGHT = 800;
	

	
	/*
	 * constructor for custom platform
	 */
	
	//jetpack: 115, 73
	//platform: 100, 100
	public Platform(String pType, int x, int y, int vx, int vy) {
		// TODO: generate random x and y according to dimensions of platform and window 
		
		this.x = x;
		this.y = y;
		this.vx = vx;
		this.vy = vy;
		hasRocket = false;
		count = 0;
		
		img = getImage(pType);
		img = img.getScaledInstance(WIDTH, HEIGHT, img.SCALE_SMOOTH);
		init(x, y);
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
		//System.out.println(platform.intersects(dooley));
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
	 */
	public void respawn() {
		//TODO: Logic to implement random spawning to guarantee no 
		//      overlapping and adequate spacing
	}
}