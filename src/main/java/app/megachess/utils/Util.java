package app.megachess.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import app.megachess.enums.Action;
import app.megachess.models.DataMessage;
import app.megachess.models.Message;

/**
 * Utilidades generales
 * 
 * @author AsteriX
 *
 */
public class Util {

	/**
	 * objectToJSON, toma un Objeto para convertirlo en un String con formato JSON
	 * 
	 * @param obj
	 * @return
	 */
	public static String objectToJSON(Message obj) {
		Gson GSON = new GsonBuilder().setPrettyPrinting().create();
		return GSON.toJson(obj, Message.class);
	}

	/**
	 * JSONToObject toma un String con formato JSON para convertirlo en un Objeto
	 * 
	 * @param json
	 * @return
	 */
	public static Message JSONToObject(String json) {
		Gson GSON = new GsonBuilder().serializeNulls().create();
		return GSON.fromJson(json, Message.class);
	}

	/**
	 * getConnectedUsers, arma un JSON para solicitar los usuarios conectados, para
	 * mas informacion ver la documentacion del WebSocket
	 * 
	 * @return
	 */
	public static String getConnectedUsers() {
		Message msj = new Message();
		DataMessage dataMsj = new DataMessage();
		msj.setAction(Action.GET_CONNECTED_USERS.getString());
		msj.setData(dataMsj);
		return objectToJSON(msj);
	}

	/**
	 * challenge, arma un JSON para desafiar a un usuario, este usuario es proveido
	 * como argumento de la funcion, para mas informacion ver la documentacion del
	 * WebSocket
	 * 
	 * @param oponent
	 * @return
	 */
	public static String challenge(String oponent) {
		Message msj = new Message();
		DataMessage dataMsj = new DataMessage();
		dataMsj.setUsername(oponent);
		dataMsj.setMessage("Queres que juguemos una partida?");
		msj.setAction(Action.CHALLENGE.getString());
		msj.setData(dataMsj);
		return objectToJSON(msj);
	}

	/**
	 * acceptChallenge, arma un JSON para aceptar cualquier solicitus entrante, para
	 * responder necesita como argumento el id del tablero, para mas informacion ver
	 * la documentacion del WebSocket
	 * 
	 * @param board_id
	 * @return
	 */
	public static String acceptChallenge(String board_id) {
		Message msj = new Message();
		DataMessage dataMsj = new DataMessage();
		msj.setAction(Action.ACCEPT_CHALLENGE.getString());
		dataMsj.setBoard_id(board_id);
		msj.setData(dataMsj);
		return objectToJSON(msj);
	}

	/**
	 * gameover, arma un JSON para mostrar por consola que la partida ya termino.
	 * **** este metodo daria pie a persistir los datos si en un futuro se desea
	 * conectar la app con una base de datos, para mas informacion ver la
	 * documentacion del WebSocket
	 * 
	 * @param req
	 * @return
	 */
	public static String gameover(Message req) {

		String message = objectToJSON(req);

		return message;
	}

	/**
	 * move, se encarga de armar los mensajes de respuestas, para responder al
	 * socket que movimiento decidi hacer, para mas informacion ver la documentacion
	 * del WebSocket
	 * 
	 * @param req
	 * @param fromRow
	 * @param fromCol
	 * @param toRow
	 * @param toCol
	 * @return
	 */
	public static String move(Message req, int fromRow, int fromCol, int toRow, int toCol) {
		Message msj = new Message();
		DataMessage dataMsj = new DataMessage();
		msj.setAction("move");
		dataMsj.setBoard_id(req.getData().getBoard_id());
		dataMsj.setTurn_token(req.getData().getTurn_token());

		dataMsj.setFrom_row(fromRow);
		dataMsj.setFrom_col(fromCol);
		dataMsj.setTo_row(toRow);
		dataMsj.setTo_col(toCol);

		msj.setData(dataMsj);
		return objectToJSON(msj);
	}
}
