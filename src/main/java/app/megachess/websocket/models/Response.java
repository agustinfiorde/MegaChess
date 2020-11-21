package app.megachess.websocket.models;

import lombok.Data;

@Data
public class Response {

	private boolean exist;
	private int fromRow;
	private int fromCol;
	
	private String piece;
	
}
