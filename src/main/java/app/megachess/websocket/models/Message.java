package app.megachess.websocket.models;

import lombok.Data;

@Data
public class Message {
	private String action;//login, get_connected_users, challenge, accept_challenge, move
	private String event;//update_user_list, ask_challenge, your_turn, gameover
	private Object data;//{"users_list":[“gabriel”, “juan”]}, 
						//{
						//  “username”: ”gabriel”,
						//  “message”: “mensaje para el otro usuario”
						//},
						//{
						//	"username":"gabriel",
						//	"board_id":"2d348323-2e79-4961-ac36-1b000e8c42a5"
						//}
						//	{ 
						//	  “board_id”: "2d348323-2e79-4961-ac36-1b000e8c42a5" 
						//	}
						//		{
						//		"board_id":"2d348323-2e79-4961-ac36-1b000e8c42a5",
						//		"turn_token":"e40573bb-138f-4171-a200-66258f546755",
						//		"username":"gabriel",
						//		"actual_turn":"white",
						//		"board":"rrhhbbqqkkbbhhrrrrhhbbqqkkbbhhrrpppppppppppppppppppppppppppppppp                                                                                                                        P       PPPPPPPP PPPPPPPPPPPPPPPPPPPPPPPRRHHBBQQKKBBHHRRRRHHBBQQKKBBHHRR",
						//		"move_left":19
						//		}
						//	{
						//        “board_id”: ”2d348323-2e79-4961-ac36-1b000e8c42a5”,
						//        “turn_token: ”e40573bb-138f-4171-a200-66258f546755”,
						//        “from_row”: 1,
						//        “from_col”: 1,
						//        “to_row”: 3,
						//        “to_col”: 1
						//    }
						//	    {
						//		"board":"B*1234567890123456*\n1|rrhhbbqqkkbbhhrr|\n2|rrhhbbqqkkbbhhrr|\n3|pppppppppppppppp|\n4|pppppppp ppppppp|\n5|        p       |\n6|                |\n7|                |\n8|                |\n9|                |\n0|                |\n1|               P|\n2|     P P        |\n3|PPPPP P PPPPPPP |\n4|PPPPPPPPPPPPPPPP|\n5|RRHHBBQQKKBBHHRR|\n6|RRHHBBQQKKBBHHRR|\nW*----------------*\n",
						//		"white_username":"gabriel",
						//		"black_username":"pedro",
						//		"white_score":"28",
						//		"black_score":"-6"
						//		}





	
}
