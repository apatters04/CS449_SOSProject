package sprint2.test;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import sprint2.product.Board;
import sprint2.product.Board.Cell;
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
	public void testNewBoard() {
		for (int row = 0; row < board.getgridSize(); row++) {
			for (int col = 0; col < board.getgridSize(); col++) {
				assertEquals("", board.getCell(row, col), Cell.EMPTY);
			}
		}
	}
	
	@Test
	public void testEmptyBoard() {
		new Console(board).displayBoard();
	}
	
	@Test
	public void testNonEmptyBoard() {
		board.makeMove(0, 0);
		board.makeMove(1, 2);
		new Console(board).displayBoard();
	}
	
	@Test
	public void testInvalidRow() {
		assertEquals("", board.getCell((board.getgridSize()+1), 0), null);
	}
	
	@Test
	public void testInvalidCol() {
		assertEquals("", board.getCell(0, (board.getgridSize()+1)), null);
	}

}
