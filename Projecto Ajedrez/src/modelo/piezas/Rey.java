package modelo.piezas;

import java.util.ArrayList;

import modelo.interfaces.MovimientoPiezas;
import modelo.jugador.ColorJugador;
import modelo.tablero.Casilla;
import modelo.tablero.Tablero;

public class Rey implements MovimientoPiezas {

	private ColorJugador color;
	private boolean hasMoved;
	private boolean castling;
	
	public Rey(ColorJugador c) {
		color = c;
		hasMoved = false;
	}
	
	@Override
	public ArrayList<Casilla> possibleMoves(int iniX, int iniY, Tablero t) {
		int movX[] = {-1, 0, 1, -1, 1, -1, 0, 1};
		int movY[] = {1, 1, 1, 0, 0, -1, -1, -1};
		ArrayList<Casilla> aux = new ArrayList<Casilla>();
		for(int i = 0; i < movX.length; i++) {
			int x = iniX + movX[i], y = iniY + movY[i];
			if((x < 8 && x >= 0) && (y < 8 && y >= 0)) {
				if(!t.getCasillaOcupada(x, y) || t.getColorCasilla(x, y) != color) {
					aux.add(t.getTablero()[x][y]);
				}
			}				
		}
		if(t.getCasillaOcupada(iniX, 0) && !t.getTablero()[iniX][0].getHasMoved() && !hasMoved) {
			castling = true;
			for(int i = iniY - 1; i > 0; i--)
				if(t.getCasillaOcupada(iniX, i)) { castling = false;}
				if (castling) {aux.add(t.getTablero()[iniX][iniY - 2]);}
		}
		if(t.getCasillaOcupada(iniX, 7) && !t.getTablero()[iniX][7].getHasMoved() && !hasMoved) {
			castling = true;
			for(int i = iniY + 1; i < 7; i++)
				if(t.getCasillaOcupada(iniX, i)) { castling = false;}
				if (castling) {aux.add(t.getTablero()[iniX][iniY + 2]);}
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
	public boolean esRey() {return true;}
	
	@Override
	public String getNombrePieza() {
		if(color == ColorJugador.BLANCO)
			return "ReyBlanco";
		else
			return "ReyNegro";
	}
}
