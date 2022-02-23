package vista;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import controlador.Controlador;

public class ModoJuegoDialog extends JDialog {
	
	private Controlador ctrl;
	private JFrame owner;
	
	public ModoJuegoDialog(JFrame o, Controlador c) {
		super(o, "Elige un modo de juego", true);
		this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		owner = o;
		ctrl = c;
		initGui();
		this.pack();
		this.setVisible(true);
	}
	
	private void initGui() {
		JPanel mp = new JPanel();
		this.setContentPane(mp);
		this.setLayout(new FlowLayout(FlowLayout.CENTER));
		this.setBounds(100, 100, 250, 75);
		this.setResizable(false);
		
		JButton PvP = createButton("1 vs 1", "Un jugador contra otro en el pc");
		PvP.setAlignmentX(LEFT_ALIGNMENT);
		
		JButton PvIA = createButton("1 vs IA", "Jugador contra maquina");
		PvIA.setAlignmentX(CENTER_ALIGNMENT);
		
		JButton online = createButton("Online", "Jugador contra jugador online");
		online.setAlignmentX(RIGHT_ALIGNMENT);
		
		PvP.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				//llamar a seleccion de nombres y cerrar esta ventana
				dispose();
				new AddPlayerDialog(owner, ctrl);
			}
			
		});
		
		PvIA.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				//crear la ia y cerrar la ventana
				dispose();
				new InitPvIADialog(owner, ctrl);
			}
			
		});
		
		online.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				//lo que toque
				dispose();
				new OnlineDialog(owner, ctrl);
			}
			
		});
		
		mp.add(PvP);
		mp.add(PvIA);
		mp.add(online);
		mp.setVisible(true);
	}
	
	private JButton createButton(String caption, String tooltip) {
		JButton button = new JButton(caption);
		button.setToolTipText(tooltip);
		return button;
	}
}
