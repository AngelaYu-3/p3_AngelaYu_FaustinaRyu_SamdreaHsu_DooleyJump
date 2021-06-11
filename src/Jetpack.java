
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.geom.AffineTransform;
import java.net.URL;
import java.util.ArrayList;


public class Jetpack {
	
	private int x, y, vx, vy;
	private Image img;

	public Jetpack(int x, int y, int vx, int vy) {
		this.x = x;
		this.y = y;
		this.vx = vx;
		this.vy = vy;
		
		img = getImage("/Graphics/jetpack.png");
		img = img.getScaledInstance(65, 65, img.SCALE_SMOOTH);
		init(x, y);
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
}
