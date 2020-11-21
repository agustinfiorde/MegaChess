package app.megachess.AI.pieces;

public interface PieceActionAssassin {

	boolean canBeEated();

	void assassinMission();

	boolean evaluateQuadrants(int row, int col);

}
