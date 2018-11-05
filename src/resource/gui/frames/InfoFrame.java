package resource.gui.frames;

import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import resource.gui.constants.Colors;

public class InfoFrame extends JFrame {

	private static final long serialVersionUID = 1547473957921958660L;
	private static final String PROMPT = "\n > ";
	
	private JTextArea textArea;
	// =========| SINGLETON |======
	private static InfoFrame inst;
	
	public static InfoFrame instancia() {
		if( inst == null )
			inst = new InfoFrame();
		return inst;
	}
	
	private InfoFrame() {
		setDefaultCloseOperation( DISPOSE_ON_CLOSE );
		textArea = new JTextArea();
		textArea.setEditable( false );
		textArea.setFont( new Font( Font.MONOSPACED, Font.PLAIN, 13) );
		textArea.setForeground( Colors.D_BLUE );
		textArea.setTabSize( 4 );
		textArea.setText( PROMPT + "Abierta la vista de información" );
		JScrollPane p = new JScrollPane( textArea );
		
		getContentPane().add( p );
		setSize( 500, 280 );
		setLocationRelativeTo( null);
		setVisible( true );
	}
	
	/**
	 * Agregamos una linea al textArea
	 * @param line
	 */
	public InfoFrame addLine( String line ) {
		addLine( line, true );
		return this;
	}
	
	/**
	 * Agregamos una linea al textarea con o sin el prompt
	 * @param line
	 * @param withPrompt
	 */
	public InfoFrame addLine( String line, boolean withPrompt ) {
		String h = textArea.getText();
		String text = h;
		if ( withPrompt ) // con prompt
			text += PROMPT + line;
		else // sin prompt
			text += "\n" + line;
		textArea.setText( text );
		return this;
	}
	
	/**
	 * 
	 * @return the TextArea
	 */
	public JTextArea getTextArea() {
		return textArea;
	}
	
	
	public InfoFrame visible(boolean b) {
		super.setVisible(b);
		return this;
	}
}
