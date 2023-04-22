package sprint4.test;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import sprint4.product.Board;
import sprint4.product.SimpleGame;
import sprint4.product.GeneralGame;
import sprint4.product.AIPlayer;

class TestComputer {
	private Board board;
	private SimpleGame simpGame;
	private GeneralGame genGame;
	private AIPlayer AIPlay;

	@Before
	public void setUp() throws Exception {
		board = new Board();
	}

	@After
	public void tearDown() throws Exception {
	}
	
	@Test
	
	public void testBlueAIWin() {
		board.updateGameState(simpGame.hasSOS(board, board.getgridSize()));
		if (AIPlay.findSOS(board) == true) {
			board.makeMove(AIPlay.getPlayRow(), AIPlay.getPlayCol(), AIPlay.getPlay());
		}
		else {
			AIPlay.randomMove(board);
			board.makeMove(AIPlay.getPlayRow(), AIPlay.getPlayCol(), AIPlay.getPlay());
		}
		board.makeMove(2, 2, 'S');
		assertEquals(true, simpGame.hasSOS(board, board.getgridSize()));
	}

}
