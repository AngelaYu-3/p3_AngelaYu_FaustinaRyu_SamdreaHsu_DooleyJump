

public class Dooley extends Character{
	private int currY = getY();
	private boolean isUp = true;

	public Dooley(String fileName, int width, int height, int x, int y, int vx, int vy) {
		super(fileName, width, height, x, y, vx, vy);
		// TODO Auto-generated constructor stub
	}
	
	public void bounce(int height, int vy) {
		if(isUp) {
			setvy(-vy);
			if(getY() <= currY - height) isUp = false;
		}
		if(!isUp) {
			setvy(vy);
			if(getY() >= currY) isUp = true;
		}
	}
	
	public void fall() {
		setvy(4);
	}
}