
public class Game {
	Board board;

	public Game(int height, int width) {
		board = new Board(height, width);
		// occupy 33% of the board randomly
		board.placeCells(height * width / 3);
	}

	public int play() {
		int numRounds = 0;
		boolean equilibrium = false;
		while (!equilibrium) {
			board.print();
			Board next = board.nextBoard();
			equilibrium = board.equals(next);
			board = next.copy();
			numRounds++;
		}
		return numRounds;
	}

	public static void main(String[] args) {
		Game game = new Game(10, 10);
		game.play();
	}

}
