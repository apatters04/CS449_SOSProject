package sprint2.product;

import java.util.Scanner;

public class Board {
	
	public enum Cell {EMPTY, BLUE, RED, ESS, NOUGHT}
	
	private Cell[][] grid;
	private int gridSize;
	private String turn;
	private String turnChoice; // 0 - S : 1 - O
	private int gameMode; // 0 - simple : 1 - general
	public Board() {
		setGameMode();
		
		if (gameMode == 1) {
			Scanner mySize = new Scanner(System.in);
			System.out.println("Enter grid size: ");
			int size = mySize.nextInt();
			setgridSize(size);
		}else {
			this.gridSize = 3;
		}

		grid = new Cell[gridSize][gridSize];

		initBoard();
		
	}
	
	public void setgridSize(int userSize) {

		this.gridSize = userSize;

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
	
	public void getTurnType() {
		Scanner userChoice  = new Scanner(System.in);
		System.out.println("Select letter to play | S or O : ");
		String  choice = userChoice.nextLine();
		this.turnChoice = choice;
	}
	
	public void makeMove(int row, int col) {
		if (row >= 0 && row < gridSize && col >= 0 && col < gridSize && grid[row][col] == Cell.EMPTY) {

			//if (turn == "Blue") {
				grid[row][col] = (turn == "Blue") ? Cell.BLUE : Cell.RED;
			
			turn = (turn == "Blue") ? "Red" : "Blue";
		}
	}



}
