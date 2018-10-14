package resource.gui.frames;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.sql.SQLException;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import resource.gui.constants.Fonts;
import resource.gui.frames.components.buttons.LigthButton;
import resource.gui.frames.components.inputs.DefaultInput;
import resource.gui.frames.components.panels.DefaultPanel;
import resource.gui.frames.components.tables.DefaultTable;
import resource.gui.resources.img.ImageManager;
import resource.model.conector.ConectorFactory;
import resource.model.exceptions.EmptyTableException;

public class GestionAlumnos extends JFrame {
	
	private static final long serialVersionUID = -900998335468872868L;
	
	private JTable tabla;
	private LigthButton	 searh;
	private LigthButton	 add;
	private LigthButton  remove;
	private DefaultInput tfSearch;
	
	private static GestionAlumnos instancia;
	
	public static GestionAlumnos instancia() throws SQLException {
		if( instancia == null )
			instancia = new GestionAlumnos();
		return instancia;
	}
	
	private GestionAlumnos() throws SQLException {
		setDefaultCloseOperation( HIDE_ON_CLOSE );
		setTitle( "Administración de alumnos" );
		setIconImage( ( new ImageIcon( ImageManager.getImage( "student.png" ) ) ).getImage() );
		componentes();
		pack();
		setLocationRelativeTo( null );
		
	}
	
	
	private void componentes() throws SQLException {
		
		
		tabla = new DefaultTable();
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

	/**
	 * @return the tabla
	 */
	public JTable getTabla() {
		return tabla;
	}


	


	/**
	 * @return the searh
	 */
	public LigthButton getSearh() {
		return searh;
	}


	/**
	 * @return the add
	 */
	public LigthButton getAdd() {
		return add;
	}


	/**
	 * @return the remove
	 */
	public LigthButton getRemove() {
		return remove;
	}


	/**
	 * @return the tfSearch
	 */
	public DefaultInput getTfSearch() {
		return tfSearch;
	}
	
	
}
