package view;


import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import context.DbHelper;
import model.HighScore;

import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.GridLayout;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;

@SuppressWarnings("serial")
public class HighScorePage extends JFrame {

	private JPanel contentPane;
	private JFrame parentFrame;
	private JPanel panel;
	/**
	 * Create the frame.
	 */
	public HighScorePage(JFrame parentFrame) {
		this.parentFrame = parentFrame;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 600, 600);
		setLocationRelativeTo(null);
		setVisible(true);

		contentPane = new JPanel();
		contentPane.setBackground(Color.black);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("High Scores");
		lblNewLabel.setFont(new Font("Ink Free", Font.BOLD, 64));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setForeground(new Color(255, 0, 0));
		lblNewLabel.setBounds(10, 11, 564, 100);
		contentPane.add(lblNewLabel);
		
		panel = new JPanel();
		panel.setBackground(new Color(0, 0, 0));
		panel.setBounds(125, 187, 335, 315);
		contentPane.add(panel);
		panel.setLayout(new GridLayout(0, 2, 5, 10));
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(0, 0, 0));
		panel_1.setBounds(125, 122, 335, 54);
		contentPane.add(panel_1);
		panel_1.setLayout(new GridLayout(0, 2, 0, 0));
		
		JLabel lblNewLabel_1 = new JLabel("Name");
		lblNewLabel_1.setForeground(new Color(255, 0, 0));
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel_1.setFont(new Font("Ink Free", Font.BOLD, 32));
		panel_1.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Score");
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_2.setForeground(new Color(255, 0, 0));
		lblNewLabel_2.setFont(new Font("Ink Free", Font.BOLD, 32));
		panel_1.add(lblNewLabel_2);
		
		JButton btnNewButton = new JButton("Back");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				HighScorePage.this.dispose();
				HighScorePage.this.parentFrame.setVisible(true);
			}
		});
		btnNewButton.setFocusable(false);
		btnNewButton.setBackground(new Color(0, 0, 0));
		btnNewButton.setForeground(new Color(255, 0, 0));
		btnNewButton.setFont(new Font("Ink Free", Font.BOLD, 32));
		btnNewButton.setBounds(125, 513, 335, 37);
		contentPane.add(btnNewButton);
		
		fillInTheHighScoreTable();
	}
	
	
	
	private void fillInTheHighScoreTable() {
		DbHelper dbHelper = DbHelper.getInstance();
		ArrayList<HighScore> highScores = dbHelper.getHighScores();
		
		for(HighScore highScore : highScores) {
			JLabel label = new JLabel(highScore.getName());
			label.setForeground(Color.red);
			label.setFont(new Font("Ink Free", Font.BOLD, 32));
			label.setHorizontalAlignment(SwingConstants.LEFT);
			JLabel label2 = new JLabel( String.valueOf( highScore.getScore()));
			label2.setForeground(Color.red);
			label2.setFont(new Font("Ink Free", Font.BOLD, 32));
			label2.setHorizontalAlignment(SwingConstants.RIGHT);
			panel.add(label);
			panel.add(label2);
		}
		
	}
}
