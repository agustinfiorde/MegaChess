package app.megachess.services;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import app.megachess.entities.DataGame;

@Repository
public interface DataGameRepository extends JpaRepository<DataGame, String>{

}
