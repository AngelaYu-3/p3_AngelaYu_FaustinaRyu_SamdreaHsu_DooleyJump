import java.awt.Graphics;
import java.awt.Dimension;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * This class provides a GUI for solitaire games related to Elevens.
 */
public class GUI extends JFrame implements ActionListener {

	/** The main panel containing the game components. */
	private JPanel panel;

	public GUI() {
		initDisplay();
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		repaint();
	}

	/**
	 * Run the game.
	 */
	public void displayGame() {
		java.awt.EventQueue.invokeLater(new Runnable() {
			public void run() {
				setVisible(true);
			}
		});
	}

	/**
	 * Draw the display (cards and messages).
	 */
	public void repaint() {
		panel.repaint();
	}

	/**
	 * Initialize the display.
	 */
	private void initDisplay()	{
		panel = new JPanel() {
			public void paintComponent(Graphics g) {
				super.paintComponent(g);
			}
		};

		this.setSize(new Dimension(600, 800));
		this.setResizable(false);
		panel.setLayout(null);
		//panel.setPreferredSize(
			//new Dimension(600 - 20, 600 - 20));
		
		//pack();
		//getContentPane().add(panel);
		panel.setVisible(true);
	}
	

	/**
	 * Respond to a button click (on either the "Replace" button
	 * or the "Restart" button).
	 * @param e the button click action event
	 */
	public void actionPerformed(ActionEvent e) {
			repaint();
	}
}
