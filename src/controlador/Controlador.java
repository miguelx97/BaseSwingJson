package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JMenuItem;

import bbdd.PersistenciaFake;
import modelo.CustomReturn;
import modelo.EjObjeto;
import util.Log;
import vista.PanelForm;
import vista.PanelShow;
import vista.VistaPrincipal;

public class Controlador implements ActionListener {

	private VistaPrincipal vistaPrincipal;
	private PanelForm panelForm;
	private PanelShow panelShow;
	Log log = new Log();
	
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
			if (((JButton) opcion).equals(panelForm.getBtnInsertarModificar())) {	//Insertar o modificar
				insertarModificar(persistencia);
			} else if (((JButton) opcion).equals(panelShow.getBtnModificar())) {	//Ir a modificar
				irModificar();
			} else if (((JButton) opcion).equals(panelShow.getBtnEliminar())) {		//Eliminar
				eliminar(persistencia);								
			}
		}

		
		log.imprimir("");
	
	}

	
	private void insertarModificar(PersistenciaFake persistencia) {			//INSERTAR y MODIFICAR
		EjObjeto ejObjeto = null;
		CustomReturn ret = panelForm.getDatos();
		
		if (ret.getError() == null) {
			ejObjeto = (EjObjeto) ret.getObject();
			log.imprimir("insertarModificar" , ejObjeto.toString());
			
				int res = 0;
				if(ejObjeto.getId() == -2) {	//insertar
					res = persistencia.guardar(ejObjeto);
					
					if (res == 1) {
						vistaPrincipal.setMensaje(VistaPrincipal.INFO, "Objeto guardado");
						panelForm.limpiar();
					}
				} else {						//modificar
					res = persistencia.modificar(ejObjeto);
					
					if (res == 1) {
						panelShow.rellenarTabla(persistencia.obtener());
						vistaPrincipal.setMensaje(VistaPrincipal.INFO, "Objeto modificado");
						vistaPrincipal.definirPanel(panelShow);	
					}	
				}

				if(res != 1) {
					error("Error al guardar en la base de datos");
				}
						
		} else {
			error(ret.getError());
		}
		
	}
	

	private void irModificar() {										//IR A MODIFICAR
		EjObjeto ejObjeto = panelShow.getDatoDeTabla();
		log.imprimir("getBtnModificar", ejObjeto.toString());
		if (ejObjeto.getEjInt() != -1) {
			panelForm.setDatos(ejObjeto);
			panelForm.tipoPanel(PanelForm.MODIFICAR);
			vistaPrincipal.definirPanel(panelForm);
		} else {
			error("Selecciona una fila");
		}
	}

	private void eliminar(PersistenciaFake persistenciaFake) {			//ELIMINAR
				int id = panelShow.eliminarObjeto();
				log.imprimir("getBtnEliminar", id+"");
				if(id != -1) {
					int res = persistenciaFake.eliminar(id);
					if (res == 1) {
						vistaPrincipal.setMensaje(VistaPrincipal.INFO, "Objeto eliminado");
					} else {
						error("ERROR al eliminar el contacto");
					}	
				} else {
					error("Selecciona una fila");
				}
	}
	
	
	public void error(String mensaje) {
		vistaPrincipal.setMensaje(VistaPrincipal.INFO_ERROR, mensaje);
	}

}
