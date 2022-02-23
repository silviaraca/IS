package PruebasJunit;

import static org.junit.jupiter.api.Assertions.*;


import org.junit.jupiter.api.Test;

import modelo.jugador.ColorJugador;
import modelo.piezas.Alfil;
import modelo.piezas.Caballo;
import modelo.piezas.Peon;
import modelo.piezas.Reina;
import modelo.piezas.Rey;
import modelo.piezas.Torre;
import modelo.tablero.Tablero;

class PruebaMovimientosPosibles {

	Tablero t = new Tablero();
	@Test
	void movPosiblesReina() {
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
		t.getTablero()[1][4].setPieza(new Peon(ColorJugador.BLANCO));
		t.getTablero()[3][4].setIsOcuppied(true);
		t.getTablero()[3][4].setPieza(new Torre(ColorJugador.BLANCO));
		t.getTablero()[1][3].setIsOcuppied(true);
		t.getTablero()[1][3].setPieza(new Peon(ColorJugador.BLANCO));
		t.getTablero()[1][5].setIsOcuppied(true);
		t.getTablero()[1][5].setPieza(new Alfil(ColorJugador.BLANCO));
		t.getTablero()[4][6].setIsOcuppied(true);
		t.getTablero()[4][6].setPieza(new Rey(ColorJugador.BLANCO));
		t.getTablero()[4][2].setIsOcuppied(true);
		t.getTablero()[4][2].setPieza(new Alfil(ColorJugador.BLANCO));
		
		assertEquals(5, t.posiblesMovimientosPieza(2, 4).size()); // Con esta disposicion del tablero, la reina tendria que tener 5 movimientos posibles
	}
	
	
	@Test
	void movPosiblesAlfil() {
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
		t.getTablero()[1][4].setPieza(new Peon(ColorJugador.BLANCO));
		t.getTablero()[3][4].setIsOcuppied(true);
		t.getTablero()[3][4].setPieza(new Torre(ColorJugador.BLANCO));
		t.getTablero()[1][3].setIsOcuppied(true);
		t.getTablero()[1][3].setPieza(new Peon(ColorJugador.BLANCO));
		t.getTablero()[1][5].setIsOcuppied(true);
		t.getTablero()[1][5].setPieza(new Alfil(ColorJugador.BLANCO));
		t.getTablero()[4][6].setIsOcuppied(true);
		t.getTablero()[4][6].setPieza(new Rey(ColorJugador.BLANCO));
		t.getTablero()[4][2].setIsOcuppied(true);
		t.getTablero()[4][2].setPieza(new Alfil(ColorJugador.BLANCO));
		
		assertEquals(7, t.posiblesMovimientosPieza(4, 2).size()); // Con esta disposicion del tablero, el alfil tendria que tener 4 movimientos posibles
	}
	

	@Test
	void movPosiblesCaballo() {
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
		t.getTablero()[1][4].setPieza(new Peon(ColorJugador.BLANCO));
		t.getTablero()[3][4].setIsOcuppied(true);
		t.getTablero()[3][4].setPieza(new Torre(ColorJugador.BLANCO));
		t.getTablero()[1][3].setIsOcuppied(true);
		t.getTablero()[1][3].setPieza(new Peon(ColorJugador.BLANCO));
		t.getTablero()[1][5].setIsOcuppied(true);
		t.getTablero()[1][5].setPieza(new Alfil(ColorJugador.BLANCO));
		t.getTablero()[4][6].setIsOcuppied(true);
		t.getTablero()[4][6].setPieza(new Rey(ColorJugador.BLANCO));
		t.getTablero()[4][2].setIsOcuppied(true);
		t.getTablero()[4][2].setPieza(new Alfil(ColorJugador.BLANCO));
		
		assertEquals(6, t.posiblesMovimientosPieza(2, 3).size()); // Con esta disposicion del tablero, el caballo tendria que tener 6 movimientos posibles
	}

	@Test
	void movPosiblesPeon() {
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
		t.getTablero()[1][4].setPieza(new Peon(ColorJugador.BLANCO));
		t.getTablero()[3][4].setIsOcuppied(true);
		t.getTablero()[3][4].setPieza(new Torre(ColorJugador.BLANCO));
		t.getTablero()[1][3].setIsOcuppied(true);
		t.getTablero()[1][3].setPieza(new Peon(ColorJugador.BLANCO));
		t.getTablero()[1][5].setIsOcuppied(true);
		t.getTablero()[1][5].setPieza(new Alfil(ColorJugador.BLANCO));
		t.getTablero()[4][6].setIsOcuppied(true);
		t.getTablero()[4][6].setPieza(new Rey(ColorJugador.BLANCO));
		t.getTablero()[4][2].setIsOcuppied(true);
		t.getTablero()[4][2].setPieza(new Alfil(ColorJugador.BLANCO));
		
		assertEquals(0, t.posiblesMovimientosPieza(1, 4).size()); // Con esta disposicion del tablero, el peon tendria que tener 0 movimientos posibles
	}
	
