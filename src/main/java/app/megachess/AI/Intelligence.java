package app.megachess.AI;

import java.util.List;

import app.megachess.AI.pieces.BishopAI;
import app.megachess.AI.pieces.HorseAI;
import app.megachess.AI.pieces.KingAI;
import app.megachess.AI.pieces.PawnAI;
import app.megachess.AI.pieces.QueenAI;
import app.megachess.AI.pieces.RookAI;
import app.megachess.models.DataMessage;
import app.megachess.models.Message;
import app.megachess.models.Response;
import app.megachess.utils.ChessUtil;
import app.megachess.utils.Util;

public class Intelligence {

	private static int fromRow = 0;
	private static int fromCol = 0;
	private static int toRow = 0;
	private static int toCol = 0;

	private Intelligence() {
	}

	/**
	 * -Primero que nada evaluó si alguna de mis piezas puede comer alguna pieza del
	 * enemigo que no sea un peón o caballos (**ya que si como un peón o caballo
	 * expongo la pieza que fue a comerlo y no se justifica en cuanto a puntajes).
	 *
	 * -En caso que nadie pueda defender busco reinas para que hagan su misión de
	 * avanzar a la casilla que se le ordeno, con el objetivo de ser soportes o que
	 * se arrinconen a la izquierda (**va a ser el sector designado para esconder
	 * las reinas y evitar que queden expuestas ante peones enemigos).
	 *
	 * -En caso que ninguna de mis piezas pueda comer y las reinas ya hayan cumplido
	 * su misión, procedo con la estrategia de hacer avanzar los peones de a grupos
	 * de 4 para que se protejan entre si hasta que lleguen a ser reinas. El avance
	 * de los grupos tiene un orden establecido para evitar exponer las piezas más
	 * importantes del fondo de mi tablero. Se empieza por algun extremo de las
	 * torres, luego sector de caballos, luego alfiles y luego se avanza por el
	 * sector del otro lado con la misma logica.
	 *
	 * -En caso que ya no haya más opciones, procedo a mover o los reyes o los
	 * alfiles o los caballos. Si estamos en este punto asumo que no hay más reinas
	 * ni torres ni peones
	 * 
	 * @param msj
	 * @return JSON o ""
	 */
	public static String evaluate(Message msj) {

		DataMessage msjData = msj.getData();
		String[][] board = ChessUtil.getBoard(msjData.getBoard());
		String color = msjData.getActual_turn();
		String answer = null;


		// DEFENSA de peones
		answer = pawnDefense(msj, board, color);
		if (answer != null) {
			return answer;
		}
		// DEFENSA con reyes
		answer = kingDefense(msj, board, color);
		if (answer != null) {
			return answer;
		}
		// DEFENSA de reinas
		answer = queenDefense(msj, board, color);
		if (answer != null) {
			return answer;
		}
		// DEFENSA de caballos
		answer = horseDefense(msj, board, color);
		if (answer != null) {
			return answer;
		}
		// DEFENSA de alfiles
		answer = bishopDefense(msj, board, color);
		if (answer != null) {
			return answer;
		}
		// DEFENSA de torres
		answer = rookDefense(msj, board, color);
		if (answer != null) {
			return answer;
		}

		// ACCION reinas
		answer = queenAction(msj, board, color);
		if (answer != null) {
			return answer;
		}
		// ACCION torres
		answer = rookAction(msj, board, color);
		if (answer != null) {
			return answer;
		}

		// MOVER peones
		answer = pawnResolver(msj, board, color);
		if (answer != null) {
			return answer;
		}
		// MOVER reyes
		answer = kingProceed(msj, board, color);
		if (answer != null) {
			return answer;
		}
		// MOVER alfiles
		answer = bishopProceed(msj, board, color);
		if (answer != null) {
			return answer;
		}
		// MOVER caballos
		answer = horseProceed(msj, board, color);
		if (answer != null) {
			return answer;
		}

		return "";
	}

