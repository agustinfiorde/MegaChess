package app.megachess.AI.pieces;

import app.megachess.enums.PieceDirection;
import app.megachess.utils.ChessUtil;

public class RookAI extends Piece {

	public RookAI(int[] position, String board[][], String color) {
		super(position, board, color);
	}

	/**
	 * Este metodo sirve para arrinconar la pieza a la izquierda del tablero
	 * 
	 * @return
	 */
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
				if (evaluateQuadrant(toRow, toCol) && !ChessUtil.isPawnEnemy(board, toRow, toCol, color)) {
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
			return false;
		}
		if (fromRow < row) {
			if (evaluateTrajectoryToBot()) {
				if (toRow >= row) {
					setTo(row, fromCol);
					return true;
				} else {
					return false;
				}
			}
		} else {
			if (evaluateTrajectoryToTop()) {
				if (toRow <= row) {
					setTo(row, fromCol);
					return true;
				} else {
					return false;
				}
			}
		}
		return false;
	}

}
