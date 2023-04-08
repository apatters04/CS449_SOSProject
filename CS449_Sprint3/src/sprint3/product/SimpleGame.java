package sprint3.product;

import sprint3.product.Board.Cell;

public class SimpleGame {
	
	private Board board;
	
	public void startGame() {
		System.out.println("Welcome to an SOS Game");
	}
	
	public boolean hasSOS(Board board, int size) {
		boolean sosState = false;
		for (int row = 0; row < size; row++) {
			for (int col = 0; col < size; col++) {
				if (board.getCell(row, col) == Cell.OH) { // find O, check cells around O for S
					
					if ( ((row - 1) >= 0) && ((row + 1) < board.getgridSize()) && ((col - 1) >= 0) && ((col + 1) < board.getgridSize())) { //not an edge case
						if ((board.getCell(row - 1, col - 1) == Cell.ESS) && (board.getCell(row + 1, col + 1) == Cell.ESS)) {
							sosState = true;
							break;
						} else if ((board.getCell(row - 1, col) == Cell.ESS) && (board.getCell(row + 1, col) == Cell.ESS)) {
							sosState = true;
							break;
						} else if ((board.getCell(row - 1, col + 1) == Cell.ESS) && (board.getCell(row + 1, col - 1) == Cell.ESS)) {
							sosState = true;
							break;
						} else if ((board.getCell(row, col - 1) == Cell.ESS) && (board.getCell(row, col + 1) == Cell.ESS)) {
							sosState = true;
							break;
						}
					}
					
				}
			}
		}
		
		return sosState;
	}
	
	
}
