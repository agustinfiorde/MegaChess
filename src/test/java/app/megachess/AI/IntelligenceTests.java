package app.megachess.AI;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.util.List;

import org.junit.jupiter.api.Test;

import app.megachess.enums.Event;
import app.megachess.models.DataMessage;
import app.megachess.models.Message;
import app.megachess.models.Response;
import app.megachess.utils.ChessUtil;

public class IntelligenceTests {

	public Message messageGenerator(String boardString) {

		Message msj = new Message();
		msj.setEvent(Event.YOUR_TURN.toString());
		DataMessage msjData = new DataMessage();
		msjData.setBoard_id("2d348323-2e79-4961-ac36-1b000e8c42a5");
		msjData.setTurn_token("2d348323-2e79-4961-ac36-1b000e8token");
		msjData.setUsername("fiordeX");
		msjData.setActual_turn("white");
		msjData.setBoard(boardString);
		msjData.setMove_left(199);
		msj.setData(msjData);

		return msj;
	}

	@Test
	public void evaluate() {

		String boardString = "rrhhbbqqkkbbhhrr" + 
							"rrhhbbqqkkbbhhrr" + 
							"pppppppppppppppp" + 
							"pppppppppppppppp" + 
							"                " + 
							"                " + 
							"                " + 
							"                " + 
							"                " + 
							"                " + 
							"                " + 
							"                " + 
							"PPPPPPPPPPPPPPPP" + 
							"PPPPPPPPPPPPPPPP" + 
							"RRHHBBQQKKBBHHRR" + 
							"RRHHBBQQKKBBHHRR";

		Message msj = messageGenerator(boardString);

		// Envio informacion espero algo distinto a empty
		assertNotEquals("", Intelligence.evaluate(msj));

		boardString = "rrhhbbqqkkbbhhrr" + 
						"rrhhbbqqkkbbhhrr" + 
						"pppppppppppppppp" + 
						"pppppppppppppppp" + 
						"                " + 
						"                " + 
						"                " + 
						"                " + 
						"                " + 
						"                " + 
						"                " + 
						"                " + 
						"                " + 
						"                " + 
						"                " + 
						"                ";
		msj.getData().setBoard(boardString);

		// envio un tablero sin blancas espero empty al no haber ninguna
		// opcion para hacer
		assertEquals("", Intelligence.evaluate(msj));
	}

	@Test
	public void queenDefense() {

		String boardString = "rrhhbbqqkkbbhhrr" + 
							"rrhhbbqqkkbbhhrr" + 
							"pppppppppppppppp" + 
							"pppppppppppppppp" + 
							"                " + 
							"                " + 
							"                " + 
							"                " + 
							"                " + 
							"                " + 
							"                " + 
							"                " + 
							"PPPPPPPPPPPPPPPP" + 
							"PPPPPPPPPPPPPPPP" + 
							"RRHHBBQQKKBBHHRR" + 
							"RRHHBBQQKKBBHHRR";
		Message msj = messageGenerator(boardString);
		String[][] board = ChessUtil.getBoard(msj.getData().getBoard());
		String color = msj.getData().getActual_turn();

		// Ante ninguna amenaza deberia retornar null
		assertEquals(null, Intelligence.queenDefense(msj, board, color));

		boardString = "rrhhbbqqkkbbhhrr" + 
					"rrhhbbqqkkbbhhrr" + 
					"pppppppppppppppp" + 
					"pppppppppppppppp" + 
					"                " + 
					"                " + 
					"                " + 
					"                " + 
					"                " + 
					"                " + 
					"                " + 
					"                " + 
					"                " + 
					"                " + 
					"RRHHBB QKKBBHHRR" + 
					"       q        ";
		msj.getData().setBoard(boardString);
		board = ChessUtil.getBoard(msj.getData().getBoard());

		// Ante una amenaza deberia retornar un JSON
		assertNotEquals(null, Intelligence.queenDefense(msj, board, color));
	}

