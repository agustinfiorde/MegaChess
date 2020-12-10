package app.megachess.websocket;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.concurrent.TimeUnit;

import app.megachess.AI.Intelligence;
import app.megachess.models.Message;
import app.megachess.utils.ChessUtil;
import app.megachess.utils.Util;

public class Connection {

	private WebSocketClient clientEndPoint;
	private final String root = "ws://megachess.herokuapp.com/service?authtoken=";
	private final String tokenFiordeX = "b3eefc9b-e1b2-4a07-bcc3-50b1d1ebfc84";

	private String url;

	/*
	 * constructor quien se encarga de desencadenar la app, seteando la URL y
	 * dandole inicio a la app
	 */
	public Connection() {
		setURL();
		start();
	}

	/*
	 * Genera la URL en base a los atributos de la clase
	 */
	private void setURL() {
		url = root.concat(tokenFiordeX);
	}

	public String getUrl() {
		return url;
	}

	/*
	 * start, gestiona los mensajes recibidos por parte del socket, para determinar
	 * el accionar especifico en base a cada mensaje
	 */
	public void start() {

		try {
			clientEndPoint = new WebSocketClient(new URI(url));
			clientEndPoint.addMessageHandler(new WebSocketClient.MessageHandler() {
				public void handleMessage(String msj) {

					Message message = Util.JSONToObject(msj);

					// Solicitud de desafio
					if (msj.contains("ask_challenge")) {
						clientEndPoint.sendMessage(Util.acceptChallenge(message.getData().getBoard_id()));
					}

					// Turno
					if (msj.contains("your_turn")) {

						try {
							TimeUnit.MILLISECONDS.sleep(300);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
						ChessUtil.showBoard(message.getData().getBoard());
						String res = Intelligence.evaluate(message);
						clientEndPoint.sendMessage(res);

					}

					// Game over
					if (msj.contains("gameover")) {
						System.out.println(Util.gameover(message));
					}
				}
			});

			Thread.sleep(1000);
		} catch (InterruptedException ex) {
			System.err.println("InterruptedException exception: " + ex.getMessage());
		} catch (URISyntaxException ex) {
			System.err.println("URISyntaxException exception: " + ex.getMessage());
		}
	}
}
