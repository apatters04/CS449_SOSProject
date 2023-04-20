package sprint4.product;

import sprint4.product.Board.Cell;

public class SimpleGame {
	
	
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
							board.setCell(row, col, Cell.USED);
							break;
						} else if ((board.getCell(row - 1, col) == Cell.ESS) && (board.getCell(row + 1, col) == Cell.ESS)) {
							sosState = true;
							board.setCell(row, col, Cell.USED);
							break;
						} else if ((board.getCell(row - 1, col + 1) == Cell.ESS) && (board.getCell(row + 1, col - 1) == Cell.ESS)) {
							sosState = true;
							board.setCell(row, col, Cell.USED);
							break;
						} else if ((board.getCell(row, col - 1) == Cell.ESS) && (board.getCell(row, col + 1) == Cell.ESS)) {
							sosState = true;
							board.setCell(row, col, Cell.USED);
							break;
						}
					} 
					
					else if ((row - 1) < 0) { //top row check
						if ((board.getCell(row, col - 1) == Cell.ESS) && (board.getCell(row, col + 1) == Cell.ESS)){
							sosState = true;
							board.setCell(row, col, Cell.USED);
							break;
						}
					}	
					
					else if ((row + 1) >= board.getgridSize()) { //bottom row check
						if ((board.getCell(row, col - 1) == Cell.ESS) && (board.getCell(row, col + 1) == Cell.ESS)){
							sosState = true;
							board.setCell(row, col, Cell.USED);
							break;
						}
					}
					
					else if ((col - 1) < 0) { //left column check
						if ((board.getCell(row - 1, col) == Cell.ESS) && (board.getCell(row + 1, col) == Cell.ESS)){
							sosState = true;
							board.setCell(row, col, Cell.USED);
							break;
						}
					}
					
					else if ((col + 1) >= board.getgridSize()) { //right column check
						if ((board.getCell(row - 1, col) == Cell.ESS) && (board.getCell(row + 1, col) == Cell.ESS)){
							sosState = true;
							board.setCell(row, col, Cell.USED);
							break;
						}
					}
					
					else if ( ((row - 1) < 0 && (col - 1) < 0) ||  ((row - 1) < 0 && (col + 1) >= board.getgridSize()) || ((row + 1) >= board.getgridSize() && (col + 1) >= board.getgridSize()) || ((row + 1) >= board.getgridSize() && (col - 1) < 0)) {
						sosState = false; //if O is in a corner, SOS is false and ignored.
						break;
					}
					
				}
			}
		}
		
		return sosState;
	}
	
	
}
