public class Vines extends Platform {
/**
 * 	vine platform extended from Platform
 */
	public Vines(int x, int y, int vx, int vy) {
		super("/Graphics/vine.png", x, y, vx, vy);
	}
	

	/**
	 * vines disappear after 1 bounce
	 */
	public boolean checkPlat(Dooley d) {
		if((isSteppedOn(d) || (!isSteppedOn(d) && d.getY() - 55 < y + 26 
				&& (d.getX() + 10 > x + 14 && d.getX() + 10 < x + WIDTH - 20))) && d.getNumBounces() < 2) {
			result(d);
			return false;
		}
		else{
			d.fall();
			return true;
		}
	}
}