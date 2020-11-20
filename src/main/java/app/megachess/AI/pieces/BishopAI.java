package app.megachess.AI.pieces;

import app.megachess.enums.AllDirection;

public class BishopAI extends Piece {

	private AllDirection direction;

	public BishopAI(String piece, int[] position, String board, AllDirection direction) {
		super(piece, position, board, false);
		this.direction = direction;
	}

	@Override
	public boolean canMove() {

		evaluateTrajectory(direction);

		return true;
	}

	@Override
	public boolean canEat() {

		evaluateTrajectory(direction);

		return true;
	}

}
