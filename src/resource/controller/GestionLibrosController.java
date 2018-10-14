package resource.controller;

import javax.swing.table.DefaultTableModel;

import resource.gui.frames.GestionLibros;
import resource.model.conector.ConectorFactory;
import resource.model.conector.DataBaseConection;

public class GestionLibrosController {
	
	// ======| SINGLETON |======== //
	private static GestionLibrosController instancia;
	
	/**
	 * 
	 * @return la &uacute;niaca instancia activa
	 */
	public static GestionLibrosController instancia() {
		if( instancia == null )
			instancia = new GestionLibrosController();
		return instancia;
	}
	// ======| /SINGLETON |======== //
	
	private DataBaseConection con;
	private GestionLibros window;
	private DefaultTableModel model;
	private GestionLibrosController() {
		con = ConectorFactory.getBaseActiva();
	}
	
	/**
	 * Establecemos la visata del controlador
	 * @param vista
	 */
	public void setWindow( GestionLibros vista ) {
		window = vista;
	}
	
	public void _init() {
		
	}
	
	

}
