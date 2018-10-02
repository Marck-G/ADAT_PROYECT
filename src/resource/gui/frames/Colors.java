package resource.gui.frames;

import java.awt.Color;

public final class Colors {
	public static final Color PRIMARY 	= Utils.hexDecode( "26c6da" );
	public static final Color P_LIGHT 	= Utils.hexDecode( "6ff9ff" );
	
	
	
	final static class Utils{
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
	}
}
