package sprint2.test;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import sprint2.product.Board;
import sprint2.product.Console;

public class TestBoard {
	private Board board;
	@Before
	public void setUp() throws Exception{
		board = new Board();
	}
	
	@After
	public void tearDown() throws Exception{
	}
	
	@Test
	public void testEmptyBoard() {
		new Console(board).displayBoard();
	}
	
	@Test
	public void testNonEmptyBoard() {
		board.makeMove(0, 0);
		board.makeMove(1, 1);
		new Console(board).displayBoard();
	}
}
