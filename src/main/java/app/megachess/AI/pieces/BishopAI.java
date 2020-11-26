package app.megachess.AI.pieces;

import app.megachess.enums.PieceDirection;

public class BishopAI extends Piece {

	public BishopAI(String piece, int[] position, String[][] board, String color) {
		super(piece, position, board, color);

	}

//	@Override
//	public boolean canDefend() {
//		return (evaluateBot() || evaluateTop() || evaluateLeft() || evaluateRight() || evaluateBotLeft()
//				|| evaluateBotRight() || evaluateTopLeft() || evaluateTopRight());
//	}

	@Override
	public boolean canDefend() {

		PieceDirection[] posibilities = new PieceDirection[] { 
				PieceDirection.TO_BOT_LEFT, PieceDirection.TO_BOT_RIGHT,
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
		// TODO Auto-generated method stub
		return false;
	}
}
