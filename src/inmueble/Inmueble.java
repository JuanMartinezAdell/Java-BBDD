package inmueble;

import java.time.LocalDate;

public class Inmueble {
	
	protected long id;
	protected String nombre;
	protected String direccion;
	protected String ciudad;
	protected long superficie;
	protected int habitaciones;
	protected double precio;
	protected LocalDate fechaCreacion;
	
	/**
	 * @param nombre
	 * @param direccion
	 * @param ciudad
	 * @param superficie
	 * @param habitaciones
	 * @param precio
	 * @param fechaCreacion
	 */
	public Inmueble(String nombre, String direccion, String ciudad, long superficie, int habitaciones, double precio,
			LocalDate fechaCreacion) {
		super();
		this.nombre = nombre;
		this.direccion = direccion;
		this.ciudad = ciudad;
		this.superficie = superficie;
		this.habitaciones = habitaciones;
		this.precio = precio;
		this.fechaCreacion = fechaCreacion;
	}

	/**
	 * @param id
	 * @param nombre
	 * @param direccion
	 * @param ciudad
	 * @param superficie
	 * @param habitaciones
	 * @param precio
	 * @param fechaCreacion
	 */
	public Inmueble(long id, String nombre, String direccion, String ciudad, long superficie, int habitaciones,
			double precio, LocalDate fechaCreacion) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.direccion = direccion;
		this.ciudad = ciudad;
		this.superficie = superficie;
		this.habitaciones = habitaciones;
		this.precio = precio;
		this.fechaCreacion = fechaCreacion;
	}

	/**
	 * @return the id
	 */
	public long getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(long id) {
		this.id = id;
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
	 * @return the direccion
	 */
	public String getDireccion() {
		return direccion;
	}

	/**
	 * @param direccion the direccion to set
	 */
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	/**
	 * @return the ciudad
	 */
	public String getCiudad() {
		return ciudad;
	}

	/**
	 * @param ciudad the ciudad to set
	 */
	public void setCiudad(String ciudad) {
		this.ciudad = ciudad;
	}

	/**
	 * @return the superficie
	 */
	public long getSuperficie() {
		return superficie;
	}

	/**
	 * @param superficie the superficie to set
	 */
	public void setSuperficie(long superficie) {
		this.superficie = superficie;
	}

	/**
	 * @return the habitaciones
	 */
	public int getHabitaciones() {
		return habitaciones;
	}

	/**
	 * @param habitaciones the habitaciones to set
	 */
	public void setHabitaciones(int habitaciones) {
		this.habitaciones = habitaciones;
	}

	/**
	 * @return the precio
	 */
	public double getPrecio() {
		return precio;
	}

	/**
	 * @param precio the precio to set
	 */
	public void setPrecio(double precio) {
		this.precio = precio;
	}

	/**
	 * @return the fechaCreacion
	 */
	public LocalDate getFechaCreacion() {
		return fechaCreacion;
	}

	/**
	 * @param fechaCreacion the fechaCreacion to set
	 */
	public void setFechaCreacion(LocalDate fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Inmueble [id=");
		builder.append(id);
		builder.append(", nombre=");
		builder.append(nombre);
		builder.append(", direccion=");
		builder.append(direccion);
		builder.append(", ciudad=");
		builder.append(ciudad);
		builder.append(", superficie=");
		builder.append(superficie);
		builder.append(", habitaciones=");
		builder.append(habitaciones);
		builder.append(", precio=");
		builder.append(precio);
		builder.append(", fechaCreacion=");
		builder.append(fechaCreacion);
		builder.append("]");
		return builder.toString();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (id ^ (id >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!(obj instanceof Inmueble))
			return false;
		Inmueble other = (Inmueble) obj;
		if (id != other.id)
			return false;
		return true;
	}
	
}
