package resource.model.conector;

import java.io.File;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import resource.model.conector.derby.TableCreation;

public class DerbyConector extends DataBaseConection {
	
	private String dbDir;
	
	// ==========| SINGLETON |===========
	private static DerbyConector instancia;
	
	/**
	 * 
	 * @return la instancia activa
	 */
	public static DerbyConector instancia() {
		if( instancia == null )
			instancia = new DerbyConector();
		return instancia;
	}
	
	// cosntructor
	private DerbyConector() {
		// cogemos el directorio actual
		File file =  new File( this.getClass().getResource(".").getPath() ) ;
		String dirActual = file.getAbsolutePath();
		// obtenemos el indice de la carpeta bin
		int mainDir = dirActual.indexOf( "bin" );
		// nos quedamos con los directorios anteriores
		String db_dir = dirActual.substring(0,mainDir );
		// agregamos el nuevo directorio
		dbDir = "jdbc:derby:" + db_dir + "db";
	}

	@Override
	public void conect() throws ClassNotFoundException, SQLException, 
	InstantiationException, IllegalAccessException {
		Class.forName( "org.apache.derby.jdbc.EmbeddedDriver" );
		connection = DriverManager.getConnection(url, username, pass );
		connection.setAutoCommit(false);
		if( !this.existDataBase() ) {
			TableCreation.instancia().createTables();
		}
		connection.commit();
	}
	/**
	 * 
	 * @return the Database Directory
	 */
	public String getDbDir() {
		return dbDir;
	}
	
	/**
	 * Comprobamos si existe la base de datos en la url pasada
	 * @return <ul>
	 * 	<li> <strong> true:</strong> si existe la base de datos</li>
	 * <li> <strong> false:</strong> si no existe la base de datos o en
	 * caso de error</li>
	 * </ul>
	 */
	public boolean existDataBase() {
		try {
			ResultSet r = connection.createStatement()
					.executeQuery( "Select count(*) from libro" );
			if ( r.next() ) {
				r.close();
				return true;
			}
		} catch ( SQLException e ) {
			return false;
		}
		return false;
	}
	
	
	
	
	
	public static void main(String[] args) throws Exception {
		DerbyConector d = new DerbyConector();
		d.setConectionData( d.getDbDir() + ";create=true", "", "");
		d.conect();
		
		System.out.println( d.existDataBase() );
		d.close();
	}

}
