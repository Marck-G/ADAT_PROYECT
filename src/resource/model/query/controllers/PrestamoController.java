package resource.model.query.controllers;

import java.util.ArrayList;

import resource.model.beans.Prestamo;
import resource.model.conector.ConectorFactory;
import resource.model.conector.DataBaseConection;

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
	
	private ArrayList<Prestamo> getPrestamosActivos(){
		return null;
	}
	
	private ArrayList<Prestamo> getPrestamosHistoricos(){
		return null;
	}
	
	public ArrayList<Prestamo> getPrestamos( int type ){
		return null;
	}
	
	public void actualizarPrestamo( Prestamo p, boolean devuelto ) {}
	
}
