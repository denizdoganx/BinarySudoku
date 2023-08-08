package view;

import javax.swing.JLabel;
import javax.swing.JPanel;

import model.Piece;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.util.ArrayList;

@SuppressWarnings("serial")
public class PiecePanel extends JPanel {

	/**
	 * Create the panel.
	 */
	private int row;
	
	private int column;
	
	private ArrayList<JLabel> labels;
	
	public PiecePanel() {
		row = 3;
		column = 3;
		labels = new ArrayList<>();
		setBackground(new Color(0, 0, 0));
		setLayout(new GridLayout(row, column, 30, 15));
		fillInThePanelWithLabels();
	}

	public void fillInThePanelWithNewPiece(Piece p) {
		clearAllLabels();
		switch (p.getPieceID()) {
		case 1: 
			labels.get(0).setText(String.valueOf(p.getCells().get(0).getValue()));
			break;
		case 2:
			labels.get(0).setText(String.valueOf(p.getCells().get(0).getValue()));
			labels.get(1).setText(String.valueOf(p.getCells().get(1).getValue()));
			break;
		case 3:
			labels.get(0).setText(String.valueOf(p.getCells().get(0).getValue()));
			labels.get(3).setText(String.valueOf(p.getCells().get(1).getValue()));
			break;
		case 4:
			labels.get(0).setText(String.valueOf(p.getCells().get(0).getValue()));
			labels.get(1).setText(String.valueOf(p.getCells().get(1).getValue()));
			labels.get(2).setText(String.valueOf(p.getCells().get(2).getValue()));
			break;
		case 5:
			labels.get(0).setText(String.valueOf(p.getCells().get(0).getValue()));
			labels.get(3).setText(String.valueOf(p.getCells().get(1).getValue()));
			labels.get(6).setText(String.valueOf(p.getCells().get(2).getValue()));
			break;
		case 6:
			labels.get(0).setText(String.valueOf(p.getCells().get(0).getValue()));
			labels.get(1).setText(String.valueOf(p.getCells().get(1).getValue()));
			labels.get(3).setText(String.valueOf(p.getCells().get(2).getValue()));
			break;
		case 7:
			labels.get(0).setText(String.valueOf(p.getCells().get(0).getValue()));
			labels.get(1).setText(String.valueOf(p.getCells().get(1).getValue()));
			labels.get(4).setText(String.valueOf(p.getCells().get(2).getValue()));
			break;
		case 8:
			labels.get(0).setText(String.valueOf(p.getCells().get(0).getValue()));
			labels.get(3).setText(String.valueOf(p.getCells().get(1).getValue()));
			labels.get(4).setText(String.valueOf(p.getCells().get(2).getValue()));
			break;
		case 9:
			labels.get(1).setText(String.valueOf(p.getCells().get(0).getValue()));
			labels.get(3).setText(String.valueOf(p.getCells().get(1).getValue()));
			labels.get(4).setText(String.valueOf(p.getCells().get(2).getValue()));
			break;
		case 10:
			labels.get(0).setText(String.valueOf(p.getCells().get(0).getValue()));
			labels.get(1).setText(String.valueOf(p.getCells().get(1).getValue()));
			labels.get(3).setText(String.valueOf(p.getCells().get(2).getValue()));
			labels.get(4).setText(String.valueOf(p.getCells().get(3).getValue()));
			break;
		}

	}
	
	private void clearAllLabels() {
		for(JLabel label : labels) {
			label.setText("");
		}
	}
	
	private void fillInThePanelWithLabels() {
		
		for(int i = 0;i < row * column; i++) {
			JLabel label = new JLabel();
			label.setFont(new Font("MV Boli", Font.BOLD, 24));
			label.setForeground(Color.lightGray);
			labels.add(label);
			add(label);
		}
		
	}

}
