/**
 * 
 */
package inmueble;

import java.sql.SQLException;
import java.time.LocalDate;

/**
 * @author Juan
 *
 */
public class Venta {
	
	protected Inmueble inmueble;
	protected double precioVenta;
	protected LocalDate fecha;
	protected String comprador;  //En verdad esto debería ser otra clase Usuario y una tabla users
	
	/**
	 * @param inmueble
	 * @param precioVenta
	 * @param fecha
	 * @param comprador
	 */
	public Venta(Inmueble inmueble, double precioVenta, LocalDate fecha, String comprador) {
		super();
		this.inmueble = inmueble;
		this.precioVenta = precioVenta;
		this.fecha = fecha;
		this.comprador = comprador;
	}

	/**
	 * @param inmueble
	 * @param precioVenta
	 * @param fecha
	 * @param comprador
	 */
	public Venta(int id, double precioVenta, LocalDate fecha, String comprador) {
		super();
		try {
			this.inmueble = DAOInmueble.getInmueble(id);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		this.precioVenta = precioVenta;
		this.fecha = fecha;
		this.comprador = comprador;
	}

	/**
	 * @return the inmueble
	 */
	public Inmueble getInmueble() {
		return inmueble;
	}

	/**
	 * @param inmueble the inmueble to set
	 */
	public void setInmueble(Inmueble inmueble) {
		this.inmueble = inmueble;
	}

	/**
	 * @return the precioVenta
	 */
	public double getPrecioVenta() {
		return precioVenta;
	}

	/**
	 * @param precioVenta the precioVenta to set
	 */
	public void setPrecioVenta(double precioVenta) {
		this.precioVenta = precioVenta;
	}

	/**
	 * @return the fecha
	 */
	public LocalDate getFecha() {
		return fecha;
	}

	/**
	 * @param fecha the fecha to set
	 */
	public void setFecha(LocalDate fecha) {
		this.fecha = fecha;
	}

	/**
	 * @return the comprador
	 */
	public String getComprador() {
		return comprador;
	}

	/**
	 * @param comprador the comprador to set
	 */
	public void setComprador(String comprador) {
		this.comprador = comprador;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Venta [inmueble=");
		builder.append(inmueble);
		builder.append(", precioVenta=");
		builder.append(precioVenta);
		builder.append(", fecha=");
		builder.append(fecha);
		builder.append(", comprador=");
		builder.append(comprador);
		builder.append("]");
		return builder.toString();
	}

}
