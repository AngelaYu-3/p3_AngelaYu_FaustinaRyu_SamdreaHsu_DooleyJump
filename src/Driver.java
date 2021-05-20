import java.awt.Color;
import java.awt.Font;
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
	private Background bg = new Background("/Graphics/background.png", 0, 0, 600, 800);
	private Background bg1 = new Background("/Graphics/background1.png", 0, 0, 600, 800);
	private Dooley dooley = new Dooley("/Graphics/dooleyLeft.png", 60, 60, 350, 247);
	private JFrame f = new JFrame();
	private Font f1 = new Font("Courier New", 1, 35);
	private Font f2 = new Font("Courier New", 1, 25);
	private int mx, my;
	
	public void paint(Graphics g) {

		super.paintComponent(g);
		
		//homescreen button
		if(!isStart) {
			bg1.paint(g);
		}
		if(isStart) {
			bg.paint(g);
			startScreen(g);
			dooley.paint(g);
		}		
		if(isStart && mx < 400 && mx > 200 && my > 300 && my < 380) {
			isStart = false;
		}
		
		
	
	}
	
	public void startScreen(Graphics g) {
		g.setFont(f1);
		g.setColor(Color.black);
		g.drawString("Welcome to Dooley Jump", 80, 250);
		
		g.setColor(Color.green);
		g.fillRect(200, 300, 200, 50);
		g.setFont(f2);
		g.setColor(Color.black);
		g.drawString("play", 270, 330);
	}

	public void update() {

	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		update();
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