	@Test
	void movPosiblesRey() {
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
		t.getTablero()[1][4].setPieza(new Peon(ColorJugador.BLANCO));
		t.getTablero()[3][4].setIsOcuppied(true);
		t.getTablero()[3][4].setPieza(new Torre(ColorJugador.BLANCO));
		t.getTablero()[1][3].setIsOcuppied(true);
		t.getTablero()[1][3].setPieza(new Peon(ColorJugador.BLANCO));
		t.getTablero()[1][5].setIsOcuppied(true);
		t.getTablero()[1][5].setPieza(new Alfil(ColorJugador.BLANCO));
		t.getTablero()[4][6].setIsOcuppied(true);
		t.getTablero()[4][6].setPieza(new Rey(ColorJugador.BLANCO));
		t.getTablero()[4][2].setIsOcuppied(true);
		t.getTablero()[4][2].setPieza(new Alfil(ColorJugador.BLANCO));
		
		assertEquals(8, t.posiblesMovimientosPieza(4, 6).size()); // Con esta disposicion del tablero, el rey tendria que tener 8 movimientos posibles
	}
	@Test
	void movPosiblesTorre() {
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
		t.getTablero()[1][4].setPieza(new Peon(ColorJugador.BLANCO));
		t.getTablero()[3][4].setIsOcuppied(true);
		t.getTablero()[3][4].setPieza(new Torre(ColorJugador.BLANCO));
		t.getTablero()[1][3].setIsOcuppied(true);
		t.getTablero()[1][3].setPieza(new Peon(ColorJugador.BLANCO));
		t.getTablero()[1][5].setIsOcuppied(true);
		t.getTablero()[1][5].setPieza(new Alfil(ColorJugador.BLANCO));
		t.getTablero()[4][6].setIsOcuppied(true);
		t.getTablero()[4][6].setPieza(new Rey(ColorJugador.BLANCO));
		t.getTablero()[4][2].setIsOcuppied(true);
		t.getTablero()[4][2].setPieza(new Alfil(ColorJugador.BLANCO));
		
		assertEquals(10, t.posiblesMovimientosPieza(3, 4).size()); // Con esta disposicion del tablero, la torre tendria que tener 10 movimientos posibles
		
	
	}
	
	
	
	@Test
	void oneMovPossible() { // Por ultimo creando un tablero con y comprobando una pieza que solo tenga un movimiento posible
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
		
		
		t.getTablero()[0][3].setIsOcuppied(true);
		t.getTablero()[0][3].setPieza(new Caballo(ColorJugador.BLANCO));
		// Fuerzo a que este caballo solo tenga un movimiento posible, en este caso a 1,1
		
		
		t.getTablero()[3][6].setIsOcuppied(true);
		t.getTablero()[3][6].setPieza(new Peon(ColorJugador.BLANCO));		
		t.getTablero()[1][4].setIsOcuppied(true);
		t.getTablero()[1][4].setPieza(new Peon(ColorJugador.BLANCO));		
		t.getTablero()[3][4].setIsOcuppied(true);
		t.getTablero()[3][4].setPieza(new Torre(ColorJugador.BLANCO));
		t.getTablero()[1][3].setIsOcuppied(true);
		t.getTablero()[1][3].setPieza(new Peon(ColorJugador.BLANCO));
		t.getTablero()[1][5].setIsOcuppied(true);
		t.getTablero()[1][5].setPieza(new Alfil(ColorJugador.BLANCO));
		t.getTablero()[4][6].setIsOcuppied(true);
		t.getTablero()[4][6].setPieza(new Rey(ColorJugador.BLANCO));
		t.getTablero()[2][2].setIsOcuppied(true);
		t.getTablero()[2][2].setPieza(new Peon(ColorJugador.BLANCO));
		t.getTablero()[4][2].setIsOcuppied(true);
		t.getTablero()[4][2].setPieza(new Alfil(ColorJugador.BLANCO));
		
		assertEquals(1, t.posiblesMovimientosPieza(0, 3).size()); // Primero compruebo que solo haya un movimineto posible
		assertEquals(1, t.posiblesMovimientosPieza(0, 3).get(0).getX()); // Despues compruebo que estan bien las cordenadas del movimiento posible
		assertEquals(1, t.posiblesMovimientosPieza(0, 3).get(0).getY());
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
