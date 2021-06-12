import java.awt.Image;

public class Bones extends Platform{
	
	public Bones(String fileName, int x, int y, int vx, int vy) {
		super(fileName, x, y, vx, vy);
	}
	
	public Bones() {
		super("/Graphics/bone.png", 0, 0, 0, 0);
	}
	/*
	 * The result of stepping on a bone is that
	 * 1) Dooley bounces 
	 * 2) the bone breaks
	 * 3) all objects on screen don't move
	 */
	
	public boolean checkPlat(Dooley d) {
		if((isSteppedOn(d) || (!isSteppedOn(d) && d.getY() - 55 < y + 26 
				&& (d.getX() + 10 > x + 14 && d.getX() + 10 < x + WIDTH - 20))) && d.getNumBounces() < 3) {
			result(d);
			return false;
		}
		else{
			d.fall();
			return true;
		}
	}
}