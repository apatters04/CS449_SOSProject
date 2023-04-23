package sprint4.test;

import static org.junit.Assert.*;

import java.util.Scanner;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import sprint4.product.Console;
import sprint4.product.Board;
import sprint4.product.Board.GameState;

public class TestBlueComputer {
	private Board board;

	@Before
	public void setUp() throws Exception {
		board = new Board();
	}

	@After
	public void tearDown() throws Exception {
	}
	
	@Test
	public void testBlueAIWin() {
		new Console(board).play();
		assertEquals(GameState.BLUE_WIN, board.getGameState());
	}
	
	@Test
	public void testRedAIWin() {
		board.resetGame();
		new Console(board).play();
		assertEquals(GameState.RED_WIN, board.getGameState());
	}

	
}
