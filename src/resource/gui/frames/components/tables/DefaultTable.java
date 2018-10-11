package resource.gui.frames.components.tables;

import javax.swing.JFrame;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import resource.gui.constants.Colors;
import resource.gui.constants.Fonts;

public class DefaultTable extends JTable {
	
	public DefaultTable( DefaultTableModel model ) {
		super( model );
		
		setBackground( Colors.LL_GREEN );
		setFont( Fonts.SANS );
		setSelectionBackground( Colors.D_GREEN );
		setSelectionForeground( Colors.S_FONT );
		setShowVerticalLines( false );
		setGridColor( Colors.D_GREEN );
		setRowHeight( 30 );
		getTableHeader().setBackground( Colors.D_GREEN );
		getTableHeader().setForeground( Colors.S_FONT);
	}

}
