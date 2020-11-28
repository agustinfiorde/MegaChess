package app.megachess.AI.pieces;

import app.megachess.enums.PieceDirection;

public class BishopAI extends Piece {

	private PieceDirection frontLeft;
	private PieceDirection frontRight;
	private boolean dancerStrategy;

	public BishopAI(String piece, int[] position, String[][] board, String color, boolean dancerStrategy) {
		super(piece, position, board, color);

		this.dancerStrategy = dancerStrategy;
		if (color.equals("white")) {
			this.frontLeft = PieceDirection.TO_TOP_LEFT;
			this.frontRight = PieceDirection.TO_TOP_RIGHT;
		} else {
			this.frontLeft = PieceDirection.TO_BOT_LEFT;
			this.frontRight = PieceDirection.TO_BOT_RIGHT;
		}

	}

	@Override
	public boolean canDefend() {

		PieceDirection[] posibilities = new PieceDirection[] { PieceDirection.TO_BOT_LEFT, PieceDirection.TO_BOT_RIGHT,
				PieceDirection.TO_TOP_LEFT, PieceDirection.TO_TOP_RIGHT };

		for (PieceDirection target : posibilities) {
			if (evaluateTrajectory(target)) {
				if (evaluateQuadrant(toRow, toCol)) {
					return true;
				}
			}
		}
		return false;
	}

	@Override
	public boolean canProceed() {

		if (dancerStrategy) {
			return evaluateTrajectory(frontLeft);
		} else {
			PieceDirection[] posibilities = new PieceDirection[] { frontLeft, frontRight };
			for (PieceDirection target : posibilities) {
				if (evaluateTrajectory(target)) {
					return true;
				}
			}
		}
		return false;
	}
}
