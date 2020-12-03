package app.megachess.AI.pieces;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import app.megachess.utils.ChessUtil;

public class PawnAITests {

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
							"                " + 
							"                " + 
							"                " + 
							"                " + 
							"                " + 
							"                " +
							"     rrrrr      " + 
							"     rrrrr      " + 
							"     rr"+piece+"rr      " + 
							"     rrrrr      " + 
							"     rrrrr      " ;

		this.boardNextTo=ChessUtil.getBoard(boardNextTo);
		
		String boardWithBlocks= "                " + 
								"                " + 
								"                " + 
								"                " + 
								"                " + 
								"                " + 
								"                " + 
								"                " + 
								"                " + 
								"                " + 
								"                " + 
								"     PPPPP      " + 
								"     PPPPP      " + 
								"      P"+piece+"P       " + 
								"      PPP       " +
								"                ";

		this.boardWithBlocks=ChessUtil.getBoard(boardWithBlocks);
	}

	public PawnAI generatePawn(int[] position, String[][] board, String color) {
		return new PawnAI(position, board, color);
	}
	
	@Test
	public void canDefend() {
		
		setPieceInBoard("P");
		
		PawnAI pawn = generatePawn(new int[] {7,7}, boardEmpty, "white");
		//ante ninguna amenaza proxima para comer retornaria false
		assertFalse(pawn.canDefend());
		
		pawn = generatePawn(new int[] {7,7}, boardLongTrajectory, "white");
		//ante ninguna amenaza proxima para comer retornaria false
		assertFalse(pawn.canDefend());
		
		pawn = generatePawn(new int[] {13,7}, boardNextTo, "white");
		//ante amenazas proximas en posiciones validas retornaria true
		assertTrue(pawn.canDefend());
		
		pawn = generatePawn(new int[] {13,7}, boardWithBlocks, "white");
		//ante bloqueo total de los compañeros retornaria false
		assertFalse(pawn.canDefend());
		
		//------------------------------------------------------------------------
		
		setPieceInBoard("p");
		
		pawn = generatePawn(new int[] {7,7}, boardEmpty, "black");
		//ante ninguna amenaza proxima para comer retornaria false
		assertFalse(pawn.canDefend());
				
		pawn = generatePawn(new int[] {7,7}, boardLongTrajectory, "white");
		//ante ninguna amenaza proxima para comer retornaria false
		assertFalse(pawn.canDefend());
				
		pawn = generatePawn(new int[] {13,7}, boardNextTo, "white");
		//ante amenazas proximas en posiciones validas retornaria true
		assertTrue(pawn.canDefend());
				
		pawn = generatePawn(new int[] {13,7}, boardWithBlocks, "white");
		//ante bloqueo total de los compañeros retornaria false
		assertFalse(pawn.canDefend());
		
	}
	
	@Test
	public void canProceed() {
		
		setPieceInBoard("P");
		
		PawnAI pawn = generatePawn(new int[] {7,7}, boardEmpty, "white");
		//ante ningun bloqueo frontal retornaria true
		assertTrue(pawn.canProceed());
		
		pawn = generatePawn(new int[] {7,7}, boardLongTrajectory, "white");
		//ante ningun bloqueo frontal retornaria true
		assertTrue(pawn.canProceed());
		
		pawn = generatePawn(new int[] {13,7}, boardNextTo, "white");
		//ante bloqueos adelante de amenazas retornaria false
		assertFalse(pawn.canProceed());
		
		pawn = generatePawn(new int[] {13,7}, boardWithBlocks, "white");
		//ante bloqueo total de los compañeros retornaria false
		assertFalse(pawn.canProceed());
		
		//------------------------------------------------------------------------
		
		setPieceInBoard("p");
		
		pawn = generatePawn(new int[] {7,7}, boardEmpty, "black");
		//ante ningun bloqueo frontal retornaria true
		assertTrue(pawn.canProceed());
		
		pawn = generatePawn(new int[] {7,7}, boardLongTrajectory, "black");
		//ante ningun bloqueo frontal retornaria true
		assertTrue(pawn.canProceed());
		
		pawn = generatePawn(new int[] {13,7}, boardNextTo, "black");
		//ante bloqueos adelante de amenazas retornaria false
		assertFalse(pawn.canProceed());
		
		pawn = generatePawn(new int[] {13,7}, boardWithBlocks, "black");
		//ante bloqueo total de los compañeros retornaria false
		assertFalse(pawn.canProceed());
		
	}
	
}
