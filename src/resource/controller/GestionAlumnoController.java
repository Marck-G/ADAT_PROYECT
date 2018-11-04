package resource.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import resource.gui.frames.GestionAlumnos;
import resource.gui.frames.components.buttons.LigthButton;
import resource.gui.frames.dialog.DefaultDialog;
import resource.model.beans.Alumno;
import resource.model.exceptions.AlumnoNotFoundException;
import resource.model.exceptions.EmptyTableException;
import resource.model.query.controllers.AlumnosController;
import resource.utils.Formating;

public class GestionAlumnoController {
	// ==========| SINGLETON |=========== //
	private static GestionAlumnoController instancia;
	
	public static GestionAlumnoController instancia() {
		if ( instancia == null )
			instancia = new GestionAlumnoController();
		return instancia;
	}
	
	private GestionAlumnoController() {}
	// =========| FIN |=======  //
	
	private GestionAlumnos window;
	private DefaultTableModel m;
	
	public void _init( GestionAlumnos w ) {
		window = w;
		start();	
		search();
		actualizarBase();
		remove();
		add();
		menuBar();
	}
	
	private void start() {
		try {
			GestionAlumnos window = GestionAlumnos.instancia();
			m = new DefaultTableModel();
			// añadimos las cabeceras
			m.addColumn( "DNI" );
			m.addColumn( "Nombre" );
			m.addColumn( "Primer Apellido" );
			m.addColumn( "Segundo Apellido" );
			window.getTabla().setModel( m );
			try {
				//cogemos todos los alumnos para listarlos
				ArrayList<Alumno> als = AlumnosController.instancia().getAlumnos();
				Alumno[] alumnos = new Alumno[ als.size() ];
				//los pasamos a un array
				als.toArray( alumnos );
				//convertimos cada objeto en un array para generar la matriz de datos:
				for (Alumno a : alumnos) {
					if(  a != null)
						m.addRow( a.toArray() );
				}
				//mostramos el total de filas encontradas
				//new DefaultDialog( "Se han listado " + m.getRowCount()  + " alumnos" );
			} catch (EmptyTableException e) {
				new DefaultDialog("Error: " + e.getMessage() );
			}
			GestionAlumnos.instancia().addWindowListener( new WindowAdapter() {

				/* (non-Javadoc)
				 * @see java.awt.event.WindowAdapter#windowClosing(java.awt.event.WindowEvent)
				 */
				@Override
				public void windowClosing(WindowEvent e) {
					
					//escondemos una y mostramos la otra
					window.setVisible( false );
					MenuPrincipalController.instancia().getWindow().setVisible( true );
				}
				
			});
		} catch (SQLException e) {
			new DefaultDialog( e.getMessage() );
		} //catch(  )
	}
	
