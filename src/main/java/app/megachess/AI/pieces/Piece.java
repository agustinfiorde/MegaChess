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

	protected boolean toEat;
	protected boolean toMove;

	protected String[][] board;

	public Piece(String piece, int[] position, String board, boolean toEat) {
		this.board = ChessUtil.getBoard(board);
		this.color = ChessUtil.isWhite(piece) ? "white" : "black";
		this.toEat = toEat;
		this.toMove = !this.toEat;
		setPosition(position);
		setMovementToEatAndType(piece);
	}

	protected void setPosition(int[] position) {
		this.fromRow = position[0];
		this.fromCol = position[1];
	}

	protected void setTo(int toRow, int toCol) {
		this.toRow = toRow;
		this.toCol = toCol;
	}

	protected void setMovementToEatAndType(String piece) {
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
