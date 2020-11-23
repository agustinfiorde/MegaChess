package app.megachess.AI.pieces;

import app.megachess.enums.AllDirection;
import app.megachess.utils.ChessUtil;
import app.megachess.websocket.models.Response;

public class RookAI extends Piece implements PieceActionAssassin {

	public RookAI(String piece, int[] position, String board[][], String color) {
		super(piece, position, board, color);
	}

	@Override
	public boolean canDefend() {
		return (evaluateBot() || evaluateTop() || evaluateLeft() || evaluateRight());
	}

	@Override
	public boolean canProceed() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean murder() {
		int back;
		int botLine;
		int thirdLine;
		int secondLine;
		int frontLine;
		AllDirection toTop;
		AllDirection toBot;

		if (color.equals("white")) {
			back = fromRow < 15 ? fromRow + 1 : 0;
			botLine = 0;
			thirdLine = 1;
			secondLine = 2;
			frontLine = 3;
			toTop = AllDirection.TO_TOP;
			toBot = AllDirection.TO_BOT;
		} else {
			back = fromRow > 0 ? fromRow - 1 : 0;
			botLine = 15;
			thirdLine = 14;
			secondLine = 13;
			frontLine = 12;
			toTop = AllDirection.TO_BOT;
			toBot = AllDirection.TO_TOP;
		}

		assassinMissionLastLine(frontLine, secondLine, thirdLine, botLine, toTop, toBot, back);
		assassinMissionThirdLine(frontLine, secondLine, thirdLine, botLine, toTop, toBot, back);
		assassinMissionSecondLine(frontLine, secondLine, thirdLine, botLine, toTop, toBot, back);
		assassinMissionFirstLine(frontLine, secondLine, thirdLine, botLine, toTop, toBot, back);

		if (getToCol() != null && getToRow() != null) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public boolean hunt() {
		Response target = targetToHunt();
		if (target != null) {

			if (target.getFromCol() != fromCol) {
				if (target.getFromCol() > fromCol) {
					evaluateTrajectory(AllDirection.RIGHT);
				} else {
					evaluateTrajectory(AllDirection.LEFT);
				}
			} else {
				setToCol(fromCol);
			}

			if (target.getFromRow() != fromRow) {
				if (target.getFromRow() > fromRow) {
					evaluateTrajectory(AllDirection.TO_BOT);
				} else {
					evaluateTrajectory(AllDirection.TO_TOP);
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

	public void assassinMissionLastLine(int frontLine, int secondLine, int thirdLine, int botLine, AllDirection toTop,
			AllDirection toBot, int back) {
		if (!ChessUtil.rowIsClear(board, botLine, color)) {

			// Last Line
			if (fromRow == botLine) {
				if (toRight()) {
					evaluateTrajectory(AllDirection.RIGHT);
				} else if (toLeft()) {
					evaluateTrajectory(AllDirection.LEFT);
				} else if (ChessUtil.rowIsClear(board, botLine, color)) {
					setTo(back, toCol);
				}
			} else {
				evaluateTrajectory(toTop);
			}
		}
	}

	public void assassinMissionThirdLine(int frontLine, int secondLine, int thirdLine, int botLine, AllDirection toTop,
			AllDirection toBot, int back) {
		if (!ChessUtil.rowIsClear(board, thirdLine, color) && ChessUtil.rowIsClear(board, botLine, color)) {

			// Third Line
			if (fromRow == thirdLine) {
				if (toRight()) {
					evaluateTrajectory(AllDirection.RIGHT);
				} else if (toLeft()) {
					evaluateTrajectory(AllDirection.LEFT);
				} else if (ChessUtil.rowIsClear(board, thirdLine, color)) {
					setTo(back, toCol);
				}
			} else {
				evaluateTrajectory(toTop);
			}
		}
	}

	public void assassinMissionSecondLine(int frontLine, int secondLine, int thirdLine, int botLine, AllDirection toTop,
			AllDirection toBot, int back) {
		if (!ChessUtil.rowIsClear(board, secondLine, color) && ChessUtil.rowIsClear(board, thirdLine, color)
				&& ChessUtil.rowIsClear(board, botLine, color)) {
			// Second Line

			if (fromRow == secondLine) {
				if (toRight()) {
					evaluateTrajectory(AllDirection.RIGHT);
				} else if (toLeft()) {
					evaluateTrajectory(AllDirection.LEFT);
				} else if (ChessUtil.rowIsClear(board, secondLine, color)) {
					setTo(back, toCol);
				}
			} else {
				evaluateTrajectory(toTop);
			}
		}
	}

	public void assassinMissionFirstLine(int frontLine, int secondLine, int thirdLine, int botLine, AllDirection toTop,
			AllDirection toBot, int back) {
		if (!ChessUtil.rowIsClear(board, frontLine, color) && ChessUtil.rowIsClear(board, secondLine, color)
				&& ChessUtil.rowIsClear(board, thirdLine, color) && ChessUtil.rowIsClear(board, botLine, color)) {
			// First Line

			if (fromRow == frontLine) {
				if (toRight()) {
					evaluateTrajectory(AllDirection.RIGHT);
				} else if (toLeft()) {
					evaluateTrajectory(AllDirection.LEFT);
				} else {
					hunt();
				}
			}
		}
	}

}
