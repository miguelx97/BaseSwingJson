package bbdd;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

import util.Log;

public class Conexion {
	private String driver;
	private String url;
	private String usuario;
	private String password;
	private Connection con;
	
	Log log = new Log(this.getClass().getSimpleName());
	
	public Connection getConnection() {
		try {
			cargarAtributos();
			Class.forName(driver);
			con = DriverManager.getConnection(url, usuario, password);		
		} catch (SQLException ex) {
			System.out.println("Error en la conexion");
			log.error(new Object(){}.getClass().getEnclosingMethod().getName(), ex.getMessage());
		} catch (ClassNotFoundException e) {
			System.out.println("Error por Clase no encontrada");
			log.error(new Object(){}.getClass().getEnclosingMethod().getName(), e.getMessage());
		}
		return con;
	}

	private void cargarAtributos() {
		Properties propiedades = new Properties();
		InputStream entrada = null;
		try {
		entrada = new FileInputStream("configuracion.properties");
		// cargamos el archivo de propiedades
		propiedades.load(entrada);
		// obtenemos las propiedades y las imprimimos
		
//		driver = propiedades.getProperty("driver");
//		url = propiedades.getProperty("url");
//		usuario = propiedades.getProperty("usuario");
//		password = propiedades.getProperty("password");
		
		driver="org.h2.Driver";
		url="jdbc:h2:file:.\\bbdd\\bbdd";
		usuario="miguel";
		password="Pa$$w0rd";
		
		} catch (IOException ex) {
			log.error(new Object(){}.getClass().getEnclosingMethod().getName(), ex.getMessage());
		}

		
	}	
	
}