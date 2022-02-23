package modelo.jugador;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import modelo.tablero.Casilla;
import modelo.tablero.Tablero;

public class JugadorAutomaticoAleatorio {	//Jugador automatico mediante movimientos aleatorios

	private ColorJugador color;
	private final boolean nivelMedio;
	
	protected Map<String, Casilla> piezas;
	protected ArrayList<String> nombrePiezas;
	
	public JugadorAutomaticoAleatorio(ColorJugador c, Tablero t, boolean esNivelMedio) {
		color = c;
		nivelMedio = esNivelMedio;
		piezas = new HashMap<String, Casilla>();
		nombrePiezas =  new ArrayList<String>();
		initAleatorio(t);
	}
	
	public Pair<Pair<Integer, Integer>, Pair<Integer, Integer>> movimientoIA(Tablero t) {
		Pair<Pair<Integer, Integer>, Pair<Integer, Integer>> coordenadas = null;
		if(nivelMedio) {
			coordenadas = nivelMedio(t);
			//Si no se puede comer al rey y por lo tanto no hay movimientos
			if(coordenadas == null) {
				//Aleatorio
				return nivelFacil(t);
			}
			else {
				return coordenadas;
			}
		}
		return nivelFacil(t);
	}
	
	//Metodo que ejecuta un movimiento que comera al rey si puede
	public Pair<Pair<Integer, Integer>, Pair<Integer, Integer>> nivelMedio(Tablero t) {
		for (int i = 0; i < nombrePiezas.size(); i++) {
			//Lista con los posibles movimientos de la pieza
			ArrayList<Casilla> jaque = piezas.get(nombrePiezas.get(i)).possibleMovementsPieza(t);
			for (int j = 0; j < jaque.size(); j++) {
				if(jaque.get(j).getPieza().esRey()) {
					return new Pair<Pair<Integer,Integer>, Pair<Integer,Integer>>(
							new Pair<Integer,Integer>(piezas.get(nombrePiezas.get(i)).getX(),
									piezas.get(nombrePiezas.get(i)).getY()),
							new Pair<Integer,Integer>(jaque.get(j).getX(),
									jaque.get(j).getY()));
				}
			}
		}
		return null;
	}
	
	//Metodo que ejecuta un movimiento aleatorio
	private Pair<Pair<Integer, Integer>, Pair<Integer, Integer>> nivelFacil(Tablero t) {
		ArrayList<Casilla> movimientos = new ArrayList<Casilla>();
		Pair<Integer, Integer> origen;
		Pair<Integer, Integer> destino;
		do {
			//selecciona la pieza
			int r = new Random().nextInt(piezas.size());
			movimientos = piezas.get(nombrePiezas.get(r)).possibleMovementsPieza(t);
			origen = new Pair<Integer, Integer>(piezas.get(nombrePiezas.get(r)).getX(), piezas.get(nombrePiezas.get(r)).getY());
		}
		while(movimientos == null || movimientos.size() <= 0);
		//Selecciona la casilla
		int r = new Random().nextInt(movimientos.size());
		destino = new Pair<Integer, Integer>(movimientos.get(r).getX(), movimientos.get(r).getY());
		
		return new Pair<Pair<Integer, Integer>, Pair<Integer, Integer>>(origen, destino);	
	}
	
	public ColorJugador getColor() {return color;}
	
	private void initAleatorio(Tablero t) {
		int j;
		if(this.color == ColorJugador.BLANCO) {j = 1;}
		else {j = 6;}
		
		for(int i = 0; i < Tablero.TAM; i++) {
			int aux = i + 1;
			piezas.put("peon" + aux, t.getTablero()[j][i]);
			nombrePiezas.add("peon" + aux);
		}

		if(this.color == ColorJugador.BLANCO) {j = 0;}
		else {j = 7;}
		
		piezas.put("torre1", t.getTablero()[j][0]);
		nombrePiezas.add("torre1");
		piezas.put("caballo1", t.getTablero()[j][1]);
		nombrePiezas.add("caballo1");
		piezas.put("alfil1", t.getTablero()[j][2]);
		nombrePiezas.add("alfil1");
		piezas.put("reina", t.getTablero()[j][3]);
		nombrePiezas.add("reina");
		piezas.put("rey", t.getTablero()[j][4]);
		nombrePiezas.add("rey");
		piezas.put("alfil2", t.getTablero()[j][5]);
		nombrePiezas.add("alfil2");
		piezas.put("caballo2", t.getTablero()[j][6]);
		nombrePiezas.add("caballo2");
		piezas.put("torre2", t.getTablero()[j][7]);
		nombrePiezas.add("torre2");
	}
}
