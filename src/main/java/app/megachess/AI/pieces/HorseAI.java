package app.megachess.AI.pieces;

public class HorseAI extends Piece {

	private int continuousFront;
	private int distantFront;

	public HorseAI(String piece, int[] position, String[][] board, String color) {
		super(piece, position, board, color);
		if (color.equals("white")) {
			this.distantFront = -2;
			this.continuousFront = -1;
		} else {
			this.distantFront = 2;
			this.continuousFront = 1;
		}
	}

	@Override
	public boolean canDefend() {
		return evaluateLikeHorse();
	}

	/*
	 * canProceed, en el caso de los caballos evalua si alguna casillas de las
	 * frontales esta disponible como para avanzar
	 */
	@Override
	public boolean canProceed() {

		for (int i = -2; i < 3; i++) {

			if (fromRow + distantFront < 0 || fromRow + distantFront > 15 || fromCol + i < 0 || fromCol + i > 15) {
				continue;
			}
			if ((continuousFront == 1 && i == -2) || (continuousFront == 1 && i == 2) || (distantFront == 2 && i == -2)
					|| (distantFront == 2 && i == 2) || (continuousFront == -1 && i == -2)
					|| (continuousFront == -1 && i == 2) || (distantFront == -2 && i == -2)
					|| (distantFront == -2 && i == 2)) {
				if (board[distantFront][i].equals(" ")) {
					setTo(distantFront, i);
					return true;
				}
				if (board[continuousFront][i].equals(" ")) {
					setTo(continuousFront, i);
					return true;
				}
			}
		}
		return false;
	}

	/**
	 * evaluateLikeHorse, evalua las casillas habiles para un caballo y ve si existe
	 * la posibilidad de comer a una pieza enemiga
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

				if (fromRow + i < 0 || fromRow + i > 15 || fromCol + j < 0 || fromCol + j > 15) {
					continue;
				}
				if ((i == 1 && j == -2) || (i == 2 && j == -1) || (i == 1 && j == 2) || (i == 2 && j == 1)
						|| (i == -1 && j == -2) || (i == -2 && j == -1) || (i == -1 && j == 2) || (i == -2 && j == 1)) {
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
