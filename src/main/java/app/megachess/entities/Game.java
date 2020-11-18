package app.megachess.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.GenericGenerator;

import app.megachess.enums.Profile;
import lombok.Data;

/*
 * this entity is to persist the games played
 */
@Entity
@Data
public class Game implements Serializable {

	private static final long serialVersionUID = 6522896498689132123L;

	@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid2")
	private String id;

	private String boardId;

	private String myColor;

	private String opponentUsername;

	@OneToMany
	private List<DataGame> datas;

	@Enumerated(EnumType.STRING)
	private Profile myProfile;
	@Enumerated(EnumType.STRING)
	private Profile oponentProfile;

	private String myScore;
	private String yourScore;

	@Temporal(TemporalType.TIMESTAMP)
	private Date start;
	@Temporal(TemporalType.TIMESTAMP)
	private Date finish;

}
