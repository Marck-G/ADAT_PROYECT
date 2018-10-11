package resource.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import resource.gui.frames.Configuracion;
import resource.gui.frames.GestionAlumnos;
import resource.gui.frames.MenuPrincipal;
import resource.model.conector.ConectorFactory;

public class MenuPrincipalController {
	
	private static MenuPrincipalController instancia;
	
	public static MenuPrincipalController instancia() {
		if( instancia == null )
			instancia = new MenuPrincipalController();
		return  instancia ;
	}
	
	private MenuPrincipal window;
	
	
	
	private MenuPrincipalController() {
	}
	
	public void _init( MenuPrincipal w ) {
		window = w;
		ConectorFactory.setDataBase( ConectorFactory.MYSQL_DB );
		try {
			ConectorFactory.getBaseActiva().conect();
		} catch ( Exception e ) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		alumnoView();
		exit();
		config();
	}
	
	private void config() {
		window.getBtnOtros().addActionListener( new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				Configuracion.instancia().setVisible( true );
				Configuracion.instancia().setSelected(ConectorFactory.MYSQL_DB);
				
			}
		});
	}

	private void exit() {
		window.getBtnExit().addActionListener( new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// cerramos todas las ventanas instanciadas
				window.dispose();	
				GestionAlumnoController.instancia().getWindow().dispose();
				Configuracion.instancia().dispose();
			}
		});	
	}

	private void alumnoView() {
		window.getBtnAlumnos().addActionListener( new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					GestionAlumnos.instancia().setVisible( true );
					window.setVisible( false );
					GestionAlumnoController.instancia()._init( GestionAlumnos.instancia() );
					
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}				
			}
		});					
	}

	/**
	 * @return the window
	 */
	public MenuPrincipal getWindow() {
		return window;
	}
	
	
}
