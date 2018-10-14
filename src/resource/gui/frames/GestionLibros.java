package resource.gui.frames;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import resource.gui.frames.components.buttons.DefaultButton;
import resource.gui.frames.components.buttons.LigthButton;
import resource.gui.frames.components.buttons.ToggleButton;
import resource.gui.frames.components.inputs.DefaultInput;
import resource.gui.frames.components.panels.DefaultPanel;
import resource.gui.frames.components.tables.DefaultTable;
import resource.gui.resources.img.ImageManager;

public class GestionLibros extends JFrame {
	
	private static final long serialVersionUID = 1L;
	
	// =======| SINGLETON |========
	private static GestionLibros instancia;
	
	public static GestionLibros instancia() {
		if( instancia == null ) {
			instancia = new GestionLibros();
		}
		return instancia;
	}
	
	// =========| FIN |============
	
	private JTabbedPane 	tabs;
	private LigthButton		add;
	private LigthButton		remove;
	private DefaultButton	search;
	private DefaultInput	tfSearch;
	private DefaultTable	tablaLibros;
	private DefaultTable	tablaPrestamos;
	private DefaultPanel 	libroPanel;
	private DefaultPanel 	prestamoPanel;
	private ToggleButton	toggle;
	
	
	private GestionLibros() {
		setDefaultCloseOperation( HIDE_ON_CLOSE );
		components();
		
		setSize(800,600);
		setLocationRelativeTo( null );
		setVisible(true);
	}
	
	//INSTANCIAMOS TODOS LOS OBJETOS
	private void components() {
		tabs 			= new JTabbedPane();
		tablaLibros 	= new DefaultTable();
		tablaPrestamos 	= new DefaultTable();
		tabs.setFocusable( false );

		remove  = new LigthButton("");
		add		= new LigthButton("");
		search	= new DefaultButton("");
		
		remove.setIcon( new ImageIcon( ImageManager.getImage( "rm.png" ) ) );
		add.setIcon( new ImageIcon( ImageManager.getImage( "add.png" ) ) );
		search.setIcon( new ImageIcon( ImageManager.getImage( "search.png" ) ) );
		
		libroPanel		 			 = new DefaultPanel( new GridBagLayout() );
		GridBagConstraints c = new GridBagConstraints();
		tfSearch 			 = new DefaultInput( libroPanel );
		
		c.fill		= GridBagConstraints.HORIZONTAL;
		c.gridx		= 0;
		c.gridy		= 0;
		c.gridwidth	= 2;
		c.insets	= new Insets( 0, 5, 10, 5 );
		libroPanel.add( tfSearch, c );
		
		c.gridx 	= 0;
		c.gridy		= 1;
		c.insets 	= new Insets( 0, 0, 0, 0 );
		libroPanel.add( search, c );
		
		c.gridx		= 0;
		c.gridy		= 2;
		c.gridwidth	= 1;
		libroPanel.add( add, c );
		
		c.gridx		= 1;
		c.gridy		= 2;
		libroPanel.add( remove, c );
		
		prestamoPanel = new DefaultPanel( new GridBagLayout() );
		
		toggle = new ToggleButton("Devueltos", "No devueltos");
		c.gridx = 0;
		c.gridy	= 0;
		prestamoPanel.add( toggle, c );
		
		JScrollPane lib = new JScrollPane( tablaLibros );
		JScrollPane pre = new JScrollPane( tablaPrestamos );
		
		tabs.addTab("libros",  lib );
		tabs.add("Prestamos",  pre );
		
		getContentPane().add( tabs, "Center");
		getContentPane().add(libroPanel, "East");
	}

	/**
	 * @return the tabs
	 */
	public JTabbedPane getTabs() {
		return tabs;
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
	 * @return the search
	 */
	public DefaultButton getSearch() {
		return search;
	}

	/**
	 * @return the tfSearch
	 */
	public DefaultInput getTfSearch() {
		return tfSearch;
	}

	/**
	 * @return the tablaLibros
	 */
	public DefaultTable getTablaLibros() {
		return tablaLibros;
	}

	/**
	 * @return the tablaPrestamos
	 */
	public DefaultTable getTablaPrestamos() {
		return tablaPrestamos;
	}

	/**
	 * @return the libroPanel
	 */
	public DefaultPanel getLibroPanel() {
		return libroPanel;
	}

	/**
	 * @return the prestamoPanel
	 */
	public DefaultPanel getPrestamoPanel() {
		return prestamoPanel;
	}

	/**
	 * @return the toggle
	 */
	public ToggleButton getToggle() {
		return toggle;
	}
	
	
	

}
