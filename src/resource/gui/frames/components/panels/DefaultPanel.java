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

	public DefaultPanel() {
		setBackground( Colors.PRIMARY );
		setForeground( Colors.P_FONT );
	}
	
	public DefaultPanel( LayoutManager layout ) {
		this();
		setLayout( layout );
	}
	
}
