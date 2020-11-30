package app.megachess.AI.pieces;

import app.megachess.enums.PieceDirection;

public interface PieceActionAssassin {

	/**
	 * murder, hace referencia a una funcion que se encarga de gestionar la mision
	 * para ir a asesinar todas las piezas enemigas.
	 * 
	 * @return
	 */
	boolean murder();

	/**
	 * hunt, es una funcion encargada de controlar y ver si es posible ir a la
	 * caceria de la o las ultimas piezas restantes en el juego
	 * 
	 * @return
	 */
	boolean hunt();

	/**
	 * assassinMissionLastLine, determina la manera de actuar y evaluar lo que hay
	 * que hacer en el fondo del tablero
	 * 
	 * @param botLine
	 * @param toTop
	 */
	boolean assassinMissionLastLine(int botLine, PieceDirection toTop);

	/**
	 * assassinMissionThirdLine, determina la manera de actuar y evaluar lo que hay
	 * que hacer en la penultima fila del tablero
	 * 
	 * @param thirdLine
	 * @param botLine
	 * @param toTop
	 * @param toBot
	 */
	boolean assassinMissionThirdLine(int thirdLine, int botLine, PieceDirection toTop, PieceDirection toBot);

	/**
	 * assassinMissionSecondLine, determina la manera de actuar y evaluar lo que hay
	 * que hacer en la segunda fila del tablero
	 * 
	 * @param secondLine
	 * @param thirdLine
	 * @param botLine
	 * @param toTop
	 * @param toBot
	 */
	boolean assassinMissionSecondLine(int secondLine, int thirdLine, int botLine, PieceDirection toTop,
			PieceDirection toBot);

	/**
	 * assassinMissionFirstLine, determina la manera de actuar y evaluar lo que hay
	 * que hacer en la primera fila del tablero
	 * 
	 * @param frontLine
	 * @param secondLine
	 * @param thirdLine
	 * @param botLine
	 * @param toTop
	 * @param toBot
	 */
	boolean assassinMissionFirstLine(int frontLine, int secondLine, int thirdLine, int botLine, PieceDirection toTop,
			PieceDirection toBot);

}
