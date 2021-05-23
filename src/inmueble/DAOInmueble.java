/**
 * 
 */
package inmueble;

/**
 * @author Juan
 *
 */

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;



public class DAOInmueble {
		
	public DAOInmueble() {
		//Comprobamos que no haya inmuebles, si no los hay los cargamos del CSV

		try {
			// Obtenemos la conexión
			Connection con = DBConnection.getConnection();
	
			// Creamos el objeto para enviar sentencias
			PreparedStatement ps = con
					.prepareStatement("SELECT * FROM inmuebles");
			ResultSet rs = ps.executeQuery();
			
			// Con que haya uno ya no se carga el CSV
			boolean vacio = true;
			if (rs.next()) {
				vacio = false;
			}
			ps.close();
			
			//Si está vacía la tabla la cargamos
			if (vacio) {
				DAOInmueble.cargarCSV();
			}
			
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		
	}
	
	public static boolean cargarCSV() {
		
		boolean realizado = true;
        Path ficheroCSV = Paths.get("inmuebles.csv");
        try (Stream<String> fileStream = Files.lines(ficheroCSV)) {
        	List<List<String>> values = fileStream.map(line -> Arrays.asList(line.split(","))).collect(Collectors.toList());
            values.forEach(value -> {
            	//Formato de la fecha en el fichero CSV
            	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("M/d/yyyy");

            	Inmueble in = new Inmueble(Integer.parseInt(value.get(0)), value.get(1), value.get(2), value.get(3), Integer.parseInt(value.get(4)),
            					Integer.parseInt(value.get(5)), Double.parseDouble(value.get(6)), LocalDate.parse(value.get(7), formatter));
                try {
                	//Insertamos el inmueble
					DAOInmueble.insertar(in);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
            });
            
            fileStream.close();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return realizado;
        
	}
	
	public static void insertar(Inmueble inmueble) throws SQLException {
		// Obtenemos la conexión
		Connection con = DBConnection.getConnection();

		// Creamos el objeto para enviar sentencias
		PreparedStatement ps = con
				.prepareStatement("INSERT INTO inmuebles (nombre,direccion,ciudad,superficie,habitaciones,precio,fechaCreacion) VALUES (?,?,?,?,?,?,?)");
		ps.setString(1, inmueble.getNombre());
		ps.setString(2, inmueble.getDireccion());
		ps.setString(3, inmueble.getCiudad());
		ps.setLong(4, inmueble.getSuperficie());
		ps.setInt(5, inmueble.getHabitaciones());
		ps.setDouble(6, inmueble.getPrecio());
		ps.setDate(7, Date.valueOf(inmueble.getFechaCreacion())); //LocalDate to Date
		ps.executeUpdate();
		
		ps.close();
	}
	
	public static void update(int id, Inmueble inmueble) throws SQLException {
		
		// Obtenemos la conexión
		Connection con = DBConnection.getConnection();

		// Creamos el objeto para enviar sentencias
		PreparedStatement ps = con
				.prepareStatement("UPDATE inmuebles SET nombre=?,direccion=?,ciudad=?,superficie=?,habitaciones=?,precio=?,fechaCreacion=? "
						+ "	WHERE id=?");
		ps.setString(1, inmueble.getNombre());
		ps.setString(2, inmueble.getDireccion());
		ps.setString(3, inmueble.getCiudad());
		ps.setLong(4, inmueble.getSuperficie());
		ps.setInt(5, inmueble.getHabitaciones());
		ps.setDouble(6, inmueble.getPrecio());
		ps.setDate(7, Date.valueOf(inmueble.getFechaCreacion())); //LocalDate to Date
		ps.setInt(8, id);

		ps.executeUpdate();
		
		ps.close();
	}
	
	public static void delete(int id) throws SQLException {
		
		// Obtenemos la conexión
		Connection con = DBConnection.getConnection();

		// Creamos el objeto para enviar sentencias
		PreparedStatement ps = con
				.prepareStatement("DELETE FROM inmuebles WHERE id = ?");
		ps.setInt(1, id);

		ps.executeUpdate();
		
		ps.close();
	}

	
	public static HashSet<Inmueble> getInmuebles() throws SQLException {
		HashSet<Inmueble> inmuebles = new HashSet<>();
		
		// Obtenemos la conexión
		Connection con = DBConnection.getConnection();

		// Creamos el objeto para enviar sentencias
		PreparedStatement ps = con
				.prepareStatement("SELECT * FROM inmuebles");

		ResultSet rs = ps.executeQuery();
		
		// Tomamos cada usuario y lo metemos en una lista a devolver
		while (rs.next()) {
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-M-d"); //Este es el formato de las fechas en MySQL
			inmuebles.add(new Inmueble(rs.getLong("id"), rs.getString("nombre"), rs.getString("direccion"), rs.getString("ciudad"), rs.getLong("superficie"),
										rs.getInt("habitaciones"), rs.getDouble("precio"), LocalDate.parse(rs.getString("fechaCreacion"), formatter))) ;
		}
			
		ps.close();
		
		return inmuebles;
		
	}
	
	public static Inmueble getInmueble(int id) throws SQLException {
		Inmueble inmueble = null;
		
		// Obtenemos la conexión
		Connection con = DBConnection.getConnection();

		// Creamos el objeto para enviar sentencias
		PreparedStatement ps = con
				.prepareStatement("SELECT * FROM inmuebles WHERE id = ?");
		ps.setInt(1, id);

		ResultSet rs = ps.executeQuery();
		
		// Sólo hay un usuario con ese login
		if (rs.next()) {
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-M-d"); //Este es el formato de las fechas en MySQL
			inmueble = new Inmueble(rs.getLong("id"), rs.getString("nombre"), rs.getString("direccion"), rs.getString("ciudad"), rs.getLong("superficie"),
					rs.getInt("habitaciones"), rs.getDouble("precio"), LocalDate.parse(rs.getString("fechaCreacion"), formatter)) ;
		}
		
		ps.close();

		return inmueble;		
	}
	
}
