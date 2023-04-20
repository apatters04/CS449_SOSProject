package sprint4.product;

import sprint4.product.Board.Cell;
import java.util.Random;

public class AIPlayer {
	private int r;
	private int c;
	private char play = ' ';
	
	private Random rand = new Random();
	
	public boolean findSOS(Board board, int size) {

		for (int row = 0; row < board.getgridSize(); row++) {
			for (int col = 0; col < board.getgridSize(); col++) {
				if (board.getCell(row, col) == Cell.EMPTY) {
					
					//check if O move would make a win
					if ((board.getCell(row + 1, col) == Cell.ESS) && (board.getCell(row - 1, col) == Cell.ESS)) {
						play = 'O';
						r = row;
						c = col;
						return true;
					}
					
					else if ((board.getCell(row, col + 1) == Cell.ESS) && (board.getCell(row, col - 1) == Cell.ESS)) {
						play = 'O';
						r = row;
						c = col;
						return true;
					}
					else if ((board.getCell(row + 1, col - 1) == Cell.ESS) && (board.getCell(row - 1, col + 1) == Cell.ESS)) {
						play = 'O';
						r = row;
						c = col;
						return true;
					}
					else if ((board.getCell(row - 1, col - 1) == Cell.ESS) && (board.getCell(row + 1, col + 1) == Cell.ESS)) {
						play = 'O';
						r = row;
						c = col;
						return true;
					}
					
					//check if S move would make a win
					
					if ((board.getCell(row - 1, col) == Cell.OH) && (board.getCell(row - 2, col) == Cell.ESS)) {
						play = 'S';
						r = row;
						c = col;
						return true;
					}
					
					else if ((board.getCell(row + 1, col) == Cell.OH) && (board.getCell(row + 2, col) == Cell.ESS)) {
						play = 'S';
						r = row;
						c = col;
						return true;
					}
					else if ((board.getCell(row, col - 1) == Cell.OH) && (board.getCell(row, col - 2) == Cell.ESS)) {
						play = 'S';
						r = row;
						c = col;
						return true;
					}
					else if ((board.getCell(row, col + 1) == Cell.OH) && (board.getCell(row, col + 2) == Cell.ESS)) {
						play = 'S';
						r = row;
						c = col;
						return true;
					}
					
					else if ((board.getCell(row + 1, col - 1) == Cell.OH) && (board.getCell(row + 2, col - 2) == Cell.ESS)) {
						play = 'S';
						r = row;
						c = col;
						return true;
					}
					else if ((board.getCell(row + 1, col + 1) == Cell.OH) && (board.getCell(row + 2, col + 2) == Cell.ESS)) {
						play = 'S';
						r = row;
						c = col;
						return true;
					}
					else if ((board.getCell(row - 1, col - 1) == Cell.OH) && (board.getCell(row - 2, col - 2) == Cell.ESS)) {
						play = 'S';
						r = row;
						c = col;
						return true;
					}
					else if ((board.getCell(row - 1, col + 1) == Cell.OH) && (board.getCell(row - 2, col + 2) == Cell.ESS)) {
						play = 'S';
						r = row;
						c = col;
						return true;
					}
				}
				
			}
		}
		
		return false;
	}
	
	public void randomMove(Board board) {

		
		int randRow = rand.nextInt(board.getgridSize());
		int randCol = rand.nextInt(board.getgridSize());
		
		while (board.getCell(randRow, randCol) != Cell.EMPTY) {
			randRow = rand.nextInt(board.getgridSize());
			randCol = rand.nextInt(board.getgridSize());
		}
		
		int randPlay = rand.nextInt(2);
		
		r = randRow;
		c = randCol;
		if (randPlay == 0) {
			play = 'O';
		}
		else if (randPlay == 1) {
			play = 'S';
		}
	}
	
	public int getPlayRow() {
		return r;
	}
	
	public int getPlayCol() {
		return c;
	}
	
	public char getPlay() {
		return play;
	}
	
	
}
