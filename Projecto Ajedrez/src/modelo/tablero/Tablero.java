package modelo.tablero;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import org.json.JSONObject;

import controlador.Controlador;
import controlador.Controlador.turnoActual;
import modelo.interfaces.Acciones;
import modelo.interfaces.Observable;
import modelo.interfaces.MovimientoPiezas;
import modelo.jugador.ColorJugador;
import modelo.piezas.*;
import vista.coronar.CoronarDialog;

public class Tablero implements Observable<Acciones>{
	
	public static final int TAM = 8;
	private turnoActual turno;
	//Matriz con las casillas del tablero
	private Casilla[][] tablero;
	//ArrayList con todas las vistas del tablero
	private ArrayList<Acciones[][]> botones;
	//Arraylist con los demas tipos de vistas
	private ArrayList<Acciones> obs;
	
	
	public Tablero() {
		tablero = new Casilla[TAM][TAM];
		obs = new ArrayList<Acciones>();
		botones = new ArrayList<Acciones[][]>();
		initTablero();
	}
	
	public Casilla[][] getTablero() {return tablero;}
	
	public boolean getCasillaOcupada(int x, int y) {return tablero[x][y].getIsOcuppied();}	
	public ColorJugador getColorCasilla(int x, int y) {return tablero[x][y].getColorPieza();}
	public String getNombrePieza(int x, int y) {return tablero[x][y].getPieza().getNombrePieza();}
	public boolean tieneCasillaRey(int x, int y) {return tablero[x][y].esRey();}
	
	///////////////////////////////////////////////////MOVIMIENTO
	public ArrayList<Casilla> posiblesMovimientosPieza(int origenX, int origenY) {
		return tablero[origenX][origenY].possibleMovementsPieza(this);
	}
	
	//Devuelve una lista de las casillas amenazadas
	public ArrayList<Casilla> listaAmenazadas(turnoActual turno){
		this.turno = turno;
		ArrayList<Casilla> aux = new ArrayList<Casilla>();
		for(int i = 0; i < TAM; i++) {
			for(int j = 0; j < TAM; j++) {
				if(tablero[i][j].getIsOcuppied() && !tablero[i][j].getPieza().getNombrePieza().contains("Peon")) {
					if(turno == turnoActual.J1 && tablero[i][j].getColorPieza().equals(ColorJugador.BLANCO)) {
						aux.addAll(tablero[i][j].possibleMovementsPieza(this));
					}
					else if(turno == turnoActual.J2 && tablero[i][j].getColorPieza().equals(ColorJugador.NEGRO)) {
						aux.addAll(tablero[i][j].possibleMovementsPieza(this));
					}
				}
				else if(tablero[i][j].getIsOcuppied() && tablero[i][j].getPieza().getNombrePieza().contains("Peon")) {
					if(turno == turnoActual.J1 && tablero[i][j].getColorPieza().equals(ColorJugador.BLANCO)) {
						if(j == 7) aux.add(tablero[i + 1][j - 1]);
						else if (j == 0) aux.add(tablero[i + 1][j + 1]);
						else {
							aux.add(tablero[i + 1][j + 1]);
							aux.add(tablero[i + 1][j - 1]);
						}
					}
					else if(turno == turnoActual.J2 && tablero[i][j].getColorPieza().equals(ColorJugador.NEGRO)) {
						if(j == 7) aux.add(tablero[i - 1][j - 1]);
						else if (j == 0) aux.add(tablero[i - 1][j + 1]);
						else {
							aux.add(tablero[i - 1][j + 1]);
							aux.add(tablero[i - 1][j - 1]);
						}
					}
				}
			}
		}
		return aux;
	}
	
