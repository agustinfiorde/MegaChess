package app.megachess.AI.pieces;

import app.megachess.enums.PieceDirection;
import app.megachess.utils.ChessUtil;

public class QueenAI extends Piece {

	public QueenAI(int[] position, String board[][], String color) {
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
	public boolean canProceed() {

		int row = color.equals("white") ? 7 : 8;

		if (!ChessUtil.rowIsClearOfEnemies(board, row, color) && fromRow != row) {
			row = color.equals("white") ? 8 : 7;
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

	@Override
	public boolean canDefend() {

//		if (isUnderAttack()) {
//			return evaluateTrajectoryToLeft();
//		}
		//&& !ChessUtil.isHorseEnemy(board, toRow, toCol, color)

		for (PieceDirection target : PieceDirection.values()) {
			if (evaluateTrajectory(target)) {
				if (evaluateQuadrant(toRow, toCol) && !ChessUtil.isPawnEnemy(board, toRow, toCol, color)
						) {
					return true;
				}
			}
		}
		return false;
	}

}
