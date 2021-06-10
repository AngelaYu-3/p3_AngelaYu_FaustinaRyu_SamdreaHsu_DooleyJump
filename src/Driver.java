import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Driver extends JPanel implements ActionListener, KeyListener, MouseListener{
  
	private boolean isStart, isDead, isUp;
	private Background bg; 
	private JFrame f;
	private int mx, my, di, x, y, sy, px, py, pc;
	private Background[] scroll = new Background[2]; 
        private Enemies[] enemies = new Enemies[3];
        	private ArrayList<Enemies> enemy = new ArrayList<Enemies>();
        	private int count;
        	private boolean dead;
        private Dooley[] dooley = new Dooley[3];
        private Pea[] p = new Pea[4];
        
        
       
        
        //use awsd keys to move dooley once game starts

	public void paint(Graphics g) {
		super.paintComponent(g);

	//PLAYSCREEN
		if(!isStart) {
			
			
			scroll[0].paint(g);
			scroll[1].paint(g);
			
		  dooley[di].paint(g);
		  dooley[di].setvy(0);
		    dooley[di].paint(g);
		    dooley[di].setvy(0);
		    
		    //moving background
		    if(isUp) {
		    	scroll(50);  
		    
		    }
		    
		    //shooting
		    if(pc == 1) {
		    	p[0].newShot(g, p);
		    	pc = 0;
		    }
		    p[0].shoot(g, p, dooley[di]);
		    p[0].reset(p);
		    
		    //left right respawning
		    if(dooley[di].getX() <= 0) dooley[di].setX(535);
		    if(dooley[di].getX() >= 600) dooley[di].setX(5);
		    
		}
		
	//STARTSCREEN
		if(isStart) {
			bg.paint(g);
			bg.startScreen(g);
			dooley[di].paint(g);
			dooley[di].bounce(25);
		}		
		if(isStart && mx < 400 && mx > 200 && my > 300 && my < 380) {
			isStart = false;
		}
		
		//spawn enemy based on dooley position
		for (int i = 0; i < enemy.size(); i++) {
			if (!isStart && enemy.get(i).getY()<300) {
				enemy.get(i).paint(g);
				
			}
		}
		
		//move back and forth
		for (int i = 0; i < enemy.size(); i++) {
			if (enemy.get(i).getX()<0 || enemy.get(i).getX()>530) {
				System.out.println("overbounds");
				enemy.get(i).setvx(enemy.get(i).getvx()*-1);
			}
				
		}
		
		
		//enemies die after shot 3 times
		for (int i = 0; i < enemy.size(); i++) {
			for (int j = 0; j < p.length; j++) {
				if (enemy.get(i).isColliding(p[j])) {
					count++;
				}
					if (count == 3) {
						enemy.get(i).setvx(0);
						enemy.remove(i);
						System.out.println("enemies die");
						enemy.get(i).reset(enemy);
						count = 0;
					}
					
				
			}
		}
		
		//dooley dies
		for (int i = 0; i < enemy.size(); i++) {
			if (enemy.get(i).isColliding(dooley[di])) {
				enemy.get(i).setvx(0);
				System.out.println("dooley die");
				isDead = true;
			}
		}
		
	
		
		
		
	//ENDSCREEN
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
        
        	enemy = new ArrayList<Enemies>();
        	enemy.add(new Enemies("/Graphics/Enemy1.png", 65, 65, (int)(Math.random()*(500)+100),(int)(Math.random()*(400)-100), 1, 0));
        	enemy.add(new Enemies("/Graphics/Enemy2.png", 65, 65, (int)(Math.random()*(500)+100),(int)(Math.random()*(400)-100), 1, 0));
        	enemy.add(new Enemies("/Graphics/Enemy3.png", 65, 65, (int)(Math.random()*(500)+100),(int)(Math.random()*(400)-100), 1, 0));
        	dead = false;
        	count = 0;
        
        dooley[0] = new Dooley("/Graphics/dooleyLeft.png", 65, 65, 350, 247, 0, 0);        
        dooley[1] = new Dooley("/Graphics/dooleyRight.png", 65, 65, 350, 247, 0, 0);
        dooley[2] = new Dooley("/Graphics/dooleyUp.png", 65, 65, 350, 247, 0, 0);
        
        
        
        
        f = new JFrame();
		isStart = true;

		di = 0;
		pc = 0;
		px = dooley[di].getX() + 17;
		py = dooley[di].getY() - 20;
        
		
        for(int i = 0; i < 4; i++) {
        	p[i] = new Pea("/Graphics/Pea.png", 38, 38, px, py, 0, -10);
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
		scroll[0].setvy(5);
		scroll[1].setvy(5);
			
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
	  		if(scroll[0].getY() >= 800) scroll[0].setY(-800);
			if(scroll[1].getY() >= 800) scroll[1].setY(-800);
	  		sy = scroll[0].getY();
			isUp = true;
			pc = 1;
	    	break;
    	
	    //add horizontal movement here look at logic for up movement
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
		
		
		/*
		 * turn off velocity for dooley if you don't want it moving when you have stopped
		 * pressing the keys
		 */

		// do the same thing for the other keys

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