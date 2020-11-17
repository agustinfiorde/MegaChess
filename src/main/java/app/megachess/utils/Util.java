package app.megachess.utils;

import com.google.gson.Gson;

import app.megachess.websocket.models.Message;

public class Util {
	
	private Util() {
		
	}

	public static String objectToJSON(Object obj) {
		return new Gson().toJson(obj);
	}

	public static Message JSONToObject(String json) {
		return new Gson().fromJson(json, Message.class);
	}

}

