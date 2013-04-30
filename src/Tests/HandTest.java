/**
 * 
 */
package Tests;

import static org.junit.Assert.*;

import java.util.Iterator;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import data.Hand;
import data.Piece;

/**
 * @author Troy
 *
 */
public class HandTest {

	private static Hand hand;
	
	/**
	 * @throws java.lang.Exception
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		hand = new Hand();
	}

	/**
	 * @throws java.lang.Exception
	 */
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void initHand() {
		assertNotNull(hand);
		assertEquals(hand.piecesLeft(),21);
	}
	
	@Test
	public void testPieces() {
		Iterator<Piece> i = hand.getIterator();
		assertEquals(i.next(),new Piece(Piece.Type.I));
		assertEquals(i.next(),new Piece(Piece.Type.L));
		assertEquals(i.next(),new Piece(Piece.Type.U));
		assertEquals(i.next(),new Piece(Piece.Type.Z));
		assertEquals(i.next(),new Piece(Piece.Type.T));
		assertEquals(i.next(),new Piece(Piece.Type.X));
		assertEquals(i.next(),new Piece(Piece.Type.W));
		assertEquals(i.next(),new Piece(Piece.Type.V));
		assertEquals(i.next(),new Piece(Piece.Type.F));
		assertEquals(i.next(),new Piece(Piece.Type.P));
		assertEquals(i.next(),new Piece(Piece.Type.Y));
		assertEquals(i.next(),new Piece(Piece.Type.N));
		assertEquals(i.next(),new Piece(Piece.Type.One));
		assertEquals(i.next(),new Piece(Piece.Type.Two));
		assertEquals(i.next(),new Piece(Piece.Type.Three));
		assertEquals(i.next(),new Piece(Piece.Type.ShortI));
		assertEquals(i.next(),new Piece(Piece.Type.ShortT));
		assertEquals(i.next(),new Piece(Piece.Type.ShortL));
		assertEquals(i.next(),new Piece(Piece.Type.ShortZ));
		assertEquals(i.next(),new Piece(Piece.Type.Square));
		assertEquals(i.next(),new Piece(Piece.Type.Crooked3));
		
	}
	
	@Test
	public void removePiece() {
		Piece.Type[] types = Piece.Type.values();
        hand.removePiece(new Piece(Piece.Type.I));
        assertEquals(hand.piecesLeft(),20);
        hand.removePiece(new Piece(Piece.Type.L));
        assertEquals(hand.piecesLeft(),19);
	}

	@Test
	public void testReset() {
		hand.reset();
		assertEquals(hand.piecesLeft(),0);
	}
}
