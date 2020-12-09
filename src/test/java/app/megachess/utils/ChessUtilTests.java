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

	@Test
	public void findWhitePawnsGroupProgress() {
		
		String boardString =  "rrhhbbqq  bbhhrr" + 
				  "rrhhbbqq  bbhhrr" + 
				  "pppppppp        " + 
				  "pppppppp        " + 
				  "                " + 
				  "                " + 
				  "                " + 
				  "                " + 
				  "                " + 
				  "                " + 
				  "                " + 
				  "                " + 
				  "PPPPPPPP        " + 
				  "PPPPPPPP        " + 
				  "RRHHBBQQKKBBHHRR" + 
				  "RRHHBBQQKKBBHHRR";
		String[][] board = ChessUtil.getBoard(boardString);
		
		assertEquals(0,ChessUtil.findWhitePawnsGroupProgress(board, 15, 8, "white").size());
		assertEquals(16,ChessUtil.findWhitePawnsGroupProgress(board, 8, 0, "white").size());
	}
	
	@Test
	public void findBlackPawnsGroupProgress() {
		
		String boardString =  "rrhhbbqq  bbhhrr" + 
				  "rrhhbbqq  bbhhrr" + 
				  "pppppppp        " + 
				  "pppppppp        " + 
				  "                " + 
				  "                " + 
				  "                " + 
				  "                " + 
				  "                " + 
				  "                " + 
				  "                " + 
				  "                " + 
				  "PPPPPPPP        " + 
				  "PPPPPPPP        " + 
				  "RRHHBBQQKKBBHHRR" + 
				  "RRHHBBQQKKBBHHRR";
		String[][] board = ChessUtil.getBoard(boardString);
		
		assertEquals(0,ChessUtil.findBlackPawnsGroupProgress(board, 15, 8, "white").size());
		assertEquals(16,ChessUtil.findBlackPawnsGroupProgress(board, 8, 0, "white").size());
		
	}
	
	@Test
	public void findBlackPawnsDirectProgress() {
		
		String boardString =  "rrhhbbqq  bbhhrr" + 
				  "rrhhbbqq  bbhhrr" + 
				  "pppppppp        " + 
				  "pppppppp        " + 
				  "                " + 
				  "                " + 
				  "                " + 
				  "                " + 
				  "                " + 
				  "                " + 
				  "                " + 
				  "                " + 
				  "PPPPPPPP        " + 
				  "PPPPPPPP        " + 
				  "RRHHBBQQKKBBHHRR" + 
				  "RRHHBBQQKKBBHHRR";
		String[][] board = ChessUtil.getBoard(boardString);
		
		assertEquals(0,ChessUtil.findBlackPawnsDirectProgress(board, 8, 15, "white").size());
		assertEquals(16,ChessUtil.findBlackPawnsDirectProgress(board, 0, 8, "white").size());
		
	}
	
	@Test
	public void findWhitePawnsDirectProgress() {
		
		String boardString =  "rrhhbbqq  bbhhrr" + 
				  "rrhhbbqq  bbhhrr" + 
				  "pppppppp        " + 
				  "pppppppp        " + 
				  "                " + 
				  "                " + 
				  "                " + 
				  "                " + 
				  "                " + 
				  "                " + 
				  "                " + 
				  "                " + 
				  "PPPPPPPP        " + 
				  "PPPPPPPP        " + 
				  "RRHHBBQQKKBBHHRR" + 
				  "RRHHBBQQKKBBHHRR";
		String[][] board = ChessUtil.getBoard(boardString);
		
		assertEquals(0,ChessUtil.findWhitePawnsDirectProgress(board, 8, 15, "white").size());
		assertEquals(16,ChessUtil.findWhitePawnsDirectProgress(board, 0, 8, "white").size());
		
	}
		
	@Test
	public void isPawnEnemy() {
		
		String boardString = "rrhhbbqqkkbbhhrr" + 
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
		String[][] board = ChessUtil.getBoard(boardString);
		
		assertFalse(ChessUtil.isPawnEnemy(board, 13, 13, "white"));
		assertFalse(ChessUtil.isPawnEnemy(board, 8, 15,"white"));
		assertTrue(ChessUtil.isPawnEnemy(board, 2, 13,"white"));
		
	}
	
	@Test
	public void isHorseEnemy() {
		
		String boardString = "rrhhbbqqkkbbhhrr" + 
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
		String[][] board = ChessUtil.getBoard(boardString);
		
		assertFalse(ChessUtil.isHorseEnemy(board, 15, 13, "white"));
		assertFalse(ChessUtil.isHorseEnemy(board, 0, 15,"white"));
		assertTrue(ChessUtil.isHorseEnemy(board, 0, 13,"white"));
		
	}
	
	@Test
	public void getKingsByColor() {
		String boardString = "rrhhbbqq  bbhhrr" + 
							  "rrhhbbqq  bbhhrr" + 
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
		String[][] board = ChessUtil.getBoard(boardString);
		
		assertEquals(4,ChessUtil.getKingsByColor(board, "k", "white").size());
		assertEquals(0,ChessUtil.getKingsByColor(board, "k", "black").size());
	}
	
	@Test
	public void isEmpty() {
		
		String boardString = "rrhhbbqqkkbbhhrr" + 
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
		String[][] board = ChessUtil.getBoard(boardString);
		
		assertTrue(ChessUtil.isEmpty(board, 8, 8));
		assertFalse(ChessUtil.isEmpty(board, 15, 15));
	}

	@Test
	public void countQueenInMid() {
		
		String boardString = "rrhhbbqqkkbbhhrr" + 
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
		String[][] board = ChessUtil.getBoard(boardString);

		assertEquals(0,ChessUtil.countQueenInMid(board, "white"));
		
		boardString = "rrhhbbqqkkbbhhrr" + 
				  "rrhhbbqqkkbbhhrr" + 
				  "pppppppppppppppp" + 
				  "pppppppppppppppp" + 
				  "                " + 
				  "                " + 
				  "                " + 
				  "          Q     " + 
				  "           Q    " + 
				  "            Q   " + 
				  "                " + 
				  "                " + 
				  "PPPPPPPPPPPPPPPP" + 
				  "PPPPPPPPPPPPPPPP" + 
				  "RRHHBBQQKKBBHHRR" + 
				  "RRHHBBQQKKBBHHRR";
		board = ChessUtil.getBoard(boardString);
		
		assertEquals(3,ChessUtil.countQueenInMid(board, "white"));
		
	}
	
	@Test
	public void countRookInMid() {
		
		String boardString = "rrhhbbqqkkbbhhrr" + 
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
		String[][] board = ChessUtil.getBoard(boardString);

		assertEquals(0,ChessUtil.countRookInMid(board, "white"));
		
		boardString = "rrhhbbqqkkbbhhrr" + 
				  "rrhhbbqqkkbbhhrr" + 
				  "pppppppppppppppp" + 
				  "pppppppppppppppp" + 
				  "                " + 
				  "                " + 
				  "                " + 
				  "          R     " + 
				  "           R    " + 
				  "                " + 
				  "                " + 
				  "                " + 
				  "PPPPPPPPPPPPPPPP" + 
				  "PPPPPPPPPPPPPPPP" + 
				  "RRHHBBQQKKBBHHRR" + 
				  "RRHHBBQQKKBBHHRR";
		board = ChessUtil.getBoard(boardString);
		
		assertEquals(2,ChessUtil.countRookInMid(board, "white"));
		
	}
}
