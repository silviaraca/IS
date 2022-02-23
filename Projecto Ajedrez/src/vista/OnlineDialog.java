package vista;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import controlador.Controlador;
import modelo.jugador.ColorJugador;

public class OnlineDialog extends JDialog {

	/**
	 * 
	 */
	
	public OnlineDialog(JFrame o, Controlador ctrl) {
		super(o, "Online", true);
		//this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		this.setLayout(new BorderLayout());
		
		//Panel superior con selección Jugador
		JPanel top = new JPanel();
		top.add(new JLabel("Introduce nombre:"));
		JTextField nick = new JTextField();
		nick.setPreferredSize(new Dimension(100,20));
		top.add(nick);
		top.add(new JLabel("Elige color:"));
		String[] colores = {"BLANCO","NEGRO"};
		JComboBox color = new JComboBox(colores);
		top.add(color);
		this.add(top, BorderLayout.PAGE_START);
		
		//Panel central con ajustes del socket
		JPanel mid = new JPanel();
		mid.add(new JLabel("Introduce IP destino:"));
		JTextField ip = new JTextField();
		ip.setPreferredSize(new Dimension(100,20));
		mid.add(ip);
		this.add(mid, BorderLayout.LINE_START);
		
		//Panel inferior para confirmar
		JPanel bottom = new JPanel();
		JButton ok = new JButton("ok");
		ok.setPreferredSize(new Dimension(50,20));
		ok.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				ctrl.setOnline(true);
				ctrl.setIpDestino(ip.getText());
				ctrl.crearJugadorOnline(nick.getText(), ColorJugador.valueOf((String) color.getSelectedItem()));
				exit();
			}
			
		});
		bottom.add(ok);
		this.add(bottom, BorderLayout.PAGE_END);
		
		this.pack();
		this.setVisible(true);
		this.setResizable(false);
	}
	
	private void exit() {
		this.dispose();
	}
}
