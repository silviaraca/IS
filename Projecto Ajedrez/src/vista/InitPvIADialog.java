package vista;

import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import controlador.Controlador;
import modelo.jugador.ColorJugador;

public class InitPvIADialog extends JDialog{
	private Controlador ctrl;
	private JFrame owner;
	
	public InitPvIADialog(JFrame o, Controlador c) {
		super(o, "Jugador vs IA", true);
		this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		owner = o;
		ctrl = c;
		this.setBounds(100, 100, 250, 75);
		initGui();
		this.pack();
		this.setVisible(true);
		this.setResizable(false);
	}
	
	private void initGui() {
		JPanel mp = new JPanel();
		mp.setLayout(new BoxLayout(mp, BoxLayout.PAGE_AXIS));
		this.add(mp);
		
		mp.add(new JLabel("Elige quien sera el jugador inicial"));
		String[] opciones1 = {"Jugador", "IA", "Aleatorio"};
		JComboBox<String> inicial = new JComboBox<String>(opciones1);
		mp.add(inicial);
		
		mp.add(new JLabel("Inserta tu nombre de jugador"));
		JTextField nombre = new JTextField();
		mp.add(nombre);

		mp.add(new JLabel("Elige un modo de juego de la IA"));
		JPanel dificultad = new JPanel();
		dificultad.setLayout(new GridLayout(1, 2));
		String[] opciones2 = {"Aleatorio", "Aleatorio con jaque", "Fichero"};
		JComboBox<String> modoIA = new JComboBox<String>(opciones2);
		
		JPanel dificultadInfo = new JPanel();
		dificultadInfo.setLayout(new BoxLayout(dificultadInfo, BoxLayout.PAGE_AXIS));
		dificultadInfo.add(new JLabel(" Aleatorio - Facil"));
		dificultadInfo.add(new JLabel(" Jaque - Medio"));
		dificultadInfo.add(new JLabel(" Fichero - Dificil"));
		
		dificultad.add(modoIA);
		//dificultad.add(new JSeparator(SwingConstants.VERTICAL));
		dificultad.add(dificultadInfo);
		//dificultad.add(new JSeparator(SwingConstants.VERTICAL));
		mp.add(dificultad);
		
		JButton AceptButton = createButton("Aceptar", "Confirma los cambios y programa el evento");
		AceptButton.setAlignmentY(CENTER_ALIGNMENT);
		AceptButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String nombreJugador = nombre.getSelectedText();
				if(nombreJugador == null) {
					nombreJugador = "Jugador";
				}
				if(inicial.getSelectedIndex() == 0) {
					ctrl.addPlayerAndIA(nombreJugador, ColorJugador.BLANCO, ColorJugador.NEGRO, modoIA.getSelectedItem().toString());
				}
				else if(inicial.getSelectedIndex() == 1) {
					ctrl.addPlayerAndIA(nombreJugador, ColorJugador.NEGRO, ColorJugador.BLANCO, modoIA.getSelectedItem().toString());
				}
				else {
					Random r = new Random();
					int random = r.nextInt(2)+1;
					if(random == 1) {
						JOptionPane.showMessageDialog(owner, "Eres el jugador BLANCO");						
						ctrl.addPlayerAndIA(nombreJugador, ColorJugador.BLANCO, ColorJugador.NEGRO, modoIA.getSelectedItem().toString());
					}
					else {
						JOptionPane.showMessageDialog(owner, "Eres el jugador NEGRO");
						ctrl.addPlayerAndIA(nombreJugador, ColorJugador.NEGRO, ColorJugador.BLANCO, modoIA.getSelectedItem().toString());
					}			
				}
				dispose();
			}
		});
		mp.add(AceptButton);
	}
	
	private JButton createButton(String caption, String tooltip) {
		JButton button = new JButton(caption);
		button.setToolTipText(tooltip);
		return button;
	}
}
