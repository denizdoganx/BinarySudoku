package util;

import model.Piece;

public class SpaceSearch {
	

	
	private char[][] gameArray;
	
	public SpaceSearch(char[][] gameArray) {
		this.gameArray = gameArray;
	}

	public boolean checkSpace(Piece piece) {
		boolean flag = false;
		switch (piece.getPieceID()){
		case 1:
			flag = checkForPieceID1();
			break;
		case 2:
			flag = checkForPieceID2();
			break;
		case 3:
			flag = checkForPieceID3();
			break;
		case 4:
			flag = checkForPieceID4();
			break;
		case 5:
			flag = checkForPieceID5();
			break;
		case 6:
			flag = checkForPieceID6();
			break;
		case 7:
			flag = checkForPieceID7();
			break;
		case 8:
			flag = checkForPieceID8();
			break;
		case 9:
			flag = checkForPieceID9();
			break;
		case 10:
			flag = checkForPieceID10();
			break;
		}
		return flag;
	}
	
	private boolean checkForPieceID1() {
		boolean flag = false;
		for(int i = 0;i < gameArray.length; i++) {
			for(int j = 0;j < gameArray[0].length; j++) {
				if(gameArray[i][j] == '-') {
					flag = true;
				}
			}
		}
		return flag;
	}
	
	private boolean checkForPieceID2() {
		boolean flag = false;
		for(int i = 0;i < gameArray.length; i++) {
			for(int j = 0;j < gameArray[0].length - 1; j++) {
				if(gameArray[i][j] == '-' && gameArray[i][j+1] == '-') {
					flag = true;
				}
			}
		}
		return flag;
	}
	
	private boolean checkForPieceID3() {
		boolean flag = false;
		for(int i = 0;i < gameArray.length - 1; i++) {
			for(int j = 0;j < gameArray[0].length; j++) {
				if(gameArray[i][j] == '-' && gameArray[i+1][j] == '-') {
					flag = true;
				}
			}
		}
		return flag;
	}
	
	private boolean checkForPieceID4() {
		boolean flag = false;
		for(int i = 0;i < gameArray.length; i++) {
			for(int j = 0;j < gameArray[0].length - 2; j++) {
				if(gameArray[i][j] == '-' && gameArray[i][j+1] == '-' && gameArray[i][j+2] == '-') {
					flag = true;
				}
			}
		}
		return flag;
	}
	
	private boolean checkForPieceID5() {
		boolean flag = false;
		for(int i = 0;i < gameArray.length - 2; i++) {
			for(int j = 0;j < gameArray[0].length; j++) {
				if(gameArray[i][j] == '-' && gameArray[i+1][j] == '-' && gameArray[i+2][j] == '-') {
					flag = true;
				}
			}
		}
		return flag;
	}
	
	private boolean checkForPieceID6() {
		boolean flag = false;
		for(int i = 0;i < gameArray.length - 1; i++) {
			for(int j = 0;j < gameArray[0].length - 1; j++) {
				if(gameArray[i][j] == '-' && gameArray[i+1][j] == '-' && gameArray[i][j+1] == '-') {
					flag = true;
				}
			}
		}
		return flag;
	}
	
	private boolean checkForPieceID7() {
		boolean flag = false;
		for(int i = 0;i < gameArray.length - 1; i++) {
			for(int j = 0;j < gameArray[0].length - 1; j++) {
				if(gameArray[i][j] == '-' && gameArray[i][j+1] == '-' && gameArray[i+1][j+1] == '-') {
					flag = true;
				}
			}
		}
		return flag;
	}
	
	private boolean checkForPieceID8() {
		boolean flag = false;
		for(int i = 0;i < gameArray.length - 1; i++) {
			for(int j = 0;j < gameArray[0].length - 1; j++) {
				if(gameArray[i][j] == '-' && gameArray[i+1][j] == '-' && gameArray[i+1][j+1] == '-') {
					flag = true;
				}
			}
		}
		return flag;
	}
	
	private boolean checkForPieceID9() {
		boolean flag = false;
		for(int i = 0;i < gameArray.length - 1; i++) {
			for(int j = 0;j < gameArray[0].length - 1; j++) {
				if(gameArray[i][j+1] == '-' && gameArray[i+1][j] == '-' && gameArray[i+1][j+1] == '-') {
					flag = true;
				}
			}
		}
		return flag;
	}
	
	private boolean checkForPieceID10() {
		boolean flag = false;
		for(int i = 0;i < gameArray.length - 1; i++) {
			for(int j = 0;j < gameArray[0].length - 1; j++) {
				if(gameArray[i][j] == '-' && gameArray[i][j+1] == '-' && gameArray[i+1][j] == '-' && gameArray[i+1][j+1] == '-') {
					flag = true;
				}
			}
		}
		return flag;
	}
}
