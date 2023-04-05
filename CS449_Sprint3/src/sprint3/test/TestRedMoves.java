package sprint3.test;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import sprint3.product.Board;
import sprint3.product.Board.Cell;

public class TestRedMoves {

	private Board board;

	@Before
	public void setUp() throws Exception {
		board = new Board();
		board.makeMove(1, 1);
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testRedTurnMoveVacantCell() {
		board.getTurnType();
		board.makeMove(0, 0);
		assertEquals("", board.getCell(0, 0), Cell.RED);
		assertEquals("", board.getTurn(), "Blue");
	}

	@Test
	public void testRedTurnMoveNonVacantCell() {
		board.getTurnType();
		board.makeMove(0, 0);
		board.getTurnType();
		board.makeMove(1, 0);
		assertEquals("", board.getCell(1, 0), Cell.BLUE);
		assertEquals("", board.getTurn(), "Red");
		board.getTurnType();
		board.makeMove(1, 0);
		assertEquals("", board.getTurn(), "Red");
	}

	@Test
	public void testRedTurnInvalidRowMove() {
		board.getTurnType();
		board.makeMove((board.getgridSize() + 1), 0);
		assertEquals("", board.getTurn(), "Red");
	}

	@Test
	public void testRedTurnInvalidColumnMove() {
		board.getTurnType();
		board.makeMove(0, (board.getgridSize() + 1));
		assertEquals("", board.getTurn(), "Red");
	}

}
