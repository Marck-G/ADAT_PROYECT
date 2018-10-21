/**
 * 
 */
package resource.utils;

/**
 * Clase orientada al formateo de Strings
 * @author Marck-G
 *
 */
public final class Formating {
	
	/**
	 * Combierte los saltos de linea normales (\n) en saltos<br/>
	 * de linea de html (&lt;br/&gt;)
	 * @param string
	 * @return string formateada
	 */
	public static String toHTML( String string ) {
		String out = "<html>";
		out += string.replace( "\n", "<br>" );
		out = out.replace( "\t", "&nbsp;&nbsp;&nbsp;&nbsp;" );
		out += "</html>";
		System.out.println( string + String.format(" \n %-30s", out) );
		return out;
	}
}
