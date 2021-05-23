/**
 * 
 */
package inmueble;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashSet;

/**
 * @author Juan
 *
 */
public class DAOInmobiliaria {
		
	public DAOInmobiliaria() {
		
	}
	
	public static Venta getVenta(int idInmueble) throws SQLException {
		Venta venta = null;
		
		// Obtenemos la conexión
		Connection con = DBConnection.getConnection();

		// Creamos el objeto para enviar sentencias
		PreparedStatement ps = con
				.prepareStatement("SELECT * FROM ventas WHERE id = ?");
		ps.setInt(1, idInmueble);

		ResultSet rs = ps.executeQuery();
		
		// Sólo hay un usuario con ese login
		if (rs.next()) {
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-M-d"); //Este es el formato de las fechas en MySQL
			venta = new Venta(rs.getInt("id_inmueble"), rs.getDouble("precio_venta"), LocalDate.parse(rs.getString("fecha"), formatter),
								rs.getString("comprador")) ;
		}

		ps.close();

		//Le añadimos el objeto Inmueble a la Venta
		Inmueble inmueble = DAOInmueble.getInmueble((int) venta.getInmueble().getId());
		if(inmueble != null)
			venta.setInmueble(inmueble);
		
		return venta;
	}

	
	public static HashSet<Venta> getVentas() throws SQLException {
		HashSet<Venta> ventas = new HashSet<>();
		
		// Obtenemos la conexión
		Connection con = DBConnection.getConnection();

		// Creamos el objeto para enviar sentencias
		PreparedStatement ps = con
				.prepareStatement("SELECT * FROM ventas");

		ResultSet rs = ps.executeQuery();
		
		// Tomamos cada usuario y lo metemos en una lista a devolver
		while (rs.next()) {
			Venta venta = null;
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-M-d"); //Este es el formato de las fechas en MySQL
			venta = new Venta(rs.getInt("id_inmueble"), rs.getDouble("precio_venta"), LocalDate.parse(rs.getString("fecha"), formatter),
								rs.getString("comprador")) ;
			//Le añadimos el objeto Inmueble a la Venta
			Inmueble inmueble = DAOInmueble.getInmueble((int) venta.getInmueble().getId());
			if(inmueble != null)
				venta.setInmueble(inmueble);
			
			//Añadimos la venta con su inmueble a las ventas
			ventas.add(venta);
			
		}
			
		ps.close();
		
		return ventas;
		
	}
	
	public static void addVenta(int idInmueble, double precioVenta, LocalDate fechaVenta, String comprador) throws SQLException {
		// Obtenemos la conexión
		Connection con = DBConnection.getConnection();

		// Creamos el objeto para enviar sentencias
		PreparedStatement ps = con
				.prepareStatement("INSERT INTO ventas (id_inmueble, precio_venta, fecha, comprador) VALUES (?,?,?,?)");
		ps.setInt(1, idInmueble);
		ps.setDouble(2, precioVenta);
		ps.setDate(3, Date.valueOf(fechaVenta)); //LocalDate to Date
		ps.setString(4, comprador);
		ps.executeUpdate();
		
		ps.close();
	}
	
	public static void updateVenta(int idInmueble, double precioVenta, LocalDate fechaVenta, String comprador) throws SQLException {
		// Obtenemos la conexión
		Connection con = DBConnection.getConnection();

		// Creamos el objeto para enviar sentencias
		PreparedStatement ps = con
				.prepareStatement("UPDATE ventas SET precio_venta=?,fecha=?,comprador=? "
						+ "	WHERE id_inmueble=?");
		ps.setInt(4, idInmueble);
		ps.setDouble(1, precioVenta);
		ps.setDate(2, Date.valueOf(fechaVenta)); //LocalDate to Date
		ps.setString(3, comprador);
		ps.executeUpdate();
		
		ps.close();
	}
}
