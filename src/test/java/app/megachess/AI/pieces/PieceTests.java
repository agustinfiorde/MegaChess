package app.megachess.AI.pieces;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import app.megachess.utils.ChessUtil;

public class PieceTests {
	
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
							"      rrr       " + 
							"      r"+piece+"r       " + 
							"      rrr       " + 
							"                " + 
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
								"                " + 
								"      PPP       " + 
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

	public KingAI generateKing(int[] position, String[][] board, String color) {
		return new KingAI(position, board, color);
	}

	public BishopAI generateBishop(int[] position, String[][] board, String color, boolean dancerStrategy) {
		return new BishopAI(position, board, color, dancerStrategy);
	}

	public HorseAI generateHorse(int[] position, String[][] board, String color) {
		return new HorseAI(position, board, color);
	}

	public RookAI generateRook(int[] position, String[][] board, String color) {
		return new RookAI(position, board, color);
	}

	public PawnAI generatePawn(int[] position, String[][] board, String color) {
		return new PawnAI(position, board, color);
	}
		
	@Test
	public void toLeft() {
		
		setPieceInBoard("Q");
		
		QueenAI queen = generateQueen(new int[] {7,7}, boardEmpty, "white");
		//Deberia retornar false por que no va a haber nadie a la izquierda
		assertFalse(queen.toLeft());
		
		queen = generateQueen(new int[] {7,7}, boardLongTrajectory, "white");
		//Deberia retornar true por que a lo lejos de su izquierda hay un enemigo
		assertTrue(queen.toLeft());
		
		queen = generateQueen(new int[] {7,7}, boardNextTo, "white");
		//Deberia retornar true por que a su izquierda hay un enemigo
		assertTrue(queen.toLeft());
	}
	
	@Test
	public void toRight() {
		
		setPieceInBoard("Q");
		
		QueenAI queen = generateQueen(new int[] {7,7}, boardEmpty, "white");
		//Deberia retornar false por que no va a haber nadie a la derecha
		assertFalse(queen.toRight());
		
		queen = generateQueen(new int[] {7,7}, boardLongTrajectory, "white");
		//Deberia retornar true por que a lo lejos de su derecha hay un enemigo
		assertTrue(queen.toRight());
		
		queen = generateQueen(new int[] {7,7}, boardNextTo, "white");
		//Deberia retornar true por que a su derecha hay un enemigo
		assertTrue(queen.toRight());
	}
	
	@Test
	public void evaluateTrajectoryToTop() {
		
		setPieceInBoard("Q");
		
		QueenAI queen = generateQueen(new int[] {7,7}, boardEmpty, "white");
		//Deberia retornar true, por que hasta arriba si se puede desplazar
		assertTrue(queen.evaluateTrajectoryToTop());
		
		queen = generateQueen(new int[] {7,7}, boardLongTrajectory, "white");
		//Deberia retornar true por que a lo lejos arriba hay un enemigo
		assertTrue(queen.evaluateTrajectoryToTop());
		
		queen = generateQueen(new int[] {7,7}, boardNextTo, "white");
		//Deberia retornar true por que arriba hay un enemigo
		assertTrue(queen.evaluateTrajectoryToTop());
		
		queen = generateQueen(new int[] {7,7}, boardWithBlocks, "white");
		//Deberia retornar false por que arriba hay un compañero bloqueando
		assertFalse(queen.evaluateTrajectoryToTop());
		
	}
	
	@Test
	public void evaluateTrajectoryToBot() {
		
		setPieceInBoard("Q");
		
		QueenAI queen = generateQueen(new int[] {7,7}, boardEmpty, "white");
		//Deberia retornar true, por que hasta abajo si se puede desplazar
		assertTrue(queen.evaluateTrajectoryToBot());
		
		queen = generateQueen(new int[] {7,7}, boardLongTrajectory, "white");
		//Deberia retornar true por que a lo lejos abajo hay un enemigo
		assertTrue(queen.evaluateTrajectoryToBot());
		
		queen = generateQueen(new int[] {7,7}, boardNextTo, "white");
		//Deberia retornar true por que abajo hay un enemigo
		assertTrue(queen.evaluateTrajectoryToBot());
		
		queen = generateQueen(new int[] {7,7}, boardWithBlocks, "white");
		//Deberia retornar false por que abajo hay un compañero bloqueando
		assertFalse(queen.evaluateTrajectoryToBot());
		
	}
	
