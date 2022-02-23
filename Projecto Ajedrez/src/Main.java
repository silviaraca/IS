import javax.swing.SwingUtilities;

import controlador.Controlador;
import modelo.tablero.Tablero;
import vista.ModoJuegoDialog;
import vista.VentanaPrincipal;

public class Main {

	public static void main(String[] args) {
		Tablero t = new Tablero();
		Controlador ctrl = new Controlador(t);
		initVista(ctrl);
	}
	
	private static void initVista(Controlador ctrl) {
		SwingUtilities.invokeLater(new Runnable() {			
			@Override
			public void run() {
				VentanaPrincipal vp = new VentanaPrincipal(ctrl);
				new ModoJuegoDialog(vp, ctrl);			
			}
		});
	}
}
