package PruebasJunit;

import static org.junit.jupiter.api.Assertions.*;

import javax.swing.JOptionPane;

import org.junit.jupiter.api.Test;

import controlador.Controlador;
import modelo.tablero.Tablero;

class PruebaBlancasNegras {
	
	Tablero t = new Tablero();
	Controlador c = new Controlador(t);
	
	@Test
	void testBlancasNegras() {
		String nombre1 = JOptionPane.showInputDialog("Nombre jugador blanco: ");
		String nombre2 = JOptionPane.showInputDialog("Nombre jugador negro: ");
		c.addPlayers(nombre1, nombre2);
		assertEquals(nombre1, c.getJBlanco().getNombre()); //Comprueba si el nombre del J1 se ha asociado con el color Blanco
		assertEquals(nombre2, c.getJNegro().getNombre());  //Comprueba si el nombre del J1 se ha asociado con el color Blanco
	}
	
	

}
