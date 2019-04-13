package util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Log {
	
	Date fecha = new Date();
	String fechaFormato = new SimpleDateFormat("HH:mm:ss").format(fecha);
	
	public void imprimir(String clase, String funcion, String parametros, String mensaje) {
		String log = "";
		
		if(!clase.isEmpty()) {
			log += "Clase: " + clase + " | ";
		}
		if(!funcion.isEmpty()) {
			log += "Funcion: " + funcion + " | ";
		}
		if(!mensaje.isEmpty()) {
			log += "Mensaje: " + mensaje + " | ";
		}
		if(!parametros.isEmpty()) {
			log += "Parametros: " + parametros + " | ";		
		}
		
		if(!log.isEmpty()) {
			log= fechaFormato + "  " + log.replaceAll("\\|\\s*$", "");
		}
		
		
		
		System.out.println(log);
		
	}
	
	public void imprimir(String clase, String funcion, String parametros) {
		imprimir(clase, funcion, parametros, "");
	}
	
	public void imprimir(String funcion, String parametros) {
		imprimir("", funcion, parametros);
	}
	
	public void imprimir(String funcion) {
		imprimir(funcion, "");
	}

}
