package resource.controller;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.table.DefaultTableModel;

import resource.gui.frames.GestionAlumnos;
import resource.model.beans.Alumno;
import resource.model.exceptions.EmptyTableException;
import resource.model.query.controllers.AlumnosController;

public class GestionAlumnoController {
	private static GestionAlumnoController instancia;
	
	public static GestionAlumnoController instancia() {
		if ( instancia == null )
			instancia = new GestionAlumnoController();
		return instancia;
	}
	
	private GestionAlumnoController() {}
	
	private GestionAlumnos window;
	
	public void _init( GestionAlumnos w ) {
		window = w;
		try {
			GestionAlumnos window = GestionAlumnos.instancia();
			DefaultTableModel m = window.getModel();
			try {
				ArrayList<Alumno> als = AlumnosController.instancia().getAlumnos();
				Alumno[] alumnos = new Alumno[ als.size() ];
				als.toArray( alumnos );
				
				for (Alumno a : alumnos) {
					if(  a != null)
						m.addRow( a.toArray() );
				}
			} catch (EmptyTableException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			GestionAlumnos.instancia().addWindowListener( new WindowAdapter() {

				/* (non-Javadoc)
				 * @see java.awt.event.WindowAdapter#windowClosing(java.awt.event.WindowEvent)
				 */
				@Override
				public void windowClosing(WindowEvent e) {
					
					
					window.setVisible( false );
					MenuPrincipalController.instancia().getWindow().setVisible( true );
				}
				
			});
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * @return the window
	 */
	public GestionAlumnos getWindow() {
		return window;
	}
	
	
}
