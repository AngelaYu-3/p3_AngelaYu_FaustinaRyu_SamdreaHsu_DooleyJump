import java.awt.event.KeyEvent;

public class Dooley extends Character{

	public Dooley(String fileName, int width, int height, int x, int y) {
		super(fileName, width, height, x, y);
		// TODO Auto-generated constructor stub
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
