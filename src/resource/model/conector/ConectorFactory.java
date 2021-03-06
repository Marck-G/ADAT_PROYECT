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
	public static final int DERBY_DB	= 40;
	
	private static DataBaseConection baseActiva;
	
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
			MysqlConector.instance().setConectionData(
					"jdbc:mysql://127.0.0.1:3306/gestion_libros",
					"root", "tiger");
			return MysqlConector.instance();
		case DERBY_DB:
			DerbyConector.instancia().setConectionData( 
					DerbyConector.instancia().getDbDir() + ";create=true",
					"","");
			return DerbyConector.instancia();
		default:
			return null;
		}
	}
	
	
	/**
	 * Se establece la base activa
	 * @param id
	 */
	public static void setDataBase( int id ) {
		baseActiva = makeConector( id );
	}
	
	/**
	 * 
	 * @return instancia activa de base de datos
	 */
	public static DataBaseConection getBaseActiva() {
		return baseActiva;
	}
	
}
