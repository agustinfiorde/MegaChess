package app.megachess.AI.pieces;

import app.megachess.enums.PieceDirection;
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
	 * @return true or false
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
	 * @return true or false
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
	 * llamar segun el parametro.
	 * 
	 * @param target
	 * @return el resultado del submetodo llamado true or false
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
	 * evaluateTrajectoryToTop, evalua la trayectoria hacia arriba y si existe
	 * posibilidad de hacerla, busca hasta donde hay libertad de avance y la setea o
	 * hasta donde haya libertad de avance y un enemigo para setear esa posicion del
	 * enemigo.
	 * 
	 * @return true or false
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
	 * evaluateTrajectoryToBot, evalua la trayectoria hacia abajo y si existe
	 * posibilidad de hacerla, busca hasta donde hay libertad de avance y la setea o
	 * hasta donde haya libertad de avance y un enemigo para setear esa posicion del
	 * enemigo.
	 * 
	 * @return true or false
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
	 * evaluateTrajectoryToLeft, evalua la trayectoria hacia la izquierda y si
	 * existe posibilidad de hacerla, busca hasta donde hay libertad de avance y la
	 * setea o hasta donde haya libertad de avance y un enemigo para setear esa
	 * posicion del enemigo.
	 * 
	 * @return true or false
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
	 * evaluateTrajectoryToRight, evalua la trayectoria hacia la derecha y si existe
	 * posibilidad de hacerla, busca hasta donde hay libertad de avance y la setea o
	 * hasta donde haya libertad de avance y un enemigo para setear esa posicion del
	 * enemigo.
	 * 
	 * @return true or false
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
	 * evaluateTrajectoryToTopLeft, evalua la trayectoria hacia arriba a la
	 * izquierda y si existe posibilidad de hacerla, busca hasta donde hay libertad
	 * de avance y la setea o hasta donde haya libertad de avance y un enemigo para
	 * setear esa posicion del enemigo.
	 * 
	 * @return true or false
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
	 * evaluateTrajectoryToTopRight, evalua la trayectoria hacia arriba a la derecha
	 * y si existe posibilidad de hacerla, busca hasta donde hay libertad de avance
	 * y la setea o hasta donde haya libertad de avance y un enemigo para setear esa
	 * posicion del enemigo.
	 * 
	 * @return true or false
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
	 * evaluateTrajectoryToBotLeft, evalua la trayectoria hacia abajo a la izquierda
	 * y si existe posibilidad de hacerla, busca hasta donde hay libertad de avance
	 * y la setea o hasta donde haya libertad de avance y un enemigo para setear esa
	 * posicion del enemigo.
	 * 
	 * @return true or false
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
	 * evaluateTrajectoryToBotRight, evalua la trayectoria hacia abajo a la derecha
	 * y si existe posibilidad de hacerla, busca hasta donde hay libertad de avance
	 * y la setea o hasta donde haya libertad de avance y un enemigo para setear esa
	 * posicion del enemigo.
	 * 
	 * @return true or false
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
	 * evalua el cuadrante superior proximo a la pieza y si existe una pieza enemiga
	 * para poder comer.
	 * 
	 * @return true or false
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
	 * evalua el cuadrante inferior proximo a la pieza y si existe una pieza enemiga
	 * para poder comer.
	 * 
	 * @return true or false
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
	 * evalua el cuadrante izquierdo proximo a la pieza y si existe una pieza
	 * enemiga para poder comer.
	 * 
	 * @return true or false
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
	 * evalua el cuadrante derecho proximo a la pieza y si existe una pieza enemiga
	 * para poder comer.
	 * 
	 * @return true or false
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
	 * evalua el cuadrante superior-izquierdo proximo a la pieza y si existe una
	 * pieza enemiga para poder comer.
	 * 
	 * @return true or false
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
	 * evalua el cuadrante inferior-izquierdo proximo a la pieza y si existe una
	 * pieza enemiga para poder comer.
	 * 
	 * @return true or false
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
	 * evalua el cuadrante superior-derecho proximo a la pieza y si existe una pieza
	 * enemiga para poder comer.
	 * 
	 * @return true or false
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
	 * evalua el cuadrante inferior-izquierdo proximo a la pieza y si existe una
	 * pieza enemiga para poder comer.
	 * 
	 * @return true or false
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
	 * evalua un cuadrante especifico en el tablero para ver si hay o no un enemigo
	 * 
	 * @param row
	 * @param col
	 * @return true or false
	 */
	protected boolean evaluateQuadrant(int row, int col) {
		return ChessUtil.isMyEnemy(board[row][col], color) ? true : false;
	}

	/**
	 * evalua si la pieza esta bajo ataque
	 * 
	 * @return
	 */
	public boolean isUnderAttack() {

		if (right >= 0 && right <= 15 && front >= 0 && front <= 15) {
			if (ChessUtil.isPawnEnemy(board, front, right, color)) {
				return true;
			}
		}

		if (left >= 0 && left <= 15 && front >= 0 && front <= 15) {
			if (ChessUtil.isPawnEnemy(board, front, left, color)) {
				return true;
			}
		}

		return false;
	}

}
