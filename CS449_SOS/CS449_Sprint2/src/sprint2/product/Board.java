package sprint2.product;


public class Board {
	
	private int[][] grid;
	private int gridSize;
	private char turn = 'S';
	
	public Board() {
	}
	
	public void setgridSize(int newSize) {
		this.gridSize = newSize;

		grid = new int[gridSize][gridSize];
	}

	public int getgridSize() {
		return gridSize;
	}
	
	public int getCell(int row, int col) {
		if (row >= 0 && row < gridSize && col >= 0 && col < gridSize) {
			return grid[row][col];
		}
		else { 
			return -1;
		}
	}
	
	public char getTurn() {
		return turn;
	}
	
	public void makeMove(int row, int col) {
		if (row >= 0 && row < 3 && col >= 0 && col < 3 && grid[row][col] == 0) {
			grid[row][col] = (turn == 'S')? 1 : 2;
			turn = (turn == 'S')? 'O' : 'S';
		}
	}
	


}