	/**
	 * queenDefense se encarga de recibir la informacion real del juego y procesar
	 * un mensaje de respuesta si alguna reina puede defender.
	 * 
	 * @param msj
	 * @param board
	 * @param color
	 * @return JSON o null
	 */
	public static String queenDefense(Message msj, String[][] board, String color) {

		List<Response> responses = ChessUtil.getPiecesByColor(board, "q", color);

		QueenAI queen;
		for (Response r : responses) {
			queen = new QueenAI(new int[] { r.getFromRow(), r.getFromCol() }, board, color);
			if (queen.canDefend()) {
				fromCol = queen.getFromCol();
				toCol = queen.getToCol();
				fromRow = queen.getFromRow();
				toRow = queen.getToRow();
				return Util.move(msj, fromRow, fromCol, toRow, toCol);
			}
		}
		return null;
	}

	/**
	 * horseDefense se encarga de recibir la informacion real del juego y procesar
	 * un mensaje de respuesta si algun caballo puede defender
	 * 
	 * @param msj
	 * @param board
	 * @param color
	 * @return JSON o null
	 */
	public static String horseDefense(Message msj, String[][] board, String color) {

		List<Response> responses = ChessUtil.getPiecesByColor(board, "h", color);

		HorseAI horse;
		for (Response r : responses) {
			horse = new HorseAI(new int[] { r.getFromRow(), r.getFromCol() }, board, color);
			if (horse.canDefend()) {
				fromCol = horse.getFromCol();
				toCol = horse.getToCol();
				fromRow = horse.getFromRow();
				toRow = horse.getToRow();
				return Util.move(msj, fromRow, fromCol, toRow, toCol);
			}
		}
		return null;
	}

	/**
	 * rookDefense se encarga de recibir la informacion real del juego y procesar un
	 * mensaje de respuesta si alguna torre puede defender.
	 * 
	 * @param msj
	 * @param board
	 * @param color
	 * @return JSON o null
	 */
	public static String rookDefense(Message msj, String[][] board, String color) {

		List<Response> responses = ChessUtil.getPiecesByColor(board, "r", color);

		RookAI rook;
		for (Response r : responses) {
			rook = new RookAI(new int[] { r.getFromRow(), r.getFromCol() }, board, color);
			if (rook.canDefend()) {
				fromCol = rook.getFromCol();
				toCol = rook.getToCol();
				fromRow = rook.getFromRow();
				toRow = rook.getToRow();
				return Util.move(msj, fromRow, fromCol, toRow, toCol);
			}
		}
		return null;
	}

	/**
	 * kingDefense se encarga de recibir la informacion real del juego y procesar un
	 * mensaje de respuesta si algun rey puede defender
	 * 
	 * @param msj
	 * @param board
	 * @param color
	 * @return JSON o null
	 */
	public static String kingDefense(Message msj, String[][] board, String color) {

		List<Response> responses = ChessUtil.getPiecesByColor(board, "k", color);
		KingAI king;
		for (Response r : responses) {
			king = new KingAI(new int[] { r.getFromRow(), r.getFromCol() }, board, color);
			if (king.canDefend()) {
				fromCol = king.getFromCol();
				toCol = king.getToCol();
				fromRow = king.getFromRow();
				toRow = king.getToRow();
				return Util.move(msj, fromRow, fromCol, toRow, toCol);
			}
		}
		return null;
	}

	/**
	 * bishopDefense se encarga de recibir la informacion real del juego y procesar
	 * un mensaje de respuesta si algun alfil puede defender
	 * 
	 * @param msj
	 * @param board
	 * @param color
	 * @return JSON o null
	 */
	public static String bishopDefense(Message msj, String[][] board, String color) {

		List<Response> responses = ChessUtil.getPiecesByColor(board, "b", color);
		BishopAI bishop;
		for (Response r : responses) {
			bishop = new BishopAI(new int[] { r.getFromRow(), r.getFromCol() }, board, color, false);
			if (bishop.canDefend()) {
				fromCol = bishop.getFromCol();
				toCol = bishop.getToCol();
				fromRow = bishop.getFromRow();
				toRow = bishop.getToRow();
				return Util.move(msj, fromRow, fromCol, toRow, toCol);
			}
		}
		return null;
	}

