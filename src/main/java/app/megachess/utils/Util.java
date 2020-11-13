package app.megachess.utils;

import com.google.gson.Gson;

import app.megachess.websocket.models.Message;

public class Util {

	public static String objectToJSON(Object obj) {
		return new Gson().toJson(obj);
	}

	public static Message JSONToObject(String json) {
		return new Gson().fromJson(json, Message.class);
	}

	public static void test() {

		String json = "{\r\n" + 
				"\"event\":\"your_turn\",\r\n" + 
				"\"data\":{\r\n" + 
				"\"board_id\":\"2d348323-2e79-4961-ac36-1b000e8c42a5\",\r\n" + 
				"\"turn_token\":\"e40573bb-138f-4171-a200-66258f546755\",\r\n" + 
				"\"username\":\"gabriel\",\r\n" + 
				"\"actual_turn\":\"white\",\r\n" + 
				"\"board\":\"rrhhbbqqkkbbhhrrrrhhbbqqkkbbhhrrpppppppppppppppppppppppppppppppp                                                                                                                        P       PPPPPPPP PPPPPPPPPPPPPPPPPPPPPPPRRHHBBQQKKBBHHRRRRHHBBQQKKBBHHRR\",\r\n" + 
				"\"move_left\":19\r\n" + 
				"}\r\n" + 
				"}\r\n" + 
				"";

		
		Message m =JSONToObject(json);
		
		System.out.println(m);
		System.out.println(objectToJSON(m));

	}
}

