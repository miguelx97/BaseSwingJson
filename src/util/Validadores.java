package util;

public class Validadores {

	static public boolean esNumero(String str) {
		try {
			Double.parseDouble(str);
			return true;
		} catch (NumberFormatException e) {
			return false;
		}
	}
	
	static public boolean estaVacio(String str) {
		if(str.isEmpty()) {
			return true;
		} else {
			return false;
		}
	}

}
