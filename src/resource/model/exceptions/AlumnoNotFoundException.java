package resource.model.exceptions;

public class AlumnoNotFoundException extends Exception {
	public AlumnoNotFoundException() {
		super( "No se ha encontrado al alumno buscado" );
	}
}
