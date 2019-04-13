package vista;

import java.awt.Dimension;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

import controlador.Controlador;
import modelo.EjObjeto;

public class PanelShow extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTable tablaContactos;
	private DefaultTableModel model;
	private JButton btnModificar;
	private JButton btnEliminar;
	
	public JButton getBtnModificar() {
		return btnModificar;
	}

	public JButton getBtnEliminar() {
		return btnEliminar;
	}

	public PanelShow() {
		inicializarComponentes();
	}

	private void inicializarComponentes() {
		setLayout(null);
		setPreferredSize(new Dimension(550, 350));
		tabla();
		JLabel lblMostrar = new JLabel("Mostrar");
		lblMostrar.setBounds(223, 23, 46, 14);
		add(lblMostrar);
		
		btnModificar = new JButton("Modificar");
		btnModificar.setBounds(26, 316, 89, 23);
		add(btnModificar);
		
		btnEliminar = new JButton("Eliminar");
		btnEliminar.setBounds(436, 316, 89, 23);
		add(btnEliminar);
		
	}
	

	private void tabla() {
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(26, 72, 499, 214);
		add(scrollPane);
		
		tablaContactos = new JTable();

		tablaContactos.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		model = new DefaultTableModel() {
			private static final long serialVersionUID = 1L;
			boolean[] columnEditables = {
				false, false, false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		};
		model.addColumn("id");
		model.addColumn("ejInt");
		model.addColumn("ejString");
		tablaContactos.setModel(model);
		tablaContactos.getColumnModel().getColumn(0).setMinWidth(0);
		tablaContactos.getColumnModel().getColumn(0).setMaxWidth(0);
		tablaContactos.getColumnModel().getColumn(0).setPreferredWidth(0);
		tablaContactos.getColumnModel().getColumn(0).setResizable(false);
		
		scrollPane.setViewportView(tablaContactos);
	}
	
	public void rellenarTabla (ArrayList<EjObjeto> listaContactos) {
		limpiarTabla();
		String[] fila = new String[3];
		
		for (EjObjeto item : listaContactos) {
			fila[0]= item.getId()+"";
			fila[1]= item.getEjInt()+"";
			fila[2]= item.getEjString();
			
			model.addRow(fila);
		}
		
	}
	
	public void limpiarTabla() {
		for (int i = model.getRowCount(); i > 0; i--) {
			model.removeRow(i-1);
		}
	}
	
	public void setControlador(Controlador c) {
		btnModificar.addActionListener(c);
		btnEliminar.addActionListener(c);		
	}
	
	public EjObjeto getDatoDeTabla () {
		int row = tablaContactos.getSelectedRow();
		EjObjeto ejObjeto;
		if(row != -1) {
		int id = Integer.parseInt(model.getValueAt(row,0)+"") ;
		int ejInt = Integer.parseInt(model.getValueAt(row,1)+"") ;
		String ejString = model.getValueAt(row,2) + "";
		
		ejObjeto = new EjObjeto(id, ejInt, ejString);
		} else {
			ejObjeto = new EjObjeto(0, -1,"");
		}

		
		
		return ejObjeto;
	}
	
	public int eliminarContacto () {
		int row = tablaContactos.getSelectedRow();
		int id = -1;
		if(row != -1) {
			id = Integer.parseInt(model.getValueAt(row,0) + "");
			model.removeRow(row);
			}
		return id;
	}

}
