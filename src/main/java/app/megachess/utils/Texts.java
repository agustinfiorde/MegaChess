package app.megachess.utils;

public class Texts {

	public static final String get_connected_users= "{'action':'get_connected_users',data:{}}";

	public static String challenge(String oponent) {
		return "{" + 
				"action : challenge," + 
				"data: {" + 
					"username: "+oponent+ 
					"message: mensaje para el otro usuario" + 
						"}" + 
				"}";
	}
	
	public static String accept_challenge(String board_id) {
		return "{" + 
				"action: 'accept_challenge'," + 
				"data: {" + 
				"board_id:"+ board_id + 
				"}" + 
				"}";
	}
	
}
