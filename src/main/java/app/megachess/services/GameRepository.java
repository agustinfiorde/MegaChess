package app.megachess.services;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import app.megachess.entities.Game;

@Repository
public interface GameRepository extends JpaRepository<Game, String>{

	@Query("SELECT g from Game g WHERE g.boardId LIKE :boardId")
	public Game getByBoardId(@Param("boardId") String boardId);
	
}
