package app.megachess.AI.pieces;

import app.megachess.enums.AllDirection;
import app.megachess.enums.MovementToEat;
import app.megachess.enums.MovementType;
import app.megachess.utils.ChessUtil;
import lombok.Data;

@Data
public abstract class Piece implements PieceAction {

	protected String color;
	protected MovementToEat movementToEat;
	protected MovementType movementType;

	protected int fromRow;
	protected int fromCol;

	protected int toRow;
	protected int toCol;

	protected String[][] board;

	public Piece(String piece, int[] position, String[][] board, String color) {
		this.board = board;
		this.color = color;
		setPosition(position);
	}

	protected void setPosition(int[] position) {
		this.fromRow = position[0];
		this.fromCol = position[1];
	}

	protected void setTo(int toRow, int toCol) {
		this.toRow = toRow;
		this.toCol = toCol;
	}

	protected boolean toLeft() {
		for (int i = fromCol; i >= 0; i--) {
			if (ChessUtil.isMyEnemy(board[fromRow][i], color)) {
				return true;
			}
		}
		return false;
	}

	protected boolean toRight() {
		for (int i = fromCol; i < 16; i++) {
			if (ChessUtil.isMyEnemy(board[fromRow][i], color)) {
				return true;
			}
		}
		return false;
	}

	protected void evaluateTrajectory(AllDirection target) {

		if (target.equals(AllDirection.TO_TOP)) {
			for (int i = fromRow; i >= 0; i--) {
				if (ChessUtil.isMyEnemy(board[i][fromCol], color)) {
					setTo(i, fromCol);
					break;
				}
			}
		}

		if (target.equals(AllDirection.TO_BOT)) {
			for (int i = fromRow; i < 16; i++) {
				if (ChessUtil.isMyEnemy(board[i][fromCol], color) || i == 15) {
					setTo(i, fromCol);
					break;
				}
			}
		}

		if (target.equals(AllDirection.LEFT)) {
			for (int i = fromCol; i >= 0; i--) {
				if (ChessUtil.isMyEnemy(board[fromRow][i], color) || i == 0) {
					setTo(fromRow, i);
					break;
				}
			}
		}

		if (target.equals(AllDirection.RIGHT)) {
			for (int i = fromCol; i < 16; i++) {
				if (ChessUtil.isMyEnemy(board[fromRow][i], color) || i == 15) {
					setTo(fromRow, i);
					break;
				}
			}
		}

		boolean end;

		if (target.equals(AllDirection.TO_TOP_LEFT)) {

			for (int i = fromRow; i >= 0; i--) {
				for (int j = fromCol; j >= 0; j--) {

					end = i == 0 || j == 0;

					if (ChessUtil.isMyEnemy(board[i][j], color) || end) {
						setTo(i, j);
						break;
					}
				}
			}
		}

		if (target.equals(AllDirection.TO_TOP_RIGHT)) {
			for (int i = fromRow; i >= 0; i--) {
				for (int j = fromCol; j < 16; j++) {

					end = i == 0 || j == 15;

					if (ChessUtil.isMyEnemy(board[i][j], color) || end) {
						setTo(i, j);
						break;
					}
				}
			}
		}

		if (target.equals(AllDirection.TO_BOT_LEFT)) {
			for (int i = fromRow; i < 16; i++) {
				for (int j = fromCol; j >= 0; j--) {

					end = i == 15 || j == 0;

					if (ChessUtil.isMyEnemy(board[i][j], color) || end) {
						setTo(i, j);
						break;
					}
				}
			}
		}

		if (target.equals(AllDirection.TO_BOT_RIGHT)) {
			for (int i = fromRow; i < 16; i++) {
				for (int j = fromCol; j < 16; j++) {

					end = i == 15 || j == 15;

					if (ChessUtil.isMyEnemy(board[i][j], color) || end) {
						setTo(i, j);
						break;
					}
				}
			}
		}
	}

}
