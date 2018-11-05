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
	
	
	public void createTables() throws SQLException {
		createTableAlumno();
		createTableLibro();
		createTableHistorico();
		createTablePrestamo();
	}
	
	public void createTableLibro() throws SQLException {
		conn.createStatement().execute( "CREATE TABLE libro (" + 
				"  codigo varchar(13) NOT NULL," + 
				"  isbn varchar(13) DEFAULT NULL," + 
				"  titulo varchar(70) DEFAULT NULL," + 
				"  autor varchar(40) DEFAULT NULL," + 
				"  editorial varchar(30) DEFAULT NULL," + 
				"  asignatura varchar(20) DEFAULT NULL," + 
				"  estado varchar(30) DEFAULT NULL," + 
				"  PRIMARY KEY (codigo)" + 
				")");
	}
	
	public void createTableAlumno() throws SQLException {
		conn.createStatement().execute( "CREATE TABLE alumno (" + 
				"  dni varchar(9) NOT NULL," + 
				"  nombre varchar(20) DEFAULT NULL," + 
				"  ap1 varchar(30)," + 
				"  ap2 varchar(30) DEFAULT NULL," + 
				"  PRIMARY KEY (dni)" + 
				")");
	}
	
	public void createTablePrestamo() throws SQLException {
		conn.createStatement().execute( "CREATE TABLE prestamo (" + 
				"  alumno varchar(9) NOT NULL," + 
				"  libro varchar(13) NOT NULL," + 
				"  fecha_alta date NOT NULL," + 
				"  fecha_dev date DEFAULT NULL," + 
				"  PRIMARY KEY (alumno,libro,fecha_alta)" + 
				")");
	}
	
	public void createTableHistorico() throws SQLException {
		conn.createStatement().execute( "CREATE TABLE historico (" + 
				"  alumno varchar(9) NOT NULL," + 
				"  libro varchar(13) NOT NULL," + 
				"  fecha_alta date NOT NULL," + 
				"  fecha_dev date DEFAULT NULL," + 
				"  PRIMARY KEY (alumno,libro,fecha_alta)" + 
				")");

	}
	
}
