import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.geom.AffineTransform;
import java.net.URL;

//include autoscroller logic here! 
public class Background{
	private int x, y, vy; 
	private Image img; 

	
	/* if filename is provided */
	public Background(String fileName, int startx, int starty, int width, int height) {
		// assignment statements for attributes
		x = starty;
		y = startx;
		vy = 2;
		
		img = getImage(fileName);
		img = img.getScaledInstance(width, height, img.SCALE_SMOOTH);
		init(x, y);
	}

	public void scroll() {
		y += vy;
		tx.setToTranslation(x, y);

	}
	
	public int getY() {
		return y;
	}
	
	public void setY(int y) {
	    this.y = y;
	}
	
	private AffineTransform tx = AffineTransform.getTranslateInstance(x, y);

	// draw the affine transform
	public void paint(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		g2.drawImage(img, tx, null);
		
	}
	
	private void init(double a, double b) {
		tx.setToTranslation(a, b);
		tx.scale(1, 1);
	}


	private Image getImage(String path) {
		Image tempImage = null;
		try {
			URL imageURL = Background.class.getResource(path);
			tempImage = Toolkit.getDefaultToolkit().getImage(imageURL);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return tempImage;
	}




}
