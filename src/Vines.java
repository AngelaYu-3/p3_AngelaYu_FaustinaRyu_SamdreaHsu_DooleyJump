public class Vines extends Platform {
	
	public Vines(int x, int y, int vx, int vy) {
		super("/Graphics/vine.png", x, y, vx, vy);
	}
	
	public Vines(int x, int y) {
		super("/Graphics/vine.png", x, y, 0, 0);
	}
	
	public Vines() {
		super("/Graphics/vine.png", 0, 0, 0, 0);
	}
	
	
	/*
	 * The result of stepping on vines is that
	 * 1) Dooley jumps super high
	 * 2) The vine goes off screen, so do some of the other platforms above it
	 * 3) the method returns how much all characters need to move by
	 */
	
	public void result(Dooley d) {
		d.bounce(100, 5);
	}
	
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