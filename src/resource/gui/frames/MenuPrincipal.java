/**
 * 
 */
package resource.gui.frames;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import resource.controller.MenuPrincipalController;
import resource.gui.constants.Fonts;
import resource.gui.frames.components.buttons.DefaultButton;
import resource.gui.frames.components.buttons.LigthButton;
import resource.gui.frames.components.panels.DefaultPanel;
import resource.gui.resources.img.ImageManager;

/**
 * Pantalla inicial de la aplicaci&oacute;n
 * @author Marck-G
 *
 */
public class MenuPrincipal extends JFrame {
	
	private static final long serialVersionUID = 3285524243457872984L;
	private DefaultButton 	btnExit;
	private LigthButton 	btnBiblioteca;
	private LigthButton		btnAlumnos;
	private LigthButton		btnOtros;
	private MenuPrincipalController controller;
	
	public MenuPrincipal() {
		setUndecorated( true );
		setContentPane( new DefaultPanel( new BorderLayout()));
		setDefaultCloseOperation( EXIT_ON_CLOSE );
		setIconImage( new ImageIcon( ImageManager.getImage("icon.png") ).getImage() );
				
		components();
		
			events();
			
			tooltips();
		
		
		setSize(430, 300);
		setLocationRelativeTo( null );
		setVisible(true);
	}
	private void components() {
		getContentPane().setFont( Fonts.ARIAL );
		
		// btn de salida
		btnExit = new DefaultButton("SALIR");
		btnExit.setFont( Fonts.BTN_FONT );
		
		//btn Biblioteca
		btnBiblioteca = new LigthButton("");
		btnBiblioteca.setIcon( new ImageIcon( ImageManager.getImage( "book.png" ) ) );
		btnBiblioteca.setPreferredSize( new Dimension(300, 100));
		
		//btn alumnos
		btnAlumnos = new LigthButton("");
		btnAlumnos.setIcon( new ImageIcon( ImageManager.getImage( "student.png" ) ) );
		btnAlumnos.setPreferredSize( new Dimension(100, 100));
		
		//btn otros
		btnOtros = new LigthButton("");
		btnOtros.setIcon( new ImageIcon( ImageManager.getImage( "settings.png" ) ) );
		
		GridBagLayout layout = new GridBagLayout();
		DefaultPanel center = new DefaultPanel( layout );
		GridBagConstraints gbConst = new GridBagConstraints();
		
		//colocamos btnBiblioteca
		gbConst.fill 		= GridBagConstraints.VERTICAL;
		gbConst.gridx 		= 0;
		gbConst.gridy 		= 0;
		gbConst.gridheight 	= 2;
		gbConst.weighty 	= 10;
		gbConst.insets		= new Insets(5, 0, 10, 10);
		center.add( btnBiblioteca, gbConst );
		
		//colocamos btnAlumno
		gbConst.gridx	= 1;
		gbConst.gridy	= 0;
		gbConst.gridheight = 1;
		gbConst.weighty = 2;
		gbConst.insets		= new Insets(5, 0, 5, 0);
		center.add( btnAlumnos, gbConst );
		
		//colocamos btnAlumno
		gbConst.gridx	= 1;
		gbConst.gridy	= 1;
		gbConst.weighty = 2;
		gbConst.insets	= new Insets(0, 0, 10, 0);
		gbConst.gridheight = 1;
		gbConst.fill 	= GridBagConstraints.VERTICAL;
		center.add( btnOtros, gbConst );

		
		getContentPane().add(btnExit, BorderLayout.SOUTH);
		getContentPane().add(center, BorderLayout.CENTER);
	}
	
	/**
	 * @return the btnExit
	 */
	public DefaultButton getBtnExit() {
		return btnExit;
	}
	/**
	 * @return the btnBiblioteca
	 */
	public LigthButton getBtnBiblioteca() {
		return btnBiblioteca;
	}
	/**
	 * @return the btnAlumnos
	 */
	public LigthButton getBtnAlumnos() {
		return btnAlumnos;
	}
	/**
	 * @return the btnOtros
	 */
	public LigthButton getBtnOtros() {
		return btnOtros;
	}
	private void tooltips() {
		btnBiblioteca.setToolTipText( "Acceder al &aacute;rea de la biblioteca" );
		btnAlumnos.setToolTipText( "Acceder al Ã¡rea de alumnos");
		btnOtros.setToolTipText( "Opciones" );
		btnExit.setToolTipText( "Cerrar el programa" );
	}
	
	private void events() {				
		controller = MenuPrincipalController.instancia();
		controller._init( this );
	}
	
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		new MenuPrincipal();
	}

}
