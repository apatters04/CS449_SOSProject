package sprint3.test;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import sprint3.product.Board;
import sprint3.product.Board.Cell;

class SimpleGameTest {
	private Board board;


	@Before
	public void setUp() throws Exception {
		board = new Board();
	}

	@After
	public void tearDown() throws Exception {
	}
	
	@Test
	public void testBlueSTurnVacantCell() {
		board.makeMove(0, 0, 'S');
		assertEquals("", board.getCell(0, 0), Cell.BLUE);
		assertEquals("", board.getTurn(), "Red");
	}	
	
	@Test
	public void testBlueOTurnVacantCell() {
		board.makeMove(0, 0, 'O');
		assertEquals("", board.getCell(0, 0), Cell.BLUE);
		assertEquals("", board.getTurn(), "Red");
	}
	
	@Test
	public void testRedSTurnVacantCell() {
		board.makeMove(0, 0, 'S');
		board.makeMove(1, 0, 'S');
		assertEquals("", board.getCell(0, 0), Cell.RED);
		assertEquals("", board.getTurn(), "Blue");
	}	
	
	@Test
	public void testRedOTurnVacantCell() {
		board.makeMove(0, 0, 'S');
		board.makeMove(1, 0, 'O');
		assertEquals("", board.getCell(0, 0), Cell.RED);
		assertEquals("", board.getTurn(), "Blue");
	}

}
