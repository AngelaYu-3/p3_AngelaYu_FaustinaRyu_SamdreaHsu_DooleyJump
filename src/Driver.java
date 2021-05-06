import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Driver extends JPanel implements ActionListener, KeyListener{

	private String state = "RUNNING1";
	Background bg;
	Dooley dooley = new Dooley("/Graphics/dooleyLeft.png", 60, 60);
	Font titleFont = new Font("Times New Roman", Font.PLAIN, 28);
	
	JFrame f;
	Container con;
	JPanel startPanel, startButtonPanel, mainTextPanel;
	JButton startButton;
	JLabel startName;
	ImagePanel testImg = new ImagePanel(new ImageIcon("/Graphics/background1.png").getImage());
	TitleScreenHandler tHandler = new TitleScreenHandler();
	
	public class ImagePanel extends JPanel{
		private Image img;
		
		public ImagePanel(String img) {
			this(new ImageIcon(img).getImage());
		}
		
		public ImagePanel(Image img) {
		    this.img = img;
		    Dimension size = new Dimension(img.getWidth(null), img.getHeight(null));
		    setPreferredSize(size);
		    setMinimumSize(size);
		    setMaximumSize(size);
		    setSize(size);
		    setLayout(null);
		  }

		  public void paintComponent(Graphics g) {
		    g.drawImage(img, 0, 0, null);
		  }
	}
	
	// ****************************paint
	// method******************************************
	public void paint(Graphics g) {

		super.paintComponent(g);
		bg.paint(g);
		dooley.paint(g);
        
       

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
		
		switch(state){       
	    case "RUNNING1":
	    	bg = new Background("/Graphics/background.png", 0, 0, 600, 800);
	        break;   
	    case "RUNNING2":
	    	bg = new Background("/Graphics/background1.png", 0, 0, 600, 800);
	        break;
	    case "START":
	    	bg = new Background("/Graphics/background.png", 0, 0, 600, 800);
	        break;
	    case "END":
	    	break;
	    default:
	        throw new RuntimeException("Unknown state: " + state);
	    }
		
		f = new JFrame();
		f.setTitle("DooleyJump!");
		f.setSize(800, 600);
		//f.getContentPane().setBackground(Color.black);
		f.setLayout(null);
		f.setResizable(false);
		f.addKeyListener(this);

		f.add(this);
		t = new Timer(17, this);
		t.start();
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		con = f.getContentPane();
		
		/*startPanel = new JPanel();
		startPanel.setBounds(100, 100, 600, 150);
		startPanel.setBackground(Color.black);
		startName = new JLabel("DOOLEY JUMP");
		startName.setForeground(Color.WHITE);
		startName.setFont(titleFont);*/
		
		startButtonPanel = new JPanel();
		startButtonPanel.setBounds(300, 400, 200, 100);
		startButtonPanel.setBackground(Color.black);
		
		startButton = new JButton("START");
		startButton.setBackground(Color.black);
		startButton.setForeground(Color.white);
		startButton.addActionListener(tHandler);
		
		startPanel.add(testImg);
		startButtonPanel.add(startButton);
		
		con.add(startPanel);
		con.add(startButtonPanel);
		
		f.setVisible(true);
		
	}
	
	public void createGameScreen() {
		startPanel.setVisible(false);
		startButtonPanel.setVisible(false);
		mainTextPanel = new JPanel();
		mainTextPanel.setBounds(100, 100, 600, 250);
		mainTextPanel.setBackground(Color.blue);;
		con.add(mainTextPanel);
	}
	
	public class TitleScreenHandler implements ActionListener{
		public void actionPerformed(ActionEvent event) {
			createGameScreen();
		}
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

}