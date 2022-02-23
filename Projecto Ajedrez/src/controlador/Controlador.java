package controlador;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

import org.json.JSONObject;
import org.json.JSONTokener;

import modelo.interfaces.Acciones;
import modelo.interfaces.Observable;
import modelo.interfaces.MovimientoPiezas;
import modelo.jugador.ColorJugador;
import modelo.jugador.Jugador;
import modelo.jugador.JugadorAutomaticoAleatorio;
import modelo.jugador.JugadorAutomaticoFichero;
import modelo.jugador.Pair;
import modelo.tablero.Casilla;
import modelo.tablero.Tablero;

public class Controlador implements Observable<Acciones>, Runnable{
	
	private Jugador j1;
	private Jugador j2;
	private JugadorAutomaticoAleatorio IA;
	private static boolean isSelected = false;
	private boolean Online = false;
	private int iniX;
	private int iniY;
	
	private Tablero t;
	private ArrayList<Casilla> possibleMov;
	private ArrayList<Casilla> amenaza;
	
	public enum turnoActual{J1, J2, IA}
	public turnoActual turno;
	
	private boolean IAActivada;
	
	private String ipDestino;
	
	public Controlador(Tablero tab) {
		t = tab;
		addPlayers("Jugador1", "Jugador2");
		
		Thread online = new Thread(this);
		online.start();
	}
	
	public void setOnline(boolean online) {Online = online;}
	public void setIpDestino(String s) {ipDestino = s;}
	
	@Override
	public void run() {
		try {
			ServerSocket servidor = new ServerSocket(9090);
			
			while(true) {
				Socket cliente = servidor.accept();
				
				ObjectInputStream in = new ObjectInputStream(cliente.getInputStream());
				String aux = (String) in.readObject();
				JSONObject jo = new JSONObject(new JSONTokener(aux));
				t.moverPieza(jo.getInt("Xini"), jo.getInt("Yini"), jo.getInt("Xfin"), jo.getInt("Yfin"), this);
				if(turno == turnoActual.J1) turno = turnoActual.J2;
				else turno = turnoActual.J1;
				
			}
		}catch(IOException | ClassNotFoundException e) {
			System.err.println(e.getMessage());
		}
		
	}
	
	public void crearJugadorOnline(String nombre, ColorJugador color) {
		j1 = new Jugador(nombre, color);
		j2 = new Jugador("", ColorJugador.NEGRO);
	}
	
	//Crea jugadores del 1vs1
	public void addPlayers(String nombre1, String nombre2){
		IAActivada = false;
		turno = turnoActual.J1;
		j1 = new Jugador(nombre1, ColorJugador.BLANCO);
		j2 = new Jugador(nombre2, ColorJugador.NEGRO);
	}
	
	public void addPlayerAndIA(String nombreJ, ColorJugador colorJ, ColorJugador colorIA, String mIA) {
		j1 = new Jugador(nombreJ, colorJ);
		if(mIA.equalsIgnoreCase("aleatorio")) {
			IA = new JugadorAutomaticoAleatorio(colorIA, t, false);
		}
		else if (mIA.equalsIgnoreCase("fichero")){
			IA = new JugadorAutomaticoFichero(colorIA, t);
		}
		else {
			IA = new JugadorAutomaticoAleatorio(colorIA, t, true);
		}
		IAActivada = true;
		if(colorJ == ColorJugador.BLANCO)
			turno = turnoActual.J1;
		else{
			turno = turnoActual.IA;
			controlTurnos(0, 0);
		}
	}
	
	//////////////////////////////////////////////////////VISTA
	@Override
	public void addObserver(Acciones[][] o) {t.addObserver(o);}
	@Override
	public void removeObserver(Acciones[][] o) {t.removeObserver(o);}
	@Override
	public void addObserver(Acciones o) {t.addObserver(o);}
	@Override
	public void removeObserver(Acciones o) {t.removeObserver(o);}
	
	///////////////////////////////////////////////////MOVIMIENTO 
	public boolean getIsSelected() {return isSelected;}
	
	public void controlTurnos(int x, int y) {
		//Seleccion y movimiento de pieza del jugador automatico aleatorio o por fichero
		if(IAActivada) {
			if(turno == turnoActual.IA) {
				Pair<Pair<Integer, Integer>, Pair<Integer, Integer>> coord = IA.movimientoIA(t);
				t.moverPieza(coord.getPrimero().getPrimero(), coord.getPrimero().getSegundo(),
						coord.getSegundo().getPrimero(), coord.getSegundo().getSegundo(), this);
				turno = turnoActual.J1;
			}
			else {
				turnoJugadorvsIA(x, y);
			}
		}
		//Turno de los jugadores normales
		else {
			turnosPvP(x, y);
		}
	}
	
