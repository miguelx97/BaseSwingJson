package bbdd;

import java.util.ArrayList;
import java.util.Arrays;

import modelo.EjObjeto;

public class PersistenciaFake implements IPersistencia{
	static int contador = 0;
	static ArrayList<EjObjeto> bbdd = new ArrayList<EjObjeto>(Arrays.asList(new EjObjeto(contador++, 1, "uno"),new EjObjeto(contador++, 2, "dos"),new EjObjeto(contador++, 3, "tres")));
	
	public int guardar(EjObjeto ejObjeto) {
		ejObjeto.setId(contador++);
		bbdd.add(ejObjeto);
		
		return 1;
	}
	
	public ArrayList<EjObjeto> obtenerLista() {
		return bbdd;
	}
	
	
	public int eliminar(int id) {
		EjObjeto ejObjetoBbdd;
		boolean encontrado = false;
		for (int i = 0; i < bbdd.size() || !encontrado; i++) {
			ejObjetoBbdd = bbdd.get(i);
			if(ejObjetoBbdd.getId() == id) {
				bbdd.remove(ejObjetoBbdd);
				encontrado = true;
			}
		}
		return 1;
	}
	
	public int modificar(EjObjeto ejObjeto) {
		
		EjObjeto ejObjetoBbdd;
		for (int i = 0; i < bbdd.size(); i++) {
			ejObjetoBbdd = bbdd.get(i);
			if(ejObjetoBbdd.getId() == ejObjeto.getId()) {
				bbdd.set(i, ejObjeto);
			}
		}
		return 1;
	}

	
	
	
}
