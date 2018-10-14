package resource.gui.frames.components.buttons;

import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import resource.gui.constants.Colors;

public class ToggleButton extends JButton{
	private static final long serialVersionUID = 1L;
	private boolean activated;
	private String textOn;
	private String textOff;
	public ToggleButton( String textOn, String textOff ) {
		activated = false;
		this.textOff = textOff;
		this.textOn  = textOn;
		setBackground( Colors.PRIMARY );
		refresView();
		setFocusable( false );
		setBorder( null );
		setCursor( new Cursor( Cursor.HAND_CURSOR));
		setPreferredSize( new Dimension(100, 40 ) );
		
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
			setText( textOn );
		} else {
			setBackground( Colors.SECONDARY );
			setText( textOff );
		}
	}
	
	/**
	 * Cambiamos el estado de boton
	 */
	public void changeActivated() {
		activated = ( activated )? false:true;
	}
	
	/**
	 * 
	 * @return activated
	 */
	public boolean isActivated() {
		return activated;
	}
	
}
