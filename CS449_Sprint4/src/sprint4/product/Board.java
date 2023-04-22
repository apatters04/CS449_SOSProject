package sprint4.product;

import java.util.Scanner;

public class Board {
	
	public enum Cell {EMPTY, BLUE, RED, ESS, OH, USED}
	public enum GameState {PLAYING, DRAW, BLUE_WIN, RED_WIN, END}
	
	protected Cell[][] grid;
	private int gridSize = 0;
	protected String turn;
	private int gameMode; // 0 - simple : 1 - general
	private int bluePlayer;
	private int redPlayer;
	
	protected GameState currentGameState;
	
	public Board() {
		setGameMode();
		setBlueAIPlayer();
		setRedAIPlayer();
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
	
	public void setBlueAIPlayer() {
		Scanner blueAI = new Scanner(System.in);
		System.out.println("Blue Turn: AI or Human Player");
		System.out.println("0) AI Player or 1) Human Player: ");
		int  isAI = blueAI.nextInt();
		this.bluePlayer = isAI;

	}
	
	public int getBlueAIPlayer() {
		return bluePlayer;
	}
	
	public void setRedAIPlayer() {
		Scanner redAI = new Scanner(System.in);
		System.out.println("Red Turn: AI or Human Player");
		System.out.println("0) AI Player or 1) Human Player: ");
		int  isAI = redAI.nextInt();
		this.redPlayer = isAI;
		
	}
	
	public int getRedAIPlayer() {
		return redPlayer;
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
			turn = (turn == "Blue") ? "Red" : "Blue";
		}
	}
	
	public void updateGameState(boolean win) {
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
	
	public void setGameState(GameState state) {
		currentGameState = state;
	}
	
	public GameState getGameState() {
		return currentGameState;
	}

}
