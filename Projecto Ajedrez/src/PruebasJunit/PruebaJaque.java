package PruebasJunit;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import controlador.Controlador;
import controlador.Controlador.turnoActual;
import modelo.jugador.ColorJugador;
import modelo.piezas.Peon;
import modelo.piezas.Reina;
import modelo.tablero.Casilla;
import modelo.tablero.Tablero;

class PruebaJaque {

	Tablero t = new Tablero();
	Controlador c = new Controlador(t);
	
	@Test
	void test() {
		boolean jaque = false;
		int i = 0;
		/*t.getTablero()[0][3].setPieza(null);
		t.getTablero()[0][3].setIsOcuppied(false);
		t.getTablero()[3][0].setIsOcuppied(true);
		t.getTablero()[3][0].setPieza(new Reina(ColorJugador.BLANCO));*/
		
		t.getTablero()[1][2].setPieza(null);
		t.getTablero()[1][2].setIsOcuppied(false);
		t.getTablero()[3][2].setIsOcuppied(true);
		t.getTablero()[3][2].setPieza(new Peon(ColorJugador.BLANCO));
		
		t.getTablero()[6][3].setPieza(null);
		t.getTablero()[6][3].setIsOcuppied(false);
		t.getTablero()[4][3].setIsOcuppied(true);
		t.getTablero()[4][3].setPieza(new Reina(ColorJugador.BLANCO));
		
		Controlador.setIsSelected(true);
		c.setPossibleMov(t.posiblesMovimientosPieza(0, 3));
		c.setIniX(0); 
		c.setIniY(3);
		c.controlTurnos(3, 0);
		ArrayList<Casilla> aux = t.listaAmenazadas(turnoActual.J1);
		while(i < aux.size() && !jaque) {
			if(aux.get(i).getIsOcuppied()) {
				if (aux.get(i).getPieza().esRey()) {
					jaque = true;
				}
			}
			i++;
		}
		

		assertEquals(true, jaque);
	}

}
