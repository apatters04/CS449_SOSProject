package sprint3.product;

import java.util.Scanner;

//import sprint3.product.Board.Cell;
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
	
	public boolean isOver() {
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
		else if (board.getGameState() == GameState.END) {
			System.out.println("GAME END");
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
				char play;
				System.out.println("Current player: " + board.getTurn());
				System.out.println("S or O: ");
				play = in.next().charAt(0);
				System.out.print("Move at row: ");
				row = in.nextInt();
				System.out.print("Move at column: ");
				column = in.nextInt();
				if (row < 0 || row > board.getgridSize() || column < 0 || column > board.getgridSize())
					System.out.println("Invalid move at (" + row + "," + column + ")");
				else {
					board.makeMove(row, column, play);
					board.updateGameState(simpGame.hasSOS(board, board.getgridSize()));
					displayBoard();
					done = isOver();
				}
			}

			in.close();
		}
		else if (board.getGameMode() == 1) {
			board.resetGame();
			Scanner in = new Scanner(System.in);
			boolean done = false;
			
			simpGame.startGame();
			System.out.println("Gamemode: General");
			System.out.println("Grid Size: " + board.getgridSize());

			while (!done) {
				int row, column;
				char play;
				System.out.println("Current player: " + board.getTurn());
				System.out.println("S or O: ");
				play = in.next().charAt(0);
				System.out.print("Move at row: ");
				row = in.nextInt();
				System.out.print("Move at column: ");
				column = in.nextInt();
				if (row < 0 || row > board.getgridSize() || column < 0 || column > board.getgridSize())
					System.out.println("Invalid move at (" + row + "," + column + ")");
				else {
					board.makeMove(row, column, play);
					if (genGame.hasSOS(board, board.getgridSize())) {
						if (board.getTurn() == "Red") {
							genGame.addBluePoints(1);
						}else if (board.getTurn() == "Blue") {
							genGame.addRedPoints(1);
						}
					}
					System.out.println("Blue Points: " + genGame.getBluePoints());
					System.out.println("Red Points: " + genGame.getRedPoints());
					board.updateGameState(board.boardFull());
					displayBoard();
					done = isOver();
				}
			}
			
			if (genGame.getBluePoints() > genGame.getRedPoints()) {
				System.out.println("Blue Wins");
			} else if (genGame.getBluePoints() < genGame.getRedPoints()) {
				System.out.println("Red Wins");
			} else if (genGame.getBluePoints() == genGame.getRedPoints()) {
				System.out.println("Draw! No one wins");
			}

			in.close();
			

		}

	}
	
	public static void main(String[] args) {

		new Console(new Board()).play();
	}

}
