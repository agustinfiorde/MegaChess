package app.megachess.AI.pieces;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import app.megachess.utils.ChessUtil;

public class KingAITests {

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

	public KingAI generateKing(int[] position, String[][] board, String color) {
		return new KingAI(position, board, color);
	}
	
	@Test
	public void canDefend() {
		
		setPieceInBoard("K");
		
		KingAI king = generateKing(new int[] {7,7}, boardEmpty, "white");
		//ante ninguna amenaza proxima retornaria false
		assertFalse(king.canDefend());
		
		king = generateKing(new int[] {7,7}, boardLongTrajectory, "white");
		//ante ninguna amenaza proxima retornaria false
		assertFalse(king.canDefend());
		
		king = generateKing(new int[] {7,7}, boardNextTo, "white");
		//ante amenazas proximas retornaria true
		assertTrue(king.canDefend());
		
		king = generateKing(new int[] {7,7}, boardWithBlocks, "white");
		//ante bloqueo total de los compañeros retornaria false
		assertFalse(king.canDefend());
		
	}
	
	@Test
	public void canProceed() {
		
		setPieceInBoard("K");
		
		KingAI king = generateKing(new int[] {7,7}, boardEmpty, "white");
		//ante ningun bloqueo frontal retornaria true
		assertTrue(king.canProceed());
		
		king = generateKing(new int[] {7,7}, boardLongTrajectory, "white");
		//ante ningun bloqueo frontal retornaria true
		assertTrue(king.canProceed());
		
		king = generateKing(new int[] {7,7}, boardNextTo, "white");
		//ante bloqueo de los enemigos retornaria false
		assertFalse(king.canProceed());
		
		king = generateKing(new int[] {7,7}, boardWithBlocks, "white");
		//ante bloqueo total de los compañeros retornaria false
		assertFalse(king.canProceed());
		
	}
	
	@Test
	public void evaluateTrajectoryToRight() {
		
		setPieceInBoard("K");
		
		KingAI king = generateKing(new int[] {7,7}, boardEmpty, "white");
		//ante ningun bloqueo a la derecha retornaria true
		assertTrue(king.evaluateTrajectoryToRight());
		
		king = generateKing(new int[] {7,7}, boardLongTrajectory, "white");
		//ante ningun bloqueo a la derecha retornaria true
		assertTrue(king.evaluateTrajectoryToRight());
		
		king = generateKing(new int[] {7,7}, boardNextTo, "white");
		//ante bloqueo de los enemigos a la derecha retornaria false
		assertFalse(king.evaluateTrajectoryToRight());
		
		king = generateKing(new int[] {7,7}, boardWithBlocks, "white");
		//ante bloqueo a la derecha de los compañeros retornaria false
		assertFalse(king.evaluateTrajectoryToRight());
		
	}
	
	@Test
	public void evaluateTrajectoryToLeft() {
		
		setPieceInBoard("K");
		
		KingAI king = generateKing(new int[] {7,7}, boardEmpty, "white");
		//ante ningun bloqueo a la izquierda retornaria true
		assertTrue(king.evaluateTrajectoryToLeft());
		
		king = generateKing(new int[] {7,7}, boardLongTrajectory, "white");
		//ante ningun bloqueo a la izquierda retornaria true
		assertTrue(king.evaluateTrajectoryToLeft());
		
		king = generateKing(new int[] {7,7}, boardNextTo, "white");
		//ante bloqueo de los enemigos a la izquierda retornaria false
		assertFalse(king.evaluateTrajectoryToLeft());
		
		king = generateKing(new int[] {7,7}, boardWithBlocks, "white");
		//ante bloqueo a la izquierda de los compañeros retornaria false
		assertFalse(king.evaluateTrajectoryToLeft());
		
	}
	
	@Test
	public void evaluateTrajectoryToTop() {
		
		setPieceInBoard("K");
		
		KingAI king = generateKing(new int[] {7,7}, boardEmpty, "white");
		//ante ningun bloqueo arriba retornaria true
		assertTrue(king.evaluateTrajectoryToTop());
		
		king = generateKing(new int[] {7,7}, boardLongTrajectory, "white");
		//ante ninguno bloque arriba retornaria true
		assertTrue(king.evaluateTrajectoryToTop());
		
		king = generateKing(new int[] {7,7}, boardNextTo, "white");
		//ante bloqueo arriba de los enemigos retornaria false
		assertFalse(king.evaluateTrajectoryToTop());
		
		king = generateKing(new int[] {7,7}, boardWithBlocks, "white");
		//ante bloqueo arriba por parte de los compañeros retornaria false
		assertFalse(king.evaluateTrajectoryToTop());
		
	}
	
