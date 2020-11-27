package app.megachess.AI.pieces;

public class HorseAI extends Piece {

	private int continuousFront;
	private int distantFront;

	public HorseAI(String piece, int[] position, String[][] board, String color) {
		super(piece, position, board, color);
		if (color.equals("white")) {
			this.distantFront = fromRow - 2;
			this.continuousFront = fromRow - 1;
		} else {
			this.distantFront = fromRow + 2;
			this.continuousFront = fromRow + 1;
		}
	}

	@Override
	public boolean canDefend() {
		return evaluateLikeHorse();
	}

	@Override
	public boolean canProceed() {

		int front = continuousFront;
		int x;
		
		for (int i = 0; i < 2; i++) {
			for (int j = -2; j < 3; j++) {
				
				x = fromCol + j;
				
				if (front >= 0 && front <= 15 && x >= 0 && x <= 15
						&& ((i == 2 && j == -1) || (i == 2 && j == 1) || (i == 1 && j == -2) || (i == 1 && j == 2)
								|| (i == -1 && j == -2) || (i == -1 && j == 2) || (i == -2 && j == -1)
								|| (i == -2 && j == 1))) {
					if (evaluateQuadrant(front, x)) {
						setTo(front, j);
						return true;
					}
				}
			}
			front = distantFront;
		}

		return false;
	}

	/**
	 * evaluateLikeHorse, es una funcion especifica para evaluar las 8 posibles
	 * casillas que alcanza un caballo para ver si puede comer
	 * 
	 * @return
	 */
	private boolean evaluateLikeHorse() {
		int x;
		int y;
		for (int i = -2; i < 3; i++) {
			for (int j = -2; j < 3; j++) {
				y = fromRow + i;
				x = fromCol + j;

				if (y >= 0 && y <= 15 && x >= 0 && x <= 15
						&& ((i == 2 && j == -1) || (i == 2 && j == 1) || (i == 1 && j == -2) || (i == 1 && j == 2)
								|| (i == -1 && j == -2) || (i == -1 && j == 2) || (i == -2 && j == -1)
								|| (i == -2 && j == 1))) {
					if (evaluateQuadrant(y, x)) {
						setTo(y, x);
						return true;
					}
				}
			}
		}
		return false;
	}

}
