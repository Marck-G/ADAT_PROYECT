/**
 * 
 */
package resource.gui.frames.components.panels;

import java.awt.LayoutManager;
import javax.swing.JPanel;

import resource.gui.constants.Colors;

/**
 * Panel principal
 * @author Marck-G
 *
 */
public class DefaultPanel extends JPanel {
	private static final long serialVersionUID = 8249260350208382008L;

	public DefaultPanel() {
		setBackground( Colors.PRIMARY );
		setForeground( Colors.P_FONT );
	}
	
	public DefaultPanel( LayoutManager layout ) {
		this();
		setLayout( layout );
	}
	
}
