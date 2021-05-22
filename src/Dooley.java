import java.awt.event.KeyEvent;

public class Dooley extends Character{
	private int currY = getY();
	private boolean isUp = true;

	public Dooley(String fileName, int width, int height, int x, int y, int vx, int vy) {
		super(fileName, width, height, x, y, vx, vy);
		// TODO Auto-generated constructor stub
	}
	
	public void bounce(int height) {
		if(isUp) {
			setvy(-1);
			if(getY() <= currY - height) isUp = false;
		}
		if(!isUp) {
			setvy(1);
			if(getY() >= currY) isUp = true;
		}
	}
	
	public void keyPressed(KeyEvent e) {
	    switch(e.getKeyChar()) {
	    
	    case 'w':
	    	super.hop(0, -50);
	    	break;
	    
	    case 's':
	    	super.hop(0, 50);
    	    break;
    	    
	    case 'a':
	    	super.hop(-50, 0);
    	    break;
    	    
	    case 'd':
	    	super.hop(50, 0);
    	    break;
	    }
	}

}