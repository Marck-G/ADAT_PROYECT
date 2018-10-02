/**
 * 
 */
package resource.gui.frames.components.buttons;

import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;

import resource.gui.constants.Colors;

/**
 * @author Marck-G
 *
 */
public class DefaultButton extends JButton {
	
	private static final long serialVersionUID = -8499643363411696697L;

	public DefaultButton( String text ) {
		super( text );
		setBackground( Colors.SECONDARY );
		setForeground( Colors.S_FONT );
		setPreferredSize( new Dimension(0, 40));
		setFocusable( false );
		setBorder(null);
		setCursor( new Cursor( Cursor.HAND_CURSOR));
		addMouseListener( new MouseAdapter() {

			/* (non-Javadoc)
			 * @see java.awt.event.MouseAdapter#mouseEntered(java.awt.event.MouseEvent)
			 */
			@Override
			public void mouseEntered(MouseEvent e) {
				setBackground( Colors.Utils.lighter( Colors.PRIMARY, 60 ) );
				setForeground( Colors.P_FONT );
			}

			/* (non-Javadoc)
			 * @see java.awt.event.MouseAdapter#mouseClicked(java.awt.event.MouseEvent)
			 */
			@Override
			public void mouseClicked(MouseEvent e) {
				setBackground( Colors.Utils.lighter( Colors.SECONDARY, 60 ) );
			}

			/* (non-Javadoc)
			 * @see java.awt.event.MouseAdapter#mouseExited(java.awt.event.MouseEvent)
			 */
			@Override
			public void mouseExited(MouseEvent e) {
				setBackground( Colors.SECONDARY );
				setForeground( Colors.S_FONT );
			}
			
			
		});
	}
}
