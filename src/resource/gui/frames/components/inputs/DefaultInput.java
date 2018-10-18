package resource.gui.frames.components.inputs;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.JTextField;

import resource.gui.constants.Colors;
import resource.gui.constants.Colors.Utils;
import resource.gui.constants.Fonts;
/**
 * JTextField personalizado
 * @see {@link JTextField}
 * @author Marck-G
 *
 */
public class DefaultInput extends JTextField {
	private static final long serialVersionUID = -6673266511671355909L;
	/**
	 * 
	 * Recibe el panel que lo contiene para aceder a las características del panel
	 * @param panel
	 */
	
	private String placeholder;
	
	public DefaultInput(Container panel) {
		setBorder( null );
		setBackground( panel.getBackground() );
		setForeground( panel.getForeground() );
		setDisabledTextColor(Utils.hexDecode("#4f4f4f"));
		if ( isFocusOwner() )
			setBorder( BorderFactory.createMatteBorder(0, 0, 3, 0, Colors.INPUTS_BORDER ) );
		else
			setBorder( BorderFactory.createMatteBorder(0, 0, 3, 0, Colors.Utils.lighter( panel.getBackground(),30 ) ) );
		setMinimumSize( new Dimension( 60, 30 ) );
		setPreferredSize( new Dimension( 100, 40));
		setSelectionColor( Colors.SECONDARY );
		setSelectedTextColor( Colors.P_FONT );
		setFont( Fonts.ARIAL );
		
		addMouseListener( new MouseAdapter() {

			/* (non-Javadoc)
			 * @see java.awt.event.MouseAdapter#mouseEntered(java.awt.event.MouseEvent)
			 */
			@Override
			public void mouseEntered(MouseEvent e) {
				setForeground( Colors.S_LIGTH );
			}

			/* (non-Javadoc)
			 * @see java.awt.event.MouseAdapter#mouseExited(java.awt.event.MouseEvent)
			 */
			@Override
			public void mouseExited(MouseEvent e) {
				setForeground( panel.getForeground() );
			}			
		});
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
				setBorder( BorderFactory.createMatteBorder(0, 0, 3, 0, Colors.Utils.lighter( panel.getBackground(), 30 ) ) );
			}
			
		});
		
	}
	
	/**
	 * Establecer el texto de muestra en el input
	 * @param text
	 */
	public void setPlaceholderText( String text ) {
		placeholder = text;
	}
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		if (placeholder == null || placeholder.length() == 0 || getText().length() > 0) {
            return;
        }
		
		( ( Graphics2D ) g ).getRenderingHint( RenderingHints.KEY_ANTIALIASING );
		( ( Graphics2D ) g ).setColor( getDisabledTextColor() );
		( ( Graphics2D ) g ).drawString( placeholder, getInsets().left, g.getFontMetrics().getMaxAscent() + getInsets().top + 10);
	}
	

}
