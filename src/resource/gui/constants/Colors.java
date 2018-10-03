package resource.gui.constants;

import java.awt.Color;
/**
 * Clase con constantes de color y funciones relacionadas con ellos
 * @author Marck-G
 *
 */
public final class Colors {
	private static final int PROGRESS	= 10;
	public static final Color PRIMARY 	= Utils.hexDecode( "607d8b" );
	public static final Color P_LIGHT 	= Utils.hexDecode( "8eacbb" );
	public static final Color P_DARK	= Utils.hexDecode( "34515e" );
	
	public static final Color SECONDARY	= Utils.hexDecode( "f44336" );
	public static final Color S_LIGTH	= Utils.hexDecode( "ff7961" );
	public static final Color S_DARK	= Utils.hexDecode( "ba000d" );
	
	public static final Color P_FONT	= Utils.hexDecode( "000000" );
	public static final Color S_FONT	= Utils.hexDecode( "ffffff" );
	
	public static final Color INPUTS_BORDER = Utils.hexDecode( "ff1744" );
	
	
	/**
	 * Clase con utilidades para el color
	 * @author Marck-G
	 *
	 */
	public final static class Utils{
		/**
		 * Decodificador de colores hexadecimales completos
		 * @param color
		 * @return
		 */
		private static Color decode( String color ) {
			String[] colorsUnit = new String[3];
			int index = 0;
			//sacamos pareja de caracteres
			for (int i = 0; i < colorsUnit.length; i++) {
				colorsUnit[i] = color.substring(index, index+2);
				index+= 2;
			}
			//los transformamos a enteros
			int[] colorInt = { Integer.parseInt( colorsUnit[0], 16),
					Integer.parseInt( colorsUnit[1], 16),
					Integer.parseInt( colorsUnit[2], 16)};
			//creamos el color correspondiente
			return new Color(colorInt[0], colorInt[1], colorInt[2]);
		}
		/**
		 * Crea un color a partir del hexadecimal
		 * @param hexColor
		 * @return
		 */
		public static Color hexDecode( String hexColor ) {
			if( hexColor.indexOf( '#' ) == -1 ) {
				return decode( hexColor );
			}else {
				return decode( hexColor.substring( 1 ) );
			}				
		}
		/**
		 * Oscurece un color en value menos
		 * @param color
		 * @param value
		 * @return color oscurecido en value veces
		 */
		public static Color darkness( Color color, int value ) {
			return new Color( 	( color.getRed() - value < 0 )?0:color.getRed() - value,
								( color.getGreen() - value < 0 )?0:color.getGreen() - value,  
								( color.getBlue() - value < 0 )?0:color.getBlue() - value );
		}
		
		/**
		 * Oscurece el color según la constante
		 * @param color
		 * @return color
		 */
		public static Color darkness( Color color ) {
			return darkness( color, PROGRESS );
		}
		
		/**
		 * Aclara un color según el valor que recibe
		 * @param color
		 * @param value
		 * @return color
		 */
		public static Color lighter( Color color, int value ) {
			return new Color( 	(color.getRed() + value > 255)?255:color.getRed() + value,
								(color.getGreen() + value > 255)?255:color.getGreen() + value,
								(color.getBlue() + value > 255)?255:color.getBlue() + value );
		}
		
		/**
		 * Aclara un color según la constante
		 * @param color
		 * @return color
		 */
		public static Color lighter( Color color ) {
			return lighter( color, PROGRESS );
		}
	}
}
