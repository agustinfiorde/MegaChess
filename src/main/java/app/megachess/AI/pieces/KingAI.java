package app.megachess.AI.pieces;

import app.megachess.enums.PieceDirection;

public class KingAI extends Piece {

	private PieceDirection toFront;

	public KingAI(String piece, int[] position, String[][] board, String color) {
		super(piece, position, board, color);

		this.toFront = color.equals("white") ? PieceDirection.TO_TOP : PieceDirection.TO_BOT;
	}

	@Override
	public boolean canDefend() {
		return (evaluateBot() || evaluateTop() || evaluateLeft() || evaluateRight() || evaluateBotLeft()
				|| evaluateBotRight() || evaluateTopLeft() || evaluateTopRight());
	}

	/**
	 * cuando se llame canProceed la priodidad es ir hacia adelante, pero en el caso
	 * que este bloqueado ira para la derecha o la izquierda, esta implementacion
	 * aplica para la parte final del juego el the kingDancer
	 */
	@Override
	public boolean canProceed() {

		if (evaluateTrajectory(toFront)) {
			return true;
		}
		if (evaluateTrajectory(PieceDirection.RIGHT)) {
			return true;
		}
		if (evaluateTrajectory(PieceDirection.LEFT)) {
			return true;
		}

		return false;
	}
}
