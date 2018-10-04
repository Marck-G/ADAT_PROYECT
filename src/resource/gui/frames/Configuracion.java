package resource.gui.frames;

import javax.swing.JFrame;

import resource.gui.frames.components.buttons.DefaultButton;
import resource.gui.frames.components.inputs.DefaultInput;
import resource.gui.frames.components.inputs.PasswordInput;

/**
 * Pantalla de configuración de la aplicación
 * @author Marck-G
 *
 */
public final class Configuracion extends JFrame {
	private static final long serialVersionUID = 5264980408571899105L;
	
	private DefaultInput inDBUrl;
	private DefaultInput inDBUser;
	private PasswordInput inPass;
	private DefaultButton btnEditar;
	private DefaultButton btnAceptar;
	
	public Configuracion() {
		setDefaultCloseOperation( EXIT_ON_CLOSE );
		
		
		
		
		setSize( 300, 400);
		setVisible( true );
		
	}
	

}
