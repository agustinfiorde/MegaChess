package app.megachess.AI.pieces;

import app.megachess.enums.PieceDirection;
import app.megachess.utils.ChessUtil;

public class RookAI extends Piece {

	public RookAI(int[] position, String board[][], String color) {
		super(position, board, color);
	}

	public boolean hide() {
		if (fromRow > 3 && fromRow < 12 && fromCol != 0) {
			return evaluateTrajectoryToRight();
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

		int row = color.equals("white") ? 7 : 8;

		if (fromRow != row) {

			if (color.equals("white")) {
				
				evaluateTop();

				if (toRow != null) {
					if (toRow < row) {
						setTo(row, fromCol);
						return true;
					}
				}

			} else {
				evaluateBot();
				
				if (toRow != null) {
					if (toRow < row) {
						setTo(row, fromCol);
						return true;
					}
				}

			}
		}

//		// ir primero para abajo
//		if (color.equals("white")) {
//			if (evaluateTrajectoryToBot()) {
//				return true;
//			}
//		} else {
//			if (evaluateTrajectoryToTop()) {
//				return true;
//			}
//		}
//
//		// sino ir a la derecha
//		if (evaluateTrajectoryToRight()) {
//			return true;
//		}
//
//		// sino ir a la izquierda
//		if (evaluateTrajectoryToLeft()) {
//			return true;
//		}
//
//		// sino ir para arriba
//		if (color.equals("white")) {
//			if (evaluateTrajectoryToTop()) {
//				return true;
//			}
//		} else {
//			if (evaluateTrajectoryToBot()) {
//				return true;
//			}
//		}

		return false;
	}

}
