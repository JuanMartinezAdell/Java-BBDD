/**
 * 
 */
package inmueble;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * @author Juan
 *
 */
public class Inmobiliaria {
	
	protected HashSet<Inmueble> inmobiliaria;
	protected String nombre;
	/**
	 * @param nombre
	 */
	public Inmobiliaria(String nombre) {
		super();
		this.nombre = nombre;
		
		DAOInmueble in = new DAOInmueble(); //Si la BD está vacía los carga del fichero CSV
		try {
			this.inmobiliaria = DAOInmueble.getInmuebles();
		} catch (SQLException e) {
			e.printStackTrace();
		}
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
	 * @return the inmobiliaria
	 */
	public HashSet<Inmueble> getInmobiliaria() {
		return inmobiliaria;
	}
	

	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Inmobiliaria [nombre=");
		builder.append(nombre);
		builder.append("]");
		builder.append("   Inmuebles:" + System.getProperty("line.separator"));
		for(Inmueble i : this.inmobiliaria) {
			builder.append(i.getId() + " - " + i.getNombre());
			builder.append(System.getProperty("line.separator"));
		}
		return builder.toString();
	}
	
	/**
	 * Devuelve los Inmuebles con un precio superior al indicado en el parámetro
	 * @param precio
	 * @return HashSet<Inmueble> de aquellos mayores a un precio
	 */
	public HashSet<Inmueble> inmueblesPorPrecioMayor(long precio) {
		return (HashSet<Inmueble>) this.getInmobiliaria().stream()
						.filter(i -> i.getPrecio() > precio)
						.sorted( (i1,i2) -> (int) (i1.getPrecio() - i2.getPrecio()) )
						.collect(Collectors.toSet());
	}
	
	/**
	 * Devuelve los Inmuebles con un precio inferior o igual al indicado en el parámetro
	 * @param precio
	 * @return HashSet<Inmueble> de aquellos inferiores o iguales a un precio
	 */
	public HashSet<Inmueble> inmueblesPorPrecioMenor(long precio) {
		return (HashSet<Inmueble>) this.getInmobiliaria().stream()
						.filter(i -> i.getPrecio() <= precio)
						.sorted( (i1,i2) -> (int) (i1.getPrecio() - i2.getPrecio()) )
						.collect(Collectors.toSet());
	}
	
	/**
	 * Devuelve los Inmuebles con superficie superior a la indicada en el parámetro
	 * @param precio
	 * @return HashSet<Inmueble> de aquellos mayores a una superficie
	 */
	public HashSet<Inmueble> inmueblesPorSuperficieMayor(int superficie) {
		return (HashSet<Inmueble>) this.getInmobiliaria().stream()
				.filter(i -> i.getSuperficie() > superficie)
				.sorted( (i1,i2) -> (int) (i1.getSuperficie() - i2.getSuperficie()) )
				.collect(Collectors.toSet());
	}
	
	/**
	 * Devuelve los Inmuebles con superficie inferior o igual a la indicada en el parámetro
	 * @param precio
	 * @return HashSet<Inmueble> de aquellos inferiores o iguales a una superficie
	 */
	public HashSet<Inmueble> inmueblesPorSuperficieMenor(int superficie) {
		return (HashSet<Inmueble>) this.getInmobiliaria().stream()
				.filter(i -> i.getSuperficie() <= superficie)
				.sorted( (i1,i2) -> (int) (i1.getSuperficie() - i2.getSuperficie()) )
				.collect(Collectors.toSet());
	}
	
	/**
	 * Devuelve los Inmuebles con superficie inferior o igual a la indicada en el parámetro
	 * @param precio
	 * @return HashSet<Inmueble> de aquellos inferiores o iguales a una superficie
	 */
	public HashSet<Inmueble> inmueblesPorCiudad(String ciudad) {
		return (HashSet<Inmueble>) this.getInmobiliaria().stream()
				.filter(i -> i.getCiudad().contains(ciudad))
				.sorted( (i1,i2) -> (int) (i1.getPrecio() - i2.getPrecio()) )
				.collect(Collectors.toSet());
	}
	
	/**
	 * Devuelve los Inmuebles que cumplan un Predicate
	 * @param precio
	 * @return HashSet<Inmueble> de aquellos que cumplan una condicion relativa a los inmuebles
	 */
	public HashSet<Inmueble> inmueblesPorCondicion(Predicate<Inmueble> condicion) {
		return (HashSet<Inmueble>) this.getInmobiliaria().stream()
				.filter(condicion)
				.sorted( (i1,i2) -> (int) (i1.getPrecio() - i2.getPrecio()) )
				.collect(Collectors.toSet());
	}
	
	public Map<String, List<Inmueble>> inmueblesAgrupadosPorCiudad() {
		return this.getInmobiliaria().stream()
						.collect(Collectors.groupingBy(Inmueble::getCiudad));
	}
	
	
	/////////////// Métodos sobre Ventas
	
	/**
	 * Calcula el 3% de todas las ventas de la inmobiliaria en el año pasado como parámetro
	 * @param año
	 * @return 3% total del ventas en el año
	 */
	public double getBeneficiosAnuales(int año) {
		
		HashSet<Venta> ventas = null;
		try {
			ventas = DAOInmobiliaria.getVentas();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		double total = 0;
		if (ventas != null) {
			total = ventas.stream()
					.filter( v -> v.getFecha().getYear() == año)
					.mapToDouble( v -> v.getPrecioVenta())
					.sum();
			
			//El beneficio de la inmobiliaria es el 3% del total de las ventas
			total = total * 0.03;
		}
		 
		
		return total;
	}
	
	
	
	
	
	
	public int getNumeroVentasAnuales(int año) {
		HashSet<Venta> ventas = null;
		try {
			ventas = DAOInmobiliaria.getVentas();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		long total = 0;
		if (ventas != null) {
			total = ventas.stream()
					.filter( v -> v.getFecha().getYear() == año)
					.count();
		}
		 
		return (int) total;
		
	}
	
	/**
	 * Mira todas las ventas y devuelve aquellas cuyo inmueble esté localizado en la ciudad pasada por parámetro
	 * @param ciudad
	 * @return HashSet de Venta donde de aquellas ventas realizadas en inmuebles en la ciudad parámetro
	 */
	public HashSet<Venta> getVentasPorCiudad(String ciudad) {
		HashSet<Venta> ventasCiudad = null;
		try {
			ventasCiudad = DAOInmobiliaria.getVentas();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		ventasCiudad = (HashSet<Venta>) ventasCiudad.stream()
					.filter(v -> v.getInmueble().getCiudad().equals(ciudad))
					.collect(Collectors.toSet());
		
		return ventasCiudad;
	}
	
	
	/**
	 * Método que devuelve un Map con cada ciudad y el total de beneficios en esa ciudad
	 * @return
	 */
	public HashMap<String, Double> getBeneficioVentasCiudad() {
		HashMap<String, Double> ventasCiudad = null;
		try {
			ventasCiudad = (HashMap<String, Double>) DAOInmobiliaria.getVentas().stream()
						.collect( Collectors.groupingBy( (Venta v) -> v.getInmueble().getCiudad(), Collectors.summingDouble(Venta::getPrecioVenta) ));
			
			//Saco el count, pero no consigo sacar el precio.
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return ventasCiudad;
	}
}
