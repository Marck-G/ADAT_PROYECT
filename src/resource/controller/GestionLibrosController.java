package resource.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;

import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.table.DefaultTableModel;

import resource.gui.frames.GestionLibros;
import resource.gui.frames.components.tables.DefaultTable;
import resource.gui.frames.dialog.DefaultDialog;
import resource.model.beans.Estado;
import resource.model.beans.Libro;
import resource.model.beans.Prestamo;
import resource.model.exceptions.AlumnoNotFoundException;
import resource.model.exceptions.LibroNotFoundException;
import resource.model.query.controllers.LibrosController;
import resource.model.query.controllers.PrestamoController;
import resource.utils.Formating;

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
	
	private GestionLibros window;
	private DefaultTableModel model;
	
	private GestionLibrosController() {
	}
	
	public void _init( GestionLibros vista ) {
		window = vista;
		tabEvent();
		startLibros();
		searchEvents();
		add();
		remove();
		update();
		btnEvent();
		tableEvent();
		exit();
	}
	
	// =========| PESTAÑA DE LIBROS |========== //
	
	// lista los libros
	private void startLibros() {
		model = new DefaultTableModel();
		model.addColumn( "Código" );
		model.addColumn( "ISBN" );
		model.addColumn( "Titulo" );
		model.addColumn( "Autor" );
		model.addColumn( "Editorial" );
		model.addColumn( "Asignatura" );
		model.addColumn( "Estado" );
		
		try {
			//añadimos todos los libros al modelo
			for (Libro l : LibrosController.instancia().getLibros() ) {
				model.addRow( l.toArray() );
			}
		window.getTablaLibros().setModel( model );
		} catch (Exception e) {
			e.printStackTrace();
			new DefaultDialog( Formating.toHTML( e.getClass() + "\n" 
					+e.getMessage() ) );
		} 
	}
	
	private void exit() {
		window.addWindowListener( new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				MenuPrincipalController.instancia().getWindow().setVisible(
						true );
				window.dispose();
			}
		});
	}
	
	private void searchEvents() {
		window.getSearch().addActionListener( new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				search();				
			}
		});
		window.getTfSearch().addActionListener( new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				search();
			}
		});
	}
	
	private void search() {
		if( window.getTfSearch().getText().isEmpty() ) {
			updateDataBase();
			startLibros();
			return;
		}
		String codigo = window.getTfSearch().getText();
		
		model = new DefaultTableModel();
		model.addColumn( "Código" );
		model.addColumn( "ISBN" );
		model.addColumn( "Titulo" );
		model.addColumn( "Autor" );
		model.addColumn( "Editorial" );
		model.addColumn( "Asignatura" );
		model.addColumn( "Estado" );
		window.getTablaLibros().setModel( model );
		try {
			for ( Libro l : LibrosController.instancia().getLibros( codigo ) ) {				
				model.addRow( l.toArray() );
			}
		} catch (SQLException | LibroNotFoundException e) {
			new DefaultDialog( e.getMessage() );
		}
	}
	
	private void add() {
		window.getAdd().addActionListener( new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				String codigo;
				if( (codigo = window.getTfSearch().getText() ).isEmpty() ) {
					new DefaultDialog(
							"Introduce el código del nuevo libro en el campo");
					return;
				}
				try {
					Libro n = new Libro( codigo, "", "","", "", "", 
							Estado.NUEVO );
					LibrosController.instancia().addLibro( n );
					// actualizamos la base de datos
					updateDataBase();
					model.addRow( n.toArray() );
					refreshTF();
				} catch (SQLException e) {
					new DefaultDialog( e.getMessage() );
				}
				
			}
		});
	}
	public void updateDataBase() {
		try {
			LibrosController.instancia().removeLibro();
			LibrosController.instancia().addLibro();
		} catch (SQLException e) {
			new DefaultDialog( e.getMessage() );
		}
	}
	
	private void remove() {
		window.getRemove().addActionListener( new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				String codigo;
				int row;
				if( !( codigo = window.getTfSearch().getText() ).isEmpty() ) {
					for (row = 0; row < window.getTablaLibros().getRowCount() && 
						!( ( String ) model.getValueAt( row, 0 ) ).equals( 
								codigo ); row++);
				}else {
					row = window.getTablaLibros().getSelectedRow();
					codigo = (String) model.getValueAt( row, 0 );
				}
				
				try {
					LibrosController.instancia().removeLibro( codigo );
					model.removeRow( row );
					refreshTF();
				} catch (SQLException e) {
					new DefaultDialog( e.getMessage() );
				}
				
			}
		});
	}
	private void refreshTF() {
		window.getTfSearch().setText("");
	}
	private void update() {
		window.getTablaLibros().addPropertyChangeListener( 
				new PropertyChangeListener() {
			
			@Override
			public void propertyChange(PropertyChangeEvent arg0) {
				if( arg0.getPropertyName().equals( "tableCellEditor" ) && 
						!window.getTablaLibros().isEditing() ) {
					int x = window.getTablaLibros().getSelectedRow();
					String codigo = (String) model.getValueAt( x, 0 );
					String[] data = {
							codigo,
							model.getValueAt( x, 1 ) + "",
							model.getValueAt( x, 2 ) + "",
							model.getValueAt( x, 3 ) + "",
							model.getValueAt( x, 4 ) + "",
							model.getValueAt( x, 5 ) + "",
							Estado.getEstadoFrom( 
								model.getValueAt( x, 6 ).toString() ).estado()
					};
					Libro l = Libro.toLibro( data );
					try {
						LibrosController.instancia().updateLibro( codigo, l );
					} catch (SQLException e) {
						new DefaultDialog( e.getMessage() );
					}
				}
			}
		});
	}
	
	// ========| EVENTO JTABBEDPANE |======== //
	private void tabEvent() {
		window.getTabs().addChangeListener( new ChangeListener() {
			
			@Override
			public void stateChanged( ChangeEvent e ) {
				// está en la pestaña de libros
				if( window.getTabs().getSelectedIndex() == 0 ) {
					startLibros();
					
					window.getLibroPanel().setVisible( true );
					window.getPrestamoPanel().setVisible( false );
					window.getContentPane().add( window.getLibroPanel(), "East");
				}else {
					startPrestamos( PrestamoController.NO_DEVUELTO );
					window.getLibroPanel().setVisible( false );
					window.getToggle().changeActivated();
					window.getPrestamoPanel().setVisible( true );
					window.getContentPane().add( window.getPrestamoPanel() , "East" );
					
				}
				
			}
		});
	}

	private void startPrestamos( int type ) {
		model = new DefaultTableModel();
		model.addColumn( "Código" );
		model.addColumn( "Título" );
		model.addColumn( "DNI" );
		model.addColumn( "Nombre" );
		model.addColumn( "Fecha prestamo" );
		model.addColumn( "Fecha de devolucion" );
		model.addColumn( "Estado" );
		window.getTablaPrestamos().setModel( model );
		
		try {
			ArrayList<Prestamo> libros = PrestamoController.instancia().getPrestamos( type );
			for (Prestamo p : libros) {
				model.addRow( p.toArray() );
			}
		} catch (SQLException | LibroNotFoundException | AlumnoNotFoundException e) {
			new DefaultDialog( e.getMessage() );
		}
		
	}
	
	private void btnEvent() {
		window.getToggle().addActionListener( new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if( window.getToggle().isActivated() ) {
					startPrestamos( PrestamoController.DEVUELTO);
				}else {
					startPrestamos( PrestamoController.NO_DEVUELTO );
				}
			}
		});
	}
	
	private void tableEvent() {
		DefaultTable t = window.getTablaPrestamos();
		t.addPropertyChangeListener( new PropertyChangeListener() {
			
			@Override
			public void propertyChange(PropertyChangeEvent e) {
				if( e.getPropertyName().equals( "tableCellEditor" ) && !t.isEditing() ) {
					if( t.getEditingColumn()  == 6 ) {
						//comprobamos que sea la ultima columna la que se edita
						String devuelto = (String) model.getValueAt( t.getSelectedRow(), t.getColumnCount() - 1);
						if( devuelto.equalsIgnoreCase("devuelto") ) {
							String[] data = {
									model.getValueAt( t.getSelectedRow(), 0 ).toString(),
									model.getValueAt( t.getSelectedRow(), 2 ).toString(),
									model.getValueAt( t.getSelectedRow(), 4 ).toString(),
									model.getValueAt( t.getSelectedRow(), 5 ).toString(),
									model.getValueAt( t.getSelectedRow(), 6 ).toString()
							};
							Prestamo p;
							try {
								p = Prestamo.toPrestamo(data);
								PrestamoController.instancia().setPrestamoDevuelto( p );
							} catch (ParseException | SQLException e2) {
								new DefaultDialog( e2.getMessage() );
							}
						}
					} else {
						new DefaultDialog( "Solo se puede editar el estado" );
						return;
					}
					
				}
			}
		});
	}
	

}