	//Hace las comprobaciones de como mover la pieza(enroque, coronar, movimiento normal etc)
	public void moverPieza(int origenX, int origenY, int destinoX, int destinoY, Controlador c) {
		String msg = tablero[origenX][origenY].getPieza().getNombrePieza() + "[" + (origenX+1) + "][" + (origenY+1) + "] --> [" + (destinoX+1) + "][" + (destinoY+1) + "]";
		//Jaque mate
		if(tablero[destinoX][destinoY].getIsOcuppied() && tablero[destinoX][destinoY].getPieza().esRey()) {
			String aux = "\nHa ganado el jugador " + tablero[origenX][origenY].getColorPieza();
			JOptionPane.showMessageDialog(null, aux.toLowerCase());
			msg += aux.toLowerCase();
			enableDisableVistaCasilla(false);
		}
		
		//Enroque
		if (tablero[origenX][origenY].getPieza().esRey() && destinoY == origenY + 2) {
			movimiento(origenX, 7, destinoX, 5);
		}
		else if (tablero[origenX][origenY].getPieza().esRey() && destinoY == origenY - 2) {
			movimiento(origenX, 0, destinoX, 3);
		}
		
		movimiento(origenX, origenY, destinoX, destinoY);
		
		//Coronacion
		if(tablero[destinoX][destinoY].getPieza().canBeCoronated(destinoX)) {
			new CoronarDialog(tablero[destinoX][destinoY].getPieza().getColor(), c, destinoX, destinoY);
			enableDisableVistaCasilla(false);
		}
		ArrayList<Casilla> aux = listaAmenazadas(turno);
		for(int i = 0; i < aux.size(); i++) {
			if(aux.get(i).getIsOcuppied()) {
				if (aux.get(i).getPieza().esRey()) {
					msg += " Jaque";
				}
			}		
		}
		for(Acciones ob : obs) ob.updateHistorial(msg);
	}
	
	//Mueve la pieza
	private void movimiento(int origenX, int origenY, int destinoX, int destinoY) {
		tablero[origenX][origenY].setHasMoved(true);
		if(tablero[destinoX][destinoY].getIsOcuppied()) {
			for(Acciones ob : obs) ob.updateBanquillo(tablero[destinoX][destinoY].getPieza().getNombrePieza());
		}
		tablero[destinoX][destinoY].setPieza(tablero[origenX][origenY].getPieza());
		tablero[destinoX][destinoY].setIsOcuppied(true);
		tablero[origenX][origenY].setPieza(null);
		tablero[origenX][origenY].setIsOcuppied(false);
		//Actualiza la vista
		updateVistaCasilla(origenX, origenY, destinoX, destinoY);
	}
	
	public void coronar(MovimientoPiezas p, int x, int y) {
		tablero[x][y].setPieza(p);
		for (int i = 0; i < botones.size(); i++)
			botones.get(i)[x][y].updateImage(tablero[x][y].updateVistaCasilla());
	}
	
	///////////////////////////////////////////////////VISTA
	
	//Metodo que actualiza todas las vistas del tablero de las casillas origen y destino
	private void updateVistaCasilla(int origenX, int origenY, int destinoX, int destinoY) {
		for (int i = 0; i < botones.size(); i++) {
			botones.get(i)[origenX][origenY].updateImage(tablero[origenX][origenY].updateVistaCasilla());
			botones.get(i)[destinoX][destinoY].updateImage(tablero[destinoX][destinoY].updateVistaCasilla());
		}
	}
	
	/*
	 * Metodo que actualiza todas las vistas del tablero para marcar las casillas
	 * Si marcar es true entonces actualiza el fondo a color azul, si no actualia las casillas
	 * a sus piezas y colores correspondientes
	 */
	public void actualizarColoresFondocasilla(ArrayList<Casilla> c, boolean marcar) {
		for (int tab = 0; tab < botones.size(); tab++) {
			for (int i = 0; i < TAM; i++)
				for (int j = 0; j < TAM; j++) {
					int k = 0;
					boolean found = false;
					while(k < c.size() && !found) {
						if(tablero[i][j] == c.get(k)) {
							if(marcar)
								botones.get(tab)[i][j].updateImage(tablero[i][j].marcarCasilla());
							else
								botones.get(tab)[i][j].updateImage(tablero[i][j].updateVistaCasilla());
							found = true;
						}
						k++;
					}
				}
		}
	}
	
