package sprint4.product;

import java.util.Scanner;

//import sprint3.product.Board.Cell;
import sprint4.product.Board.GameState;
import sprint4.product.SimpleGame;
import sprint4.product.GeneralGame;


public class Console {
	private Board board;
	private SimpleGame simpGame;
	private GeneralGame genGame;
	private AIPlayer AIPlay;
	
	public Console(Board board) {
		this.board = board;
		this.simpGame = new SimpleGame();
		this.genGame = new GeneralGame();
		this.AIPlay = new AIPlayer();
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
	
	public void playSimpleGame(int blueAI, int redAI) {
		if (blueAI == 0 && redAI == 0) {
			board.resetGame();
			Scanner in = new Scanner(System.in);
			boolean done = false;
			
			simpGame.startGame();
			System.out.println("Gamemode: Simple");
			System.out.println("Grid Size: " + board.getgridSize());
	
			in.close();
	
			while (!done) {
				System.out.println("Current player: " + board.getTurn());

				if (board.getTurn() == "Blue") {
					if (AIPlay.findSOS(board, board.getgridSize()) == true) {
						board.makeMove(AIPlay.getPlayRow(), AIPlay.getPlayCol(), AIPlay.getPlay());
					}
					else {
						AIPlay.randomMove(board);
						board.makeMove(AIPlay.getPlayRow(), AIPlay.getPlayCol(), AIPlay.getPlay());
					}
				}
				else if (board.getTurn() == "Red") {
					if (AIPlay.findSOS(board, board.getgridSize()) == true) {
						board.makeMove(AIPlay.getPlayRow(), AIPlay.getPlayCol(), AIPlay.getPlay());
					}
					else {
						AIPlay.randomMove(board);
						board.makeMove(AIPlay.getPlayRow(), AIPlay.getPlayCol(), AIPlay.getPlay());
					}
				}
				board.updateGameState(simpGame.hasSOS(board, board.getgridSize()));
				displayBoard();
				done = isOver();
				}
		}
		
		else if (blueAI == 1 && redAI == 0) { //blue human, red computer
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

				if (board.getTurn() == "Blue") {
					System.out.println("S or O: ");
					play = in.next().charAt(0);
					System.out.print("Move at row: ");
					row = in.nextInt();
					System.out.print("Move at column: ");
					column = in.nextInt();
					if (row < 0 || row > board.getgridSize() || column < 0 || column > board.getgridSize()) {
						System.out.println("Invalid move at (" + row + "," + column + ")");
					}
					else {
						board.makeMove(row, column, play);
					}
					
				}
				else if (board.getTurn() == "Red") {
					if (AIPlay.findSOS(board, board.getgridSize()) == true) {
						board.makeMove(AIPlay.getPlayRow(), AIPlay.getPlayCol(), AIPlay.getPlay());
					}
					else {
						AIPlay.randomMove(board);
						board.makeMove(AIPlay.getPlayRow(), AIPlay.getPlayCol(), AIPlay.getPlay());
					}
				}
				board.updateGameState(simpGame.hasSOS(board, board.getgridSize()));
				displayBoard();
				done = isOver();
				}
	
			in.close();
		} 
		
		else if (blueAI == 0 && redAI == 1) { //blue computer, red human
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

				if (board.getTurn() == "Blue") {
					if (AIPlay.findSOS(board, board.getgridSize()) == true) {
						board.makeMove(AIPlay.getPlayRow(), AIPlay.getPlayCol(), AIPlay.getPlay());
					}
					else {
						AIPlay.randomMove(board);
						board.makeMove(AIPlay.getPlayRow(), AIPlay.getPlayCol(), AIPlay.getPlay());
					}
					
				}
				else if (board.getTurn() == "Red") {
					System.out.println("S or O: ");
					play = in.next().charAt(0);
					System.out.print("Move at row: ");
					row = in.nextInt();
					System.out.print("Move at column: ");
					column = in.nextInt();
					if (row < 0 || row > board.getgridSize() || column < 0 || column > board.getgridSize()) {
						System.out.println("Invalid move at (" + row + "," + column + ")");
					}
					else {
						board.makeMove(row, column, play);
					}
					
				}
				board.updateGameState(simpGame.hasSOS(board, board.getgridSize()));
				displayBoard();
				done = isOver();
				}
	
			in.close();
		} 
		
