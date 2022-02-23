package PruebasJunit;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import controlador.Controlador;
import modelo.jugador.ColorJugador;
import modelo.piezas.Alfil;
import modelo.piezas.Caballo;
import modelo.piezas.Peon;
import modelo.piezas.Reina;
import modelo.piezas.Rey;
import modelo.piezas.Torre;
import modelo.tablero.Casilla;
import modelo.tablero.Tablero;

class PruebasComer {

	Tablero t = new Tablero();
	Controlador c = new Controlador(t);
	
	@Test
	void torreCome() {
		for(int i = 0; i < 8; i++) {
			for(int j = 0; j < 8; j++) {
				t.getTablero()[i][j].setPieza(null);
				t.getTablero()[i][j].setIsOcuppied(false);
			}
		}
		t.getTablero()[0][0].setIsOcuppied(true);
		t.getTablero()[0][0].setPieza(new Torre(ColorJugador.BLANCO));
		t.getTablero()[7][0].setIsOcuppied(true);
		t.getTablero()[7][0].setPieza(new Peon(ColorJugador.NEGRO));
		Controlador.setIsSelected(true);
		c.setPossibleMov(t.posiblesMovimientosPieza(0, 0));
		c.setIniX(0);
		c.setIniY(0);
		c.controlTurnos(7, 0); 
		assertEquals("TorreBlanca", t.getNombrePieza(7, 0));
	}
	
	@Test
	void alfilCome() {
		for(int i = 0; i < 8; i++) {
			for(int j = 0; j < 8; j++) {
				t.getTablero()[i][j].setPieza(null);
				t.getTablero()[i][j].setIsOcuppied(false);
			}
		}
		t.getTablero()[0][2].setIsOcuppied(true);
		t.getTablero()[0][2].setPieza(new Alfil(ColorJugador.BLANCO));
		t.getTablero()[5][7].setIsOcuppied(true);
		t.getTablero()[5][7].setPieza(new Peon(ColorJugador.NEGRO));
		Controlador.setIsSelected(true);
		c.setPossibleMov(t.posiblesMovimientosPieza(0, 2));
		c.setIniX(0);
		c.setIniY(2);
		c.controlTurnos(5, 7); 
		assertEquals("AlfilBlanco", t.getNombrePieza(5, 7));
	}
	
	@Test
	void caballoCome() {
		for(int i = 0; i < 8; i++) {
			for(int j = 0; j < 8; j++) {
				t.getTablero()[i][j].setPieza(null);
				t.getTablero()[i][j].setIsOcuppied(false);
			}
		}
		t.getTablero()[0][1].setIsOcuppied(true);
		t.getTablero()[0][1].setPieza(new Caballo(ColorJugador.BLANCO));
		t.getTablero()[2][2].setIsOcuppied(true);
		t.getTablero()[2][2].setPieza(new Peon(ColorJugador.NEGRO));
		Controlador.setIsSelected(true);
		c.setPossibleMov(t.posiblesMovimientosPieza(0, 1));
		c.setIniX(0);
		c.setIniY(1);
		c.controlTurnos(2, 2); 
		assertEquals("CaballoBlanco", t.getNombrePieza(2, 2));
	}

	@Test
	void reinaCome() {
		for(int i = 0; i < 8; i++) {
			for(int j = 0; j < 8; j++) {
				t.getTablero()[i][j].setPieza(null);
				t.getTablero()[i][j].setIsOcuppied(false);
			}
		}
		t.getTablero()[0][3].setIsOcuppied(true);
		t.getTablero()[0][3].setPieza(new Reina(ColorJugador.BLANCO));
		t.getTablero()[4][7].setIsOcuppied(true);
		t.getTablero()[4][7].setPieza(new Peon(ColorJugador.NEGRO));
		Controlador.setIsSelected(true);
		c.setPossibleMov(t.posiblesMovimientosPieza(0, 3));
		c.setIniX(0);
		c.setIniY(3);
		c.controlTurnos(4, 7); 
		assertEquals("ReinaBlanca", t.getNombrePieza(4, 7));
	}
	
	@Test
	void reyCome() {
		for(int i = 0; i < 8; i++) {
			for(int j = 0; j < 8; j++) {
				t.getTablero()[i][j].setPieza(null);
				t.getTablero()[i][j].setIsOcuppied(false);
			}
		}
		t.getTablero()[0][4].setIsOcuppied(true);
		t.getTablero()[0][4].setPieza(new Rey(ColorJugador.BLANCO));
		t.getTablero()[1][3].setIsOcuppied(true);
		t.getTablero()[1][3].setPieza(new Peon(ColorJugador.NEGRO));
		Controlador.setIsSelected(true);
		c.setPossibleMov(t.posiblesMovimientosPieza(0, 4));
		c.setIniX(0);
		c.setIniY(4);
		c.controlTurnos(1, 3); 
		assertEquals("ReyBlanco", t.getNombrePieza(1, 3));
	}

	@Test
	void PeonCome() {
		for(int i = 0; i < 8; i++) {
			for(int j = 0; j < 8; j++) {
				t.getTablero()[i][j].setPieza(null);
				t.getTablero()[i][j].setIsOcuppied(false);
			}
		}
		t.getTablero()[1][4].setIsOcuppied(true);
		t.getTablero()[1][4].setPieza(new Peon(ColorJugador.BLANCO));
		t.getTablero()[2][3].setIsOcuppied(true);
		t.getTablero()[2][3].setPieza(new Peon(ColorJugador.NEGRO));
		Controlador.setIsSelected(true);
		c.setPossibleMov(t.posiblesMovimientosPieza(1, 4));
		c.setIniX(1);
		c.setIniY(4);
		c.controlTurnos(2, 3); 
		assertEquals("PeonBlanco", t.getNombrePieza(2, 3));
	}
}
