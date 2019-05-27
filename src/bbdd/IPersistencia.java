package bbdd;

import java.util.ArrayList;

import modelo.EjObjeto;

public interface IPersistencia {
	public int guardar(EjObjeto ejObjeto);
	public ArrayList<EjObjeto> obtenerLista(String filtro);
	public int eliminar(int id);
	public int modificar(EjObjeto ejObjeto);
}
