package sprint2.product;

import java.util.Scanner;

public class Console {
	private Board board;
	
	public Console(Board board) {
		this.board = board;
		
		@SuppressWarnings("resource")
		Scanner mySize = new Scanner(System.in);
		System.out.println("Enter grid size: ");
		int size = mySize.nextInt();
		
		board.setgridSize(size);
		
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
	
	public static void main(String[] args) {

		new Console(new Board()).displayBoard();;
	}

}
