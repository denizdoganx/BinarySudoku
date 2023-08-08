package view;

import javax.swing.JFrame;
import javax.swing.JPanel;


@SuppressWarnings("serial")
public class GameFrame extends JFrame {

	private JPanel contentPane;


	/**
	 * Create the frame.
	 */
	public GameFrame() {
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setTitle("Binary Sudoku");
		contentPane = new GamePanel(this);
		setContentPane(contentPane);
		pack();
		setLocationRelativeTo(null);
		setVisible(true);
	}

}
