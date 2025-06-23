package Conexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexiondb {

	private static final String URL ="jdbc:mysql://localhost:3306/tiendas"; 
	private static final String USUARIO = "root"; 
	private static final String CONTRASENA = "";
	
	public Connection conectar() {
		
	Connection conexion = null;

	try { 
		Class.forName("com.mysql.cj.jdbc.Driver"); conexion =
		DriverManager.getConnection(URL, USUARIO, CONTRASENA);
		System.out.println("Conexión exitosa a la base de datos MySQL.");

	} catch (ClassNotFoundException | SQLException e) {
		System.err.println("Error de conexión: " + e.getMessage()); 
	}

	return conexion; 
	}

}
