package sprint3.product;

import java.util.Scanner;

public class Board {
	
	public enum Cell {EMPTY, BLUE, RED, ESS, NOUGHT}
	public enum GameState {PLAYING, DRAW, BLUE_WIN, RED_WIN}
	
	private Cell[][] grid;
	private int gridSize = 0;
	private String turn;
	private int gameMode; // 0 - simple : 1 - general
	
	private GameState currentGameState;
	
	public Board() {
		setGameMode();
		setgridSize();
		
		grid = new Cell[gridSize][gridSize];

		initBoard();
		
	}
	
	public void resetGame() {
		for (int row = 0; row < gridSize; ++row) {
			for (int col = 0; col < gridSize; ++col) {
				grid[row][col] = Cell.EMPTY;
			}
		}
		currentGameState = GameState.PLAYING;
		turn = "Blue";
	}
	
	public void setgridSize() {
		
		while (gridSize < 3) {
			Scanner mySize = new Scanner(System.in);
			System.out.println("Enter grid size: ");
			int size = mySize.nextInt();
			
			this.gridSize = size;
			
			if (gridSize < 3) {
				System.out.println("Please enter a grid size greater than 2");
				continue;
			}
			else {
				break;
			}
		}
	}

	public int getgridSize() {
		return gridSize;
	}
	
	public void setGameMode() {
		Scanner myMode = new Scanner(System.in);
		System.out.println("0) Simple or 1) General: ");
		int  mode = myMode.nextInt();
		this.gameMode = mode;
	}
	
	public int getGameMode() {
		return gameMode;
	}
	
	public void initBoard() {
		for (int row = 0; row < gridSize; row++) {
			for(int col = 0; col < gridSize; col++) {
				grid[row][col] = Cell.EMPTY;
			}
		}
		turn = "Blue";
	}

	
	public Cell getCell(int row, int col) {
		if (row >= 0 && row < gridSize && col >= 0 && col < gridSize) {
			return grid[row][col];
		}
		else { 
			return null;
		}
	}
	
	public String getTurn() {
		return turn;
	}

	
	public void makeMove(int row, int col) {
		if (row >= 0 && row < gridSize && col >= 0 && col < gridSize && grid[row][col] == Cell.EMPTY) {

			//if (turn == "Blue") {
				grid[row][col] = (turn == "Blue") ? Cell.BLUE : Cell.RED;
			updateGameState(turn, row, col);
			turn = (turn == "Blue") ? "Red" : "Blue";
		}
	}
	
	void updateGameState(String turn, int row, int col) {
		if (hasWon(turn, row, col)) {
			currentGameState = (turn == "Blue") ? GameState.BLUE_WIN : GameState.RED_WIN;
		}
		else if (isDraw()) {
			currentGameState = GameState.DRAW;
		}
	}
	
	private boolean isDraw() {
		for (int row = 0; row < gridSize; ++row) {
			for (int col = 0; col < gridSize; ++col) {
				if (grid[row][col] == Cell.EMPTY) {
					return false;
				}
			}
		}
		return true;
	}
	
	private boolean hasWon(String turn, int row, int col) {
		Cell token = (turn == "Blue") ? Cell.BLUE : Cell.RED;
		return (grid[row][0] == token // 3-in-the-row
				&& grid[row][1] == token && grid[row][2] == token
				|| grid[0][col] == token // 3-in-the-column
						&& grid[1][col] == token && grid[2][col] == token
				|| row == col // 3-in-the-diagonal
						&& grid[0][0] == token && grid[1][1] == token && grid[2][2] == token
				|| row + col == 2 // 3-in-the-opposite-diagonal
						&& grid[0][2] == token && grid[1][1] == token && grid[2][0] == token);
	}
	
	public GameState getGameState() {
		return currentGameState;
	}

}
