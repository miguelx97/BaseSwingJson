package bbdd;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import modelo.EjObjeto;
import util.Log;

public class Persistencia implements IPersistencia{
	Log log = new Log(this.getClass().getSimpleName());
	public int guardar(EjObjeto ejObjeto) {
		int r = 0;
			
		try{
			Conexion conexion = new Conexion();
			Connection con = conexion.getConnection();
			String query =	"INSERT INTO ej_tabla (ej_int, ej_string) " +
									"VALUES (?, ?)";

			PreparedStatement pstmt = con.prepareStatement(query);
			pstmt.setInt(1, ejObjeto.getEjInt());
			pstmt.setString(2, ejObjeto.getEjString());
			
			r = pstmt.executeUpdate();
			
			pstmt.close();
			con.commit();
			
			if (con != null) con.close();
			
		} catch(SQLException ex) {
			log.error(new Object(){}.getClass().getEnclosingMethod().getName(), ex.getMessage());
		}
		return r;
	}
	
	
	public ArrayList<EjObjeto> obtenerLista() {
		ArrayList<EjObjeto> listaEjObjetos = new ArrayList<EjObjeto>();
		
			try{
			Conexion conexion = new Conexion();
			Connection con = conexion.getConnection();
			String query = "SELECT id, ej_int,  ej_string FROM ej_tabla";

			
			Statement pstmt = con.createStatement();
			ResultSet rset = pstmt.executeQuery(query);
			
			EjObjeto contact = null;
			
			while (rset.next()) {
				int id = rset.getInt("id");
				int ejInt = rset.getInt("ej_int");
				String ejString = rset.getString("ej_string");
				
				contact = new EjObjeto(id, ejInt, ejString);
				
				listaEjObjetos.add(contact);
			}
			
			rset.close();
			pstmt.close();
			
			if (con != null) con.close();
			
		} catch (SQLException ex) {
			log.error(new Object(){}.getClass().getEnclosingMethod().getName(), ex.getMessage());
		}
		
		return listaEjObjetos;
	}
	
	
	public int eliminar(int id) {
		int r = 0;
		
		try{
			Conexion conexion = new Conexion();
			Connection con = conexion.getConnection();
			String query =	"DELETE FROM ej_tabla WHERE id=" + id;

			PreparedStatement pstmt = con.prepareStatement(query);
			
			r = pstmt.executeUpdate();
			
			pstmt.close();
			con.commit();
			
			if (con != null) con.close();
			
		} catch(SQLException ex) {
			log.error(new Object(){}.getClass().getEnclosingMethod().getName(), ex.getMessage());
		}
		return r;
	}
	
	public int modificar(EjObjeto ejObjeto) {
		int r = 0;
			
		try{
			Conexion conexion = new Conexion();
			Connection con = conexion.getConnection();
			String query =	" UPDATE ej_tabla " +
							" SET  ej_int = ?,  ej_string = ?" +
							" WHERE id = ?";

			PreparedStatement pstmt = con.prepareStatement(query);
			pstmt.setInt(1, ejObjeto.getEjInt());
			pstmt.setString(2, ejObjeto.getEjString());
			pstmt.setInt(3, ejObjeto.getId());
			
			r = pstmt.executeUpdate();
			
			pstmt.close();
			con.commit();
			
			if (con != null) con.close();
			
		} catch(SQLException ex) {
			log.error(new Object(){}.getClass().getEnclosingMethod().getName(), ex.getMessage());
		}
		return r;
	}
	
	
}

/*
CREATE TABLE CONTACTOS
(
   ID_CONTACTO   NUMBER (4) PRIMARY KEY,
   NOMBRE        VARCHAR2 (20),
   NUMERO        VARCHAR2 (20) NOT NULL,
   DESTACADO     VARCHAR2 (2)
)
*/