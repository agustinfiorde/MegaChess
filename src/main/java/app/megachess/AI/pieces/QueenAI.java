package app.megachess.AI.pieces;

import app.megachess.enums.PieceDirection;
import app.megachess.utils.ChessUtil;

public class QueenAI extends Piece {

	private PieceDirection goBack;

	public QueenAI(int[] position, String board[][], String color) {
		super(position, board, color);

		this.goBack = color.equals("white") ? PieceDirection.TO_BOT : PieceDirection.TO_TOP;

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

		int row = color.equals("white") ? 5 : 10;

		if (!ChessUtil.rowIsClearOfEnemies(board, row, color)) {
			row = color.equals("white") ? 6 : 9;
		}

		if (fromRow == row) {
			if (isUnderAttack(fromRow, fromCol)) {
				if (evaluateTrajectory(goBack)) {
					return true;
				}
			}
			return false;
		}

		if (fromRow < row) {
			if (evaluateTrajectoryToBot()) {

				if (toRow >= row) {
					setTo(row, fromCol);

					if (isUnderAttack(row, fromCol)) {
						if (evaluateTrajectory(goBack)) {
							return true;
						}
					}

				} else {
					if (isUnderAttack(row, fromCol)) {
						if (evaluateTrajectory(goBack)) {
							return true;
						}
					}
				}
			}
		} else {
			if (evaluateTrajectoryToTop()) {

				if (toRow <= row) {
					setTo(row, fromCol);

					if (isUnderAttack(row, fromCol)) {
						if (evaluateTrajectory(goBack)) {
							return true;
						}
					}
				} else {
					if (isUnderAttack(row, fromCol)) {
						if (evaluateTrajectory(goBack)) {
							return true;
						}
					}
				}
			}
		}

		if (toCol == null || toRow == null) {
			return false;
		}

		return true;
	}

	@Override
	public boolean canDefend() {

//		//ir a comer otra reina
//		if (isUnderAttack(fromRow, fromCol)) {
//			if (hide()) {
//				return true;
//			}
//			
//			if (evaluateTrajectory(goBack)) {
//				return true;
//			}
//		}

		for (PieceDirection target : PieceDirection.values()) {
			if (evaluateTrajectory(target)) {

				if (evaluateQuadrant(toRow, toCol) && !ChessUtil.isPawnEnemy(board, toRow, toCol, color)) {

					if (!isUnderAttack(toRow, toCol)) {
						return true;
					}
					if (ChessUtil.isQueenEnemy(board, toRow, toCol, color)) {
						return true;
					}
				}

				if (toRow > 5 && toRow < 10 && ChessUtil.isPawnEnemy(board, toRow, toCol, color)) {
					if (!isUnderAttack(toRow, toCol)) {
						return true;
					}
				}
			}
		}
		
		//ir a comer otra reina
		if (isUnderAttack(fromRow, fromCol)) {
			if (hide()) {
				return true;
			}
			
			if (evaluateTrajectory(goBack)) {
				return true;
			}
		}

		return false;
	}

}
