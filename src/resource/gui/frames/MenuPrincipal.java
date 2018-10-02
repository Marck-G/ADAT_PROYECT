/**
 * 
 */
package resource.gui.frames;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import resource.gui.frames.components.buttons.DefaultButton;
import resource.gui.frames.components.panels.DefaultPanel;

/**
 * @author Marck-G
 *
 */
public class MenuPrincipal extends JFrame {
	
	
	public MenuPrincipal() {
		setUndecorated( true );
		setContentPane( new DefaultPanel( new BorderLayout()));
		setDefaultCloseOperation( EXIT_ON_CLOSE );
		JButton btn = new DefaultButton( "Salir" );
		btn.addActionListener( new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				dispose();
				
			}
		});
		btn.setFocusable( false );
		getContentPane().add(btn, BorderLayout.SOUTH);
		setSize(200, 300);
		setLocationRelativeTo( null );
		setVisible(true);
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		new MenuPrincipal();

	}

}
