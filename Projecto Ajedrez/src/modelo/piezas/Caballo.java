package modelo.piezas;

import java.util.ArrayList;

import modelo.interfaces.MovimientoPiezas;
import modelo.jugador.ColorJugador;
import modelo.tablero.Casilla;
import modelo.tablero.Tablero;

public class Caballo implements MovimientoPiezas {

	private ColorJugador color;
	
	public Caballo(ColorJugador c) {
		color = c;
	}

	@Override
	public ArrayList<Casilla> possibleMoves(int iniX, int iniY, Tablero t) {
		int movX[] = {-2, -1, 1, 2, -2, -1, 1, 2};
		int movY[] = {1, 2, 2, 1, -1, -2, -2, -1};
		ArrayList<Casilla> aux = new ArrayList<Casilla>();
		for(int i = 0; i < movX.length; i++) {
			int x = iniX + movX[i], y = iniY + movY[i];
			if((x < 8 && x >= 0) && (y < 8 && y >= 0)) {
				if(!t.getCasillaOcupada(x, y) || t.getColorCasilla(x, y) != color) {
					aux.add(t.getTablero()[x][y]);
				}
			}				
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
			return "CaballoBlanco";
		else
			return "CaballoNegro";
	}
}
