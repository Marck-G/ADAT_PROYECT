package resource.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import resource.gui.frames.Configuracion;
import resource.gui.frames.GestionAlumnos;
import resource.gui.frames.GestionLibros;
import resource.gui.frames.MenuPrincipal;
import resource.gui.frames.dialog.DefaultDialog;
import resource.model.conector.ConectorFactory;
import resource.utils.Formating;

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
			new DefaultDialog( Formating.toHTML( e.getCause().toString() ) );
		}
		alumnoView();
		exit();
		config();
		librosView();
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
				try {
					GestionAlumnos.instancia().dispose();
				} catch (SQLException e) {
					new DefaultDialog( e.getMessage() );
				}
				Configuracion.instancia().dispose();
			}
		});	
	}

	private void alumnoView() {
		window.getBtnAlumnos().addActionListener( new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					// hacemos visible una y escondemos la otra
					GestionAlumnos.instancia().setVisible( true );
					window.setVisible( false );
					// oinicamos el controlador con la vista correspondiente
					GestionAlumnoController.instancia()._init( GestionAlumnos.instancia() );
					
				} catch (SQLException e1) {
					new DefaultDialog( e1.getMessage() );
				}				
			}
		});					
	}
	
	private void librosView() {
		window.getBtnBiblioteca().addActionListener( new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				GestionLibros.instancia().setVisible( true );
				window.setVisible( false );
				//TODO: iniciar el controlador con la instacia de la vista
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
