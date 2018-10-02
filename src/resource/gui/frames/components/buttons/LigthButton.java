package resource.gui.frames.components.buttons;


import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;

import resource.gui.constants.Colors;

public class LigthButton extends JButton {
	private static final long serialVersionUID = 2336267315766067100L;

	public LigthButton( String text ) {
		super( text );
		setBackground( Colors.S_LIGTH );
		setForeground( Colors.P_FONT );
		setPreferredSize( new Dimension(100, 40));
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
				setForeground( Colors.SECONDARY );
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
				setBackground( Colors.S_LIGTH );
				setForeground( Colors.P_FONT );
			}
			
			
		});
		
	}
}
