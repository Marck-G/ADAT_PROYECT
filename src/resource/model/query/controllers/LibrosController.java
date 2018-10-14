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
	/**
	 * 
	 * @return ArrayList< Libro > Todos los libros de la base de datos
	 * @throws SQLException
	 * @throws EmptyTableException
	 */
	public ArrayList< Libro > getLibros() throws SQLException, EmptyTableException{
		ArrayList< Libro > out = new ArrayList< Libro >();
		String sql = "SELECT " + DATOS_LIBRO + "FROM libro";
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
	 * Busca un libro según coincidencias
	 * @param codigo | titulo | isbn | autor | editorial | estado
	 * @return libro
	 * @throws SQLException
	 * @throws SearchEmptyException 
	 * @throws LibroNotFoundException 
	 */
	public ArrayList< Libro > getLibros( String codigo ) throws SQLException, LibroNotFoundException{
		String sql = "SELECT " + DATOS_LIBRO + " FROM libro WHERE codigo=? OR titulo LIKE ? OR autor LIKE ?"
				+ " OR editorial LIKE ? OR asignatura LIKE ? OR estado LIKE ?";
		PreparedStatement pr = connection.getConnection().prepareStatement( sql );
		pr.setString( 1, codigo );
		pr.setString( 2, "%"+ codigo + "%" );
		pr.setString( 3, "%"+ codigo + "%" );
		pr.setString( 4, "%"+ codigo + "%" );
		pr.setString( 5, "%"+ codigo + "%" );
		pr.setString( 6, "%"+ codigo + "%" );
		ResultSet resul = connection.executeStatement( pr );
		ArrayList< Libro > out = new ArrayList< Libro >();
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
			throw new LibroNotFoundException();
		return out;
	}
	/**
	 * Busca un libro por su codigo
	 * @param codigo
	 * @return libro buscado
	 * @throws SQLException
	 * @throws LibroNotFoundException
	 */
	public Libro getLibrosCodigo( String codigo ) throws SQLException, LibroNotFoundException {
		String sql = "SELECT " + DATOS_LIBRO + " FROM libro WHERE codigo=? ";
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
			String sql = "INSERT INTO libro VALUES( ?,?,?,?,?,?,?)";
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
		if( addLibros != null ) 
			addLibros.executeBatch();
	}
	
	/**
	 * Elimina un libro de la base de datos
	 * @param codigo
	 * @throws SQLException 
	 */
	public void removeLibro( String codigo ) throws SQLException {
		if( addLibros == null ) {
			String sql = "DELETE FROM libro WHERE codigo = ?";
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
	
	public void updateLibro( String codigo, Libro libro ) throws SQLException {
		String sql = "UPDATE libro set isbn=?, titulo=?, autor=?, editorial=?, asignatura=?, estado=? "
				+ " WHERE codigo=?";
		PreparedStatement pr = connection.getConnection().prepareStatement( sql );
		pr.setString( 1, libro.getIsbn() );
		pr.setString( 2, libro.getTitulo() );
		pr.setString( 3, libro.getAutor() );
		pr.setString( 4, libro.getEditorial() );
		pr.setString( 5, libro.getAsignatura() );
		pr.setString( 6, libro.getEstado().estado() );
		pr.setString( 7, libro.getCodigo() );
		pr.executeUpdate();
	}
	

}
