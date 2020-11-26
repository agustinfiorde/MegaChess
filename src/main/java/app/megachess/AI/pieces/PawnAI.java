package app.megachess.AI.pieces;

public class PawnAI extends Piece {

	public PawnAI(String piece, int[] position, String[][] board, String color) {
		super(piece, position, board, color);
	}

	/**
	 * setFrontForFirstPawnMove, setea lo que seria el frente para un peon que esta
	 * partiendo de las lineas iniciales
	 */
	private void setFrontForFirstPawnMove() {
		if (color.equals("white")) {
			this.front = fromRow - 2;
		} else {
			this.front = fromRow + 2;
		}
	}

	@Override
	public boolean canProceed() {

		int firstLineToStart;
		int secondLineToStart;

		if (color.equals("white")) {
			firstLineToStart = 12;
			secondLineToStart = 13;
		} else {
			firstLineToStart = 3;
			secondLineToStart = 2;
		}
		if ((fromRow == firstLineToStart || fromRow == secondLineToStart)) {
			setFrontForFirstPawnMove();
			if (board[fromRow + 1][fromCol].equals(" ") && board[fromRow + 2][fromCol].equals(" ")) {
				setTo(front, fromCol);
				return true;
			}
		}

		if (board[front][fromCol].equals(" ")) {
			setTo(front, fromCol);
			return true;
		}
		return false;
	}

	@Override
	public boolean canDefend() {
		return (evaluateTopLeft() || evaluateTopRight());
	}

}
