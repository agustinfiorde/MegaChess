package app.megachess.AI.pieces;

public class KingAI extends Piece {

	public KingAI(String piece, int[] position, String[][] board, String color) {
		super(piece, position, board, color);
	}

	@Override
	public boolean canDefend() {
		return (evaluateBot() || evaluateTop() || evaluateLeft() || evaluateRight() || evaluateBotLeft()
				|| evaluateBotRight() || evaluateTopLeft() || evaluateTopRight());
	}

	@Override
	public boolean canProceed() {
		if (fromRow <= 7) {
			setTo(fromRow + 1, fromCol);
			return true;
		}
		if (fromRow >= 8) {
			setTo(fromRow - 1, fromCol);
			return true;
		}
		return false;
	}

}
