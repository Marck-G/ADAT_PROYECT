package resource.gui.frames;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JRadioButton;

import resource.gui.constants.Fonts;
import resource.gui.frames.components.buttons.DefaultButton;
import resource.gui.frames.components.panels.DefaultPanel;
import resource.model.conector.ConectorFactory;

/**
 * Pantalla de configuración de la aplicación
 * @author Marck-G
 *
 */
public final class Configuracion extends JDialog {
	private static final long serialVersionUID = 5264980408571899105L;
	
	private static Configuracion instancia;
	
	public static Configuracion instancia() {
		return ( instancia == null )? new Configuracion(): instancia;
	}
	
	private JRadioButton mysql;
	private JRadioButton sqlite;
	private ButtonGroup b;
	private JButton	btnSalir;
	
	private Configuracion() {
		setDefaultCloseOperation( DISPOSE_ON_CLOSE );
		setUndecorated(true);
		setModal( true );
		setContentPane( new DefaultPanel( new BorderLayout()) );
		
		b = new ButtonGroup();
		
		DefaultPanel p = new DefaultPanel();
		p.setBorder( BorderFactory.createEmptyBorder(30, 0, 0, 0));
		JLabel l = new JLabel("Selecciona una base de datos: ");
		l.setFont( Fonts.BTN_FONT);
		p.add( l );
		mysql = new JRadioButton("MySQL");
		mysql.setBackground( getContentPane().getBackground() );
		mysql.setForeground( getContentPane().getForeground() );
		mysql.setFont( Fonts.BTN_FONT );
		mysql.setFocusable( false );
		mysql.setSelected( true );
		sqlite = new JRadioButton("SQLite");
		sqlite.setBackground( getContentPane().getBackground() );
		sqlite.setForeground( getContentPane().getForeground() );
		sqlite.setFont( Fonts.BTN_FONT );
		sqlite.setFocusable( false );
		
		b.add( mysql );
		b.add( sqlite );
		
		p.add( sqlite );
		p.add( mysql );
		
		btnSalir = new DefaultButton("ACEPTAR");
		btnSalir.addActionListener( new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				dispose();
				
			}
		});
		
		getContentPane().add(p, "Center");
		getContentPane().add(btnSalir, "South");
		setSize( 300, 150);
		setLocationRelativeTo( null );
		setResizable( false );
		
	}
	
	/**
	 * Establecemos cual es la conexi&oacute;n actual
	 * @param id de la conexi&oacute;n
	 */
	public void setSelected( int id ) {
		switch ( id ) {
		case ConectorFactory.MYSQL_DB:
			mysql.setSelected( true );
			break;
		case ConectorFactory.SQLITE_DB:
			sqlite.setSelected( true);
			break;

		default:
			break;
		}
	}
	
	/**
	 * 
	 * @return id de la conexi&oacute;n
	 */
	public int getSelected() {
		if( mysql.isSelected() )
			return ConectorFactory.MYSQL_DB;
		if( sqlite.isSelected() )
			return ConectorFactory.SQLITE_DB;
		return -1;
	}
	

}
