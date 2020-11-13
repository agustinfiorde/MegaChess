package app.megachess;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import app.megachess.chess.MainChess;

@SpringBootApplication
public class MegaChessApplication {

	public static void main(String[] args) {
		SpringApplication.run(MegaChessApplication.class, args);

		MainChess.main();
		
	}

}
