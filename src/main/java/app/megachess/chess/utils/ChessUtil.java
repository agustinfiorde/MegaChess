package app.megachess.chess.utils;

public class ChessUtil {

	/**
	 * 
	 * @param move_left
	 * @return value if the game is over or not
	 */
	public static boolean isOver(Integer move_left) {
		return move_left == 0 ? true : false;
	}
	
}
