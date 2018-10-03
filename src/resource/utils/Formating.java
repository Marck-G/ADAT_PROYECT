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
		for (String str : string.split( "\n" ) ) {
			out += str + "<br/>";
		}
		out += "</html>";
		return out;
	}
}
