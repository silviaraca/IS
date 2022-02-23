package modelo.interfaces;

import java.util.ArrayList;

import modelo.jugador.ColorJugador;
import modelo.tablero.Casilla;
import modelo.tablero.Tablero;

public interface MovimientoPiezas {
	//Metodo que devuelve una lista con las casillas a las que puede moverse la pieza
	public ArrayList<Casilla> possibleMoves(int iniX, int iniY, Tablero t);
	
	//Metodo que indica si las dos piezas tienen colores distintos
	default boolean colorOpuesto(ColorJugador color) {
		if(this.getColor() == ColorJugador.BLANCO && color == ColorJugador.NEGRO)
			return true;
		else if(this.getColor() == ColorJugador.NEGRO && color == ColorJugador.BLANCO)
			return true;
		else
			return false;
	}
	
	public default boolean canBeCoronated(int y) {return false;}
	public default boolean esRey() {return false;}
	public ColorJugador getColor();
	public String getNombrePieza();
	public void setHasMoved(boolean hasMoved);
	public boolean getHasMoved();
}
