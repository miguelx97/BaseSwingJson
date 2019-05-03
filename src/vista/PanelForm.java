package vista;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import controlador.Controlador;
import modelo.ReturnDatos;
import modelo.EjObjeto;
import numerales.Medidas;
import util.Validadores;

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
	private JButton btnInsertarModificar;

	public JButton getBtnInsertarModificar() {
		return btnInsertarModificar;
	}

	public void setBtnInsertarModificar(JButton btnInsertarModificar) {
		this.btnInsertarModificar = btnInsertarModificar;
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
		
		btnInsertarModificar = new JButton("INSERTAR");
		btnInsertarModificar.setBounds(217, 252, 131, 23);
		add(btnInsertarModificar);

		txtString.addKeyListener(new MyKeyListener());		//poner en el ultimo jTextField que al pulsar enter se presione el boton
	}
	
	public void setControlador(Controlador c) {
		btnInsertarModificar.addActionListener(c);		
	}
	
	public ReturnDatos getDatos () {
		EjObjeto ejObjeto;
		ReturnDatos customReturn = new ReturnDatos();
		
		if(Validadores.estaVacio(txtInt.getText())) {
			customReturn.setError("Rellene txtInt");
			txtInt.requestFocus();
		} else if(Validadores.estaVacio(txtString.getText())){
			customReturn.setError("Rellene txtString");
			txtString.requestFocus();
		} else if (!Validadores.esNumero(txtInt.getText())){
			customReturn.setError("El campo txtInt debe ser numerico");
			txtInt.requestFocus();
		}
		else {
			ejObjeto = new EjObjeto(id,
					Integer.parseInt(txtInt.getText()+""), 
					txtString.getText());
			
			customReturn.setObject(ejObjeto);
			txtInt.requestFocus();
		
		}		
		 return customReturn;
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
			btnInsertarModificar.setText("INSERTAR");
			id = -2;
			break;
		case MODIFICAR:
			lblTitulo.setText("MODIFICAR");
			btnInsertarModificar.setText("MODIFICAR");
			break;
		default:
			break;
		}
		
		
	}
	
	
	public class MyKeyListener extends KeyAdapter {
        public void keyPressed(KeyEvent evt) {
            if (evt.getKeyChar() == KeyEvent.VK_ENTER) {
                btnInsertarModificar.doClick();
            }
        }
    }

}
