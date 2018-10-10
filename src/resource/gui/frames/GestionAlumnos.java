package resource.gui.frames;

import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import resource.gui.constants.Colors;
import resource.gui.constants.Fonts;
import resource.gui.frames.components.inputs.DefaultInput;
import resource.model.beans.Alumno;

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
		
		Alumno[] al = new Alumno[10];
		for (int i = 0; i < al.length; i++) {
			int dni = (int) Math.floor( Math.random() * 99999999 + 1000000 );
			al[i] = new Alumno(
					dni + (new Character( (char)(dni % 255 + 97 ))).toString() +"",
					"Alumno " + dni % 20, 
					"Primer Apellido",
					"Segundo Apellido");
		}
		
		String[][] data = new String[10][4];
		for (int i = 0; i < data.length; i++) {
			data[i] = al[i].toArray();
		}
		model = new DefaultTableModel( data, new String[] { "DNI", "Nombre", "Primer Apellido", "Segundo Apellido" });
		tabla = new JTable( model );
		tabla.setBackground( Colors.S_LIGTH );
		tabla.setSelectionBackground( Colors.PRIMARY );
		tabla.setFont( Fonts.ARIAL  );
		tabla.setShowVerticalLines( false );
		tabla.setRowHeight( 30 );
		tabla.getTableHeader().setBackground( Colors.PRIMARY );
		tabla.getTableHeader().setFont( tabla.getFont());
		tabla.setGridColor( Colors.S_DARK );
		tabla.setSelectionForeground( Colors.S_FONT );
		tabla.setAutoResizeMode( JTable.AUTO_RESIZE_ALL_COLUMNS);
		tabla.getTableHeader().setReorderingAllowed(false);
		JScrollPane p = new JScrollPane( tabla );
		getContentPane().add( p, "Center");
	}
	
	public static void main(String[] args) {
		new GestionAlumnos();
		
	}
	
}
