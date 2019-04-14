package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JMenuItem;

import bbdd.PersistenciaFake;
import modelo.EjObjeto;
import util.Log;
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
		PersistenciaFake persistencia = new PersistenciaFake();
		Log log = new Log();
		log.imprimir("CONTROLADOR");
		Object opcion = e.getSource();
		
		
		if (opcion instanceof JMenuItem) {		//MENU
			log.imprimir("JMenuItem");
			if (((JMenuItem) opcion).equals(vistaPrincipal.getMntmAdd())) {
				log.imprimir("getMntmAdd");
				panelForm.limpiar();
				vistaPrincipal.definirPanel(panelForm);
				panelForm.tipoPanel(PanelForm.INSERTAR);
			} else if (((JMenuItem) opcion).equals(vistaPrincipal.getMntmShow())) {
				log.imprimir("getMntmShow");
				ArrayList<EjObjeto> lista = persistencia.obtener();
				panelShow.rellenarTabla(lista);
				vistaPrincipal.definirPanel(panelShow);	
			}
		} else if(opcion instanceof JButton) {		//BOTONES
			log.imprimir("JButton");
			if (((JButton) opcion).equals(panelForm.getBtnInsertar())) {	//Insertar
				insertar(persistencia, log);
			} else if (((JButton) opcion).equals(panelShow.getBtnModificar())) {	//Ir a modificar
				irModificar(log);
			} else if (((JButton) opcion).equals(panelForm.getBtnModificar())) {		//Modificar
				modificar(persistencia, log);
			} else if (((JButton) opcion).equals(panelShow.getBtnEliminar())) {		//Eliminar
				eliminar(persistencia, log);								
			}
		}

		
		log.imprimir("");
	
	}

	private void insertar(PersistenciaFake persistenciaFake, Log log) {
		EjObjeto ejObjeto = panelForm.getDatos();
		log.imprimir("getBtnInsertar" , ejObjeto.toString());
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
	}

	private void irModificar(Log log) {
		EjObjeto ejObjeto = panelShow.getDatoDeTabla();
		log.imprimir("getBtnModificar", ejObjeto.toString());
		if (ejObjeto.getEjInt() != -1) {
			panelForm.setDatos(ejObjeto);
			panelForm.tipoPanel(PanelForm.MODIFICAR);
			vistaPrincipal.definirPanel(panelForm);
		} else {
			vistaPrincipal.setMensaje(VistaPrincipal.INFO_ERROR, "Selecciona una fila");
		}
	}

	private void modificar(PersistenciaFake persistenciaFake, Log log) {
		EjObjeto ejObjeto = panelForm.getDatos();
		log.imprimir("getBtnModificar" , ejObjeto.toString());
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
	}

	private void eliminar(PersistenciaFake persistenciaFake, Log log) {
				int id = panelShow.eliminarContacto();
				log.imprimir("getBtnEliminar", id+"");
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
