package bbdd;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import modelo.EjObjeto;

public class Persistencia {
	public int guardar(EjObjeto ejObjeto) {
		int r = 0;
			
		try{
			Conexion conexion = new Conexion();
			Connection con = conexion.getConnection();
			String query =	"INSERT INTO EJBBDD (EJINT, EJSTRING) " +
									"VALUES (?, ?)";

			PreparedStatement pstmt = con.prepareStatement(query);
			pstmt.setInt(1, ejObjeto.getEjInt());
			pstmt.setString(2, ejObjeto.getEjString());
			
			r = pstmt.executeUpdate();
			
			pstmt.close();
			con.commit();
			
			if (con != null) con.close();
			
		} catch(SQLException s) {
		}
		return r;
	}
	
	public ArrayList<EjObjeto> obtener() {
		ArrayList<EjObjeto> listaEjObjetos = new ArrayList<EjObjeto>();
		
			try{
			Conexion conexion = new Conexion();
			Connection con = conexion.getConnection();
			String query = "SELECT ID, EJINT,  EJSTRING FROM EJBBDD";

			
			Statement pstmt = con.createStatement();
			ResultSet rset = pstmt.executeQuery(query);
			
			EjObjeto contact = null;
			
			while (rset.next()) {
				int id = rset.getInt("ID");
				int ejInt = rset.getInt("EJSTRING");
				String ejString = rset.getString("EJSTRING");
				
				contact = new EjObjeto(id, ejInt, ejString);
				
				listaEjObjetos.add(contact);
			}
			
			rset.close();
			pstmt.close();
			
			if (con != null) con.close();
			
		} catch (SQLException s) {
			s.printStackTrace();
		}
		
		return listaEjObjetos;
	}
	
	
	public int eliminar(int id) {
		int r = 0;
		
		try{
			Conexion conexion = new Conexion();
			Connection con = conexion.getConnection();
			String query =	"DELETE FROM EJBBDD WHERE ID_EJ=" + id;

			PreparedStatement pstmt = con.prepareStatement(query);
			
			r = pstmt.executeUpdate();
			
			pstmt.close();
			con.commit();
			
			if (con != null) con.close();
			
		} catch(SQLException s) {
		}
		return r;
	}
	
	public int modificarEjObjeto(EjObjeto ejObjeto) {
		int r = 0;
			
		try{
			Conexion conexion = new Conexion();
			Connection con = conexion.getConnection();
			String query =	" UPDATE CONTACTOS " +
							" SET  EJINT = ?,  EJSTRING = ?" +
							" WHERE ID_CONTACTO = ?";

			PreparedStatement pstmt = con.prepareStatement(query);
			pstmt.setInt(1, ejObjeto.getEjInt());
			pstmt.setString(2, ejObjeto.getEjString());
			pstmt.setInt(3, ejObjeto.getId());
			
			r = pstmt.executeUpdate();
			
			pstmt.close();
			con.commit();
			
			if (con != null) con.close();
			
		} catch(SQLException s) {
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