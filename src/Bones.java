import java.awt.Image;

/**
 * Bones class
 */
public class Bones extends Platform{
	
	public Bones(String fileName, int x, int y, int vx, int vy) {
		super(fileName, x, y, vx, vy);
	}
	
	/**
	 * checking when Dooley should bounce on bone
	 * and when Dooley should fall off bone (after 3 bounces)
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