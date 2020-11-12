package app.megachess.chess;

/**
 * 
 * @author AsteriX
 *
 */
public class MainChess {

	/**
	 * 
	 * @param move_left
	 * @return value if the game is over or not
	 */
	public boolean isOver(Integer move_left) {
		return move_left == 0 ? true : false;
	}
	
	

}
