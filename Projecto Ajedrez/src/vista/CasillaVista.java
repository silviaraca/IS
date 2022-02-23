package vista;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.ImageIcon;
import javax.swing.JButton;

import controlador.Controlador;
import modelo.interfaces.Acciones;

public class CasillaVista extends JButton implements Acciones{
	
	private final int posX;
	private final int posY;
	
	public CasillaVista(Controlador c, int x, int y) {
		posX = x;
		posY = y;
		setBounds(5, 5, VentanaTablero.TAM_CASILLA - 10, VentanaTablero.TAM_CASILLA - 10);
		//c.addObserver(this, posX, posY);
		
		addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				/*
				if(c.getIsSelected()) c.moverPieza(posX, posY);
				else c.seleccionarPieza(posX, posY);
				*/
				c.controlTurnos(posX, posY);
			}
		});
	}

	@Override
	public void updateImage(String s) {
		setIcon(new ImageIcon("src"+ File.separatorChar + "imagenes" + File.separatorChar + s));
	}

	@Override
	public void setEnableDisable(boolean b) {
		setEnabled(b);
	}

	@Override
	public void updateTurno(Controlador.turnoActual s) {}

	@Override
	public void updateHistorial(String s) {}

	@Override
	public void updateError(String s) {}

	@Override
	public void reset() {
	}

	@Override
	public void updateBanquillo(String s) {
		// TODO Auto-generated method stub
		
	}
}
