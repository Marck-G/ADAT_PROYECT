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
	 
	 @Override
	public String toString() {
		return estado;
	}
	 
	 /**
	  * Devuelve un estado según el string que vuelve 
	  * @param estadoStr
	  * @return
	  */
	 public static Estado getEstadoFrom( String estadoStr ) {
		 for ( Estado e : Estado.values() ) {
			if( estadoStr.equalsIgnoreCase( e.estado() ) )
				return e;
		}
		 return null;
	 }
}
