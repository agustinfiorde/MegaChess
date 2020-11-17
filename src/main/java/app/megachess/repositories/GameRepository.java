package app.megachess.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import app.megachess.chess.Game;

@Repository
public interface GameRepository extends JpaRepository<Game, String>{

}
