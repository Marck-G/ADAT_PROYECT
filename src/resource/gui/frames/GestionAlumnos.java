package resource.gui.frames;

import javax.swing.JFrame;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

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
		
		
		
		setLocationRelativeTo( null );
		setVisible( true );
	}
	
	
	private void componentes() {
		model = new DefaultTableModel();
		tabla = new JTable( model );
	}
	
	public static void main(String[] args) {
		new GestionAlumnos();
		
	}
	
}
