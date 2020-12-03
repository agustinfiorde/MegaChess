package app.megachess.AI.pieces;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import app.megachess.enums.PieceDirection;
import app.megachess.utils.ChessUtil;

public class RookAITests {

	public String[][] boardEmpty;
	public String[][] boardLongTrajectory;
	public String[][] boardNextTo;
	public String[][] boardWithBlocks;
	
	public void setPieceInBoard(String piece) {
		
		String boardEmpty=  "                " + 
							"                " + 
							"                " + 
							"                " + 
							"                " + 
							"                " + 
							"                " + 
							"       "+piece+"        " + 
							"                " + 
							"                " + 
							"                " + 
							"                " + 
							"                " + 
							"                " + 
							"                " + 
							"                ";
		
		this.boardEmpty=ChessUtil.getBoard(boardEmpty);
		
		String boardLongTrajectory= " r      r      r" + 
									"                " + 
									"                " + 
									"                " + 
									"                " + 
									"                " + 
									"                " + 
									"r      "+piece+"       r" + 
									"                " + 
									"                " + 
									"                " + 
									"                " + 
									"                " + 
									"                " + 
									"               r" + 
									"r       r       ";
	
		this.boardLongTrajectory=ChessUtil.getBoard(boardLongTrajectory);
		
		String boardNextTo= "                " + 
							"                " + 
							"                " + 
							"                " + 
							"                " + 
							"     rrrrr      " + 
							"     rrrrr      " + 
							"     rr"+piece+"rr      " + 
							"     rrrrr      " + 
							"     rrrrr      " + 
							"                " + 
							"                " + 
							"                " + 
							"                " + 
							"                " + 
							"                ";

		this.boardNextTo=ChessUtil.getBoard(boardNextTo);
		
		String boardWithBlocks= "                " + 
								"                " + 
								"                " + 
								"                " + 
								"                " + 
								"     PPPPP      " + 
								"     PPPPP      " + 
								"      P"+piece+"P       " + 
								"      PPP       " + 
								"                " + 
								"                " + 
								"                " + 
								"                " + 
								"                " + 
								"                " + 
								"                ";

		this.boardWithBlocks=ChessUtil.getBoard(boardWithBlocks);
	}

	public RookAI generateRook(int[] position, String[][] board, String color) {
		return new RookAI(position, board, color);
	}
	
	@Test
	public void canDefend() {
		
		setPieceInBoard("R");
		
		RookAI rook = generateRook(new int[] {7,7}, boardEmpty, "white");
		//ante ninguna amenaza en el tablero retornaria false
		assertFalse(rook.canDefend());
		
		rook = generateRook(new int[] {7,7}, boardLongTrajectory, "white");
		//ante amenazas a la distancia retornaria true
		assertTrue(rook.canDefend());
		
		rook = generateRook(new int[] {7,7}, boardNextTo, "white");
		//ante amenazas proximas retornaria true
		assertTrue(rook.canDefend());
		
		rook = generateRook(new int[] {7,7}, boardWithBlocks, "white");
		//ante bloqueo total de los compañeros retornaria false
		assertFalse(rook.canDefend());
		
	}
	
	@Test
	public void assassinMissionLastLine() {
		
		String boardString= "rrrrrrrrrrrrrrrr" + 
						    "rrrrrrrrrrrrrrrr" + 
						    "rrrrrrrrrrrrrrrr" + 
						    "rrrrrrrrrrrrrrrr" + 
						    "                " + 
						    "                " + 
						    "                " + 
						    "       R        " + 
						    "                " + 
						    "                " + 
						    "                " + 
						    "                " + 
						    "rrrrrrrrrrrrrrrr" + 
						    "rrrrrrrrrrrrrrrr" + 
						    "rrrrrrrrrrrrrrrr" + 
						    "rrrrrrrrrrrrrrrr";
	
		String[][] board = ChessUtil.getBoard(boardString);
		
		setPieceInBoard("R");
		
		RookAI rook = generateRook(new int[] {7,7}, board, "white");
		assertTrue(rook.assassinMissionLastLine(0, PieceDirection.TO_TOP));

	}
	
	@Test
	public void assassinMissionThirdLine() {

	}
	
	@Test
	public void assassinMissionSecondLine() {
		
	}
	
	@Test
	public void assassinMissionFirstLine() {

	}
	
	@Test
	public void murder() {
		
	}
	
	@Test
	public void hunt() {
				
		String boardString= "                " + 
						    "                " + 
						    "                " + 
						    "                " + 
						    "                " + 
						    "                " + 
						    "                " + 
						    "       R        " + 
						    "                " + 
						    "                " + 
						    "                " + 
						    "                " + 
						    "       P        " + 
						    "                " + 
						    "                " + 
						    "       r        ";
	
		String[][] board = ChessUtil.getBoard(boardString);
		
		RookAI rook = generateRook(new int[] {7,7}, board, "white");
		//como hay un peon obstruyendo devuelve false
//		assertFalse(rook.hunt());
		
		boardString= "                " + 
				    "                " + 
				    "       R        " + 
				    "       q        " + 
				    "                " + 
				    "                " + 
				    "                " + 
				    "       r        " + 
				    "                " + 
				    "                " + 
				    "                " + 
				    "                " + 
				    "                " + 
				    "                " + 
				    "                " + 
				    "                ";

		board = ChessUtil.getBoard(boardString);

		rook = generateRook(new int[] {7,7}, board, "white");
		//como no hay obstruccion devuelve true
//		assertTrue(rook.hunt());
		

	}
}
