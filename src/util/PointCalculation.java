package util;

import java.util.ArrayList;

public class PointCalculation {

	private boolean[][] garbage;
	
	private char[][] gameArray;
	
	public PointCalculation(boolean[][] garbage, char[][] gameArray) {
		super();
		this.garbage = garbage;
		this.gameArray = gameArray;
	}

	public int checkScore() {
		
		int rowScore, columnScore, squareScore;
		
		resetGarbage();
		
		rowScore = checkRowByRow();
		
		columnScore = checkColumnByColumn();
		
		squareScore = checkSquareBySquare();
				
		removeGarbage();
		
		return rowScore + columnScore + squareScore;
	}
	
	private int checkRowByRow() {
		int takenPoint = 0;
		ArrayList<Character> rowArray = new ArrayList<>();
		// These loops are checking match on row
		for(int i = 0;i < gameArray.length; i++) {
			for(int j = 0;j < gameArray[0].length; j++) {
				rowArray.add(gameArray[i][j]);
			}
			// Here , we are checking the user got any points
			if(checkArrayForPoint(rowArray)) {
				// Now we can calculate the taken points
				takenPoint += calculatePoints(rowArray);
				//Here , we are marking taken points
				for(int k = 0;k < garbage[0].length; k++) {
					garbage[i][k] = true;
				}
			}
			rowArray.clear();
		}
		return takenPoint;
	}
	
	private int checkColumnByColumn() {
		int takenPoint = 0;
		ArrayList<Character> columnArray = new ArrayList<>();
		// These loops are checking match on column
		for(int i = 0;i < gameArray[0].length; i++) {
			for(int j = 0;j < gameArray.length; j++) {
				columnArray.add(gameArray[j][i]);
			}
			// Here , we are checking the user got any points
			if(checkArrayForPoint(columnArray)) {
				// Now we can calculate the taken points
				takenPoint += calculatePoints(columnArray);
				//Here , we are marking taken points
				for(int k = 0;k < garbage[0].length; k++) {
					garbage[k][i] = true;
				}
			}
			columnArray.clear();
		}
		return takenPoint;
	}
	
	private int checkSquareBySquare() {
		int takenPoint = 0;
		ArrayList<Character> squareArray = new ArrayList<>();
		for(int matrixRowIndex = 0;matrixRowIndex < 9; matrixRowIndex += 3) {
			for(int matrixColumnIndex = 0;matrixColumnIndex < 9; matrixColumnIndex += 3) {
				for(int i = 0;i < 3; i++) {
					for(int j = 0;j < 3; j++) {
						squareArray.add(gameArray[i + matrixRowIndex][j + matrixColumnIndex]);
					}
				}
				if(checkArrayForPoint(squareArray)) {
					// Now we can calculate the taken points
					 takenPoint += calculatePoints(squareArray);
					//Here , we are marking taken points					
					for(int k = 0;k < 3; k++) {
						for(int m = 0;m < 3; m++) {
							garbage[k + matrixRowIndex][m + matrixColumnIndex] = true;
						}
					}
				}
				squareArray.clear();
			}
		}
		return takenPoint;
	}
	
	private boolean checkArrayForPoint(ArrayList<Character> pointArray) {
		boolean flag = true;
		for(Character c : pointArray) {
			if(c != '0' && c != '1') {
				flag = false;
			}
		}
		return flag;
	}
	
	private int calculatePoints(ArrayList<Character> pointArray) {
		// here, we should call setTheScore procedure when the user got point
		int pow = pointArray.size() - 1;
		int takenPoint = 0;
		for(Character c : pointArray) {
			if(c != '0') {
				takenPoint += Math.pow(2, pow);
			}
			pow--;
		}
		return takenPoint;
		
	}
	
	private void resetGarbage() {
		for(int i = 0;i < garbage.length; i++) {
			for(int j = 0;j < garbage[0].length; j++) {
				garbage[i][j] = false;
			}
		}
	}
	
	private void removeGarbage() {
		for(int i = 0;i < gameArray.length; i++) {
			for(int j = 0;j < gameArray.length; j++) {
				if(garbage[i][j]) {
					gameArray[i][j] = '-';
				}
			}
		}
	}
}
