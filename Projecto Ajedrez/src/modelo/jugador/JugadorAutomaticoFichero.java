package modelo.jugador;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JOptionPane;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

import modelo.tablero.Casilla;
import modelo.tablero.Tablero;

public class JugadorAutomaticoFichero extends JugadorAutomaticoAleatorio {
	
	private boolean aleatorio = false;
	private static int contadorTurno = 1;
	private Map<String, ArrayList<Pair<String, Pair<String, Integer>>>> movimientos;
	//			turno, 	posibles movs->nombre pieza,dir mov, num casillas
	
	//Jugador que lee las jugadas de un fichero y en caso de no poder mover hace un movimiento aleatorio
	public JugadorAutomaticoFichero(ColorJugador c, Tablero t) {
		super(c, t, true);
		movimientos = new HashMap<String, ArrayList<Pair<String, Pair<String, Integer>>>>();
		loadFile();
	}
	
	@Override
	public Pair<Pair<Integer, Integer>, Pair<Integer, Integer>> movimientoIA(Tablero t) {
		if(!aleatorio) {
			ArrayList<Casilla> casillasMov = new ArrayList<Casilla>();
			Pair<Integer, Integer> origen;
			Pair<Integer, Integer> destino;
			/*
			resetea el contador para que si hay mas de 20 movimientos
			programados y ya se han efectuado todos, pueda realizar los movimientos desde el turno 5
			*/
			if(contadorTurno > movimientos.size() && movimientos.size() >= 20) {
				contadorTurno = 5;
			}
			if(movimientos.containsKey("turno" + contadorTurno)) {
				for (int i = 0; i < 5; i++) {
					String nombrePieza = movimientos.get("turno" + contadorTurno).get(i).getPrimero();			//nombre de la pieza
					int numMovs = movimientos.get("turno" + contadorTurno).get(i).getSegundo().getSegundo();	//numero de casillas a moverse
					casillasMov = piezas.get(nombrePieza).possibleMovementsPieza(t);							//lista con los posibles movimientos
					
					if(casillasMov != null && casillasMov.size() > 0 && casillasMov.size() >= numMovs) {
						origen = new Pair<Integer, Integer>(piezas.get(nombrePieza).getX(), piezas.get(nombrePieza).getY());
						destino = movimientoProgramadoValido(casillasMov, origen,
								movimientos.get("turno" + contadorTurno).get(i).getSegundo().getPrimero(),
								movimientos.get("turno" + contadorTurno).get(i).getSegundo().getSegundo(),
								movimientos.get("turno" + contadorTurno).get(i).getPrimero());
						if(destino != null) {
							contadorTurno++;
							return new Pair<Pair<Integer,Integer>, Pair<Integer,Integer>>(origen, destino);
						}
					}
				}
			}
			contadorTurno++;
		}
		System.out.println("Movimientos programados imposibles, realizando movimiento aleatorio");
		return super.movimientoIA(t);
	}

	
	//Metodo que determina si el movimiento puede realizarse (bien escrito en el fichero etc)
	private Pair<Integer, Integer> movimientoProgramadoValido(ArrayList<Casilla> casillasMov, Pair<Integer, Integer> origen,
			String movimiento, int numCasillas, String nombrePieza) {
		int iniX = origen.getPrimero();
		int iniY = origen.getSegundo();
		for (int i = 0; i < casillasMov.size(); i++) {
			switch (movimiento) {
			case "vertical":
				if(casillasMov.get(i).getX() == (iniX + numCasillas) && casillasMov.get(i).getY() == iniY) {
					return new Pair<Integer, Integer>(casillasMov.get(i).getX(), casillasMov.get(i).getY());
				}
				break;
			case "horizontal":
				if(casillasMov.get(i).getX() == iniX && casillasMov.get(i).getY() == (iniY + numCasillas)) {
					return new Pair<Integer, Integer>(casillasMov.get(i).getX(), casillasMov.get(i).getY());
				}
				break;		
			case "diagonalArriba":
				if(casillasMov.get(i).getX() == (iniX + Math.abs(numCasillas)) && casillasMov.get(i).getY() == (iniY + numCasillas)) {
					return new Pair<Integer, Integer>(casillasMov.get(i).getX(), casillasMov.get(i).getY());
				}
				break;
			case "diagonalAbajo":
				if(casillasMov.get(i).getX() == (iniX - Math.abs(numCasillas)) && casillasMov.get(i).getY() == (iniY + numCasillas)) {
					return new Pair<Integer, Integer>(casillasMov.get(i).getX(), casillasMov.get(i).getY());
				}
				break;
			case "peonMover":
				if(piezas.get(nombrePieza).getColorPieza() == ColorJugador.BLANCO) {
					if(casillasMov.get(i).getX() == (iniX + Math.abs(numCasillas))) {
						return new Pair<Integer, Integer>(casillasMov.get(i).getX(), casillasMov.get(i).getY());
					}
				}
				else {
					if(casillasMov.get(i).getX() == (iniX - Math.abs(numCasillas))) {
						return new Pair<Integer, Integer>(casillasMov.get(i).getX(), casillasMov.get(i).getY());
					}
				}
				break;
			case "peonComer":
				break;
			default:
				Pair<Integer, Integer> caballo = movimientoProgramadoValidoCaballo(casillasMov.get(i), origen, movimiento);
				if(caballo != null) {
					return caballo;
				}
			}
		}
		return null;
	}
	
