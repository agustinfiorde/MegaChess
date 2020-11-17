package app.megachess.websocket;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import app.megachess.utils.ChessUtil;
import app.megachess.utils.Util;
import app.megachess.websocket.models.Message;

public class Connection {

	private final boolean automaticChallenge = false;
	private List<String> challengedUsers = new ArrayList<>();

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
		boolean option = false;
		url = option ? root.concat(tokenFiorde) : root.concat(tokenFiordeX);
		username = option ? "fiorde" : "fiordeX";
	}

	public void start() {
		try {
			clientEndPoint = new WebSocketClient(new URI(url));
			clientEndPoint.addMessageHandler(new WebSocketClient.MessageHandler() {
				public void handleMessage(String msj) {

					Message message = Util.JSONToObject(msj);

					/**
					 * ----- USERS_LIST
					 */
					if (msj.contains("users_list")) {

//						List<String> users_list = message.getData().getUsers_list().stream().filter(e -> !e.equals(username)).collect(Collectors.toList());
						// DOWN Incluirme, UP Excluirme
						List<String> users_list = message.getData().getUsers_list();
						// MOSTRAR LISTA
						users_list.forEach((e) -> System.out.println(e));
						clientEndPoint.sendMessage(Util.challenge(selectOpponentToChallenge(users_list)));
					}

					/**
					 * ----- ASK_CHALLENGE
					 */
					if (msj.contains("ask_challenge")) {
						System.out.println("Te esta desafiando " + message.getData().getUsername());
						clientEndPoint.sendMessage(Util.acceptChallenge(message.getData().getBoard_id()));
					}

					/**
					 * ----- MY_TURN
					 */
					if (msj.contains("your_turn")) {
						System.out.println("Tu turno");
						ChessUtil.showBoard(message.getData().getBoard());
						System.out.println(Util.move(message));
						clientEndPoint.sendMessage(Util.move(message));
					}

				}
			});

			Thread.sleep(5000);

		} catch (InterruptedException ex) {
			System.err.println("InterruptedException exception: " + ex.getMessage());
		} catch (URISyntaxException ex) {
			System.err.println("URISyntaxException exception: " + ex.getMessage());
		}
	}

	/**
	 * Detecta los usuarios conectados y los desafia, me puedo evitar o no a mi mismo.
	 * Tambien guarda los usuarios desafiados, una vez que no hay mas usuarios desafiados deberia empezar de nuevo TODO
	 * @param users_list
	 * @return
	 */
	private String selectOpponentToChallenge(List<String> users_list) {
		if (automaticChallenge) {
			for (String o : users_list) {
				if (!challengedUsers.contains(o)) {
					return o;
				}
			}
		}
		return username;
	}

	public void test() {

		String boardString = "rrhhbbqqkkbbhhrQrrhhbbqqkkbbhhrQpppppppppppppppQpppppppppppppppQ"
				+ "                                                                            "
				+ "                                                    "
				+ "PPPPPPPPPPPPPPPqPPPPPPPPPPPPPPPqRRHHBBQQKKBBHHRqRRHHBBQQKKBBHHRq";

	}
}
