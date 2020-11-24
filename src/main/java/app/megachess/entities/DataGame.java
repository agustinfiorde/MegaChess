package app.megachess.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import lombok.Data;

/*
 * this entity is to persist data of the games played
 */
@Entity
@Data
@Table(name="datasgames")
public class DataGame {

	@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid2")
	private String id;

	private String boardId;

	private String board;

}
