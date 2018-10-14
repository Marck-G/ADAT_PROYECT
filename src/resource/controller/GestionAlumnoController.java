package resource.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.net.ConnectException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.naming.CommunicationException;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import resource.gui.constants.Colors.Utils;
import resource.gui.frames.GestionAlumnos;
import resource.gui.frames.components.buttons.LigthButton;
import resource.gui.frames.components.tables.DefaultTable;
import resource.gui.frames.dialog.DefaultDialog;
import resource.model.beans.Alumno;
import resource.model.exceptions.AlumnoNotFoundException;
import resource.model.exceptions.EmptyTableException;
import resource.model.query.controllers.AlumnosController;
import resource.utils.Formating;

public class GestionAlumnoController {
	private static GestionAlumnoController instancia;
	
	public static GestionAlumnoController instancia() {
		if ( instancia == null )
			instancia = new GestionAlumnoController();
		return instancia;
	}
	
	private GestionAlumnoController() {}
	
	private GestionAlumnos window;
	private DefaultTableModel m;
	private int rowEditing;
	
	public void _init( GestionAlumnos w ) {
		window = w;
		start();	
		search();
		actualizarBase();
		remove();
		add();
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
			new DefaultDialog( e.getSQLState() );
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
						System.out.println( Alumno.toAlumno(data) );
						AlumnosController.instancia().addAlumno( Alumno.toAlumno( data ));
						AlumnosController.instancia().addAlumno();
						start();
					} catch (SQLException ex) {
						// TODO Auto-generated catch block
						ex.printStackTrace();
					}
				}
				
			}
		});
		
		window.getTabla().addPropertyChangeListener( new PropertyChangeListener() {
			
			@Override
			public void propertyChange(PropertyChangeEvent e ) {
				if( e.getPropertyName().equals( "tableCellEditor" ) && !window.getTabla().isEditing() ) {
					int x = window.getTabla().getEditingRow();
					int y =  window.getTabla().getEditingColumn();
					String oldDni = (String) window.getTabla().getValueAt( x, 0 );
					System.out.println(oldDni);
					// si el dni no esta vacio lo actulizamos
					//if ( !oldDni.isEmpty() ) {
						System.out.println( x +  ", " + y );
						String[] data = { m.getValueAt( x, 0 ) + "",
								m.getValueAt( x,1 ).toString()+ "",
								m.getValueAt( x,2 ).toString() + "",
								m.getValueAt( x,3 ).toString() + ""};
						try {
							// lo agregamos al la base de datos
							AlumnosController.instancia().update( oldDni, Alumno.toAlumno( data ) );
							//AlumnosController.instancia().update();
						} catch (SQLException ex) {
							// TODO Auto-generated catch block
							ex.printStackTrace();
						}
					//} else {}
				}
				
			}
		});
		
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
	private void actualizar() {
		try {
			AlumnosController.instancia().removeAlumno();
			AlumnosController.instancia().addAlumno();
			AlumnosController.instancia().update();
		} catch (SQLException ex) {
			new DefaultDialog( ex.getCause().toString() );
		}
	}
	
	private void remove() {
		JTable tabla = window.getTabla();
		
		window.getRemove().addActionListener( new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				String dni = (String) tabla.getValueAt( tabla.getSelectedRow(), 0 );
				try {
					AlumnosController.instancia().removeAlumno( dni );
					m.removeRow( tabla.getSelectedRow() );
				} catch (SQLException e) {
					new DefaultDialog( e.getCause().toString() );
				}
			}
		});
	}
	
	

	/**
	 * @return the window
	 */
	public GestionAlumnos getWindow() {
		return window;
	}
	
	
}
