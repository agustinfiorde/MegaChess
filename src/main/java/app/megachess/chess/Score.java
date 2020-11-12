package app.megachess.chess;

/**
 * 
 * @author AsteriX
 *
 */
import lombok.Data;

@Data
public class Score {
	
	/*
	 * 
	 */
	
	
	private Integer score;

	/**
	 * 
	 * @param value
	 */
	public void addScore(Integer value) {
		this.score += value;
	}

	/**
	 * 
	 * @param value
	 */
	public void substractScore(Integer value) {
		this.score -= value;
	}

}
