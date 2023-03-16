package sprint2.product;

import java.util.Scanner;
import sprint2.product.Board.Cell;

public class Console {
	private Board board;
	
	public Console(Board board) {
		this.board = board;

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
	
	private char symbol(Cell cell) {
		if (cell==Cell.CROSS)
			return 'S';
		else
			if (cell==Cell.NOUGHT)
				return 'O';
			else return ' ';
	}
	
	
	
	public static void main(String[] args) {

		new Console(new Board()).displayBoard();;
	}

}
