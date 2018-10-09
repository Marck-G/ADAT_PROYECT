package resource.model.beans;

/**
 * Representa el alumno almacenado en la base de datos
 * @author Marck-G
 *
 */
public class Alumno {
	
	private String dni;
	private String nombre;
	private String ap1;
	private String ap2;
	
	/**
	 * @param dni
	 * @param nombre
	 * @param ap1
	 * @param ap2
	 */
	public Alumno(String dni, String nombre, String ap1, String ap2) {
		this.dni = dni;
		this.nombre = nombre;
		this.ap1 = ap1;
		this.ap2 = ap2;
	}
	
	/**
	 * Parsea un array de datos a un Alumno
	 * @param data
	 * @return Alumno parseado
	 */
	public static Alumno toAlumno( String[] data ) {
		return new Alumno( data[0], data[1], data[2], data[3] );
	}
	
	/**
	 * convierte el objeto en un array de datos
	 * @return String[]
	 */
	public String[] toArray() {
		String [] out = new String[4];
		out[0] = dni;
		out[1] = nombre;
		out[2] = ap1;
		out[3] = ap2;
		return out;
	}

	/**
	 * @return the dni
	 */
	public String getDni() {
		return dni;
	}

	/**
	 * @param dni the dni to set
	 */
	public void setDni(String dni) {
		this.dni = dni;
	}

	/**
	 * @return the nombre
	 */
	public String getNombre() {
		return nombre;
	}

	/**
	 * @param nombre the nombre to set
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	/**
	 * @return the ap1
	 */
	public String getAp1() {
		return ap1;
	}

	/**
	 * @param ap1 the ap1 to set
	 */
	public void setAp1(String ap1) {
		this.ap1 = ap1;
	}

	/**
	 * @return the ap2
	 */
	public String getAp2() {
		return ap2;
	}

	/**
	 * @param ap2 the ap2 to set
	 */
	public void setAp2(String ap2) {
		this.ap2 = ap2;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((dni == null) ? 0 : dni.hashCode());
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Alumno other = (Alumno) obj;
		if (dni == null) {
			if (other.dni != null)
				return false;
		} else if (!dni.equals(other.dni))
			return false;
		return true;
	}
	
	@Override
	public String toString() {
		
		return ap1 + " " + ap2 +", "+ nombre;
	}
	
}
