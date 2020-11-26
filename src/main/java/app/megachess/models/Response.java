package app.megachess.models;

import lombok.Data;

/**
 * Este modelo me sirve para los datos de una pieza segun la necesidad. Primero
 * manejo un valor auxiliar del tipo boolean para ver si se pudo o no encontrar
 * o su se puede o no usar la pieza. Un String para saber que pieza es la que
 * vino y la posicion fromRow y fromCol
 * 
 * @author AsteriX
 *
 */
@Data
public class Response {

	private boolean exist;
	private int fromRow;
	private int fromCol;

	private String piece;

}
