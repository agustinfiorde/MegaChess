package app.megachess.websocket.models;

import java.util.List;

import lombok.Data;

@Data
public class DataMessage {

	private String board_id;
	private String turn_token;
	private String username;
	private String actual_turn;
	private String board;
	private String move_left;

	private List<String> users_list;
	private String message;

	private Integer from_row;
	private Integer from_col;
	private Integer to_row;
	private Integer to_col;

	private String white_username;
	private String black_username;
	private String white_score;// manejarlo como integer
	private String black_score;// manejarlo como integer

	
	//{"users_list":[“gabriel”, “juan”]}, 
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
