/**
 * 
 */
package inmueble;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Collectors;;

/**
 * @author Juan
 *
 */
public class Test {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Inmobiliaria inmo = new Inmobiliaria("Tucasaylamia");
		System.out.println(inmo);
		System.out.println(inmo.inmueblesPorPrecioMayor(5000000));
		HashMap<String , List<Inmueble>> inmueblesCiudad = (HashMap<String, List<Inmueble>>) inmo.inmueblesAgrupadosPorCiudad();
		Set<String> ciudades = inmueblesCiudad.keySet();
		for(String c : ciudades) {
			System.out.println(c+": ");
			for(Inmueble i : inmueblesCiudad.get(c)) {
				System.out.print("- "+i.getNombre()+", ");
			}
			System.out.println();
			
		}
		
		
		//Ventas
		try {
			HashSet<Venta> ventas = DAOInmobiliaria.getVentas();
			for(Venta v : ventas) {
				System.out.println("Inmueble '" + v.getInmueble().getNombre() + "' vendido por " + v.getPrecioVenta() + "€");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		System.out.println("Beneficio total (3% ventas) " + inmo.getBeneficiosAnuales(2021) + "€");
		System.out.println("Número de inmuebles vendidos en 2001: " + inmo.getNumeroVentasAnuales(2021));
		System.out.println("Ventas en 'Palaran' durante todo el tiempo: "+inmo.getVentasPorCiudad("Palaran"));
		System.out.println("Beneficio ciudad: "+inmo.getBeneficioVentasCiudad());
		 
	}

}
