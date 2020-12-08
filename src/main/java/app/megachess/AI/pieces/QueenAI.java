package app.megachess.AI.pieces;

import app.megachess.enums.PieceDirection;
import app.megachess.utils.ChessUtil;

public class QueenAI extends Piece {

	public QueenAI(int[] position, String board[][], String color) {
		super(position, board, color);
	}

	public boolean hide() {
		if (fromRow > 4 && fromRow < 11 && fromCol != 0) {
			return evaluateTrajectoryToLeft();
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

			if (left > 0) {
				if (ChessUtil.isEmpty(board, front, left)) {
					setTo(front, left);
					return true;
				}
			}

			if (right < 15) {
				if (ChessUtil.isEmpty(board, front, right)) {
					setTo(front, right);
					return true;
				}
			}
		}

		return false;
	}

	@Override
	public boolean canDefend() {
		for (PieceDirection target : PieceDirection.values()) {
			if (evaluateTrajectory(target)) {
				if (evaluateQuadrant(toRow, toCol) && !ChessUtil.isPawn(board, toRow, toCol, color)) {
					return true;
				}
			}
		}
		return false;
	}

}
