package app.megachess.AI.pieces;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import app.megachess.utils.ChessUtil;

public class QueenAITests {

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
							"     rr rr      " + 
							"     rr rr      " + 
							"       "+piece+"        " + 
							"     rr rr      " + 
							"     rr rr      " + 
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

	public QueenAI generateQueen(int[] position, String[][] board, String color) {
		return new QueenAI(position, board, color);
	}
	
	@Test
	public void canDefend() {
		
		setPieceInBoard("Q");
		
		QueenAI queen = generateQueen(new int[] {7,7}, boardEmpty, "white");
		//ante ninguna amenaza en el tablero retornaria false
		assertFalse(queen.canDefend());
		
		queen = generateQueen(new int[] {7,7}, boardLongTrajectory, "white");
		//ante amenazas a la distancia retornaria true
		assertTrue(queen.canDefend());
		
		queen = generateQueen(new int[] {7,7}, boardNextTo, "white");
		//ante amenazas proximas retornaria true
		assertTrue(queen.canDefend());
		
		queen = generateQueen(new int[] {7,7}, boardWithBlocks, "white");
		//ante bloqueo total de los compañeros retornaria false
		assertFalse(queen.canDefend());
		
	}
	
	@Test
	public void assassinMissionLastLine() {
				
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
	
	}
	
}
