package app.megachess.AI.pieces;

public class BishopAI extends Piece {

	public BishopAI(String piece, int[] position, String[][] board, String color) {
		super(piece, position, board, color);

	}

	@Override
	public boolean canDefend() {
		return (evaluateBot() || evaluateTop() || evaluateLeft() || evaluateRight() || evaluateBotLeft()
				|| evaluateBotRight() || evaluateTopLeft() || evaluateTopRight());
	}

	@Override
	public boolean canProceed() {
		// TODO Auto-generated method stub
		return false;
	}
}