	@Test
	public void horseDefense() {

		String boardString = "rrhhbbqqkkbbhhrr" + 
							"rrhhbbqqkkbbhhrr" + 
							"pppppppppppppppp" + 
							"pppppppppppppppp" + 
							"                " + 
							"                " + 
							"                " + 
							"                " + 
							"                " + 
							"                " + 
							"                " + 
							"       q q q    " + 
							"PPPPPPPPPPPPPPPP" + 
							"PPPPPPPqPHPqPPPP" + 
							"RRHHBBQQKKBBPPRR" + 
							"RRHHBBQQKqBqPPRR";
		Message msj = messageGenerator(boardString);
		String[][] board = ChessUtil.getBoard(msj.getData().getBoard());
		String color = msj.getData().getActual_turn();

		// Ante ninguna amenaza deberia retornar null
		assertEquals(null, Intelligence.horseDefense(msj, board, color));

		boardString = "rrhhbbqqkkbbhhrr" + 
					"rrhhbbqqkkbbhhrr" + 
					"pppppppppppppppp" + 
					"pppppppppppppppp" + 
					"                " + 
					"                " + 
					"                " + 
					"                " + 
					"                " + 
					"                " + 
					"                " + 
					"       qqq q    " + 
					"PPPPPPPPPPPPPPPP" + 
					"PPPPPPPqPHPPPPPP" + 
					"RRHHBBQQKKBBHHRR" + 
					"RRHHBBQQKqBqHHRR";
		msj.getData().setBoard(boardString);
		board = ChessUtil.getBoard(msj.getData().getBoard());

		// Ante una amenaza deberia retornar un JSON
		assertNotEquals(null, Intelligence.horseDefense(msj, board, color));
	}
	
	@Test
	public void rookDefense() {

		String boardString = "rrhhbbqqkkbbhhrr" + 
							 "rrhhbbqqkkbbhhrr" + 
							 "pppppppppppppppp" + 
							 "pppppppppppppppp"	+ 
							 "                " + 
							 "                " + 
							 "                " + 
							 "                " + 
							 "                "	+ 
							 "                " + 
							 "                " + 
							 "                " + 
							 "PPPPPPPqPqPPPPPP" + 
							 "PPPPPPPPRPPPPPPP"	+ 
							 "PPPHBBQqKqBBHHPP" + 
							 "RRHHBBQQKKBBHPPP";
		Message msj = messageGenerator(boardString);
		String[][] board = ChessUtil.getBoard(msj.getData().getBoard());
		String color = msj.getData().getActual_turn();

		// Ante ninguna amenaza deberia retornar null
		assertEquals(null, Intelligence.rookDefense(msj, board, color));

		boardString = "rrhhbbqqkkbbhhrr" + 
					  "rrhhbbqqkkbbhhrr" + 
					  "pppppppppppppppp" + 
					  "pppppppppppppppp"	+ 
					  "                " + 
					  "                " + 
					  "                " + 
					  "                " + 
					  "                "	+ 
					  "                " + 
					  "                " + 
					  "                " + 
					  "PPPPPPP q PPPPPP" + 
					  "PPPPPPP R PPPPPP"	+ 
					  "PPPHBBQ   BBHHPP" + 
					  "RRHHBBQQKKBBHPPP";
		msj.getData().setBoard(boardString);
		board = ChessUtil.getBoard(msj.getData().getBoard());

		// Ante una amenaza dentro de sus posibilidades deberia retornar un JSON
		assertNotEquals(null, Intelligence.rookDefense(msj, board, color));
	}
	
	@Test
	public void kingDefense() {

		String boardString = "rrhhbbqqkkbbhhrr" + 
							 "rrhhbbqqkkbbhhrr" + 
							 "pppppppppppppppp" + 
							 "pppppppppppppppp"	+ 
							 "                " + 
							 "                " + 
							 "                " + 
							 "                " + 
							 "                "	+ 
							 "                " + 
							 "                " + 
							 "       qqqqq    " + 
							 "PPPPPPPqPPPqPPPP" + 
							 "PPPPPPPqPKPqPPPP" + 
							 "PPPHBBQqPPBqHHPP" + 
							 "RRHHBBQqqqqqHPPP";
		Message msj = messageGenerator(boardString);
		String[][] board = ChessUtil.getBoard(msj.getData().getBoard());
		String color = msj.getData().getActual_turn();

		// Ante ninguna amenaza proxima deberia retornar null
		assertEquals(null, Intelligence.kingDefense(msj, board, color));

		boardString = "rrhhbbqqkkbbhhrr" + 
					  "rrhhbbqqkkbbhhrr" + 
					  "pppppppppppppppp" + 
					  "pppppppppppppppp" + 
					  "                " + 
					  "                " + 
					  "                " + 
					  "                " + 
					  "                " + 
					  "                " + 
					  "                " + 
					  "                " + 
					  "PPPPPPPPqqqPPPPP" + 
					  "PPPPPPPPqKqPPPPP" + 
					  "PPPHBBQPqqqBHHPP" + 
					  "RRHHBBQQKKBBHPPP";
		msj.getData().setBoard(boardString);
		board = ChessUtil.getBoard(msj.getData().getBoard());

		// Ante una amenaza proxima deberia retornar un JSON
		assertNotEquals(null, Intelligence.kingDefense(msj, board, color));
	}

