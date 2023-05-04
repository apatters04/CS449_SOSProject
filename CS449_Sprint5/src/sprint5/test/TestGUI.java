package sprint5.test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import sprint5.product.Board;
import sprint5.product.SosGUI;

import org.junit.After;
import org.junit.Before;

class TestGUI {
	private Board board;

	@Before
	public void setUp() throws Exception {
		board = new Board();
	}
	
	@After
	public void tearDown() throws Exception {
		
	}
	
	@Test
	public void testEmptyBoard() {
		new SosGUI(new Board());
		try {
			Thread.sleep(5000);
		} catch(InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testNonEmptyBoard() {
		board.makeMove(1, 1, 'S');	
		new SosGUI(new Board());
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
