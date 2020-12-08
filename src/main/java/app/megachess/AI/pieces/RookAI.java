package app.megachess.AI.pieces;

import app.megachess.enums.PieceDirection;
import app.megachess.utils.ChessUtil;

public class RookAI extends Piece {

	public RookAI(int[] position, String board[][], String color) {
		super(position, board, color);
	}

	public boolean hide() {
		if (fromRow > 4 && fromRow < 11 && fromCol != 0) {
			return evaluateTrajectoryToLeft();
		}
		return false;
	}

	@Override
	public boolean canDefend() {

		PieceDirection[] posibilities = new PieceDirection[] { PieceDirection.LEFT, PieceDirection.RIGHT,
				PieceDirection.TO_TOP, PieceDirection.TO_BOT };

		for (PieceDirection target : posibilities) {
			if (evaluateTrajectory(target)) {
				if (evaluateQuadrant(toRow, toCol) && !ChessUtil.isPawn(board, toRow, toCol, color)) {
					return true;
				}
			}
		}
		return false;
	}

	@Override
	public boolean canProceed() {

		int row = color.equals("white") ? 8 : 7;

		if (fromRow == row) {
			if (ChessUtil.isEmpty(board, front, fromCol)) {
				setTo(front, fromCol);
				return true;
			}
		}

		return false;
	}

}
