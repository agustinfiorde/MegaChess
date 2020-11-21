package app.megachess.AI.pieces;

import app.megachess.enums.AllDirection;
import app.megachess.utils.ChessUtil;

public class QueenAI extends Piece {

	private AllDirection direction;

	public QueenAI(String piece, int[] position, String board[][], String color) {
		super(piece, position, board, color);
	}

	@Override
	public boolean canMove() {

		evaluateTrajectory(direction);

		return true;
	}

	@Override
	public boolean canEat() {
		// -----------5 6 7 white
		// -----------8 9 10 black
		evaluateTrajectory(direction);

		return true;
	}

	public boolean canBeEated() {

		// desplazarla lateralmente fuera del peligro
		return false;
	}

	public void assassin() {
		if (color.equals("white")) {
			// logica para el blanco
			if (!ChessUtil.rowIsClear(board, 2) && !ChessUtil.rowIsClear(board, 1) && !ChessUtil.rowIsClear(board, 0)) {
				// ir hasta el fondo
				// llamar un metodo para desplazamientos laterales
			}
			if (!ChessUtil.rowIsClear(board, 2) && !ChessUtil.rowIsClear(board, 1)) {
				// ir hasta la penultima
				// llamar un metodo para desplazamientos laterales
			}
			if (!ChessUtil.rowIsClear(board, 2)) {
				// ir hasta la segunda
				// llamar un metodo para desplazamientos laterales
			}
			if (ChessUtil.rowIsClear(board, 2) && ChessUtil.rowIsClear(board, 1) && ChessUtil.rowIsClear(board, 0)) {
				// quedarse en la primera
				// llamar un metodo para desplazamientos laterales
			}
		} else {
			// logica para el negro
			if (!ChessUtil.rowIsClear(board, 2) && !ChessUtil.rowIsClear(board, 1) && !ChessUtil.rowIsClear(board, 0)) {
				// ir hasta el fondo
				// llamar un metodo para desplazamientos laterales
			}
			if (!ChessUtil.rowIsClear(board, 2) && !ChessUtil.rowIsClear(board, 1)) {
				// ir hasta la penultima
				// llamar un metodo para desplazamientos laterales
			}
			if (!ChessUtil.rowIsClear(board, 2)) {
				// ir hasta la segunda
				// llamar un metodo para desplazamientos laterales
			}
			if (ChessUtil.rowIsClear(board, 2) && ChessUtil.rowIsClear(board, 1) && ChessUtil.rowIsClear(board, 0)) {
				// quedarse en la primera
				// llamar un metodo para desplazamientos laterales
			}
		}
	}

}
