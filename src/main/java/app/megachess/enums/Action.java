package app.megachess.enums;

/*
 * Actions from the websocket
 */
public enum Action {

	LOGIN("login"), GET_CONNECTED_USERS("get_connected_users"), CHALLENGE("challenge"),
	ACCEPT_CHALLENGE("accept_challenge"), MOVE("move");

	private final String string;

	Action(String string) {
		this.string = string;
	}

	public String getString() {
		return string;
	}

}