	@Test
	public void evaluateTrajectoryToLeft() {
		
		setPieceInBoard("Q");
		
		QueenAI queen = generateQueen(new int[] {7,7}, boardEmpty, "white");
		//Deberia retornar true, por que hasta la izquierda si se puede desplazar
		assertTrue(queen.evaluateTrajectoryToLeft());
		
		queen = generateQueen(new int[] {7,7}, boardLongTrajectory, "white");
		//Deberia retornar true por que a lo lejos a la izquierda hay un enemigo
		assertTrue(queen.evaluateTrajectoryToLeft());
		
		queen = generateQueen(new int[] {7,7}, boardNextTo, "white");
		//Deberia retornar true por que a la izquierda hay un enemigo
		assertTrue(queen.evaluateTrajectoryToLeft());
		
		queen = generateQueen(new int[] {7,7}, boardWithBlocks, "white");
		//Deberia retornar false por que a la izquierda hay un compañero bloqueando
		assertFalse(queen.evaluateTrajectoryToLeft());
		
	}
	
	@Test
	public void evaluateTrajectoryToRight() {
		
		setPieceInBoard("Q");
		
		QueenAI queen = generateQueen(new int[] {7,7}, boardEmpty, "white");
		//Deberia retornar true, por que hasta la derecha si se puede desplazar
		assertTrue(queen.evaluateTrajectoryToRight());
		
		queen = generateQueen(new int[] {7,7}, boardLongTrajectory, "white");
		//Deberia retornar true por que a lo lejos a la derecha hay un enemigo
		assertTrue(queen.evaluateTrajectoryToRight());
		
		queen = generateQueen(new int[] {7,7}, boardNextTo, "white");
		//Deberia retornar true por que a la derecha hay un enemigo
		assertTrue(queen.evaluateTrajectoryToRight());
		
		queen = generateQueen(new int[] {7,7}, boardWithBlocks, "white");
		//Deberia retornar false por que a la derecha hay un compañero bloqueando
		assertFalse(queen.evaluateTrajectoryToRight());
		
	}
	
	@Test
	public void evaluateTrajectoryToTopLeft() {
		
		setPieceInBoard("Q");
		
		QueenAI queen = generateQueen(new int[] {7,7}, boardEmpty, "white");
		//Deberia retornar true, por que hasta arriba a la izquierda si se puede desplazar
		assertTrue(queen.evaluateTrajectoryToTopLeft());
		
		queen = generateQueen(new int[] {7,7}, boardLongTrajectory, "white");
		//Deberia retornar true por que a lo lejos arriba a la izquierda hay un enemigo
		assertTrue(queen.evaluateTrajectoryToTopLeft());
		
		queen = generateQueen(new int[] {7,7}, boardNextTo, "white");
		//Deberia retornar true por que arriba a la izquierda hay un enemigo
		assertTrue(queen.evaluateTrajectoryToTopLeft());
		
		queen = generateQueen(new int[] {7,7}, boardWithBlocks, "white");
		//Deberia retornar false por que arriba a la izquierda hay un compañero bloqueando
		assertFalse(queen.evaluateTrajectoryToTopLeft());
		
	}
	
	@Test
	public void evaluateTrajectoryToTopRight() {
		
		setPieceInBoard("Q");
		
		QueenAI queen = generateQueen(new int[] {7,7}, boardEmpty, "white");
		//Deberia retornar true, por que hasta arriba a la derecha si se puede desplazar
		assertTrue(queen.evaluateTrajectoryToTopRight());
		
		queen = generateQueen(new int[] {7,7}, boardLongTrajectory, "white");
		//Deberia retornar true por que a lo lejos arriba a la derecha hay un enemigo
		assertTrue(queen.evaluateTrajectoryToTopRight());
		
		queen = generateQueen(new int[] {7,7}, boardNextTo, "white");
		//Deberia retornar true por que arriba a la derecha hay un enemigo
		assertTrue(queen.evaluateTrajectoryToTopRight());
		
		queen = generateQueen(new int[] {7,7}, boardWithBlocks, "white");
		//Deberia retornar false por que arriba a la derecha hay un compañero bloqueando
		assertFalse(queen.evaluateTrajectoryToTopRight());
		
	}
	
