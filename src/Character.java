import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.geom.AffineTransform;
import java.net.URL;

public class Character{
	// attributes of a frog
	private int x, y; // Position of frog
	private int vx, vy;
	protected int width; // the size of frog
	protected int height;
	
	private Image img; 
	
	/* if filename is provided */
	public Character(String fileName, int width, int height) {
		// assignment statements for attributes
		x = 400;
		y = 400;
		vx = 0;
		vy = 0;
		img = getImage(fileName);
		img = img.getScaledInstance(width, height, img.SCALE_SMOOTH);
		init(x, y);

	}


	// gets image and proccess it
	public void move() {
		
		y += vy;
		x += vx;
		tx.setToTranslation(x, y);

	}

	public void hop(int x, int y) {
        this.y += y;
        this.x += x;
        
		tx.setToTranslation(x, y);
		
	}
	
	
	private AffineTransform tx = AffineTransform.getTranslateInstance(x, y);

	// draw the affine transform
	public void paint(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		move(); //ask frog to update its location variables
		g2.drawImage(img, tx, null);
		
	}

	private void init(double a, double b) {
		tx.setToTranslation(a, b);
		tx.scale(1, 1);
	}

	// converts image to make it drawable in paint
	private Image getImage(String path) {
		Image tempImage = null;
		try {
			URL imageURL = Character.class.getResource(path);
			tempImage = Toolkit.getDefaultToolkit().getImage(imageURL);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return tempImage;
	}

	// setters and getters

	public void setVx(int vx) {
		this.vx = vx;
	}

	public void setVy(int vy) {
		this.vy = vy;
		
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
		tx.setToTranslation(x, y);
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
		tx.setToTranslation(x, y);
	}
	
	/* Helper function for collision detection later */
	public Rectangle getRect() {
		Rectangle temp = new Rectangle(x,y,width,height);
		return temp;
	}

}
