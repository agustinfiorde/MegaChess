package app.megachess.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import app.megachess.enums.Action;
import app.megachess.websocket.models.DataMessage;
import app.megachess.websocket.models.Message;

public class Util {

	private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();

	public static String objectToJSON(Message obj) {
		return GSON.toJson(obj, Message.class);
	}

	public static Message JSONToObject(String json) {
		return new Gson().fromJson(json, Message.class);
	}

	public static String getConnectedUsers() {
		Message msj = new Message();
		DataMessage dataMsj = new DataMessage();
		msj.setAction(Action.GET_CONNECTED_USERS.getString());
		msj.setData(dataMsj);
		return objectToJSON(msj);
	}

	public static String challenge(String oponent) {
		Message msj = new Message();
		DataMessage dataMsj = new DataMessage();
		dataMsj.setUsername(oponent);
		dataMsj.setMessage("Queres que juguemos una partida?");
		msj.setAction(Action.CHALLENGE.getString());
		msj.setData(dataMsj);
		return objectToJSON(msj);
	}

	public static String acceptChallenge(String board_id) {
		Message msj = new Message();
		DataMessage dataMsj = new DataMessage();
		msj.setAction(Action.ACCEPT_CHALLENGE.getString());
		dataMsj.setBoard_id(board_id);
		msj.setData(dataMsj);
		return objectToJSON(msj);
	}

	public static String gameover(Message req) {

		String message = objectToJSON(req);

		return message;
	}

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
