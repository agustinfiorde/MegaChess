package app.megachess.AI.pieces;

import app.megachess.enums.PieceDirection;
import app.megachess.utils.ChessUtil;

public class RookAI extends Piece {

	public RookAI(int[] position, String board[][], String color) {
		super(position, board, color);
	}

	/**
	 * Este metodo sirve para ver si una pieza se puede arrinconar a la izquierda
	 * del tablero
	 * 
	 * @return true or false
	 */
	public boolean hide() {
		if (fromRow > 4 && fromRow < 11 && fromCol != 0) {

			evaluateTrajectoryToLeft();

			if (toCol != null) {
				if (evaluateQuadrant(front, toCol) || evaluateQuadrant(front, toCol + 1)) {
					return false;
				} else {
					return true;
				}
			}

		}
		return false;
	}

	@Override
	public boolean canDefend() {
		
		if (isUnderAttack()) {

			if (ChessUtil.isEmpty(board, back, fromCol)) {
				setTo(back, fromCol);
				return true;
			}

			if (left >= 0) {
				if (ChessUtil.isEmpty(board, back, left)) {
					setTo(back, left);
					return true;
				}
			}
			if (right >= 15) {
				if (ChessUtil.isEmpty(board, back, right)) {
					setTo(back, left);
					return true;
				}
			}

		}

		PieceDirection[] posibilities = new PieceDirection[] { PieceDirection.LEFT, PieceDirection.RIGHT,
				PieceDirection.TO_TOP, PieceDirection.TO_BOT };

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

		int row = color.equals("white") ? 6 : 9;

		if (!ChessUtil.rowIsClearOfEnemies(board, row, color) && fromRow != row) {
			row = color.equals("white") ? 9 : 6;
		}

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
