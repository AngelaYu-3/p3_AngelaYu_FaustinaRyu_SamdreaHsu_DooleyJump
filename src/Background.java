
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.geom.AffineTransform;
import java.net.URL;

public class Background{
	private int x, y, vx, vy; 
	private Image img; 
	private Font f1 = new Font("Courier New", 1, 35);
	private Font f2 = new Font("Courier New", 1, 25);

	
	/* if filename is provided */
	public Background(String fileName, int startx, int starty, int width, int height) {
		// assignment statements for attributes
		x = starty;
		y = startx;
		vy = 0;
		
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
	
	public void setvy(int vy) {
		this.vy = vy;
	}
	
	public void startScreen(Graphics g) {
		g.setFont(f1);
		g.setColor(Color.black);
		g.drawString("Welcome to Dooley Jump", 80, 250);
		
		g.setColor(new Color(148, 209, 113));
		g.fillRect(200, 300, 200, 50);
		g.setFont(f2);
		g.setColor(Color.black);
		g.drawString("play", 270, 330);
 
	}

	//still need to link end button & animate endScreen to come in at right time
	public void endScreen(Graphics g) {
		g.setFont(f1);
		g.setColor(Color.black);
		g.drawString("Dooley is ded RIP", 120, 260);
		
		g.setColor(new Color(148, 209, 113));
		g.fillRect(200, 300, 200, 50);
		g.setFont(f2);
		g.setColor(Color.black);
		g.drawString("replay", 260, 330);
		
		g.setColor(new Color(148, 209, 113));
		g.fillRect(200, 370, 200, 50);
		g.setFont(f2);
		g.setColor(Color.black);
		g.drawString("end", 275, 400);
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