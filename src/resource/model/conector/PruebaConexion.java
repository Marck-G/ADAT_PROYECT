package resource.model.conector;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PruebaConexion {
 	public static void main(String[] args) throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {
 		ConectorFactory.setDataBase( ConectorFactory.MYSQL_DB );
		DataBaseConection b = ConectorFactory.getBaseActiva() ;
		b.conect();
		PreparedStatement s = ConectorFactory.getBaseActiva().connection.prepareStatement("SELECT dni FROM alumno");
		ResultSet r = s.executeQuery();
		while( r.next() ) {
			System.out.println( r.getString("dni") );
		}
	}
}
