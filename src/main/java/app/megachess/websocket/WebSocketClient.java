package app.megachess.websocket;

import java.net.URI;

import javax.websocket.ClientEndpoint;
import javax.websocket.ContainerProvider;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.WebSocketContainer;

/**
 * ClientEndPoint, se encarga de gestionar todo lo relacionado a pedir o recibir
 * informacion desde el socket.
 * 
 * @author AsteriX
 *
 */
@ClientEndpoint
public class WebSocketClient {

	private Session userSession = null;
	private MessageHandler messageHandler;

	/**
	 * Constructor que establece la coneccion en base a la URL
	 * 
	 * @param endpointURI
	 */
	public WebSocketClient(URI endpointURI) {
		try {
			WebSocketContainer container = ContainerProvider.getWebSocketContainer();
			container.connectToServer(this, endpointURI);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * onOpen, habilita la session del usurio para estar activo
	 * 
	 * @param userSession
	 */
	@OnOpen
	public void onOpen(Session userSession) {
		System.out.println("opening websocket");
		this.userSession = userSession;
	}

	/**
	 * onClose, se encarga se desvincular mi app con el socket por algun motivo que
	 * lo demande ACTUALMENTE SE ENCUENTRA COMENTADO CON EL FIN DE PROBAR SI PUEDO
	 * MANTENER POR SIEMPRE ACTIVA MI CONECCION A RECIBIR DESAFIOS
	 * 
	 * @param userSession
	 * @param reason
	 */
	// @OnClose
	// public void onClose(Session userSession, CloseReason reason) {
	// System.out.println("closing websocket");
	// this.userSession = null;
	// }

	@OnMessage
	public void onMessage(String message) {
		if (this.messageHandler != null) {
			this.messageHandler.handleMessage(message);
		}
	}

	public void addMessageHandler(MessageHandler msgHandler) {
		this.messageHandler = msgHandler;
	}

	/**
	 * sendMessage, se encarga de enviar mi respuesta al socket
	 * 
	 * @param message
	 */
	public void sendMessage(String message) {
		this.userSession.getAsyncRemote().sendText(message);
	}

	public static interface MessageHandler {
		public void handleMessage(String message);
	}
}