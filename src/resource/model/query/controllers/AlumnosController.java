package resource.model.query.controllers;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import resource.model.beans.Alumno;
import resource.model.conector.ConectorFactory;
import resource.model.conector.DataBaseConection;
import resource.model.exceptions.AlumnoNotFoundException;
import resource.model.exceptions.EmptyTableException;

/**
 * Se encarga de la petici&oacute;n de  informaci&oacute;n a la base de datos<br>
 * relacionada con alumnos.
 * @author Marck-G
 *
 */

public class AlumnosController {
	// ========| SINGLETON |=========
	private static AlumnosController instancia;
	
	/**
	 * 
	 * @return Instancia activa de AlumnosController
	 */
	public static AlumnosController instancia() {
		return ( instancia == null )? new AlumnosController(): instancia;
	}
	// ========| FIN SINGLETON |=======
	
	private DataBaseConection conection;
	private PreparedStatement addAlum;
	private PreparedStatement rmAlum;
	
	private AlumnosController() {
		// cogemos la única base activa 
		conection = ConectorFactory.getBaseActiva();
	}
	
	/**
	 * Recoge todos los alumnos de la base de datos
	 * @return ArrayList< Alumno >
	 * @throws SQLException
	 * @throws EmptyTableException 
	 */
	public ArrayList< Alumno > getAlumnos() throws SQLException, EmptyTableException{
		ArrayList< Alumno > out = new ArrayList< Alumno >();
		//preparamos la sentencia sql
		String sql = "SELECT dni,nombre, ap1, ap2 FROM alumno";
		ResultSet r = conection.executeSql( sql );
		while ( r.next() ) {
			//creamos un nuevo alumno
			Alumno l = new Alumno(
					r.getString( "dni" ), 
					r.getString( "nombre" ), 
					r.getString( "ap1" ), 
					r.getString( "ap2" ) );
			//lo guardamos en el array de salida
			out.add( l );
		}
		if ( out.isEmpty() )
			return out;			
		throw new EmptyTableException();
	}
	/**
	 * Busca un alumno en la base de datos
	 * @param dni
	 * @return Alumno buscado
	 * @throws SQLException
	 * @throws AlumnoNotFoundException 
	 */
	public Alumno getAlumno( String dni ) throws SQLException, AlumnoNotFoundException {
		String sql = "SELECT dni,nombre, ap1, ap2 FROM alumno WHERE dni=?";
		PreparedStatement p = conection.getConnection().prepareStatement( sql );
		// metemos el dni
		p.setString( 1, dni );
		ResultSet r = conection.executeStatement( p );
		if ( r.next() ) {
			return new Alumno( 
					r.getString( "dni" ), 
					r.getString( "nombre" ), 
					r.getString( "ap1" ), 
					r.getString( "ap2" ) );
		}
		// no se encontro al alumno
		throw new AlumnoNotFoundException();
	}
	
	/**
	 * Busca un alumno en la base de datos
	 * @param alumno
	 * @return Alumno
	 * @throws SQLException
	 * @throws AlumnoNotFoundException
	 */
	public Alumno getAlumno( Alumno alumno ) throws SQLException, AlumnoNotFoundException {
		return getAlumno( alumno.getDni() );
	}
	
	/**
	 * Añade un alumno a la tabla
	 * @param alum
	 * @return 0 si hay algun error
	 * @throws SQLException
	 */
	public void addAlumno( Alumno alum ) throws SQLException {
		if ( addAlum == null ) {
			String sql = "INSERT INTO alumno VALUES(?,?,?,?)";
			addAlum = conection.getConnection().prepareStatement( sql );
		}
		// metemos los datos
		addAlum.setString( 1, alum.getDni() );
		addAlum.setString( 2, alum.getNombre() );
		addAlum.setString( 3, alum.getAp1() );
		addAlum.setString( 4, alum.getAp2() );
		addAlum.addBatch();		
	}
	
	/**
	 * Añade alumnos a la tabla
	 * 
	 * @param alumnos
	 * @return n&uacute;mero de alumnos a&ntilde;adidos
	 * @throws SQLException
	 */
	public void addAlumno( ArrayList<Alumno> alumnos ) throws SQLException {
		for (Alumno a : alumnos) {
			addAlumno(a);
		}
	}
	
	/**
	 * Elimina un alumno
	 * @param dni
	 * @throws SQLException
	 */
	public void removeAlumno( String dni ) throws SQLException {
		if( rmAlum == null ) {
			String sql = "DELETE FROM ALUMNO WHERE dni = ?";
			rmAlum = conection.getConnection().prepareStatement( sql );
		}
		rmAlum.setString( 1, dni ); 
		rmAlum.addBatch();
	}
	
	/**
	 * Elimina un alumno
	 * @param alumno
	 * @throws SQLException
	 */
	public void removeAlumno( Alumno alumno ) throws SQLException {
		removeAlumno( alumno.getDni() );
	}
	
	/**
	 * Elimina varios alumnos
	 * @param alumnos
	 * @throws SQLException
	 */
	public void removeAlumno( ArrayList<Alumno> alumnos ) throws SQLException{
		for (Alumno alumno : alumnos) {
			removeAlumno( alumno );
		}
	}
	
	/**
	 * Ejecutamos la elimninaciones
	 * @throws SQLException
	 */
	public void removeAlumno() throws SQLException {
		if ( rmAlum != null ) {
			rmAlum.executeBatch();
			rmAlum.clearBatch();
		}
	}
	
}
