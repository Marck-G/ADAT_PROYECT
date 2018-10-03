/**
 * 
 */
package resource.gui.frames.dialog;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;

import resource.gui.frames.components.buttons.DefaultButton;
import resource.gui.frames.components.panels.DefaultPanel;

/**
 * @author Marck-G
 *
 */
public class DefaultDialog extends JDialog {
	
	/**
	 * Muesta un dialogo por defecto basico 
	 * @param msg
	 * @param ventana
	 */
	public DefaultDialog( String msg ) {
		setUndecorated( true );
		setDefaultCloseOperation( DISPOSE_ON_CLOSE );
		setModal( true );
		
		setContentPane( new DefaultPanel( new BorderLayout() ) );
		
		// creamos un panel para la parte sur
		DefaultPanel panelSur = new DefaultPanel();
		DefaultButton btnAceptar = new DefaultButton( "Aceptar" );
		panelSur.setLayout( new FlowLayout( FlowLayout.RIGHT ) );
		btnAceptar.addActionListener( new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
		panelSur.add( btnAceptar );
		DefaultPanel center = new DefaultPanel( new FlowLayout( FlowLayout.CENTER ) );
		center.add( new JLabel(msg) );
		center.setMinimumSize( new Dimension( 200, 40 ) );
		center.setBorder( BorderFactory.createEmptyBorder(20, 70, 10, 70));
		getContentPane().add( center , BorderLayout.CENTER );
		getContentPane().add( panelSur, BorderLayout.SOUTH );
		
		pack();
		setLocationRelativeTo( null );
		setVisible( true );
	}
	
}
