import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class BoardTest {

	Board b1, b2, b3, vline, hline;

	@Before
	public void setUp() throws Exception {
		b1 = new Board(3, 3);
		b2 = new Board(4, 4);
		b3 = new Board(5, 4);
		vline = new Board(3, 3);
		hline = new Board(3, 3);
		// lines
		for (int i = 0; i < 3; i++) {
			vline.placeCell(i, 1);
			hline.placeCell(1, i);
		}
	}

	@Test
	public void testIsAtEdgeEdge() {
		for (int x = 0; x < b3.width(); x++) {
			assertTrue(b3.isAtEdge(0, x));
			assertTrue(b3.isAtEdge(b3.height() - 1, x));
		}
		for (int y = 0; y < b3.height(); y++) {
			assertTrue(b3.isAtEdge(y, 0));
			assertTrue(b3.isAtEdge(y, b3.width() - 1));
		}
	}

	@Test
	public void testIsAtEdgeNonEdge() {
		for (int y = 1; y < b3.height() - 1; y++) {
			for (int x = 1; x < b3.width() - 1; x++) {
				assertFalse(b3.isAtEdge(y, x));
			}
		}
	}

	@Test
	public void testCountNeighborsDim3Corner() {
		b1.placeCell(0, 0);
		assertEquals(0, b1.countNeighbors(0, 0));
		assertEquals(1, b1.countNeighbors(1, 0));
		assertEquals(1, b1.countNeighbors(0, 1));
	}

	@Test
	public void testCountNeighborsDim3Middle() {
		b1.placeCell(1, 1);
		assertEquals(1, b1.countNeighbors(0, 0));
		assertEquals(1, b1.countNeighbors(0, 1));
		assertEquals(1, b1.countNeighbors(0, 2));
		assertEquals(1, b1.countNeighbors(1, 0));
		assertEquals(0, b1.countNeighbors(1, 1));
		assertEquals(1, b1.countNeighbors(1, 2));
		assertEquals(1, b1.countNeighbors(2, 0));
		assertEquals(1, b1.countNeighbors(2, 1));
		assertEquals(1, b1.countNeighbors(2, 2));
	}

	@Test
	public void testCountNeighborsLine() {
		assertEquals(2, vline.countNeighbors(0, 0));
		assertEquals(1, vline.countNeighbors(0, 1));
		assertEquals(2, vline.countNeighbors(0, 2));
		assertEquals(3, vline.countNeighbors(1, 0));
		assertEquals(2, vline.countNeighbors(1, 1));
		assertEquals(3, vline.countNeighbors(1, 2));
		assertEquals(2, vline.countNeighbors(2, 0));
		assertEquals(1, vline.countNeighbors(2, 1));
		assertEquals(2, vline.countNeighbors(2, 2));
	}

	@Test
	public void testNextBoardLine() {
		assertTrue(hline.nextBoard().equals(vline));
		assertTrue(vline.nextBoard().equals(hline));
		assertTrue(hline.nextBoard().nextBoard().equals(hline));
	}

	@Test
	public void testNextBoardSquare() {
		// 2 x 2 squares are still lives
		b1.placeCell(0, 0);
		b1.placeCell(0, 1);
		b1.placeCell(1, 0);
		b1.placeCell(1, 1);
		assertTrue(b1.nextBoard().equals(b1));
		assertTrue(b1.nextBoard().nextBoard().equals(b1));
	}

	@Test
	public void testNextBoardFish() {
		// fish shapes are also still lives
		b2.placeCell(0, 0);
		b2.placeCell(0, 1);
		b2.placeCell(1, 0);
		b2.placeCell(1, 2);
		b2.placeCell(2, 1);
		assertTrue(b2.nextBoard().equals(b2));
		assertTrue(b2.nextBoard().nextBoard().equals(b2));
	}
}
