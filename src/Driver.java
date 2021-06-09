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
    private Enemies[] enemies = new Enemies[3];  
    private Dooley[] dooley = new Dooley[5]; 
    private Pea[] p = new Pea[numPeas];
    private Platform[] p1 = new Platform[4];
    private Music[] shuffler = new Music[3];
    private Jetpack j;
    
    private Font font = new Font("Courier New", 1, 25);
    private Timer t;

	public void paint(Graphics g) {
		super.paintComponent(g);

	//PLAYSCREEN
		if(!isStart) {
			shuffler[sm].play();
			scroll[0].paint(g);
			scroll[1].paint(g);
			
			g.setFont(font);
			g.setColor(Color.black);
			g.drawString("Score: " + score, 5, 20);
			
			enemies[0].paint(g);
			enemies[1].paint(g);
			enemies[2].paint(g);

			
			
			p1[3].paint(g);
			j.paint(g);

			//bone logic still needs to be integrated
		    p1[pi].paint(g);
		    isDead = p1[pi].checkPlat(dooley[di]);
		    if(isDead && dooley[di].getNumBounces() == 3) pi = 1;
			
		    //vine logic still needs to be integrated
			//if(!p1[2].checkPlat(dooley[di])) p1[2].paint(g);
			//else isDead = true;
		    
		    dooley[di].paint(g);
		    dooley[di].setvy(0);
		    
		    //moving background
		    if(isUp) scroll(50, 5);
		    if(isLeft) translate(-60);
		    if(isRight) translate(60);
		    
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
			dooley[di].bounce(25, 1);
		}		
		if(isStart && mx < 400 && mx > 200 && my > 300 && my < 380) {
			isStart = false;
			di = 0;
		}
		
	//ENDSCREEN
		if(isDead) {
			bg.endScreen(g);
			
			if(mx < 400 && mx > 200 && my > 300 && my < 380) {
				isDead = false;
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
		sm = (int)(Math.random()*3);
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
        
        enemies[0] = new Enemies("/Graphics/Enemy1.png", 60, 60, 50, 50, 0, 1);
        enemies[1] = new Enemies("/Graphics/Enemy2.png", 60, 60, 100, 50, 0, 1);
        enemies[2] = new Enemies("/Graphics/Enemy3.png", 60, 60, 150, 50, 0, 1);
        
        dooley[0] = new Dooley("/Graphics/dooleyLeft.png", 65, 65, 247, 475, 0, 0);
        dooley[1] = new Dooley("/Graphics/dooleyRight.png", 65, 65, 350, 247, 0, 0);
        dooley[2] = new Dooley("/Graphics/dooleyUp.png", 65, 65, 350, 247, 0, 0);
        dooley[3] = new Dooley("/Graphics/dooleyLeft.png",65, 65, 350, 247, 0, 0);
        dooley[4] = new Dooley("/Graphics/dooleyjetLeft.png", 80, 90, 350, 300, 0,0);
        
        f = new JFrame();
		isStart = true;
		di = 3;
		pc = 0;
		pi = 0;
		score = 0;
		px = dooley[di].getX() + 17;
		py = dooley[di].getY() - 20;
        
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