	private void search() {
		LigthButton btn = window.getSearh();
		ActionListener action = new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if( window.getTfSearch().getText().isEmpty() ) {
					// ACTUALIZAMOS LOS ALUMNOS
					actualizar();
					start();
				}else {
					// buscamos el alumno
					try {
						
						ArrayList<Alumno> als = AlumnosController.instancia().getAlumno( window.getTfSearch().getText() );
						m= new DefaultTableModel();
						// añadimos las cabeceras
						m.addColumn( "DNI" );
						m.addColumn( "Nombre" );
						m.addColumn( "Primer Apellido" );
						m.addColumn( "Segundo Apellido" );
						for (Alumno a : als) {
							m.addRow( a.toArray() );
						}						
						window.getTabla().setModel( m );
					} catch (AlumnoNotFoundException | SQLException e) {
						new DefaultDialog(  e.getMessage() );
					}
				}
				
			}
		};
		//colocamos los listeners
		window.getTfSearch().addActionListener( action );
		btn.addActionListener( action );
	}
	
	private void add() {
		window.getAdd().addActionListener( new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if( window.getTfSearch().getText().isEmpty() ) {
					new DefaultDialog( Formating.toHTML( "Coloca el nuevo DNI \n en cuadro de texto" ) );
				}else {
					//m.addRow( new String[] {"","","",""} );
					String[] data = { window.getTfSearch().getText(),
							"nombre", "Primer apellido", "Segundo apellido"};
					try {
						AlumnosController.instancia().addAlumno( Alumno.toAlumno( data ));
						AlumnosController.instancia().addAlumno();
						start();
					} catch (SQLException ex) {
						new DefaultDialog(  ex.getMessage() );
					}
					vaciarTf();
				}
				
			}
		});
		
		window.getTabla().addPropertyChangeListener( new PropertyChangeListener() {
			
			@Override
			public void propertyChange(PropertyChangeEvent e ) {
				if( e.getPropertyName().equals( "tableCellEditor" ) && !window.getTabla().isEditing() ) {
					int x = window.getTabla().getEditingRow();
					int y =  window.getTabla().getEditingColumn();
					String dni = (String) window.getTabla().getValueAt( x, 0 );
					String[] data = { m.getValueAt( x, 0 ) + "",
							m.getValueAt( x,1 ).toString()+ "",
							m.getValueAt( x,2 ).toString() + "",
							m.getValueAt( x,3 ).toString() + ""};
					try {
						// lo agregamos al la base de datos
						AlumnosController.instancia().update( dni, Alumno.toAlumno( data ) );
						//AlumnosController.instancia().update();
					} catch (SQLException ex) {
						new DefaultDialog( ex.getMessage() );
					}
					vaciarTf();
					
				}
				
			}
		});
		
	}
	
	private void vaciarTf() {
		window.getTfSearch().setText("");
	}
	
	
	// Actualiza la base de datos mientras el usuario no la esta utilizando
	private void actualizarBase() {
		window.addWindowListener( new WindowAdapter() {

			/* (non-Javadoc)
			 * @see java.awt.event.WindowAdapter#windowClosing(java.awt.event.WindowEvent)
			 */
			@Override
			public void windowClosing(WindowEvent e) {
				actualizar();
			}
		});
	}
	/**
	 * Actualiza la base de datos
	 */
	private void actualizar() {
		try {
			//ejecutamos todos los bach que haya
			AlumnosController.instancia().removeAlumno();
			AlumnosController.instancia().addAlumno();
			AlumnosController.instancia().update();
		} catch (SQLException ex) {
			new DefaultDialog( ex.getMessage() );
		}
	}
	/**
	 * Elimna el alumno seleccionados
	 */
	private void remove() {
		
		JTable tabla = window.getTabla();
		
		window.getRemove().addActionListener( new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if( tabla.getSelectedRow() == -1 ) {
					new DefaultDialog( Formating.toHTML( "Selecciona una fila a eliminar \no escribe el dni del alumno" ) );
					return;
				}
				String dni;
				int i;
				if( !window.getTfSearch().getText().isEmpty() ) { //cogemos el escrito
					dni = window.getTfSearch().getText();
					for ( i = 0; i < m.getRowCount() && !dni.equals( m.getValueAt( i, 0 ) ); i++);
				}else { //cogemos el dni selecionado
					dni = (String) tabla.getValueAt( tabla.getSelectedRow(), 0 );
					i = tabla.getSelectedRow();
				}
				try {
					//lo borramos de la base de datos y del modelo
					AlumnosController.instancia().removeAlumno( dni );
					m.removeRow( i );
				} catch (SQLException e) {
					new DefaultDialog( e.getMessage() );
				}
				vaciarTf();
			}
		});
	}
	
	/**
	 * Colocamos los eventos de la barra de men&uacute;
	 */
	private void menuBar() {
		window.getAhtml().addActionListener( new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				new DefaultDialog("Abierta la ayuda");
				//TODO: hacer que salga la ayuda en HTML
			}
		});
		window.getaPDF().addActionListener( new ActionListener() {
					
			@Override
			public void actionPerformed(ActionEvent arg0) {
				new DefaultDialog("Abierta la ayuda pdf");
				//TODO: Hacer que salga la ayuda en pdf
			}
		});
	}
	

	/**
	 * @return the window
	 */
	public GestionAlumnos getWindow() {
		return window;
	}
	
	public void close() {
		if( window != null )
			window.dispose();
	}
	
	
}
