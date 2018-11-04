package resource.gui.frames;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

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
import resource.model.conector.DerbyConector;
import resource.model.conector.derby.TableCreation;

/**
 * Pantalla de configuración de la aplicación
 * @author Marck-G
 *
 */
public final class Configuracion extends JDialog {
	private static final long serialVersionUID = 5264980408571899105L;
	
	private static Configuracion instancia;
	private static int bdSelection;
	
	public static Configuracion instancia() {
		if( instancia == null )
			instancia = new Configuracion();
		return instancia;
	}
	
	private JRadioButton mysql;
	private JRadioButton sqlite;
	private ButtonGroup b;
	private JButton	btnSalir;
	
	private Configuracion() {
		setDefaultCloseOperation( HIDE_ON_CLOSE );
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
		sqlite = new JRadioButton("Derby");
		sqlite.setBackground( getContentPane().getBackground() );
		sqlite.setForeground( getContentPane().getForeground() );
		sqlite.setFont( Fonts.BTN_FONT );
		sqlite.setFocusable( false );
		setSelected();
		
		b.add( mysql );
		b.add( sqlite );
		
		p.add( sqlite );
		p.add( mysql );
		
		btnSalir = new DefaultButton("ACEPTAR");
		btnSalir.addActionListener( new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				conectionManager();
				dispose();
				
			}
		});
		
		getContentPane().add(p, "Center");
		getContentPane().add(btnSalir, "South");
		setSize( 300, 150);
		setLocationRelativeTo( null );
		setResizable( false );
		
	}
	
	private void conectionManager() {
		String conectorName = ConectorFactory.getBaseActiva().getClass().getSimpleName();
		if( sqlite.isSelected()  && conectorName.equals("MysqlConector") ) {
			try {
				ConectorFactory.getBaseActiva().close();
				ConectorFactory.setDataBase( ConectorFactory.DERBY_DB );
				//comprobamos que existan las tablas
				// en caso de que no existan se las crea
				dbStatus();
				return;
			} catch (SQLException e) {}
		}
		if( mysql.isSelected() && conectorName.equals("DerbyConector") ) {
			try {
				ConectorFactory.getBaseActiva().close();
				ConectorFactory.setDataBase( ConectorFactory.MYSQL_DB );
				return;
			} catch (SQLException e) {}
		}
	}
	
	public static void setBdSelection( int bdId ) {
		bdSelection = bdId;
	}
	
	/**
	 * Establecemos cual es la conexi&oacute;n actual
	 * @param id de la conexi&oacute;n
	 */
	public void setSelected() {
		switch ( bdSelection ) {
		case ConectorFactory.MYSQL_DB:
			mysql.setSelected( true );
			break;
		case ConectorFactory.DERBY_DB:
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
			return ConectorFactory.DERBY_DB;
		return -1;
	}
	
	// para bases embebidas:
	public void dbStatus() {
		if ( !DerbyConector.instancia().existDataBase() ) {
			InfoFrame.instancia().setVisible( true );
			String msg = "No se han encontrado las tablas. Se crearán ahora.";
			InfoFrame.instancia().addLine( msg );
			try {
				InfoFrame.instancia().addLine( "Creando la tabla libro...");
				TableCreation.instancia().createTableLibro();
				
			} catch (SQLException e) {
				InfoFrame.instancia().addLine( e.getMessage() );
			}
			try {
				InfoFrame.instancia().addLine( "Creando la tabla alumno...");
				TableCreation.instancia().createTableAlumno();
				
			} catch ( SQLException e) {
				InfoFrame.instancia().addLine( e.getMessage() );
			}
			
			try {
				InfoFrame.instancia().addLine( "Creando la tabla prestamo...");
				TableCreation.instancia().createTablePrestamo();
				
			} catch (SQLException e) {
				InfoFrame.instancia().addLine( e.getMessage() );
			}
			try {
				InfoFrame.instancia().addLine( "Creando la tabla historico...");
				TableCreation.instancia().createTableHistorico();
			} catch (SQLException e) {
				InfoFrame.instancia().addLine( e.getMessage() );
			}
		}
	}

}
