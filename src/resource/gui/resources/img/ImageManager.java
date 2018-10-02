package resource.gui.resources.img;

import java.net.URL;

public final class ImageManager {
	
	private ImageManager() {}
	
	/**
	 * Cargar una imagen del paquete de imagenes
	 * @param image
	 * @return url
	 */
	public static URL getImage( String image ) {
		return ImageManager.class.getResource( "/resource/gui/resources/img/" + image );
	}
	
}
