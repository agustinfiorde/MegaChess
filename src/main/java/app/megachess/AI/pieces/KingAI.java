package app.megachess.AI.pieces;

public class KingAI extends Piece {

	public KingAI(int[] position, String[][] board, String color) {
		super(position, board, color);
	}

	@Override
	public boolean canDefend() {
		return (evaluateBot() || evaluateTop() || evaluateLeft() || evaluateRight() || evaluateBotLeft()
				|| evaluateBotRight() || evaluateTopLeft() || evaluateTopRight());
	}

	/**
	 * cuando se llame canProceed la priodidad es ir hacia adelante, pero en el caso
	 * que este bloqueado ira para la derecha o la izquierda, esta implementacion
	 * aplica para la parte final del juego o el the kingDancer
	 */
	@Override
	public boolean canProceed() {

		//ir primero para abajo
		if (color.equals("white")) {
			if (evaluateTrajectoryToBot()) {
				return true;
			}
		} else {
			if (evaluateTrajectoryToTop()) {
				return true;
			}
		}
		
		//sino ir a la derecha
		if (evaluateTrajectoryToRight()) {
			return true;
		}
		
		//sino ir a la izquierda
		if (evaluateTrajectoryToLeft()) {
			return true;
		}
				
		//sino ir para arriba
		if (color.equals("white")) {
			if (evaluateTrajectoryToTop()) {
				return true;
			}
		} else {
			if (evaluateTrajectoryToBot()) {
				return true;
			}
		}
		
		return false;
	}

	/*
	 * Override para evaluar trayectoria libre para la derecha del rey
	 */
	@Override
	public boolean evaluateTrajectoryToRight() {

		if (right > 15) {
			return false;
		}
		if (board[fromRow][right].equals(" ")) {
			setTo(fromRow, right);
			return true;
		} else {
			return false;
		}
	}

	/*
	 * Override para evaluar trayectoria libre para la izquiera del rey
	 */
	@Override
	public boolean evaluateTrajectoryToLeft() {

		if (left < 0) {
			return false;
		}
		if (board[fromRow][left].equals(" ")) {
			setTo(fromRow, left);
			return true;
		} else {
			return false;
		}
	}

	/*
	 * Override para evaluar trayectoria libre hacia arriba del tablero tomando como
	 * referencia al rey y sus movimientos
	 */
	@Override
	protected boolean evaluateTrajectoryToTop() {

		if (fromRow - 1 < 0) {
			return false;
		}
		if (board[fromRow - 1][fromCol].equals(" ")) {
			setTo(fromRow - 1, fromCol);
			return true;
		} else {
			return false;
		}
	}

	/*
	 * Override para evaluar trayectoria libre hacia abajo del tablero tomando como
	 * referencia al rey y sus movimientos
	 */
	@Override
	protected boolean evaluateTrajectoryToBot() {

		if (fromRow + 1 > 15) {
			return false;
		}
		if (board[fromRow + 1][fromCol].equals(" ")) {
			setTo(fromRow + 1, fromCol);
			return true;
		} else {
			return false;
		}
	}

	/*
	 * Override para evaluar trayectoria libre hacia arriba y la derecha del tablero
	 * tomando como referencia al rey y sus movimientos
	 */
	@Override
	protected boolean evaluateTrajectoryToTopRight() {

		if (fromRow - 1 < 0) {
			return false;
		}

		if (fromCol + 1 > 15) {
			return false;
		}

		if (board[fromRow - 1][fromCol + 1].equals(" ")) {
			setTo(fromRow - 1, fromCol + 1);
			return true;
		} else {
			return false;
		}
	}

	/*
	 * Override para evaluar trayectoria libre hacia arriba y la izquierda del
	 * tablero tomando como referencia al rey y sus movimientos
	 */
	@Override
	protected boolean evaluateTrajectoryToTopLeft() {

		if (fromRow - 1 < 0) {
			return false;
		}

		if (fromCol - 1 < 0) {
			return false;
		}

		if (board[fromRow - 1][fromCol - 1].equals(" ")) {
			setTo(fromRow - 1, fromCol - 1);
			return true;
		} else {
			return false;
		}
	}

	/*
	 * Override para evaluar trayectoria libre hacia abajo y la derecha del tablero
	 * tomando como referencia al rey y sus movimientos
	 */
	@Override
	protected boolean evaluateTrajectoryToBotRight() {

		if (fromRow + 1 > 15) {
			return false;
		}

		if (fromCol + 1 > 15) {
			return false;
		}

		if (board[fromRow + 1][fromCol + 1].equals(" ")) {
			setTo(fromRow + 1, fromCol + 1);
			return true;
		} else {
			return false;
		}
	}

	/*
	 * Override para evaluar trayectoria libre hacia abajo y la izquierda del
	 * tablero tomando como referencia al rey y sus movimientos
	 */
	@Override
	protected boolean evaluateTrajectoryToBotLeft() {

		if (fromRow + 1 > 15) {
			return false;
		}

		if (fromCol - 1 < 0) {
			return false;
		}

		if (board[fromRow + 1][fromCol - 1].equals(" ")) {
			setTo(fromRow + 1, fromCol - 1);
			return true;
		} else {
			return false;
		}
	}
}
