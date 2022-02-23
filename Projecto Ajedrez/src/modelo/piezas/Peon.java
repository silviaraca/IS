package modelo.piezas;

import java.util.ArrayList;

import modelo.interfaces.MovimientoPiezas;
import modelo.jugador.ColorJugador;
import modelo.tablero.Casilla;
import modelo.tablero.Tablero;

public class Peon implements MovimientoPiezas {

	private ColorJugador color;
	private boolean hasMoved;
	
	public Peon(ColorJugador c) {
		color = c;
		hasMoved = false;
	}
	
	@Override
	public ArrayList<Casilla> possibleMoves(int iniX, int iniY, Tablero t) {
		ArrayList<Casilla> aux = new ArrayList<Casilla>();
		int movX[] = {1,1,1,2};
		int movY[] = {-1,1,0,0};
		boolean avanza = true;
		int i = 0;
		if (this.color == ColorJugador.NEGRO) {
			movX[0] = -1;
			movX[1] = -1;
			movX[2] = -1;
			movX[3] = -2;
		}
		
		while(avanza && i < movX.length) {
			try {
				if((i == 0 || i == 1)) {
					if(t.getCasillaOcupada(iniX + movX[i], iniY + movY[i]) && colorOpuesto(t.getTablero()[iniX + movX[i]][iniY + movY[i]].getColorPieza())){
						aux.add(t.getTablero()[iniX + movX[i]][iniY + movY[i]]);
					}
				}
				else if (i == 3) {
					if(!t.getCasillaOcupada(iniX + movX[i], iniY + movY[i]) && !hasMoved) {
						aux.add(t.getTablero()[iniX + movX[i]][iniY + movY[i]]);
					}
					else avanza = false;
				}
				else {
					if(!t.getCasillaOcupada(iniX + movX[i], iniY + movY[i])) {
							aux.add(t.getTablero()[iniX + movX[i]][iniY + movY[i]]);
					}
					else avanza = false;
				}
			}
			catch (IndexOutOfBoundsException e) {}
			i++;
		}		
		return aux;
	}
	
	/*
	switch (movimiento) {
		case "mover":
			for (int i = 0; i < posiblesMovs.size(); i++) {
				if(this.color == ColorJugador.NEGRO) {
					if(posiblesMovs.get(i).getX() == (iniX - numMovs)) {
						return new Pair<Integer, Integer>(posiblesMovs.get(i).getX(), posiblesMovs.get(i).getY());
					}
				}
				else {
					if(posiblesMovs.get(i).getX() == (iniX + numMovs)) {
						return new Pair<Integer, Integer>(posiblesMovs.get(i).getX(), posiblesMovs.get(i).getY());
					}
				}
			}
			break;
		case "comer":
			for (int i = 0; i < posiblesMovs.size(); i++) {
				if(posiblesMovs.get(i).getX() == (iniX + numMovs) && posiblesMovs.get(i).getY() == (iniY + numMovs)) {
					return new Pair<Integer, Integer>(posiblesMovs.get(i).getX(), posiblesMovs.get(i).getY());
				}
			}
			break;
		}
	 */
	
	public boolean getHasMoved() {return hasMoved;}
	
	public void setHasMoved(boolean hasMoved) {
		this.hasMoved = hasMoved;
	}

	@Override
	public boolean canBeCoronated(int x) {
		if (this.color == ColorJugador.NEGRO && x == 0)
			return true;
		else if(this.color == ColorJugador.BLANCO && x == 7)
			return true;
		else
			return false;
	}
	
	public ColorJugador getColor() {return color;}
	
	@Override
	public String getNombrePieza() {
		if(color == ColorJugador.BLANCO)
			return "PeonBlanco";
		else
			return "PeonNegro";
	}
}
