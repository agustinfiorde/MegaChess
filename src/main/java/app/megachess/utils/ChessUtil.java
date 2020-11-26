package app.megachess.utils;

import java.util.ArrayList;
import java.util.List;

import app.megachess.models.Response;

/**
 * Utilidades especificas del MegaChess
 * 
 * @author AsteriX
 *
 */
public class ChessUtil {

	/**
	 * isWhite, evalua si determinada pieza es blanca
	 * 
	 * @param character
	 * @return
	 */
	public static boolean isWhite(String character) {
		return Character.isUpperCase(character.charAt(0));
	}

	/**
	 * isBlack, evalua si determinada pieza es negra
	 * 
	 * @param character
	 * @return
	 */
	public static boolean isBlack(String character) {
		return Character.isLowerCase(character.charAt(0));
	}

	/**
	 * rowIsClear, evalua si una fila especificada por argumento, esta libre de
	 * enemigos
	 * 
	 * @param board
	 * @param row
	 * @param color
	 * @return
	 */
	public static boolean rowIsClear(String[][] board, int row, String color) {
		for (int i = 0; i < 16; i++) {
			if (isMyEnemy(board[row][i], color)) {
				return false;
			}
		}
		return true;
	}

	/**
	 * pawnsActives, genera una lista con todos los peones vivos de mi equipo.
	 * 
	 * Posee logica diferenciada, ya que en los negros empieza desde abajo hacia
	 * arriba del tablero y en las blancas empieza desde arriba para abajo
	 * 
	 * @param board
	 * @param color
	 * @return
	 */
	public static List<Response> pawnsActives(String[][] board, String color) {
		List<Response> responses = new ArrayList<>();
		Response res;
		int startRow;
		int endRow;
		String piece;
		if (color.equals("white")) {
			startRow = 9;
			endRow = 13;
			piece = "P";
			for (int i = startRow; i <= endRow; i++) {
				for (int j = 15; j >= 0; j--) {
					if (board[i][j].equals(piece)) {
						res = new Response();
						res.setExist(true);
						res.setFromRow(i);
						res.setFromCol(j);
						res.setPiece(piece);
						responses.add(res);
					}
				}
			}
		} else {
			startRow = 6;
			endRow = 2;
			piece = "p";
			for (int i = startRow; i >= endRow; i--) {
				for (int j = 0; j < 16; j++) {
					if (board[i][j].equals(piece)) {
						res = new Response();
						res.setExist(true);
						res.setFromRow(i);
						res.setFromCol(j);
						res.setPiece(piece);
						responses.add(res);
					}
				}
			}
		}
		return responses;
	}

	/**
	 * whitePawnFirstMove, evalua el movimiento del primero peon del equipo blanco.
	 * 
	 * @param board
	 * @return
	 */
	public static Response whitePawnFirstMove(String[][] board) {
		Response res = new Response();
		for (int i = 0; i < 16; i++) {
			if (board[11][i].equals("P")) {
				res.setExist(true);
				res.setFromRow(11);
				res.setFromCol(i);
				res.setPiece("P");
				return res;
			}
		}
		res.setExist(false);
		return res;
	}

	/**
	 * getPiecesByColor, busca todas las piezas segun su color y la pieza puntual
	 * que quiero buscar
	 * 
	 * @param board
	 * @param piece
	 * @param color
	 * @return
	 */
	public static List<Response> getPiecesByColor(String[][] board, String piece, String color) {
		List<Response> responses = new ArrayList<>();
		Response res;
		piece = color.equals("white") ? piece.toUpperCase() : piece.toLowerCase();
		for (int i = 0; i < 16; i++) {
			for (int j = 0; j < 16; j++) {
				if (board[i][j].equals(piece)) {
					res = new Response();
					res.setExist(true);
					res.setFromRow(i);
					res.setFromCol(j);
					responses.add(res);
				}
			}
		}
		return responses;
	}

