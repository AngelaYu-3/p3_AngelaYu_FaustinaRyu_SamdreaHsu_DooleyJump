public class Bones extends Platform{

	public Bones(int x, int y) {
		super("/Graphics/bone.png", x, y);
	}
	
	/*
	 * The result of stepping on a bone is that
	 * 1) Dooley bounces 
	 * 2) the bone breaks
	 * 3) all objects on screen don't move
	 */
	/*public int result(Dooley d) {
		d.bounce(100, 5);
		img = getImage("/Graphics/bone1.png");
		img = img.getScaledInstance(WIDTH, HEIGHT, img.SCALE_SMOOTH);
		init(x, y);
		return 0;
	}*/
}