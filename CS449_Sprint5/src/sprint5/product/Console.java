package sprint5.product;

import java.util.Scanner;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import sprint5.product.GeneralGame;
import sprint5.product.SimpleGame;
import sprint5.product.Board.GameState;


public class Console {
	private Board board;
	private SimpleGame simpGame;
	private GeneralGame genGame;
	private AIPlayer AIPlay;
	
	private ArrayList<String> gameRecords = new ArrayList<String>();
	
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
				gameRecords.add("|" + board.getCell(row, col));
				System.out.print("|" + board.getCell(row, col));
			}
			gameRecords.add("|\n");
			System.out.println("|");
		}
		
		System.out.println("----------");
		
	}
	
	public void addToRecords() {
		gameRecords.add("----------" + "\n");
		for (int row = 0; row < board.getgridSize(); row++) {
			for(int col = 0; col < board.getgridSize(); col++) {
				gameRecords.add("|" + board.getCell(row, col));
			}
			gameRecords.add("|" + "\n");
		}
	
		gameRecords.add("----------" + "\n");
	}
	
	public void recordGame() {
		try {
			FileWriter gameRecorder = new FileWriter("gamerecord.txt", false); //overwrites every new game
			
			if (board.getGameMode() == 0) { //write gamemode
				gameRecorder.write("Simple SOS Game\n");
			} else if (board.getGameMode() == 1) {

				gameRecorder.write("General SOS Game\n");
			}
			
			
			if (board.getBlueAIPlayer() == 0 && board.getRedAIPlayer() == 0) {
				gameRecorder.write("Blue Player: Computer\n");
				gameRecorder.write("Red Player: Computer\n");
			} else if (board.getBlueAIPlayer() == 1 && board.getRedAIPlayer() == 0) {
				gameRecorder.write("Blue Player: Human\n");
				gameRecorder.write("Red Player: Computer\n");
			} else if (board.getBlueAIPlayer() == 0 && board.getRedAIPlayer() == 1) {
				gameRecorder.write("Blue Player: Computer\n");
				gameRecorder.write("Red Player: Human\n");
			} else if (board.getBlueAIPlayer() == 1 && board.getRedAIPlayer() == 1) {
				gameRecorder.write("Blue Player: Human\n");
				gameRecorder.write("Red Player: Human\n");
			}
			
			
			gameRecorder.write("Board Size: " + board.getgridSize() + "\n"); //write board size
			
			for (int i = 0; i < gameRecords.size(); i++) { //write all gamemoves
				gameRecorder.write(gameRecords.get(i));
			}
			
			if (((board.getGameState() == GameState.DRAW) || ((genGame.getBluePoints() == genGame.getRedPoints()) && (board.getGameMode() == 1))) ) { //writing winner
				if (board.getGameMode() == 1) {
					gameRecorder.write("Blue Points: " + genGame.getBluePoints() + "\n");
					gameRecorder.write("Red Points: " + genGame.getRedPoints() + "\n");
				}
				gameRecorder.write("DRAW!");
			}
			else if (((board.getGameState() == GameState.BLUE_WIN) && (board.getGameMode() == 0)) || ((genGame.getBluePoints() > genGame.getRedPoints()) && (board.getGameMode() == 1))) {
				if (board.getGameMode() == 1) {
					gameRecorder.write("Blue Points: " + genGame.getBluePoints() + "\n");
					gameRecorder.write("Red Points: " + genGame.getRedPoints() + "\n");
				}
				gameRecorder.write("BLUE WINS!");
			}
			else if (((board.getGameState() == GameState.BLUE_WIN) && (board.getGameMode() == 0)) || ((genGame.getBluePoints() < genGame.getRedPoints()) && (board.getGameMode() == 1))) {
				if (board.getGameMode() == 1) {
					gameRecorder.write("Blue Points: " + genGame.getBluePoints() + "\n");
					gameRecorder.write("Red Points: " + genGame.getRedPoints() + "\n");
				}
				gameRecorder.write("RED WINS!");
			}
			else if (board.getGameState() == GameState.END) {
				gameRecorder.write("GAME END");
			}
			gameRecorder.close();
			
		} catch (IOException e) {
			System.out.println("Could not record game");
			e.printStackTrace();
		}
	}
	
	public boolean isOver() {
		GameState state = board.getGameState();
		if (state == GameState.PLAYING) {
			return false;
		}
		if ((board.getGameState() == GameState.DRAW) || ((board.getGameState() == GameState.END) && (genGame.getBluePoints() == genGame.getRedPoints()) && (board.getGameMode() == 1))) {
			System.out.println("DRAW!");
		}
		else if (((board.getGameState() == GameState.BLUE_WIN) && (board.getGameMode() == 0)) || ((genGame.getBluePoints() > genGame.getRedPoints()) && (board.getGameMode() == 1))) {
			System.out.println("BLUE WINS!");
		}
		else if (((board.getGameState() == GameState.BLUE_WIN) && (board.getGameMode() == 0)) || ((genGame.getBluePoints() < genGame.getRedPoints()) && (board.getGameMode() == 1))) {
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
					if (AIPlay.findSOS(board) == true) {
						board.makeMove(AIPlay.getPlayRow(), AIPlay.getPlayCol(), AIPlay.getPlay());
					}
					else {
						AIPlay.randomMove(board);
						board.makeMove(AIPlay.getPlayRow(), AIPlay.getPlayCol(), AIPlay.getPlay());
					}
				}
				else if (board.getTurn() == "Red") {
					if (AIPlay.findSOS(board) == true) {
						board.makeMove(AIPlay.getPlayRow(), AIPlay.getPlayCol(), AIPlay.getPlay());
					}
					else {
						AIPlay.randomMove(board);
						board.makeMove(AIPlay.getPlayRow(), AIPlay.getPlayCol(), AIPlay.getPlay());
					}
				}
				board.updateGameState(simpGame.hasSOS(board, board.getgridSize()));
				displayBoard();
				addToRecords();
				done = isOver();
				}
			recordGame();
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
					if (AIPlay.findSOS(board) == true) {
						board.makeMove(AIPlay.getPlayRow(), AIPlay.getPlayCol(), AIPlay.getPlay());
					}
					else {
						AIPlay.randomMove(board);
						board.makeMove(AIPlay.getPlayRow(), AIPlay.getPlayCol(), AIPlay.getPlay());
					}
				}
				board.updateGameState(simpGame.hasSOS(board, board.getgridSize()));
				displayBoard();
				addToRecords();
				done = isOver();
				}

			recordGame();
	
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
					if (AIPlay.findSOS(board) == true) {
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
				addToRecords();
				done = isOver();
				}

			recordGame();
	
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
					addToRecords();
					done = isOver();
				}

				recordGame();
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
					if (AIPlay.findSOS(board) == true) {
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
					if (AIPlay.findSOS(board) == true) {
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
					addToRecords();
					done = isOver();
				}

			
			
			if (genGame.getBluePoints() > genGame.getRedPoints()) {
				System.out.println("Blue Wins");
			} else if (genGame.getBluePoints() < genGame.getRedPoints()) {
				System.out.println("Red Wins");
			} else if (genGame.getBluePoints() == genGame.getRedPoints()) {
				System.out.println("Draw! No one wins");
			}

			recordGame();
			in.close();
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
					if (AIPlay.findSOS(board) == true) {
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
					addToRecords();
					done = isOver();
				}
			
			
			if (genGame.getBluePoints() > genGame.getRedPoints()) {
				System.out.println("Blue Wins");
			} else if (genGame.getBluePoints() < genGame.getRedPoints()) {
				System.out.println("Red Wins");
			} else if (genGame.getBluePoints() == genGame.getRedPoints()) {
				System.out.println("Draw! No one wins");
			}
			

			recordGame();
	
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
					if (AIPlay.findSOS(board) == true) {
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
					addToRecords();
					done = isOver();
				}
			
			
			if (genGame.getBluePoints() > genGame.getRedPoints()) {
				System.out.println("Blue Wins");
			} else if (genGame.getBluePoints() < genGame.getRedPoints()) {
				System.out.println("Red Wins");
			} else if (genGame.getBluePoints() == genGame.getRedPoints()) {
				System.out.println("Draw! No one wins");
			}
			recordGame();
	
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
					addToRecords();
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
			recordGame();
	
			in.close();
		}

	}
	
	public void play() {

		board.resetGame();
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
		
		else if (board.getGameMode() == 1) {
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
