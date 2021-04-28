import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.Dimension;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.net.URL;

public class GUI extends JFrame implements ActionListener {

	private JPanel panel;
	Background bg = new Background("background.png", 0, 0);

	public GUI() {
		initDisplay();
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		repaint();
	}

	public void displayGame() {
		java.awt.EventQueue.invokeLater(new Runnable() {
			public void run() {
				setVisible(true);
				
			}
		});
	}


	public void repaint() {
		panel.repaint();
	}

	private void initDisplay()	{
		this.setSize(new Dimension(600, 800));
		this.setResizable(false);
		
		
		panel = new JPanel() {
			public void paintComponent(Graphics g) {
				super.paintComponent(g);
				bg.paint(g);
			}
		};
		
		panel.setVisible(true);
	}
	
	public void actionPerformed(ActionEvent e) {
			repaint();
	}
}
