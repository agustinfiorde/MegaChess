package app.megachess.AI.pieces;

import app.megachess.utils.ChessUtil;

public class KingAI extends Piece {

	public KingAI(String piece, int[] position, String board, boolean toEat) {
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
			if (ChessUtil.isMyEnemy(board[fromRow + 1][fromCol], color) || ChessUtil.isEmpty(fromRow + 1, fromCol, board)) {
				setTo(fromRow + 1, fromCol);
				return true;
			}
		} else {
			if (ChessUtil.isMyEnemy(board[fromRow - 1][fromCol], color) || ChessUtil.isEmpty(fromRow - 1, fromCol, board)) {
				setTo(fromRow - 1, fromCol);
				return true;
			}
		}
		return false;
	}

	@Override
	public boolean canEat() {
		if (color.equals("white")) {
			if (fromRow != 15) {
				// evalua abajo izq
				if (ChessUtil.isMyEnemy(board[fromRow + 1][fromCol - 1], color)) {
					setTo(fromRow + 1, fromCol - 1);
					return true;
				}
				// evalua abajo
				if (ChessUtil.isMyEnemy(board[fromRow + 1][fromCol], color)) {
					setTo(fromRow + 1, fromCol);
					return true;
				}
				// evalua abajo der
				if (ChessUtil.isMyEnemy(board[fromRow + 1][fromCol + 1], color)) {
					setTo(fromRow + 1, fromCol + 1);
					return true;
				}
			}

			// evalua izq
			if (ChessUtil.isMyEnemy(board[fromRow][fromCol - 1], color)) {
				setTo(fromRow, fromCol - 1);
				return true;
			}
			// evalua arriba izq
			if (ChessUtil.isMyEnemy(board[fromRow - 1][fromCol - 1], color)) {
				setTo(fromRow - 1, fromCol - 1);
				return true;
			}
			// evalua arriba
			if (ChessUtil.isMyEnemy(board[fromRow - 1][fromCol], color)) {
				setTo(fromRow - 1, fromCol);
				return true;
			}
			// evalua arriba der
			if (ChessUtil.isMyEnemy(board[fromRow - 1][fromCol + 1], color)) {
				setTo(fromRow - 1, fromCol + 1);
				return true;
			}
			// evalua der
			if (ChessUtil.isMyEnemy(board[fromRow][fromCol + 1], color)) {
				setTo(fromRow, fromCol + 1);
				return true;
			}
		} else {

			if (fromRow != 0) {
				// evalua atras izq
				if (ChessUtil.isMyEnemy(board[fromRow - 1][fromCol - 1], color)) {
					setTo(fromRow - 1, fromCol - 1);
					return true;
				}
				// evalua atras
				if (ChessUtil.isMyEnemy(board[fromRow - 1][fromCol], color)) {
					setTo(fromRow - 1, fromCol);
					return true;
				}
				// evalua atras der
				if (ChessUtil.isMyEnemy(board[fromRow - 1][fromCol + 1], color)) {
					setTo(fromRow - 1, fromCol + 1);
					return true;
				}
			}

			// evalua izq
			if (ChessUtil.isMyEnemy(board[fromRow][fromCol - 1], color)) {
				setTo(fromRow, fromCol - 1);
				return true;
			}
			// evalua adelante izq
			if (ChessUtil.isMyEnemy(board[fromRow + 1][fromCol - 1], color)) {
				setTo(fromRow + 1, fromCol - 1);
				return true;
			}
			// evalua adelante
			if (ChessUtil.isMyEnemy(board[fromRow + 1][fromCol], color)) {
				setTo(fromRow + 1, fromCol);
				return true;
			}
			// evalua adelante der
			if (ChessUtil.isMyEnemy(board[fromRow + 1][fromCol + 1], color)) {
				setTo(fromRow + 1, fromCol + 1);
				return true;
			}
			// evalua der
			if (ChessUtil.isMyEnemy(board[fromRow][fromCol + 1], color)) {
				setTo(fromRow, fromCol + 1);
				return true;
			}
		}
		return false;
	}

}