	@Test
	public void bishopDefense() {

		String boardString = "rrhhbbqqkkbbhhrr" + 
							 "rrhhbbqqkkbbhhrr" + 
							 "pppppppppppppppp" + 
							 "pppppppppppppppp"	+ 
							 "                " + 
							 "                " + 
							 "                " + 
							 "                " + 
							 "                "	+ 
							 "                " + 
							 "                " + 
							 "PPPPPPPPPPPPPPPP" + 
							 "PPPPPPPPqPPPPPPP" + 
							 "PPPPPPPqBqPPPPPP" + 
							 "PPPPPPPPqPPPPPPP" + 
							 "PPPPPPPPPPPPPPPP";
		Message msj = messageGenerator(boardString);
		String[][] board = ChessUtil.getBoard(msj.getData().getBoard());
		String color = msj.getData().getActual_turn();

		// Ante ninguna posibilidad de comer deberia retornar null
		assertEquals(null, Intelligence.bishopDefense(msj, board, color));

		boardString = "rrhhbbqqkkbbhhrr" + 
					  "rrhhbbqqkkbbhhrr" + 
					  "pppppppppppppppp" + 
					  "pppppppppppppppp" + 
					  "                " + 
					  "                " + 
					  "                " + 
					  "                " + 
					  "                " + 
					  "                " + 
					  "                " + 
					  "                " + 
					  "PPPPPPPqPqPPPPPP" + 
					  "PPPPPPPPBPPPPPPP" + 
					  "PPPPPPPqPqPPPPPP" + 
					  "PPPPPPPPPPPPPPPP";
		msj.getData().setBoard(boardString);
		board = ChessUtil.getBoard(msj.getData().getBoard());

		// Ante una posibilidad de comer deberia retornar un JSON
		assertNotEquals(null, Intelligence.bishopDefense(msj, board, color));
	}
	
	@Test
	public void pawnDefense() {

		String boardString = "rrhhbbqqkkbbhhrr" + 
							 "rrhhbbqqkkbbhhrr" + 
							 "pppppppppppppppp" + 
							 "pppppppppppppppp"	+ 
							 "                " + 
							 "                " + 
							 "                " + 
							 "                " + 
							 "                "	+ 
							 "                " + 
							 "                " + 
							 "QQQQQQQQqQQQQQQQ" + 
							 "QQQQQQQqPqQQQQQQ" + 
							 "QQQQQQQqqqQQQQQQ" + 
							 "QQQQQQQQQQQQQQQQ" + 
							 "PPPPPPPPPPPPPPPP";
		Message msj = messageGenerator(boardString);
		String[][] board = ChessUtil.getBoard(msj.getData().getBoard());
		String color = msj.getData().getActual_turn();

		// Ante ninguna posibilidad de comer deberia retornar null
		assertEquals(null, Intelligence.pawnDefense(msj, board, color));

		boardString = "rrhhbbqqkkbbhhrr" + 
					  "rrhhbbqqkkbbhhrr" + 
					  "pppppppppppppppp" + 
					  "pppppppppppppppp" + 
					  "                " + 
					  "                " + 
					  "                " + 
					  "                " + 
					  "                " + 
					  "                " + 
					  "                " + 
					  "                " + 
					  "PPPPPPPPqPqPPPPP" + 
					  "QQQQQQQQQPQQQQQQ" + 
					  "PPPPPPPPPPPPPPPP" + 
					  "PPPPPPPPPPPPPPPP";
		msj.getData().setBoard(boardString);
		board = ChessUtil.getBoard(msj.getData().getBoard());

		// Ante una posibilidad de comer deberia retornar un JSON
		assertNotEquals(null, Intelligence.pawnDefense(msj, board, color));
	}
		
