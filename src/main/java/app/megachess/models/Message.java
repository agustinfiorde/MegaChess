package app.megachess.models;

import lombok.Data;

@Data
public class Message {
	private String action;// login, get_connected_users, challenge, accept_challenge, move
	private String event;// update_user_list, ask_challenge, your_turn, gameover
	private DataMessage data;
	
	@Override
	public String toString() {
		
		if (action!=null) {
			return ("Message [ " +"action=" + action + ", event=" + event + ", data=" + data + "]")
					.replaceAll("(?<=(, |\\())[^\\s(]+?=null(?:, )?", "")
		            .replaceFirst(", \\)$", ")");
		} else {
			return ("Message [ " + "event=" + event + ", data=" + data + "]")
					.replaceAll("(?<=(, |\\())[^\\s(]+?=null(?:, )?", "")
		            .replaceFirst(", \\)$", ")");
		}
	}
	
}
