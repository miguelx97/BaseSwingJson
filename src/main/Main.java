package main;

import bbdd.PersistenciaFake;
import controlador.Controlador;
import vista.PanelForm;
import vista.PanelShow;
import vista.VistaPrincipal;


public class Main {
	
	public static void main(String[] args) {
		java.awt.EventQueue.invokeLater(new Runnable() {
			public void run() {
				// la vista: 
				VistaPrincipal vistaPrincipal = new VistaPrincipal();
				
				// los paneles 
				PanelForm panelForm = new PanelForm();
				PanelShow panelShow = new PanelShow();
				
				// y el control: 
				Controlador controlador = new Controlador(vistaPrincipal);
				controlador.setPanelForm(panelForm);
				controlador.setPanelShow(panelShow);
				
				// configura la vista 
				vistaPrincipal.setControlador(controlador);
				panelForm.setControlador(controlador);
				panelShow.setControlador(controlador);

				
				// y arranca la interfaz (vista): 
				vistaPrincipal.hacerVisible();
				
				//mostrar PanelShow
				panelShow.rellenarTabla(new PersistenciaFake().obtener());
				vistaPrincipal.definirPanel(panelShow);	
			}
		});

	}

}
