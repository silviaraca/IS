package PruebasJunit;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import controlador.Controlador;
import controlador.Controlador.turnoActual;
import modelo.jugador.ColorJugador;
import modelo.tablero.Tablero;

class PruebaIA {
	
	Tablero t = new Tablero();
	Controlador c = new Controlador(t);
	@Test
	void testIA() {
		c.addPlayerAndIA("Ejemplo", ColorJugador.NEGRO, ColorJugador.BLANCO, "aleatorio");
		c.controlTurnos(0, 0);
		assertEquals(turnoActual.J1, c.getTurno());
	}
	

}
