package app.megachess.models;

import java.util.List;

import lombok.Data;

@Data
public class DataMessage {

	private String board_id;
	private String turn_token;
	private String username;
	private String actual_turn;
	private String board;
	private Integer move_left;

	private String opponent_username;

	private List<String> users_list;
	private String message;

	private Integer from_row;
	private Integer from_col;
	private Integer to_row;
	private Integer to_col;

	private String white_username;
	private String black_username;
	private String white_score;
	private String black_score;

	@Override
	public String toString() {

		return (" [ " + "board_id=" + board_id + ", turn_token=" + turn_token + ", username=" + username
				+ ", actual_turn=" + actual_turn + ", board=" + board + ", move_left=" + move_left
				+ ", opponent_username=" + opponent_username + ", users_list=" + users_list + ", message=" + message
				+ ", from_row=" + from_row + ", from_col=" + from_col + ", to_row=" + to_row + ", to_col=" + to_col
				+ ", white_username=" + white_username + ", black_username=" + black_username + ", white_score="
				+ white_score + ", black_score=" + black_score + "]")
						.replaceAll("(?<=(, |\\())[^\\s(]+?=null(?:, )?", "").replaceFirst(", \\)$", ")");

	}

	// {"users_list":[“gabriel”, “juan”]},
	// {
	// “username”: ”gabriel”,
	// “message”: “mensaje para el otro usuario”
	// },
	// {
	// "username":"gabriel",
	// "board_id":"2d348323-2e79-4961-ac36-1b000e8c42a5"
	// }
	// {
	// “board_id”: "2d348323-2e79-4961-ac36-1b000e8c42a5"
	// }
	// {
	// "board_id":"2d348323-2e79-4961-ac36-1b000e8c42a5",
	// "turn_token":"e40573bb-138f-4171-a200-66258f546755",
	// "username":"gabriel",
	// "actual_turn":"white",
	// "board":"rrhhbbqqkkbbhhrrrrhhbbqqkkbbhhrrpppppppppppppppppppppppppppppppp P
	// PPPPPPPP PPPPPPPPPPPPPPPPPPPPPPPRRHHBBQQKKBBHHRRRRHHBBQQKKBBHHRR",
	// "move_left":19
	// }
	// {
	// “board_id”: ”2d348323-2e79-4961-ac36-1b000e8c42a5”,
	// “turn_token: ”e40573bb-138f-4171-a200-66258f546755”,
	// “from_row”: 1,
	// “from_col”: 1,
	// “to_row”: 3,
	// “to_col”: 1
	// }
	// {
	// "board":"B*1234567890123456*\n1|rrhhbbqqkkbbhhrr|\n2|rrhhbbqqkkbbhhrr|\n3|pppppppppppppppp|\n4|pppppppp
	// ppppppp|\n5| p |\n6| |\n7| |\n8| |\n9| |\n0| |\n1| P|\n2| P P |\n3|PPPPP P
	// PPPPPPP
	// |\n4|PPPPPPPPPPPPPPPP|\n5|RRHHBBQQKKBBHHRR|\n6|RRHHBBQQKKBBHHRR|\nW*----------------*\n",
	// "white_username":"gabriel",
	// "black_username":"pedro",
	// "white_score":"28",
	// "black_score":"-6"
	// }

}
