package fishingtools.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Logger;


public class ConnectionUtil {
	
	private static final Logger log = Logger.getLogger(ConnectionUtil.class.getName());
	
	private static Connection connection;
	
	private static String host = "jdbc:mysql://localhost:3306/fishingtools";
	private static String dbUsername = "root";
	private static String dbPassword = "convertit";
	
	static {
		try {
			// incarcam driver pentru Mysql
			Class.forName("com.mysql.jdbc.Driver");
			log.info("loaded mysql driver");
		} catch (ClassNotFoundException e) {
			log.severe(String.format("Class not found: %s", e.getMessage()));
			e.printStackTrace();
		}
	}

	public static Connection getConnection() throws SQLException {
		// obtin conexiunea de la DriverManager
		connection = DriverManager.getConnection(host, dbUsername, dbPassword);
		log.info("loaded connection");
		return connection;
	}

}
