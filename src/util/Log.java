package util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Log {
	private String clase;
	
	public Log(String clase) {
		this.clase = clase;
	}
	
	Date fecha = new Date();
	String fechaFormato = new SimpleDateFormat("yyyy/mm/dd hh:mm:ss").format(fecha);
	
	public void print(String mensaje) {			
		
		System.out.println(fechaFormato + "   [" + clase + "] " + mensaje);
		
	}
	
	
	public void error(String func, String error) {
		print("ERROR: " + "Function: " + func + ". Message: " + error);
	}
	
	
	public void iniFunc(String function) {
		print("Starting function: " + function + ". Class: " + clase);
	}
	
	
	public void iniFunc(String function, Object[] params) {
		
		String strParams = "";
		String separador = " , ";
		for (Object param : params) {
			
			if(param instanceof String){
				strParams += "\"" + param + "\"" + separador;
			} else{
				strParams += param + separador;
			}
			
			
		}
		
		strParams = strParams.replaceAll("[\\s\\S]{0," + separador.length() + "}$", "");
		
		print("Starting function: " + function + "(" + strParams + ")");
	}
}