	/**
	 * pawnDefense se encarga de recibir la informacion real del juego y procesar un
	 * mensaje de respuesta si algun peon puede defender
	 * 
	 * @param msj
	 * @param board
	 * @param color
	 * @return JSON o null
	 */
	public static String pawnDefense(Message msj, String[][] board, String color) {
		List<Response> responses = ChessUtil.pawnsActives(board, color);
		PawnAI pawn;
		for (Response r : responses) {
			pawn = new PawnAI(new int[] { r.getFromRow(), r.getFromCol() }, board, color, false);
			if (pawn.canDefend()) {
				fromCol = pawn.getFromCol();
				toCol = pawn.getToCol();
				fromRow = pawn.getFromRow();
				toRow = pawn.getToRow();
				return Util.move(msj, fromRow, fromCol, toRow, toCol);
			}
		}
		return null;
	}

	/**
	 * queenAction se encarga de recibir informacion real del juego y buscar la
	 * reina posicionada mas proxima al tablero enemigo, si esta pieza no esta
	 * bloqueda debera cumplir alguna de sus dos misiones (ir al medio o esconderse
	 * en la izquierda).
	 * 
	 * @param msj
	 * @param board
	 * @param color
	 * @return JSON o null
	 */
	public static String queenAction(Message msj, String[][] board, String color) {
		List<Response> responses = ChessUtil.getPiecesByColor(board, "q", color);
		QueenAI queen;
		for (Response r : responses) {
			queen = new QueenAI(new int[] { r.getFromRow(), r.getFromCol() }, board, color);
			if (queen.canProceed()) {
				fromCol = queen.getFromCol();
				toCol = queen.getToCol();
				fromRow = queen.getFromRow();
				toRow = queen.getToRow();

				return Util.move(msj, fromRow, fromCol, toRow, toCol);
			}
//			if (queen.hide()) {
//				fromCol = queen.getFromCol();
//				toCol = queen.getToCol();
//				fromRow = queen.getFromRow();
//				toRow = queen.getToRow();
//
//				return Util.move(msj, fromRow, fromCol, toRow, toCol);
//			}
		}
		return null;
	}

	/**
	 * rookAction se encarga de recibir informacion real del juego y buscar la torre
	 * libre, si esta pieza no esta bloqueda debera cumplir alguna de sus dos
	 * misiones (ir al medio o esconderse en la izquierda).
	 * 
	 * @param msj
	 * @param board
	 * @param color
	 * @return JSON o null
	 */
	public static String rookAction(Message msj, String[][] board, String color) {
		List<Response> responses = ChessUtil.getPiecesByColor(board, "r", color);
		RookAI rook;
		for (Response r : responses) {
			rook = new RookAI(new int[] { r.getFromRow(), r.getFromCol() }, board, color);
			if (rook.canProceed()) {
				fromCol = rook.getFromCol();
				toCol = rook.getToCol();
				fromRow = rook.getFromRow();
				toRow = rook.getToRow();
				return Util.move(msj, fromRow, fromCol, toRow, toCol);
			}

//			if (rook.hide()) {
//				fromCol = rook.getFromCol();
//				toCol = rook.getToCol();
//				fromRow = rook.getFromRow();
//				toRow = rook.getToRow();
//				return Util.move(msj, fromRow, fromCol, toRow, toCol);
//			}
		}
		return null;
	}

