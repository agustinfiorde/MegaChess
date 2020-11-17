package app.megachess.websocket;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.stream.Collectors;

import app.megachess.utils.ChessUtil;
import app.megachess.utils.Util;
import app.megachess.websocket.models.Message;

public class Connection {

	private WebSocketClient clientEndPoint;

	private final String root = "ws://megachess.herokuapp.com/service?authtoken=";
	
	private final String tokenFiordeX = "b3eefc9b-e1b2-4a07-bcc3-50b1d1ebfc84";
	private final String tokenFiorde = "c383588c-c72b-4583-ad71-1bb9b3f5f4eb";
	private String username;
	
	private String url;

	public Connection() {
		setURL();
		start();
	}

	private void setURL() {
		boolean option=false;
		url = option ? root.concat(tokenFiorde) : root.concat(tokenFiordeX);
		username = option ? "fiorde" : "fiordeX";
	}

	public void start() {

		try {
			clientEndPoint = new WebSocketClient(new URI(url));

			clientEndPoint.addMessageHandler(new WebSocketClient.MessageHandler() {
				public void handleMessage(String msj) {


					/**
					 *    USERS_LIST
					 */
					if (msj.contains("users_list")) {
						Message message= Util.JSONToObject(msj);
						
						/**
						 * Para obtener todos los usuarios menos yo
						 */
//						List<String> users_list= message.getData().getUsers_list()
//								.stream()
//								.filter(e -> !e.equals(username))
//								.collect(Collectors.toList());
						List<String> users_list= message.getData().getUsers_list();
						
						System.out.println("----------------------Lista de usuarios");
						System.out.println(message);
						users_list.forEach((e)->System.out.println(e));
					}
					
					/**
					 *    ASK_CHALLENGE preguntar desafio
					 */
					if (msj.contains("ask_challenge")) {
						Message message= Util.JSONToObject(msj);
						
						String board_id =message.getData().getBoard_id();
						
						System.out.println("Te estan desafiando");
						System.out.println(message.toString());
						System.out.println(board_id);
					}
					
					/**
					 *    MY_TURN
					 */
					if (msj.contains("your_turn")) {
						Message message= Util.JSONToObject(msj);
						
						String board_id =message.getData().getBoard_id();
						
						System.out.println("Tu turno");
						System.out.println(board_id);
					}


				}
			});

//			clientEndPoint.sendMessage("");

			Thread.sleep(5000);

		} catch (InterruptedException ex) {
			System.err.println("InterruptedException exception: " + ex.getMessage());
		} catch (URISyntaxException ex) {
			System.err.println("URISyntaxException exception: " + ex.getMessage());
		}

//		try {
//			final WebSocketClient clientEndPoint = new WebSocketClient(new URI(url));
//
//			clientEndPoint.addMessageHandler(new WebSocketClient.MessageHandler() {
//				public void handleMessage(String message) {
//
//					System.out.println("raw"+message);
//					
//				}
//			});
//
//
//			clientEndPoint.sendMessage("");
//
//			Thread.sleep(1000);
//
//		} catch (InterruptedException ex) {
//			System.err.println("InterruptedException exception: " + ex.getMessage());
//		} catch (URISyntaxException ex) {
//			System.err.println("URISyntaxException exception: " + ex.getMessage());
//		}

	}

	public void test() {

		String boardString = "rrhhbbqqkkbbhhrQrrhhbbqqkkbbhhrQpppppppppppppppQpppppppppppppppQ"
				+ "                                                                            "
				+ "                                                    "
				+ "PPPPPPPPPPPPPPPqPPPPPPPPPPPPPPPqRRHHBBQQKKBBHHRqRRHHBBQQKKBBHHRq";

		String board[][] = ChessUtil.getBoard(boardString);
		ChessUtil.showBoard(board);

	}

	public void acept_challenge() {

	}
}
