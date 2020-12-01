package app.megachess.AI.pieces;

import app.megachess.enums.PieceDirection;
import app.megachess.models.Response;
import app.megachess.utils.ChessUtil;

public class QueenAI extends Piece implements PieceActionAssassin {

	public QueenAI(int[] position, String board[][], String color) {
		super(position, board, color);
	}

	@Override
	public boolean canProceed() {
		/*
		 * No aplica a la reina, ya que la mision de la reina no es avanzar con el
		 * objetivo de moverse para conseguir puntos, sino avanzar a cumplir su mission
		 * de asesina o a cazar
		 */
		return false;
	}

	@Override
	public boolean canDefend() {
		for (PieceDirection target : PieceDirection.values()) {
			if (evaluateTrajectory(target)) {
				if (evaluateQuadrant(toRow, toCol)) {
					return true;
				}
			}
		}
		return false;
	}

	@Override
	public boolean assassinMissionLastLine(int botLine, PieceDirection toTop) {
		if (!ChessUtil.rowIsClearOfEnemies(board, botLine, color)) {
			if (fromRow == botLine) {
				if (toRight()) {
					return evaluateTrajectory(PieceDirection.RIGHT);
				} else if (toLeft()) {
					return evaluateTrajectory(PieceDirection.LEFT);
				} else if (ChessUtil.rowIsClearOfEnemies(board, botLine, color)) {
					setTo(back, fromCol);
					return true;
				}
			} else {
				return evaluateTrajectory(toTop);
			}
		}
		return false;
	}

	@Override
	public boolean assassinMissionThirdLine(int thirdLine, int botLine, PieceDirection toTop, PieceDirection toBot) {
		if (!ChessUtil.rowIsClearOfEnemies(board, thirdLine, color)
				&& ChessUtil.rowIsClearOfEnemies(board, botLine, color)) {
			if (fromRow == thirdLine) {

				if (toRight()) {
					evaluateTrajectory(PieceDirection.RIGHT);
				} else if (toLeft()) {
					evaluateTrajectory(PieceDirection.LEFT);
				} else if (ChessUtil.rowIsClearOfEnemies(board, thirdLine, color)) {
					setTo(back, fromCol);
				}

			} else {
				if (fromRow < thirdLine) {
					evaluateTrajectory(PieceDirection.TO_BOT);

					if (toRow != null) {
						if (toRow >= thirdLine) {
							setTo(thirdLine, toCol);
						}
					}
				} else {
					evaluateTrajectory(PieceDirection.TO_TOP);

					if (toRow != null) {
						if (toRow <= thirdLine) {
							setTo(thirdLine, toCol);
						}
					}
				}
			}
		}
		return false;
	}

	@Override
	public boolean assassinMissionSecondLine(int secondLine, int thirdLine, int botLine, PieceDirection toTop,
			PieceDirection toBot) {
		if (!ChessUtil.rowIsClearOfEnemies(board, secondLine, color)
				&& ChessUtil.rowIsClearOfEnemies(board, thirdLine, color)
				&& ChessUtil.rowIsClearOfEnemies(board, botLine, color)) {
			if (fromRow == secondLine) {
				if (toRight()) {
					evaluateTrajectory(PieceDirection.RIGHT);
				} else if (toLeft()) {
					evaluateTrajectory(PieceDirection.LEFT);
				} else if (ChessUtil.rowIsClearOfEnemies(board, secondLine, color)) {
					setTo(back, fromCol);
				}
			} else {
				if (fromRow < secondLine) {
					evaluateTrajectory(PieceDirection.TO_BOT);

					if (toRow != null) {
						if (toRow >= secondLine) {
							setTo(secondLine, toCol);
						}
					}
				} else {
					evaluateTrajectory(PieceDirection.TO_TOP);

					if (toRow != null) {
						if (toRow <= secondLine) {
							setTo(secondLine, toCol);
						}
					}
				}
			}
		}
		return false;
	}

	@Override
	public boolean assassinMissionFirstLine(int frontLine, int secondLine, int thirdLine, int botLine,
			PieceDirection toTop, PieceDirection toBot) {
		if (!ChessUtil.rowIsClearOfEnemies(board, frontLine, color)
				&& ChessUtil.rowIsClearOfEnemies(board, secondLine, color)
				&& ChessUtil.rowIsClearOfEnemies(board, thirdLine, color)
				&& ChessUtil.rowIsClearOfEnemies(board, botLine, color)) {
			if (fromRow == frontLine) {
				if (toRight()) {
					return evaluateTrajectory(PieceDirection.RIGHT);
				} else if (toLeft()) {
					return evaluateTrajectory(PieceDirection.LEFT);
				}
			} else {
				if (fromRow < frontLine) {
					evaluateTrajectory(PieceDirection.TO_BOT);

					if (toRow != null) {
						if (toRow >= frontLine) {
							setTo(frontLine, toCol);
							return true;
						}
					}
				} else {
					evaluateTrajectory(PieceDirection.TO_TOP);

					if (toRow != null) {
						if (toRow <= frontLine) {
							setTo(frontLine, toCol);
							return true;
						}
					}
				}
			}
		}
		return false;
	}

	@Override
	public boolean murder() {

		int botLine;
		int thirdLine;
		int secondLine;
		int frontLine;
		PieceDirection toTop;
		PieceDirection toBot;

		if (color.equals("white")) {
			botLine = 0;
			thirdLine = 1;
			secondLine = 2;
			frontLine = 3;
			toTop = PieceDirection.TO_TOP;
			toBot = PieceDirection.TO_BOT;
		} else {
			botLine = 15;
			thirdLine = 14;
			secondLine = 13;
			frontLine = 12;
			toTop = PieceDirection.TO_BOT;
			toBot = PieceDirection.TO_TOP;
		}

		assassinMissionLastLine(botLine, toTop);
		if (getToCol() != null && getToRow() != null) {
			return true;
		}
		assassinMissionThirdLine(thirdLine, botLine, toTop, toBot);
		if (getToCol() != null && getToRow() != null) {
			return true;
		}
		assassinMissionSecondLine(secondLine, thirdLine, botLine, toTop, toBot);
		if (getToCol() != null && getToRow() != null) {
			return true;
		}
		assassinMissionFirstLine(frontLine, secondLine, thirdLine, botLine, toTop, toBot);
		if (getToCol() != null && getToRow() != null) {
			return true;
		}
		return false;

	}

	@Override
	public boolean hunt() {
		Response target = targetToHunt();
		if (target != null) {

			if (target.getFromCol() != fromCol) {
				if (target.getFromCol() > fromCol) {
					evaluateTrajectory(PieceDirection.RIGHT);
				} else {
					evaluateTrajectory(PieceDirection.LEFT);
				}
			} else {
				setToCol(fromCol);
			}

			if (target.getFromRow() != fromRow) {
				if (target.getFromRow() > fromRow) {
					evaluateTrajectory(PieceDirection.TO_BOT);
				} else {
					evaluateTrajectory(PieceDirection.TO_TOP);
				}
			} else {
				setToRow(fromRow);
			}
		}

		if (getToCol() != null && getToRow() != null) {
			return true;
		} else {
			return false;
		}
	}

}
