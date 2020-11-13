package app.megachess.chess;

import java.net.URI;
import java.net.URISyntaxException;

import app.megachess.utils.Util;
import app.megachess.websocket.WebSocketClient;

/**
 * 
 * @author AsteriX
 *
 */
public class MainChess {

//	private static final String URL="ws://mega-chess-qa.herokuapp.com/service?authtoken=b3eefc9b-e1b2-4a07-bcc3-50b1d1ebfc84";
	private static final String URL="wss://echo.websocket.org";
	
	
	public static void main() {
		
		try {
			// open websocket
        	final WebSocketClient clientEndPoint = new WebSocketClient(new URI(URL));

			// add listener
			clientEndPoint.addMessageHandler(new WebSocketClient.MessageHandler() {
				public void handleMessage(String message) {

					System.out.println(message);

				}
			});

			
			Util.test();
			
			
			// send message to websocket
			clientEndPoint.sendMessage("{'color':'Rojo'}");

			// wait 5 seconds for messages from websocket
			Thread.sleep(5000);

		} catch (InterruptedException ex) {
			System.err.println("InterruptedException exception: " + ex.getMessage());
		} catch (URISyntaxException ex) {
			System.err.println("URISyntaxException exception: " + ex.getMessage());
		}
		
	}
	
		

}
