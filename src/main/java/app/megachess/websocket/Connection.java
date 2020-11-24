package app.megachess.websocket;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;

import app.megachess.AI.Intelligence;
import app.megachess.services.GameDataService;
import app.megachess.utils.ChessUtil;
import app.megachess.utils.Util;
import app.megachess.websocket.models.Message;

public class Connection {

	private WebSocketClient clientEndPoint;

	@Autowired
	private GameDataService gdService;

	private String username;
	private String url;

	private final String root = "ws://megachess.herokuapp.com/service?authtoken=";
	private final String tokenFiordeX = "b3eefc9b-e1b2-4a07-bcc3-50b1d1ebfc84";
	private final String tokenFiorde = "c383588c-c72b-4583-ad71-1bb9b3f5f4eb";

	private final boolean automaticChallenge = false;
	private final boolean isFiordeX = true;
	private List<String> challengedUsers = new ArrayList<>();

	public Connection() {
		setURL();
		start();
	}

	private void setURL() {
		url = isFiordeX ? root.concat(tokenFiordeX) : root.concat(tokenFiorde);
		username = isFiordeX ? "fiordeX" : "fiorde";
	}

	public void start() {
		try {
			clientEndPoint = new WebSocketClient(new URI(url));

			clientEndPoint.addMessageHandler(new WebSocketClient.MessageHandler() {
				public void handleMessage(String msj) {

					Message message = Util.JSONToObject(msj);

					// Lista de participantes
					if (msj.contains("users_list")) {

						// List<String> users_list = message.getData().getUsers_list().stream().filter(e
						// -> !e.equals(username)).collect(Collectors.toList());
						List<String> users_list = message.getData().getUsers_list();
						System.out.println("--------------------------------------------");
						users_list.forEach((e) -> System.out.print(e + " . "));
						System.out.println("");
						System.out.println("--------------------------------------------");
//						clientEndPoint.sendMessage(Util.challenge(selectOpponentToChallenge(users_list)));
					}

					// Solicitud de desafio
					if (msj.contains("ask_challenge")) {
						clientEndPoint.sendMessage(Util.acceptChallenge(message.getData().getBoard_id()));
					}

					// Turno
					if (msj.contains("your_turn")) {

						ChessUtil.showBoard(message.getData().getBoard());
						try {
							TimeUnit.MILLISECONDS.sleep(200);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
						String res = Intelligence.evaluate(message);
						
						clientEndPoint.sendMessage(res);
					}

					// Game over
					if (msj.contains("gameover")) {
//						gdService.saveGame(message);
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
}
