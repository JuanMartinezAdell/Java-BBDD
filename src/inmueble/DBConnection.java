package inmueble;
import java.sql.SQLException;
import javax.sql.rowset.JdbcRowSet;
import javax.sql.rowset.RowSetFactory;
import javax.sql.rowset.RowSetProvider;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 * 
 */

/**
 * @author Juan
 *
 */
public class DBConnection {
	
	private static final String JDBC_URL = "jdbc:mysql://localhost:3306/inmuebles";
	private static final String USERNAME = "root";
	private static final String PASSWORD = "jma123";
	
	private static Connection instance = null;
	
	private DBConnection() { }
	
	public static Connection getConnection() throws SQLException {
		if (instance == null) {
			Properties props = new Properties();
			props.put("user", USERNAME);
			props.put("password", PASSWORD);
			instance = DriverManager.getConnection(JDBC_URL, props);
		}
		
		return instance;
	}

}
