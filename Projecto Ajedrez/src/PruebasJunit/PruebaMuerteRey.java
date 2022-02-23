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
import vista.HistorialVista;

class PruebaMuerteRey {

	Tablero t = new Tablero();
	Controlador c = new Controlador(t);
	HistorialVista h = new HistorialVista(c);
	
	@Test
	void MuerteReyBlanco() {
		boolean aux = false;
		for(int i = 0; i < 8; i++) {
			for(int j = 0; j < 8; j++) {
				t.getTablero()[i][j].setPieza(null);
				t.getTablero()[i][j].setIsOcuppied(false);
			}
		}
		t.getTablero()[3][4].setIsOcuppied(true);
		t.getTablero()[3][4].setPieza(new Peon(ColorJugador.BLANCO));
		t.getTablero()[4][5].setIsOcuppied(true);
		t.getTablero()[4][5].setPieza(new Rey(ColorJugador.NEGRO));
		Controlador.setIsSelected(true);
		c.setPossibleMov(t.posiblesMovimientosPieza(3, 4));
		c.setIniX(3);
		c.setIniY(4);
		c.controlTurnos(4, 5);
		assertEquals("PeonBlanco", t.getNombrePieza(4, 5));
		if(h.getHistorial().contains("ha ganado el jugador")) aux = true;
		assertEquals(true, aux);
	}
	
	@Test
	void MuerteReyNegro() {
		boolean aux = false;
		for(int i = 0; i < 8; i++) {
			for(int j = 0; j < 8; j++) {
				t.getTablero()[i][j].setPieza(null);
				t.getTablero()[i][j].setIsOcuppied(false);
			}
		}
		t.getTablero()[3][4].setIsOcuppied(true);
		t.getTablero()[3][4].setPieza(new Rey(ColorJugador.BLANCO));
		t.getTablero()[1][3].setIsOcuppied(true);
		t.getTablero()[1][3].setPieza(new Reina(ColorJugador.NEGRO));
		Controlador.setIsSelected(true);
		c.setPossibleMov(t.posiblesMovimientosPieza(3, 4));
		c.setIniX(3);
		c.setIniY(4);
		c.controlTurnos(3, 5);
		
		
		Controlador.setIsSelected(true);
		c.setPossibleMov(t.posiblesMovimientosPieza(1, 3));
		c.setIniX(1);
		c.setIniY(3);
		c.controlTurnos(3, 5);
		assertEquals("ReinaNegra", t.getNombrePieza(3, 5));
		if(h.getHistorial().contains("ha ganado el jugador")) aux = true;
		assertEquals(true, aux);
	}

}
