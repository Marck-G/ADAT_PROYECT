package resource.model.query.controllers;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import resource.model.beans.Estado;
import resource.model.beans.Libro;
import resource.model.conector.ConectorFactory;
import resource.model.conector.DataBaseConection;
import resource.model.exceptions.EmptyTableException;
import resource.model.exceptions.LibroNotFoundException;
import resource.model.exceptions.SearchEmptyException;

public class LibrosController {
	private static LibrosController instancia;
	private final static String DATOS_LIBRO = " codigo,isbn, titulo, autor, editorial, asignatura, estado ";
	
	public static LibrosController instancia() {
		return ( instancia == null )? instancia = new LibrosController():instancia; 
	}
	
	private DataBaseConection connection;
	private PreparedStatement addLibros;
	private PreparedStatement rmLibros;
	
	private LibrosController() {
		connection = ConectorFactory.getBaseActiva();
	}
	
	public ArrayList< Libro > getLibros() throws SQLException, EmptyTableException{
		ArrayList< Libro > out = new ArrayList< Libro >();
		String sql = "SELECT " + DATOS_LIBRO + "FROM LIBRO";
		ResultSet resul = connection.executeSql( sql );
		while( resul.next() ) {
			Libro l = new Libro(
					resul.getString( "codigo" ),
					resul.getString( "isbn" ),
					resul.getString( "titulo" ),
					resul.getString( "autor" ),
					resul.getString( "editorial" ),
					resul.getString( "asignatura" ), 
					Estado.getEstadoFrom( resul.getString( "estado" ) ) );
			out.add( l );
		}
		if( out.isEmpty() )
			throw new EmptyTableException();
		return out;
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
					resul.getString( "isbn" ),
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
	 * @throws LibroNotFoundException 
	 */
	public Libro getLibrosCodigo( String codigo ) throws SQLException, LibroNotFoundException{
		String sql = "SELECT " + DATOS_LIBRO + " FROM LIBRO WHERE codigo=?";
		PreparedStatement pr = connection.getConnection().prepareStatement( sql );
		pr.setString( 1, codigo );
		ResultSet resul = connection.executeStatement( pr );
		if( resul.next() )
			return new Libro(
					resul.getString( "codigo" ),
					resul.getString( "isbn" ),
					resul.getString( "titulo" ),
					resul.getString( "autor" ),
					resul.getString( "editorial" ), 
					resul.getString( "asignatura" ),
					Estado.getEstadoFrom( resul.getString( "estado" ) ) );
		throw new LibroNotFoundException();
	}
	
	/**
	 * Añade un libro a la base de datos
	 * @param libro
	 * @throws SQLException
	 */
	public void addLibro( Libro libro ) throws SQLException {
		
		if ( addLibros == null ) {
			String sql = "INSERT INTO LIBRO VALUES( ?,?,?,?,?,?,?)";
			addLibros = connection.getConnection().prepareStatement( sql );
		}
			
		addLibros.setString( 1, libro.getCodigo() );
		addLibros.setString( 2, libro.getIsbn() );
		addLibros.setString( 3, libro.getTitulo() );
		addLibros.setString( 4, libro.getAutor() );
		addLibros.setString( 5, libro.getEditorial() );
		addLibros.setString( 6, libro.getAsignatura() );
		addLibros.setString( 7, libro.getEstado().estado().toUpperCase() );
		addLibros.addBatch();
	}
	
	/**
	 * Añade varios libros a la base de datos
	 * @param libros
	 * @throws SQLException
	 */
	
	public void addLibro( ArrayList<Libro> libros ) throws SQLException{
		for ( Libro l : libros ) {
			addLibro( l );
		}
	}
	
	/**
	 * Ejecuta todas las adiciones de libros
	 * @return
	 * @throws SQLException
	 */
	public void addLibro() throws SQLException {
		addLibros.executeBatch();
		addLibros.clearBatch();
	}
	
	/**
	 * Elimina un libro de la base de datos
	 * @param codigo
	 * @throws SQLException 
	 */
	public void removeLibro( String codigo ) throws SQLException {
		if( addLibros == null ) {
			String sql = "DELETE FROM LIBRO WHERE codigo = ?";
			rmLibros = connection.getConnection().prepareStatement( sql );
		}		
		rmLibros.setString(1, codigo);
		rmLibros.addBatch();			
	}
	
	/**
	 * Eleimina un libro
	 * @param libro
	 * @throws SQLException
	 */
	public void removeLibro( Libro libro ) throws SQLException{
		removeLibro( libro.getCodigo() );
	}
	
	/**
	 * Elimina varios libros de la base de datos
	 * @param libros
	 * @throws SQLException
	 */
	public void removeLibro( ArrayList<Libro> libros ) throws SQLException{
		for (Libro l : libros) {
			removeLibro( l );
		}
	}
	
	/**
	 * Ejecuta todas la eliminaciones
	 * @throws SQLException
	 */
	public void removeLibro() throws SQLException {
		if( rmLibros != null ) {
			rmLibros.executeBatch();
			rmLibros.clearBatch();
		}
	}
	

}
