package resource.gui.frames;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import resource.gui.constants.Fonts;
import resource.gui.frames.components.buttons.LigthButton;
import resource.gui.frames.components.inputs.DefaultInput;
import resource.gui.frames.components.panels.DefaultPanel;
import resource.gui.frames.components.tables.DefaultTable;
import resource.gui.resources.img.ImageManager;
import resource.model.beans.Alumno;
import resource.model.conector.ConectorFactory;
import resource.model.exceptions.EmptyTableException;
import resource.model.query.controllers.AlumnosController;

public class GestionAlumnos extends JFrame {
	
	private static final long serialVersionUID = -900998335468872868L;
	
	private JTable tabla;
	private DefaultTableModel model;
	private LigthButton	 searh;
	private LigthButton	 add;
	private LigthButton  remove;
	private DefaultInput tfSearch;
	
	public GestionAlumnos() throws SQLException, EmptyTableException {
		setDefaultCloseOperation( DISPOSE_ON_CLOSE );
		
		componentes();
		
		setLocationRelativeTo( null );
		setSize( 600, 200 );
		setVisible( true );
	}
	
	
	private void componentes() throws SQLException, EmptyTableException {
		//lamad al controlador 
		ArrayList<Alumno> als = AlumnosController.instancia().getAlumnos();
		Alumno[] al = new Alumno[3];
		als.toArray( al );
		String[][] data = new String[ als.size()][al.length ];
		for (int i = 0; i < data.length; i++) {
			data[i] = al[i].toArray();
		}
		System.out.println(als);
		//fin controlador
		model = new DefaultTableModel( data, new String[] { "DNI", "Nombre", "Primer Apellido", "Segundo Apellido" });
		tabla = new DefaultTable( model );
		JScrollPane p = new JScrollPane( tabla );
		getContentPane().add( p, "Center");
		
		searh = new LigthButton( "" );
		searh.setIcon( new ImageIcon( ImageManager.getImage("search.png") ) );
		
		
		
		DefaultPanel este = new DefaultPanel( new GridBagLayout() );
		GridBagConstraints c = new GridBagConstraints();
		tfSearch = new DefaultInput( este );
		tfSearch.setFont( Fonts.BTN_FONT );
		c.gridx 	= 0;
		c.gridy		= 0;
		c.gridwidth = 2;
		c.weightx	= 3;
		c.insets	= new Insets(0, 10, 0, 10);
		c.fill		= GridBagConstraints.HORIZONTAL;
		este.add(tfSearch, c );

		c.gridx 	= 0;
		c.gridy		= 1;
		c.gridwidth = 2;
		c.weightx	= 1;
		c.insets	= new Insets(0, 0, 0, 0);
		c.fill		= GridBagConstraints.HORIZONTAL;
		este.add( searh, c );
		
		add = new LigthButton("");
		add.setIcon( new ImageIcon( ImageManager.getImage("add.png") ));
		c.gridx 	= 0;
		c.gridy		= 2;
		c.gridwidth = 1;
		c.fill		= GridBagConstraints.HORIZONTAL;
		este.add( add, c );
		
		remove = new LigthButton( "" );
		remove.setIcon( new ImageIcon( ImageManager.getImage( "rm.png" ) ) );
		c.gridx 	= 1;
		c.gridy		= 2;
		c.fill 		= GridBagConstraints.HORIZONTAL;
		este.add( remove, c );
		
		getContentPane().add(este, "East" );
		
		
	}
	
	public static void main(String[] args) throws SQLException, EmptyTableException, ClassNotFoundException, InstantiationException, IllegalAccessException {
		ConectorFactory.setDataBase( ConectorFactory.MYSQL_DB );
		ConectorFactory.getBaseActiva().conect();
		new GestionAlumnos();
		
	}
	
}
