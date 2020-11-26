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
		String jsonWithError = "{\r\n" + 
				"  \"action: \"challenge\",\r\n" + 
				"  \"data\": {\r\n" + 
				"    \"username\": \"fiordeX\",\r\n" + 
				"    \"message\": \"mensaje para el otro usuario\"\r\n" + 
				"  }\r\n" + 
				"}";
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
		String json = "{\r\n" + 
				"  \"action\": \"challenge\",\r\n" + 
				"  \"data\": {\r\n" + 
				"    \"username\": \"fiordeX\",\r\n" + 
				"    \"message\": \"mensaje para el otro usuario\"\r\n" + 
				"  }\r\n" + 
				"}";
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
	
	

}
