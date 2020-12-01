package app.megachess.AI.pieces;

import app.megachess.enums.PieceDirection;
import app.megachess.models.Response;
import app.megachess.utils.ChessUtil;
import lombok.Data;

@Data
public abstract class Piece implements PieceAction {

	protected String color;

	protected Integer fromRow;
	protected Integer fromCol;

	protected Integer toRow;
	protected Integer toCol;

	protected Integer front;
	protected Integer back;
	protected Integer left;
	protected Integer right;

	protected String[][] board;

	public Piece(int[] position, String[][] board, String color) {

		this.board = board;
		this.color = color;
		setPosition(position);
		this.right = this.fromCol + 1;
		this.left = this.fromCol - 1;
		if (color.equals("white")) {
			this.front = this.fromRow - 1;
			this.back = this.fromRow + 1;
		} else {
			this.front = this.fromRow + 1;
			this.back = this.fromRow - 1;
		}
	}

	/**
	 * setPosition necesita el arreglo con la posicion, [0] es la fila y [1] es la
	 * columna, lo recorre y setea el fromTo y fromCol
	 * 
	 * @param position
	 */
	protected void setPosition(int[] position) {
		this.fromRow = position[0];
		this.fromCol = position[1];
	}

	/**
	 * setTo necesita el toRow y toCol. Setea ambos de forma simultanea
	 * 
	 * @param toRow
	 * @param toCol
	 */
	protected void setTo(int toRow, int toCol) {
		this.toRow = toRow;
		this.toCol = toCol;
	}

