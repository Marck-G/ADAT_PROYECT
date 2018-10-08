package resource.model.query.controllers;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import resource.model.beans.Libro;
import resource.model.conector.ConectorFactory;
import resource.model.conector.DataBaseConection;

public class LibrosController {
	private static LibrosController instancia;
	
	public static LibrosController instancia() {
		return ( instancia == null )? new LibrosController():instancia; 
	}
	
	private DataBaseConection connection;
	
	private LibrosController() {
		connection = ConectorFactory.getBaseActiva();
	}
	
	public ArrayList< Libro > getLibrosAssignatura( String asignatura ) throws SQLException{
		ArrayList< Libro > out = new ArrayList< Libro >();
		String sql = "SELECT codigo, titulo, autor, editorial, asignatura, estado "
				+ "FROM LIBRO"
				+ " WHERE asigntura = upper(?)";
		PreparedStatement pr = connection.getConnection().prepareStatement( sql );
		pr.setString( 1, asignatura );
		ResultSet resul = pr.executeQuery();
		while ( resul.next() ) {
			Libro l = new Libro(
					resul.getString( "codigo" ),
					resul.getString( "titulo" ),
					resul.getString( "autor" ),
					resul.getString(""), asignatura, estado)
			out.add( l );
		}
	}

}
