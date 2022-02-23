package modelo.piezas;

import java.util.ArrayList;

import modelo.interfaces.MovimientoPiezas;
import modelo.jugador.ColorJugador;
import modelo.tablero.Casilla;
import modelo.tablero.Tablero;

public class Alfil implements MovimientoPiezas {

	private ColorJugador color;
	
	public Alfil(ColorJugador c) {
		color = c;
	}

	@Override
	public ArrayList<Casilla> possibleMoves(int iniX, int iniY, Tablero t) {
		int movX[] = {-1, 1, -1, 1};
		int movY[] = {1, 1, -1, -1};
		ArrayList<Casilla> aux = new ArrayList<Casilla>();
		for (int i = 0; i < movX.length; i++) {								//Contador de las 4 posibles direcciones
			int x = iniX + movX[i];
			int y = iniY + movY[i];
			boolean comer = false;
			try {
				while((!t.getCasillaOcupada(x, y) || t.getColorCasilla(x, y) != this.color) && !comer) {
					if(colorOpuesto(t.getColorCasilla(x, y))) {
						comer = true;
					}
					aux.add(t.getTablero()[x][y]);
					x += movX[i];
					y += movY[i];
				}
			}
			catch (IndexOutOfBoundsException e) {}
		}
		return aux;
	}
	
	public boolean getHasMoved(){return true;}
	
	public void setHasMoved(boolean hasMoved) {}
	
	@Override
	public ColorJugador getColor() {return color;}

	@Override
	public String getNombrePieza() {
		if(color == ColorJugador.BLANCO)
			return "AlfilBlanco";
		else
			return "AlfilNegro";
	}
}
