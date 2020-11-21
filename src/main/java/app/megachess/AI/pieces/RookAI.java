package app.megachess.AI.pieces;

import app.megachess.enums.AllDirection;

public class RookAI extends Piece {

	private AllDirection direction;

	public RookAI(String piece, int[] position, String board[][], String color) {
		super(piece, position, board, color);
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
