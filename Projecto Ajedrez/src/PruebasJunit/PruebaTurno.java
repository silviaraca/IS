package PruebasJunit;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import controlador.Controlador;
import controlador.Controlador.turnoActual;
import modelo.tablero.Casilla;
import modelo.tablero.Tablero;

class PruebaTurno {

	Tablero t = new Tablero();
	Controlador c = new Controlador(t);
	
	@Test
	void comprobarTurno() {
		c.setPossibleMov(t.posiblesMovimientosPieza(0, 1));
		c.setIniX(0);
		c.setIniY(1);
		Controlador.setIsSelected(true);
		c.controlTurnos(2, 0);
		assertEquals(turnoActual.J2, c.getTurno());
	}
	
	
}
