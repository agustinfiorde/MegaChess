package app.megachess;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import app.megachess.websocket.Connection;

@SpringBootApplication
@EnableAutoConfiguration
public class MegaChessApplication {



	public static void main(String[] args) {

		SpringApplication.run(MegaChessApplication.class, args);
		new Connection();
	}



	

}
