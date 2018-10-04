/**
 * 
 */
package resource.gui.frames.components.inputs;

import java.awt.Container;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

import javax.swing.BorderFactory;
import javax.swing.JPasswordField;

import resource.gui.constants.Colors;
import resource.gui.constants.Fonts;

/**
 * JPasswordField Personalizada
 * @see {@link JPasswordField}
 * @author Marck-G
 *
 */
public class PasswordInput extends JPasswordField {

	private static final long serialVersionUID = -1162286409382970474L;

	public PasswordInput( Container container) {
		setBackground( container.getBackground() );
		setForeground( container.getForeground() );
		setFont( Fonts.ARIAL );
		setBorder( BorderFactory.createMatteBorder(0, 0, 3, 0,  Colors.INPUTS_BORDER ) );

		addFocusListener( new FocusAdapter() {

			/* (non-Javadoc)
			 * @see java.awt.event.FocusAdapter#focusGained(java.awt.event.FocusEvent)
			 */
			@Override
			public void focusGained(FocusEvent e) {
				setBorder( BorderFactory.createMatteBorder(0, 0, 3, 0, Colors.INPUTS_BORDER ) );
			}

			/* (non-Javadoc)
			 * @see java.awt.event.FocusAdapter#focusLost(java.awt.event.FocusEvent)
			 */
			@Override
			public void focusLost(FocusEvent e) {
				setBorder( BorderFactory.createMatteBorder(0, 0, 3, 0, Colors.Utils.lighter( container.getBackground(), 30 ) ) );
			}
			
		});
	}
}
