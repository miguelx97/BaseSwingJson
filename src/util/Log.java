package util;

//import java.io.BufferedWriter;
//import java.io.FileWriter;
//import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Log {
	private String clase;
	
	//Inicializacion:			Log log = new Log(this.getClass().getSimpleName());
	//LLamada en funciones:		log.iniFunc(new Object(){}.getClass().getEnclosingMethod().getName(), new Object[]{});
	
	public Log(String clase) {
		this.clase = clase;
	}
	
	
	public void print(String mensaje) {		//Imprime los logs con fecha y hora
		
		Date fecha = new Date();
		String fechaFormato = new SimpleDateFormat("yyyy/mm/dd hh:mm:ss").format(fecha);
		String imprimir =fechaFormato + "   [" + clase + "] " + mensaje;
		System.out.println(imprimir);
//		BufferedWriter writer;
//		try {
//			writer = new BufferedWriter(new FileWriter("c:/logs.txt"));
//			writer.write(imprimir);
//			writer.close();
//		
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
	    
	}
	
	
	public void error(String func, String error) {		//Informa de un error
		print("ERROR: " + "Function: " + func + ". Mensaje: " + error);
	}
	
	
	public void iniFunc(String funcion) {		//Avisa de cuando entra en una funcion
		iniFunc(funcion, new Object[]{});
	}
	
	
	public void iniFunc(String funcion, Object[] params) {	//Avisa de cuando entra en una funcion con varios parametros
		
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
		
		print("Iniciando funcion: " + funcion + "(" + strParams + ")");
	}
	
	public void iniFunc(String funcion, String param) {		//Avisa de cuando entra en una funcion y envia un parametro
		print("Iniciando funcion: " + funcion + "(" + param + ")");
	}
	
	public void alert(String mensaje) {	//Para llamar la atencion
		print("¡¡¡¡ " + mensaje + " !!!!");
	}
	
	public void space() {	//Para separar unos los de otros
		System.out.println();
	}
	
}
