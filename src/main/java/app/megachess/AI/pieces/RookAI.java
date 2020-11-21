package app.megachess.AI.pieces;

import java.util.ArrayList;
import java.util.List;

import app.megachess.enums.AllDirection;

public class RookAI extends Piece {

	private List<AllDirection> directions;

	public RookAI(String piece, int[] position, String board[][], String color) {
		super(piece, position, board, color);
	}

	@Override
	public boolean canMove() {

		return false;
	}

	@Override
	public boolean canEat() {

		setDirections();

		for (AllDirection direction : this.directions) {
			evaluateTrajectory(direction);
		}
		if (getToCol() != null) {
			return true;
		} else {
			return false;
		}
	}

	private void setDirections() {

		this.directions = new ArrayList<>();
		directions.add(AllDirection.LEFT);
		directions.add(AllDirection.RIGHT);
		directions.add(AllDirection.TO_BOT);
		directions.add(AllDirection.TO_TOP);
	}
}
