package sprint2.test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import org.junit.After;
import org.junit.Before;
import sprint2.product.Board;
import sprint2.product.SosGUI;

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
			Thread.sleep(10000);
		} catch(InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testNonEmptyBoard() {

		new SosGUI(new Board());
		try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