	private Pair<Integer, Integer> movimientoProgramadoValidoCaballo(Casilla c, Pair<Integer, Integer> origen, String movimiento) {
		int iniX = origen.getPrimero();
		int iniY = origen.getSegundo();
		switch (movimiento) {
		case "arribaIzquierda":
			if(c.getX() == (iniY - 2) && c.getY() == (iniX - 1)) {
				return new Pair<Integer, Integer>(c.getX(), c.getY());
			}
			break;
		case "arribaDerecha":
			if(c.getX() ==  (iniY - 2) && c.getY() == (iniX + 1)) {
				return new Pair<Integer, Integer>(c.getX(), c.getY());
			}
			break;
		case "izquierdaArriba":
			if(c.getX() ==  (iniY - 1) && c.getY() == (iniX - 2)) {
				return new Pair<Integer, Integer>(c.getX(), c.getY());
			}
			break;
		case "derechaArriba":
			if(c.getX() ==  (iniY - 1) && c.getY() == (iniX + 2)) {
				return new Pair<Integer, Integer>(c.getX(), c.getY());
			}
			break;
		case "izquierdaAbajo":
			if(c.getX() ==  (iniY + 1) && c.getY() == (iniX  - 2)) {
				return new Pair<Integer, Integer>(c.getX(), c.getY());
			}
			break;
		case "derechaAbajo":
			if(c.getX() ==  (iniY + 1) && c.getY() == (iniX + 2)) {
				return new Pair<Integer, Integer>(c.getX(), c.getY());
			}
			break;
		case "abajoIzquierda":
			if(c.getX() ==  (iniY + 2) && c.getY() == (iniX - 1)) {
				return new Pair<Integer, Integer>(c.getX(), c.getY());
			}
			break;
		case "abajoDerecha":
			if(c.getX() ==  (iniY + 2) && c.getY() == (iniX + 1)) {
				return new Pair<Integer, Integer>(c.getX(), c.getY());
			}
			break;
		}
		return null;
	}
	
	//Carga el archivo
	private void loadFile() {
		java.io.InputStream in = null;
		try {
			in = new FileInputStream(new File("resources" + File.separatorChar + "jugadablancas2.json"));
		} catch (FileNotFoundException e) {
			JOptionPane.showMessageDialog(null, "Fichero no encontrado, usando fichero por defecto");
			try {
				in = new FileInputStream(new File("resources" + File.separatorChar + "jugadas1.json"));
			} catch (FileNotFoundException e1) {
				JOptionPane.showMessageDialog(null, "No hay ficheros validos, iniciando modo aleatorio");
				aleatorio = true;
				return;
			}
		}
		JSONObject fichero = new JSONObject(new JSONTokener(in));
		JSONArray jugadas = fichero.getJSONArray("jugadas");					//array del json fichero
		for(int i = 0; i < jugadas.length(); i++) {
			JSONObject turno = jugadas.getJSONObject(i);						//objetos del array ("turno1") etc
			int aux = i + 1;
			movimientos.put("turno" + aux, new ArrayList<Pair<String,Pair<String,Integer>>>());
			for (int j = 1; j < 6; j++) {
				JSONObject t = turno.getJSONObject("turno" + aux);
				if(t.has("movimiento" + j)) {
					JSONArray mov = t.getJSONArray("movimiento" + j);	//array con el tipo de jugada
					movimientos.get("turno" + aux).add(new Pair<String, Pair<String,Integer>>(
							mov.getString(0), new Pair<String, Integer>(mov.getString(1), mov.getInt(2))));//pone la jugada en el map de jugadas
				}
			}
		}
	}
}