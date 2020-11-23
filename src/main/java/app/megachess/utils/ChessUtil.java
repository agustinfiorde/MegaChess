package app.megachess.utils;

import java.util.ArrayList;
import java.util.List;

import app.megachess.websocket.models.Response;

public class ChessUtil {

	public static boolean isWhite(String character) {
		return Character.isUpperCase(character.charAt(0));
	}

	public static boolean isBlack(String character) {
		return Character.isLowerCase(character.charAt(0));
	}

	public static boolean rowIsClear(String[][] board, int row, String color) {
		for (int i = 0; i < 16; i++) {
			if (isMyEnemy(board[row][i], color)) {
				return false;
			}
		}
		return true;
	}

	public static String getPieceByPosition(String[][] board, int row, int col) {

		if (!board[row][col].equals(" ")) {
			return board[row][col];
		}
		return " ";
	}

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

	public static Response somePawnIsActive(String[][] board, String color) {

		Response res = new Response();
		// black
		int startRow = 6;
		int endRow = 2;
		String piece = "p";
		// white
		if (color.equals("white")) {
			startRow = 9;
			endRow = 13;
			piece = "P";
			for (int i = startRow; i <= endRow; i++) {
				for (int j = 0; j < 16; j++) {
					if (board[i][j].equals(piece) && board[i - 1][j].equals(" ")) {
						res.setExist(true);
						res.setFromRow(i);
						res.setFromCol(j);
						res.setPiece(piece);
						return res;
					}
				}
			}
		} else {
			for (int i = startRow; i >= endRow; i--) {
				for (int j = 0; j < 16; j++) {
					if (board[i][j].equals(piece) && board[i + 1][j].equals(" ")) {
						res.setExist(true);
						res.setFromRow(i);
						res.setFromCol(j);
						res.setPiece(piece);
						return res;
					}
				}
			}
		}
		res.setExist(false);
		return res;
	}

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

	public static List<Response> getDefenderPiecesBot(String[][] board, String piece, String color) {
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

	public static List<Response> getDefenderPiecesTop(String[][] board, String piece, String color) {
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

	public static boolean isEnemyInTheCenter(String[][] board, String color) {
		int row = color.equals("white") ? 8 : 7;
		if (color.equals("white")) {
			for (int j = 0; j < 16; j++) {
				if (isBlack(board[row][j])) {
					return true;
				}
			}
		} else {
			for (int j = 0; j < 16; j++) {
				if (isWhite(board[row][j])) {
					return true;
				}
			}
		}
		return false;
	}

	public static boolean isEnemyInFosa(String[][] board, String color) {

		int row = (color.toLowerCase()).equals("white") ? 11 : 4;

		if ((color.toLowerCase()).equals("white")) {
			for (int j = 0; j < 16; j++) {
				if (isBlack(board[row][j])) {
					return true;
				}
			}
		} else {
			for (int j = 0; j < 16; j++) {
				if (isWhite(board[row][j])) {
					return true;
				}
			}
		}
		return false;
	}

	public static boolean isEnemyInFrontLine(String[][] board, String color) {

		int row = (color.toLowerCase()).equals("white") ? 12 : 3;

		if ((color.toLowerCase()).equals("white")) {
			for (int j = 0; j < 16; j++) {

				if (isBlack(board[row][j])) {
					return true;
				}
			}
		} else {
			for (int j = 0; j < 16; j++) {

				if (isWhite(board[row][j])) {
					return true;
				}
			}
		}
		return false;
	}

	public static boolean isEnemyInSecondLine(String[][] board, String color) {

		int row = (color.toLowerCase()).equals("white") ? 13 : 2;

		if ((color.toLowerCase()).equals("white")) {
			for (int j = 0; j < 16; j++) {
				if (isBlack(board[row][j])) {
					return true;
				}
			}
		} else {
			for (int j = 0; j < 16; j++) {
				if (isWhite(board[row][j])) {
					return true;
				}
			}
		}
		return false;
	}

	public static boolean isEnemyInThirdLine(String[][] board, String color) {

		int row = (color.toLowerCase()).equals("white") ? 14 : 1;

		if ((color.toLowerCase()).equals("white")) {
			for (int j = 0; j < 16; j++) {
				if (isBlack(board[row][j])) {
					return true;
				}
			}
		} else {
			for (int j = 0; j < 16; j++) {
				if (isWhite(board[row][j])) {
					return true;
				}
			}
		}
		return false;
	}

	public static boolean isEnemyInBotLine(String[][] board, String color) {

		int row = (color.toLowerCase()).equals("white") ? 15 : 0;

		if ((color.toLowerCase()).equals("white")) {
			for (int j = 0; j < 16; j++) {
				if (isBlack(board[row][j])) {
					return true;
				}
			}
		} else {
			for (int j = 0; j < 16; j++) {
				if (isWhite(board[row][j])) {
					return true;
				}
			}
		}
		return false;
	}

	public static boolean isEmptyByPosition(String[][] board, int row, int col) {
		return (board[row][col].equals(" ") || board[row][col].isEmpty()) ? true : false;
	}

	public static boolean isMyTeam(String piece, String myColor) {
		if (myColor.equals("white")) {
			return isWhite(piece);
		} else {
			return isBlack(piece);
		}
	}

	public static boolean isMyEnemy(String piece, String myColor) {
		if (myColor.equals("white")) {
			return isBlack(piece);
		} else {
			return isWhite(piece);
		}
	}

	public static boolean isPawnEnemy(String piece, String myColor) {
		String enemyParameter = myColor.equals("white") ? "p" : "P";
		return enemyParameter.equals(piece) ? true : false;
	}

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

	public static void showBoard(String rowBoard) {
		String board[][] = getBoard(rowBoard);
		for (int i = 0; i < 16; i++) {
			for (int j = 0; j < 16; j++) {
				System.out.print(board[i][j]);
			}
			System.out.println("");
		}
		System.out.println("                                    ");
		System.out.println("                                    ");
		System.out.println("                                    ");
	}
}
