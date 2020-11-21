package app.megachess.AI;

import java.util.List;
import java.util.Random;

import app.megachess.AI.pieces.KingAI;
import app.megachess.AI.pieces.PawnAI;
import app.megachess.AI.pieces.QueenAI;
import app.megachess.AI.pieces.RookAI;
import app.megachess.utils.ChessUtil;
import app.megachess.utils.Util;
import app.megachess.websocket.models.DataMessage;
import app.megachess.websocket.models.Message;
import app.megachess.websocket.models.Response;

public class Intelligence {

	public static int startMoves = 199;

	private static int fromRow = 0;
	private static int fromCol = 0;
	private static int toRow = 0;
	private static int toCol = 0;

	public static String evaluate(Message msj) {

		DataMessage msjData = msj.getData();
		String[][] board = ChessUtil.getBoard(msjData.getBoard());
		String color = msjData.getActual_turn();
		String answer;

		if (msjData.getMove_left() == startMoves) {
			return evaluateFirstMove(msj, msjData, board);
		}

		List<Response> responses = ChessUtil.pawnsActives(board, color);

		// defensa de peones
		answer = pawnDefense(msj, responses, board, color);
		if (answer != null) {
			return answer;
		}

		// defensa de torres TODO
		answer = rookDefense(msj, board, color);
		if (answer != null) {
			return answer;
		}

		// defensa de caballos TODO

		// defensa de alfiles TODO

		// defensa con reinas TODO

		// defensa con reyes
		answer = kingDefense(msj, board, color);
		if (answer != null) {
			return answer;
		}

		// reina asesina
		answer = queenAction(msj, board, color);
		if (answer != null) {
			return answer;
		}

		// mover peones
		answer = pawnAction(msj, responses, board, color);
		if (answer != null) {
			return answer;
		}

		// sacar reinas de reserva TODO

		// sacar torres de reserva TODO

		// sacar alfiles TODO

		// mover reyes al frente TODO

		return "";
	}

	// TODO terminar
	public static String rookDefense(Message msj, String[][] board, String color) {
		List<Response> responses = ChessUtil.getPiecesByColor(board, "r", color);
		RookAI rook;
		for (Response r : responses) {
			rook = new RookAI(r.getPiece(), new int[] { r.getFromRow(), r.getFromCol() }, board, color);
			if (rook.canEat()) {
				fromCol = rook.getFromCol();
				toCol = rook.getToCol();
				fromRow = rook.getFromRow();
				toRow = rook.getToRow();
				return Util.move(msj, fromRow, fromCol, toRow, toCol);
			}
		}
		return null;
	}

	public static String kingDefense(Message msj, String[][] board, String color) {
		List<Response> responses = ChessUtil.getPiecesByColor(board, "k", color);
		KingAI rook;
		for (Response r : responses) {
			rook = new KingAI(r.getPiece(), new int[] { r.getFromRow(), r.getFromCol() }, board, color);
			if (rook.canEat()) {
				fromCol = rook.getFromCol();
				toCol = rook.getToCol();
				fromRow = rook.getFromRow();
				toRow = rook.getToRow();
				return Util.move(msj, fromRow, fromCol, toRow, toCol);
			}
		}
		return null;
	}

	public static String pawnDefense(Message msj, List<Response> responses, String[][] board, String color) {

		PawnAI pawn;
		for (Response r : responses) {
			pawn = new PawnAI(r.getPiece(), new int[] { r.getFromRow(), r.getFromCol() }, board, color);
			if (pawn.canEat()) {
				fromCol = pawn.getFromCol();
				toCol = pawn.getToCol();
				fromRow = pawn.getFromRow();
				toRow = pawn.getToRow();
				return Util.move(msj, fromRow, fromCol, toRow, toCol);
			}
		}
		return null;
	}

	public static String queenAction(Message msj, String[][] board, String color) {
		QueenAI queen;
		Response res;
		res = null;
		if (color.equals("white")) {
			res = ChessUtil.topPossitionAssassin(board);
		} else {
			res = ChessUtil.botPossitionAssassin(board);
		}

		// TODO validar si puede hacer el movimiento
		if (res.isExist()) {
			queen = new QueenAI(res.getPiece(), new int[] { res.getFromRow(), res.getFromCol() }, board, color, true);
			fromCol = queen.getFromCol();
			// TODO aca llega nullo negra 14 2 deberia continuar para abajo pero se queda
			// sin hacer nada
			System.out.println("position " + res.getFromRow() + "," + res.getFromCol() + " Color" + color);
			toCol = queen.getToCol();
			fromRow = queen.getFromRow();
			toRow = queen.getToRow();
			return Util.move(msj, fromRow, fromCol, toRow, toCol);
		}

		return null;
	}

	public static String pawnAction(Message msj, List<Response> responses, String[][] board, String color) {

		PawnAI pawn;
		for (Response r : responses) {
			pawn = new PawnAI(r.getPiece(), new int[] { r.getFromRow(), r.getFromCol() }, board, color);
			if (pawn.canMove()) {
				fromCol = pawn.getFromCol();
				toCol = pawn.getToCol();
				fromRow = pawn.getFromRow();
				toRow = pawn.getToRow();
				return Util.move(msj, fromRow, fromCol, toRow, toCol);
			}
		}
		return null;
	}

	public static String evaluateFirstMove(Message msj, DataMessage msjData, String[][] board) {
		if (msjData.getActual_turn().equals("white")) {
			fromCol = new Random().nextBoolean() ? 2 : 13;
			toCol = fromCol;
			fromRow = 12;
			toRow = 10;
			return Util.move(msj, fromRow, fromCol, toRow, toCol);
		} else {
			Response res = ChessUtil.whitePawnFirstMove(board);
			if (res.isExist()) {
				// como negro evalua para encarar a los caballos por el lado opuesto
				int col = res.getFromCol();
				if (col >= 1 && col <= 4) {
					fromCol = 13;
					toCol = fromCol;
					fromRow = 3;
					toRow = 5;
				} else if (col >= 11 && col <= 14) {
					fromCol = 2;
					toCol = fromCol;
					fromRow = 3;
					toRow = 5;
				}
			} else {
				// en caso que no haya avanzado encara por cualquier lado, similar como lo haria
				// en el turno blanco
				fromCol = new Random().nextBoolean() ? 2 : 13;
				toCol = fromCol;
				fromRow = 12;
				toRow = 10;
			}
			return Util.move(msj, fromRow, fromCol, toRow, toCol);
		}
	}
}
