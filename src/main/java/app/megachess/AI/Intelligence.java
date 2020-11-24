package app.megachess.AI;

import java.util.List;
import java.util.Random;

import app.megachess.AI.pieces.BishopAI;
import app.megachess.AI.pieces.HorseAI;
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

	public static final int startMoves = 199;

	private static List<Response> responses;

	private static int fromRow = 0;
	private static int fromCol = 0;
	private static int toRow = 0;
	private static int toCol = 0;

	public static String evaluate(Message msj) {

		DataMessage msjData = msj.getData();
		String[][] board = ChessUtil.getBoard(msjData.getBoard());
		String color = msjData.getActual_turn();
		String answer = null;

		if (msjData.getMove_left() == startMoves) {
			return evaluateFirstMove(msj, msjData, board);
		}

		// defensa de peones
		answer = pawnDefense(msj, board, color);
		if (answer != null) {
			return answer;
		}

		// defensa de torres
		answer = rookDefense(msj, board, color);
		if (answer != null) {
			return answer;
		}

		// defensa de caballos
		answer = horseDefense(msj, board, color);
		if (answer != null) {
			return answer;
		}

		// defensa de alfiles
		answer = bishopDefense(msj, board, color);
		if (answer != null) {
			return answer;
		}

		// defensa con reinas
		answer = queenDefense(msj, board, color);
		if (answer != null) {
			return answer;
		}

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

		// torre asesinas
		answer = rookAction(msj, board, color);
		if (answer != null) {
			return answer;
		}

		// mover peones
		answer = pawnAction(msj, board, color);
		if (answer != null) {
			return answer;
		}

		// mover reyes
		answer = kingProceed(msj, board, color);
		if (answer != null) {
			return answer;
		}

		return "";
	}

	public static String queenDefense(Message msj, String[][] board, String color) {

		if (color.equals("white")) {
			responses = ChessUtil.getDefenderPiecesBot(board, "Q", color);
		} else {
			responses = ChessUtil.getDefenderPiecesBot(board, "q", color);
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

	public static String horseDefense(Message msj, String[][] board, String color) {

		responses = ChessUtil.getPiecesByColor(board, "k", color);

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

	public static String rookDefense(Message msj, String[][] board, String color) {

		if (color.equals("white")) {
			responses = ChessUtil.getDefenderPiecesBot(board, "R", color);
		} else {
			responses = ChessUtil.getDefenderPiecesBot(board, "r", color);
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

	public static String kingDefense(Message msj, String[][] board, String color) {
		responses = ChessUtil.getPiecesByColor(board, "k", color);
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

	public static String bishopDefense(Message msj, String[][] board, String color) {
		responses = ChessUtil.getPiecesByColor(board, "b", color);
		BishopAI bishop;
		for (Response r : responses) {
			bishop = new BishopAI(r.getPiece(), new int[] { r.getFromRow(), r.getFromCol() }, board, color);
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
				fromCol = new Random().nextBoolean() ? 2 : 13;
				toCol = fromCol;
				fromRow = 12;
				toRow = 10;
			}
			return Util.move(msj, fromRow, fromCol, toRow, toCol);
		}
	}
}
