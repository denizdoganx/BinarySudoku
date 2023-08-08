package view;


import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import context.DbHelper;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

@SuppressWarnings("serial")
public class GameOverPage extends JFrame {

	private JPanel contentPane;

	/**
	 * Create the frame.
	 */
	public GameOverPage(int score) {
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setTitle("Game Over !");
		contentPane = new JPanel();
		contentPane.setBackground(Color.black);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setBounds(100, 100, 600, 600);
		setLocationRelativeTo(null);
		setVisible(true);
		
		
		
		
		JLabel lblNewLabel = new JLabel("Game Over");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Ink Free", Font.BOLD, 99));
		lblNewLabel.setForeground(new Color(255, 0, 0));
		lblNewLabel.setBounds(10, 11, 564, 236);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Your Score : " + score);
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setForeground(new Color(255, 0, 0));
		lblNewLabel_1.setFont(new Font("Ink Free", Font.BOLD, 36));
		lblNewLabel_1.setBounds(10, 258, 564, 75);
		contentPane.add(lblNewLabel_1);
		
		JButton btnNewButton = new JButton("Try Again");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				GameOverPage.this.dispose();
				new GameFrame();
			}
		});
		btnNewButton.setFocusable(false);
		btnNewButton.setForeground(new Color(255, 0, 0));
		btnNewButton.setBackground(new Color(0, 0, 0));
		btnNewButton.setFont(new Font("Ink Free", Font.BOLD, 32));
		btnNewButton.setBounds(131, 430, 323, 55);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Show High Scores");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				GameOverPage.this.setVisible(false);
				new HighScorePage(GameOverPage.this);
			}
		});
		btnNewButton_1.setForeground(Color.RED);
		btnNewButton_1.setFont(new Font("Ink Free", Font.BOLD, 32));
		btnNewButton_1.setFocusable(false);
		btnNewButton_1.setBackground(Color.BLACK);
		btnNewButton_1.setBounds(131, 495, 323, 55);
		contentPane.add(btnNewButton_1);

		
		
		DbHelper dbHelper = DbHelper.getInstance();
		String name = JOptionPane.showInputDialog(null, "Please enter your name: ", "Game Over !", JOptionPane.QUESTION_MESSAGE);
		if(name != null && !name.isEmpty()) {
			dbHelper.addScore(name, score);
		}
	}
}
