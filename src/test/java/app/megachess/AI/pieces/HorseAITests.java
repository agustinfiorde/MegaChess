package app.megachess.AI.pieces;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import app.megachess.utils.ChessUtil;

public class HorseAITests {
	
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

	public HorseAI generateHorse(int[] position, String[][] board, String color) {
		return new HorseAI(position, board, color);
	}

	@Test
	public void canProceed() {

		setPieceInBoard("H");
		
		HorseAI horse = generateHorse(new int[] {7,7}, boardEmpty, "white");
		//Ante un tablero totalmente vacio puede avanzar
		assertTrue(horse.canProceed());
		
		horse = generateHorse(new int[] {7,7}, boardNextTo, "white");
		//Ante enemigos en frente, significa que no puede avanzar, por lo tanto retorna false
		assertFalse(horse.canProceed());
		
		horse = generateHorse(new int[] {7,7}, boardWithBlocks, "white");
		//Ante aliados en frente, significa que no puede avanzar, por lo tanto retorna false
		assertFalse(horse.canProceed());
	}
	
	@Test
	public void canDefend() {

		setPieceInBoard("H");
		
		HorseAI horse = generateHorse(new int[] {7,7}, boardEmpty, "white");
		//Ante un tablero totalmente vacio no tiene que defender, retorna false
		assertFalse(horse.canDefend());
		
		horse = generateHorse(new int[] {7,7}, boardNextTo, "white");
		//Ante enemigos en sus potenciales casillas de movimiento retornar true
		assertTrue(horse.canDefend());
		
		horse = generateHorse(new int[] {7,7}, boardWithBlocks, "white");
		//Ante aliados en sus potenciales casillas de movilidad no podra moverse y retorna false
		assertFalse(horse.canDefend());
	}
}
