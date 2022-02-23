package PruebasJunit;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import controlador.Controlador;
import controlador.Controlador.turnoActual;
import modelo.interfaces.Acciones;
import modelo.jugador.ColorJugador;
import modelo.piezas.Alfil;
import modelo.piezas.Caballo;
import modelo.piezas.Peon;
import modelo.piezas.Reina;
import modelo.piezas.Rey;
import modelo.piezas.Torre;
import modelo.tablero.Casilla;
import modelo.tablero.Tablero;

class PruebaCasillasAmenazadas {
	Tablero t = new Tablero();
	Controlador c = new Controlador(t);
	private ArrayList<Casilla> amenaza;
	@Test
	void casillasAmenazadas() {
	
		for(int i = 0; i < 2; i++) {
			for(int j = 0; j < 8; j++) {
				t.getTablero()[i][j].setPieza(null);
				t.getTablero()[i][j].setIsOcuppied(false);
			}
		}
		
		
		t.getTablero()[2][4].setIsOcuppied(true);
		t.getTablero()[2][4].setPieza(new Reina(ColorJugador.BLANCO));
		t.getTablero()[2][3].setIsOcuppied(true);
		t.getTablero()[2][3].setPieza(new Caballo(ColorJugador.BLANCO));
		t.getTablero()[1][4].setIsOcuppied(true);
		t.getTablero()[1][4].setPieza(new Alfil(ColorJugador.BLANCO)); //Alfil1
		t.getTablero()[1][3].setIsOcuppied(true);
		t.getTablero()[1][3].setPieza(new Torre(ColorJugador.BLANCO)); //Torre1
		t.getTablero()[3][4].setIsOcuppied(true);
		t.getTablero()[3][4].setPieza(new Torre(ColorJugador.BLANCO)); //Torre2
		t.getTablero()[1][5].setIsOcuppied(true);
		t.getTablero()[1][5].setPieza(new Alfil(ColorJugador.BLANCO)); //Alfil2
		t.getTablero()[4][6].setIsOcuppied(true);
		t.getTablero()[4][6].setPieza(new Rey(ColorJugador.BLANCO));
		t.getTablero()[4][2].setIsOcuppied(true);
		t.getTablero()[4][2].setPieza(new Peon(ColorJugador.BLANCO));
		
		
		assertEquals(44, t.listaAmenazadas(turnoActual.J1).size()); /* Segun la disposicion del tablero, si la funcion estuviese bien realizada 
		tendria que haber 44 casillas amenazadas por parte del jugador blanco. 4 de la torre1, 5 del alfil1, 4 del alfil2, 6 del caballo, 5 de la reina
		10 de la torre2, 8 del rey y 2 del peon 
		*/
		
		
		
	}
		

}
