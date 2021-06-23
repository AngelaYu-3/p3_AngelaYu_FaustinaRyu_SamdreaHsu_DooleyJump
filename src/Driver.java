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
import java.text.DecimalFormat;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

/**
 * Driver: from which program is run from
 */
public class Driver extends JPanel implements ActionListener, KeyListener, MouseListener{
   
	private boolean isStart, isDead, isUp, isLeft, isRight, platDiff, shiftDown, isTranslate, isWinner;
	private JFrame f;
	private int mx, my, di, pi, x, y, xr, sy, sx, sm, px, py, pc, plat, score, lowest;
	private int numPeas = 10;
	private int numPlat = 17;
	
	private Background bg[] = new Background[2]; 
	private Background[] scroll = new Background[2]; 
	//private ArrayList<Enemies> enemy = new ArrayList<Enemies>();
	private Enemies finalBoss = new Enemies("Graphics/enemy1.png", 150, 150, 100, 200, 4, 0);
    private Dooley[] dooley = new Dooley[5]; 
    private Pea[] p = new Pea[numPeas];
    private Platform[] platforms = new Platform[numPlat];
    private Platform dummy = new Platform(300, 300);
    private Music[] shuffler = new Music[3];
    private Music[] soundEffects = new Music[3];
    private Jetpack j;
    private ArrayList<Platform> pl = new ArrayList<Platform>();
    
    private Font font = new Font("Courier New", 1, 25);
    private Timer t;
    private double currTime = (System.currentTimeMillis()/1000.0);
    private double value;

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
			if(plat == 16 || plat == 17) finalBoss.paint(g);
			finalBoss.move(finalBoss);
			isWinner = finalBoss.enemiesShot(finalBoss, p);
			if(isWinner) isDead = true;
			g.setFont(font);
			g.setColor(Color.black);
			value = (System.currentTimeMillis()/1000.0) - currTime;
			value = value * Math.pow(10, 3);
		    value = Math.floor(value);
		    value = value / Math.pow(10, 3);
			g.drawString("Time: " + value, 5, 20);	
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
			

/**
 * REPLAY BUTTON TEST CODE--TESTED
 */     
			if(isLeft && !isDead) {
				dooley[di].setvx(-1);
				dooley[di].setvy(-1);
				translate(-70);
				if(dooley[di].getX() - sx <= -70) isTranslate = true;
			}
		    if(isRight && !isDead) {
		    	dooley[di].setvx(1);
		    	dooley[di].setvy(-1);
		    	translate(70);
		    	if(dooley[di].getX() - sx >= 70) isTranslate = true;
		    }

		    for(int i = 0; i < numPlat; i++) {
		    	if(platforms[i].getPaint()) platforms[i].paint(g);
		    	if(platforms[i].isSteppedOn(dooley[di])) {
		    		if(i - plat < -2) isDead = true;
		    		if(i != plat) platDiff = true;
		    		else platDiff = false;
		    		plat = i;
		    	}
		    }

		    if(platforms[plat].checkPlat(dooley[di], platforms[plat], platDiff, plat) && isTranslate) {
	    		dooley[di].setvy(8);	    		
	    	}else {

	    		if(platDiff) {
	    			shiftDown = true;
	    			for(int i = 0; i < numPlat; i++) {
	    		    	platforms[i].setShift(true);
	    		    }
				}
	    		
	    		if(shiftDown) {
	    			for(int i = 0; i < numPlat; i++) {
	    				if(platforms[i].getY() < platforms[i].getYi() + (600-platforms[plat].getYi())) {
	    					platforms[i].setVy(5);
	    				}
	    				else{
	    					platforms[i].setShift(false);
	    					platforms[i].setVy(0);
	    				}
	    			}
	    			shiftDown = platforms[0].endShift(platforms, numPlat);
	    			scroll(600-platforms[plat].getYi(), 5);
	    			score += (int)((600-platforms[plat].getYi())/10);
	    		}
	    		dooley[di].bounce(76, 5);
	    		isTranslate = false;
	    	}
		    
		    if(scroll[0].getY() >= 800) scroll[0].setY(-800);
			if(scroll[1].getY() >= 800) scroll[1].setY(-800);
		    
		    if(dooley[di].getY() > 600) {
		    	soundEffects[1].play(0.0f);
		    	if(dooley[di].getY() > 820) isDead = true;
		    }

		    for(int i = 1; i < 6; i++) {
		        g.drawLine(i * 100, 0, i * 100, 800);
		    }
		    
		    for(int i = 1; i < 8; i++) {
		    	g.drawLine(0, i*100, 800, i*100);
		    }
		}   

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
		if(isDead || isWinner) {
			bg[1].paint(g);
			bg[1].endScreen(g, value);
			dooley[3].paint(g);
			dooley[3].bounce(25, 1);
			
			//replay button
			if(mx < 400 && mx > 200 && my > 300 && my < 380) {	
				//score = 0;
				isDead = false;
				isStart = false;
				di = 0;
				dooley[di].setX(300);
				dooley[di].setY(567);
				currTime = (int)(System.currentTimeMillis()/1000);
				soundEffects[1] = new Music("fall.wav", false);
				for(int i = 0; i < 10; i++) {
			        p[i] = new Pea("/Graphics/Pea.png", 38, 38, px, py, 0, -10);
			    }
				
				resetPath();
				scroll[0].setY(0);
				scroll[1].setY(-800);
				shiftDown = false;
				platDiff = false;
				isTranslate = false;
				plat = 0;
				mx = 0;
				my = 0;
				isWinner = false;
				finalBoss = new Enemies("Graphics/enemy1.png", 150, 150, 100, 100, 3, 0);
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
        platforms = platforms[0].path();
        pl.add(new Platform(xr, lowest + (510-475)));
        pl.add(new Platform(xr, lowest + (510-475-100)));
      
    	//j = new Jetpack(platforms[3].jetX(), platforms[3].jetY(), 0, 0);
        
        shuffler[0] = new Music("BlindingLights.wav",false);
        shuffler[1] = new Music("Butter.wav",false);
        shuffler[2] = new Music("NGU.wav",false);
        
       	scroll[0] = new Background("/Graphics/background1.png", 0, 0, 600, 800);
        scroll[1] = new Background("/Graphics/background1.png", -800, 0, 600, 800);
       
        dooley[0] = new Dooley("/Graphics/dooleyLeft.png", 65, 65, 300, 567, 0, 0);
        dooley[1] = new Dooley("/Graphics/dooleyRight.png", 65, 65, 300, 567, 0, 0);
        dooley[2] = new Dooley("/Graphics/dooleyUp.png", 65, 65, 300, 567, 0, 0);
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


	/**
	 * user (keys) game interaction
	 */
	@Override
	public void keyPressed(KeyEvent e) {
	    if(!isStart){
      	    switch(e.getKeyChar()) {
	    
	    case 'w':
	    	resetPos(2);
			pc = 1;
	    	break;
    	
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
    	y = platforms[plat].getY() - 32;
    	this.di = di;
    	
    	if(di == 2) y -= 30;
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
  
  public void resetPath() {
  	for(int i = 0; i < numPlat; i++) {
  		platforms[i].setY(platforms[i].getYi());
  		platforms[i].setShift(true);
  	}
  }

}