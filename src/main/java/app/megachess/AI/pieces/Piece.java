package app.megachess.AI.pieces;

import app.megachess.enums.MovementToEat;
import app.megachess.enums.MovementType;
import app.megachess.utils.ChessUtil;

public abstract class Piece implements PieceAction {

	protected String color;
	protected MovementToEat movementToEat;
	protected MovementType movementType;

	protected int fromRow;
	protected int fromCol;

	protected int toRow;
	protected int toCol;

	protected String board;
	protected boolean isPosible;

	public Piece(String piece, int[] position, int[] to, String board) {
		this.board = board;
		this.isPosible = false;
		setColor(piece);
		setPositionAndTo(position, to);
		setMovementToEatAndType(piece);
	}

	private void setColor(String piece) {
		this.color = ChessUtil.isWhite(piece) ? "white" : "black";
	}

	private void setPositionAndTo(int[] position, int[] to) {
		this.fromRow = position[0];
		this.fromCol = position[1];
		this.toRow = to[0];
		this.toCol = to[1];
	}

	private void setMovementToEatAndType(String piece) {
		switch (piece.toLowerCase()) {
		case "p":
			movementToEat = MovementToEat.FRONT_DIAGONAL;
			movementType = MovementType.FRONT;
			break;
		case "r":
			movementToEat = MovementToEat.SIDE_FRONT;
			movementType = MovementType.SIDE_FRONT;
			break;
		case "h":
			movementToEat = MovementToEat.HORSE;
			movementType = MovementType.HORSE;
			break;
		case "b":
			movementToEat = MovementToEat.DIAGONAL;
			movementType = MovementType.DIAGONAL;
			break;
		case "q":
			movementToEat = MovementToEat.ALL;
			movementType = MovementType.ALL;
			break;
		case "k":
			movementToEat = MovementToEat.ALL;
			movementType = MovementType.ALL;
			break;
		default:
			break;
		}
	}

}
