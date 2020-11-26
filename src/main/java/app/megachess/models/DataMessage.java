package app.megachess.models;

import java.util.List;

import lombok.Data;

/**
 * Modelo para la data que viene en los JSON de respuesta, tiene todas las
 * posibles respuestas. Otra funcion se encarga de desestructurar o formar el
 * JSON
 * 
 * @author AsteriX
 *
 */
@Data
public class DataMessage {

	private String board_id;
	private String turn_token;
	private String username;
	private String actual_turn;
	private String board;
	private Integer move_left;

	private String opponent_username;

	private List<String> users_list;
	private String message;

	private Integer from_row;
	private Integer from_col;
	private Integer to_row;
	private Integer to_col;

	private String white_username;
	private String black_username;
	private String white_score;
	private String black_score;

}
