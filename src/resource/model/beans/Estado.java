package resource.model.beans;

/**
 * Enumeracion para el estado
 * @author Marck-G
 *
 */
public enum Estado {
	NUEVO("Nuevo"),
	U_NUEVO("Usado Nuevo"),
	U_SEMINUEVO("Usado Seminuevo"),
	U_ESTROPEADO("Usado Estropeado"),
	RESTAURADO("Restaurado");
	
	private String estado;
	
	 Estado( String msg ) {
		estado = msg;
	}
	 
	 /**
	  * Cogemos el texto del de la enumeracion
	  * @return estado : String
	  */
	 public String estado() {
		 return estado;
	 }
}
