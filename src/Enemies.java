
public class Enemies extends Character {
	
	int x;
	int y;
	int width; 
	int height;
	private int xv;
	
	//hello
			
	// default constructor, sets all to zero
	public Enemies(int x, int y, int w, int h) {
			super(x,y,w,h);
			xv=1;
	}
		
	public String toString() {
			return x + " " + y;
	}
	
	public void move() {
			changeX(xv);
	}
				
		
}
