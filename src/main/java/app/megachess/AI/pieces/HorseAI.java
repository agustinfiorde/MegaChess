package app.megachess.AI.pieces;

public class HorseAI extends Piece {

	public HorseAI(String piece, int[] position, String[][] board, String color) {
		super(piece, position, board, color);
	}

	@Override
	public boolean canMove() {

		return false;
	}

	@Override
	public boolean canEat() {

		return false;
	}

}
