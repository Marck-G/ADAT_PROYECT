package resource.model.query.controllers;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import resource.model.beans.Estado;
import resource.model.beans.Libro;
import resource.model.conector.ConectorFactory;
import resource.model.conector.DataBaseConection;
import resource.model.exceptions.SearchEmptyException;

public class LibrosController {
	private static LibrosController instancia;
	private final static String DATOS_LIBRO = " codigo, titulo, autor, editorial, asignatura, estado ";
	
	public static LibrosController instancia() {
		return ( instancia == null )? new LibrosController():instancia; 
	}
	
	private DataBaseConection connection;
	
	private LibrosController() {
		connection = ConectorFactory.getBaseActiva();
	}
	
	/**
	 * Buscar libros por asignatura
	 * @param asignatura
	 * @return ArrayList< Libro >
	 * @throws SQLException
	 * @throws SearchEmptyException
	 */
	public ArrayList< Libro > getLibrosAsignatura( String asignatura ) throws SQLException, SearchEmptyException{
		ArrayList< Libro > out = new ArrayList< Libro >();
		String sql = "SELECT " + DATOS_LIBRO 
				+ "FROM LIBRO"
				+ " WHERE asigntura = upper(?)";
		PreparedStatement pr = connection.getConnection().prepareStatement( sql );
		pr.setString( 1, asignatura );
		ResultSet resul = pr.executeQuery();
		while ( resul.next() ) {
			//creamos el libro con los datos de la base
			Libro l = new Libro(
					resul.getString( "codigo" ),
					resul.getString( "titulo" ),
					resul.getString( "autor" ),
					resul.getString( "editorial" ), 
					resul.getString( "asignatura" ),
					Estado.getEstadoFrom( resul.getString( "estado" ) ) );
			//lo metemos en el arraylist
			out.add( l );
		}
		
		if( out.isEmpty() )
			throw new SearchEmptyException();
		return out;
	}
	
	/**
	 * Busca un libro según su código
	 * @param codigo
	 * @return libro
	 * @throws SQLException
	 * @throws SearchEmptyException 
	 */
	public Libro getLibrosCodigo( String codigo ) throws SQLException, SearchEmptyException{
		String sql = "SELECT " + DATOS_LIBRO + " FROM LIBRO WHERE codigo=?";
		PreparedStatement pr = connection.getConnection().prepareStatement( sql );
		pr.setString( 1, codigo );
		ResultSet resul = connection.executeStatement( pr );
		if( resul.next() )
			return new Libro(
					resul.getString( "codigo" ),
					resul.getString( "titulo" ),
					resul.getString( "autor" ),
					resul.getString( "editorial" ), 
					resul.getString( "asignatura" ),
					Estado.getEstadoFrom( resul.getString( "estado" ) ) );
		throw new SearchEmptyException();
	}
	
	public void addLibro( Libro libro ) {
		
	}

}
