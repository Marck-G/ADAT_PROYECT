package resource.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import resource.gui.frames.AddPrestamo;
import resource.gui.frames.components.inputs.DefaultInput;
import resource.gui.frames.dialog.DefaultDialog;
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
		
		window.getAceptar().addActionListener( validar() );
		
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
				if( p.getText().isEmpty() ) {
					new DefaultDialog( Formating.toHTML( "El campo " + p.getName() + " no puede estar vac&iacute;o" ) );
					p.requestFocus();
					return;
				}else {
					p.nextFocus();
				}
				
			}
		};
	}
	
	private ActionListener validar() {
		return null;
	}
	
	
}
