package util;

import javax.swing.JLabel;

public class TiempoMensaje implements Runnable{
	private JLabel mensaje;
	
	public TiempoMensaje(JLabel mensaje) {
		this.mensaje = mensaje;
	}

	

	@Override
	public void run() {
		int seg = 3;
		mensaje.setVisible(true);
		try {
			Thread.sleep(seg * 1000);
		} catch (InterruptedException e1) {

			e1.printStackTrace();
		} finally {
			mensaje.setVisible(false);
		}
	}
	
	
}
