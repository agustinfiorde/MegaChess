package app.megachess.AI.pieces;

import app.megachess.utils.ChessUtil;

public class PawnAI extends Piece {

	public PawnAI(String piece, int[] position, String board, boolean toEat) {
		super(piece, position, board, toEat);
		if (toEat) {
			canEat();
		} else {
			canMove();
		}
	}

	@Override
	public boolean canMove() {
		if (color.equals("white")) {
			if (fromRow == 12 || fromRow == 13) {
				if (fromRow == 12) {
					if (ChessUtil.isEmpty(11, fromCol, board) && ChessUtil.isEmpty(10, fromCol, board)) {
						setTo(fromRow - 2, fromCol);
						return true;
					}
				} else {
					if (ChessUtil.isEmpty(12, fromCol, board) && ChessUtil.isEmpty(11, fromCol, board)) {
						setTo(fromRow - 2, fromCol);
						return true;
					}
				}
			}
			if (ChessUtil.isEmpty(fromRow - 1, fromCol, board)) {
				setTo(fromRow - 1, fromCol);
				return true;
			}
		} else {
			if (fromRow == 2 || fromRow == 3) {
				if (fromRow == 3) {
					if (ChessUtil.isEmpty(4, fromCol, board) && ChessUtil.isEmpty(5, fromCol, board)) {
						setTo(fromRow + 2, fromCol);
						return true;
					}
				} else {
					if (ChessUtil.isEmpty(3, fromCol, board) && ChessUtil.isEmpty(4, fromCol, board)) {
						setTo(fromRow + 2, fromCol);
						return true;
					}
				}
			}
			if (ChessUtil.isEmpty(fromRow + 1, fromCol, board)) {
				setTo(fromRow + 1, fromCol);
				return true;
			}
		}
		return false;
	}

	@Override
	public boolean canEat() {
		if (color.equals("white")) {
			// evaluar opcion izq
			if (fromCol >= 1) {
				if (ChessUtil.isMyEnemy(board[fromRow - 1][fromCol - 1], color)) {
					setTo(fromRow - 1, fromCol - 1);
					return true;
				}
			}
			// evaluar opcion der
			if (fromCol <= 14) {
				if (ChessUtil.isMyEnemy(board[fromRow - 1][fromCol + 1], color)) {
					setTo(fromRow - 1, fromCol + 1);
					return true;
				}
			}
		} else {
			// evaluar opcion abajo izq
			if (fromCol >= 1) {
				if (ChessUtil.isMyEnemy(board[fromRow + 1][fromCol - 1], color)) {
					setTo(fromRow + 1, fromCol - 1);
					return true;
				}
			}
			// evaluar opcion abajo der
			if (fromCol <= 14) {
				if (ChessUtil.isMyEnemy(board[fromRow + 1][fromCol + 1], color)) {
					setTo(fromRow + 1, fromCol + 1);
					return true;
				}
			}
		}
		return false;
	}

}
