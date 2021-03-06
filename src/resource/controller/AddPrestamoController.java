package resource.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import resource.gui.frames.AddPrestamo;
import resource.gui.frames.components.inputs.DefaultInput;
import resource.gui.frames.dialog.DefaultDialog;
import resource.model.beans.Alumno;
import resource.model.beans.Libro;
import resource.model.exceptions.AlumnoNotFoundException;
import resource.model.exceptions.LibroNotFoundException;
import resource.model.query.controllers.AlumnosController;
import resource.model.query.controllers.LibrosController;
import resource.model.query.controllers.PrestamoController;
import resource.utils.Formating;

public class AddPrestamoController {
	
	// ===========| SINGLETON |=========== //
	private static AddPrestamoController instacia;
	
	public static AddPrestamoController instancia() {
		if( instacia == null )
			instacia = new AddPrestamoController();
		return instacia;
	}
	
	// ===============| FIN |============= //
	
	private AddPrestamo window;
	
	
	private AddPrestamoController() {
		
	}
	
	public void _init( AddPrestamo w ) {
		window = w;
		eventos();
	}
	
	private void eventos() {
		window.getCancelar().addActionListener( new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				window.dispose();				
			}
		});
		
		window.getAceptar().addActionListener( aceptar() );
		
		window.getCodigo().addActionListener( textFieldEvents() );
		window.getDni().addActionListener( textFieldEvents() );
		window.getFecha_alta().addActionListener( textFieldEvents() );
		window.getFecha_dev().addActionListener( textFieldEvents() );
	}
	
	private ActionListener textFieldEvents() {
		return new ActionListener() {
			
			@Override
			public void actionPerformed( ActionEvent e ) {
				DefaultInput p = ( DefaultInput ) e.getSource();
				// si el input esta vacio muestra un mensaje en caso contrario 
				if( p.getText().isEmpty() ) {
					new DefaultDialog( Formating.toHTML( "El campo " + 
				p.getName() + " no puede estar vac&iacute;o" ) );
					p.requestFocus();
					return;
				}else {
					p.nextFocus();
				}
				
			}
		};
	}
	
	private ActionListener aceptar() {
		return new ActionListener() {
			
			@Override
			public void actionPerformed( ActionEvent e ) {
				if( window.getDni().getText().isEmpty() ||
						window.getCodigo().getText().isEmpty() || 
						window.getFecha_alta().getText().isEmpty() ||
						window.getFecha_dev().getText().isEmpty()) {
					//si hay campos vacios
					new DefaultDialog( "Rellena todos los campos" );
					return;
				}
				try {
					// agregamos el prestamo
					try {
						Alumno a = AlumnosController.instancia().getAlumno( window.getDni().getText() ).get(0);
						Libro l = LibrosController.instancia().getLibrosCodigo( window.getCodigo().getText() );
						PrestamoController.instancia().addPrestamo(
								l.getCodigo(),
								a.getDni(), 
								window.getFecha_alta().getText(), 
								window.getFecha_dev().getText() );
						window.dispose();
					} catch (AlumnoNotFoundException e2) {
						new DefaultDialog( "No existe el alumno con dni: " + window.getDni().getText()  );
						return;
					}catch ( LibroNotFoundException e2) {
						new DefaultDialog( "No existe el libro con el código: " + window.getCodigo().getText()  );
						return;
					}
					
				} catch (Exception e1) {
					new DefaultDialog( Formating.toHTML( e1.getMessage() ) );
				}
			}
		};
	}
	
	public void close() {
		if( window != null )
			window.dispose();
	}
	
}
