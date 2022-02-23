package vista;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import controlador.Controlador;
import controlador.Controlador.turnoActual;
import modelo.tablero.Tablero;


public class ControlVista extends JPanel{
	
	private JButton ExitButton;
	private JButton pedirTablas;
	private JButton reiniciarPartida;
	private JButton reglasBasicas;
	private JButton rendirse;
	int cont = 1;
	
	
	public ControlVista(VentanaPrincipal mw, Controlador ctrl) {
		initGui(mw, ctrl);
	}
	
	private void initGui(VentanaPrincipal mw, Controlador ctrl) {
		
		JPanel botones = new JPanel();
		botones.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));
		this.setLayout(new BorderLayout());
		
		ExitButton = createButton("Exit Game", "", "Salir del Juego");
		ExitButton.addActionListener(new ActionListener() {		
			@Override
			public void actionPerformed(ActionEvent e) {
				Object[] options = {"Si", "No"};
				int r = JOptionPane.showOptionDialog(null, "Desea salir del juego?", "Salir", JOptionPane.YES_NO_OPTION , JOptionPane.QUESTION_MESSAGE , null, options, options[1]);
				if(r == 0) {System.exit(0);}
				else { return; }
			}
		});
		
		pedirTablas = createButton("Tablas", "", "Pedir tablas");
		pedirTablas.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Object[] options = {"Si", "No"};
				int r = JOptionPane.showOptionDialog(null, "Desea pedir tablas?", "Tablas", JOptionPane.YES_NO_OPTION , JOptionPane.QUESTION_MESSAGE , null, options, options[1]);
				if(r == 0) {
					System.out.println("Hay tablas!");
				}
				else { return; }
			}
			
		});
		reiniciarPartida = createButton("Reiniciar", "", "Reiniciar partida");
		reiniciarPartida.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Object[] options = {"Si", "No"};
				int r = JOptionPane.showOptionDialog(null, "Desea reiniciar la partida?", "Reiniciar", JOptionPane.YES_NO_OPTION , JOptionPane.QUESTION_MESSAGE , null, options, options[1]);
				if(r == 0) {
					inicializarDeNuevo(mw, ctrl);
				}
				else return; 
			}
			
		});
		
		
		reglasBasicas = createButton("Reglas basicas", "", "Reglas basicas");
		reglasBasicas.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				
				Object[] options = {"Cerrar", "Regla siguiente", "Regla anterior"};
				int r = JOptionPane.showOptionDialog(null, "Pag "+ cont  +"/"+"6"+"\n\n" + toStringReglas(cont) + "\n\n", "Reglas basicas", JOptionPane.INFORMATION_MESSAGE, JOptionPane.INFORMATION_MESSAGE, null, options, options[1]);
				if(r == 1) {
					if(cont < 6) cont++;
					actionPerformed(e);
				}else if( r == 2) {
					if(cont > 1) cont--;
					actionPerformed(e);
				}
			}
			
		});
		
		rendirse = createButton("Rendirse", "", "Rendirse");
		rendirse.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				
				Object[] options = {"Si", "No"};
				int r = JOptionPane.showOptionDialog(null, "Deseas rendirte? Tu rival ganara la partida", "Rendirse", JOptionPane.YES_NO_OPTION , JOptionPane.QUESTION_MESSAGE , null, options, options[1]);
				if(r == 0) {
					String aux;
					if(!ctrl.isIAActivada()) {	
						if(ctrl.getTurno() == turnoActual.J1) aux = "el jugador Negro";
						else aux = "el jugador Blanco";
						
					}else aux = "la IA";
					JOptionPane.showMessageDialog(null, "Ha ganado " + aux);
					System.exit(0);
				}
				else return; 
			}
			
		});
		
		
		botones.add(pedirTablas);
		botones.add(ExitButton);
		botones.add(reiniciarPartida);
		botones.add(reglasBasicas);
		botones.add(rendirse);
		this.add(botones);
	}
	
	private void inicializarDeNuevo(VentanaPrincipal mw, Controlador ctrl) { // Si el jugador desea reiniciar la partida lo que hace es crear una partida nueva como si fueran los metodos del Main
		ctrl.reset();
		new ModoJuegoDialog(mw, ctrl);
	}
	
	public String toStringReglas(int op) {
		switch (op) {
		case 1:
			return "El objetivo del juego es capturar al rey del otro jugador."+"\nUna vez que el rey es atacado y no puede escapar, se" + "\ndeclara jaque mate y el juego finaliza";
		case 2:
			return "Cada jugador solo tiene un movimiento por turno";
		case 3:
			return "Si tu rey se encuentra amenazado por una pieza enemiga,"+ "\n solo puedes o mover el rey para evitar el jaque o mover una pieza" + "\npara evitar que el rey muera";
		case 4:
			return "Cuando aun no se han movido los peones, estos pueden"+ "\navanzar 2 casillas";
		case 5:
			return "Si un peon logra llegar al otro lado del tablero (fila 1"+"\no fila 8) el jugador tendra la opcion de coronar" + "\nese peon pudiendo elegir si cambiarlo por " + "\nuna reina, un alfil, un caballo o una torre";
		case 6:
			return "Si no hay piezas entre el rey y la torre, el jugador" + "\npuede decidir hacer enroque, siempre que estas dos" + "\npiezas no se hayan movido a lo largo de la partida";
		default:
			return " ";
		}	
	}
	
	private JButton createButton(String caption, String imageName, String tooltip) {
		JButton button = new JButton(caption);
		button.setToolTipText(tooltip);
		return button;
	}	
}
