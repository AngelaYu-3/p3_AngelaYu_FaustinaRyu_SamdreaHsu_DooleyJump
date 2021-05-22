import java.awt.Graphics;
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

	private boolean isStart = true;
	private boolean isDead = false;
	private Background bg = new Background("/Graphics/background.png", 0, 0, 600, 800);
	private Dooley dooley = new Dooley("/Graphics/dooleyLeft.png", 60, 60, 350, 247, 0, 0);
	private JFrame f = new JFrame();
	private int mx, my;
	private Background[] scroll = {new Background("/Graphics/background1.png", 0, 0, 600, 800), new Background("/Graphics/background1.png", -800, 0, 600, 800)};

	Enemies e1 = new Enemies("/Graphics/Enemy1.png", 60, 60, 50, 50, 0, 1);
	Enemies e2 = new Enemies("/Graphics/Enemy2.png", 60, 60, 100, 50, 0, 1);
	Enemies e3 = new Enemies("/Graphics/Enemy3.png", 60, 60, 150, 50, 0, 1);

	
	public void paint(Graphics g) {

		super.paintComponent(g);
		
		//playscreen
		if(!isStart) {
			//autoscroll --needs to be modified for dooley
			scroll[0].paint(g);
			scroll[1].paint(g);
			scroll[0].scroll();
			scroll[1].scroll();
			if(scroll[0].getY() >= 800) scroll[0].setY(-800);
			if(scroll[1].getY() >= 800) scroll[1].setY(-800);
			
			e1.paint(g);
		    e2.paint(g);
		    e3.paint(g);
		}
		
		//startscreen
		if(isStart) {
			bg.paint(g);
			bg.endScreen(g);
			dooley.paint(g);
			dooley.bounce(25);
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

	@Override
	public void actionPerformed(ActionEvent arg0) {
		repaint();
	}

	public static void main(String[] arg) {
		new Driver();
	}

	public Driver() {
		
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

	@Override
	public void keyPressed(KeyEvent e) {
		dooley.keyPressed(e);	
	}

	@Override
	public void keyReleased(KeyEvent e) {
		
		
		/*
		 * turn off velocity for Frog if you don't want it moving when you have stopped
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













