package resource.gui.frames.components.buttons;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import resource.gui.constants.Colors;

public class ToggleButton extends JButton{
	private boolean activated;
	public ToggleButton( String text ) {
		activated = false;
		setText( text );
		setBackground( Colors.PRIMARY );
		refresView();
		setFocusable( false );
		setBorder( null );
		
		addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				changeActivated();
				refresView();				
			}
		});
		
	}
	
	/**
	 * Refresca la vista del boton
	 */
	private void refresView() {
		if ( activated ) {
			setBackground( Colors.PRIMARY );
		} else {
			setBackground( Colors.SECONDARY );
		}
	}
	
	/**
	 * Cambiamos el estado de boton
	 */
	public void changeActivated() {
		activated = ( activated )? false:true;
	}
	
	
	
	public static void main(String[] args) {
		JFrame w = new JFrame( "Prueba" );
		ToggleButton b = new ToggleButton("btn");
		b.setBackground( Colors.BACKGROUND);
		
		w.getContentPane().add(b, "South");
		w.setSize(200, 100);
		w.setDefaultCloseOperation( w.DISPOSE_ON_CLOSE );
		w.setVisible( true );
	}
}
