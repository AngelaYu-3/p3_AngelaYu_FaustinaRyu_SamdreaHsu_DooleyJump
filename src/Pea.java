
public class Pea extends Character{

	private int x; // Position of character
	private int y;
	private int vx, vy;
	private int width; // the size of frog
	private int height;
	
	private String fileName;
	
	public Pea(String fileName, int width, int height, int x, int y, int vx, int vy) {
		super(fileName, width, height, x, y, vx, vy);
		// TODO Auto-generated constructor stub
	}
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
	
	public int getvx(int vx) {
		return vx;
	}
	
	public int getvy(int vy) {
		return vy;
	}
	
	
	public int getWidth() {
		return width;
	}
	
	public int getHeight() {
		return height;
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

}

