package app.megachess.AI.pieces;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import app.megachess.utils.ChessUtil;

public class BishopAITests {

	public String[][] boardEmpty;
	public String[][] boardLongTrajectory;
	public String[][] boardNextTo;
	public String[][] boardWithBlocks;

	public void setPieceInBoard(String piece) {

		String boardEmpty = "                " + 
							"                " + 
							"                " + 
							"                " + 
							"                " + 
							"                " + 
							"                " + 
							"       " + piece + "        " + 
							"                " + 
							"                " + 
							"                " + 
							"                " + 
							"                " + 
							"                " + 
							"                " + 
							"                ";

		this.boardEmpty = ChessUtil.getBoard(boardEmpty);

		String boardLongTrajectory = "        r      r" + 
									 " r              " + 
									 "                " + 
									 "           r    " + 
									 "                " + 
									 "                " + 
									 "                " + 
									 "r      "+piece+"       r" + 
									 "                " + 
									 "                " + 
									 "                " + 
									 "   r       r    " + 
									 "                " + 
									 "                " + 
									 "               r" + 
									 "r       r       ";

		this.boardLongTrajectory = ChessUtil.getBoard(boardLongTrajectory);

		String boardNextTo = "                " + 
							 "                " + 
							 "                " + 
							 "                " + 
							 "                " + 
 						     "     rrrrr      " + 
						     "     rrrrr      " + 
						     "     rr" + piece + "rr      " + 
						     "     rrrrr      " + 
						     "     rrrrr      " + 
						     "                " + 
						     "                " + 
						     "                " + 
						     "                " + 
						     "                " + 
						     "                ";

		this.boardNextTo = ChessUtil.getBoard(boardNextTo);

		String boardWithBlocks = "                " + 
								 "                " + 
								 "                " + 
								 "                "	+ 
								 "                " + 
								 "     PPPPP      " + 
								 "     PPPPP      " + 
								 "      P" + piece + "P       " + 
								 "      PPP       " + 
								 "                " + 
								 "                " + 
								 "                " + 
								 "                "	+ 
								 "                " + 
								 "                " + 
								 "                ";

		this.boardWithBlocks = ChessUtil.getBoard(boardWithBlocks);
	}

	public BishopAI generateBishop(int[] position, String[][] board, String color, boolean dancerStrategy) {
		return new BishopAI(position, board, color, dancerStrategy);
	}

	@Test
	public void canDefend() {

		setPieceInBoard("B");

		BishopAI bishop = generateBishop(new int[] { 7, 7 }, boardEmpty, "white", false);
		// ante ninguna amenaza proxima retornaria false
		assertFalse(bishop.canDefend());

		bishop = generateBishop(new int[] { 7, 7 }, boardLongTrajectory, "white", false);
		// ante amenazas a lo lejos retornaria true
		assertTrue(bishop.canDefend());

		bishop = generateBishop(new int[] { 7, 7 }, boardNextTo, "white", false);
		// ante amenazas proximas en diagonales retornaria true
		assertTrue(bishop.canDefend());

		bishop = generateBishop(new int[] { 7, 7 }, boardWithBlocks, "white", false);
		// ante bloqueo total de los compañeros retornaria false
		assertFalse(bishop.canDefend());

	}

	@Test
	public void canProceed() {

		setPieceInBoard("B");

		BishopAI bishop = generateBishop(new int[] { 7, 7 }, boardEmpty, "white", false);
		// ante ningun impedimento para avanzar retornaria true
		assertTrue(bishop.canProceed());

		bishop = generateBishop(new int[] { 7, 7 }, boardLongTrajectory, "white", false);
		// ante ningun impedimento para avanzar retornaria true hasta existe la
		// posibilidad de avanzar matando un enemigo
		assertTrue(bishop.canProceed());

		bishop = generateBishop(new int[] { 7, 7 }, boardNextTo, "white", false);
		// ante amenazas proximas bloqueando las diagonales retornaria false
		assertTrue(bishop.canProceed());

		bishop = generateBishop(new int[] { 7, 7 }, boardWithBlocks, "white", false);
		// ante bloqueo total de los compañeros retornaria false
		assertFalse(bishop.canProceed());

	}

}