	/**
	 * pawnAction controla la accion a realizar por algun peon segun la situacion
	 * 
	 * @param msj
	 * @param board
	 * @param color
	 * @return JSON o null
	 */
	public static String pawnAction(Message msj, String[][] board, String color, List<Response> responses,
			boolean initialProceed) {

		PawnAI pawn;
		for (Response r : responses) {
			pawn = new PawnAI(new int[] { r.getFromRow(), r.getFromCol() }, board, color, initialProceed);
			if (pawn.canProceed()) {
				fromCol = pawn.getFromCol();
				toCol = pawn.getToCol();
				fromRow = pawn.getFromRow();
				toRow = pawn.getToRow();
				return Util.move(msj, fromRow, fromCol, toRow, toCol);
			}
		}
		return null;
	}

	/**
	 * progressBySector, hace avanzar lo peones como grupo para evitar que se puedan
	 * defender unos a los otros
	 * 
	 * @param msj
	 * @param board
	 * @param fromCol
	 * @param toCol
	 * @param color
	 * @return JSON o null
	 */
	public static String progressBySectorInGroup(Message msj, String[][] board, int fromCol, int toCol, String color,
			boolean initialProceed) {
		List<Response> responses;
		if (color.equals("white")) {
			responses = ChessUtil.findWhitePawnsGroupProgress(board, fromCol, toCol, color);
		} else {
			responses = ChessUtil.findBlackPawnsGroupProgress(board, fromCol, toCol, color);
		}
		if (!responses.isEmpty()) {
			String answer = pawnAction(msj, board, color, responses, initialProceed);
			if (answer != null) {
				return answer;
			}
		}
		return null;
	}

	/**
	 * progressBySectorDirectly, hace avanzar a los peones segun el orden especifico
	 * en el cual se le mandan a la funcion. El objetivo es hacerlos avanzar de
	 * forma directa
	 * 
	 * @param msj
	 * @param board
	 * @param fromCol
	 * @param toCol
	 * @param color
	 * @param initialProceed
	 * @return JSON o null
	 */
	public static String progressBySectorDirectly(Message msj, String[][] board, int fromCol, int toCol, String color,
			boolean initialProceed) {
		List<Response> responses;
		if (color.equals("white")) {
			responses = ChessUtil.findWhitePawnsDirectProgress(board, fromCol, toCol, color);
		} else {
			responses = ChessUtil.findBlackPawnsDirectProgress(board, fromCol, toCol, color);
		}
		if (!responses.isEmpty()) {
			String answer = pawnAction(msj, board, color, responses, initialProceed);
			if (answer != null) {
				return answer;
			}
		}
		return null;
	}

	/**
	 * pawnResolver, evalua segun la situacion del tablero para determinar que
	 * desicion tomar para el avance de los peones
	 * 
	 * @param msj
	 * @param board
	 * @param color
	 * @return JSON o null
	 */
	public static String pawnResolver(Message msj, String[][] board, String color) {

		String answer;
		int fromCol;
		int toCol;

		/*
		 * initialProceed, condiciona si los peones avanzan a pasos cortos o largos, eso
		 * depende si tienen soportes del equipo en el medio y si hay enemigos asechando
		 */
		boolean initialProceed = ((ChessUtil.countQueenInMid(board, color) + ChessUtil.countQueenInMid(board, color) > 3)
				&& (ChessUtil.countQueenEnemiesInMid(board, color) == 0)) ? true : false;

		// sector de Torres izquierda col 0
		fromCol = 0;
		toCol = 1;
		answer = progressBySectorDirectly(msj, board, fromCol, toCol, color, true);
		if (answer != null) {
			return answer;
		}

		// sector de Torres derecha col 15
		fromCol = 14;
		toCol = 15;
		answer = progressBySectorDirectly(msj, board, fromCol, toCol, color, true);
		if (answer != null) {
			return answer;
		}

		// sector Reinas
		fromCol = 7;
		toCol = 6;
		answer = progressBySectorInGroup(msj, board, fromCol, toCol, color, initialProceed);
		if (answer != null) {
			return answer;
		}

		// sector Alfiles izquierda
		fromCol = 5;
		toCol = 4;
		answer = progressBySectorInGroup(msj, board, fromCol, toCol, color, initialProceed);
		if (answer != null) {
			return answer;
		}

		// sector Alfiles derecha
		fromCol = 15;
		toCol = 14;
		answer = progressBySectorInGroup(msj, board, fromCol, toCol, color, initialProceed);
		if (answer != null) {
			return answer;
		}

		// sector Caballos izquierda
		fromCol = 3;
		toCol = 2;
		answer = progressBySectorInGroup(msj, board, fromCol, toCol, color, initialProceed);
		if (answer != null) {
			return answer;
		}

		// sector Caballos derecha
		fromCol = 13;
		toCol = 12;
		answer = progressBySectorInGroup(msj, board, fromCol, toCol, color, initialProceed);
		if (answer != null) {
			return answer;
		}

		// sector Alfiles derecha
		fromCol = 15;
		toCol = 14;
		answer = progressBySectorInGroup(msj, board, fromCol, toCol, color, initialProceed);
		if (answer != null) {
			return answer;
		}

		return null;
	}

