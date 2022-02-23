package PruebasJunit;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import controlador.Controlador;
import controlador.Controlador.turnoActual;
import modelo.tablero.Tablero;

class PruebaSaltoPeones {
	
	
	Tablero t = new Tablero();
	Controlador c = new Controlador(t);

	@Test
	void comprobarSaltoPeon() {
		for(int i = 0; i < 8; i++) {
			c.setPossibleMov(t.posiblesMovimientosPieza(1, i));
			c.setIniX(1);
			c.setIniY(i);
			Controlador.setIsSelected(true);
			c.controlTurnos(3, i);
			assertEquals("PeonBlanco", t.getNombrePieza(3, i));
			c.setTurno(turnoActual.J2);
			if(c.getTurno() == turnoActual.J2) c.setTurno(turnoActual.J1);
		}
		
		
	}

}
