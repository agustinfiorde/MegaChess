package app.megachess.AI.pieces;

import app.megachess.enums.AllDirection;
import app.megachess.utils.ChessUtil;
import lombok.Data;

@Data
public abstract class Piece implements PieceAction {

	protected String color;

	protected Integer fromRow;
	protected Integer fromCol;

	protected Integer toRow;
	protected Integer toCol;

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
		switch (target.toString()) {
		case "TO_TOP":
			evaluateTrajectoryToTop(target);
			break;
		case "TO_BOT":
			evaluateTrajectoryToBot(target);
			break;
		case "LEFT":
			evaluateTrajectoryToLeft(target);
			break;
		case "RIGHT":
			evaluateTrajectoryToRight(target);
			break;
		case "TO_TOP_LEFT":
			evaluateTrajectoryToTopLeft(target);
			break;
		case "TO_TOP_RIGHT":
			evaluateTrajectoryToTopRight(target);
			break;
		case "TO_BOT_LEFT":
			evaluateTrajectoryToBotLeft(target);
			break;
		case "TO_BOT_RIGHT":
			evaluateTrajectoryToBotRight(target);
			break;
		default:
			break;
		}
	}

	private void evaluateTrajectoryToTop(AllDirection target) {
		if (target.equals(AllDirection.TO_TOP)) {
			for (int i = fromRow; i >= 0; i--) {

				if (i - 1 < 0) {
					break;
				}

				if (ChessUtil.isMyTeam(board[i - 1][fromCol], color)) {
					break;
				}

				if (ChessUtil.isMyEnemy(board[i][fromCol], color)) {
					setTo(i, fromCol);
					break;
				}
			}
		}
	}

	private void evaluateTrajectoryToBot(AllDirection target) {
		if (target.equals(AllDirection.TO_BOT)) {
			for (int i = fromRow; i < 16; i++) {

				if (i + 1 > 15) {
					break;
				}

				if (ChessUtil.isMyTeam(board[i + 1][fromCol], color)) {
					break;
				}
				if (ChessUtil.isMyEnemy(board[i][fromCol], color) || i == 15) {
					setTo(i, fromCol);
					break;
				}
			}
		}
	}

	private void evaluateTrajectoryToLeft(AllDirection target) {
		if (target.equals(AllDirection.LEFT)) {
			for (int i = fromCol; i >= 0; i--) {

				if (i - 1 < 0) {
					break;
				}

				if (ChessUtil.isMyTeam(board[fromRow][i], color)) {
					break;
				}
				if (ChessUtil.isMyEnemy(board[fromRow][i], color) || i == 0) {
					setTo(fromRow, i);
					break;
				}
			}
		}
	}

	private void evaluateTrajectoryToRight(AllDirection target) {
		if (target.equals(AllDirection.RIGHT)) {
			for (int i = fromCol; i < 16; i++) {

				if (i + 1 > 15) {
					break;
				}

				if (ChessUtil.isMyTeam(board[fromRow][i], color)) {
					break;
				}
				if (ChessUtil.isMyEnemy(board[fromRow][i], color) || i == 15) {
					setTo(fromRow, i);
					break;
				}
			}
		}
	}

	private void evaluateTrajectoryToTopLeft(AllDirection target) {
		boolean end;

		if (target.equals(AllDirection.TO_TOP_LEFT)) {
			fors: for (int i = fromRow; i >= 0; i--) {
				for (int j = fromCol; j >= 0; j--) {
					end = i == 0 || j == 0;

					if (i - 1 < 0 || j - 1 < 0) {
						break fors;
					}

					if (ChessUtil.isMyTeam(board[i][j], color)) {
						break fors;
					}
					if (ChessUtil.isMyEnemy(board[i][j], color) || end) {
						setTo(i, j);
						break;
					}
				}
			}
		}
	}

	private void evaluateTrajectoryToTopRight(AllDirection target) {
		boolean end;

		if (target.equals(AllDirection.TO_TOP_RIGHT)) {
			fors: for (int i = fromRow; i >= 0; i--) {
				for (int j = fromCol; j < 16; j++) {
					end = i == 0 || j == 15;

					if (i - 1 < 0 || j + 1 > 15) {
						break fors;
					}

					if (ChessUtil.isMyTeam(board[i][j], color)) {
						break fors;
					}
					if (ChessUtil.isMyEnemy(board[i][j], color) || end) {
						setTo(i, j);
						break;
					}
				}
			}
		}
	}

	private void evaluateTrajectoryToBotLeft(AllDirection target) {
		boolean end;

		if (target.equals(AllDirection.TO_BOT_LEFT)) {
			fors: for (int i = fromRow; i < 16; i++) {
				for (int j = fromCol; j >= 0; j--) {
					end = i == 15 || j == 0;

					if (i + 1 > 15 || j - 1 < 0) {
						break fors;
					}

					if (ChessUtil.isMyTeam(board[i][j], color)) {
						break fors;
					}
					if (ChessUtil.isMyEnemy(board[i][j], color) || end) {
						setTo(i, j);
						break;
					}
				}
			}
		}
	}

	private void evaluateTrajectoryToBotRight(AllDirection target) {
		boolean end;
		if (target.equals(AllDirection.TO_BOT_RIGHT)) {
			fors: for (int i = fromRow; i < 16; i++) {
				for (int j = fromCol; j < 16; j++) {
					end = i == 15 || j == 15;

					if (i + 1 > 15 || j + 1 > 15) {
						break fors;
					}

					if (ChessUtil.isMyTeam(board[i][j], color)) {
						break fors;
					}
					if (ChessUtil.isMyEnemy(board[i][j], color) || end) {
						setTo(i, j);
						break;
					}
				}
			}
		}
	}

}
