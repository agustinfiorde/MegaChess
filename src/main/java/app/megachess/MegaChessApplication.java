package app.megachess;

import java.net.URI;
import java.net.URISyntaxException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import app.megachess.websocket.WebSocketClient;


@SpringBootApplication
public class MegaChessApplication {

	public static void main(String[] args) {
		SpringApplication.run(MegaChessApplication.class, args);
		

        try {
            // open websocket                        
//        	final WebSocketClient clientEndPoint = new WebSocketClient(new URI("ws://mega-chess-qa.herokuapp.com/service?authtoken=b3eefc9b-e1b2-4a07-bcc3-50b1d1ebfc84"));
        	final WebSocketClient clientEndPoint = new WebSocketClient(new URI("wss://echo.websocket.org"));
        	
            // add listener
            clientEndPoint.addMessageHandler(new WebSocketClient.MessageHandler() {
                public void handleMessage(String message) {
                    
                    if (message.contains("2")) {
                    	System.out.println(message);
					}
                }
            });

            // send message to websocket
            clientEndPoint.sendMessage("{asd:'asd'}");
            clientEndPoint.sendMessage("{asd:'2'}");
            clientEndPoint.sendMessage("{asd:'3'}");
            clientEndPoint.sendMessage("{asd:'222'}");
            clientEndPoint.sendMessage("{asd:'5'}");
      

            // wait 5 seconds for messages from websocket
            Thread.sleep(5000);

        } catch (InterruptedException ex) {
            System.err.println("InterruptedException exception: " + ex.getMessage());
        } catch (URISyntaxException ex) {
            System.err.println("URISyntaxException exception: " + ex.getMessage());
        }
    }

}
