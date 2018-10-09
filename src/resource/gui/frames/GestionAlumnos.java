package resource.gui.frames;

import javax.swing.JFrame;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import resource.gui.frames.components.inputs.DefaultInput;

public class GestionAlumnos extends JFrame {
	
	private JTable tabla;
	private DefaultTableModel model;
	private DefaultInput dni;
	private DefaultInput nombre;
	private DefaultInput apellido1;
	private DefaultInput apellido2;
	
	public GestionAlumnos() {
		setDefaultCloseOperation( DISPOSE_ON_CLOSE );
		
		componentes();
		
		setLocationRelativeTo( null );
		setSize( 600, 200 );
		setVisible( true );
	}
	
	
	private void componentes() {
		model = new DefaultTableModel();
		model.addColumn( "DNI" );
		model.addColumn( "Nombre" );
		model.addColumn( "Primer Apellido" );
		model.addColumn( "Segundo Apellido" );
		model.addRow( new String[] { "78654896A", "Nombre", "Apellido" } );
		model.addRow( new String[] { "78654896A", "Nombre", "Apellido" } );
		model.addRow( new String[] { "78654896A", "Nombre", "Apellido" } );
		tabla = new JTable( model );
		tabla.setAutoResizeMode( JTable.AUTO_RESIZE_ALL_COLUMNS);
		tabla.getTableHeader().setReorderingAllowed(false);
		getContentPane().add( tabla, "Center");
	}
	
	public static void main(String[] args) {
		new GestionAlumnos();
		
	}
	
}
