package Tests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class HumanPlayerTest {

	private data.Game game;
	
	/*
	 * Called before each test case
	 */
	@BeforeClass
	public void setUp() {
		game = new data.Game();
		game.start();
		game.addPlayer(new data.HumanPlayer("Player 1", 1));
		game.addPlayer(new data.HumanPlayer("Player 2", 2));
		game.addPlayer(new data.HumanPlayer("Player 3", 3));
		game.addPlayer(new data.HumanPlayer("Player 4", 4));
	}
	
	@Test
	public void initialized() {
		assertNull(game.getPlayer(0));
		assertNotNull(game.getPlayer(1));
		assertNotNull(game.getPlayer(2));
		assertNotNull(game.getPlayer(3));
		assertNotNull(game.getPlayer(4));
		assertNull(game.getPlayer(5));
		
	}
	
	@Test
	public void testInitHand() {
		assertNotNull(game.getPlayer(1).getHand());
		assertEquals(game.getPlayer(1).getHand().piecesLeft(),21);
	}

	@Test
	public void testInitName() {
		assertEquals(game.getPlayer(1).getName(),"Player 1");
	}

	@Test
	public void testInitScore() {
		assertEquals(game.getPlayer(1).getScore(), 0);
	}

	@Test
	public void testGetIndex() {
		//fail("Not yet implemented");
	}

	@Test
	public void testIsAutoProgress() {
		//fail("Not yet implemented");
	}

	@Test
	public void testSetAutoProgress() {
		//fail("Not yet implemented");
	}

	@Test
	public void testAddScore() {
		//fail("Not yet implemented");
	}

	@Test
	public void testReset() {
		//fail("Not yet implemented");
	}

	@Test
	public void testHasMoreMoves() {
		//fail("Not yet implemented");
	}

	@Test
	public void testSetHasMoreMoves() {
		//fail("Not yet implemented");
	}

	@Test
	public void testWaitForNextMove() {
		//fail("Not yet implemented");
	}

	@Test
	public void testGetNextMove() {
		//fail("Not yet implemented");
	}

	@Test
	public void testPutNextMove() {
		//fail("Not yet implemented");
	}

	@Test
	public void testAbort() {
		//fail("Not yet implemented");
	}

	@Test
	public void testClone() {
		//fail("Not yet implemented");
	}

	@Test
	public void testHumanPlayer() {
		//fail("Not yet implemented");
	}

}
