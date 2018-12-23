
public class Game {
	Board current;
	
	public Game(int height, int width) {
		current = new Board(height, width);
	}
	
	public int play() {
		int numRounds = 0;
		boolean equilibrium = false;
		while (!equilibrium) {
			Board next = current.nextBoard();
			equilibrium = current.equals(next);
			current = next.copy();
			numRounds++;
		}
		return numRounds;		
	}
	
	public static void main(String[] args) {
		Board vline = new Board(3, 3);
		Board hline = new Board(3, 3);
		for (int i = 0; i < 3; i++) {
			vline.placeCell(i, 1);
			hline.placeCell(1, i);
		}
	}

}
