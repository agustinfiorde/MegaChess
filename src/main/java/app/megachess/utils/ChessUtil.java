package app.megachess.utils;

import java.util.ArrayList;
import java.util.List;

public class ChessUtil {

	public static boolean isWhite(String character) {
		return Character.isUpperCase(character.charAt(0));
	}

	public static boolean isBlack(String character) {
		return Character.isLowerCase(character.charAt(0));
	}

	public static List<Object> evaluateFirstMove(String[][] board) {

		List<Object> values = new ArrayList<>();

		for (int i = 11; i > 3; i--) {
			for (int j = 0; j < 16; j++) {
				if (board[i][j].equals("P")) {
					values.add(true);
					values.add(new int[] { i, j });
					return values;
				}
			}
		}
		values.add(false);
		return values;
	}

	public static List<Object> getPieceByPosition(String[][] board, int[] position) {
		List<Object> values = new ArrayList<>();

		if (!board[position[0]][position[1]].equals(" ")) {
			values.add(true);
			values.add(position);
			values.add(board[position[0]][position[1]]);
			return values;
		} else {
			values.add(false);
			return values;
		}

	}

	public static List<Object> somePawnIsAlive(String[][] board, String color) {
		List<Object> values = new ArrayList<>();

		if ((color.toLowerCase()).equals("white")) {
			for (int i = 8; i < 14; i++) {
				for (int j = 0; j < 16; j++) {
					if (board[i][j].equals("P")) {
						values.add(true);
						values.add(new int[] { i, j });
						values.add(board[i][j]);
						return values;
					}
				}
			}
		} else {
			for (int i = 7; i > 1; i--) {
				for (int j = 0; j < 16; j++) {
					if (board[i][j].equals("p")) {
						values.add(true);
						values.add(new int[] { i, j });
						values.add(board[i][j]);
						return values;
					}
				}
			}
		}
		values.add(false);
		return values;
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

	public static List<Object> topPossitionAssassin(String[][] board) {
		List<Object> values = new ArrayList<>();

		for (int i = 0; i < 16 - 2; i++) {
			for (int j = 0; j < 16; j++) {

				if (isWhite(board[i][j]) && board[i][j].equals("Q")) {
					values.add(true);
					values.add(new int[] { i, j });
					return values;
				}
			}
		}
		values.add(false);
		return values;
	}

	public static List<Object> botPossitionAssassin(String[][] board) {
		List<Object> values = new ArrayList<>();

		for (int i = 15; i >= 0 + 2; i--) {
			for (int j = 15; j >= 0; j--) {

				if (isBlack(board[i][j]) && board[i][j].equals("q")) {
					values.add(true);
					values.add(new int[] { i, j });
					return values;
				}
			}
		}
		values.add(false);
		return values;
	}

	public static List<Object> centerOfMap(String[][] board, String color) {
		List<Object> values = new ArrayList<>();
		int row = (color.toLowerCase()).equals("white") ? 8 : 7;

		if ((color.toLowerCase()).equals("white")) {
			for (int j = 0; j < 16; j++) {
				if (isBlack(board[row][j])) {
					values.add(true);
					values.add(new int[] { row, j });
					values.add(board[row][j]);
					return values;
				}
			}
		} else {
			for (int j = 0; j < 16; j++) {
				if (isWhite(board[row][j])) {
					values.add(true);
					values.add(new int[] { row, j });
					values.add(board[row][j]);
					return values;
				}
			}
		}
		values.add(false);
		return values;
	}

	public static List<Object> fosaDefense(String[][] board, String color) {
		List<Object> values = new ArrayList<>();
		int row = (color.toLowerCase()).equals("white") ? 11 : 4;

		if ((color.toLowerCase()).equals("white")) {
			for (int j = 0; j < 16; j++) {
				if (isBlack(board[row][j])) {
					values.add(true);
					values.add(new int[] { row, j });
					values.add(board[row][j]);
					return values;
				}
			}
		} else {
			for (int j = 0; j < 16; j++) {
				if (isWhite(board[row][j])) {
					values.add(true);
					values.add(new int[] { row, j });
					values.add(board[row][j]);
					return values;
				}
			}
		}
		values.add(false);
		return values;
	}

	public static List<Object> frontLineDefense(String[][] board, String color) {
		List<Object> values = new ArrayList<>();
		int row = (color.toLowerCase()).equals("white") ? 12 : 3;

		if ((color.toLowerCase()).equals("white")) {
			for (int j = 0; j < 16; j++) {
				if (isBlack(board[row][j])) {
					values.add(true);
					values.add(new int[] { row, j });
					values.add(board[row][j]);
					return values;
				}
			}
		} else {
			for (int j = 0; j < 16; j++) {
				if (isWhite(board[row][j])) {
					values.add(true);
					values.add(new int[] { row, j });
					values.add(board[row][j]);
					return values;
				}
			}
		}
		values.add(false);
		return values;
	}

	public static List<Object> secondLineDefense(String[][] board, String color) {
		List<Object> values = new ArrayList<>();
		int row = (color.toLowerCase()).equals("white") ? 13 : 2;

		if ((color.toLowerCase()).equals("white")) {
			for (int j = 0; j < 16; j++) {
				if (isBlack(board[row][j])) {
					values.add(true);
					values.add(new int[] { row, j });
					values.add(board[row][j]);
					return values;
				}
			}
		} else {
			for (int j = 0; j < 16; j++) {
				if (isWhite(board[row][j])) {
					values.add(true);
					values.add(new int[] { row, j });
					values.add(board[row][j]);
					return values;
				}
			}
		}
		values.add(false);
		return values;
	}

	public static List<Object> thirdLineDefense(String[][] board, String color) {
		List<Object> values = new ArrayList<>();
		int row = (color.toLowerCase()).equals("white") ? 14 : 1;

		if ((color.toLowerCase()).equals("white")) {
			for (int j = 0; j < 16; j++) {
				if (isBlack(board[row][j])) {
					values.add(true);
					values.add(new int[] { row, j });
					values.add(board[row][j]);
					return values;
				}
			}
		} else {
			for (int j = 0; j < 16; j++) {
				if (isWhite(board[row][j])) {
					values.add(true);
					values.add(new int[] { row, j });
					values.add(board[row][j]);
					return values;
				}
			}
		}
		values.add(false);
		return values;
	}

	public static List<Object> bottomLineDefense(String[][] board, String color) {
		List<Object> values = new ArrayList<>();
		int row = (color.toLowerCase()).equals("white") ? 15 : 0;

		if ((color.toLowerCase()).equals("white")) {
			for (int j = 0; j < 16; j++) {
				if (isBlack(board[row][j])) {
					values.add(true);
					values.add(new int[] { row, j });
					values.add(board[row][j]);
					return values;
				}
			}
		} else {
			for (int j = 0; j < 16; j++) {
				if (isWhite(board[row][j])) {
					values.add(true);
					values.add(new int[] { row, j });
					values.add(board[row][j]);
					return values;
				}
			}
		}
		values.add(false);
		return values;
	}

	public static void showBoard(String rowBoard) {
		
		String board[][]=getBoard(rowBoard);
		
		for (int i = 0; i < 16; i++) {
			for (int j = 0; j < 16; j++) {
				System.out.print(board[i][j]);
			}
			System.out.println("");
		}
	}

}