	@Test
	public void pawnAction() {
		String boardString = "rrhhbbqqkkbbhhrr" + 
						  "rrhhbbq kkbbhhrr" + 
						  "ppppppp pppppppp" + 
						  "ppppppp pppppppp" + 
						  "                " + 
						  "                " + 
						  "                " + 
						  "                " + 
						  "                " + 
						  "                " + 
						  "                " + 
						  "QQQQQQQQQQQQQQQQ" + 
						  "QQQQQQQQPQQQQQQQ" + 
						  "QQQQQQQQQQQQQQQQ" + 
						  "QQQQQQQQQQQQQQQQ" + 
						  "QQQQQQQQQQQQQQQQ";
		Message msj = messageGenerator(boardString);
		String[][] board = ChessUtil.getBoard(msj.getData().getBoard());
		String color = msj.getData().getActual_turn();
		List<Response> responses = ChessUtil.findPawnByBotSector(board, 15, 0);
		
		
		// Ante un bloqueo deberia retornar null
		
		assertEquals(null, Intelligence.pawnAction(msj, board, color, responses));
		
		boardString = "rrhhbbqqkkbbhhrr" + 
				  "rrhhbbqqkkbbhhrr" + 
				  "pppppppppppppppp" + 
				  "pppppppppppppppp" + 
				  "                " + 
				  "                " + 
				  "                " + 
				  "                " + 
				  "                " + 
				  "                " + 
				  "                " + 
				  "                " + 
				  "QQQQQQPQQQQQQQQQ" + 
				  "QQQQQQQQQQQQQQQQ" + 
				  "QQQQQQQQQQQQQQQQ" + 
				  "QQQQQQQQQQQQQQQQ";
		msj.getData().setBoard(boardString);
		board = ChessUtil.getBoard(msj.getData().getBoard());
		
		//Ante ningun bloqueo deberia retornar un JSON
		assertNotEquals(null, Intelligence.pawnAction(msj, board, color, responses));
	}
	
	@Test
	public void progressBySector() {
		String boardString = "rrhhbbqqkkbbhhrr" + 
				  "rrhhbbq kkbbhhrr" + 
				  "ppppppp pppppppp" + 
				  "ppppppp pppppppp" + 
				  "                " + 
				  "                " + 
				  "                " + 
				  "                " + 
				  "                " + 
				  "                " + 
				  "qqqqqqqqqqqqqqqq" + 
				  "PPPPPPPPPPPPPPPP" + 
				  "QQQQQQQQQQQQQQQQ" + 
				  "QQQQQQQQQQQQQQQQ" + 
				  "QQQQQQQQQQQQQQQQ" + 
				  "QQQQQQQQQQQQQQQQ";
		Message msj = messageGenerator(boardString);
		String[][] board = ChessUtil.getBoard(msj.getData().getBoard());
		String color = msj.getData().getActual_turn();
		
		// Ante un bloqueo deberia retornar null
		assertEquals(null, Intelligence.progressBySector(msj, board, 15, 0, color));
		
		boardString = "rrhhbbqqkkbbhhrr" + 
				  "rrhhbbqqkkbbhhrr" + 
				  "pppppppppppppppp" + 
				  "pppppppppppppppp" + 
				  "                " + 
				  "                " + 
				  "                " + 
				  "                " + 
				  "                " + 
				  "                " + 
				  "                " + 
				  "PPPPPPPPPPPPPPPP" + 
				  "QQQQQQQQQQQQQQQQ" + 
				  "QQQQQQQQQQQQQQQQ" + 
				  "QQQQQQQQQQQQQQQQ" + 
				  "QQQQQQQQQQQQQQQQ";
		msj.getData().setBoard(boardString);
		board = ChessUtil.getBoard(msj.getData().getBoard());
		
		// Ante ningun bloqueo deberia retornar un JSON
		assertNotEquals(null, Intelligence.progressBySector(msj, board, 15, 0, color));
	}
	
