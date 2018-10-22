package pruebas;

import java.sql.SQLException;

import resource.model.conector.ConectorFactory;
import resource.model.query.controllers.LibrosController;

public class Pruebas {
	public static void main(String[] args) throws Exception {
		ConectorFactory.setDataBase( ConectorFactory.MYSQL_DB );
		ConectorFactory.getBaseActiva().conect();
		LibrosController lManager = LibrosController.instancia();
		System.out.println(lManager.getLibrosCodigo(codigo));
	}

}
