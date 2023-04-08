package sprint3.product;

import java.util.Scanner;

public class Board {
	
	public enum Cell {EMPTY, BLUE, RED, ESS, OH, USED}
	public enum GameState {PLAYING, DRAW, BLUE_WIN, RED_WIN, END}
	
	private Cell[][] grid;
	private int gridSize = 0;
	private String turn;
	private char play;
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
	
	public void setCell(int row, int col, Cell input) {
		grid[row][col] = input;
	}
	
	public String getTurn() {
		return turn;
	}

	
	public void makeMove(int row, int col, char playType) {
		if (row >= 0 && row < gridSize && col >= 0 && col < gridSize && grid[row][col] == Cell.EMPTY) {
			if (playType == 'S') {
				grid[row][col] = Cell.ESS;
			}else if (playType == 'O') {
				grid[row][col] = Cell.OH;
			}
			//grid[row][col] = (turn == "Blue") ? Cell.BLUE : Cell.RED;
			//updateGameState();
			turn = (turn == "Blue") ? "Red" : "Blue";
		}
	}
	
	void updateGameState(boolean win) {
		if (hasWon(win)) {
			currentGameState = (turn == "Red") ? GameState.BLUE_WIN : GameState.RED_WIN; //flips game state
		}
		else if (isDraw() && (gameMode == 0)) {
			currentGameState = GameState.DRAW;
		}
		else if (boardFull() && (gameMode == 1)) {
			currentGameState = GameState.END;
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
	
	boolean boardFull() {
		for (int row = 0; row < gridSize; ++row) {
			for (int col = 0; col < gridSize; ++col) {
				if (grid[row][col] == Cell.EMPTY) {
					return false;
				}
			}
		}
		return true;
	}
	
	private boolean hasWon(boolean win) {
		boolean hasWin = win;
		
		return hasWin;
	}
	
	public GameState getGameState() {
		return currentGameState;
	}

}
