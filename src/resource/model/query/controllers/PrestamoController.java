package resource.model.query.controllers;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import resource.model.beans.Alumno;
import resource.model.beans.Libro;
import resource.model.beans.Prestamo;
import resource.model.conector.ConectorFactory;
import resource.model.conector.DataBaseConection;
import resource.model.exceptions.AlumnoNotFoundException;
import resource.model.exceptions.LibroNotFoundException;

public class PrestamoController {
	// =======| SINGLETON |==========
	private static PrestamoController instancia;
	
	/**
	 * 
	 * @return insatancia activa de PrestamoController
	 */
	public static PrestamoController instancia() {
		if( instancia == null )
			instancia = new PrestamoController();
		return instancia;
	}
	// ========| FIN |=========
	
	public static final int DEVUELTO = 142;
	public static final int NO_DEVUELTO = 152;
	public static final int AMBOS = 100;
	
	private DataBaseConection connection;
	
	private PrestamoController() {
		connection = ConectorFactory.getBaseActiva();
	}
	
	private ArrayList<Prestamo> getPrestamosActivos() throws SQLException{
		ArrayList<Prestamo> out = new ArrayList<Prestamo>();
		String sql = "SELECT libro, alumno, fecha_alta, fecha_dev from prestamo";
		ResultSet resul = connection.executeSql(sql);
		while ( resul.next() ) {
			Date fec_al = new Date( resul.getDate("fecha_alta").getTime() );
			Date fec_dev = new Date( resul.getDate("fecha_dev").getTime() );
			Libro l = null;
			try {
				Prestamo p = new Prestamo( 
							LibrosController.instancia().getLibrosCodigo( resul.getString( "libro" ) ),
							AlumnosController.instancia().getAlumno( resul.getString( "alumno" ) ).get(0),
							fec_al,
							fec_dev,
							false );
				out.add( p );
			} catch (LibroNotFoundException | AlumnoNotFoundException e) {
				System.out.println( e.getMessage() );
			}
			
		}
		resul.close();
		return out;
	}
	
	private ArrayList<Prestamo> getPrestamosHistoricos() throws SQLException, LibroNotFoundException, AlumnoNotFoundException{
		ArrayList<Prestamo> out = new ArrayList<Prestamo>();
		String sql = "SELECT libro, alumno, fecha_alta, fecha_dev from historico";
		ResultSet resul = connection.executeSql(sql);
		while ( resul.next() ) {
			Date fec_al = new Date( resul.getDate("fecha_alta").getTime() );
			Date fec_dev = new Date( resul.getDate("fecha_dev").getTime() );
			try {
				Prestamo p = new Prestamo( 
						LibrosController.instancia().getLibrosCodigo( resul.getString( "libro" ) ),
						AlumnosController.instancia().getAlumno( resul.getString( "alumno" ) ).get(0),
						fec_al,
						fec_dev,
						true );
			out.add( p );
			} catch (LibroNotFoundException | AlumnoNotFoundException e) {
				System.out.println( e.getMessage() );
			}
			
		}
		return out;
	}
	
	/**
	 * Lista los prestamos seg&uacute;n el estado que se introduzca
	 * @param type
	 * @return ArrayList<Prestamo>
	 * @throws SQLException
	 * @throws LibroNotFoundException
	 * @throws AlumnoNotFoundException
	 */
	public ArrayList<Prestamo> getPrestamos( int type ) throws SQLException, LibroNotFoundException, AlumnoNotFoundException{
		switch ( type ) {
		case DEVUELTO:
			return getPrestamosHistoricos();
		case NO_DEVUELTO:
			return getPrestamosActivos();
		case AMBOS:
			ArrayList<Prestamo> ar1 = getPrestamosActivos();
			ar1.addAll( getPrestamosHistoricos() );
			return ar1;
		}
		
		return null;
	}
	
	/**
	 * Actualiza las tablas correspondientes para que un prestamos <br/>
	 * se considere devuelto
	 * @param p
	 * @param devuelto
	 * @throws SQLException
	 */
	public void setPrestamoDevuelto( Prestamo p ) throws SQLException {
		String sql1 = "DELETE FROM prestamo WHERE alumno = ? AND libro = ? AND fecha_alta = ?";
		String sql2 = "INSERT INTO historico(alumno, libro, fecha_alta, fecha_dev) VALUES( ?,?,?,?)";
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		//Borramos de la tabla de prestamos
		PreparedStatement rm = connection.getConnection().prepareStatement(sql1);
		rm.setString( 1, p.getAlumno().getDni() );
		rm.setString( 2, p.getLibro().getCodigo() );
		rm.setString( 3, format.format( p.getFecha_alta() ) );
		rm.executeUpdate();		
		// lo agragamos en la tabla de historicos
		PreparedStatement insert = connection.getConnection().prepareStatement( sql2 );
		insert.setString( 1, p.getAlumno().getDni() );
		insert.setString( 2, p.getLibro().getCodigo() );
		insert.setString( 3, format.format( p.getFecha_alta() ) );
		insert.setString( 4, format.format( p.getFecha_devolucion() ) );
		insert.executeUpdate();
	}
	
	public void addPrestamo( 
			String codigo, 
			String dni,
			String fecha_alta, 
			String fecha_dev ) throws SQLException, ParseException {
		String sql = "INSERT INTO prestamo VALUES(?,?,?,?)";
		SimpleDateFormat out = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat in = new SimpleDateFormat("dd/MM/yy");
		
		PreparedStatement p = connection.getConnection().prepareStatement( sql );
		p.setString( 1,  dni );
		p.setString( 2, codigo );
		p.setString( 3, out.format( in.parse( fecha_alta ) ) );
		p.setString( 4, out.format( in.parse( fecha_dev ) ) );
		
		p.executeUpdate();
	}
	
	
}
