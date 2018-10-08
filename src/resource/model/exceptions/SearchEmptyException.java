package resource.model.exceptions;

public class SearchEmptyException extends Exception {
	public SearchEmptyException() {
		super( "No se ha encontrado datos correspondientes a la busqueda" );
	}
}