	@Test
	public void evaluateTrajectoryToBot() {
		
		setPieceInBoard("K");
		
		KingAI king = generateKing(new int[] {7,7}, boardEmpty, "white");
		//ante ningun bloque abajo retornaria true
		assertTrue(king.evaluateTrajectoryToBot());
		
		king = generateKing(new int[] {7,7}, boardLongTrajectory, "white");
		//ante ningun bloque frontal retornaria true
		assertTrue(king.evaluateTrajectoryToBot());
		
		king = generateKing(new int[] {7,7}, boardNextTo, "white");
		//ante bloque de los enemigos retornaria false
		assertFalse(king.evaluateTrajectoryToBot());
		
		king = generateKing(new int[] {7,7}, boardWithBlocks, "white");
		//ante bloqueo total de los compañeros retornaria false
		assertFalse(king.evaluateTrajectoryToBot());
		
	}
	
	@Test
	public void evaluateTrajectoryToTopRight() {
		
		setPieceInBoard("K");
		
		KingAI king = generateKing(new int[] {7,7}, boardEmpty, "white");
		//ante ningun bloqueo arriba a la derecha retornaria true
		assertTrue(king.evaluateTrajectoryToTopRight());
		
		king = generateKing(new int[] {7,7}, boardLongTrajectory, "white");
		//ante ningun bloque arriba a la derecha retornaria true
		assertTrue(king.evaluateTrajectoryToTopRight());
		
		king = generateKing(new int[] {7,7}, boardNextTo, "white");
		//ante bloqueo de los enemigos arriba a la derecha retornaria false
		assertFalse(king.evaluateTrajectoryToTopRight());
		
		king = generateKing(new int[] {7,7}, boardWithBlocks, "white");
		//ante bloqueo arriba a la derecha de los compañeros retornaria false
		assertFalse(king.evaluateTrajectoryToTopRight());
		
	}
	
	@Test
	public void evaluateTrajectoryToTopLeft() {
		
		setPieceInBoard("K");
		
		KingAI king = generateKing(new int[] {7,7}, boardEmpty, "white");
		//ante ningun bloqueo arriba a la izquierda retornaria true
		assertTrue(king.evaluateTrajectoryToTopLeft());
		
		king = generateKing(new int[] {7,7}, boardLongTrajectory, "white");
		//ante ningun bloqueo arriba a la izquierda retornaria true
		assertTrue(king.evaluateTrajectoryToTopLeft());
		
		king = generateKing(new int[] {7,7}, boardNextTo, "white");
		//ante bloqueo de los enemigos arriba a la izquierda retornaria false
		assertFalse(king.evaluateTrajectoryToTopLeft());
		
		king = generateKing(new int[] {7,7}, boardWithBlocks, "white");
		//ante bloqueo arriba a la izquierda de los compañeros retornaria false
		assertFalse(king.evaluateTrajectoryToTopLeft());
		
	}
	
	@Test
	public void evaluateTrajectoryToBotRight() {
		
		setPieceInBoard("K");
		
		KingAI king = generateKing(new int[] {7,7}, boardEmpty, "white");
		//ante ningun bloqueo abajo a la derecha retornaria true
		assertTrue(king.evaluateTrajectoryToBotRight());
		
		king = generateKing(new int[] {7,7}, boardLongTrajectory, "white");
		//ante ningun bloqueo abajo a la derecha retornaria true
		assertTrue(king.evaluateTrajectoryToBotRight());
		
		king = generateKing(new int[] {7,7}, boardNextTo, "white");
		//ante bloqueo de los enemigos abajo a la derecha retornaria false
		assertFalse(king.evaluateTrajectoryToBotRight());
		
		king = generateKing(new int[] {7,7}, boardWithBlocks, "white");
		//ante bloqueo abajo a la derecha de los compañeros retornaria false
		assertFalse(king.evaluateTrajectoryToBotRight());
		
	}
	
	@Test
	public void evaluateTrajectoryToBotLeft() {
		
		setPieceInBoard("K");
		
		KingAI king = generateKing(new int[] {7,7}, boardEmpty, "white");
		//ante ningun bloqueo abajo a la izquierda retornaria true
		assertTrue(king.evaluateTrajectoryToBotLeft());
		
		king = generateKing(new int[] {7,7}, boardLongTrajectory, "white");
		//ante ningun bloqueo abajo a la izquierda retornaria true
		assertTrue(king.evaluateTrajectoryToBotLeft());
		
		king = generateKing(new int[] {7,7}, boardNextTo, "white");
		//ante bloqueo de los enemigos abajo a la izquierda retornaria false
		assertFalse(king.evaluateTrajectoryToBotLeft());
		
		king = generateKing(new int[] {7,7}, boardWithBlocks, "white");
		//ante bloqueo abajo a la izquierda de los compañeros retornaria false
		assertFalse(king.evaluateTrajectoryToBotLeft());
		
	}
	
}
