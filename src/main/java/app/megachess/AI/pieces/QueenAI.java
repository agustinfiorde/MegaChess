package app.megachess.AI.pieces;

import app.megachess.enums.AllDirection;
import app.megachess.utils.ChessUtil;

public class QueenAI extends Piece implements PieceActionAssassin {

	private boolean assassin;
	private AllDirection direction;

	public QueenAI(String piece, int[] position, String board[][], String color, boolean assassin) {
		super(piece, position, board, color);
		this.assassin = assassin;
		canBeEated();
	}

	// las reinas no lo utilizan
	@Override
	public boolean canMove() {
		evaluateTrajectory(direction);
		return true;
	}

	// las reinas no lo utilizan
	@Override
	public boolean canEat() {
		evaluateTrajectory(direction);
		return true;
	}

	// evalua si la reina esta en peligro y la hace actuar
	public boolean canBeEated() {
		int frontRow;

		if (color.equals("white")) {
			frontRow = fromRow - 1;
		} else {
			frontRow = fromRow + 1;
		}
		if (assassin) {
			if (evaluateQuadrants(frontRow, fromCol - 1)) {
				setTo(frontRow, fromCol - 1);
				return true;
			}
			if (evaluateQuadrants(frontRow, fromCol + 1)) {
				setTo(frontRow, fromCol + 1);
				return true;
			}
			assassinMission();
			return true;
		} else {
			// futuro modulo para defensa central
			evaluateTrajectory(AllDirection.LEFT);
			evaluateTrajectory(AllDirection.RIGHT);
			moveLikeDefender();
			return true;
		}
	}

	// sub metodo para analizar los cuadrantes donde la reina puede ser comida, por
	// ahora solo funciona contra los peones
	public boolean evaluateQuadrants(int row, int col) {
		if (row == -1 || row == 0 || row == 1 || row == 16 || row == 14 || (fromCol == 0 && fromCol > col)
				|| (fromCol == 15 && fromCol < col)) {
			return false;
		}
		if (ChessUtil.isPawnEnemy(board[row][col], color)) {
			return true;
		} else {
			return false;
		}
	}

	// establece la mision de la reina asesina en base a su posicion y objetivos
	// cumplidos
	public void assassinMission() {

		int botLine;
		int thirdLine;
		int secondLine;
		int frontLine;
		AllDirection toTop;
		AllDirection toBot;

		if (color.equals("white")) {
			botLine = 0;
			thirdLine = 1;
			secondLine = 2;
			frontLine = 3;
			toTop = AllDirection.TO_TOP;
			toBot = AllDirection.TO_BOT;
		} else {
			botLine = 15;
			thirdLine = 14;
			secondLine = 13;
			frontLine = 12;
			toTop = AllDirection.TO_BOT;
			toBot = AllDirection.TO_TOP;
		}

		if (!ChessUtil.rowIsClear(board, botLine, color)) {
			// Last Line
			if (fromRow == botLine) {
				if (toRight()) {
					evaluateTrajectory(AllDirection.RIGHT);
				} else if (toLeft()) {
					evaluateTrajectory(AllDirection.LEFT);
				}
			} else {
				evaluateTrajectory(toTop);
			}
		}
		if (!ChessUtil.rowIsClear(board, thirdLine, color) && ChessUtil.rowIsClear(board, botLine, color)) {
			// Third Line

			if (fromRow == thirdLine) {
				if (toRight()) {
					evaluateTrajectory(AllDirection.RIGHT);
				} else if (toLeft()) {
					evaluateTrajectory(AllDirection.LEFT);
				} else {
					evaluateTrajectory(toTop);
				}
			} else {
				if (fromRow == 0 || fromRow == 15) {
					evaluateTrajectory(toBot);
				} else {
					evaluateTrajectory(toTop);
				}
			}
		}
		if (!ChessUtil.rowIsClear(board, secondLine, color) && ChessUtil.rowIsClear(board, thirdLine, color)
				&& ChessUtil.rowIsClear(board, botLine, color)) {
			// Second Line

			if (fromRow == secondLine) {
				if (toRight()) {
					evaluateTrajectory(AllDirection.RIGHT);
				} else if (toLeft()) {
					evaluateTrajectory(AllDirection.LEFT);
				} else {
					evaluateTrajectory(toTop);
				}
			} else {
				if (fromRow == 0 || fromRow == 15) {
					evaluateTrajectory(toBot);
				} else {
					evaluateTrajectory(toTop);
				}
			}
		}
		if (!ChessUtil.rowIsClear(board, frontLine, color) && ChessUtil.rowIsClear(board, secondLine, color)
				&& ChessUtil.rowIsClear(board, thirdLine, color) && ChessUtil.rowIsClear(board, botLine, color)) {
			// First Line

			if (fromRow == frontLine) {
				if (toRight()) {
					evaluateTrajectory(AllDirection.RIGHT);
				} else if (toLeft()) {
					evaluateTrajectory(AllDirection.LEFT);
				}
			} else {
				if (fromRow == 0 || fromRow == 15) {
					evaluateTrajectory(toBot);
				} else {
					evaluateTrajectory(toTop);
				}
			}
		}
	}

	// Metodo para el modulo de defensoras centrales moviendolas en caso de que
	// esten en peligro
	// por ahora no aplica
	public void moveLikeDefender() {
		if (fromCol != 15) {
			setTo(fromRow, 15);
		} else {
			setTo(fromRow, 0);
		}
	}
}
