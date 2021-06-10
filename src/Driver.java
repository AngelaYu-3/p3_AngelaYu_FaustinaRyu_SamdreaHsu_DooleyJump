import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Rectangle;
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
	private int mx, my, di, x, y, sy, px, py, pc, score;
	private Background[] scroll = new Background[2]; 
    private Enemies[] enemies = new Enemies[3];   
    private Dooley[] dooley = new Dooley[3];
    private int numPeas = 10;
    private Pea[] p = new Pea[numPeas];
    private Platform p1 = new Bones();
    private Font font = new Font("Courier New", 1, 25);
    
    //BTS (butter), Never Gonna Give You Up, Taylor Swift
    private Music playMusic;

    private ArrayList<Platform> platforms = new ArrayList<Platform>();
    private int numPlatforms = 5;
    private boolean isMoving;
    
	//use awsd keys to move dooley once game starts
	public void paint(Graphics g) {

	//TESTING
		super.paintComponent(g);
	
	//PLAYSCREEN
		if(!isStart) {
			playMusic.play();
			
			scroll[0].paint(g);
			scroll[1].paint(g);
			
			g.setFont(font);
			g.setColor(Color.black);
			g.drawString("Score: " + score, 5, 20);
			
			enemies[0].paint(g);
			enemies[1].paint(g);
			enemies[2].paint(g);
			dooley[di].paint(g);
			dooley[di].setvy(0);
			

			for(Platform p: platforms) {
				p.paint(g);
				if(p.isSteppedOn(dooley[di])) {
					for(Platform i: platforms) {
						i.shiftDown(600, 5);
						scroll(600, 5);
						}
					
				}
				
				if(p.isShifting()) {
					p.shiftDown(600, 5);
					scroll(600, 5);
				} 
				 
			}
			
			
		    dooley[di].paint(g);
		    dooley[di].setvy(0);
		    
		    p1.paint(g);
		    //System.out.println(p1.isSteppedOn(dooley[di]));
		    
		    //moving background
		    if(isUp) {
		    	scroll(50, 5);  
		    }
		    
		    //shooting
		    if(pc == 1) {
		    	p[0].newShot(g, p, numPeas);
		    	pc = 0;
		    }
		    p[0].shoot(g, p, dooley[di], numPeas);
		    reset();
		    
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
	
	public void reset() {
		for(int i = 0; i < numPeas; i++) {
	    	if(p[i].getMoving() && p[i].getY() < 0) {
	    		p[i].reset();
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
		playMusic = new Music("Butter.wav", true);
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
		di = 0;
		pc = 0;
		score = 0;
		px = dooley[di].getX() + 17;
		py = dooley[di].getY() - 20;
        
        //Initialize platforms
        for(int i = 0; i < numPlatforms; i++) {
        	// TODO: have some logic that will give 20% chance of vine,
        	//		20% chance of broken, and 60% chance of normal
        	platforms.add(0, new Platform());  
        	
        	int moreRand = (int)(Math.random() * 20);
        	platforms.get(0).setX(platforms.get(0).getX() + moreRand);
			platforms.get(0).setY(platforms.get(0).getY() + moreRand);
        	
        	boolean touching = true;
        	while(touching) {
        		touching = false;
        		for(int j = 0; j < platforms.size(); j++) {
        			if (j != 0 && platforms.get(0).tooClose(platforms.get(j))) {
            			touching = true;
            			
            			// stagger the platforms if they intersect
            			int fix = 10;
            			if ( platforms.get(0).getX() < platforms.get(j).getX()) {
            				// stagger to the left
            				platforms.get(0).setX(platforms.get(0).getX() - fix);
            				platforms.get(0).setY(platforms.get(0).getY() + fix);
            			} else {
            				// stagger to the right
            				platforms.get(0).setX(platforms.get(0).getX() + fix);
            				platforms.get(0).setY(platforms.get(0).getY() + fix);
            			}

        			}
        		}
        	}
        	
        	
        	
        }
		
        
        for(int i = 0; i < 10; i++) {
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
	
	public void scroll(int y, int vy) {		
		scroll[0].setvy(vy);
		scroll[1].setvy(vy);
			
		if(scroll[0].getY() <= sy + y) {
			scroll[0].scroll();
			scroll[1].scroll();
			if(scroll[0].getY() >= 800) scroll[0].setY(-800);
			if(scroll[1].getY() >= 800) scroll[1].setY(-800);
		}else {
			score += 50;
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

