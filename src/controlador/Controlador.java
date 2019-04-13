package controlador;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JMenuItem;

import bbdd.PersistenciaFake;
import modelo.EjObjeto;
import vista.PanelForm;
import vista.PanelShow;
import vista.VistaPrincipal;

public class Controlador implements ActionListener {

	private VistaPrincipal vistaPrincipal;
	private PanelForm panelForm;
	private PanelShow panelShow;
	
	public Controlador(VistaPrincipal vPrincipal) {
		this.vistaPrincipal = vPrincipal;
	}
	
	public void setVistaPrincipal(VistaPrincipal vistaPrincipal) {
		this.vistaPrincipal = vistaPrincipal;
	}

	public void setPanelForm(PanelForm panelForm) {
		this.panelForm = panelForm;
	}

	public void setPanelShow(PanelShow panelShow) {
		this.panelShow = panelShow;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		System.out.println("CONTROLADOR");
		Object opcion = e.getSource();
		PersistenciaFake persistenciaFake = new PersistenciaFake();
		
		if (opcion instanceof JMenuItem) {		//MENU
			System.out.println("--JMenuItem");
			if (((JMenuItem) opcion).equals(vistaPrincipal.getMntmAdd())) {
				System.out.println("----getMntmAdd");
				panelForm.limpiar();
				vistaPrincipal.definirPanel(panelForm);
				panelForm.tipoPanel(PanelForm.INSERTAR);
			} else if (((JMenuItem) opcion).equals(vistaPrincipal.getMntmShow())) {
				System.out.println("----getMntmShow");
				ArrayList<EjObjeto> lista = persistenciaFake.obtener();
				panelShow.rellenarTabla(lista);
				vistaPrincipal.definirPanel(panelShow);	
			}
		} else if(opcion instanceof JButton) {		//BOTONES
			System.out.println("--JButton");
			if (((JButton) opcion).equals(panelForm.getBtnInsertar())) {	//Insertar
				EjObjeto ejObjeto = panelForm.getDatos();
				System.out.println("----getBtnInsertar. Param: " + ejObjeto.toString());
					if (ejObjeto.getEjInt() != -1) {
						int res = persistenciaFake.guardar(ejObjeto);
					if (res == 1) {
						vistaPrincipal.setMensaje(VistaPrincipal.INFO, "Contacto guardado");
					} else {
						vistaPrincipal.setMensaje(VistaPrincipal.INFO_ERROR, "ERROR al guardar el contacto");
					}	
					panelForm.limpiar();
				} else {
					vistaPrincipal.setMensaje(VistaPrincipal.INFO_ERROR, "Rellene los campos");
				}
			} else if (((JButton) opcion).equals(panelShow.getBtnModificar())) {	//Ir a modificar
				EjObjeto ejObjeto = panelShow.getDatoDeTabla();
				System.out.println("----getBtnModificar. Param: " + ejObjeto.toString());
				if (ejObjeto.getEjInt() != -1) {
					panelForm.setDatos(ejObjeto);
					panelForm.tipoPanel(PanelForm.MODIFICAR);
					vistaPrincipal.definirPanel(panelForm);
				} else {
					vistaPrincipal.setMensaje(VistaPrincipal.INFO_ERROR, "Selecciona una fila");
				}
			} else if (((JButton) opcion).equals(panelForm.getBtnModificar())) {		//Modificar
				EjObjeto ejObjeto = panelForm.getDatos();
				System.out.println("----getBtnModificar. Param: " + ejObjeto.toString());
				if (ejObjeto.getEjInt() != -1) {
					int res = persistenciaFake.modificar(ejObjeto);
					
					if (res == 1) {
						panelShow.rellenarTabla(persistenciaFake.obtener());
						vistaPrincipal.setMensaje(VistaPrincipal.INFO, "Contacto modificado");
						vistaPrincipal.definirPanel(panelShow);	
						
					} else {
						vistaPrincipal.setMensaje(VistaPrincipal.INFO_ERROR, "ERROR al modificar el contacto");
					}	
				}	else {
					vistaPrincipal.setMensaje(VistaPrincipal.INFO_ERROR, "Rellena los campos");
				}
			} else if (((JButton) opcion).equals(panelShow.getBtnEliminar())) {			//Eliminar
					int id = panelShow.eliminarContacto();
					System.out.println("----getBtnEliminar. Param: " + id);
					if(id != -1) {
						int res = persistenciaFake.eliminar(id);
						if (res == 1) {
							vistaPrincipal.setMensaje(VistaPrincipal.INFO, "Contacto eliminado");
						} else {
							vistaPrincipal.setMensaje(VistaPrincipal.INFO_ERROR, "ERROR al eliminar el contacto");
						}	
					} else {
						vistaPrincipal.setMensaje(VistaPrincipal.INFO_ERROR, "Selecciona una fila");
					}
								
			}
		}
		
//		((Component) opcion).addFocusListener(new FocusAdapter() {
//			@Override
//			public void focusLost(FocusEvent e) {
//				vistaPrincipal.setMensaje(VistaPrincipal.INFO_ERROR, "---");
//			}
//		});
	
	}

}
