package app.megachess.AI.pieces;

public class HorseAI extends Piece {

	public HorseAI(String piece, int[] position, String[][] board, String color) {
		super(piece, position, board, color);
	}

	@Override
	public boolean canDefend() {
		return evaluateLikeHorse();
	}

	@Override
	public boolean canProceed() {
		// TODO Auto-generated method stub
		return false;
	}

	private boolean evaluateLikeHorse() {

		int x;
		int y;

		for (int i = -2; i < 3; i++) {
			for (int j = -2; j < 3; j++) {
				y = fromRow + i;
				x = fromCol + j;
				if (y >= 0 && y <= 15 && x >= 0 && x <= 15) {
					setTo(y, x);
					return true;
				}
			}
		}
		return false;

	}

}
