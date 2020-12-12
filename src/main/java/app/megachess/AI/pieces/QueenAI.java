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

		for (PieceDirection target : PieceDirection.values()) {
			if (evaluateTrajectory(target)) {

				if (evaluateQuadrant(toRow, toCol) && !ChessUtil.isPawnEnemy(board, toRow, toCol, color)) {
					if (!isUnderAttack(toRow, toCol)) {
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
