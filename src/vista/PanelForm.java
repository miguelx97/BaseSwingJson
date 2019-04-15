package vista;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JPanel;

import controlador.Controlador;
import modelo.EjObjeto;
import numerales.Medidas;

import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class PanelForm extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public static final int INSERTAR = 1;
	public static final int MODIFICAR = 2;
	
	private int id;
	private JLabel lblTitulo;
	private JTextField txtInt;
	private JTextField txtString;
	private JButton btnInsertar;
	private JButton btnModificar;

	public JButton getBtnModificar() {
		return btnModificar;
	}

	public JButton getBtnInsertar() {
		return btnInsertar;
	}

	public PanelForm() {
		inicializarComponentes();
	}

	private void inicializarComponentes() {
		setLayout(null);
		setPreferredSize(new Dimension(Medidas.WIDTH_PANELES, Medidas.HEIGHT_PANELES));
		
		lblTitulo = new JLabel("FORM");
		lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitulo.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblTitulo.setBounds(0, 0, Medidas.WIDTH_PANELES, 60);
		add(lblTitulo);
		
		JLabel lblEjemploInt = new JLabel("Ejemplo Int:");
		lblEjemploInt.setBounds(203, 114, 80, 14);
		add(lblEjemploInt);
		
		JLabel lblEjemploString = new JLabel("Ejemplo String:");
		lblEjemploString.setBounds(203, 163, 80, 14);
		add(lblEjemploString);
		
		txtInt = new JTextField();
		txtInt.setBounds(293, 111, 86, 20);
		add(txtInt);
		txtInt.setColumns(10);
		
		txtString = new JTextField();
		txtString.setBounds(293, 160, 86, 20);
		add(txtString);
		txtString.setColumns(10);
		
		btnInsertar = new JButton("INSERTAR");
		btnInsertar.setBounds(248, 252, 89, 23);
		add(btnInsertar);
		
		btnModificar = new JButton("MODIFICAR");
		btnModificar.setBounds(248, 252, 89, 23);
		add(btnModificar);

	}
	
	public void setControlador(Controlador c) {
		btnInsertar.addActionListener(c);		
		btnModificar.addActionListener(c);		
	}
	
	public EjObjeto getDatos () {
		EjObjeto ejObjeto;
		if(!(txtInt.getText().isEmpty() || txtString.getText().isEmpty())) {
			ejObjeto = new EjObjeto(id,
				Integer.parseInt(txtInt.getText()+""), 
				txtString.getText());
			return ejObjeto;	
		}
		return new EjObjeto(id, -1,"");
		
			
	}
	
	public void setDatos (EjObjeto ejObjeto) {
		id = ejObjeto.getId();
		txtInt.setText(ejObjeto.getEjInt()+"");		
		txtString.setText(ejObjeto.getEjString());		
	}
	
	public void limpiar () {
		txtInt.setText("");		
		txtString.setText("");		
	}
	
	public void tipoPanel(int tipo) {
		
		switch (tipo) {
		case INSERTAR:
			lblTitulo.setText("NUEVO");
			btnInsertar.setVisible(true);
			btnModificar.setVisible(false);
			break;
		case MODIFICAR:
			lblTitulo.setText("MODIFICAR");
			btnInsertar.setVisible(false);
			btnModificar.setVisible(true);
			break;
		default:
			break;
		}
		
		
	}

}
