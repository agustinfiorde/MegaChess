package app.megachess.AI.pieces;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import app.megachess.enums.Event;
import app.megachess.models.DataMessage;
import app.megachess.models.Message;
import app.megachess.utils.ChessUtil;

public class QueenAITests {

	public String[][] boardEmpty;
	public String[][] boardLongTrajectory;
	public String[][] boardNextTo;
	public String[][] boardWithBlocks;
	
	public Message messageGenerator(String boardString) {

		Message msj = new Message();
		msj.setEvent(Event.YOUR_TURN.toString());
		DataMessage msjData = new DataMessage();
		msjData.setBoard_id("2d348323-2e79-4961-ac36-1b000e8c42a5");
		msjData.setTurn_token("2d348323-2e79-4961-ac36-1b000e8token");
		msjData.setUsername("fiordeX");
		msjData.setActual_turn("white");
		msjData.setBoard(boardString);
		msjData.setMove_left(199);
		msj.setData(msjData);

		return msj;
	}
	
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
	public void hide() {

		String boardString = "rrhhbbqqkkbbhhrr" + 
							  "rrhhbbqqkkbbhhrr" + 
							  "pppppppppppppppp" + 
							  "pppppppppppppppp" + 
							  "                " + 
							  "                " + 
							  "                " + 
							  "                " + 
							  "Q               " + 
							  "                " + 
							  "                " + 
							  "                " + 
							  "PPPPPPPPPPPPPPPP" + 
							  "PPPPPPPPPPPPPPPP" + 
							  "PPPPPPPPPPPPPPPP" + 
							  "PPPPPPPPPPPPPPPP";
		
		String board[][] =ChessUtil.getBoard(boardString);

		QueenAI queen = generateQueen(new int[] {8,0}, board, "white");
		// al estar a la izquierda, no puede ir mas a la izquierda
		assertFalse(queen.hide());
		
		

		boardString = "rrhhbbqqkkbbhhrr" + 
					  "rrhhbbqqkkbbhhrr" + 
					  "pppppppppppppppp" + 
					  "pppppppppppppppp" + 
					  "                " + 
					  "                " + 
					  "                " + 
					  "                " + 
					  "QQ              " + 
					  "                " + 
					  "                " + 
					  "                " + 
					  "PPPPPPPPPPPPPPPP" + 
					  "PPPPPPPPPPPPPPPP" + 
					  "PPPPPPPPPPPPPPPP" + 
					  "PPPPPPPPPPPPPPPP";
		
		board =ChessUtil.getBoard(boardString);

		queen = generateQueen(new int[] {8,0}, board, "white");
		// al estar a la izquierda, no puede ir mas a la izquierda
		assertFalse(queen.hide());
		
		boardString = "rrhhbbqqkkbbhhrr" + 
				  "rrhhbbqqkkbbhhrr" + 
				  "pppppppppppppppp" + 
				  "pppppppppppppppp" + 
				  "                " + 
				  "                " + 
				  "                " + 
				  "                " + 
				  "QQ             Q" + 
				  "                " + 
				  "                " + 
				  "                " + 
				  "PPPPPPPPPPPPPPPP" + 
				  "PPPPPPPPPPPPPPPP" + 
				  "PPPPPPPPPPPPPPPP" + 
				  "PPPPPPPPPPPPPPPP";
	
		board =ChessUtil.getBoard(boardString);
	
		queen = generateQueen(new int[] {8,15}, board, "white");
		// al estar a la derecha puede ir a la izquierda
		assertTrue(queen.hide());
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
				  "pp              " + 
				  "Q               " + 
				  "                " + 
				  "                " + 
				  "                " + 
				  "PPPPPPPPPPPPPPPP" + 
				  "PPPPPPPPPPPPPPPP" + 
				  "PPPPPPPPPPPPPPPP" + 
				  "PPPPPPPPPPPPPPPP";

		String board[][] =ChessUtil.getBoard(boardString);
		
		QueenAI queen = generateQueen(new int[] {8,0}, board, "white");
		// al estar bloqueado, no puede avanzar
		assertFalse(queen.canProceed());
		
		
		
		boardString = "rrhhbbqqkkbbhhrr" + 
				  "rrhhbbqqkkbbhhrr" + 
				  "pppppppppppppppp" + 
				  "pppppppppppppppp" + 
				  "                " + 
				  "                " + 
				  "                " + 
				  "ppp             " + 
				  "QQ              " + 
				  "                " + 
				  "                " + 
				  "                " + 
				  "PPPPPPPPPPPPPPPP" + 
				  "PPPPPPPPPPPPPPPP" + 
				  "PPPPPPPPPPPPPPPP" + 
				  "PPPPPPPPPPPPPPPP";
		
		board =ChessUtil.getBoard(boardString);
		
		queen = generateQueen(new int[] {8,0}, board, "white");
		// al estar desbloqueado puede avanzar
		assertFalse(queen.canProceed());
		
		boardString = "rrhhbbqqkkbbhhrr" + 
			  "rrhhbbqqkkbbhhrr" + 
			  "pppppppppppppppp" + 
			  "pppppppppppppppp" + 
			  "                " + 
			  "                " + 
			  "                " + 
			  "                " + 
			  "                " + 
			  "               Q" + 
			  "                " + 
			  "                " + 
			  "PPPPPPPPPPPPPPPP" + 
			  "PPPPPPPPPPPPPPPP" + 
			  "PPPPPPPPPPPPPPPP" + 
			  "PPPPPPPPPPPPPPPP";
		
		board =ChessUtil.getBoard(boardString);
		
		queen = generateQueen(new int[] {9,15}, board, "white");
		// al poder avanzar devolvera true
		assertTrue(queen.canProceed());
	}
}
