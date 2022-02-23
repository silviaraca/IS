package PruebasJunit;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import controlador.Controlador;
import modelo.jugador.ColorJugador;
import modelo.piezas.Alfil;
import modelo.piezas.Peon;
import modelo.piezas.Reina;
import modelo.piezas.Rey;
import modelo.piezas.Torre;
import modelo.tablero.Tablero;

class PruebaMovimientosBasicos {
	Tablero t = new Tablero();
	Controlador c = new Controlador(t);
	
	
	@Test
	void movimientoPeon() {
		for(int i = 0; i < 8; i++) {
			for(int j = 0; j < 8; j++) {
				t.getTablero()[i][j].setPieza(null);
				t.getTablero()[i][j].setIsOcuppied(false);
			}
		}
		t.getTablero()[0][4].setIsOcuppied(true);
		t.getTablero()[0][4].setPieza(new Peon(ColorJugador.BLANCO));
		Controlador.setIsSelected(true);
		c.setPossibleMov(t.posiblesMovimientosPieza(0, 4));
		c.setIniX(0);
		c.setIniY(4);
		c.controlTurnos(1, 4);
		assertEquals("PeonBlanco", t.getNombrePieza(1, 4));
	}
	
	@Test
	void movimientoTorre() {
		for(int i = 0; i < 8; i++) {
			for(int j = 0; j < 8; j++) {
				t.getTablero()[i][j].setPieza(null);
				t.getTablero()[i][j].setIsOcuppied(false);
			}
		}
		t.getTablero()[0][4].setIsOcuppied(true);
		t.getTablero()[0][4].setPieza(new Torre(ColorJugador.BLANCO));
		Controlador.setIsSelected(true);
		c.setPossibleMov(t.posiblesMovimientosPieza(0, 4));
		c.setIniX(0);
		c.setIniY(4);
		c.controlTurnos(7, 4);
		assertEquals("TorreBlanca", t.getNombrePieza(7, 4));
	}

	@Test
	void movimientoAlfil() {
		for(int i = 0; i < 8; i++) {
			for(int j = 0; j < 8; j++) {
				t.getTablero()[i][j].setPieza(null);
				t.getTablero()[i][j].setIsOcuppied(false);
			}
		}
		t.getTablero()[0][4].setIsOcuppied(true);
		t.getTablero()[0][4].setPieza(new Alfil(ColorJugador.BLANCO));
		Controlador.setIsSelected(true);
		c.setPossibleMov(t.posiblesMovimientosPieza(0, 4));
		c.setIniX(0);
		c.setIniY(4);
		c.controlTurnos(4, 0);
		assertEquals("AlfilBlanco", t.getNombrePieza(4, 0));
	}
	
	@Test
	void movimientoReina() {
		for(int i = 0; i < 8; i++) {
			for(int j = 0; j < 8; j++) {
				t.getTablero()[i][j].setPieza(null);
				t.getTablero()[i][j].setIsOcuppied(false);
			}
		}
		t.getTablero()[0][4].setIsOcuppied(true);
		t.getTablero()[0][4].setPieza(new Reina(ColorJugador.BLANCO));
		Controlador.setIsSelected(true);
		c.setPossibleMov(t.posiblesMovimientosPieza(0, 4));
		c.setIniX(0);
		c.setIniY(4);
		c.controlTurnos(7, 4);
		assertEquals("ReinaBlanca", t.getNombrePieza(7, 4));
	}
	
	
	
	@Test
	void movimientoRey() {
		for(int i = 0; i < 8; i++) {
			for(int j = 0; j < 8; j++) {
				t.getTablero()[i][j].setPieza(null);
				t.getTablero()[i][j].setIsOcuppied(false);
			}
		}
		t.getTablero()[0][4].setIsOcuppied(true);
		t.getTablero()[0][4].setPieza(new Rey(ColorJugador.BLANCO));
		Controlador.setIsSelected(true);
		c.setPossibleMov(t.posiblesMovimientosPieza(0, 4));
		c.setIniX(0);
		c.setIniY(4);
		c.controlTurnos(1, 3);
		assertEquals("ReyBlanco", t.getNombrePieza(1, 3));
	}
}
