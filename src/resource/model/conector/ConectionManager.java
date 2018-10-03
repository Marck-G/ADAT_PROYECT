package resource.model.conector;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;

import resource.gui.frames.dialog.DefaultDialog;

/**
 * Clase encargada de establecer la conexión con la base de datos <br/>
 *  y de guradar la información de la conexion en un archivo
 * @author mk_dev
 *
 */
public class ConectionManager {
	
	private final static String CONECTION_FILE = "_conexion.con";
	
	private DataBaseConection conector;
	
	
	public void	leerConexion() {
		try {
			BufferedReader reader = new BufferedReader( new InputStreamReader( new  FileInputStream( CONECTION_FILE ) ) );
		} catch (FileNotFoundException e) {
			// No se ha encontrado el archivo por lo que el usuario deberá crear una conexión
			new DefaultDialog("No se ha configurado ninguna conexión");
		}
		
	}
	
}
