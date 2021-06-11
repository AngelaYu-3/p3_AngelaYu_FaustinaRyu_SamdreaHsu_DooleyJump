import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.geom.AffineTransform;
import java.net.URL;

public class Character{
	// attributes of a character
	protected int x; // Position of character
	protected int y;
	protected int vx, vy;
	protected int width; 
	protected int height;
	protected String fileName;
	int stepy;
	int id;
	int vcount;
	
	protected Image img; 
	
	/* if filename is provided */
	public Character(String fileName, int width, int height, int x, int y, int vx, int vy) {
		// assignment statements for attributes
		this.width = width;
		this.height = height;
		this.x = x;
		this.y = y;
		this.vx = vx;
		this.vy = vy;
		this.fileName = fileName;
		img = getImage(fileName);
		img = img.getScaledInstance(width, height, img.SCALE_SMOOTH);
		init(x, y);

	}

	// gets image and process it
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
	
	//check if two characters are in the same spot 
	public boolean equals(Character obj) {
		Character other = obj;
		if ((obj.getX()+obj.getWidth()) >= other.getX() &&
			obj.getX() <= (other.getX()+other.getWidth()) &&
			(obj.getY()+obj.getHeight()) >= other.getY() &&
			obj.getY() <= (other.getY()+other.getHeight())
			) {
			return true;
		}
		else {
			return false;
		}
	}
	
	protected AffineTransform tx = AffineTransform.getTranslateInstance(x, y);

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

	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
	
	public int getvx() {
		return vx;
	}
	
	public int getvy() {
		return vy;
	}
	
	
	public int getWidth() {
		return width;
	}
	
	public int getHeight() {
		return height;
	}
	
	public int getVcount() {
		return vcount;
	}
	
	public void setX(int x) {
		this.x = x;
		tx.setToTranslation(x, y);
	}
	
	public void setY(int y) {
		this.y = y;
		tx.setToTranslation(x, y);
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
	
	public void setVcount(int v) {
		vcount = v;
	}
	
	//returns the index number of image from the ArrayList
	public int show() {
		return id;
	}
	
	public String toString() {
		return x + " " + y;
	}
	
	/* Helper function for collision detection later */
	public Rectangle getRect() {
		Rectangle temp = new Rectangle(x,y,width,height);
		return temp;
	}

	
	
}