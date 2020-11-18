package app.megachess.AI;

import app.megachess.websocket.models.DataMessage;
import app.megachess.websocket.models.Message;

public class Intelligence {

	public static int startMoves = 200;

	public static void evaluate(Message msj) {

		DataMessage msjData = msj.getData();

		boolean myTurn = msj.getEvent().equals("your_turn");

		if (myTurn) {

		} else {

		}

		String color = (myTurn && msjData.getActual_turn().equals("white")) ? "white" : "black";

		/*
		 * - Empezando usar move_left == 200 estoy empezando + - Evaluo si soy Blanco o
		 * Negro - Si soy blanco o negro ataco por uno de los 4 caballos (es decir hago
		 * avanzar mi peon por ahi)
		 * 
		 * - Evaluo lineas defensivas, si esta ok hago que el asesino penetre - Si muere
		 * asesino hago avanzar otro peon si no hay mas peones, saco una reina, si no
		 * hay mas reinas, saco torres
		 * 
		 */
		if ((msjData.getMove_left() == startMoves || msjData.getMove_left() == startMoves - 1) && myTurn) {
			if (msjData.getActual_turn().equals("white")) {
				// avanzar
			} else {
				// evaluar primer avance y no encarar por el mismo lado pensar que hacer en el
				// caso de que perdio el turno o avanzo un solo paso

				// metodo ya listo para evaluar el primer movimiento del blanco
			}
		}

	}

	public static void evaluateStart(String board, String color) {

	}

	public static void evaluateCourse(String board, String color) {

	}

	public static int[] decision() {

		int[] values = new int[4];

		return values;
	}

}
