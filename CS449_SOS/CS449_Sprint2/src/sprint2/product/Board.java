package sprint2.product;

import java.util.Scanner;

public class Board {
	
	public enum Cell {EMPTY, ESS, NOUGHT}
	
	private Cell[][] grid;
	private int gridSize=5;
	private char turn;
	
	private boolean playOneTurn = true;
	private boolean playTwoTurn = false;
	
	public Board() {
		//setgridSize();
		grid = new Cell[gridSize][gridSize];
		initBoard();
		
	}
	
	public void setgridSize(int userSize) {
		/*Scanner mySize = new Scanner(System.in);
		System.out.println("Enter grid size: ");
		int size = mySize.nextInt();
		this.gridSize = size;*/
		
		this.gridSize = userSize;

	}
	
	public void initBoard() {
		for (int row = 0; row < gridSize; row++) {
			for(int col = 0; col < gridSize; col++) {
				grid[row][col] = Cell.EMPTY;
			}
		}
		turn = 'X';
	}

	public int getgridSize() {
		return gridSize;
	}
	
	public Cell getCell(int row, int col) {
		if (row >= 0 && row < gridSize && col >= 0 && col < gridSize) {
			return grid[row][col];
		}
		else { 
			return null;
		}
	}
	
	public char getTurn() {
		return turn;
	}
	
	public void makeMove(int row, int col) {
		if (row >= 0 && row < gridSize && col >= 0 && col < gridSize && grid[row][col] == Cell.EMPTY) {
			grid[row][col] = (turn == 'S') ? Cell.ESS : Cell.NOUGHT;
			turn = (turn == 'S') ? 'O' : 'S';
		}
	}
	


}
