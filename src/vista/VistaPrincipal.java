package vista;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.UIManager;

import controlador.Controlador;
import javax.swing.SwingConstants;
import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

public class VistaPrincipal extends JFrame implements IVentanaAgenda{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public static final int INFO_EXITO = 1;
	public static final int INFO_ERROR = 2;
	public static final int INFO = 3;
	
	private JScrollPane superPanel;
	private JMenuItem mntmAdd;
	private JMenuItem mntmShow;
	private JLabel lblMensaje;


	public JMenuItem getMntmAdd() {
		return mntmAdd;
	}
	public JMenuItem getMntmShow() {
		return mntmShow;
	}

	public VistaPrincipal() {
		super("NOMBRE");
		crearMenu();
		inicializar();
	}
	
private void crearMenu() {
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		mntmAdd = new JMenuItem("Insertar");
		mntmAdd.setHorizontalTextPosition(SwingConstants.CENTER);
		mntmAdd.setHorizontalAlignment(SwingConstants.CENTER);
		menuBar.add(mntmAdd);
		
		mntmShow = new JMenuItem("Mostrar");
		mntmShow.setHorizontalTextPosition(SwingConstants.CENTER);
		mntmShow.setHorizontalAlignment(SwingConstants.CENTER);
		menuBar.add(mntmShow);

	}

	@Override
	public void inicializar() {
		try {
			UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName()); 
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		superPanel = new JScrollPane();
		superPanel.setBounds(0, 0, 590, 371);
		getContentPane().setLayout(null);
		
		lblMensaje = new JLabel("XXXXX");
		lblMensaje.setHorizontalAlignment(SwingConstants.CENTER);
		lblMensaje.setBounds(0, 345, 590, 69);
		getContentPane().add(lblMensaje);
		
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setPreferredSize(new Dimension(600, 450));
		setLocationRelativeTo(null);
		
		getContentPane().add(superPanel);
	}
	
	public void setMensaje(int estado, String mensaje) {	
		lblMensaje.setText(mensaje);
		lblMensaje.setVisible(true);
		switch (estado) {
		case INFO_EXITO:
			lblMensaje.setForeground (Color.green);
			break;
		case INFO_ERROR:
			lblMensaje.setForeground (Color.red);
			break;
		case INFO:
			lblMensaje.setForeground (Color.blue);
			break;
		default:
			break;
		}
		
		
	}

	@Override
	public void hacerVisible() {
		// Colocar automaticamente los componente en la ventana
		this.pack();
		// hace visible la ventana
		this.setVisible(true);
	}
	
	@Override
	public void setControlador(Controlador c) {
		mntmAdd.addActionListener(c);
		mntmShow.addActionListener(c);
		
	}

	@Override
	public void definirPanel(JPanel panel) {
		superPanel.setViewportView(panel);
	}
	
}
