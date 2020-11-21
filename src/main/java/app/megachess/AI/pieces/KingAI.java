package app.megachess.AI.pieces;

import app.megachess.utils.ChessUtil;

public class KingAI extends Piece {

	public KingAI(String piece, int[] position, String[][] board, String color) {
		super(piece, position, board, color);
	}

	@Override
	public boolean canMove() {
		if (color.equals("white")) {
			if (ChessUtil.isMyEnemy(board[fromRow - 1][fromCol], color)
					|| ChessUtil.isEmptyByPosition(board, fromRow - 1, fromCol)) {
				setTo(fromRow + 1, fromCol);
				return true;
			}
		} else {
			if (ChessUtil.isMyEnemy(board[fromRow + 1][fromCol], color)
					|| ChessUtil.isEmptyByPosition(board, fromRow + 1, fromCol)) {
				setTo(fromRow - 1, fromCol);
				return true;
			}
		}
		return false;
	}

	public boolean evaluateBack(int back, int limitBack) {
		// evalua atras

		if (color.equals("white")) {
			if (back <= limitBack) {
				if (ChessUtil.isMyEnemy(board[back][fromCol], color)) {
					setTo(back, fromCol);
					return true;
				}
			}
		} else {
			if (back >= limitBack) {
				if (ChessUtil.isMyEnemy(board[back][fromCol], color)) {
					setTo(back, fromCol);
					return true;
				}
			}
		}
		return false;
	}

	public boolean evaluateBackLeft(int back, int limitBack) {
		// evalua abajo izquierda
		if (color.equals("white")) {
			if (back <= limitBack && fromCol - 1 >= 0) {
				if (ChessUtil.isMyEnemy(board[back][fromCol - 1], color)) {
					setTo(back, fromCol - 1);
					return true;
				}
			}
		} else {
			if (back >= limitBack && fromCol - 1 >= 0) {
				if (ChessUtil.isMyEnemy(board[back][fromCol - 1], color)) {
					setTo(back, fromCol - 1);
					return true;
				}
			}
		}
		return false;
	}

	public boolean evaluateBackRight(int back, int limitBack) {
		// evalua abajo a la derecha
		if (color.equals("white")) {
			if (back <= limitBack && fromCol + 1 <= 15) {
				if (ChessUtil.isMyEnemy(board[back][fromCol + 1], color)) {
					setTo(back, fromCol + 1);
					return true;
				}
			}
		} else {
			if (back >= limitBack && fromCol + 1 <= 15) {
				if (ChessUtil.isMyEnemy(board[back][fromCol + 1], color)) {
					setTo(back, fromCol + 1);
					return true;
				}
			}
		}
		return false;
	}

	public boolean evaluateRight() {
		// evalua derecha
		if (fromCol + 1 <= 15) {
			if (ChessUtil.isMyEnemy(board[fromRow][fromCol + 1], color)) {
				setTo(fromRow, fromCol + 1);
				return true;
			}
		}
		return false;
	}

	public boolean evaluateLeft() {
		// evalua izquierda
		if (fromCol - 1 >= 0) {
			if (ChessUtil.isMyEnemy(board[fromRow][fromCol - 1], color)) {
				setTo(fromRow, fromCol - 1);
				return true;
			}
		}
		return false;
	}

	public boolean evaluateFront(int front, int limitFront) {
		// evalua adelante
		if (front <= limitFront) {
			if (ChessUtil.isMyEnemy(board[front][fromCol], color)) {
				setTo(front, fromCol);
				return true;
			}
		}
		return false;
	}

	public boolean evaluateFrontLeft(int front, int limitFront) {
		// evalua adelante izquierda
		if (front <= limitFront && fromCol - 1 >= 0) {
			if (ChessUtil.isMyEnemy(board[front][fromCol - 1], color)) {
				setTo(front, fromCol);
				return true;
			}
		}
		return false;
	}

	public boolean evaluateFrontRight(int front, int limitFront) {
		// evalua adelante derecha
		if (front <= limitFront && fromCol + 1 <= 15) {
			if (ChessUtil.isMyEnemy(board[front][fromCol + 1], color)) {
				setTo(front, fromCol);
				return true;
			}
		}
		return false;
	}

	@Override
	public boolean canEat() {

		int front;
		int back;
		int limitFront;
		int limitBack;

		if (color.equals("white")) {
			limitBack = 15;
			limitFront = 0;
			back = fromRow + 1;
			front = fromRow - 1;
		} else {
			limitBack = 0;
			limitFront = 15;
			back = fromRow - 1;
			front = fromRow + 1;
		}

		if (evaluateBack(back, limitBack)) {
			return true;
		}

		if (evaluateBackLeft(back, limitBack)) {
			return true;
		}

		if (evaluateBackRight(back, limitBack)) {
			return true;
		}

		if (evaluateLeft()) {
			return true;
		}

		if (evaluateRight()) {
			return true;
		}

		if (evaluateFront(front, limitFront)) {
			return true;
		}

		if (evaluateFrontLeft(front, limitFront)) {
			return true;
		}

		if (evaluateFrontRight(front, limitFront)) {
			return true;
		}

		return false;
	}

}
