package bbdd;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.google.gson.Gson;

import modelo.EjObjeto;
import util.Log;

public class Persistencia implements IPersistencia{
	Log log = new Log(this.getClass().getSimpleName());
	Gson gson = new Gson();
	String data="";
	public int guardar(EjObjeto ejObjeto) {
		data=gson.toJson(ejObjeto);
		log.print("guardar: " + data);
		int r = 0;
		try{
			Conexion conexion = new Conexion();
			Connection con = conexion.getConnection();
			String query =	"INSERT INTO ej_tabla (data) " +
									"VALUES (?)";

			PreparedStatement pstmt = con.prepareStatement(query);
			pstmt.setString(1, data);
			
			r = pstmt.executeUpdate();
			
			pstmt.close();
			con.commit();
			
			if (con != null) con.close();
			
		} catch(SQLException ex) {
			log.error(new Object(){}.getClass().getEnclosingMethod().getName(), ex.getMessage());
		}
		return r;
	}
	
	
	public ArrayList<EjObjeto> obtenerLista(String filtro) {
		ArrayList<EjObjeto> listaEjObjetos = new ArrayList<EjObjeto>();
		
			try{
			Conexion conexion = new Conexion();
			Connection con = conexion.getConnection();
			String query = "SELECT id, data FROM ej_tabla "
					+ " where data LIKE '%" + filtro + "%'";

			
			Statement pstmt = con.createStatement();
			ResultSet rset = pstmt.executeQuery(query);
			
			EjObjeto ejObjeto = null;
			
			while (rset.next()) {
				int id = rset.getInt("id");
				data = rset.getString("data");
				
				ejObjeto = gson.fromJson(data, EjObjeto.class);
				ejObjeto.setId(id);
				
				listaEjObjetos.add(ejObjeto);
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
		data=gson.toJson(ejObjeto);
		log.print("modificar: " + data);
		try{
			Conexion conexion = new Conexion();
			Connection con = conexion.getConnection();
			String query =	" UPDATE ej_tabla " +
							" SET  data = ?" +
							" WHERE id = ?";

			PreparedStatement pstmt = con.prepareStatement(query);
			pstmt.setString(1, data);
			pstmt.setInt(2, ejObjeto.getId());
			
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