	/**
	 * kingProceed, llama al rey disponible para empezar a moverlo en el mapa. De
	 * esta manera podra hacer puntos.
	 * 
	 * @param msj
	 * @param board
	 * @param color
	 * @return JSON o null
	 */
	public static String kingProceed(Message msj, String[][] board, String color) {
		List<Response> responses = ChessUtil.getKingsByColor(board, "k", color);
		KingAI king;
		for (Response r : responses) {
			king = new KingAI(new int[] { r.getFromRow(), r.getFromCol() }, board, color);
			if (king.canProceed()) {
				fromCol = king.getFromCol();
				toCol = king.getToCol();
				fromRow = king.getFromRow();
				toRow = king.getToRow();
				return Util.move(msj, fromRow, fromCol, toRow, toCol);
			}
		}
		return null;
	}

	/**
	 * bishopProceed, llama al alfil disponible para empezar a hacerlo avanzar. De
	 * esta manera podra avanzar para ver si come o hacer puntos por el simple hecho
	 * de moverse
	 * 
	 * @param msj
	 * @param board
	 * @param color
	 * @return JSON o null
	 */
	public static String bishopProceed(Message msj, String[][] board, String color) {
		List<Response> responses = ChessUtil.getPiecesByColor(board, "b", color);
		BishopAI bishop;
		for (Response r : responses) {
			bishop = new BishopAI(new int[] { r.getFromRow(), r.getFromCol() }, board, color, false);
			if (bishop.canProceed()) {
				fromCol = bishop.getFromCol();
				toCol = bishop.getToCol();
				fromRow = bishop.getFromRow();
				toRow = bishop.getToRow();
				return Util.move(msj, fromRow, fromCol, toRow, toCol);
			}
		}
		return null;
	}

	/**
	 * horseProceed, llama al caballo disponible para empezar a hacerlo avanzar, de
	 * esta manera podra buscar piezas para comer o hacer puntos por el simple hecho
	 * de moverse
	 * 
	 * @param msj
	 * @param board
	 * @param color
	 * @return respuesta en JSON o null
	 */
	public static String horseProceed(Message msj, String[][] board, String color) {
		List<Response> responses = ChessUtil.getPiecesByColor(board, "h", color);
		HorseAI horse;
		for (Response r : responses) {
			horse = new HorseAI(new int[] { r.getFromRow(), r.getFromCol() }, board, color);
			if (horse.canProceed()) {
				fromCol = horse.getFromCol();
				toCol = horse.getToCol();
				fromRow = horse.getFromRow();
				toRow = horse.getToRow();
				return Util.move(msj, fromRow, fromCol, toRow, toCol);
			}
		}
		return null;
	}

}
