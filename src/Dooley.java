

public class Dooley extends Character{
	private int currY = getY();
	private boolean isUp = true;
	private int numBounces = 0;	

	public Dooley(String fileName, int width, int height, int x, int y, int vx, int vy) {
		super(fileName, width, height, x, y, vx, vy);
		// TODO Auto-generated constructor stub
	}
	
	public int bounce(int height, int vy) {
		if(isUp) {
			setvy(-vy);
			if(getY() <= currY - height) isUp = false;
		}
		if(!isUp) {
			setvy(vy);
			if(getY() >= currY) {
				isUp = true;
				numBounces++;
			}
			
		}
		
		return numBounces;
	}
	
	public void fall() {
		setvy(1);
	}
	
	public int getNumBounces() {
		return numBounces;
	}
	
	/*
	 * experimenting in progress...
	 * 
	public void bounce(int height) {
		if(!isUp) {
			setvy(-10);
			isUp = true;
			currY = getY();
		} else {
			if (getY() - currY >= height) {
				setvy(-10);
				isUp = false;
			}
			
		}
	}
	*/
}