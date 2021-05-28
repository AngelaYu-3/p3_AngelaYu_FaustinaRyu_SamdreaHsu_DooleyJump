
public class Vines extends Platform {
	
	public Vines() {
		super("/Graphics/vine.png");
	}
	
	
	/*
	 * The result of stepping on vines is that
	 * 1) Dooley jumps super high
	 * 2) The vine goes off screen, so do some of the other platforms above it
	 * 3) the method returns how much all characters need to move by
	 */
	public int result(Dooley d) {
		d.bounce(10);
		return WINDOW_HEIGHT - this.y + 20;
	}
}
