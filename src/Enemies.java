
public class Enemies extends Character {
	
	int x;
	int y;
	int width; 
	int height;
				
	// default constructor, sets all to zero
	public Enemies(String filename, int w, int h) {
			super(filename,w,h);
			x+=100;
			y=20;
	}
		
	public String toString() {
			return x + " " + y;
	}
	
	public void move() {
		super.move();
	}
	
}
