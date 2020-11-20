package app.megachess.services;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import app.megachess.entities.DataGame;
import app.megachess.entities.Game;
import app.megachess.enums.Event;
import app.megachess.websocket.models.DataMessage;
import app.megachess.websocket.models.Message;

@Service
public class GameDataService {

	@Autowired
	private GameRepository gameRepository;

	private Integer startMoves = 199;

	@Autowired
	private DataGameRepository dataGameRepository;

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = { Exception.class })
	public void saveGame(Message msj) {
		Game g;
		if (msj.getData().getMove_left() == startMoves || msj.getData().getMove_left() == startMoves - 1) {

			g = new Game();
			g.setStart(new Date());
			g.setBoardId(msj.getData().getBoard_id());
			g.setOpponentUsername(msj.getData().getOpponent_username());
			g.setMyColor(msj.getData().getActual_turn());
			gameRepository.save(g);
		} else if (msj.getEvent().equals(Event.GAMEOVER.getString())) {
			g = new Game();
			g.setFinish(new Date());
			// Falta ver la manera de guardar y detectar los pérfiles
			// g.setOponentProfile(oponentProfile);
			// g.setMyProfile(myProfile);
			msj.getData().getBlack_score();
		} else {
			g = gameRepository.getByBoardId(msj.getData().getBoard_id());
			g.getDatas().add(saveDataGame(msj.getData()));
			gameRepository.save(g);
		}
	}

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = { Exception.class })
	public DataGame saveDataGame(DataMessage data) {
		DataGame dg = new DataGame();
		dg.setBoardId(data.getBoard_id());
		dg.setBoard(data.getBoard());
		return dataGameRepository.save(dg);
	}
}
