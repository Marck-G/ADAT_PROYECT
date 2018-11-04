package resource.model.conector.derby;

import java.sql.Connection;
import java.sql.SQLException;

import resource.model.conector.ConectorFactory;
import resource.model.conector.DataBaseConection;

public final class TableCreation {
	private DataBaseConection bd;
	private Connection conn;
	
	private static TableCreation t;
	
	public static TableCreation instancia() {
		if ( t == null )
			t = new TableCreation();
		return t;
	}
	
	private TableCreation() {
		bd = ConectorFactory.getBaseActiva();
		conn = bd.getConnection();
	}
	
	
	public void createTableLibro() throws SQLException {
		conn.createStatement().execute( "Create table libro");
	}
	
	public void createTableAlumno() throws SQLException {
		conn.createStatement().execute( "Create table alumno");
	}
	
	public void createTablePrestamo() throws SQLException {
		conn.createStatement().execute( "create table prestamo");
	}
	
	public void createTableHistorico() throws SQLException {
		conn.createStatement().execute( "Create table historico");

	}
	
}