	@Test
	public void evaluateTrajectoryToBotLeft() {
		
		setPieceInBoard("Q");
		
		QueenAI queen = generateQueen(new int[] {7,7}, boardEmpty, "white");
		//Deberia retornar true, por que hasta abajo a la izquierda si se puede desplazar
		assertTrue(queen.evaluateTrajectoryToBotLeft());
		
		queen = generateQueen(new int[] {7,7}, boardLongTrajectory, "white");
		//Deberia retornar true por que a lo lejos abajo a la izquierda hay un enemigo
		assertTrue(queen.evaluateTrajectoryToBotLeft());
		
		queen = generateQueen(new int[] {7,7}, boardNextTo, "white");
		//Deberia retornar true por que abajo a la izquierda hay un enemigo
		assertTrue(queen.evaluateTrajectoryToBotLeft());
		
		queen = generateQueen(new int[] {7,7}, boardWithBlocks, "white");
		//Deberia retornar false por que abajo a la izquierda hay un compañero bloqueando
		assertFalse(queen.evaluateTrajectoryToBotLeft());
		
	}
	
	@Test
	public void evaluateTrajectoryToBotRight() {
		
		setPieceInBoard("Q");
		
		QueenAI queen = generateQueen(new int[] {7,7}, boardEmpty, "white");
		//Deberia retornar true, por que hasta abajo a la derecha si se puede desplazar
		assertTrue(queen.evaluateTrajectoryToBotRight());
		
		queen = generateQueen(new int[] {7,7}, boardLongTrajectory, "white");
		//Deberia retornar true por que a lo lejos abajo a la derecha hay un enemigo
		assertTrue(queen.evaluateTrajectoryToBotRight());
		
		queen = generateQueen(new int[] {7,7}, boardNextTo, "white");
		//Deberia retornar true por que abajo a la derecha hay un enemigo
		assertTrue(queen.evaluateTrajectoryToBotRight());
		
		queen = generateQueen(new int[] {7,7}, boardWithBlocks, "white");
		//Deberia retornar false por que abajo a la derecha hay un compañero bloqueando
		assertFalse(queen.evaluateTrajectoryToBotRight());
		
	}
	
	@Test
	public void evaluateTop() {
		setPieceInBoard("Q");
		
		QueenAI queen = generateQueen(new int[] {7,7}, boardEmpty, "white");
		//Deberia retornar false, por que en el casillero de arriba no hay enemigos presentes
		assertFalse(queen.evaluateTop());
		
		queen = generateQueen(new int[] {7,7}, boardLongTrajectory, "white");
		//Deberia retornar false, por que en el casillero de arriba no hay enemigos presentes
		assertFalse(queen.evaluateTop());
		
		queen = generateQueen(new int[] {7,7}, boardNextTo, "white");
		//Deberia retornar true, por que en el casillero de arriba hay un enemigo
		assertTrue(queen.evaluateTop());
		
		queen = generateQueen(new int[] {7,7}, boardWithBlocks, "white");
		//Deberia retornar false, por que en el casillero de arriba hay un compañero bloqueando
		assertFalse(queen.evaluateTop());		
	}
	
	@Test
	public void evaluateBot() {
		setPieceInBoard("Q");
		
		QueenAI queen = generateQueen(new int[] {7,7}, boardEmpty, "white");
		//Deberia retornar false, por que en el casillero de abajo no hay enemigos presentes
		assertFalse(queen.evaluateBot());
		
		queen = generateQueen(new int[] {7,7}, boardLongTrajectory, "white");
		//Deberia retornar false, por que en el casillero de abajo no hay enemigos presentes
		assertFalse(queen.evaluateBot());
		
		queen = generateQueen(new int[] {7,7}, boardNextTo, "white");
		//Deberia retornar true, por que en el casillero de abajo hay un enemigo
		assertTrue(queen.evaluateBot());
		
		queen = generateQueen(new int[] {7,7}, boardWithBlocks, "white");
		//Deberia retornar false, por que en el casillero de abajo hay un compañero bloqueando
		assertFalse(queen.evaluateBot());
	}
	
