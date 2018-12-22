/**
 * Cell for Conway's Game of Life
 * 
 * @author dychoi
 *
 */
public class Cell {
	// default value false
	private boolean alive;
	private String mark = "-";

	/**
	 * Default empty cell.
	 */
	public Cell() {
		this.alive = false;
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

	@Override
	public String toString() {
		return mark;
	}

}
