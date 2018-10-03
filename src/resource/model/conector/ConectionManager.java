package resource.model.conector;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;

public class ConectionManager {
	
	private final static String CONECTION_FILE = "_conexion.con";
	
	public DataBaseConection conector;
	
	
	public void	leerConexion() {
		try {
			BufferedReader reader = new BufferedReader( new InputStreamReader( new  FileInputStream( CONECTION_FILE ) ) );
		} catch (FileNotFoundException e) {
			// No se ha encontrado el archivo por lo que el usuario deberá crear una conexión
			
		}
		
	}
	
}
