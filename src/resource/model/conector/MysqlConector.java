package resource.model.conector;

import java.sql.DriverManager;
import java.sql.SQLException;

class MysqlConector extends DataBaseConection {
		private static MysqlConector instance;
		
		
		/**
		 * 
		 * @return instancia única de MysqlConector
		 */
		public static MysqlConector instance() {
			return ( instance == null )? new MysqlConector(): instance;
		}
		
		private MysqlConector() {}
				
		@Override
		public void conect() throws ClassNotFoundException, SQLException {
			Class.forName( "com.mysql.jdbc.Driver" );
			connection = DriverManager.getConnection( url, username, pass );
		}
		

}
