package app.megachess.enums;

/*
 * Events from the websocket
 */
public enum Event {

	UPDATE_USER_LIST("update_user_list"), ASK_CHALLENGE("ask_challenge"), YOUR_TURN("your_turn"), GAMEOVER("gameover");

	private final String string;

	Event(String string) {
		this.string = string;
	}

	public String getString() {
		return string;
	}
}