	@Test
	public void pawnResolver() {
		String boardString = "rrhhbbqqkkbbhhrr" + 
						  "rrhhbbq kkbbhhrr" + 
						  "ppppppp pppppppp" + 
						  "ppppppp pppppppp" + 
						  "                " + 
						  "                " + 
						  "                " + 
						  "                " + 
						  "                " + 
						  "                " + 
						  "qqqqqqqqqqqqqqqq" + 
						  "PPPPPPPPPPPPPPPP" + 
						  "QQQQQQQQQQQQQQQQ" + 
						  "QQQQQQQQQQQQQQQQ" + 
						  "QQQQQQQQQQQQQQQQ" + 
						  "QQQQQQQQQQQQQQQQ";
		Message msj = messageGenerator(boardString);
		String[][] board = ChessUtil.getBoard(msj.getData().getBoard());
		String color = msj.getData().getActual_turn();
		
		// Ante un bloqueo deberia retornar null
		assertEquals(null, Intelligence.pawnResolver(msj, board, color));
		
		boardString = "rrhhbbqqkkbbhhrr" + 
				  "rrhhbbqqkkbbhhrr" + 
				  "pppppppppppppppp" + 
				  "pppppppppppppppp" + 
				  "                " + 
				  "                " + 
				  "                " + 
				  "                " + 
				  "                " + 
				  "                " + 
				  "                " + 
				  "PPPPPPPPPPPPPPPP" + 
				  "QQQQQQQQQQQQQQQQ" + 
				  "QQQQQQQQQQQQQQQQ" + 
				  "QQQQQQQQQQQQQQQQ" + 
				  "QQQQQQQQQQQQQQQQ";
		msj.getData().setBoard(boardString);
		board = ChessUtil.getBoard(msj.getData().getBoard());
		
		// Ante ningun bloqueo deberia retornar un JSON
		assertNotEquals(null, Intelligence.pawnResolver(msj, board, color));
	}
	
	@Test
	public void kingProceed() {
		String boardString = "rrhhbbqqkkbbhhrr" + 
						  "rrhhbbq kkbbhhrr" + 
						  "ppppppp pppppppp" + 
						  "ppppppp pppppppp" + 
						  "                " + 
						  "                " + 
						  "                " + 
						  "                " + 
						  "                " + 
						  "                " + 
						  "QQQQQQQQQQQQQQQQ" + 
						  "QQQQQQQQQQQQQQQQ" + 
						  "QQQQQQQQQQQQQQQQ" + 
						  "QQQQQQQQKQQQQQQQ" + 
						  "QQQQQQQQQQQQQQQQ" + 
						  "QQQQQQQQQQQQQQQQ";
		Message msj = messageGenerator(boardString);
		String[][] board = ChessUtil.getBoard(msj.getData().getBoard());
		String color = msj.getData().getActual_turn();
		
		// Ante un bloqueo deberia retornar null
		assertEquals(null, Intelligence.kingProceed(msj, board, color));
		
		boardString = "rrhhbbqqkkbbhhrr" + 
				  "rrhhbbqqkkbbhhrr" + 
				  "pppppppppppppppp" + 
				  "pppppppppppppppp" + 
				  "                " + 
				  "                " + 
				  "                " + 
				  "                " + 
				  "                " + 
				  "       P        " + 
				  "                " + 
				  "PPPPPPPKPPPPPPPP" + 
				  "QQQQQQQQQQQQQQQQ" + 
				  "QQQQQQQQQQQQQQQQ" + 
				  "QQQQQQQQQQQQQQQQ" + 
				  "QQQQQQQQQQQQQQQQ";
		msj.getData().setBoard(boardString);
		board = ChessUtil.getBoard(msj.getData().getBoard());
		
		// Ante ningun bloqueo deberia retornar un JSON
		assertNotEquals(null, Intelligence.kingProceed(msj, board, color));
	}
	
	@Test
	public void bishopProceed() {
		String boardString = "rrhhbbqqkkbbhhrr" + 
						  "rrhhbbq kkbbhhrr" + 
						  "ppppppp pppppppp" + 
						  "ppppppp pppppppp" + 
						  "                " + 
						  "                " + 
						  "                " + 
						  "                " + 
						  "                " + 
						  "                " + 
						  "QQQQQQQQ QQQQQQQ" + 
						  "QQQQQQQQ QQQQQQQ" + 
						  "QQQQQQQQ QQQQQQQ" + 
						  "QQQQQ   B    QQQ" + 
						  "QQQQQQQQ QQQQQQQ" + 
						  "QQQQQQQQ QQQQQQQ";
		Message msj = messageGenerator(boardString);
		String[][] board = ChessUtil.getBoard(msj.getData().getBoard());
		String color = msj.getData().getActual_turn();
		
		// Ante un bloqueo deberia retornar null
		assertEquals(null, Intelligence.bishopProceed(msj, board, color));
		
		boardString = "rrhhbbqqkkbbhhrr" + 
				  "rrhhbbqqkkbbhhrr" + 
				  "pppppppppppppppp" + 
				  "pppppppppppppppp" + 
				  "                " + 
				  "                " + 
				  "                " + 
				  "                " + 
				  "                " + 
				  "                " + 
				  "                " + 
				  "PPPPPP PPP PPPPP" + 
				  "QQQQQQQ Q QQQQQQ" + 
				  "QQQQQQQQBQQQQQQQ" + 
				  "QQQQQQQQQQQQQQQQ" + 
				  "QQQQQQQQQQQQQQQQ";
		msj.getData().setBoard(boardString);
		board = ChessUtil.getBoard(msj.getData().getBoard());
		
		// Ante ningun bloqueo deberia retornar un JSON
		assertNotEquals(null, Intelligence.bishopProceed(msj, board, color));
	}
	
