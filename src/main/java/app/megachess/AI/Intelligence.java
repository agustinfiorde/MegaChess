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

	private static List<Response> responses;
	private static int fromRow = 0;
	private static int fromCol = 0;
	private static int toRow = 0;
	private static int toCol = 0;

	private Intelligence() {
	}

	/**
	 * evaluate, recibe el Message, lo descompone en objetos y variables utiles para
	 * procesar
	 * 
	 * LISTA DE PASOS DE MI IA:
	 * 
	 * -si estamos ante el primer movimiento:
	 * 
	 * El objetivo para empezar es adelantar mis peones de la linea 9, con el fin de
	 * liberar espacio en el fondo.
	 * 
	 * Mi plan de accion es implementar la estrategia de "Rey Bailarin", esta
	 * consiste en mover el rey, ya que al moverlo me da 100 puntos. Las prioridades
	 * estarasn explicadas a continuacion
	 * 
	 * -en los siguientes turnos mi orden de accion es el siguiente:
	 * 
	 * -DEFENDER CON TODAS LAS PIEZAS, ES DECIR EVALUAN SI TIENE AMENAZAS CERCA, EN
	 * CASO DE SER REAL, COMEN PRIMERO ANTES DE SER COMIDOS. LOS PEONES SON LA
	 * PRIMER LINEA DE DEFENZA
	 * 
	 * -MOVER EL REY SI ES QUE EN EL PASO ANTERIOR NO SE EVALUO AMENAZA
	 * 
	 * -VER SI TENGO DISPONIBLE UNA REINA ASESINA O UNA TORRE ASESINA. TOMAR LA QUE
	 * PUEDA USAR Y LLEVARLA AL CUADRANTE ENEMIGO Y MANDARLA A MATAR PIEZAS
	 * 
	 * -SI NO HAY REINA ASESINA NI TORRE ASESINA, MOVER UN PEON HASTA QUE CORONE Y
	 * SEA MI PROXIMA REINA ASESINA
	 * 
	 * -EN CASO QUE NADA DE ESTO SEA POSIBLE CABALLOS Y ALFILES TIENEN LA ORDEN DE
	 * AVANZAR PARA JUNTAR PUNTOS E IR EN BUSCA DE ENEMIGOS
	 * 
	 * -BASICAMENTE LA ESTRATEGIA ES DEFENDER COMO PILAR FUNDAMENTAL, SI NO EXISTE
	 * AMENAZA MOVER EL REY, CUANDO ESTO NO SEA POSIBLE. COMENZAR A ATACAR
	 * 
	 * @param msj
	 * @return
	 */
	public static String evaluate(Message msj) {

		DataMessage msjData = msj.getData();
		String[][] board = ChessUtil.getBoard(msjData.getBoard());
		String color = msjData.getActual_turn();
		String answer = null;

		// KINGDANCER
//		answer = pawnActionToKingDancer(msj, board, color);
//		if (answer != null) {
//			return answer;
//		}
//		answer = bishopActionToKingDancer(msj, board, color);
//		if (answer != null) {
//			return answer;
//		}

		// DEFENSA de peones
//		answer = pawnDefense(msj, board, color);
//		if (answer != null) {
//			return answer;
//		}
		// DEFENSA de caballos
//		answer = horseDefense(msj, board, color);
//		if (answer != null) {
//			return answer;
//		}
		// DEFENSA de alfiles
//		answer = bishopDefense(msj, board, color);
//		if (answer != null) {
//			return answer;
//		}
		// DEFENSA de torres
//		answer = rookDefense(msj, board, color);
//		if (answer != null) {
//			return answer;
//		}
		// DEFENSA con reinas
		answer = queenDefense(msj, board, color);
		if (answer != null) {
			return answer;
		}
		// DEFENSA con reyes
//		answer = kingDefense(msj, board, color);
//		if (answer != null) {
//			return answer;
//		}
		// MOVER reyes
//		answer = kingProceed(msj, board, color);
//		if (answer != null) {
//			return answer;
//		}
		// MOVER peones
		answer = pawnResolver(msj, board, color);
		if (answer != null) {
			return answer;
		}
		// reina ASESINA
		answer = queenAction(msj, board, color);
		if (answer != null) {
			return answer;
		}
		// torre ASESINA
//		answer = rookAction(msj, board, color);
//		if (answer != null) {
//			return answer;
//		}
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
	 * un mensaje de respuesta si alguna reina tiene que defender, es decir si esta
	 * en peligro
	 * 
	 * @param msj
	 * @param board
	 * @param color
	 * @return
	 */
	public static String queenDefense(Message msj, String[][] board, String color) {
		if (color.equals("white")) {
			responses = ChessUtil.getDefenderPiecesBot(board, "Q");
		} else {
			responses = ChessUtil.getDefenderPiecesTop(board, "q");
		}
		QueenAI queen;
		for (Response r : responses) {
			queen = new QueenAI(r.getPiece(), new int[] { r.getFromRow(), r.getFromCol() }, board, color);
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
	 * un mensaje de respuesta si algun caballo tiene que defender, es decir si esta
	 * en peligro
	 * 
	 * @param msj
	 * @param board
	 * @param color
	 * @return
	 */
	public static String horseDefense(Message msj, String[][] board, String color) {
		if (color.equals("white")) {
			responses = ChessUtil.getDefenderPiecesBot(board, "H");
		} else {
			responses = ChessUtil.getDefenderPiecesTop(board, "h");
		}
		HorseAI horse;
		for (Response r : responses) {
			horse = new HorseAI(r.getPiece(), new int[] { r.getFromRow(), r.getFromCol() }, board, color);
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
	 * mensaje de respuesta si alguna torre tiene que defender, es decir si esta en
	 * peligro
	 * 
	 * @param msj
	 * @param board
	 * @param color
	 * @return
	 */
	public static String rookDefense(Message msj, String[][] board, String color) {
		if (color.equals("white")) {
			responses = ChessUtil.getDefenderPiecesBot(board, "R");
		} else {
			responses = ChessUtil.getDefenderPiecesTop(board, "r");
		}
		RookAI rook;
		for (Response r : responses) {
			rook = new RookAI(r.getPiece(), new int[] { r.getFromRow(), r.getFromCol() }, board, color);
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
	 * mensaje de respuesta si algun rey tiene que defender, es decir si esta en
	 * peligro
	 * 
	 * @param msj
	 * @param board
	 * @param color
	 * @return
	 */
	public static String kingDefense(Message msj, String[][] board, String color) {
		if (color.equals("white")) {
			responses = ChessUtil.getDefenderPiecesBot(board, "K");
		} else {
			responses = ChessUtil.getDefenderPiecesTop(board, "k");
		}
		KingAI king;
		for (Response r : responses) {
			king = new KingAI(r.getPiece(), new int[] { r.getFromRow(), r.getFromCol() }, board, color);
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
	 * un mensaje de respuesta si algun alfil tiene que defender, es decir si esta
	 * en peligro
	 * 
	 * @param msj
	 * @param board
	 * @param color
	 * @return
	 */
	public static String bishopDefense(Message msj, String[][] board, String color) {
		if (color.equals("white")) {
			responses = ChessUtil.getDefenderPiecesBot(board, "B");
		} else {
			responses = ChessUtil.getDefenderPiecesTop(board, "b");
		}
		BishopAI bishop;
		for (Response r : responses) {
			bishop = new BishopAI(r.getPiece(), new int[] { r.getFromRow(), r.getFromCol() }, board, color, false);
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
	 * mensaje de respuesta si algun peon tiene que defender, es decir si esta en
	 * peligro y puede comer
	 * 
	 * @param msj
	 * @param board
	 * @param color
	 * @return
	 */
	public static String pawnDefense(Message msj, String[][] board, String color) {
		responses = ChessUtil.pawnsActives(board, color);
		PawnAI pawn;
		for (Response r : responses) {
			pawn = new PawnAI(r.getPiece(), new int[] { r.getFromRow(), r.getFromCol() }, board, color);
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
	 * bloqueda, debera cumplir su mision de asesinar a las piezas oponentes desde
	 * adentro
	 * 
	 * @param msj
	 * @param board
	 * @param color
	 * @return
	 */
	public static String queenAction(Message msj, String[][] board, String color) {
		QueenAI queen;
		Response res;
		res = null;
		if (color.equals("white")) {
			res = ChessUtil.topPossitionAssassin(board, "Q");
		} else {
			res = ChessUtil.botPossitionAssassin(board, "q");
		}
		if (res.isExist()) {
			queen = new QueenAI(res.getPiece(), new int[] { res.getFromRow(), res.getFromCol() }, board, color);
			if (queen.murder()) {
				fromCol = queen.getFromCol();
				toCol = queen.getToCol();
				fromRow = queen.getFromRow();
				toRow = queen.getToRow();
				return Util.move(msj, fromRow, fromCol, toRow, toCol);
			}
			if (queen.hunt()) {
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
	 * rookAction se encarga de recibir informacion real del juego y buscar la torre
	 * posicionada mas proxima al tablero enemigo, si esta pieza no esta bloqueda,
	 * debera cumplir su mision de asesinar a las piezas oponentes desde adentro
	 * 
	 * @param msj
	 * @param board
	 * @param color
	 * @return
	 */
	public static String rookAction(Message msj, String[][] board, String color) {
		RookAI rook;
		Response res;
		res = null;
		if (color.equals("white")) {
			res = ChessUtil.topPossitionAssassin(board, "R");
		} else {
			res = ChessUtil.botPossitionAssassin(board, "r");
		}
		if (res.isExist()) {
			rook = new RookAI(res.getPiece(), new int[] { res.getFromRow(), res.getFromCol() }, board, color);
			if (rook.murder()) {
				fromCol = rook.getFromCol();
				toCol = rook.getToCol();
				fromRow = rook.getFromRow();
				toRow = rook.getToRow();
				return Util.move(msj, fromRow, fromCol, toRow, toCol);
			}
			if (rook.hunt()) {
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
	 * pawnAction determina que el peon mas adelantado debera cumplir su mision de
	 * llegar a coronar, asi se transforma en reina asesina
	 * 
	 * @param msj
	 * @param board
	 * @param color
	 * @return
	 */
	public static String pawnAction(Message msj, String[][] board, String color) {

		responses = ChessUtil.pawnsActives(board, color);
		PawnAI pawn;
		for (Response r : responses) {
			pawn = new PawnAI(r.getPiece(), new int[] { r.getFromRow(), r.getFromCol() }, board, color);
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
	 * Metodo de apertura del tablero para evitar estrategia implementada por el
	 * jugador EnzoC. La funcion busca hacer avanzar a los peones en grupo, para
	 * evitar que si una reina los intenta asesinar, estaran en grupo y se podran
	 * defender mutuamente
	 * 
	 * @param msj
	 * @param board
	 * @param color
	 * @return
	 */
	public static String pawnResolver(Message msj, String[][] board, String color) {

		// sector 1
		if (color.equals("white")) {
			fromCol = 15;
			toCol = 14;
			fromRow = 13;
			toRow = 9;
			responses = ChessUtil.findPawnByBotSector(board, fromRow, fromCol, toRow, toCol);
		} else {
			fromCol = 14;
			toCol = 15;
			fromRow = 2;
			toRow = 6;
			responses = ChessUtil.findPawnByTopSector(board, fromRow, fromCol, toRow, toCol);
		}

		if (!responses.isEmpty()) {
			String answer = pawnAction(msj, board, color);
			if (answer != null) {
				return answer;
			}
		}

		// sector 2
		if (color.equals("white")) {
			fromCol = 1;
			toCol = 0;
			responses = ChessUtil.findPawnByBotSector(board, fromRow, fromCol, toRow, toCol);
		} else {
			fromCol = 0;
			toCol = 1;
			responses = ChessUtil.findPawnByTopSector(board, fromRow, fromCol, toRow, toCol);
		}

		if (!responses.isEmpty()) {
			String answer = pawnAction(msj, board, color);
			if (answer != null) {
				return answer;
			}
		}

		// sector 3
		if (color.equals("white")) {
			fromCol = 7;
			toCol = 5;
			responses = ChessUtil.findPawnByBotSector(board, fromRow, fromCol, toRow, toCol);
		} else {
			fromCol = 5;
			toCol = 7;
			responses = ChessUtil.findPawnByTopSector(board, fromRow, fromCol, toRow, toCol);
		}

		if (!responses.isEmpty()) {
			String answer = pawnAction(msj, board, color);
			if (answer != null) {
				return answer;
			}
		}

		// sector 4
		if (color.equals("white")) {
			fromCol = 10;
			toCol = 8;
			responses = ChessUtil.findPawnByBotSector(board, fromRow, fromCol, toRow, toCol);
		} else {
			fromCol = 8;
			toCol = 10;
			responses = ChessUtil.findPawnByTopSector(board, fromRow, fromCol, toRow, toCol);
		}

		if (!responses.isEmpty()) {
			String answer = pawnAction(msj, board, color);
			if (answer != null) {
				return answer;
			}
		}

		responses = ChessUtil.pawnsActives(board, color);

		if (!responses.isEmpty()) {
			String answer = pawnAction(msj, board, color);
			if (answer != null) {
				return answer;
			}
		}

		return null;
	}

	/**
	 * kingProceed, llama al rey disponible para empezar a moverlo en el mapa. de
	 * esta manera podra hacer puntos. Complemento de la estrategia KingDancer
	 * 
	 * @param msj
	 * @param board
	 * @param color
	 * @return
	 */
	public static String kingProceed(Message msj, String[][] board, String color) {
		List<Response> responses = ChessUtil.getPiecesByColor(board, "k", color);
		KingAI king;
		for (Response r : responses) {
			king = new KingAI(r.getPiece(), new int[] { r.getFromRow(), r.getFromCol() }, board, color);
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
	 * bishopProceed, llama al alfil disponible para empezar a hacerlo avanzar, de
	 * esta manera podra cazar o hacer puntos por el simple hecho de moverse
	 * 
	 * @param msj
	 * @param board
	 * @param color
	 * @return
	 */
	public static String bishopProceed(Message msj, String[][] board, String color) {
		List<Response> responses = ChessUtil.getPiecesByColor(board, "b", color);
		BishopAI bishop;
		for (Response r : responses) {
			bishop = new BishopAI(r.getPiece(), new int[] { r.getFromRow(), r.getFromCol() }, board, color, false);
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
	 * esta manera podra cazar o hacer puntos por el simple hecho de moverse
	 * 
	 * @param msj
	 * @param board
	 * @param color
	 * @return
	 */
	public static String horseProceed(Message msj, String[][] board, String color) {
		List<Response> responses = ChessUtil.getPiecesByColor(board, "h", color);
		HorseAI horse;
		for (Response r : responses) {
			horse = new HorseAI(r.getPiece(), new int[] { r.getFromRow(), r.getFromCol() }, board, color);
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

	/**
	 * pawnActionToKingDancer, son los movimientos necesarios que tiene que realizar
	 * los peones para abrir el tablero a implementar la estrategia de KingDancer
	 * 
	 * @param msj
	 * @param board
	 * @param color
	 * @return
	 */
	public static String pawnActionToKingDancer(Message msj, String[][] board, String color) {

		fromCol = 9;
		toCol = 9;
		int targetFirstLine;
		int fromFirstLine;
		int targetSecondLine;
		int fromSecondLine;
		String piece;

		if (color.equals("white")) {
			targetFirstLine = 11;
			fromFirstLine = 12;
			targetSecondLine = 12;
			fromSecondLine = 13;
			piece = "P";
		} else {
			targetFirstLine = 4;
			fromFirstLine = 3;
			targetSecondLine = 3;
			fromSecondLine = 2;
			piece = "p";
		}

		if (board[targetFirstLine][fromCol].equals(" ") && board[fromFirstLine][fromCol].equals(piece)) {
			return Util.move(msj, fromFirstLine, fromCol, targetFirstLine, fromCol);
		}
		if (board[targetSecondLine][fromCol].equals(" ") && board[fromSecondLine][fromCol].equals(piece)) {
			return Util.move(msj, fromSecondLine, fromCol, targetSecondLine, fromCol);
		}
		return null;
	}

	/**
	 * bishopActionToKingDancer, es un movimiento que se hace unicamente para
	 * liberar el espacio necesario para que el Rey implemente la estrategia de
	 * Dancer.
	 * 
	 * @param msj
	 * @param board
	 * @param color
	 * @return
	 */
	public static String bishopActionToKingDancer(Message msj, String[][] board, String color) {
		List<Response> responses = ChessUtil.getPiecesByColor(board, "b", color);
		BishopAI bishop;
		for (Response r : responses) {
			bishop = new BishopAI(r.getPiece(), new int[] { r.getFromRow(), r.getFromCol() }, board, color, true);
			if (bishop.canProceed()) {
				fromCol = bishop.getFromCol();
				toCol = bishop.getToCol();
				fromRow = bishop.getFromRow();
				toRow = bishop.getToRow();

				if (toCol != 10) {
					return Util.move(msj, fromRow, fromCol, toRow, toCol);
				}
			}
		}
		return null;
	}

}
