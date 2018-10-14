package resource.model.beans;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 
 * @author Marck-G
 *
 */
public class Prestamo {
	private Libro  		libro;
	private Alumno		alumno;
	private Date		fecha_alta;
	private Date		fecha_devolucion;
	private boolean		devuelto;
	/**
	 * @param libro
	 * @param alumno
	 * @param fecha_alta
	 * @param fecha_devolucion
	 */
	public Prestamo( Libro libro, Alumno alumno, Date fecha_alta, Date fecha_devolucion, boolean devuleto ) {
		this.libro 				= libro;
		this.alumno 			= alumno;
		this.fecha_alta 		= fecha_alta;
		this.fecha_devolucion 	= fecha_devolucion;
		this.devuelto			= devuleto;
	}
	
	/**
	 * Convertimos el objeto Prestamo a un array con toda la infomaci&oacute;n
	 * @return { codigo, titulo, dni, nombre+ap1, fecha_alt, fecha_dev, estado"
	 */
	public String[] toArray() {
		SimpleDateFormat d = new SimpleDateFormat("dd/MM/yy");
		String[] out = new String[7];
		out[0] = libro.getCodigo();
		out[1] = libro.getTitulo();
		out[2] = alumno.getDni();
		out[3] = alumno.getNombre() + " " + alumno.getAp1();
		out[4] = d.format( fecha_alta );
		out[5] = d.format( fecha_devolucion );
		out[6] = (devuelto)?"Devuelto":"No devuelto";
		return out;
	}
	
	/**
	 * 
	 * @param data{codigo,dni,fecha_alta,fecha_dev,devuelto}
	 * @return prestamo
	 * @throws ParseException 
	 */
	public static Prestamo toPrestamo( String[] data ) throws ParseException {
		SimpleDateFormat d = new SimpleDateFormat("dd/MM/yy");
		return new Prestamo(
				new Libro(data[0], "","", "", "", "", Estado.NUEVO),
				new Alumno(data[1], "", "", ""),
				d.parse(data[2]), 
				d.parse( data[3]),
				data[4].equalsIgnoreCase("devuelto") );
	}
	
	@Override
	public String toString() {
		return libro.toString();
	}
	
	/**
	 * @return the libros
	 */
	public Libro getLibro() {
		return libro;
	}
	/**
	 * @param libros the libros to set
	 */
	public void setLibro(Libro libro) {
		this.libro = libro;
	}
	/**
	 * @return the alumno
	 */
	public Alumno getAlumno() {
		return alumno;
	}
	/**
	 * @param alumno the alumno to set
	 */
	public void setAlumno(Alumno alumno) {
		this.alumno = alumno;
	}
	/**
	 * @return the fecha_alta
	 */
	public Date getFecha_alta() {
		return fecha_alta;
	}
	/**
	 * @param fecha_alta the fecha_alta to set
	 */
	public void setFecha_alta(Date fecha_alta) {
		this.fecha_alta = fecha_alta;
	}
	/**
	 * @return the fecha_devolucion
	 */
	public Date getFecha_devolucion() {
		return fecha_devolucion;
	}
	/**
	 * @param fecha_devolucion the fecha_devolucion to set
	 */
	public void setFecha_devolucion(Date fecha_devolucion) {
		this.fecha_devolucion = fecha_devolucion;
	}
	/**
	 * @return the devuelto
	 */
	public boolean isDevuelto() {
		return devuelto;
	}
	/**
	 * @param devuelto the devuelto to set
	 */
	public void setDevuelto(boolean devuelto) {
		this.devuelto = devuelto;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((alumno == null) ? 0 : alumno.hashCode());
		result = prime * result + ((fecha_alta == null) ? 0 : fecha_alta.hashCode());
		result = prime * result + ((libro == null) ? 0 : libro.hashCode());
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
		Prestamo other = (Prestamo) obj;
		if (alumno == null) {
			if (other.alumno != null)
				return false;
		} else if (!alumno.equals(other.alumno))
			return false;
		if (fecha_alta == null) {
			if (other.fecha_alta != null)
				return false;
		} else if (!fecha_alta.equals(other.fecha_alta))
			return false;
		if (libro == null) {
			if (other.libro != null)
				return false;
		} else if (!libro.equals(other.libro))
			return false;
		return true;
	}
	
	
	
	
	
}