	@Test
	public void horseProceed() {
		String boardString = "rrhhbbqqkkbbhhrr" + 
						  "rrhhbbq kkbbhhrr" + 
						  "ppppppp pppppppp" + 
						  "ppppppp pppppppp" + 
						  "                " + 
						  "                " + 
						  "                " + 
						  "                " + 
						  "                " + 
						  "                " + 
						  "QQQQQQQQQQQQQQQQ" + 
						  "QQQQQQQQQQQQQQQQ" + 
						  "QQQQQQQQQQQQQQQQ" + 
						  "QQQQQQQQHQQQQQQQ" + 
						  "QQQQQQ QQQ QQQQQ" + 
						  "QQQQQQQ Q QQQQQQ";
		Message msj = messageGenerator(boardString);
		String[][] board = ChessUtil.getBoard(msj.getData().getBoard());
		String color = msj.getData().getActual_turn();
		
		// Ante un bloqueo deberia retornar null
		assertEquals(null, Intelligence.horseProceed(msj, board, color));
		
		boardString = "rrhhbbqqkkbbhhrr" + 
				  "rrhhbbqqkkbbhhrr" + 
				  "pppppppppppppppp" + 
				  "pppppppppppppppp" + 
				  "                " + 
				  "                " + 
				  "                " + 
				  "                " + 
				  "                " + 
				  "                " + 
				  "                " + 
				  "QQQQQQQQQQQQQQQQ" + 
				  "QQQQQQQQHQQQQQQQ" + 
				  "QQQQQQQQQQQQQQQQ" + 
				  "QQQQQQQQQQQQQQQQ" + 
				  "QQQQQQQQQQQQQQQQ";
		msj.getData().setBoard(boardString);
		board = ChessUtil.getBoard(msj.getData().getBoard());
		
		// Ante ningun bloqueo deberia retornar un JSON
		assertNotEquals(null, Intelligence.horseProceed(msj, board, color));
	}
	
	@Test
	public void queenAction() {
		String boardString = "rrhhbbqqkkbbhhrr" + 
				  "rrhhbbq kkbbhhrr" + 
				  "ppppppp pppppppp" + 
				  "ppppppp pppppppp" + 
				  "                " + 
				  "                " + 
				  "                " + 
				  "                " + 
				  "Q               " + 
				  "                " + 
				  "                " + 
				  "                " + 
				  "                " + 
				  "                " + 
				  "                " + 
				  "                ";
		Message msj = messageGenerator(boardString);
		String[][] board = ChessUtil.getBoard(msj.getData().getBoard());
		String color = msj.getData().getActual_turn();
		
		// Ante libertad de avance deberia retornar algo distinto a null
		assertNotEquals(null, Intelligence.queenAction(msj, board, color));
		
		boardString = "rrhhbbqqkkbbhhrr" + 
				  "rrhhbbqqkkbbhhrr" + 
				  "pppppppppppppppp" + 
				  "pppppppppppppppp" + 
				  "pppppppppppppppp" + 
				  "pppppppppppppppp" + 
				  "pppppppppppppppp" + 
				  "pppppppppppppppp" + 
				  "Q               " + 
				  "                " + 
				  "                " + 
				  "                " + 
				  "                " + 
				  "                " + 
				  "                " + 
				  "                ";
		msj.getData().setBoard(boardString);
		board = ChessUtil.getBoard(msj.getData().getBoard());
		
		// Ante ningun bloqueo deberia retornar un JSON
		assertEquals(null, Intelligence.queenAction(msj, board, color));
	}
	
}