	@Test
	public void evaluateLeft() {
		setPieceInBoard("Q");
		
		QueenAI queen = generateQueen(new int[] {7,7}, boardEmpty, "white");
		//Deberia retornar false, por que en el casillero de la izquierda no hay enemigos presentes
		assertFalse(queen.evaluateLeft());
		
		queen = generateQueen(new int[] {7,7}, boardLongTrajectory, "white");
		//Deberia retornar false, por que en el casillero de la izquierda no hay enemigos presentes
		assertFalse(queen.evaluateLeft());
		
		queen = generateQueen(new int[] {7,7}, boardNextTo, "white");
		//Deberia retornar true, por que en el casillero de la izquierda  hay un enemigo
		assertTrue(queen.evaluateLeft());
		
		queen = generateQueen(new int[] {7,7}, boardWithBlocks, "white");
		//Deberia retornar false, por que en el casillero de la izquierda hay un compañero bloqueando
		assertFalse(queen.evaluateLeft());
	}
	
	@Test
	public void evaluateRight() {
		setPieceInBoard("Q");
		
		QueenAI queen = generateQueen(new int[] {7,7}, boardEmpty, "white");
		//Deberia retornar false, por que en el casillero de la derecha no hay enemigos presentes
		assertFalse(queen.evaluateRight());
		
		queen = generateQueen(new int[] {7,7}, boardLongTrajectory, "white");
		//Deberia retornar false, por que en el casillero de la derecha no hay enemigos presentes
		assertFalse(queen.evaluateRight());
		
		queen = generateQueen(new int[] {7,7}, boardNextTo, "white");
		//Deberia retornar true, por que en el casillero de la derecha  hay un enemigo
		assertTrue(queen.evaluateRight());
		
		queen = generateQueen(new int[] {7,7}, boardWithBlocks, "white");
		//Deberia retornar false, por que en el casillero de la derecha hay un compañero bloqueando
		assertFalse(queen.evaluateRight());
	}
	
	@Test
	public void evaluateTopLeft() {
		setPieceInBoard("Q");
		
		QueenAI queen = generateQueen(new int[] {7,7}, boardEmpty, "white");
		//Deberia retornar false, por que en el casillero de arriba a la izquierda no hay enemigos presentes
		assertFalse(queen.evaluateTopLeft());
		
		queen = generateQueen(new int[] {7,7}, boardLongTrajectory, "white");
		//Deberia retornar false, por que en el casillero de arriba a la izquierda no hay enemigos presentes
		assertFalse(queen.evaluateTopLeft());
		
		queen = generateQueen(new int[] {7,7}, boardNextTo, "white");
		//Deberia retornar true, por que en el casillero de arriba a la izquierda hay un enemigo
		assertTrue(queen.evaluateTopLeft());
		
		queen = generateQueen(new int[] {7,7}, boardWithBlocks, "white");
		//Deberia retornar false, por que en el casillero de arriba a la izquierda hay un compañero bloqueando
		assertFalse(queen.evaluateTopLeft());
	}
	
	@Test
	public void evaluateBotLeft() {
		setPieceInBoard("Q");
		
		QueenAI queen = generateQueen(new int[] {7,7}, boardEmpty, "white");
		//Deberia retornar false, por que en el casillero de abajo a la izquierda no hay enemigos presentes
		assertFalse(queen.evaluateBotLeft());
		
		queen = generateQueen(new int[] {7,7}, boardLongTrajectory, "white");
		//Deberia retornar false, por que en el casillero de abajo a la izquierda no hay enemigos presentes
		assertFalse(queen.evaluateBotLeft());
		
		queen = generateQueen(new int[] {7,7}, boardNextTo, "white");
		//Deberia retornar true, por que en el casillero de abajo a la izquierda hay un enemigo
		assertTrue(queen.evaluateBotLeft());
		
		queen = generateQueen(new int[] {7,7}, boardWithBlocks, "white");
		//Deberia retornar false, por que en el casillero de abajo a la izquierda hay un compañero bloqueando
		assertFalse(queen.evaluateBotLeft());
	}
	
