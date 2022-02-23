package modelo.piezas;

import java.util.ArrayList;

import modelo.interfaces.MovimientoPiezas;
import modelo.jugador.ColorJugador;
import modelo.tablero.Casilla;
import modelo.tablero.Tablero;

public class Torre implements MovimientoPiezas {

	private ColorJugador color;
	private boolean hasMoved;
	
	public Torre(ColorJugador c) {
		color = c;
		hasMoved = false;
	}
	
	@Override
	public ArrayList<Casilla> possibleMoves(int iniX, int iniY, Tablero t) {
		ArrayList<Casilla> aux = new ArrayList<Casilla>();
		int movX[] = {-1, 1, 0, 0};
		int movY[] = {0, 0, 1, -1};
		
		for(int i=0; i < movX.length; i++) {
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
	
	public boolean getHasMoved() {return hasMoved;}
	
	public void setHasMoved(boolean hasMoved) {
		this.hasMoved = hasMoved;
	}
	@Override
	public ColorJugador getColor() {return color;}
	
	@Override
	public String getNombrePieza() {
		if(color == ColorJugador.BLANCO)
			return "TorreBlanca";
		else
			return "TorreNegra";
	}
}
