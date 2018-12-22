/**
 * Cell for Conway's Game of Life
 * 
 * @author dychoi
 *
 */
public class Cell {
	// default value false
	private boolean alive;
	private int numNeighbors;
	private String mark;

	/**
	 * Default empty cell.
	 */
	public Cell() {
		this.alive = false;
		mark = "-";
	}

	/**
	 * Cell can either be alive or dead.
	 * 
	 * @param alive
	 *            true if alive.
	 */
	public Cell(boolean alive) {
		this.alive = alive;
		if (alive) {
			mark = "o";
		} else {
			mark = "-";
		}
	}

	/**
	 * Returns status of cell.
	 * 
	 * @return true if alive, false otherwise.
	 */
	public boolean isAlive() {
		return alive;
	}

	public void updateNumNeighbors(int num) {
		this.numNeighbors = num;
	}

	/**
	 * Returns the number of existing neighbors.
	 * 
	 * @return integer between 0 and 8.
	 */
	public int numNeighbors() {
		return numNeighbors;
	}

	@Override
	public String toString() {
		return mark;
	}

}
