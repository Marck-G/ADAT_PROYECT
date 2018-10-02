/**
 * 
 */
package resource.gui.frames;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;

/**
 * @author Marck-G
 *
 */
public class MenuPrincipal extends JFrame {
	
	
	public MenuPrincipal() {
		setUndecorated( true );
		setDefaultCloseOperation( EXIT_ON_CLOSE );
		JButton btn = new JButton("salir");
		btn.addActionListener( new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				dispose();
				
			}
		});
		btn.setFocusable( false );
		btn.setBackground( Colors.S_DARK);
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
