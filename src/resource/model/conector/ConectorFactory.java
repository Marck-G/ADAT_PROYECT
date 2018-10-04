package resource.model.conector;

/**
 * <br/>
 * 
 * Clase encargada de establecer la conexión con la base de datos <br/>
 * @see {@link DataBaseConection}
 * @author Marck-G
 *
 */
public class ConectorFactory {
	// ====| CONSTANTES DE BASE DE DATOS |====
	public static final int MYSQL_DB	= 36;
	public static final int SQLLITE_DB	= 40;
	
	private ConectorFactory() {
	}
	
	/**
	 * Crea un conector segun el id que se le pase
	 * @param id
	 * @return DataBaseConection
	 */
	public static  DataBaseConection makeConector( int id ) {
		switch ( id ) {
		case MYSQL_DB:
			return MysqlConector.instance();
		case SQLLITE_DB:
			return null;
		default:
			return null;
		}
	}
	
	
	
}
