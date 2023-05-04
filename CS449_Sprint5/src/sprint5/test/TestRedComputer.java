package sprint5.test;

import static org.junit.Assert.*;

import java.util.Scanner;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import sprint5.product.Board;
import sprint5.product.Console;
import sprint5.product.Board.GameState;

public class TestRedComputer {
	private Board board;

	@Before
	public void setUp() throws Exception {
		board = new Board();
	}

	@After
	public void tearDown() throws Exception {
	}
	
	@Test
	public void testRedAIWin() {
		board.resetGame();
		new Console(board).play();
		assertEquals(GameState.RED_WIN, board.getGameState());
	}
	
}
