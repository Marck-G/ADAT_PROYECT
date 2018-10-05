package resource.model.exceptions;

public class EmptyTableException extends Exception {
	public EmptyTableException() {
		super( "No se encontro ningún dato. Tabla vacía" );
	}
}
