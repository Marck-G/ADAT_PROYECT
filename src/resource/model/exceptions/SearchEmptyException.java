package resource.model.exceptions;

public class SearchEmptyException extends Exception {
	private static final long serialVersionUID = -500067771068530223L;

	public SearchEmptyException() {
		super( "No se ha encontrado datos correspondientes a la busqueda" );
	}
}
