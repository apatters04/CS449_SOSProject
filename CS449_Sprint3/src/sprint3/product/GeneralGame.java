package sprint3.product;

public class GeneralGame extends SimpleGame {
	
	private Board board;
	
	public void hasSOS() {
		super.hasSOS(board, board.getgridSize());
	}
}
