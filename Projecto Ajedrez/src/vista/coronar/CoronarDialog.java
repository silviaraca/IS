package vista.coronar;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;

import controlador.Controlador;
import modelo.jugador.ColorJugador;
import modelo.piezas.Alfil;
import modelo.piezas.Caballo;
import modelo.piezas.Reina;
import modelo.piezas.Torre;

public class CoronarDialog extends JDialog {

	private ColorJugador color;
	
	public CoronarDialog(ColorJugador color, Controlador c, int posX, int posY){
		this.setVisible(true);
		this.color = color;
		this.setLocation(100, 100);
		this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		initGui(c, posX, posY);
		pack();
	}
	
	private void initGui(Controlador c, int posX, int posY) {
		JPanel mp = new JPanel();
		this.setContentPane(mp);
		mp.setLayout(new BorderLayout());
		
		JPanel piezasBotones = new JPanel();
		piezasBotones.setLayout(new GridLayout(2, 2));
			piezasBotones.add(new BotonSeleccionPiezaCoronacion(new Alfil(color), c, posX, posY, this));
			piezasBotones.add(new BotonSeleccionPiezaCoronacion(new Caballo(color), c, posX, posY, this));
			piezasBotones.add(new BotonSeleccionPiezaCoronacion(new Reina(color), c, posX, posY, this));
			piezasBotones.add(new BotonSeleccionPiezaCoronacion(new Torre(color), c, posX, posY, this));
		
		JLabel mensaje = new JLabel("Selecciona una pieza para coronar");
		mp.add(mensaje, BorderLayout.PAGE_START);
		mp.add(piezasBotones, BorderLayout.CENTER);
	}
}
