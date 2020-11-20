package app.megachess.AI.pieces;

import app.megachess.enums.AllDirection;

public class HorseAI extends Piece {

	public HorseAI(String piece, int[] position, String board) {
		super(piece, position, board, false);
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