	/**
	 * toLeft, es un funcion que recorre la fila donde se encuentre la pieza, en
	 * busca de un enemigo. Si encuentra enemigo devuelve, true. Es util para saber
	 * si termino de recorrer un lado y debe irse por el otro
	 * 
	 * @return
	 */
	protected boolean toLeft() {
		for (int i = fromCol; i >= 0; i--) {
			if (ChessUtil.isMyEnemy(board[fromRow][i], color)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * toRight, es un funcion que recorre la fila donde se encuentre la pieza, en
	 * busca de un enemigo. Si encuentra enemigo devuelve, true. Es util para saber
	 * si termino de recorrer un lado y debe irse por el otro
	 * 
	 * @return
	 */
	protected boolean toRight() {
		for (int i = fromCol; i < 16; i++) {
			if (ChessUtil.isMyEnemy(board[fromRow][i], color)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * evaluateTrajectory es un administrador para ver que sub metodo necesita
	 * llamar segun el parametro
	 * 
	 * @param target
	 */
	protected boolean evaluateTrajectory(PieceDirection target) {
		switch (target.toString()) {
		case "TO_TOP":
			return evaluateTrajectoryToTop();
		case "TO_BOT":
			return evaluateTrajectoryToBot();
		case "LEFT":
			return evaluateTrajectoryToLeft();
		case "RIGHT":
			return evaluateTrajectoryToRight();
		case "TO_TOP_LEFT":
			return evaluateTrajectoryToTopLeft();
		case "TO_TOP_RIGHT":
			return evaluateTrajectoryToTopRight();
		case "TO_BOT_LEFT":
			return evaluateTrajectoryToBotLeft();
		case "TO_BOT_RIGHT":
			return evaluateTrajectoryToBotRight();
		default:
			return false;
		}
	}

	/**
	 * Evalua la trayectoria hacia la parte superior del tablero. Si encuentra un
	 * enemigo o la libertad de movimiento, procede a setear la posicion previamente
	 * evaluada
	 * 
	 * @param target
	 */
	protected boolean evaluateTrajectoryToTop() {
		for (int i = (fromRow - 1); i >= 0; i--) {

			if (ChessUtil.isMyTeam(board[i][fromCol], color)) {
				break;
			}
			if (ChessUtil.isMyEnemy(board[i][fromCol], color)) {
				setTo(i, fromCol);
				break;
			}
			setTo(i, fromCol);
		}
		return toCol != null || toRow != null ? true : false;
	}

	/**
	 * Evalua la trayectoria hacia la parte inferior del tablero. Si encuentra un
	 * enemigo o la libertad de movimiento, procede a setear la posicion previamente
	 * evaluada
	 * 
	 * @param target
	 */
	protected boolean evaluateTrajectoryToBot() {
		for (int i = (fromRow + 1); i < 16; i++) {
			if (ChessUtil.isMyTeam(board[i][fromCol], color)) {
				break;
			}
			if (ChessUtil.isMyEnemy(board[i][fromCol], color)) {
				setTo(i, fromCol);
				break;
			}
			setTo(i, fromCol);
		}
		return toCol != null || toRow != null ? true : false;
	}

	/**
	 * Evalua la trayectoria hacia la parte izquierda del tablero. Si encuentra un
	 * enemigo o la libertad de movimiento, procede a setear la posicion previamente
	 * evaluada
	 * 
	 * @param target
	 */
	protected boolean evaluateTrajectoryToLeft() {
		for (int i = (fromCol - 1); i >= 0; i--) {
			if (ChessUtil.isMyTeam(board[fromRow][i], color)) {
				break;
			}
			if (ChessUtil.isMyEnemy(board[fromRow][i], color)) {
				setTo(fromRow, i);
				break;
			}
			setTo(fromRow, i);
		}
		return toCol != null || toRow != null ? true : false;
	}

	/**
	 * Evalua la trayectoria hacia la parte derecha del tablero. Si encuentra un
	 * enemigo o la libertad de movimiento, procede a setear la posicion previamente
	 * evaluada
	 * 
	 * @param target
	 */
	protected boolean evaluateTrajectoryToRight() {

		for (int i = (fromCol + 1); i < 16; i++) {
			if (ChessUtil.isMyTeam(board[fromRow][i], color)) {
				return false;
			}
			if (ChessUtil.isMyEnemy(board[fromRow][i], color)) {
				setTo(fromRow, i);
				return true;
			}
			setTo(fromRow, i);
		}
		return toCol != null || toRow != null ? true : false;
	}

	/**
	 * Evalua la trayectoria hacia la parte superior-izquierda del tablero. Si
	 * encuentra un enemigo o la libertad de movimiento, procede a setear la
	 * posicion previamente evaluada
	 * 
	 * @param target
	 */
	protected boolean evaluateTrajectoryToTopLeft() {

		int i = fromRow;
		for (int j = (fromCol - 1); j >= 0; j--) {

			i -= 1;
			if (i < 0) {
				break;
			}
			if (ChessUtil.isMyTeam(board[i][j], color)) {
				break;
			}
			if (ChessUtil.isMyEnemy(board[i][j], color)) {
				setTo(i, j);
				break;
			}
			setTo(i, j);
		}
		return toCol != null || toRow != null ? true : false;
	}

	/**
	 * Evalua la trayectoria hacia la parte superior-derecha del tablero. Si
	 * encuentra un enemigo o la libertad de movimiento, procede a setear la
	 * posicion previamente evaluada
	 * 
	 * @param target
	 */
	protected boolean evaluateTrajectoryToTopRight() {

		int i = fromRow;
		for (int j = fromCol + 1; j < 16; j++) {

			i -= 1;
			if (i < 0) {
				break;
			}
			if (ChessUtil.isMyTeam(board[i][j], color)) {
				break;
			}
			if (ChessUtil.isMyEnemy(board[i][j], color)) {
				setTo(i, j);
				break;
			}
			setTo(i, j);
		}
		return toCol != null && toRow != null ? true : false;
	}

	/**
	 * Evalua la trayectoria hacia la parte inferior-izquierda del tablero. Si
	 * encuentra un enemigo o la libertad de movimiento, procede a setear la
	 * posicion previamente evaluada
	 * 
	 * @param target
	 */
	protected boolean evaluateTrajectoryToBotLeft() {

		int i = fromRow;
		for (int j = fromCol - 1; j >= 0; j--) {

			i += 1;
			if (i > 15) {
				break;
			}
			if (ChessUtil.isMyTeam(board[i][j], color)) {
				break;
			}
			if (ChessUtil.isMyEnemy(board[i][j], color)) {
				setTo(i, j);
				break;
			}
			setTo(i, j);
		}

		return toCol != null && toRow != null ? true : false;
	}

	/**
	 * Evalua la trayectoria hacia la parte inferior-derecha del tablero. Si
	 * encuentra un enemigo o la libertad de movimiento, procede a setear la
	 * posicion previamente evaluada
	 * 
	 * @param target
	 */
	protected boolean evaluateTrajectoryToBotRight() {

		int i = fromRow;
		for (int j = fromCol + 1; j < 16; j++) {

			i += 1;
			if (i > 15) {
				return false;
			}
			if (ChessUtil.isMyTeam(board[i][j], color)) {
				return false;
			}
			if (ChessUtil.isMyEnemy(board[i][j], color)) {
				setTo(i, j);
				break;
			}
			setTo(i, j);
		}
		return toCol != null && toRow != null ? true : false;
	}

	/**
	 * evalua el cuadrante superior proximo a la pieza
	 * 
	 * @return
	 */
	protected boolean evaluateTop() {
		if (front > 15 || front < 0 || back > 15 || back < 0) {
			return false;
		} else {
			if (evaluateQuadrant(front, fromCol)) {
				setTo(front, fromCol);
				return true;
			} else {
				return false;
			}
		}
	}

	/**
	 * evalua el cuadrante inferior proximo a la pieza
	 * 
	 * @return
	 */
	protected boolean evaluateBot() {
		if (front > 15 || front < 0 || back > 15 || back < 0) {
			return false;
		} else {
			if (evaluateQuadrant(back, fromCol)) {
				setTo(back, fromCol);
				return true;
			} else {
				return false;
			}
		}
	}

	/**
	 * evalua el cuadrante izquierdo proximo a la pieza
	 * 
	 * @return
	 */
	protected boolean evaluateLeft() {
		if (left < 0) {
			return false;
		} else {
			if (evaluateQuadrant(fromRow, left)) {
				setTo(fromRow, left);
				return true;
			} else {
				return false;
			}
		}
	}

	/**
	 * evalua el cuadrante derecho proximo a la pieza
	 * 
	 * @return
	 */
	protected boolean evaluateRight() {
		if (right > 15) {
			return false;
		} else {
			if (evaluateQuadrant(fromRow, right)) {
				setTo(fromRow, right);
				return true;
			} else {
				return false;
			}
		}
	}

	/**
	 * evalua el cuadrante superior-izquierdo proximo a la pieza
	 * 
	 * @return
	 */
	protected boolean evaluateTopLeft() {
		if (front > 15 || front < 0 || back > 15 || back < 0 || left < 0) {
			return false;
		} else {
			if (evaluateQuadrant(front, left)) {
				setTo(front, left);
				return true;
			} else {
				return false;
			}
		}
	}

	/**
	 * evalua el cuadrante inferior-izquierdo proximo a la pieza
	 * 
	 * @return
	 */
	protected boolean evaluateBotLeft() {
		if (front > 15 || front < 0 || back > 15 || back < 0 || left < 0) {
			return false;
		} else {
			if (evaluateQuadrant(back, left)) {
				setTo(back, left);
				return true;
			} else {
				return false;
			}
		}
	}

	/**
	 * evalua el cuadrante superior-derecho proximo a la pieza
	 * 
	 * @return
	 */
	protected boolean evaluateTopRight() {
		if (front > 15 || front < 0 || back > 15 || back < 0 || right > 15) {
			return false;
		} else {
			if (evaluateQuadrant(front, right)) {
				setTo(front, right);
				return true;
			} else {
				return false;
			}
		}
	}

	/**
	 * evalua el cuadrante inferior-izquierdo proximo a la pieza
	 * 
	 * @return
	 */
	protected boolean evaluateBotRight() {
		if (front > 15 || front < 0 || back > 15 || back < 0 || right > 15) {
			return false;
		} else {
			if (evaluateQuadrant(back, right)) {
				setTo(back, right);
				return true;
			} else {
				return false;
			}
		}
	}

	/**
	 * evalua un cuadrante especifico en el tablero en busca de enemigo
	 * 
	 * @param row
	 * @param col
	 * @return
	 */
	protected boolean evaluateQuadrant(int row, int col) {
		return ChessUtil.isMyEnemy(board[row][col], color) ? true : false;
	}

	/**
	 * targetToHunt se encarga de buscar una pieza que no este a la vista de ningun
	 * defensor o atacante, con el objetivo de que alguna de mis piezas se aproxime
	 * para comerla
	 * 
	 * @return
	 */
	protected Response targetToHunt() {
		for (int i = 0; i < 16; i++) {
			for (int j = 0; j < 16; j++) {
				if (ChessUtil.isMyEnemy(board[i][j], color)) {
					Response res = new Response();
					res.setExist(true);
					res.setFromRow(i);
					res.setFromCol(j);
					return res;
				}
			}
		}
		return null;
	}
}
