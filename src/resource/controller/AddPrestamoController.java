package resource.controller;

import resource.gui.frames.AddPrestamo;

public class AddPrestamoController {
	
	// ===========| SINGLETON |=========== //
	private static AddPrestamoController instacia;
	
	public static AddPrestamoController instancia() {
		if( instacia == null )
			instacia = new AddPrestamoController();
		return instacia;
	}
	
	// ===============| FIN |============= //
	
	private AddPrestamo window;
	
	
	
}