	@Test
	public void evaluateTopRight() {
		setPieceInBoard("Q");
		
		QueenAI queen = generateQueen(new int[] {7,7}, boardEmpty, "white");
		//Deberia retornar false, por que en el casillero de arriba a la derecha no hay enemigos presentes
		assertFalse(queen.evaluateTopRight());
		
		queen = generateQueen(new int[] {7,7}, boardLongTrajectory, "white");
		//Deberia retornar false, por que en el casillero de arriba a la derecha no hay enemigos presentes
		assertFalse(queen.evaluateTopRight());
		
		queen = generateQueen(new int[] {7,7}, boardNextTo, "white");
		//Deberia retornar true, por que en el casillero de arriba a la derecha hay un enemigo
		assertTrue(queen.evaluateTopRight());
		
		queen = generateQueen(new int[] {7,7}, boardWithBlocks, "white");
		//Deberia retornar false, por que en el casillero de arriba a la derecha hay un compañero bloqueando
		assertFalse(queen.evaluateTopRight());
	}
	
	@Test
	public void evaluateBotRight() {
		setPieceInBoard("Q");
		
		QueenAI queen = generateQueen(new int[] {7,7}, boardEmpty, "white");
		//Deberia retornar false, por que en el casillero de abajo a la derecha no hay enemigos presentes
		assertFalse(queen.evaluateBotRight());
		
		queen = generateQueen(new int[] {7,7}, boardLongTrajectory, "white");
		//Deberia retornar false, por que en el casillero de abajo a la derecha no hay enemigos presentes
		assertFalse(queen.evaluateBotRight());
		
		queen = generateQueen(new int[] {7,7}, boardNextTo, "white");
		//Deberia retornar true, por que en el casillero de abajo a la derecha hay un enemigo
		assertTrue(queen.evaluateBotRight());
		
		queen = generateQueen(new int[] {7,7}, boardWithBlocks, "white");
		//Deberia retornar false, por que en el casillero de abajo a la derecha hay un compañero bloqueando
		assertFalse(queen.evaluateBotRight());
	}
	
	@Test
	public void evaluateQuadrant() {
		setPieceInBoard("Q");
		
		QueenAI queen = generateQueen(new int[] {7,7}, boardEmpty, "white");
		//es un casillero vacio, devuelve false
		assertFalse(queen.evaluateQuadrant(8,8));
		
		queen = generateQueen(new int[] {7,7}, boardLongTrajectory, "white");
		//en un casillero con un enemigo a la larga distancia
		assertTrue(queen.evaluateQuadrant(0,1));
		
		queen = generateQueen(new int[] {7,7}, boardNextTo, "white");
		//en un casillero con un enemigo a la corta distancia
		assertTrue(queen.evaluateQuadrant(8,8));
		
		queen = generateQueen(new int[] {7,7}, boardWithBlocks, "white");
		//es un casillero con un aliado, devuelve false
		assertFalse(queen.evaluateQuadrant(8,8));
	}
	
	@Test
	public void targetToHunt() {
		setPieceInBoard("Q");
		
		QueenAI queen = generateQueen(new int[] {7,7}, boardEmpty, "white");
		//Como no existen enemigos devuelve null
		assertEquals(null, queen.targetToHunt());
		
		queen = generateQueen(new int[] {7,7}, boardLongTrajectory, "white");
		//como hay almenos un enemigo devuelve un Response **alejado de la posicion
		assertNotEquals(null, queen.targetToHunt());
		
		queen = generateQueen(new int[] {7,7}, boardNextTo, "white");
		//como hay almenos un enemigo devuelve un Response **proximo a la posicion
		assertNotEquals(null, queen.targetToHunt());
		
		queen = generateQueen(new int[] {7,7}, boardWithBlocks, "white");
		//como no existen enemigos pero si aliados devuelve null
		assertEquals(null, queen.targetToHunt());
	}
	
}
