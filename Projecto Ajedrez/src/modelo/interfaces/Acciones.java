package modelo.interfaces;

import controlador.Controlador;

public interface Acciones {
	//Interfaz que implementan todas las partes de la vista que pueden ser actualizadas
	public void updateImage(String s);
	public void setEnableDisable(boolean b);
	public void updateTurno(Controlador.turnoActual s);
	public void updateHistorial(String s);
	public void updateBanquillo(String s);
	public void updateError(String s);
	public void reset();
}
