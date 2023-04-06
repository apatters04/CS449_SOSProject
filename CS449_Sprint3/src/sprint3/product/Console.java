package sprint3.product;

import java.util.Scanner;

import sprint3.product.Board.Cell;
import sprint3.product.Board.GameState;
import sprint3.product.SimpleGame;
import sprint3.product.GeneralGame;


public class Console {
	private Board board;
	private SimpleGame simpGame;
	private GeneralGame genGame;
	
	public Console(Board board) {
		this.board = board;
		this.simpGame = new SimpleGame();
		this.genGame = new GeneralGame();
	}

	
	public void displayBoard() {
		for (int row = 0; row < board.getgridSize(); row++) {
			System.out.println("----------");
			for(int col = 0; col < board.getgridSize(); col++) {
				System.out.print("|" + board.getCell(row, col));
			}
			System.out.println("|");
		}
		
		System.out.println("----------");
		
	}
	
	private String symbol(Cell cell) {
		if (cell==Cell.BLUE)
			return "BLUE";
		else
			if (cell==Cell.RED)
				return "RED";
			else return " ";
	}
	
	private boolean isOver() {
		GameState state = board.getGameState();
		if (state == GameState.PLAYING) {
			return false;
		}
		if (board.getGameState() == GameState.DRAW) {
			System.out.println("DRAW!");
		}
		else if (board.getGameState() == GameState.BLUE_WIN) {
			System.out.println("BLUE WINS!");
		}
		else if (board.getGameState() == GameState.RED_WIN) {
			System.out.println("RED WINS!");
		}
		return true;
	}
	
	public void play() {
		
		if (board.getGameMode() == 0) {
			board.resetGame();
			Scanner in = new Scanner(System.in);
			boolean done = false;
			
			simpGame.startGame();
			System.out.println("Gamemode: Simple");
			System.out.println("Grid Size: " + board.getgridSize());

			while (!done) {
				int row, column;
				System.out.println("Current player: " + board.getTurn());
				System.out.print("Move at row: ");
				row = in.nextInt();
				System.out.print("Move at column: ");
				column = in.nextInt();
				if (row < 0 || row > board.getgridSize() || column < 0 || column > board.getgridSize())
					System.out.println("Invalid move at (" + row + "," + column + ")");
				else {
					board.makeMove(row, column);
					displayBoard();
					done = isOver();
				}
			}

			in.close();
		}
		else if (board.getGameMode() == 1) {
			Scanner in = new Scanner(System.in);
			boolean done = false;
			
			genGame.startGame();
			System.out.println("Gamemode: General");
			System.out.println("Grid Size: " + board.getgridSize());

			while (!done) {
				int row, column;
				System.out.println("Current player: " + board.getTurn());
				System.out.print("Move at row: ");
				row = in.nextInt();
				System.out.print("Move at column: ");
				column = in.nextInt();
				if (row < 0 || row > board.getgridSize() || column < 0 || column > board.getgridSize())
					System.out.println("Invalid move at (" + row + "," + column + ")");
				else {
					board.makeMove(row, column);
					displayBoard();
					done = isOver();
				}
			}

			in.close();

		}

	}
	
	public static void main(String[] args) {

		new Console(new Board()).play();
	}

}
