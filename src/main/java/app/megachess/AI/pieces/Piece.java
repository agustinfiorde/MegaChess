package app.megachess.AI.pieces;

import app.megachess.enums.AllDirection;
import app.megachess.utils.ChessUtil;
import app.megachess.websocket.models.Response;
import lombok.Data;

@Data
public abstract class Piece implements PieceAction {

	protected String color;

	protected Integer fromRow;
	protected Integer fromCol;

	protected Integer toRow;
	protected Integer toCol;

	protected Integer front;
	protected Integer back;
	protected Integer left;
	protected Integer right;

	protected String[][] board;

	public Piece(String piece, int[] position, String[][] board, String color) {
		this.board = board;
		this.color = color;
		setPosition(position);

		this.right = this.fromCol + 1;
		this.left = this.fromCol - 1;
		if (color.equals("white")) {
			this.front = this.fromRow - 1;
			this.back = this.fromRow + 1;
		} else {
			this.front = this.fromRow + 1;
			this.back = this.fromRow - 1;
		}
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
			for (int i = (fromRow - 1); i >= 0; i--) {
				this.toCol = null;
				this.toRow = null;
				if (ChessUtil.isMyTeam(board[i][fromCol], color)) {
					break;
				}

				if (ChessUtil.isMyEnemy(board[i][fromCol], color)) {
					setTo(i, fromCol);
					break;
				}
				setTo(i, fromCol);
			}
		}
	}

	private void evaluateTrajectoryToBot(AllDirection target) {
		if (target.equals(AllDirection.TO_BOT)) {
			for (int i = (fromRow + 1); i < 16; i++) {

				this.toCol = null;
				this.toRow = null;
				if (ChessUtil.isMyTeam(board[i][fromCol], color)) {
					break;
				}
				if (ChessUtil.isMyEnemy(board[i][fromCol], color)) {
					setTo(i, fromCol);
					break;
				}
				setTo(i, fromCol);
			}
		}
	}

	private void evaluateTrajectoryToLeft(AllDirection target) {
		if (target.equals(AllDirection.LEFT)) {
			for (int i = (fromCol - 1); i >= 0; i--) {

				this.toCol = null;
				this.toRow = null;
				if (ChessUtil.isMyTeam(board[fromRow][i], color)) {
					break;
				}
				if (ChessUtil.isMyEnemy(board[fromRow][i], color)) {
					setTo(fromRow, i);
					break;
				}
				setTo(fromRow, i);
			}
		}
	}

	private void evaluateTrajectoryToRight(AllDirection target) {
		if (target.equals(AllDirection.RIGHT)) {
			for (int i = (fromCol + 1); i < 16; i++) {

				this.toCol = null;
				this.toRow = null;
				if (ChessUtil.isMyTeam(board[fromRow][i], color)) {
					break;
				}
				if (ChessUtil.isMyEnemy(board[fromRow][i], color)) {
					setTo(fromRow, i);
					break;
				}
				setTo(fromRow, i);
			}
		}
	}

	private void evaluateTrajectoryToTopLeft(AllDirection target) {

		if (target.equals(AllDirection.TO_TOP_LEFT)) {
			fors: for (int i = fromRow - 1; i >= 0; i--) {
				for (int j = fromCol - 1; j >= 0; j--) {

					this.toCol = null;
					this.toRow = null;
					if (ChessUtil.isMyTeam(board[i][j], color)) {
						break fors;
					}
					if (ChessUtil.isMyEnemy(board[i][j], color)) {
						setTo(i, j);
						break fors;
					}
					setTo(i, j);
				}
			}
		}
	}

	private void evaluateTrajectoryToTopRight(AllDirection target) {
		if (target.equals(AllDirection.TO_TOP_RIGHT)) {
			fors: for (int i = fromRow - 1; i >= 0; i--) {
				for (int j = fromCol + 1; j < 16; j++) {

					this.toCol = null;
					this.toRow = null;
					if (ChessUtil.isMyTeam(board[i][j], color)) {
						toCol = null;
						toRow = null;
						break fors;
					}
					if (ChessUtil.isMyEnemy(board[i][j], color)) {
						setTo(i, j);
						break fors;
					}
					setTo(i, j);
				}
			}
		}
	}

	private void evaluateTrajectoryToBotLeft(AllDirection target) {

		if (target.equals(AllDirection.TO_BOT_LEFT)) {
			fors: for (int i = fromRow + 1; i < 16; i++) {
				for (int j = fromCol - 1; j >= 0; j--) {

					this.toCol = null;
					this.toRow = null;
					if (ChessUtil.isMyTeam(board[i][j], color)) {
						toCol = null;
						toRow = null;
						break fors;
					}
					if (ChessUtil.isMyEnemy(board[i][j], color)) {
						setTo(i, j);
						break fors;
					}
					setTo(i, j);
				}
			}
		}
	}

	private void evaluateTrajectoryToBotRight(AllDirection target) {
		if (target.equals(AllDirection.TO_BOT_RIGHT)) {
			fors: for (int i = fromRow + 1; i < 16; i++) {
				for (int j = fromCol + 1; j < 16; j++) {

					if (ChessUtil.isMyTeam(board[i][j], color)) {
						toCol = null;
						toRow = null;
						break fors;
					}
					if (ChessUtil.isMyEnemy(board[i][j], color)) {
						setTo(i, j);
						break;
					}
					setTo(i, j);
				}
			}
		}
	}

	protected boolean evaluateTop() {
		if (back > 15 || back < 0) {
			return false;
		} else {
			if (evaluateQuadrants(front, fromCol)) {
				setTo(front, fromCol);
				return true;
			} else {
				return false;
			}
		}
	}

	protected boolean evaluateBot() {
		if (back > 15 || back < 0) {
			return false;
		} else {
			if (evaluateQuadrants(back, fromCol)) {
				setTo(back, fromCol);
				return true;
			} else {
				return false;
			}
		}
	}

	protected boolean evaluateLeft() {
		if (left < 0) {
			return false;
		} else {
			if (evaluateQuadrants(fromRow, left)) {
				setTo(fromRow, left);
				return true;
			} else {
				return false;
			}
		}
	}

	protected boolean evaluateRight() {
		if (right > 15) {
			return false;
		} else {
			if (evaluateQuadrants(fromRow, right)) {
				setTo(fromRow, right);
				return true;
			} else {
				return false;
			}
		}
	}

	protected boolean evaluateTopLeft() {
		if (back > 15 || back < 0 || left < 0) {
			return false;
		} else {
			if (evaluateQuadrants(front, left)) {
				setTo(front, left);
				return true;
			} else {
				return false;
			}
		}
	}

	protected boolean evaluateBotLeft() {
		if (back > 15 || back < 0 || left < 0) {
			return false;
		} else {
			if (evaluateQuadrants(back, left)) {
				setTo(back, left);
				return true;
			} else {
				return false;
			}
		}
	}

	protected boolean evaluateTopRight() {
		if (back > 15 || back < 0 || right > 15) {
			return false;
		} else {
			if (evaluateQuadrants(front, right)) {
				setTo(front, right);
				return true;
			} else {
				return false;
			}
		}
	}

	protected boolean evaluateBotRight() {
		if (back > 15 || back < 0 || right > 15) {
			return false;
		} else {
			if (evaluateQuadrants(back, right)) {
				setTo(back, right);
				return true;
			} else {
				return false;
			}
		}
	}

	protected boolean evaluateQuadrants(int row, int col) {
		if (ChessUtil.isMyEnemy(board[row][col], color)) {
			return true;
		} else {
			return false;
		}
	}

	protected Response targetToHunt() {
		for (int i = 0; i < 16; i++) {
			for (int j = 0; j < 16; j++) {
				if (ChessUtil.isMyEnemy(board[i][j], color)) {
					Response res = new Response();
					res.setExist(true);
					res.setFromRow(i);
					res.setFromCol(j);
					return res;
				}
			}
		}
		return null;
	}
}
