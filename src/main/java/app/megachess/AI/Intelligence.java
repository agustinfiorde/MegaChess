package app.megachess.AI;

import java.util.List;
import java.util.Random;

import app.megachess.AI.pieces.PawnAI;
import app.megachess.AI.pieces.QueenAI;
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

		Response res;

		if (msjData.getMove_left() == startMoves) {
			return evaluateStart(msj, msjData, board);
		} else {

			// ver si los peones pueden comer
			List<Response> responses = ChessUtil.pawnsActives(board, color);
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

//			// ver si hay alguien en tercera linea
//			if (ChessUtil.isEnemyInThirdLine(board, color)) {
//				res = ??;
//			}

//			// ver si hay alguien en cuarta linea
//			if (ChessUtil.isEnemyInBotLine(board, color)) {
//				return "";
//			}

			// hay reina presente??
			QueenAI queen;
			if (color.equals("white")) {
				res = ChessUtil.topPossitionAssassin(board);
				if (res.isExist()) {
					queen = new QueenAI(res.getPiece(), new int[] { res.getFromRow(), res.getFromCol() }, board, color);
					fromCol = queen.getFromCol();
					toCol = queen.getToCol();
					fromRow = queen.getFromRow();
					toRow = queen.getToRow();
					return Util.move(msj, fromRow, fromCol, toRow, toCol);
				}
			} else {
				res = ChessUtil.botPossitionAssassin(board);
				if (res.isExist()) {
					queen = new QueenAI(res.getPiece(), new int[] { res.getFromRow(), res.getFromCol() }, board, color);
					fromCol = queen.getFromCol();
					toCol = queen.getToCol();
					fromRow = queen.getFromRow();
					toRow = queen.getToRow();
					return Util.move(msj, fromRow, fromCol, toRow, toCol);
				}
			}

			// mover peones
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

			// sacar reinas de reserva

			// sacar torres de reserva

			// sacar alfiles

			// mover reyes al frente

		}
		return "";
	}

	public static String evaluateStart(Message msj, DataMessage msjData, String[][] board) {

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
					return Util.move(msj, fromRow, fromCol, toRow, toCol);
				} else if (col >= 11 && col <= 14) {
					fromCol = 2;
					toCol = fromCol;
					fromRow = 3;
					toRow = 5;
					return Util.move(msj, fromRow, fromCol, toRow, toCol);
				}
			} else {
				// en caso que no haya avanzado encara por cualquier lado, similar como lo haria
				// en el turno blanco
				fromCol = new Random().nextBoolean() ? 2 : 13;
				toCol = fromCol;
				fromRow = 12;
				toRow = 10;
				return Util.move(msj, fromRow, fromCol, toRow, toCol);
			}
		}
		return "";
	}

}
