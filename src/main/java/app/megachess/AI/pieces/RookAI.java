package app.megachess.AI.pieces;

import app.megachess.enums.PieceDirection;
import app.megachess.utils.ChessUtil;

public class RookAI extends Piece {

	private PieceDirection goBack;
	
	public RookAI(int[] position, String board[][], String color) {
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
	public boolean canDefend() {
		
		if (isUnderAttack(fromRow, fromCol)) {
			if (hide()) {
				return true;
			}
			
			if (evaluateTrajectory(goBack)) {
				return true;
			}
		}

		PieceDirection[] posibilities = new PieceDirection[] { PieceDirection.LEFT, PieceDirection.RIGHT,
				PieceDirection.TO_TOP, PieceDirection.TO_BOT };

		for (PieceDirection target : posibilities) {
			if (evaluateTrajectory(target)) {

				if (evaluateTrajectory(target)) {
					if (evaluateQuadrant(toRow, toCol) && !ChessUtil.isPawnEnemy(board, toRow, toCol, color)) {					
						if (!isUnderAttack(toRow, toCol)) {
							return true;
						}
					}

					if (toRow > 5 && toRow < 10 && ChessUtil.isPawnEnemy(board, toRow, toCol, color) ) {
						if (!isUnderAttack(toRow, toCol)) {
							return true;
						}
					}

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
					
					if (!isUnderAttack(row, fromCol)) {
						return true;
					} 
					
				} 
			}
		} else {
			if (evaluateTrajectoryToTop()) {
				if (toRow <= row) {
					
					setTo(row, fromCol);
					
					if (!isUnderAttack(row, fromCol)) {
						return true;
					} 
					
				} 
			}
		}
		return false;
	}

}
