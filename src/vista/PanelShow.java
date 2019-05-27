package vista;

import java.awt.Dimension;
import java.awt.Font;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

import controlador.Controlador;
import modelo.EjObjeto;
import numerales.Medidas;
import javax.swing.JTextField;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class PanelShow extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTable tablaContactos;
	private DefaultTableModel model;
	private JButton btnModificar;
	private JButton btnEliminar;
	private JTextField txtFiltro;
	private JButton btnFiltrar;
	
	public JButton getBtnModificar() {
		return btnModificar;
	}

	public JButton getBtnEliminar() {
		return btnEliminar;
	}

	public JButton getBtnFiltrar() {
		return btnFiltrar;
	}

	public String getTxtFiltro() {
		return txtFiltro.getText();
	}

	public PanelShow() {
		inicializarComponentes();
	}

	private void inicializarComponentes() {
		setLayout(null);
		setPreferredSize(new Dimension(Medidas.WIDTH_PANELES, Medidas.HEIGHT_PANELES));
		tabla();
		JLabel lblTitulo = new JLabel("Mostrar");
		lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitulo.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblTitulo.setBounds(0, 0, Medidas.WIDTH_PANELES, 60);
		add(lblTitulo);
		
		btnModificar = new JButton("Modificar");
		btnModificar.setBounds(359, 316, 89, 23);
		add(btnModificar);
		
		btnEliminar = new JButton("Eliminar");
		btnEliminar.setBounds(483, 316, 89, 23);
		add(btnEliminar);
		
		btnFiltrar = new JButton("Filtrar");
		btnFiltrar.setBounds(165, 316, 72, 23);
		add(btnFiltrar);
		
		txtFiltro = new JTextField();
		txtFiltro.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				btnFiltrar.doClick();
			}
		});
		
		txtFiltro.setBounds(22, 317, 116, 20);
		add(txtFiltro);
		txtFiltro.setColumns(10);
		
	}
	

	private void tabla() {
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(22, 72, Medidas.WIDTH_PANELES, 214);
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
		btnFiltrar.addActionListener(c);	
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
	
	public void eliminarObjeto () {
		int row = tablaContactos.getSelectedRow();
		if(row != -1) {
			model.removeRow(row);
			}
	}
}
