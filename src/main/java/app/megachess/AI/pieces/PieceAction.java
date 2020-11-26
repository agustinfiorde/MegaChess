package app.megachess.AI.pieces;

public interface PieceAction {

	/**
	 * canDefend, evalua si la pieza tiene alguien en sus proximidades que pueda comer
	 * @return
	 */
	boolean canDefend();

	/**
	 * canProceed, evalua si puede proceder a avanzar, APLICABLE A PEONES Y REYES
	 * @return
	 */
	boolean canProceed();

}
