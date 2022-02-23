package modelo.jugador;

public class Pair<T1, T2> {	//Idea de clase sacada de la asignatura Tecnologia de la Programacion 2
	private T1 primero;
	private T2 segundo;

	public Pair(T1 p, T2 s) {
		primero = p;
		segundo = s;
	}

	public T1 getPrimero() {
		return primero;
	}

	public T2 getSegundo() {
		return segundo;
	}

}