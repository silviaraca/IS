package PruebasJunit;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import controlador.Controlador;
import modelo.jugador.ColorJugador;
import modelo.piezas.Alfil;
import modelo.piezas.Torre;
import modelo.tablero.Tablero;
import vista.HistorialVista;

class PruebaHistorial {

	Tablero t = new Tablero();
	Controlador c = new Controlador(t);
	HistorialVista h = new HistorialVista(c);
	@Test
	void historialJugadas() {
		for(int i = 0; i < 8; i++) {
			for(int j = 0; j < 8; j++) {
				t.getTablero()[i][j].setPieza(null);
				t.getTablero()[i][j].setIsOcuppied(false);
			}
		}
		t.getTablero()[0][4].setIsOcuppied(true);
		t.getTablero()[0][4].setPieza(new Alfil(ColorJugador.BLANCO));
		t.getTablero()[7][3].setIsOcuppied(true);
		t.getTablero()[7][3].setPieza(new Torre(ColorJugador.NEGRO));
		Controlador.setIsSelected(true);
		c.setPossibleMov(t.posiblesMovimientosPieza(0, 4));
		c.setIniX(0);
		c.setIniY(4);
		c.controlTurnos(4, 0); // Mueve primero un alfil blanco
		assertEquals("\n"+ t.getNombrePieza(4, 0) +"[1][5] --> [5][1]", h.getHistorial()); // Comprueba si se ha actualizado el historial
		Controlador.setIsSelected(true);
		c.setPossibleMov(t.posiblesMovimientosPieza(7, 3));
		c.setIniX(7);
		c.setIniY(3);
		c.controlTurnos(1, 3); // Luego mueve una torre negra
		assertEquals("\n"+t.getNombrePieza(4, 0)+"[1][5] --> [5][1]\n"+t.getNombrePieza(1, 3)+"[8][4] --> [2][4]", h.getHistorial());// Vuelve a comprobar si se ha actualizado el historial
	}

}
