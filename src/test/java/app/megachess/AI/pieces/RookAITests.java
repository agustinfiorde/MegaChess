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
	
	@Test
	public void hide() {

		String boardString = "rrhhbbqqkkbbhhrr" + 
							  "rrhhbbqqkkbbhhrr" + 
							  "pppppppppppppppp" + 
							  "pppppppppppppppp" + 
							  "                " + 
							  "                " + 
							  "                " + 
							  "                " + 
							  "R               " + 
							  "                " + 
							  "                " + 
							  "                " + 
							  "PPPPPPPPPPPPPPPP" + 
							  "PPPPPPPPPPPPPPPP" + 
							  "PPPPPPPPPPPPPPPP" + 
							  "PPPPPPPPPPPPPPPP";
		
		String board[][] =ChessUtil.getBoard(boardString);

		RookAI rook = generateRook(new int[] {8,0}, board, "white");
		// al estar a la izquierda, no puede ir mas a la izquierda
		assertFalse(rook.hide());
		
		

		boardString = "rrhhbbqqkkbbhhrr" + 
					  "rrhhbbqqkkbbhhrr" + 
					  "pppppppppppppppp" + 
					  "pppppppppppppppp" + 
					  "                " + 
					  "                " + 
					  "                " + 
					  "                " + 
					  "QR              " + 
					  "                " + 
					  "                " + 
					  "                " + 
					  "PPPPPPPPPPPPPPPP" + 
					  "PPPPPPPPPPPPPPPP" + 
					  "PPPPPPPPPPPPPPPP" + 
					  "PPPPPPPPPPPPPPPP";
		
		board =ChessUtil.getBoard(boardString);

		rook = generateRook(new int[] {8,0}, board, "white");
		// al estar a la izquierda, no puede ir mas a la izquierda
		assertFalse(rook.hide());
		
		boardString = "rrhhbbqqkkbbhhrr" + 
				  "rrhhbbqqkkbbhhrr" + 
				  "pppppppppppppppp" + 
				  "pppppppppppppppp" + 
				  "                " + 
				  "                " + 
				  "                " + 
				  "                " + 
				  "QQ             R" + 
				  "                " + 
				  "                " + 
				  "                " + 
				  "PPPPPPPPPPPPPPPP" + 
				  "PPPPPPPPPPPPPPPP" + 
				  "PPPPPPPPPPPPPPPP" + 
				  "PPPPPPPPPPPPPPPP";
	
		board =ChessUtil.getBoard(boardString);
	
		rook = generateRook(new int[] {8,15}, board, "white");
		// al estar a la derecha puede ir a la izquierda
		assertTrue(rook.hide());
	}
	
	@Test
	public void canProceed() {
		String boardString = "rrhhbbqqkkbbhhrr" + 
				  "rrhhbbqqkkbbhhrr" + 
				  "pppppppppppppppp" + 
				  "pppppppppppppppp" + 
				  "                " + 
				  "                " + 
				  "                " + 
				  "p               " + 
				  "R               " + 
				  "                " + 
				  "                " + 
				  "                " + 
				  "PPPPPPPPPPPPPPPP" + 
				  "PPPPPPPPPPPPPPPP" + 
				  "PPPPPPPPPPPPPPPP" + 
				  "PPPPPPPPPPPPPPPP";

		String board[][] =ChessUtil.getBoard(boardString);
		
		RookAI rook = generateRook(new int[] {8,0}, board, "white");
		// al estar bloqueado, no puede avanzar
		assertFalse(rook.canProceed());
		
		
		
		boardString = "rrhhbbqqkkbbhhrr" + 
				  "rrhhbbqqkkbbhhrr" + 
				  "pppppppppppppppp" + 
				  "pppppppppppppppp" + 
				  "                " + 
				  "                " + 
				  "                " + 
				  "pp              " + 
				  "QR              " + 
				  "                " + 
				  "                " + 
				  "                " + 
				  "PPPPPPPPPPPPPPPP" + 
				  "PPPPPPPPPPPPPPPP" + 
				  "PPPPPPPPPPPPPPPP" + 
				  "PPPPPPPPPPPPPPPP";
		
		board =ChessUtil.getBoard(boardString);
		
		rook = generateRook(new int[] {8,0}, board, "white");
		// al estar desbloqueado puede avanzar
		assertFalse(rook.canProceed());
		
		boardString = "rrhhbbqqkkbbhhrr" + 
			  "rrhhbbqqkkbbhhrr" + 
			  "pppppppppppppppp" + 
			  "pppppppppppppppp" + 
			  "                " + 
			  "                " + 
			  "                " + 
			  "                " + 
			  "               R" + 
			  "                " + 
			  "                " + 
			  "                " + 
			  "PPPPPPPPPPPPPPPP" + 
			  "PPPPPPPPPPPPPPPP" + 
			  "PPPPPPPPPPPPPPPP" + 
			  "PPPPPPPPPPPPPPPP";
		
		board =ChessUtil.getBoard(boardString);
		
		rook = generateRook(new int[] {8,15}, board, "white");
		// al estar desbloqueado deberia devolver true
		assertTrue(rook.canProceed());
	}
	
}
