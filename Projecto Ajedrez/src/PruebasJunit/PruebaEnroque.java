package PruebasJunit;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import controlador.Controlador;
import modelo.jugador.ColorJugador;
import modelo.piezas.Reina;
import modelo.piezas.Rey;
import modelo.piezas.Torre;
import modelo.tablero.Casilla;
import modelo.tablero.Tablero;
import vista.ModoJuegoDialog;
import vista.VentanaPrincipal;

class PruebaEnroque {

	@Test
	void enroqueLargo() {
		Tablero t = new Tablero();
		Controlador c = new Controlador(t);
		for(int i = 0; i < 8; i++) {
			for(int j = 0; j < 8; j++) {
				t.getTablero()[i][j].setPieza(null);
				t.getTablero()[i][j].setIsOcuppied(false);
			}
		}
		t.getTablero()[0][0].setIsOcuppied(true);
		t.getTablero()[0][0].setPieza(new Torre(ColorJugador.BLANCO));
		t.getTablero()[0][4].setIsOcuppied(true);
		t.getTablero()[0][4].setPieza(new Rey(ColorJugador.BLANCO));
		Controlador.setIsSelected(true);
		c.setPossibleMov(t.posiblesMovimientosPieza(0, 4));
		c.setIniX(0);
		c.setIniY(4);
		c.controlTurnos(0, 2);
		assertEquals("ReyBlanco", t.getNombrePieza(0, 2));
		assertEquals("TorreBlanca", t.getNombrePieza(0, 3));
	}
	
	
	@Test
	void enroqueCorto() {
		Tablero t = new Tablero();
		Controlador c = new Controlador(t);
		for(int i = 0; i < 8; i++) {
			for(int j = 0; j < 8; j++) {
				t.getTablero()[i][j].setPieza(null);
				t.getTablero()[i][j].setIsOcuppied(false);
			}
		}
		t.getTablero()[0][7].setIsOcuppied(true);
		t.getTablero()[0][7].setPieza(new Torre(ColorJugador.BLANCO));
		t.getTablero()[0][4].setIsOcuppied(true);
		t.getTablero()[0][4].setPieza(new Rey(ColorJugador.BLANCO));
		Controlador.setIsSelected(true);
		c.setPossibleMov(t.posiblesMovimientosPieza(0, 4));
		c.setIniX(0);
		c.setIniY(4);
		c.controlTurnos(0, 6);
		assertEquals("ReyBlanco", t.getNombrePieza(0, 6));
		assertEquals("TorreBlanca", t.getNombrePieza(0, 5));
		
	}

}
