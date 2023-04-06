package sprint3.test;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import sprint3.product.Board;
import sprint3.product.Board.Cell;

public class TestBlueMoves {

	private Board board;

	@Before
	public void setUp() throws Exception {
		board = new Board();
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testBlueTurnMoveVacantCell() {
		board.makeMove(0, 0);
		assertEquals("", board.getCell(0, 0), Cell.BLUE);
		assertEquals("", board.getTurn(), "Red");
	}

	@Test
	public void testBlueTurnMoveNonVacantCell() {
		board.makeMove(0, 0);
		board.makeMove(1, 0);
		assertEquals("", board.getCell(1, 0), Cell.RED);
		assertEquals("", board.getTurn(), "Blue");
		board.makeMove(0, 0);
		assertEquals("", board.getTurn(), "Blue");
	}

	@Test
	public void testBlueTurnInvalidRowMove() {
		board.makeMove((board.getgridSize() + 1), 0);
		assertEquals("", board.getTurn(), "Blue");
	}

	@Test
	public void testBlueTurnInvalidColumnMove() {
		board.makeMove(0, (board.getgridSize() + 1));
		assertEquals("", board.getTurn(), "Blue");
	}

}
