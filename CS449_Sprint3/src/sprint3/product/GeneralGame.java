package sprint3.product;

import sprint3.product.Board;

public class GeneralGame extends SimpleGame {
	
	private Board board;
	private int blueScore = 0;
	private int redScore = 0;
	
	public void hasSOS() {
		super.hasSOS(board, board.getgridSize());
	}
	
	public void  addBluePoints(int points) {
		blueScore += points;
	}	
	public void addRedPoints(int points) {
		redScore += points;
	}
	
	public int getBluePoints() {
		return blueScore;
	}	
	
	public int getRedPoints() {
		return redScore;
	}
	
	public boolean boardFull() {
		if (board.boardFull()) {
			return true;
		}
		return false;
	}

}
