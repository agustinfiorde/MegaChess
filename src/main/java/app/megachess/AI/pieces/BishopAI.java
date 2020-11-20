package app.megachess.AI.pieces;

public class BishopAI extends Piece {

	public BishopAI(String piece, int[] position) {
		super(piece, position, position, piece);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean canMove() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isMyTeam() {
		// TODO Auto-generated method stub
		return false;
	}

}
