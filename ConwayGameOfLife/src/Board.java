/**
 * Represents the rectangular environment on which the Cells live.
 * 
 * @author dychoi
 *
 */
public class Board {

	private int height, width;
	private Cell[][] board;

	public Board(int height, int width) {
		board = new Cell[height][width];
		this.height = board.length;
		this.width = board[0].length;
		fillEmptyBoard();

	}

	/**
	 * Place living cells
	 * 
	 * @param y
	 * @param x
	 * @return
	 */
	public boolean placeCell(int y, int x) {
		return placeCell(y, x, true);
	}

	/**
	 * Place cell, vertical location first, using 0-indexing.
	 * 
	 * @param y
	 * @param x
	 * @return true if placed, false otherwise.
	 */
	public boolean placeCell(int y, int x, boolean alive) {
		if (isOutOfRange(y, x) || isOccupied(y, x)) {
			return false;
		}
		board[y][x] = new Cell(alive);
		return true;
	}

	/**
	 * Next time step represented by the board.
	 * 
	 * Conway's Game of Life: For currently living cells 1. Less than 2
	 * neighbors: dies 2. 2 or 3 neighbors: lives 3. more than 3 neighbors: dies
	 * 
	 * For currently dead cells exactly 3 neighbors: alive.
	 * 
	 * @return
	 */
	public Board nextBoard() {
		Board next = new Board(height, width);
		for (int i = 0; i < height; i++) {
			for (int j = 0; j < width; j++) {
				int neighbors = countNeighbors(i, j);
				if (this.board[i][j].isAlive()) {
					// currently living cells
					if (neighbors < 2 || neighbors > 3) {
						next.placeCell(i, j, false);
					} else {
						next.placeCell(i, j, true);
					}

				} else {
					// currently dead cells
					if (neighbors == 3) {
						next.placeCell(i, j, true);
					} else {
						next.placeCell(i, j, false);
					}
				}
			}
		}
		return next;
	}

	/**
	 * Two equal-dimension boards are equal if each occupying cell has the same
	 * life/death status.
	 * 
	 * @param next
	 * @return
	 */
	public boolean equals(Board next) {
		// check dimensions
		if (this.height() != next.height() || this.width() != next.width()) {
			return false;
		}
		for (int i = 0; i < height; i++) {
			for (int j = 0; j < width; j++) {
				if (isOccupied(i, j) != next.isOccupied(i, j)) {
					return false;
				}
			}
		}
		return true;
	}

	/**
	 * Counts the number of neighbors that are alive.
	 * 
	 * @param y
	 * @param x
	 * @return
	 */
	protected int countNeighbors(int y, int x) {
		int counter = 0;
		for (int i = y - 1; i <= y + 1; i++) {
			for (int j = x - 1; j <= x + 1; j++) {
				if (!isOutOfRange(i, j) && isOccupied(i, j)) {
					counter++;
				}
			}
		}
		// it counts itself as a neighbor
		if (isOccupied(y, x)) {
			counter--;
		}
		return counter;
	}

	private boolean isOccupied(int y, int x) {
		return board[y][x].isAlive();
	}

	protected boolean isAtEdge(int y, int x) {
		if (x == 0 || y == 0 || x == width - 1 || y == height - 1) {
			return true;
		}
		return false;
	}

	private boolean isOutOfRange(int y, int x) {
		if (0 <= x && x < width && 0 <= y && y < height) {
			return false;
		}
		return true;
	}

	public int height() {
		return height;
	}

	public int width() {
		return width;
	}

	public void print() {
		printCoordAbove();
		for (int i = 0; i < height; i++) {
			System.out.print(i + " ");
			for (int j = 0; j < width; j++) {
				System.out.print(board[i][j] + " ");
			}
			System.out.println();
		}
	}

	private void printCoordAbove() {
		String str = "  ";
		for (int i = 0; i < width; i++) {
			str += i + " ";
		}
		System.out.println(str);
	}

	private void fillEmptyBoard() {
		for (int i = 0; i < height; i++) {
			for (int j = 0; j < width; j++) {
				board[i][j] = new Cell();
			}
		}
	}

}
