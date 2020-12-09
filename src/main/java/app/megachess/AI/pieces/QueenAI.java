package app.megachess.AI.pieces;

import app.megachess.enums.PieceDirection;
import app.megachess.utils.ChessUtil;

public class QueenAI extends Piece {

	public QueenAI(int[] position, String board[][], String color) {
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

	@Override
	public boolean canDefend() {
		for (PieceDirection target : PieceDirection.values()) {
			if (evaluateTrajectory(target)) {
				if (evaluateQuadrant(toRow, toCol) && !ChessUtil.isPawnEnemy(board, toRow, toCol, color)
						&& !ChessUtil.isHorseEnemy(board, toRow, toCol, color)) {
					return true;
				}
			}
		}
		return false;
	}

}
