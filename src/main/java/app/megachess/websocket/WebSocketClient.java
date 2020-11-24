package app.megachess.websocket;

import java.io.IOException;
import java.net.URI;

import javax.websocket.ClientEndpoint;
import javax.websocket.ContainerProvider;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.WebSocketContainer;

@ClientEndpoint
public class WebSocketClient {

	private Session userSession = null;
	private MessageHandler messageHandler;

	public WebSocketClient(URI endpointURI) {
		try {
			WebSocketContainer container = ContainerProvider.getWebSocketContainer();
			container.connectToServer(this, endpointURI);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@OnOpen
	public void onOpen(Session userSession) {
		System.out.println("opening websocket");
		this.userSession = userSession;
	}

//	@OnClose
//	public void onClose(Session userSession, CloseReason reason) {
//		System.out.println("closing websocket");
//		this.userSession = null;
//	}
	

    @OnMessage
    public void onMessage(String message) {
        if (this.messageHandler != null) {
            this.messageHandler.handleMessage(message);
        }
    }


	public void addMessageHandler(MessageHandler msgHandler) {
		this.messageHandler = msgHandler;
	}

	public void sendMessage(String message) {
		try {
			this.userSession.getBasicRemote().sendText(message);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
//	public void sendMessage(String message) {
//		this.userSession.getAsyncRemote().sendText(message);
//	}

	public static interface MessageHandler {
		public void handleMessage(String message);
	}
}