package app.megachess.AI.pieces;

import app.megachess.utils.ChessUtil;

public class PawnAI extends Piece {

	private int frontInitial;

	public PawnAI(int[] position, String[][] board, String color) {
		super(position, board, color);

		if (color.equals("white")) {
			this.front = fromRow - 1;
			this.frontInitial = fromRow - 2;
		} else {
			this.front = fromRow + 1;
			this.frontInitial = fromRow + 2;
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
		
		if (fromRow == firstLineToStart || fromRow == secondLineToStart) {

			if (left != null && left >= 0) {
				if (ChessUtil.isPawn(board, front, left, color)) {
					if (board[frontInitial][fromCol].equals(" ") && board[front][fromCol].equals(" ")) {
						setTo(frontInitial, fromCol);
						return true;
					}
				}
			}
			if (right != null && right <= 15) {
				if (ChessUtil.isPawn(board, front, right, color)) {
					if (board[frontInitial][fromCol].equals(" ") && board[front][fromCol].equals(" ")) {
						setTo(frontInitial, fromCol);
						return true;
					}
				}
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
