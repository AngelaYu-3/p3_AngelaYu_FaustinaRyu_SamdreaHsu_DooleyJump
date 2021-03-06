import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.geom.AffineTransform;
import java.net.URL;
import java.util.ArrayList;

/**
 *creates background objects
 *sets up both start/end screens to be called in driver
 */
public class Background{
	private int x, y, vx, vy; 
	private Image img; 
	private Font f1 = new Font("Courier New", 1, 35);
	private Font f2 = new Font("Courier New", 1, 25);
	private Font f3 = new Font("Courier New", 1, 16);
	
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
	

	
	/**
	 * setting up start screen
	 */
	public void startScreen(Graphics g) {
		g.setFont(f1);
		g.setColor(Color.black);
		g.drawString("Welcome to Dooley Jump", 80, 250);
		
		g.setColor(new Color(148, 209, 113));
		g.fillRect(200, 300, 200, 50);
		g.setFont(f2);
		g.setColor(Color.black);
		g.drawString("play", 270, 330);
		
		g.setFont(f3);
		g.drawString("Programmers: Angela Yu, Faustina Ryu, Samdrea Hsu", 65, 380);
		g.drawString("Graphics Help: Emily Yu", 65, 410);
		g.drawString("Music: Blinding Lights-The Weeknd, Butter-BTS", 65, 440);
		g.drawString("Never Gonna Give You Up-Rick Astley", 132, 465);
 
	}

	/**
	 * setting up end screen
	 */
	public void endScreen(Graphics g, double value) {
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
		
		g.drawString("FINAL TIME: " + value, 160, 470);
		
		/*g.setFont(f3);
		for(int i = 0; i < scoreBoard.size(); i++) {
			g.drawString("Game " + 0 + ": " + scoreBoard.get(0), 100, 430);
		}*/
	}
	
	private AffineTransform tx = AffineTransform.getTranslateInstance(x, y);

	// draw the affine transform
	public void paint(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		g2.drawImage(img, tx, null);
		
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