package app.megachess.utils;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;

import app.megachess.enums.Action;
import app.megachess.enums.Event;
import app.megachess.models.DataMessage;
import app.megachess.models.Message;

public class UtilTests {

	public boolean isJSONValid(String obj) {
		try {
			new JSONObject(obj);
		} catch (JSONException ex) {
			try {
				new JSONArray(obj);
			} catch (JSONException ex1) {
				return false;
			}
		}
		return true;
	}

	@Test
	public void objectToJSON() {
		String jsonWithError = "{\r\n" + "  \"action: \"challenge\",\r\n" + "  \"data\": {\r\n"
				+ "    \"username\": \"fiordeX\",\r\n" + "    \"message\": \"mensaje para el otro usuario\"\r\n"
				+ "  }\r\n" + "}";
		Message obj = new Message();
		DataMessage data = new DataMessage();
		obj.setAction(Action.CHALLENGE.getString());
		data.setUsername("fiordeX");
		data.setMessage("mensaje para el otro usuario");
		obj.setData(data);

		String res = Util.objectToJSON(obj);

		assertTrue(isJSONValid(res));
		assertFalse(isJSONValid(jsonWithError));
	}

	@Test
	public void JSONToObject() {
		String json = "{\r\n" + "  \"action\": \"challenge\",\r\n" + "  \"data\": {\r\n"
				+ "    \"username\": \"fiordeX\",\r\n" + "    \"message\": \"mensaje para el otro usuario\"\r\n"
				+ "  }\r\n" + "}";
		Message actual = new Message();
		DataMessage data = new DataMessage();
		actual.setAction(Action.CHALLENGE.getString());
		data.setUsername("fiordeX");
		data.setMessage("mensaje para el otro usuario");
		actual.setData(data);

		Message expected = Util.JSONToObject(json);

		assertEquals(expected, actual);
		assertNotEquals(new Message(), actual, "Are'n the same");
	}

	@Test
	public void getConnectedUsers() {
		assertTrue(isJSONValid(Util.getConnectedUsers()));
	}

	@Test
	public void challenge() {
		assertTrue(isJSONValid(Util.challenge("fiordeX")));
	}

	@Test
	public void acceptChallenge() {
		assertTrue(isJSONValid(Util.acceptChallenge("1h431l2h4k3k123")));
	}

	@Test
	public void gameover() {
		Message msj = new Message();
		DataMessage data = new DataMessage();
		msj.setEvent(Event.GAMEOVER.getString());
		data.setBoard(
				"\"B*1234567890123456*\\n1|rrhhbbqqkkbbhhrr|\\n2|rrhhbbqqkkbbhhrr|\\n3|pppppppppppppppp|\\n4|pppppppp ppppppp|\\n5|        p       |\\n6|                |\\n7|                |\\n8|                |\\n9|                |\\n0|                |\\n1|               P|\\n2|     P P        |\\n3|PPPPP P PPPPPPP |\\n4|PPPPPPPPPPPPPPPP|\\n5|RRHHBBQQKKBBHHRR|\\n6|RRHHBBQQKKBBHHRR|\\nW*----------------*\\n\"");
		data.setMessage("mensaje para el otro usuario");
		data.setWhite_username("gabriel");
		data.setWhite_score("28");
		data.setBlack_username("pedro");
		data.setBlack_score("-6");

		assertTrue(isJSONValid(Util.gameover(msj)));
	}

	@Test
	public void move() {
		int fromRow = 0;
		int fromCol = 0;
		int toRow = 1;
		int toCol = 1;
		Message msj = new Message();
		DataMessage data = new DataMessage();
		data.setBoard_id("2d348323-2e79-4961-ac36-1b000e8c42a5");
		data.setTurn_token("2d348323-2e79-4961-ac36-1b000e8c42a5");
		msj.setData(data);

		assertTrue(isJSONValid(Util.move(msj, fromRow, fromCol, toRow, toCol)));
	}

}
