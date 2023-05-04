package sprint5.test;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import sprint5.product.Board;
import sprint5.product.SimpleGame;

public class SimpleGameTest {
	private Board board;
	private SimpleGame simpGame = new SimpleGame();

	@Before
	public void setUp() throws Exception {
		board = new Board();
	}

	@After
	public void tearDown() throws Exception {
	}
	
	//Test Wins
	@Test
	public void testBlueWin() {
		board.makeMove(0, 0, 'S');
		board.updateGameState(simpGame.hasSOS(board, board.getgridSize()));
		board.makeMove(1, 1, 'O');
		board.updateGameState(simpGame.hasSOS(board, board.getgridSize()));
		board.makeMove(2, 2, 'S');
		assertEquals(true, simpGame.hasSOS(board, board.getgridSize()));
	}
	
	@Test
	public void testRedWin() {
		board.makeMove(0, 1, 'O');
		board.makeMove(0, 0, 'S');
		board.updateGameState(simpGame.hasSOS(board, board.getgridSize()));
		board.makeMove(1, 1, 'O');
		board.updateGameState(simpGame.hasSOS(board, board.getgridSize()));
		board.makeMove(2, 2, 'S');
		assertEquals(true, simpGame.hasSOS(board, board.getgridSize()));
	}
	
	//Test Draw
	@Test
	public void testDraw() {
		board.makeMove(0, 0, 'S');
		board.makeMove(0, 1, 'S');
		board.makeMove(0, 2, 'S');
		board.makeMove(1, 0, 'S');
		board.makeMove(1, 1, 'S');
		board.makeMove(1, 2, 'S');
		board.makeMove(2, 0, 'S');
		board.makeMove(2, 1, 'S');
		board.makeMove(2, 2, 'S');
		
		board.updateGameState(simpGame.hasSOS(board, board.getgridSize()));

		assertEquals(false, simpGame.hasSOS(board, board.getgridSize()));
		
	}
	

}
