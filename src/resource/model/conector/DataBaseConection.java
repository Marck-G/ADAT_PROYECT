package resource.model.conector;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
/**
 * Clase abstracta que se encarga de la conexión genérica <br>
 * a una base de datos
 * @author Marck-G
 *
 */
 public abstract class DataBaseConection {
	private static final int MAX_HISTORIAL = 10;
	protected 	String	url;
	protected 	String	username;
	protected 	String	pass;
	protected 	Connection connection;
	private 	ArrayList< PreparedStatement > historial;
	
	DataBaseConection() {
		historial = new ArrayList< PreparedStatement >();
	}
	
	/**
	 * Establecemos los par&aacute;metros necesarios
	 * @param url
	 * @param username
	 * @param pass
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public void setConectionData( String url, String username, String pass ) {
		this.url = url;
		this.username = username;
		this.pass = pass;
		
	}
	
	/**
	 * Conecta con la base de datos
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 */
	public abstract void conect() throws ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException ;
	
	public ResultSet executeSql( String sql ) throws SQLException {
		PreparedStatement p = connection.prepareStatement( sql );
		//comprobamos el numero de elementos
		checkHistorial();
		// añadimos al historial
		historial.add( p );
		
		return p.executeQuery();
	}
	
	/**
	 * Ejecuta un PreaparedStatement
	 * @param pr
	 * @return ResulSet con los datos de la ejecuci&oacute;n
	 * @throws SQLException
	 */
	public ResultSet executeStatement( PreparedStatement pr ) throws SQLException {
		//comprobamos el numero de elementos
		//checkHistorial();
		// añadimos al historial
		historial.add( pr );
		
		return pr.executeQuery();
	}
	/**
	 * Recuperar un peraredStatement ya ejecutado
	 * @param index
	 * @return preparedStatement
	 */
	public PreparedStatement getStatement( int index ) {
		return historial.get( index );
	}
	
	/**
	 * Recupera el &uacute;ltimo PreparedStatement ejecutado
	 * @return PreparedStatement 
	 */
	public PreparedStatement getLastStatement() {
		return getStatement( historial.size() - 1 );
	}
	
	// revisa que solo haya el maximo de elementos en el historial
	public DataBaseConection checkHistorial() throws SQLException {
		int leng;
		Iterator< PreparedStatement > it = historial.iterator();
		// comprobamos que no se pase del maximo de elementos
		// si es asi borramos los ultimos que son los primeros
		while (it.hasNext() && ( leng = historial.size() ) > MAX_HISTORIAL ) {
			PreparedStatement pre = (PreparedStatement) it.next();
			it.remove();
		}
		// revisamos que tods los statements esten cerrados
		// así nos ahorramos tener que cerrarlos constantemente
		for (int i = 0; i < historial.size() - 1 ; i++) {
			System.out.println(String.format("%-20s: %b","Elemento "+ i, historial.get( i ).isClosed()) );
			if ( !historial.get( i ).isClosed() ) {
				historial.get( i ).getResultSet().close();
				historial.get( i ).close();
				
			}
		}
		return this;
	}
	/**
	 * Cierra la conexi&oacute;n
	 * @throws SQLException
	 */
	public void close() throws SQLException {
		if( connection != null )
			connection.close();
	}
	
	/**
	 * Cierra la última conexión a la base de datos
	 * @throws SQLException
	 */
	public void closeLastStatement() throws SQLException {
		historial.get( historial.size() - 1 ).close();
	}
	


	/**
	 * @return the url
	 */
	public String getUrl() {
		return url;
	}

	/**
	 * @param url the url to set
	 */
	public void setUrl(String url) {
		this.url = url;
	}

	/**
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * @param username the username to set
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 * @return the pass
	 */
	public String getPass() {
		return pass;
	}

	/**
	 * @param pass the pass to set
	 */
	public void setPass(String pass) {
		this.pass = pass;
	}

	/**
	 * @return the connection
	 */
	public Connection getConnection() {
		return connection;
	}

	
	@Override
	public String toString() {
	return username+"|"+pass+"|"+url;
	}
	
}
