import java.awt.Color;
import java.awt.Font;
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
  

	private boolean isStart, isDead, isUp, isLeft, isRight;
	private JFrame f;
	private int mx, my, di, pi, x, y, sy, sx, sm, px, py, pc, score;
	private int numPeas = 10;
	
	private Background bg; 
	private Background[] scroll = new Background[2]; 
	private ArrayList<Enemies> enemy = new ArrayList<Enemies>();
    private Dooley[] dooley = new Dooley[5]; 
    private Pea[] p = new Pea[numPeas];
    private Platform[] p1 = new Platform[4];
    private Music[] shuffler = new Music[3];
    private Music[] soundEffects = new Music[3];
    private Jetpack j;
    
    private Font font = new Font("Courier New", 1, 25);
    private Timer t;
    
    private ArrayList<Platform> platforms = new ArrayList<Platform>();
    private int numPlatforms = 10;

	public void paint(Graphics g) {
	//TESTING
		super.paintComponent(g);
	
	//PLAYSCREEN
		if(!isStart) {
			//shuffler[sm].play(-20.0f);
			scroll[0].paint(g);
			scroll[1].paint(g);
			
			g.setFont(font);
			g.setColor(Color.black);
			g.drawString("Score: " + score, 5, 20);
			
			
			/*
			 * The checkPlat seems to return true immediately.. 
			 * so my game dies immediately
			 * 
			p1[3].paint(g);
			j.paint(g);
			//bone + vine logic --> still needs to be integrated
		    p1[pi].paint(g);
		    //isDead = p1[pi].checkPlat(dooley[di]);
		    if(isDead && dooley[di].getNumBounces() == 3) pi = 1;
			//if(!p1[2].checkPlat(dooley[di])) p1[2].paint(g);
			//else isDead = true;
			 * 
			 */
			
			for(Platform p: platforms) {
				p.paint(g);
					for (Enemies e: enemy) {
					if(p.isSteppedOn(dooley[di])) {
						for(Platform i: platforms) {
							i.shiftDown(600, 5);
							e.shiftDown(600, 5);
							e.moveDown(3);
							scroll(600, 5);
						}
					}
					if(p.isShifting()) {
						p.shiftDown(600, 5);
						e.shiftDown(600, 5);
						scroll(600, 5);
					} 
				}
			}
		    
		    dooley[di].paint(g);
		    dooley[di].setvy(0);
		    
		    //moving background
		   
		    if(isUp) scroll(50, 5);
		    if(isLeft && !isDead) translate(-60);
		    if(isRight && !isDead) translate(60);
		    
		    //shooting
		    if(pc == 1) {
		    	p[0].newShot(g, p, numPeas);
		    	pc = 0;
		    	soundEffects[0].play(-10.0f);
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
			dooley[di].bounce(25, 1);
		}		
		
		if(isStart && mx < 400 && mx > 200 && my > 300 && my < 380) {
			isStart = false;
		}
		
		//make sure enemy doesn't spawn on dooley
		for (int i = 0; i < enemy.size(); i++) {
			if (!isStart) {
				enemy.get(i).paint(g);
			}
		}
		
		//move back and forth
		for (int i = 0; i < enemy.size(); i++) {
			if (enemy.get(i).getX()<0 || enemy.get(i).getX()>510) {
//				System.out.println("overbounds");
				enemy.get(i).setvx(enemy.get(i).getvx()*(-1));
			} 
		}
		
		//enemies die after getting shot
		for (int i = enemy.size()-1; i >= 0; i--) {
			for (int j = 0; j < p.length; j++) {
				if (enemy.get(i).isColliding(p[j])) {
					enemy.get(i).setvx(0);
					enemy.remove(i);
					break;
							
 				} 
			}
		}
		
		//dooley dies
		for (int i = 0; i < enemy.size(); i++) {
			if (enemy.get(i).isColliding(dooley[di])) {
				enemy.get(i).setvx(0);
//				System.out.println("dooley die");
				isDead = true;
			}
// 			di = 0;
		}
		
	
	//ENDSCREEN
		if(isDead) {
			bg.endScreen(g);
			soundEffects[1].play(-30.0f);
			
			if(mx < 400 && mx > 200 && my > 300 && my < 380) {
				isDead = false;
				dooley[di].setvx(0);
				dooley[di].setvy(3);
				repaint();
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
        p1[0] = new Bones("/Graphics/bone.png", 200, 510, 0, 0);
        p1[1] = new Bones("/Graphics/bone1.png", 200, 510, 0, 3);
        p1[2] = new Vines(195, 510, 0, 0);
        p1[3] = new Platform(100, 100);
    	j = new Jetpack(p1[3].jetX(), p1[3].jetY(), 0, 0);
        
        shuffler[0] = new Music("BlindingLights.wav",false);
        shuffler[1] = new Music("Butter.wav",false);
        shuffler[2] = new Music("NGU.wav",false);
        
       	scroll[0] = new Background("/Graphics/background1.png", 0, 0, 600, 800);
        scroll[1] = new Background("/Graphics/background1.png", -800, 0, 600, 800);
        
        enemy = new ArrayList<Enemies>();
        enemy.add(new Enemies("/Graphics/Enemy1.png", 65, 65, (int)(Math.random()*(500)),(int)(Math.random()*(150)), 3, 0));
        enemy.add(new Enemies("/Graphics/Enemy2.png", 65, 65, (int)(Math.random()*(500)),(int)(Math.random()*(150)), 3, 0));
        enemy.add(new Enemies("/Graphics/Enemy3.png", 65, 65, (int)(Math.random()*(500)),(int)(Math.random()*(150)), 3, 0));
        dooley[0] = new Dooley("/Graphics/dooleyLeft.png", 65, 65, 350, 247, 0, 0);        
       
//         dooley[0] = new Dooley("/Graphics/dooleyLeft.png", 65, 65, 247, 475, 0, 0);
        dooley[1] = new Dooley("/Graphics/dooleyRight.png", 65, 65, 350, 247, 0, 0);
        dooley[2] = new Dooley("/Graphics/dooleyUp.png", 65, 65, 350, 247, 0, 0);
        dooley[3] = new Dooley("/Graphics/dooleyLeft.png",65, 65, 350, 247, 0, 0);
        dooley[4] = new Dooley("/Graphics/dooleyjetLeft.png", 80, 90, 350, 300, 0,0);
 
        soundEffects[0] = new Music("shoot.wav", true);
        soundEffects[1] = new Music("fall.wav", false);
        soundEffects[2] = new Music("jetpack.wav", true);
        
        
        
        
        f = new JFrame();
		isStart = true;

		di = 3;
		pc = 0;
		pi = 0;
		score = 0;
		sm = (int)(Math.random()*3);
		px = dooley[di].getX() + 17;
		py = dooley[di].getY() - 20;
        

        for(int i = 0; i < 10; i++) {
        	p[i] = new Pea("/Graphics/Pea.png", 38, 38, px, py, 0, -10);
        }
        
      //Initialize platforms
        for(int i = 0; i < numPlatforms; i++) {
        	// TODO: have some logic that will give 20% chance of vine,
        	//		20% chance of broken, and 60% chance of normal
        	platforms.add(0, new Platform(0,0));  
        	
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

//HELPER METHODS	
	public void reset() {
		for(int i = 0; i < numPeas; i++) {
	    	if(p[i].getMoving() && p[i].getY() < 0) {
	    		p[i].reset();
	    	}
	    }
	}
	
	public void resetPos(int di) {
		x = dooley[this.di].getX();
    	y = dooley[this.di].getY();
    	this.di = di;
    	dooley[di].setX(x);
    	dooley[di].setY(y);
	}
	
// 	public void scroll(int y) {		
// 		scroll[0].setvy(5);
// 		scroll[1].setvy(5);
//   }
  
  public void translate(int x) {
		if(x < 0) dooley[di].setvx(-5);
		else dooley[di].setvx(5);

		if(Math.abs(sx - dooley[di].getX()) < Math.abs(x)) {
			dooley[di].move();
		}else {
			dooley[di].setvx(0);
			isLeft = false;
			isRight = false;
		}
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
	  		if(scroll[0].getY() >= 800) scroll[0].setY(-800);
			if(scroll[1].getY() >= 800) scroll[1].setY(-800);
                
	  		sy = scroll[0].getY();
			isUp = true;
			pc = 1;
	    	break;
    	
	    //add horizontal movement here look at logic for up movement
	    case 'a':
	    	if(!isDead) {
	    		resetPos(0);
			    sx = dooley[di].getX();
			    isLeft = true;
	    	}
    	    break;
    	    
	    case 'd':
	    	if(!isDead) {
	    		resetPos(1);
			    sx = dooley[di].getX();
			    isRight = true;
	    	}
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

import java.awt.Color;
import java.awt.Font;
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

/**
 * Driver: from which program is run from
 */
public class Driver extends JPanel implements ActionListener, KeyListener, MouseListener{
   
	private boolean isStart, isDead, isUp, isLeft, isRight, reset;
	private JFrame f;
	private int mx, my, di, pi, x, y, xr, sy, sx, sm, px, py, pc, score, lowest, highest;
	private int numPeas = 10;
	
	private Background bg[] = new Background[2]; 
	private Background[] scroll = new Background[2]; 
	private ArrayList<Enemies> enemy = new ArrayList<Enemies>();
    private Dooley[] dooley = new Dooley[5]; 
    private Pea[] p = new Pea[numPeas];
    private Platform[] platforms = new Platform[5];
    private ArrayList<Platform> p1 = new ArrayList<Platform>();
    private Music[] shuffler = new Music[3];
    private Music[] soundEffects = new Music[3];
    private Jetpack j;
    private ArrayList<Integer> scoreBoard = new ArrayList<Integer>();
    
    private Font font = new Font("Courier New", 1, 25);
    private Timer t;

    /**
     * painting on JFrame
     */
	public void paint(Graphics g) {
		super.paintComponent(g);	
	//PLAYSCREEN
		if(!isStart && !isDead) {
			shuffler[sm].play(-20.0f);
			scroll[0].paint(g);
			scroll[1].paint(g);
			g.setFont(font);
			g.setColor(Color.black);
			g.drawString("Score: " + score, 5, 20);	
			dooley[di].paint(g);
			
			//shooting
		    if(pc == 1) {
		    	p[0].newShot(g, p, numPeas, dooley[di].getX() + 17, dooley[di].getY() - 20);
		    	pc = 0;
		    	soundEffects[0].play(-10.0f);
		    }
		    p[0].shoot(g, p, dooley[di], numPeas);
		    p[0].reset(p, numPeas);
		    
		    //left right respawning
		    if(dooley[di].getX() <= 0) dooley[di].setX(535);
		    if(dooley[di].getX() >= 600) dooley[di].setX(5);
			
		    if(isLeft && !isDead) translate(-65);
		    if(isRight && !isDead) translate(65);
		    
/**
 * ENEMIES/SHOOTING ENEMIES TEST CODE--TESTED
 */
		   /* if(enemy.size() > 1) {
		    	enemy.get(0).noDooleySpawn(isStart, enemy, g);
				enemy.get(0).enemiesShot(enemy, p);
				isDead = enemy.get(0).dooleyEnemy(enemy, dooley[di], isDead);
				enemy.get(0).move(enemy);
		    }*/
		   
		    
/**
 * ROCKET PLATFORM TEST CODE--TESTED
 */
			//platforms[3].paint(g);
			//j.paint(g);

/**
 * REPLAY BUTTON TEST CODE--TESTED
 */         platforms[4].paint(g);
			if(platforms[4].checkPlat(dooley[di])) { //look into checkPlat for intial randomization & bounce method
			   soundEffects[1].play(0.0f);
			   if(dooley[di].getY() > 820) isDead = true;
			}
			
			
			
			for(int i = 0; i < p1.size(); i++) {
				p1.get(i).paint(g);
			}
			
			p1.get(0).paint(g);
			

/**
 * BONE + VINE LOGIC--TESTED
 */
		    /*platforms[pi].paint(g);
		    if(platforms[pi].checkPlat(dooley[di]) && dooley[di].getNumBounces() == 3) {
		    	pi = 1;
		    	if(dooley[di].getY() > 820) isDead = true;
		    }
			if(!platforms[2].checkPlat(dooley[di])) platforms[2].paint(g);
			else if(dooley[di].getY() > 820) isDead = true;*/
		    
/**
 * RANDOM PLATFORM--UNTESTED
 */
		    /*for(Platform p: platforms) {
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
			 
		    }*/
			
/**
 * UNNECESSARY
 */
			//moving background
		    //if(isUp) scroll(50, 5);	   
			 //if(isUp) scroll(50, 5);
		}
		
	//STARTSCREEN
		if(isStart) {
			bg[0].paint(g);
			bg[0].startScreen(g);
			dooley[di].paint(g);
			dooley[di].bounce(25, 1);
		}		
		if(isStart && mx < 400 && mx > 200 && my > 300 && my < 380) {
			isStart = false;
			di = 0;
			mx = 0;
			my = 0;
		}
			
	//ENDSCREEN
		if(isDead) {
			bg[1].paint(g);
			scoreBoard.add(score);
			bg[1].endScreen(g, scoreBoard);
			dooley[3].paint(g);
			dooley[3].bounce(25, 1);
			
			//replay button
			if(mx < 400 && mx > 200 && my > 300 && my < 380) {	
				xr = (int)(Math.random() * 500) + 5;
				score = 0;
				isDead = false;
				isStart = false;
				di = 0;
				platforms[4].setX(xr);
				dooley[di].setX(xr + 10);
				dooley[di].setY(lowest);
				soundEffects[1] = new Music("fall.wav", false);
				for(int i = 0; i < 10; i++) {
			        p[i] = new Pea("/Graphics/Pea.png", 38, 38, px, py, 0, -10);
			    }
				p1 = randGenerate(score);
				mx = 0;
				my = 0;
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

	/**
	 * Driver constructor, objects instantiated
	 */
	public Driver() {
		lowest = 600;
		xr = (int)(Math.random() * 500) + 5;
        bg[0] = new Background("/Graphics/background.png", 0, 0, 600, 800);
        bg[1] = new Background("/Graphics/background1.png", 0, 0, 600, 800);
        platforms[0] = new Bones("/Graphics/bone.png", 240, 510, 0, 0);
        platforms[1] = new Bones("/Graphics/bone1.png", 240, 510, 0, 3);
        platforms[2] = new Vines(240, 510, 0, 0);
        platforms[3] = new Platform(100, 100);
        platforms[4] = new Platform(xr, lowest + (510 - 475));
        p1 = randGenerate(score);
    	j = new Jetpack(platforms[3].jetX(), platforms[3].jetY(), 0, 0);
        
        shuffler[0] = new Music("BlindingLights.wav",false);
        shuffler[1] = new Music("Butter.wav",false);
        shuffler[2] = new Music("NGU.wav",false);
        
       	scroll[0] = new Background("/Graphics/background1.png", 0, 0, 600, 800);
        scroll[1] = new Background("/Graphics/background1.png", -800, 0, 600, 800);

        enemy = new ArrayList<Enemies>();
        enemy.add(new Enemies("/Graphics/Enemy1.png", 65, 65, (int)(Math.random()*(500)),(int)(Math.random()*(150)), 1, 0));
        enemy.add(new Enemies("/Graphics/Enemy2.png", 65, 65, (int)(Math.random()*(500)),(int)(Math.random()*(150)), 1, 0));
        enemy.add(new Enemies("/Graphics/Enemy3.png", 65, 65, (int)(Math.random()*(500)),(int)(Math.random()*(150)), 1, 0)); 
        enemy.add(new Enemies("/Graphics/Enemy3.png", 65, 65, (int)(Math.random()*(500)),(int)(Math.random()*(150)), 1, 0));
       
        dooley[0] = new Dooley("/Graphics/dooleyLeft.png", 65, 65, xr + 10, lowest, 0, 0);
        dooley[1] = new Dooley("/Graphics/dooleyRight.png", 65, 65, xr + 10, lowest, 0, 0);
        dooley[2] = new Dooley("/Graphics/dooleyUp.png", 65, 65, xr + 10, lowest, 0, 0);
        dooley[3] = new Dooley("/Graphics/dooleyLeft.png",65, 65, 350, 247, 0, 0);
        dooley[4] = new Dooley("/Graphics/dooleyjetLeft.png", 80, 90, 350, 300, 0,0);
 
        soundEffects[0] = new Music("shoot.wav", true);
        soundEffects[1] = new Music("fall.wav", false);
        soundEffects[2] = new Music("jetpack.wav", true);
        
        f = new JFrame();
		isStart = true;
		isDead = false;

		di = 3;
		pc = 0;
		pi = 0;
		score = 0;
		sm = (int)(Math.random()*3);
		px = dooley[0].getX() + 17;
		py = dooley[0].getY() - 20;
        

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

	public ArrayList<Platform> randGenerate(int score) {
		p1.clear();
		int numPlat = 0;
		
		if(score < 100) {
			numPlat = 20	;
			for(int i = 0; i < numPlat; i++) {
				p1.add(platforms[0].respawn(800));
				while(platforms[0].tooClose(p1.get(p1.size() - 1)) || platforms[0].sameRow(p1.get(p1.size()-1))) {
					System.out.println("hi");
					p1.remove(p1.size() - 1);
					p1.add(platforms[0].respawn(800));				
				}
				
			}
			//only platforms rand generated 20 platforms
			
		}
		else if(score < 300) {
			//enemies + jetpack
		}
		else if(score < 500) {
			//bones + vines
		}
		
		return p1;
	}

	/**
	 * user (keys) game interaction
	 */
	@Override
	public void keyPressed(KeyEvent e) {
	    if(!isStart){
      	    switch(e.getKeyChar()) {
	    
	    case 'w':
	    	resetPos(2);
	  		sy = scroll[0].getY();
	  		if(scroll[0].getY() >= 800) scroll[0].setY(-800);
			if(scroll[1].getY() >= 800) scroll[1].setY(-800);
                
	  		sy = scroll[0].getY();
			isUp = true;
			pc = 1;
	    	break;
    	
	    //add horizontal movement here look at logic for up movement
	    case 'a':
	    	resetPos(0);
	    	sx = dooley[di].getX();
	    	isLeft = true;
    	    break;
    	    
	    case 'd':
	    	resetPos(1);
	    	sx = dooley[di].getX();
	    	isRight = true;
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

	/**
	 * recording where on screen mouse clicks
	 */
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
	
	public void resetPos(int di) {
		x = dooley[this.di].getX();
    	y = dooley[this.di].getY();
    	this.di = di;
    	dooley[di].setX(x);
    	dooley[di].setY(y);
	}
	
  public void translate(int x) {
		if(x < 0) dooley[di].setvx(-5);
		else dooley[di].setvx(5);

		if(Math.abs(sx - dooley[di].getX()) < Math.abs(x)) {
			dooley[di].move();
		}else {
			dooley[di].setvx(0);
			isLeft = false;
			isRight = false;
		}
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

}