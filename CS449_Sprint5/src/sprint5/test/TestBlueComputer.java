package sprint5.test;

import static org.junit.Assert.*;

import java.util.Scanner;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import sprint5.product.Board;
import sprint5.product.Console;
import sprint5.product.Board.GameState;

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
	
}
