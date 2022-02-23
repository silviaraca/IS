package PruebasJunit;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import controlador.Controlador;
import modelo.tablero.Casilla;
import modelo.tablero.Tablero;

class PruebaTablero {

	
	Tablero t = new Tablero();
	
	
	@Test
	void tableroCreadoBien() {
		assertEquals("TorreBlanca", t.getNombrePieza(0, 0));
		assertEquals("CaballoBlanco", t.getNombrePieza(0, 1));
		assertEquals("AlfilBlanco", t.getNombrePieza(0, 2));
		assertEquals("ReinaBlanca", t.getNombrePieza(0, 3));
		assertEquals("ReyBlanco", t.getNombrePieza(0, 4));
		assertEquals("AlfilBlanco", t.getNombrePieza(0, 5));
		assertEquals("CaballoBlanco", t.getNombrePieza(0, 6));
		assertEquals("TorreBlanca", t.getNombrePieza(0, 7));
		assertEquals("PeonBlanco", t.getNombrePieza(1, 0));
		assertEquals("PeonBlanco", t.getNombrePieza(1, 1));
		assertEquals("PeonBlanco", t.getNombrePieza(1, 2));
		assertEquals("PeonBlanco", t.getNombrePieza(1, 3));
		assertEquals("PeonBlanco", t.getNombrePieza(1, 4));
		assertEquals("PeonBlanco", t.getNombrePieza(1, 5));
		assertEquals("PeonBlanco", t.getNombrePieza(1, 6));
		assertEquals("PeonBlanco", t.getNombrePieza(1, 7));
		
		assertEquals("PeonNegro", t.getNombrePieza(6, 0));
		assertEquals("PeonNegro", t.getNombrePieza(6, 1));
		assertEquals("PeonNegro", t.getNombrePieza(6, 2));
		assertEquals("PeonNegro", t.getNombrePieza(6, 3));
		assertEquals("PeonNegro", t.getNombrePieza(6, 4));
		assertEquals("PeonNegro", t.getNombrePieza(6, 5));
		assertEquals("PeonNegro", t.getNombrePieza(6, 6));
		assertEquals("PeonNegro", t.getNombrePieza(6, 7));
		assertEquals("TorreNegra", t.getNombrePieza(7, 0));
		assertEquals("CaballoNegro", t.getNombrePieza(7, 1));
		assertEquals("AlfilNegro", t.getNombrePieza(7, 2));
		assertEquals("ReinaNegra", t.getNombrePieza(7, 3));
		assertEquals("ReyNegro", t.getNombrePieza(7, 4));
		assertEquals("AlfilNegro", t.getNombrePieza(7, 5));
		assertEquals("CaballoNegro", t.getNombrePieza(7, 6));
		assertEquals("TorreNegra", t.getNombrePieza(7, 7));
	}
	
	
}
