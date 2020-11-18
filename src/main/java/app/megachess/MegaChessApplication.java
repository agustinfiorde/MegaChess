package app.megachess;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import app.megachess.websocket.Connection;

@SpringBootApplication
@EnableAutoConfiguration
public class MegaChessApplication {


	/**
	 * Before running create a database in mysql and set the application.yml
	 * @param args
	 */
	public static void main(String[] args) {

		SpringApplication.run(MegaChessApplication.class, args);
		new Connection();
	}



	

}
