package view;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import model.*;
import util.*;
import javax.swing.*;

@SuppressWarnings("serial")
public class GamePanel extends JPanel implements ActionListener {

	/**
	 * Create the panel.
	 */
	
	
	static final int SCREEN_WIDTH = 1000;
	static final int SCREEN_HEIGHT = 600;
	static final int GAME_WIDTH = 450;
	static final int GAME_HEIGHT = 450;
	static final int UNIT_SIZE = 50;
	static final int GAME_UNITS = (GAME_WIDTH * GAME_HEIGHT) / (UNIT_SIZE * UNIT_SIZE);
	static final int DELAY = 75;
	static final int TOP_GAP = (SCREEN_HEIGHT - GAME_HEIGHT) / 2;
	static final int BOTTOM_GAP = (SCREEN_HEIGHT - GAME_HEIGHT) / 2;
	static final int LEFT_GAP = 25;
	static final int RIGHT_GAP = 25;
	static final int START_X = LEFT_GAP + (UNIT_SIZE / 2);
	static final int START_Y = TOP_GAP + (UNIT_SIZE / 2);
	
	private JFrame parentFrame;
	private int score;
	private int pieceNumber;
	private char direction;
	private final char[][] gameArray = new char[GAME_HEIGHT / UNIT_SIZE][GAME_WIDTH / UNIT_SIZE];
	private final boolean[][] garbage = new boolean[GAME_HEIGHT / UNIT_SIZE][GAME_WIDTH / UNIT_SIZE];
	private Timer timer;
	private boolean running;
	private JLabel scoreLabel;
	private JLabel pieceLabel;
	private RandomClass random;
	private PiecePanel piecePanel;
	private Piece currentPiece;
	private Piece nextPiece;
	private PointCalculation pointCalculation;
	private SpaceSearch spaceSearch;
	
