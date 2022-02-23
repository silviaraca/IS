package modelo.tablero;

import java.util.ArrayList;

import modelo.interfaces.MovimientoPiezas;
import modelo.jugador.ColorJugador;

public class Casilla {

	private final int posX;
	private final int posY;
	private final ColorJugador colorCasilla;
	private boolean isOcuppied;
	
	private MovimientoPiezas pieza;
	
	public Casilla(int x, int y, ColorJugador c){
		posX = x;
		posY = y;
		colorCasilla = c;
		pieza = null;
	}
	//Getters & Setters
	public int getX() {return posX;}
	public int getY() {return posY;}
	public boolean getHasMoved() {return pieza.getHasMoved();}
	public boolean getIsOcuppied() {return isOcuppied;}
	public void setIsOcuppied(boolean n) {isOcuppied = n;}
	public void setHasMoved(boolean hasMoved) {
		pieza.setHasMoved(hasMoved);
	}
	
	///////////////////////////////////////////////////PIEZAS
	
	public void setPieza(MovimientoPiezas p) {pieza = p;}
	
	public MovimientoPiezas getPieza() {return pieza;}

	public ColorJugador getColorPieza() {
		if(pieza == null) return ColorJugador.NULL;
		else return pieza.getColor();
	}
	
	//Devuelve una lista con las casillas a las que puede moverse la pieza de esta casilla
	public ArrayList<Casilla> possibleMovementsPieza(Tablero t){
		if(pieza == null) return null;
		else return pieza.possibleMoves(posX, posY, t);
	}
	
	public boolean esRey() {return pieza.esRey();}
	
	///////////////////////////////////////////////////VISTA
	//Obtiene el nombre de la pieza y el color de esta casilla y lo envia para que se actualice la vista correspondiente en el tablero
	public String updateVistaCasilla() {
		String s = "";
		if(pieza != null)
			s = pieza.getNombrePieza();
		if(this.colorCasilla == ColorJugador.BLANCO) {
			s = s + "B.jpg";
		}
		else {
			s = s + "N.jpg";
		}
		return s;
	}
	
	//Obtiene el nombre de la pieza y devuelve un string con el color con el que debe actualizarse la vista
	public String marcarCasilla() {
		String s = "";
		if(pieza != null)
			s = pieza.getNombrePieza();
		s = s + "A.jpg";
		return s;
	}
}
