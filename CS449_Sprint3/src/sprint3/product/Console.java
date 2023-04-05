package sprint3.product;

import java.util.Scanner;

import sprint3.product.Board.Cell;

public class Console {
	private Board board;
	private String choice;
	
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
	
	private String symbol(Cell cell) {
		if (cell==Cell.BLUE)
			return "BLUE";
		else
			if (cell==Cell.RED)
				return "BLUE";
			else return " ";
	}
	
	public static void main(String[] args) {

		new Console(new Board()).displayBoard();;

	}

}
