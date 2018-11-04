package resource.model.conector;

import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Conecto para MYSQL <br> 
 * @author Marck-G
 * @see {@link DataBaseConection}
 *
 */
class MysqlConector extends DataBaseConection {
		private static MysqlConector instance;
		
		
		/**
		 * 
		 * @return instancia única de MysqlConector
		 */
		public static MysqlConector instance() {
			return ( instance == null )? instance = new MysqlConector(): instance;
		}
		
		private MysqlConector() {
			super();
		}
		
				
		@Override
		public void conect() throws ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException {
			Class.forName( "com.mysql.jdbc.Driver" );
			connection = DriverManager.getConnection( url, username, pass );
		}
		

}
