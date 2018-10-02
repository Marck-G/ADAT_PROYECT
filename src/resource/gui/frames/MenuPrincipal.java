/**
 * 
 */
package resource.gui.frames;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import resource.gui.frames.components.buttons.DefaultButton;
import resource.gui.frames.components.panels.DefaultPanel;

/**
 * @author Marck-G
 *
 */
public class MenuPrincipal extends JFrame {
	
	private static final long serialVersionUID = 3285524243457872984L;
	private DefaultButton btnExit;
	
	public MenuPrincipal() {
		setUndecorated( true );
		setContentPane( new DefaultPanel( new BorderLayout()));
		setDefaultCloseOperation( EXIT_ON_CLOSE );
		components();
		
		events();
		
		
		setSize(500, 300);
		setLocationRelativeTo( null );
		setVisible(true);
	}
	private void components() {
		// btn de salida
		btnExit = new DefaultButton("SALIR");
		
		getContentPane().add(btnExit, BorderLayout.SOUTH);
	}
	
	private void events() {
		btnExit.addActionListener( new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				dispose();				
			}
		});
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		new MenuPrincipal();

	}

}
