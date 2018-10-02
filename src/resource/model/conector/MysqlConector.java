package resource.model.conector;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MysqlConector {
		private static MysqlConector instance;
		private String	url;
		private String	username;
		private String	pass;
		private Connection connection;
		
		/**
		 * 
		 * @return instancia única de MysqlConector
		 */
		public static MysqlConector instance() {
			return ( instance == null )? new MysqlConector(): instance;
		}
		
		private MysqlConector() {}
		
		/**
		 * establecemos los parametros necesarios
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
		 */
		public void conect() throws ClassNotFoundException, SQLException {
			Class.forName( "com.mysql.jdbc.Driver" );
			connection = DriverManager.getConnection( url, username, pass );
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
