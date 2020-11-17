package app.megachess.chess;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.GenericGenerator;

import app.megachess.enums.Profile;
import lombok.Data;

@Entity
@Data
public class Game implements Serializable{

	private static final long serialVersionUID = 6522896498689132123L;
	
	@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid2")
	private String id;
	
	private String myColor;
	
	private String opponent_username;
	private String board_id;
	
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
