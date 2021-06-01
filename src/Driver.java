import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;
import java.util.concurrent.TimeUnit;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Driver extends JPanel implements ActionListener, KeyListener, MouseListener{
  
	private boolean isStart, isDead, isBeginning, paintPea, isUp;
	private Background bg; 
	private JFrame f;
	private int mx, my, di, x, y, sy, px, py;
	private Background[] scroll = new Background[2]; 
    private Enemies[] enemies = new Enemies[3];   
    private Dooley[] dooley = new Dooley[3];
    private Pea[] p = new Pea[4];
    Pea p1 = new Pea("/Graphics/Pea.png", 38, 38, 100, 100, 0, 0);

	public void paint(Graphics g) {
		super.paintComponent(g);

		//playscreen
		if(!isStart) {
			scroll[0].paint(g);
			scroll[1].paint(g);
			
			//enemies[0].paint(g);
			//enemies[1].paint(g);
			//enemies[2].paint(g);
			
		    dooley[di].paint(g);
		    dooley[di].setvy(0);
		    
		    if(isUp) scroll(50);  	
		    
		    if(isUp) {
		    	int pi = 0;
		    	for(int i = 0; i < 4; i++) {
		    		if(!p[i].getMoving()) {
		    			pi = i;
		    			p[pi].setMoving(true);
		    			break;
		    		}else continue;
		    	}
		    	System.out.println(pi);
		    	System.out.println(p[pi].getMoving());
		    	p[pi].paint(g);
		    	//p1 = new Pea("/Graphics/Pea.png", 38, 38, 100, 100, 0, 0);
		    	//p1.paint(g);
		    	//paintPea = false;
		    } //p[0].resetP(p);
		    
		 
		}
		
		//startscreen
		if(isStart) {
			bg.paint(g);
			bg.startScreen(g);
			dooley[di].paint(g);
			dooley[di].bounce(25);
		}		
		if(isStart && mx < 400 && mx > 200 && my > 300 && my < 380) {
			isStart = false;
		}
		
		//endscreen
		if(isDead) {
			bg.endScreen(g);
			
			if(mx < 400 && mx > 200 && my > 300 && my < 380) {
				isDead = false;
			}
			if(mx < 400 && mx > 200 && my > 370 && my < 450) {
				System.exit(1);
			}
		}
		
	}	
	
	public int findPi(Pea[] p) {
		int pi = -1;
    	for(int i = 0; i < 4; i++) {
    		if(p[i].getMoving() == false && pi == -1) {
    			System.out.println("hi");
    			pi = i;
    			p[pi].setMoving(true);
    			
    		}
    	}
    	return pi;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		repaint();
	}

	public static void main(String[] arg) {
		new Driver();
	}

	public Driver() {
        bg = new Background("/Graphics/background.png", 0, 0, 600, 800);
       	scroll[0] = new Background("/Graphics/background1.png", 0, 0, 600, 800);
        scroll[1] = new Background("/Graphics/background1.png", -800, 0, 600, 800);
        
        enemies[0] = new Enemies("/Graphics/Enemy1.png", 60, 60, 50, 50, 0, 1);
        enemies[1] = new Enemies("/Graphics/Enemy2.png", 60, 60, 100, 50, 0, 1);
        enemies[2] = new Enemies("/Graphics/Enemy3.png", 60, 60, 150, 50, 0, 1);
        
        dooley[0] = new Dooley("/Graphics/dooleyLeft.png", 65, 65, 350, 247, 0, 0);
        dooley[1] = new Dooley("/Graphics/dooleyRight.png", 65, 65, 350, 247, 0, 0);
        dooley[2] = new Dooley("/Graphics/dooleyUp.png", 65, 65, 350, 247, 0, 0);
        
        f = new JFrame();
		isStart = true;
		isBeginning = true;
		di = 0;
		px = dooley[di].getX() + 17;
		py = dooley[di].getY() - 20;
        
        for(int i = 0; i < 4; i++) {
        	p[i] = new Pea("/Graphics/Pea.png", 38, 38, px, py, 0, -5);
        }
        
	    f.setTitle("DooleyJump!");
		f.setSize(600, 800);
		f.setResizable(false);
		f.addKeyListener(this);
		f.addMouseListener(this);

		f.add(this);
		t = new Timer(17, this);
		t.start();
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setVisible(true);	
	}

	Timer t;
	
	public void resetPos(int di) {
		x = dooley[this.di].getX();
    	y = dooley[this.di].getY();
    	this.di = di;
    	dooley[di].setX(x);
    	dooley[di].setY(y);
	}
	
	public void scroll(int y) {		
		scroll[0].setvy(2);
		scroll[1].setvy(2);
			
		if(scroll[0].getY() <= sy + y) {
			scroll[0].scroll();
			scroll[1].scroll();
		}else {
			scroll[0].setvy(0);
			scroll[1].setvy(0);
			resetPos(0);
			isUp = false;
		}
	}

	@Override
	public void keyPressed(KeyEvent e) {
	    if(!isStart){
      	    switch(e.getKeyChar()) {
	    
	    case 'w':
	    	resetPos(2);
	    	if(isBeginning) isBeginning = false;
	  		if(scroll[0].getY() >= 800) scroll[0].setY(-800);
			if(scroll[1].getY() >= 800) scroll[1].setY(-800);
	  		resetPos(2);
	  		sy = scroll[0].getY();
			isUp = true;
	    	break;
    	    
	    case 'a':
	    	resetPos(0);
	    	dooley[di].hop(-50, 0);
    	    break;
    	    
	    case 'd':
	    	resetPos(1);
	    	dooley[di].hop(50, 0);
    	    break;
	    }
      	    
	    }
	}
		

	@Override
	public void keyReleased(KeyEvent e) {
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		mx = e.getX();
		my = e.getY();
		//System.out.println(mx + " " + my);
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

}