		else if (blueAI == 1 && redAI == 1) { //two humans
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
	}
	
	public void playGeneralGame(int blueAI, int redAI) {
		if (blueAI == 0 && redAI == 0) { //two computers
			board.resetGame();
			Scanner in = new Scanner(System.in);
			boolean done = false;
			
			genGame.startGame();
			System.out.println("Gamemode: General");
			System.out.println("Grid Size: " + board.getgridSize());
			in.close();
	
			while (!done) {
				System.out.println("Current player: " + board.getTurn());
				if (board.getTurn() == "Blue") {
					if (AIPlay.findSOS(board, board.getgridSize()) == true) {
						board.makeMove(AIPlay.getPlayRow(), AIPlay.getPlayCol(), AIPlay.getPlay());
						if (genGame.hasSOS(board, board.getgridSize())) {
							if (board.getTurn() == "Red") {
								genGame.addBluePoints(1);
							}
						}
					}
					else {
						AIPlay.randomMove(board);
						board.makeMove(AIPlay.getPlayRow(), AIPlay.getPlayCol(), AIPlay.getPlay());
					}
					
				}
				else if (board.getTurn() == "Red") {
					if (AIPlay.findSOS(board, board.getgridSize()) == true) {
						board.makeMove(AIPlay.getPlayRow(), AIPlay.getPlayCol(), AIPlay.getPlay());
						if (genGame.hasSOS(board, board.getgridSize())) {
							if (board.getTurn() == "Blue") {
								genGame.addRedPoints(1);
							}
						}
					}
					else {
						AIPlay.randomMove(board);
						board.makeMove(AIPlay.getPlayRow(), AIPlay.getPlayCol(), AIPlay.getPlay());
					}
				}
					System.out.println("Blue Points: " + genGame.getBluePoints());
					System.out.println("Red Points: " + genGame.getRedPoints());
					board.updateGameState(board.boardFull());
					displayBoard();
					done = isOver();
				}
			
			
			if (genGame.getBluePoints() > genGame.getRedPoints()) {
				System.out.println("Blue Wins");
			} else if (genGame.getBluePoints() < genGame.getRedPoints()) {
				System.out.println("Red Wins");
			} else if (genGame.getBluePoints() == genGame.getRedPoints()) {
				System.out.println("Draw! No one wins");
			}
		}
		else if (blueAI == 1 && redAI == 0) { //blue human, red computer
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
				if (board.getTurn() == "Blue") {
					System.out.println("S or O: ");
					play = in.next().charAt(0);
					System.out.print("Move at row: ");
					row = in.nextInt();
					System.out.print("Move at column: ");
					column = in.nextInt();
					if (row < 0 || row > board.getgridSize() || column < 0 || column > board.getgridSize()) {
						System.out.println("Invalid move at (" + row + "," + column + ")");
					}
					else {
						board.makeMove(row, column, play);
						if (genGame.hasSOS(board, board.getgridSize())) {
							if (board.getTurn() == "Red") {
								genGame.addBluePoints(1);
							}
						}
					}
					
				}
				else if (board.getTurn() == "Red") {
					if (AIPlay.findSOS(board, board.getgridSize()) == true) {
						board.makeMove(AIPlay.getPlayRow(), AIPlay.getPlayCol(), AIPlay.getPlay());
						if (genGame.hasSOS(board, board.getgridSize())) {
							if (board.getTurn() == "Blue") {
								genGame.addRedPoints(1);
							}
						}
					}
					else {
						AIPlay.randomMove(board);
						board.makeMove(AIPlay.getPlayRow(), AIPlay.getPlayCol(), AIPlay.getPlay());
					}
				}
					System.out.println("Blue Points: " + genGame.getBluePoints());
					System.out.println("Red Points: " + genGame.getRedPoints());
					board.updateGameState(board.boardFull());
					displayBoard();
					done = isOver();
				}
			
			
			if (genGame.getBluePoints() > genGame.getRedPoints()) {
				System.out.println("Blue Wins");
			} else if (genGame.getBluePoints() < genGame.getRedPoints()) {
				System.out.println("Red Wins");
			} else if (genGame.getBluePoints() == genGame.getRedPoints()) {
				System.out.println("Draw! No one wins");
			}
	
			in.close();
		
		}	else if (blueAI == 0 && redAI == 1) { //blue human, red computer
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
				if (board.getTurn() == "Blue") {
					if (AIPlay.findSOS(board, board.getgridSize()) == true) {
						board.makeMove(AIPlay.getPlayRow(), AIPlay.getPlayCol(), AIPlay.getPlay());
						if (genGame.hasSOS(board, board.getgridSize())) {
							if (board.getTurn() == "Red") {
								genGame.addBluePoints(1);
							}
						}
					}
					else {
						AIPlay.randomMove(board);
						board.makeMove(AIPlay.getPlayRow(), AIPlay.getPlayCol(), AIPlay.getPlay());
					}
					
					
				}
				else if (board.getTurn() == "Red") {
					System.out.println("S or O: ");
					play = in.next().charAt(0);
					System.out.print("Move at row: ");
					row = in.nextInt();
					System.out.print("Move at column: ");
					column = in.nextInt();
					if (row < 0 || row > board.getgridSize() || column < 0 || column > board.getgridSize()) {
						System.out.println("Invalid move at (" + row + "," + column + ")");
					}
					else {
						board.makeMove(row, column, play);
						if (genGame.hasSOS(board, board.getgridSize())) {
							if (board.getTurn() == "Blue") {
								genGame.addRedPoints(1);
							}
						}
					}
				}
					System.out.println("Blue Points: " + genGame.getBluePoints());
					System.out.println("Red Points: " + genGame.getRedPoints());
					board.updateGameState(board.boardFull());
					displayBoard();
					done = isOver();
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
		
		else if (blueAI == 1 && redAI == 1) {
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
	
	public void play() {
		if (board.getGameMode() == 0) {
			if ((board.getBlueAIPlayer() == 0) && (board.getRedAIPlayer() == 0)) { //two computers
				playSimpleGame(0,0);
			} else if ((board.getBlueAIPlayer() == 1) && (board.getRedAIPlayer() == 0)) { //blue human, red AI
				playSimpleGame(1,0);
			} else if ((board.getBlueAIPlayer() == 0) && (board.getRedAIPlayer() == 1)) { //blue AI, red human
				playSimpleGame(0,1);
			}  else if ((board.getBlueAIPlayer() == 1) && (board.getRedAIPlayer() == 1)) { //two humans
				playSimpleGame(1,1);
			}
		}
		
		else if (board.getGameMode() == 0) {
			if ((board.getBlueAIPlayer() == 0) && (board.getRedAIPlayer() == 0)) { //two computers
				playGeneralGame(0,0);
			} else if ((board.getBlueAIPlayer() == 1) && (board.getRedAIPlayer() == 0)) { //blue human, red AI
				playGeneralGame(1,0);
			} else if ((board.getBlueAIPlayer() == 0) && (board.getRedAIPlayer() == 1)) { //blue AI, red human
				playGeneralGame(0,1);
			}  else if ((board.getBlueAIPlayer() == 1) && (board.getRedAIPlayer() == 1)) { //two humans
				playGeneralGame(1,1);
			}
		}

	}
	
	public static void main(String[] args) {

		new Console(new Board()).play();
	}

}