	public GamePanel(JFrame parentFrame) {
		this.parentFrame = parentFrame;
		this.setPreferredSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT));
		this.setBackground(Color.black);
		this.setFocusable(true);
		setLayout(null);
		
		JLabel lblNewLabel = new JLabel("New Piece");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setForeground(new Color(192, 192, 192));
		lblNewLabel.setFont(new Font("MV Boli", Font.BOLD, 24));
		lblNewLabel.setBounds(561, 25, 144, 35);
		add(lblNewLabel);
		
		scoreLabel = new JLabel("Score : 0");
		scoreLabel.setHorizontalAlignment(SwingConstants.CENTER);
		scoreLabel.setFont(new Font("MV Boli", Font.BOLD, 24));
		scoreLabel.setForeground(new Color(192, 192, 192));
		scoreLabel.setBounds(748, 25, 242, 35);
		add(scoreLabel);
		
		pieceLabel = new JLabel("Piece : 0");
		pieceLabel.setHorizontalAlignment(SwingConstants.CENTER);
		pieceLabel.setFont(new Font("MV Boli", Font.BOLD, 24));
		pieceLabel.setForeground(new Color(192, 192, 192));
		pieceLabel.setBounds(748, 106, 242, 35);
		add(pieceLabel);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(0, 0, 0));
		panel.setBounds(561, 71, 144, 160);
		piecePanel = new PiecePanel();
		panel.add(piecePanel);
		add(panel);
		this.addKeyListener(new MyKeyAdapter());
		startGame();
		
	}

	private void startGame() {
		
		fillInTheGameArray();
		random = RandomClass.getInstance();
		pointCalculation = new PointCalculation(garbage, gameArray);
		spaceSearch = new SpaceSearch(gameArray);
		running = true;
		currentPiece = null;
		score = 0;
		direction = 'R';
		timer = new Timer(DELAY, this);
		timer.start();
		newPiece();
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		draw(g);
	}
	
	
	private void draw(Graphics g) {
		
		if(running) {
			int tempX, tempY ;
			g.setFont(new Font("MV Boli", Font.BOLD, 24));
			g.setColor(Color.gray);
			for(int i = 0;i <= GAME_WIDTH  / UNIT_SIZE; i++) {
				
				if(i == 0) {
					for(int j = 0;j < GAME_WIDTH  / UNIT_SIZE; j++) {
						g.drawString(String.valueOf(j+1), 5, j*UNIT_SIZE + TOP_GAP + (UNIT_SIZE / 2));
					}
				}
				
				g.drawLine(i * UNIT_SIZE + LEFT_GAP, TOP_GAP, i * UNIT_SIZE + LEFT_GAP,  TOP_GAP + GAME_HEIGHT);
			}
			
			for(int i = 0;i <= GAME_HEIGHT / UNIT_SIZE; i++) {
				if(i == 0) {
					for(int j = 0;j < GAME_HEIGHT / UNIT_SIZE; j++) {
						g.drawString(String.valueOf(j+1), j*UNIT_SIZE + LEFT_GAP + (UNIT_SIZE / 2), TOP_GAP - 10);
					}
				}
				g.drawLine(LEFT_GAP , i * UNIT_SIZE + TOP_GAP, LEFT_GAP + GAME_WIDTH, i * UNIT_SIZE + TOP_GAP);
			}
			
			for(int i = 0;i < gameArray.length; i++) {
				for(int j = 0;j < gameArray[0].length; j++) {
					tempX = START_X + UNIT_SIZE * j;
					tempY = START_Y + UNIT_SIZE * i;
					if(
							(j >= 3 && j <= 5 && ((i >= 0 && i <= 2) || (i >= 6 && i <= 8))) ||
							(i >= 3 && i <= 5 && ((j >= 0 && j <= 2) || (j >= 6 && j <= 8)))) {
						g.setColor(new Color(40 , 40 , 40));
						g.fillRect(tempX - (UNIT_SIZE / 2) + 1, tempY - (UNIT_SIZE / 2) + 1, UNIT_SIZE-1, UNIT_SIZE-1);
	
					}
					g.setColor(new Color(10 , 40, 80));
					g.drawRect(tempX- (UNIT_SIZE / 2), tempY- (UNIT_SIZE / 2), UNIT_SIZE, UNIT_SIZE);
					if(gameArray[i][j] == '-') {
						
						g.setColor(Color.gray);
					}
					else {
						g.setColor(Color.orange);
					}
					g.drawString(String.valueOf(gameArray[i][j]), tempX, tempY);
				} 
			}
			
			if(currentPiece != null) {
				ArrayList<Cell> cells = currentPiece.getCells();
				
				for(Cell cell : cells) {
					int i = cell.getX() - 1;
					int j = cell.getY() - 1;
					tempX = START_X + UNIT_SIZE * (cell.getY() - 1);
					tempY = START_Y + UNIT_SIZE * (cell.getX() - 1);
					if(
							(j >= 3 && j <= 5 && ((i >= 0 && i <= 2) || (i >= 6 && i <= 8))) ||
							(i >= 3 && i <= 5 && ((j >= 0 && j <= 2) || (j >= 6 && j <= 8)))) {
						g.setColor(new Color(40 , 40 , 40));
					}
					else {
						g.setColor(Color.black);
					}
					
					g.drawString("-", tempX, tempY);
					g.setColor(Color.red);
					g.drawString(String.valueOf(cell.getValue()), tempX, tempY);
				}
				
			}
		}
		else {
			gameOver();
		}
				
	}
	
	private void newPiece() {
		int pieceNumber = random.getRandomNumber(1, 100);
		ArrayList<Cell> cells = new ArrayList<>();
		if(pieceNumber <= 10) {
			cells.add(new Cell(1, 1, random.getRandomNumber(0, 1)));
			nextPiece = new Piece(1, 1, cells);
		}
		else if(pieceNumber <= 20) {
			cells.add(new Cell(1, 1, random.getRandomNumber(0, 1)));
			cells.add(new Cell(1, 2, random.getRandomNumber(0, 1)));
			nextPiece = new Piece(2, 2, cells);
		}
		else if(pieceNumber <= 30) {
			cells.add(new Cell(1, 1, random.getRandomNumber(0, 1)));
			cells.add(new Cell(2, 1, random.getRandomNumber(0, 1)));
			nextPiece = new Piece(3, 2, cells);
		}
		else if(pieceNumber <= 40) {
			cells.add(new Cell(1, 1, random.getRandomNumber(0, 1)));
			cells.add(new Cell(1, 2, random.getRandomNumber(0, 1)));
			cells.add(new Cell(1, 3, random.getRandomNumber(0, 1)));
			nextPiece = new Piece(4, 3, cells);
		}
		else if(pieceNumber <= 50) {
			cells.add(new Cell(1, 1, random.getRandomNumber(0, 1)));
			cells.add(new Cell(2, 1, random.getRandomNumber(0, 1)));
			cells.add(new Cell(3, 1, random.getRandomNumber(0, 1)));
			nextPiece = new Piece(5, 3, cells);
		}
		else if(pieceNumber <= 60) {
			cells.add(new Cell(1, 1, random.getRandomNumber(0, 1)));
			cells.add(new Cell(1, 2, random.getRandomNumber(0, 1)));
			cells.add(new Cell(2, 1, random.getRandomNumber(0, 1)));
			nextPiece = new Piece(6, 3, cells);
		}
		else if(pieceNumber <= 70) {
			cells.add(new Cell(1, 1, random.getRandomNumber(0, 1)));
			cells.add(new Cell(1, 2, random.getRandomNumber(0, 1)));
			cells.add(new Cell(2, 2, random.getRandomNumber(0, 1)));
			nextPiece = new Piece(7, 3, cells);
		}
		else if(pieceNumber <= 80) {
			cells.add(new Cell(1, 1, random.getRandomNumber(0, 1)));
			cells.add(new Cell(2, 1, random.getRandomNumber(0, 1)));
			cells.add(new Cell(2, 2, random.getRandomNumber(0, 1)));
			nextPiece = new Piece(8, 3, cells);
		}
		else if(pieceNumber <= 90) {
			cells.add(new Cell(1, 2, random.getRandomNumber(0, 1)));
			cells.add(new Cell(2, 1, random.getRandomNumber(0, 1)));
			cells.add(new Cell(2, 2, random.getRandomNumber(0, 1)));
			nextPiece = new Piece(9, 3, cells);
		}
		else {
			cells.add(new Cell(1, 1, random.getRandomNumber(0, 1)));
			cells.add(new Cell(1, 2, random.getRandomNumber(0, 1)));
			cells.add(new Cell(2, 1, random.getRandomNumber(0, 1)));
			cells.add(new Cell(2, 2, random.getRandomNumber(0, 1)));
			nextPiece = new Piece(10, 3, cells);
		}
		piecePanel.fillInThePanelWithNewPiece(nextPiece);
	}
	
	private void move() {
		if(currentPiece != null) {
			ArrayList<Cell> cells = currentPiece.getCells();
			boolean flag = true;
			switch(direction) {
			case 'R':
				for(Cell cell : cells) {
					if(cell.getY() == 9) {
						flag = false;
					}
				}
				if(flag) {
					for(Cell cell : cells) {
						cell.setY(cell.getY() + 1);
					}
				}
				break;
			case 'D':
				for(Cell cell : cells) {
					if(cell.getX() == 9) {
						flag = false;
					}
				}
				if(flag) {
					for(Cell cell : cells) {
						cell.setX(cell.getX() + 1);
					}
				}
				break;
			case 'L':
				for(Cell cell : cells) {
					if(cell.getY() == 1) {
						flag = false;
					}
				}
				if(flag) {
					for(Cell cell : cells) {
						cell.setY(cell.getY() - 1);
					}
				}
				break;
			case 'U':
				for(Cell cell : cells) {
					if(cell.getX() == 1) {
						flag = false;
					}
				}
				if(flag) {
					for(Cell cell : cells) {
						cell.setX(cell.getX() - 1);
					}
				}
				break;
			}
		}
		else {
			if(doesGameAreaHaveEnoughSpace()) {
				currentPiece = nextPiece;
				newPiece();
				pieceNumber++;
				setThePieceNumber();
			}
			else {
				running = false;
				
			}
		}
	}
	
	private boolean doesGameAreaHaveEnoughSpace() {
		return spaceSearch.checkSpace(nextPiece);
	}
	
	private void gameOver() {
		timer.stop();
		parentFrame.dispose();
		new GameOverPage(score);
	}
	
	private void checkScore() {
		score += pointCalculation.checkScore();
		setTheScore();
	}
	
	private void checkCollisions() {
		// Here , we will call checkScore procedure.
		
		if(currentPiece != null) {
			ArrayList<Cell> cells = currentPiece.getCells();
			int rowIndex, columnIndex;
			boolean flag = true;
			for(Cell cell : cells) {
				rowIndex = cell.getX() - 1;
				columnIndex = cell.getY() - 1;
				if(gameArray[rowIndex][columnIndex] != '-') {
					flag = false;
				}
			}
			if(flag) {
				for(Cell cell : cells) {
					rowIndex = cell.getX() - 1;
					columnIndex = cell.getY() - 1;
					if(cell.getValue() == 0) {
						gameArray[rowIndex][columnIndex] =  '0';
					}
					else {
						gameArray[rowIndex][columnIndex] =  '1';
					}
				}
				currentPiece = null;
				checkScore();
			}
		}
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		repaint();
	}
	
	private void setThePieceNumber() {
		pieceLabel.setText("Piece : " + pieceNumber);
	}
	
	private void setTheScore() {
		scoreLabel.setText("Score : " + score);
	}
	
	public class MyKeyAdapter extends KeyAdapter {
		@Override
		public void keyPressed(KeyEvent e) {
			switch(e.getKeyCode()) {
			case KeyEvent.VK_RIGHT:
				direction = 'R';
				move();
				break;
			case KeyEvent.VK_DOWN:
				direction = 'D';
				move();
				break;
			case KeyEvent.VK_LEFT:
				direction = 'L';
				move();
				break;
			case KeyEvent.VK_UP:
				direction = 'U';
				move();
				break;
			case KeyEvent.VK_ENTER:
				checkCollisions();
				break;
			}
		}
	}
	private void fillInTheGameArray() {
		for(int i = 0;i < gameArray.length; i++) {
			for(int j = 0;j < gameArray[0].length; j++) {
				gameArray[i][j] = '-';
			}
		}
	}
}
