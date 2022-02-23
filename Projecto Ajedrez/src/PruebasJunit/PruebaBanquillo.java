package PruebasJunit;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import controlador.Controlador;
import modelo.jugador.ColorJugador;
import modelo.piezas.Caballo;
import modelo.piezas.Peon;
import modelo.piezas.Reina;
import modelo.piezas.Torre;
import modelo.tablero.Tablero;
import vista.HistorialVista;

class PruebaBanquillo {

	Tablero t = new Tablero();
	Controlador c = new Controlador(t);
	HistorialVista banquillo = new HistorialVista(c);
	
	@Test
	void Banquillo() {
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
		
		t.getTablero()[7][7].setIsOcuppied(true);
		t.getTablero()[7][7].setPieza(new Torre(ColorJugador.NEGRO));
		
		t.getTablero()[4][3].setIsOcuppied(true);
		t.getTablero()[4][3].setPieza(new Reina(ColorJugador.BLANCO));
		
		t.getTablero()[6][2].setIsOcuppied(true);
		t.getTablero()[6][2].setPieza(new Caballo(ColorJugador.NEGRO));
		
		// LA TORRE BLANCA COME AL PEON NEGRO
		Controlador.setIsSelected(true);
		c.setPossibleMov(t.posiblesMovimientosPieza(0, 0));
		c.setIniX(0);
		c.setIniY(0);
		c.controlTurnos(7, 0); 
		assertEquals("PeonNegro=1 ", banquillo.getBanquilloNegro());
		
		//LA TORRE NEGRA COME A LA TORRE BLANCA
		Controlador.setIsSelected(true);
		c.setPossibleMov(t.posiblesMovimientosPieza(7, 7));
		c.setIniX(7);
		c.setIniY(7);
		c.controlTurnos(7, 0); 
		assertEquals("TorreBlanca=1 ", banquillo.getBanquilloBlanco());
		
		//LA REINA BLANCA COME A LA TORRE NEGRA
		Controlador.setIsSelected(true);
		c.setPossibleMov(t.posiblesMovimientosPieza(4, 3));
		c.setIniX(4);
		c.setIniY(3);
		c.controlTurnos(7, 0); 
		System.out.println(banquillo.getBanquilloNegro());
		assertEquals("TorreNegra=1 " +"PeonNegro=1 ", banquillo.getBanquilloNegro());
		
		//EL CABALLO NEGRO COME A LA REINA BLANCA
		Controlador.setIsSelected(true);
		c.setPossibleMov(t.posiblesMovimientosPieza(6, 2));
		c.setIniX(6);
		c.setIniY(2);
		c.controlTurnos(7, 0); 
		assertEquals("ReinaBlanca=1 " + "TorreBlanca=1 ", banquillo.getBanquilloBlanco());
				
	}
}