	private void turnoJugadorvsIA(int x, int y) {
		if(getIsSelected()) {
			if(moverPieza(x, y)) {
				turno = turnoActual.IA;
				controlTurnos(0, 0);
			}
		}
		else
			seleccionarPieza(x, y);
	}
	
	private void turnosPvP(int x, int y) {
		if(getIsSelected())
			moverPieza(x, y);
		else
			seleccionarPieza(x, y);	
	}
	
	//Selecciona la pieza y actualiza la vista de las casillas a las que puede moverse
	private void seleccionarPieza(int x, int y) {
		possibleMov = t.posiblesMovimientosPieza(x, y);
		if(possibleMov == null || possibleMov.isEmpty()) {
			//O no hay pieza o no puede moverse
			isSelected = false;
		}
		else {
			iniX = x;
			iniY = y;
			isSelected = true;
			//Solo aparecen los movimientos posibles si pulsas una pieza del color del jugador de quien es el turno
			if(turno == turnoActual.J1 && j1.getColor() == t.getColorCasilla(iniX, iniY)
					|| turno == turnoActual.J2 && j2.getColor() == t.getColorCasilla(iniX, iniY)) {
				t.actualizarColoresFondocasilla(possibleMov, true);       
			}
		}
	}
	
	//Comprueba si la pieza puede moverse a la casilla seleccionada y en caso de poder se hace,
	//actualiza las vistas y si está en modo online manda los datos al servidor
	private boolean moverPieza(int x, int y) { //Para las pruebas del JUnit tendria que ser public
		boolean existe = false;
		if(turno == turnoActual.J1 && j1.getColor() == t.getColorCasilla(iniX, iniY)
				|| turno == turnoActual.J2 && j2.getColor() == t.getColorCasilla(iniX, iniY)) {
			
			amenaza = t.listaAmenazadas(turno);
			//Intenta mover la pieza
			int i = 0;
			while(i < possibleMov.size() && !existe) {
				if(possibleMov.get(i).getX() == x && possibleMov.get(i).getY() == y) {
					existe = true;
					t.moverPieza(iniX, iniY, x, y, this);
					for(Acciones ob : t.getObs()) {
						ob.updateError(null);
					}
					//Manda los datos al servidor
					if(Online) {
						try {
							Socket envia = new Socket("LocalHost", 9999);
							JSONObject jo = new JSONObject();
							jo.put("Xini", iniX);
							jo.put("Yini", iniY);
							jo.put("Xfin", x);
							jo.put("Yfin", y);
							jo.put("ip", ipDestino);
							ObjectOutputStream out = new ObjectOutputStream(envia.getOutputStream());
							out.writeObject(jo.toString(3));
							envia.close();
						} catch (IOException e) {e.printStackTrace();}
					}
				}
				t.actualizarColoresFondocasilla(possibleMov, false);
				i++;
			}
			
			if (!existe) {
				for(Acciones ob : t.getObs()) {ob.updateError("Movimiento Erroneo");}
			}
			if(t.getCasillaOcupada(iniX, iniY) == false) {
				//Hasta que el jugador no haya realizado un movimiento valido el turno no cambia
				if(!IAActivada) {
					if(turno == turnoActual.J1) turno = turnoActual.J2;
					else  turno = turnoActual.J1;
				}
				for(Acciones ob : t.getObs()) {
					ob.updateTurno(turno);
				}
			}
		}
		isSelected = false;
		return existe;
	}
	
	public void coronar(MovimientoPiezas p, int x, int y) {
		t.enableDisableVistaCasilla(true);
		t.coronar(p, x, y);
	}
	
	public void reset() {t.reset();}

	public Jugador getJBlanco() {return j1;}
	public Jugador getJNegro() {return j2;}

	public turnoActual getTurno() {return turno;}
	public void setTurno(turnoActual turno) {this.turno = turno;}
	public static void setIsSelected(boolean isSelected) {Controlador.isSelected = isSelected;}

	public void setIniX(int iniX) {this.iniX = iniX;}
	public void setIniY(int iniY) {this.iniY = iniY;}

	public void setPossibleMov(ArrayList<Casilla> possibleMov) {this.possibleMov = possibleMov;}

	public boolean isIAActivada() { return IAActivada; }
}
