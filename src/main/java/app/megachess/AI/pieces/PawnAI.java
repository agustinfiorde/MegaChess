package app.megachess.AI.pieces;

import app.megachess.utils.ChessUtil;

public class PawnAI extends Piece {

	public PawnAI(String piece, int[] position, String[][] board, String color) {
		super(piece, position, board, color);
	}

	@Override
	public boolean canMove() {
		if (color.equals("white")) {
			if (fromRow == 12 || fromRow == 13) {
				if (fromRow == 12) {
					if (ChessUtil.isEmptyByPosition(board, 11, fromCol)
							&& ChessUtil.isEmptyByPosition(board, 10, fromCol)) {
						setTo(fromRow - 2, fromCol);
						return true;
					}
				} else {
					if (ChessUtil.isEmptyByPosition(board, 12, fromCol)
							&& ChessUtil.isEmptyByPosition(board, 11, fromCol)) {
						setTo(fromRow - 2, fromCol);
						return true;
					}
				}
			}
			if (ChessUtil.isEmptyByPosition(board, fromRow - 1, fromCol)) {
				setTo(fromRow - 1, fromCol);
				return true;
			}
		} else {
			if (fromRow == 2 || fromRow == 3) {
				if (fromRow == 3) {
					if (ChessUtil.isEmptyByPosition(board, 4, fromCol)
							&& ChessUtil.isEmptyByPosition(board, 5, fromCol)) {
						setTo(fromRow + 2, fromCol);
						return true;
					}
				} else {
					if (ChessUtil.isEmptyByPosition(board, 3, fromCol)
							&& ChessUtil.isEmptyByPosition(board, 4, fromCol)) {
						setTo(fromRow + 2, fromCol);
						return true;
					}
				}
			}
			if (ChessUtil.isEmptyByPosition(board, fromRow + 1, fromCol)) {
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