	//Desactiva los botones de las casillas
	public void enableDisableVistaCasilla(boolean b) {
		for (int tab = 0; tab < botones.size(); tab++) {
			for (int i = 0; i < TAM; i++) {
				for (int j = 0; j < TAM; j++) {
					botones.get(tab)[i][j].setEnableDisable(b);
				}
			}
		}
	}
	
	@Override
	public void addObserver(Acciones[][] o) {
		if(o.length != 8) {
			JOptionPane.showMessageDialog(null, "La vista debe ser una matriz de 8x8", "ERROR", JOptionPane.ERROR_MESSAGE);
			System.exit(0);
		}
		botones.add(o);
		initVistaTablero();
	}
	@Override
	public void removeObserver(Acciones[][] o) {
		botones.remove(o);
		initVistaTablero();
	}
	@Override
	public void addObserver(Acciones o) {obs.add(o);}
	@Override
	public void removeObserver(Acciones o) {obs.remove(o);}
	public ArrayList<Acciones> getObs(){return obs;}
	
	///////////////////////////////////////////////////INIT
	public void reset() {
		initTablero();
		initVistaTablero();
		for (Acciones a : obs) {
			a.reset();
		}
	}
	
	//Funcion que actualiza la vista de las casillas
	private void initVistaTablero() {
		for (int tab = 0; tab < botones.size(); tab++) {
			for (int i = 0; i < TAM; i++) {
				for (int j = 0; j < TAM; j++) {		
					botones.get(0)[i][j].updateImage(tablero[i][j].updateVistaCasilla());
				}
			}
		}
	}
	
	private void initTablero() {
		for (int i = 0; i < TAM; i++) {
			for (int j = 0; j < TAM; j++) {
				if((i - j) % 2 == 0) tablero[i][j] = new Casilla(i, j, ColorJugador.NEGRO);
				else tablero[i][j] = new Casilla(i, j, ColorJugador.BLANCO);
				if(i > 1 && i < 6)
					tablero[i][j].setIsOcuppied(false);
				else
					tablero[i][j].setIsOcuppied(true);
			}
		}
		for(int i = 0; i < 8; i++) {
			tablero[1][i].setPieza(new Peon(ColorJugador.BLANCO));
			tablero[6][i].setPieza(new Peon(ColorJugador.NEGRO));
		}
		tablero[0][0].setPieza(new Torre(ColorJugador.BLANCO));
		tablero[7][0].setPieza(new Torre(ColorJugador.NEGRO));
		tablero[0][1].setPieza(new Caballo(ColorJugador.BLANCO));
		tablero[7][1].setPieza(new Caballo(ColorJugador.NEGRO));
		tablero[0][2].setPieza(new Alfil(ColorJugador.BLANCO));
		tablero[7][2].setPieza(new Alfil(ColorJugador.NEGRO));
		tablero[0][3].setPieza(new Reina(ColorJugador.BLANCO));
		tablero[7][3].setPieza(new Reina(ColorJugador.NEGRO));
		tablero[0][4].setPieza(new Rey(ColorJugador.BLANCO));
		tablero[7][4].setPieza(new Rey(ColorJugador.NEGRO));
		tablero[0][5].setPieza(new Alfil(ColorJugador.BLANCO));
		tablero[7][5].setPieza(new Alfil(ColorJugador.NEGRO));
		tablero[0][6].setPieza(new Caballo(ColorJugador.BLANCO));
		tablero[7][6].setPieza(new Caballo(ColorJugador.NEGRO));
		tablero[0][7].setPieza(new Torre(ColorJugador.BLANCO));
		tablero[7][7].setPieza(new Torre(ColorJugador.NEGRO));
	}
}
