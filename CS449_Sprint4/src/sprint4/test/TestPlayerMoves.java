package sprint4.test;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import sprint4.product.Board;
import sprint4.product.Board.Cell;

class TestPlayerMoves {

	private Board board;

	@Before
	public void setUp() throws Exception {
		board = new Board();
	}

	@After
	public void tearDown() throws Exception {
	}
	
	//Test Blue Moves
	@Test
	public void testBlueSTurnVacantCell() {
		board.makeMove(0, 0, 'S');
		assertEquals("", board.getCell(0, 0), Cell.ESS);
		assertEquals("", board.getTurn(), "Red");
	}	
	
	@Test
	public void testBlueOTurnVacantCell() {
		board.makeMove(0, 0, 'O');
		assertEquals("", board.getCell(0, 0), Cell.OH);
		assertEquals("", board.getTurn(), "Red");
	}
	
	
	@Test
	public void testBlueTurnMoveNonVacantCell() {
		board.makeMove(0, 0, 'S');
		board.makeMove(1, 0, 'O');
		assertEquals("", board.getCell(1, 0), Cell.OH);
		assertEquals("", board.getTurn(), "Blue");
		board.makeMove(0, 0, 'O');
		assertEquals("", board.getTurn(), "Blue");
	}
	
	@Test
	public void testBlueTurnInvalidRowMove() {
		board.makeMove((board.getgridSize() + 1), 0, 'S');
		assertEquals("", board.getTurn(), "Blue");
	}

	@Test
	public void testBlueTurnInvalidColumnMove() {
		board.makeMove(0, (board.getgridSize() + 1), 'S');
		assertEquals("", board.getTurn(), "Blue");
	}
	
	//Test Red Moves
	@Test
	public void testRedSTurnVacantCell() {
		board.makeMove(0, 0, 'S');
		board.makeMove(1, 0, 'S');
		assertEquals("", board.getCell(0, 0), Cell.ESS);
		assertEquals("", board.getTurn(), "Blue");
	}	
	
	@Test
	public void testRedOTurnVacantCell() {
		board.makeMove(0, 0, 'S');
		board.makeMove(1, 0, 'O');
		assertEquals("", board.getCell(1, 0), Cell.OH);
		assertEquals("", board.getTurn(), "Blue");
	}

	@Test
	public void testRedTurnInvalidRowMove() {
		board.makeMove(0, 0, 'S');
		board.makeMove((board.getgridSize() + 1), 0, 'S');
		assertEquals("", board.getTurn(), "Red");
	}

	@Test
	public void testRedTurnInvalidColumnMove() {
		board.makeMove(0, 0, 'S');
		board.makeMove(0, (board.getgridSize() + 1), 'S');
		assertEquals("", board.getTurn(), "Red");
	}
}
