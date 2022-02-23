package vista.coronar;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.ImageIcon;
import javax.swing.JButton;

import controlador.Controlador;
import modelo.interfaces.Acciones;
import modelo.interfaces.MovimientoPiezas;

public class BotonSeleccionPiezaCoronacion extends JButton implements Acciones{
	
	public BotonSeleccionPiezaCoronacion(MovimientoPiezas p, Controlador c, int posX, int posY, CoronarDialog cj) {
		updateImage(p.getNombrePieza());
		addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				c.coronar(p, posX, posY);
				cj.dispose();
			}
		});
	}

	@Override
	public void updateImage(String s) {
		setIcon(new ImageIcon("src"+ File.separatorChar + "imagenes" + File.separatorChar + s + "B.jpg"));
	}

	@Override
	public void setEnableDisable(boolean b) {}

	@Override
	public void updateTurno(Controlador.turnoActual s) {}

	@Override
	public void updateHistorial(String s) {}

	@Override
	public void updateError(String s) {}

	@Override
	public void reset() {}

	@Override
	public void updateBanquillo(String s) {}
}
