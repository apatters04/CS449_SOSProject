package sprint5.test;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import sprint5.product.Board;
import sprint5.product.GeneralGame;
import sprint5.product.Board.Cell;

public class GeneralGameTest {
	private Board board;
	private GeneralGame genGame = new GeneralGame();

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
		board.makeMove(0, 1, 'O');
		board.makeMove(0, 2, 'S');		
		board.makeMove(1, 0, 'S');
		board.makeMove(1, 1, 'O');
		board.makeMove(1, 2, 'S');		
		board.makeMove(2, 0, 'S');
		board.makeMove(2, 1, 'O');
		board.makeMove(2, 2, 'S');
		assertEquals(true, genGame.hasSOS(board, board.getgridSize()));
	}	
	
	@Test
	public void testRedWin() {
		board.makeMove(0, 1, 'O'); //Blue
		board.makeMove(0, 0, 'S'); //Red
		board.makeMove(0, 2, 'O'); //Blue	
		board.makeMove(1, 0, 'O');
		board.makeMove(1, 1, 'O');
		board.makeMove(2, 0, 'S');		
		board.makeMove(2, 1, 'S');
		board.makeMove(1, 2, 'O');
		board.makeMove(2, 2, 'S');
		assertEquals(true, genGame.hasSOS(board, board.getgridSize()));
	}

	
	//Test Draw
	@Test
	public void testDraw() {
		board.makeMove(0, 0, 'S');
		board.makeMove(0, 1, 'O');
		board.makeMove(0, 2, 'S');
		board.makeMove(1, 0, 'S');
		board.makeMove(1, 1, 'O');
		board.makeMove(1, 2, 'S');
		board.makeMove(2, 0, 'S');
		board.makeMove(2, 1, 'S');
		board.makeMove(2, 2, 'S');
		
		board.updateGameState(genGame.hasSOS(board, board.getgridSize()));

		assertEquals(false, genGame.hasSOS(board, board.getgridSize()));
		
	}
	

}
