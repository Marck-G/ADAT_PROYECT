/**
 * 
 */
package resource.model.beans;

/**
 *  Representa el libro que tenemos en la base de datos
 * @author Marck-G
 *
 */
public class Libro {
	
	private String codigo;
	private String isbn;
	private String titulo;
	private String autor;
	private String editorial;
	private String asignatura;
	private Estado estado;
	/**
	 * Contructor
	 * @param codigo
	 * @param titulo
	 * @param autor
	 * @param editorial
	 * @param asignatura
	 * @param estado
	 */
	public Libro(String codigo, String isbn, String titulo, String autor, String editorial, String asignatura, Estado estado) {
		super();
		this.codigo = codigo;
		this.titulo = titulo;
		this.autor = autor;
		this.editorial = editorial;
		this.asignatura = asignatura;
		this.estado = estado;
		this.isbn =isbn;
	}
	
	
	
	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((codigo == null) ? 0 : codigo.hashCode());
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
		Libro other = (Libro) obj;
		if (codigo == null) {
			if (other.codigo != null)
				return false;
		} else if (!codigo.equals(other.codigo))
			return false;
		return true;
	}

	

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return titulo;
	}



	/**
	 * @return the isbn
	 */
	public String getIsbn() {
		return isbn;
	}



	/**
	 * @return the codigo
	 */
	public String getCodigo() {
		return codigo;
	}
	/**
	 * @param codigo the codigo to set
	 */
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	/**
	 * @return the titulo
	 */
	public String getTitulo() {
		return titulo;
	}
	/**
	 * @param titulo the titulo to set
	 */
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	/**
	 * @return the autor
	 */
	public String getAutor() {
		return autor;
	}
	/**
	 * @param autor the autor to set
	 */
	public void setAutor(String autor) {
		this.autor = autor;
	}
	/**
	 * @return the editorial
	 */
	public String getEditorial() {
		return editorial;
	}
	/**
	 * @param editorial the editorial to set
	 */
	public void setEditorial(String editorial) {
		this.editorial = editorial;
	}
	/**
	 * @return the asignatura
	 */
	public String getAsignatura() {
		return asignatura;
	}
	/**
	 * @param asignatura the asignatura to set
	 */
	public void setAsignatura(String asignatura) {
		this.asignatura = asignatura;
	}
	/**
	 * @return the estado
	 */
	public Estado getEstado() {
		return estado;
	}
	/**
	 * @param estado the estado to set
	 */
	public void setEstado(Estado estado) {
		this.estado = estado;
	}
	
	
	
}
