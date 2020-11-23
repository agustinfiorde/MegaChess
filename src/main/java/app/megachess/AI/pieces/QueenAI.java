package app.megachess.AI.pieces;

import app.megachess.enums.AllDirection;
import app.megachess.utils.ChessUtil;
import app.megachess.websocket.models.Response;

public class QueenAI extends Piece implements PieceActionAssassin {

	public QueenAI(String piece, int[] position, String board[][], String color) {
		super(piece, position, board, color);
	}

	@Override
	public boolean canProceed() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean canDefend() {
		return (evaluateBot() || evaluateTop() || evaluateLeft() || evaluateRight() || evaluateBotLeft()
				|| evaluateBotRight() || evaluateTopLeft() || evaluateTopRight());
	}

	public void assassinMissionLastLine(int botLine, AllDirection toTop) {
		if (!ChessUtil.rowIsClear(board, botLine, color)) {

			// Last Line
			if (fromRow == botLine) {

				if (toRight()) {
					evaluateTrajectory(AllDirection.RIGHT);
				} else if (toLeft()) {
					evaluateTrajectory(AllDirection.LEFT);
				} else if (ChessUtil.rowIsClear(board, botLine, color)) {
					evaluateBot();
				}
			} else {
				evaluateTrajectory(toTop);
			}
		}
	}

	public void assassinMissionThirdLine(int thirdLine, int botLine, AllDirection toTop) {
		if (!ChessUtil.rowIsClear(board, thirdLine, color) && ChessUtil.rowIsClear(board, botLine, color)) {

			// Third Line
			if (fromRow == thirdLine) {
				if (toRight()) {
					evaluateTrajectory(AllDirection.RIGHT);
				} else if (toLeft()) {
					evaluateTrajectory(AllDirection.LEFT);
				} else if (ChessUtil.rowIsClear(board, thirdLine, color)) {
					evaluateBot();
				}
			} else {
				evaluateBot();
			}
		}
	}

	public void assassinMissionSecondLine(int secondLine, int thirdLine, int botLine, AllDirection toTop) {
		if (!ChessUtil.rowIsClear(board, secondLine, color) && ChessUtil.rowIsClear(board, thirdLine, color)
				&& ChessUtil.rowIsClear(board, botLine, color)) {
			// Second Line

			if (fromRow == secondLine) {
				if (toRight()) {
					evaluateTrajectory(AllDirection.RIGHT);
				} else if (toLeft()) {
					evaluateTrajectory(AllDirection.LEFT);
				} else if (ChessUtil.rowIsClear(board, secondLine, color)) {
					evaluateBot();
				}
			} else {
				evaluateBot();
			}
		}
	}

	public void assassinMissionFirstLine(int frontLine, int secondLine, int thirdLine, int botLine, AllDirection toTop,
			AllDirection toBot) {
		if (!ChessUtil.rowIsClear(board, frontLine, color) && ChessUtil.rowIsClear(board, secondLine, color)
				&& ChessUtil.rowIsClear(board, thirdLine, color) && ChessUtil.rowIsClear(board, botLine, color)) {
			// First Line

			if (fromRow == frontLine) {
				if (toRight()) {
					evaluateTrajectory(AllDirection.RIGHT);
				} else if (toLeft()) {
					evaluateTrajectory(AllDirection.LEFT);
				}
			}
		} else {
			hunt();
		}
	}

	@Override
	public boolean murder() {

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

		assassinMissionLastLine(botLine, toTop);
		assassinMissionThirdLine(thirdLine, botLine, toTop);
		assassinMissionSecondLine(secondLine, thirdLine, botLine, toTop);
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

}
