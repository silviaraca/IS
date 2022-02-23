package vista;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.Observable;

import javax.swing.JLabel;
import javax.swing.JPanel;

import controlador.Controlador;
import modelo.interfaces.Acciones;
import modelo.tablero.Casilla;

public class VentanaTablero extends JPanel{
	
	public static final int TAM_CASILLA = 80;
	private Acciones[][] botones;
	
	public VentanaTablero(Controlador c, GridLayout gl) {
		this.setLayout(gl);
		this.setPreferredSize(new Dimension(TAM_CASILLA*8, TAM_CASILLA*8));
		this.setVisible(true);
		this.setMaximumSize(new Dimension(600, 750));
		botones = new Acciones[8][8];
		initGUI(c);
	}
	
	private void initGUI(Controlador c) {
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				JLabel casilla;
				if((i - j) % 2 == 0) {
					casilla = createLabel(Color.BLACK, c, i, j);
				}
				else {
					casilla = createLabel(Color.WHITE, c, i, j);
				}
				this.add(casilla);
			}
		}
		c.addObserver(botones);
	}
	
	private JLabel createLabel(Color color, Controlador c, int x, int y) {
		JLabel casilla = new JLabel();
		casilla.setBounds(0, 0, TAM_CASILLA, TAM_CASILLA);
		casilla.setBackground(color);
		casilla.setOpaque(true);
		
		CasillaVista boton = new CasillaVista(c, x, y);
		botones[x][y] = boton;
		
		casilla.add(boton);
		return casilla;
	}
}
