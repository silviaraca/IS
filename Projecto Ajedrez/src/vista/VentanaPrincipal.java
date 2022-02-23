package vista;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import controlador.Controlador;
import modelo.jugador.ColorJugador;

public class VentanaPrincipal extends JFrame {

	public VentanaPrincipal(Controlador ctrl) {
		super("Ajedrez IS");
		JPanel mainPanel = new JPanel();
		this.setContentPane(mainPanel);
		mainPanel.setLayout(new BorderLayout());
		mainPanel.add(new ControlVista(this, ctrl), BorderLayout.PAGE_START);
		mainPanel.add(new VentanaTablero(ctrl, new GridLayout(8, 8)), BorderLayout.CENTER);
		mainPanel.add(new HistorialVista(ctrl), BorderLayout.EAST);
		this.setResizable(false);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.pack();
		this.setVisible(true);
	}
}
