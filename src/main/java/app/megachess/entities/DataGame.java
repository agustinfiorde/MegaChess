package app.megachess.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;

import org.hibernate.annotations.GenericGenerator;

import lombok.Data;

/*
 * this entity is to persist data of the games played
 */
@Entity
@Data
public class DataGame implements Serializable {

	private static final long serialVersionUID = 6522896498689132123L;

	@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid2")
	private String id;

	private String boardId;

	@Lob
	@Column(name = "board", length = 288)
	private String board;

}
