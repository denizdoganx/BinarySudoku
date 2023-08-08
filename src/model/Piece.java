package model;

import java.util.ArrayList;

public class Piece {

	private int pieceID;
	
	private int numberOfPieces;
	
	private ArrayList<Cell> cells;
	
	public Piece(int pieceID, int numberOfPieces, ArrayList<Cell> cells) {
		super();
		this.pieceID = pieceID;
		this.numberOfPieces = numberOfPieces;
		this.cells = cells;
	}
	
	public int getPieceID() {
		return pieceID;
	}
	
	public void setPieceID(int pieceID) {
		this.pieceID = pieceID;
	}
	
	public int getNumberOfPieces() {
		return numberOfPieces;
	}
	
	public void setNumberOfPieces(int numberOfPieces) {
		this.numberOfPieces = numberOfPieces;
	}
	
	public ArrayList<Cell> getCells() {
		return cells;
	}
	
	public void setCells(ArrayList<Cell> cells) {
		this.cells = cells;
	}
	
	
}
