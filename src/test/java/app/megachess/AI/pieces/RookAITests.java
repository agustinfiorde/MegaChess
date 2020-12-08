package app.megachess.AI.pieces;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

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
	
}
