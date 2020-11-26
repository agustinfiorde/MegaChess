package app.megachess.models;

import lombok.Data;

/**
 * Modelo para la action y event que viene en los JSON de respuesta. Otra
 * funcion se encarga de desestructurar o formar el JSON
 * 
 * @author AsteriX
 *
 */
@Data
public class Message {
	private String action;// login, get_connected_users, challenge, accept_challenge, move
	private String event;// update_user_list, ask_challenge, your_turn, gameover
	private DataMessage data;

}
