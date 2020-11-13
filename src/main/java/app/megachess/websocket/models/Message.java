package app.megachess.websocket.models;

import lombok.Data;

@Data
public class Message {
	private String action;// login, get_connected_users, challenge, accept_challenge, move
	private String event;// update_user_list, ask_challenge, your_turn, gameover
	private DataMessage data;

}
