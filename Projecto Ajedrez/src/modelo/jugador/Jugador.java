package modelo.jugador;

public class Jugador {
	
	private String nombre;
	private ColorJugador color;
	
	public Jugador(String n, ColorJugador c) {
		nombre = n;
		color = c;
	}
	
	public ColorJugador getColor() {
		return color;
	}
	
	public String getNombre() {
		return nombre;
	}
}