	/**
	 * topPossitionAssassin, empieza buscando desde arriba hacia abajo el asesino de
	 * mi equipo,
	 * 
	 * ESTA FUNCION ES RECOMENDADA PARA EL JUGADOR BLANCO
	 * 
	 * @param board
	 * @param piece
	 * @return
	 */
	public static Response topPossitionAssassin(String[][] board, String piece) {
		Response res = new Response();
		for (int i = 0; i < 16; i++) {
			for (int j = 0; j < 16; j++) {
				if (board[i][j].equals(piece)) {
					res.setExist(true);
					res.setFromRow(i);
					res.setFromCol(j);
					return res;
				}
			}
		}
		res.setExist(false);
		return res;
	}

	/**
	 * botPossitionAssassin, empieza buscando desde abajo hacia arriba el asesino de
	 * mi equipo,
	 * 
	 * ESTA FUNCION ES RECOMENDADA PARA EL JUGADOR NEGRO
	 * 
	 * @param board
	 * @param piece
	 * @return
	 */
	public static Response botPossitionAssassin(String[][] board, String piece) {
		Response res = new Response();
		for (int i = 15; i >= 0; i--) {
			for (int j = 0; j < 16; j++) {
				if (board[i][j].equals(piece)) {
					res.setExist(true);
					res.setFromRow(i);
					res.setFromCol(j);
					return res;
				}
			}
		}
		res.setExist(false);
		return res;
	}

	/**
	 * getDefenderPiecesBot, busca las piezas asignadas como potenciales defensores.
	 * 
	 * FUNCION RECOMENDADA PARA EL BLANCO
	 * 
	 * @param board
	 * @param piece
	 * @return
	 */
	public static List<Response> getDefenderPiecesBot(String[][] board, String piece) {
		List<Response> responses = new ArrayList<>();
		Response res;
		for (int i = 8; i < 16; i++) {
			for (int j = 0; j < 16; j++) {
				if (board[i][j].equals(piece)) {
					res = new Response();
					res.setExist(true);
					res.setFromRow(i);
					res.setFromCol(j);
					responses.add(res);
				}
			}
		}
		return responses;
	}

	/**
	 * getDefenderPiecesTop, busca las piezas asignadas como potenciales defensores.
	 * 
	 * FUNCION RECOMENDADA PARA EL NEGRO
	 * 
	 * @param board
	 * @param piece
	 * @return
	 */
	public static List<Response> getDefenderPiecesTop(String[][] board, String piece) {
		List<Response> responses = new ArrayList<>();
		Response res;
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 16; j++) {
				if (board[i][j].equals(piece)) {
					res = new Response();
					res.setExist(true);
					res.setFromRow(i);
					res.setFromCol(j);
					responses.add(res);
				}
			}
		}
		return responses;
	}

	/**
	 * isMyTeam, evalua si una pieza en base a la pieza y mi color, si es de mi
	 * equipo
	 * 
	 * @param piece
	 * @param myColor
	 * @return
	 */
	public static boolean isMyTeam(String piece, String myColor) {
		if (myColor.equals("white")) {
			return isWhite(piece);
		} else {
			return isBlack(piece);
		}
	}

	/**
	 * isMyEnemy, evalua si una pieza en base a la pieza y mi color, si es mi
	 * enemigo
	 * 
	 * @param piece
	 * @param myColor
	 * @return
	 */
	public static boolean isMyEnemy(String piece, String myColor) {
		if (myColor.equals("white")) {
			return isBlack(piece);
		} else {
			return isWhite(piece);
		}
	}

	/**
	 * getBoard, arma en base a un String una matriz del tablero
	 * 
	 * @param arg
	 * @return
	 */
	public static String[][] getBoard(String arg) {
		String board[][] = new String[16][16];
		int n = 0;
		for (int i = 0; i < 16; i++) {
			for (int j = 0; j < 16; j++) {
				board[i][j] = arg.substring(n++, n);
			}
		}
		return board;
	}

	/**
	 * showBoard, Sirve para visualizar en una terminal el tablero en forma de
	 * matriz
	 * 
	 * @param rowBoard
	 */
	public static void showBoard(String rowBoard) {
		String board[][] = getBoard(rowBoard);
		for (int i = 0; i < 16; i++) {
			for (int j = 0; j < 16; j++) {
				System.out.print(board[i][j]);
			}
			System.out.println("");
		}
		System.out.println("                                    ");
		System.out.println("++++++++++++++++++++++++++++++++++++");
		System.out.println("                                    ");
	}
}
