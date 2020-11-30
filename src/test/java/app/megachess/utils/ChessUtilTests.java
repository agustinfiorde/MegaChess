package app.megachess.utils;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class ChessUtilTests {

	@Test
	public void isWhite() {
		String white = "P";
		String black = "p";
		String other = "|";

		assertTrue(ChessUtil.isWhite(white));
		assertFalse(ChessUtil.isWhite(black));
		assertFalse(ChessUtil.isWhite(other));
	}

	@Test
	public void isBlack() {
		String white = "P";
		String black = "p";
		String other = "|";

		assertTrue(ChessUtil.isBlack(black));
		assertFalse(ChessUtil.isBlack(white));
		assertFalse(ChessUtil.isBlack(other));
	}

	@Test
	public void rowIsClear() {

		String boardString = "rrhhbbqqkkbbhhrr" + "rrhhbbqqkkbbhhrr" + "pppppppppppppppp" + "pppppppppppppppp"
				+ "                " + "                " + "                " + "                " + "                "
				+ "                " + "                " + "                " + "PPPPPPPPPPPPPPPP" + "PPPPPPPPPPPPPPPP"
				+ "RRHHBBQQKKBBHHRR" + "RRHHBBQQKKBBHHRR";
		String[][] board = ChessUtil.getBoard(boardString);

		// row with out enemies
		assertTrue(ChessUtil.rowIsClearOfEnemies(board, 8, "white"));
		// row with allies
		assertTrue(ChessUtil.rowIsClearOfEnemies(board, 15, "white"));
		// row with enemies
		assertFalse(ChessUtil.rowIsClearOfEnemies(board, 1, "white"));
		// row with out enemies
		assertTrue(ChessUtil.rowIsClearOfEnemies(board, 8, "black"));
		// row with allies
		assertTrue(ChessUtil.rowIsClearOfEnemies(board, 1, "black"));
		// row with enemies
		assertFalse(ChessUtil.rowIsClearOfEnemies(board, 15, "black"));
	}

	@Test
	public void pawnsActives() {

		String boardStringWithPawns = "rrhhbbqqkkbbhhrr" + 
									  "rrhhbbqqkkbbhhrr" + 
									  "pppppppppppppppp" + 
									  "pppppppppppppppp" + 
									  "                " + 
									  "                " + 
									  "                " + 
									  "                " + 
									  "                " + 
									  "                " + 
									  "                " + 
									  "                " + 
									  "PPPPPPPPPPPPPPPP" + 
									  "PPPPPPPPPPPPPPPP" + 
									  "RRHHBBQQKKBBHHRR" + 
									  "RRHHBBQQKKBBHHRR";
		String[][] board = ChessUtil.getBoard(boardStringWithPawns);

		// 32 Pawns
		assertEquals(32, ChessUtil.pawnsActives(board, "white").size());
		assertEquals(32, ChessUtil.pawnsActives(board, "black").size());
		assertNotEquals(0, ChessUtil.pawnsActives(board, "white").size());
		assertNotEquals(0, ChessUtil.pawnsActives(board, "black").size());

		String boardStringWithoutPawns = "rrhhbbqqkkbbhhrr" + "rrhhbbqqkkbbhhrr" + "                "
				+ "                " + "                " + "                " + "                " + "                "
				+ "                " + "                " + "                " + "                " + "                "
				+ "                " + "RRHHBBQQKKBBHHRR" + "RRHHBBQQKKBBHHRR";
		board = ChessUtil.getBoard(boardStringWithoutPawns);

		// 0 Pawns
		assertEquals(0, ChessUtil.pawnsActives(board, "white").size());
		assertEquals(0, ChessUtil.pawnsActives(board, "black").size());
		assertNotEquals(32, ChessUtil.pawnsActives(board, "white").size());
		assertNotEquals(32, ChessUtil.pawnsActives(board, "black").size());
	}

	@Test
	public void whitePawnFirstMove() {

		String boardStringWithPawn = "rrhhbbqqkkbbhhrr" + "rrhhbbqqkkbbhhrr" + "pppppppppppppppp" + "pppppppppppppppp"
				+ "                " + "                " + "                " + "                " + "                "
				+ "                " + "           P    " + "                " + "PPPPPPPPPPP PPPP" + "PPPPPPPPPPPPPPPP"
				+ "RRHHBBQQKKBBHHRR" + "RRHHBBQQKKBBHHRR";
		String[][] board = ChessUtil.getBoard(boardStringWithPawn);

		assertTrue(ChessUtil.whitePawnFirstMove(board).isExist());

		String boardStringWithoutPawn = "rrhhbbqqkkbbhhrr" + "rrhhbbqqkkbbhhrr" + "pppppppppppppppp"
				+ "pppppppppppppppp" + "                " + "                " + "                " + "                "
				+ "                " + "                " + "                " + "                " + "PPPPPPPPPPPPPPPP"
				+ "PPPPPPPPPPPPPPPP" + "RRHHBBQQKKBBHHRR" + "RRHHBBQQKKBBHHRR";
		board = ChessUtil.getBoard(boardStringWithoutPawn);

		assertFalse(ChessUtil.whitePawnFirstMove(board).isExist());
	}

	@Test
	public void getPiecesByColor() {

		String boardString = "rrhhbbqqkkbbhhrr" + "rrhhbbqqkkbbhhrr" + "pppppppppppppppp" + "pppppppppppppppp"
				+ "                " + "                " + "                " + "                " + "                "
				+ "                " + "                " + "                " + "PPPPPPPPPPPPPPPP" + "PPPPPPPPPPPPPPPP"
				+ "RRHHBBQQKKBBHHRR" + "RRHHBBQQKKBBHHRR";
		String[][] board = ChessUtil.getBoard(boardString);

		assertEquals(4, ChessUtil.getPiecesByColor(board, "K", "white").size());
		assertEquals(8, ChessUtil.getPiecesByColor(board, "R", "white").size());
		assertEquals(0, ChessUtil.getPiecesByColor(board, "°", "white").size());
		assertNotEquals(3, ChessUtil.getPiecesByColor(board, "K", "white").size());

		assertEquals(4, ChessUtil.getPiecesByColor(board, "k", "black").size());
		assertEquals(8, ChessUtil.getPiecesByColor(board, "r", "black").size());
		assertEquals(0, ChessUtil.getPiecesByColor(board, "°", "black").size());
		assertNotEquals(3, ChessUtil.getPiecesByColor(board, "r", "black").size());
	}

	@Test
	public void topPossitionAssassin() {
		String boardString = "rrhhbbQqkkbbhhrr" + "rrhhbbqqkkbbhhrr" + "pppppppppppppppp" + "pppppppppppppppp"
				+ "                " + "                " + "                " + "                " + "                "
				+ "                " + "                " + "                " + "PPPPPPPPPPPPPPPP" + "PPPPPPPPPPPPPPPP"
				+ "RRHHBBQQKKBBHHRR" + "RRHHBBqQKKBBHHRR";
		String[][] board = ChessUtil.getBoard(boardString);

		assertTrue(ChessUtil.topPossitionAssassin(board, "Q").isExist());
		assertFalse(ChessUtil.topPossitionAssassin(board, "").isExist());
	}

	@Test
	public void botPossitionAssassin() {
		String boardString = "rrhhbbQqkkbbhhrr" + "rrhhbbqqkkbbhhrr" + "pppppppppppppppp" + "pppppppppppppppp"
				+ "                " + "                " + "                " + "                " + "                "
				+ "                " + "                " + "                " + "PPPPPPPPPPPPPPPP" + "PPPPPPPPPPPPPPPP"
				+ "RRHHBBQQKKBBHHRR" + "RRHHBBqQKKBBHHRR";
		String[][] board = ChessUtil.getBoard(boardString);

		assertTrue(ChessUtil.botPossitionAssassin(board, "q").isExist());
		assertFalse(ChessUtil.botPossitionAssassin(board, "").isExist());
	}

	@Test
	public void getDefenderPiecesBot() {
		
		String boardString = "rrhhbbQqkkbbhhrr" + "rrhhbbqqkkbbhhrr" + "pppppppppppppppp" + "pppppppppppppppp"
				+ "                " + "                " + "                " + "                " + "                "
				+ "                " + "                " + "                " + "PPPPPPPPPPPPPPPP" + "PPPPPPPPPPPPPPPP"
				+ "RRHHBBQQKKBBHHRR" + "RRHHBBqQKKBBHHRR";
		String[][] board = ChessUtil.getBoard(boardString);
		
		assertEquals(4, ChessUtil.getDefenderPiecesBot(board, "K").size());
		assertEquals(8, ChessUtil.getDefenderPiecesBot(board, "R").size());
		assertEquals(0, ChessUtil.getDefenderPiecesBot(board, "°").size());
		assertNotEquals(3, ChessUtil.getDefenderPiecesBot(board, "R").size());
	}

	@Test
	public void getDefenderPiecesTop() {
		
		String boardString = "rrhhbbQqkkbbhhrr" + "rrhhbbqqkkbbhhrr" + "pppppppppppppppp" + "pppppppppppppppp"
				+ "                " + "                " + "                " + "                " + "                "
				+ "                " + "                " + "                " + "PPPPPPPPPPPPPPPP" + "PPPPPPPPPPPPPPPP"
				+ "RRHHBBQQKKBBHHRR" + "RRHHBBqQKKBBHHRR";
		String[][] board = ChessUtil.getBoard(boardString);
		
		assertEquals(4, ChessUtil.getDefenderPiecesTop(board, "k").size());
		assertEquals(8, ChessUtil.getDefenderPiecesTop(board, "r").size());
		assertEquals(0, ChessUtil.getDefenderPiecesTop(board, "°").size());
		assertNotEquals(3, ChessUtil.getDefenderPiecesTop(board, "r").size());
	}

//	ChessUtil.showBoard(boardStringWithPawn);
	@Test
	public void isMyTeam() {
		assertTrue(ChessUtil.isMyTeam("Q", "white"));
		assertFalse(ChessUtil.isMyTeam("q", "white"));
		assertFalse(ChessUtil.isMyTeam(" ", "white"));
		assertTrue(ChessUtil.isMyTeam("q", "black"));
		assertFalse(ChessUtil.isMyTeam("Q", "black"));
		assertFalse(ChessUtil.isMyTeam(" ", "black"));
	}

	@Test
	public void isMyEnemy() {
		assertTrue(ChessUtil.isMyEnemy("q", "white"));
		assertFalse(ChessUtil.isMyEnemy("Q", "white"));
		assertFalse(ChessUtil.isMyEnemy(" ", "white"));
		assertTrue(ChessUtil.isMyEnemy("Q", "black"));
		assertFalse(ChessUtil.isMyEnemy("q", "black"));
		assertFalse(ChessUtil.isMyEnemy(" ", "black"));
	}

	@Test
	public void getBoard() {

		String boardString = "rrhhbbQqkkbbhhrrrrhhbbqqkkbbhhrrpppppppppppppppppppppppppppppppp                                                                "
				+ "                "
				+ "                                                PPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPP"
				+ "RRHHBBQQKKBBHHRRRRHHBBqQKKBBHHRR";

		assertNotNull(ChessUtil.getBoard(boardString));
		assertNull(ChessUtil.getBoard(boardString + " "));
		assertNull(ChessUtil.getBoard(boardString.substring(0, 128)));
	}

}
