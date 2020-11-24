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

	private Integer startMoves = 199;

	@Autowired
	private GameRepository gameRepository;

	@Autowired
	private DataGameRepository dataGameRepository;

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = { Exception.class })
	public void saveGame(Message msj) {
		Game g;
		if (msj.getData().getMove_left() == startMoves) {

			g = new Game();
			g.setStart(new Date());
			g.setBoardId(msj.getData().getBoard_id());
			gameRepository.save(g);
		} else {
			updateGame(msj);
		}
	}

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = { Exception.class })
	public void updateGame(Message msj) {
		Game g = gameRepository.getByBoardId(msj.getData().getBoard_id());

		if (msj.getEvent().equals(Event.GAMEOVER.getString())) {

			g = new Game();
			g.setFinish(new Date());

			String fiordeX = "fiordeX";
			Integer fiordeXScore;
			String myColor;

			String opponentUsername;
			Integer opponentScore;

			if (msj.getData().getWhite_username().equals("fiordeX")) {
				opponentScore = Integer.parseInt(msj.getData().getBlack_score());
				opponentUsername = msj.getData().getBlack_username();
				fiordeXScore = Integer.parseInt(msj.getData().getWhite_score());
				myColor = "white";
			} else {
				opponentScore = Integer.parseInt(msj.getData().getWhite_score());
				opponentUsername = msj.getData().getWhite_username();
				fiordeXScore = Integer.parseInt(msj.getData().getBlack_score());
				myColor = "black";
			}

			if (fiordeXScore > opponentScore) {
				gameRepository.delete(g);
			} else {
				g.setOpponentUsername(opponentUsername);
				g.setYourScore(opponentScore);
				g.setMyColor(myColor);
				g.setMyScore(fiordeXScore);
				gameRepository.save(g);
			}
		} else {
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
