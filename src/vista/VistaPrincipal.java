package vista;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
import javax.swing.UIManager;


import controlador.Controlador;
import main.Main;
import util.Log;
import util.TiempoMensaje;
import java.awt.Font;

public class VistaPrincipal extends JFrame implements IVentana{
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
	Log log = new Log();
	

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
		superPanel.setBounds(0, 0, Main.WIDTH_VISTA_PRINCIPAL, Main.HEIGHT_VISTA_PRINCIPAL);
		getContentPane().setLayout(null);
		
		lblMensaje = new JLabel("");
		lblMensaje.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblMensaje.setHorizontalAlignment(SwingConstants.CENTER);
		lblMensaje.setBounds(0, Main.HEIGHT_VISTA_PRINCIPAL-5, Main.WIDTH_VISTA_PRINCIPAL, 35);
		getContentPane().add(lblMensaje);
		
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setPreferredSize(new Dimension(Main.WIDTH_VISTA_PRINCIPAL, Main.HEIGHT_VISTA_PRINCIPAL+80));
		setLocationRelativeTo(null);
		
		setResizable(false);
		
		getContentPane().add(superPanel);
	}
	
	public void setMensaje(int estado, String mensaje) {	
		//log.imprimir("VistaPrincipal", "setMensaje", mensaje, "Mostrando mensaje");
		lblMensaje.setText(mensaje);
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
		
		
		Runnable runnable = new TiempoMensaje(lblMensaje);
		
		Thread thread = new Thread(runnable);
		
		thread.start();
		
	}
	
	public void esconderMensaje() {
		lblMensaje.setVisible(false);
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




