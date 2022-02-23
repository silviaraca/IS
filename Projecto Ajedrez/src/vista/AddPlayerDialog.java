package vista;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import controlador.Controlador;

public class AddPlayerDialog extends JDialog {
	
	public AddPlayerDialog(JFrame owner, Controlador c) {
		super(owner, "Elegir el nombre de jugador", true);
		this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		initGui(c);
		this.pack();
		this.setVisible(true);
	}
	
	private void initGui(Controlador c) {
		JPanel mp = new JPanel();
		this.setContentPane(mp);
		this.setBounds(100, 100, 400, 75);
		this.setLayout(new BoxLayout(getContentPane(), BoxLayout.PAGE_AXIS));
		//this.setPreferredSize(new Dimension(400, 100));
		
		JButton basicButton = createButton("Elegir color", "", "Cada jugador elige color");
		basicButton.setAlignmentX(CENTER_ALIGNMENT);
		
		JButton randomButton = createButton("Color aleatorio", "", "El color se elige aleatoriamente");
		randomButton.setAlignmentX(CENTER_ALIGNMENT);
		basicButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				basicButton.setVisible(false);
				randomButton.setVisible(false);
				mp.remove(basicButton);
				mp.remove(randomButton);
				elegirNombre(mp, c);
			}
		});
		
		randomButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				basicButton.setVisible(false);
				randomButton.setVisible(false);
				mp.remove(basicButton);
				mp.remove(randomButton);
				randomNombre(mp, c);
			}
		});
		mp.add(basicButton);
		mp.add(randomButton);
		mp.setVisible(true);
	}
	
	private void elegirNombre(JPanel mp, Controlador c) {
		JPanel names = new JPanel();
		names.setLayout(new BoxLayout(names, BoxLayout.Y_AXIS));
		
		JPanel top = new JPanel();
		top.setLayout(new FlowLayout(FlowLayout.LEFT));
		top.add(new JLabel("Nombre Jugador Blanco:"));
		JTextField ta1 = new JTextField();
		ta1.setPreferredSize(new Dimension(100,20));
		top.add(ta1);
		
		JPanel mid = new JPanel();
		mid.setLayout(new FlowLayout(FlowLayout.LEFT));
		mid.add(new JLabel("Nombre Jugador Negro:"));
		JTextField ta2 = new JTextField();
		ta2.setPreferredSize(new Dimension(100,20));
		mid.add(ta2);
		
		
		names.add(top);
		names.add(mid);
		this.add(names, BorderLayout.CENTER);
		
		JPanel bottom = new JPanel();
		
		
//		this.add(new JLabel("Nombre jugador Blanco: "));
//		JTextField ta1 = new JTextField();
//		ta1.setPreferredSize(new Dimension(100,20));
//		this.add(ta1);
//			
//		this.add(new JLabel("Nombre jugador Negro: "));
//		JTextField ta2 = new JTextField();
//		ta2.setPreferredSize(new Dimension(100,20));
//		this.add(ta2);
//			
		JButton AceptButton = createButton("Aceptar","","Confirma los cambios y programa el evento");
		AceptButton.setAlignmentX(CENTER_ALIGNMENT);
		AceptButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String nombre1 = ta1.getText();
				String nombre2 = ta2.getText();
				if(nombre1 == null && nombre2 == null) {
					c.addPlayers("Jugador 1", "Jugador 2");
				}
				else if(nombre1 == null) {
					c.addPlayers("Jugador 1", nombre2);
				}
				else if(nombre2 == null) {
					c.addPlayers(nombre1, "Jugador 2");
				}
				else {
					c.addPlayers(nombre1, nombre2);
				}
				dispose();
			}
		});
		
		bottom.add(AceptButton);
		this.add(bottom, BorderLayout.PAGE_END);
		this.pack();
	}
	
	private void randomNombre(JPanel mp, Controlador c) {
		
		JPanel names = new JPanel();
		names.setLayout(new BoxLayout(names, BoxLayout.Y_AXIS));
		
		JPanel top = new JPanel();
		top.setLayout(new FlowLayout(FlowLayout.LEFT));
		top.add(new JLabel("Nombre Jugador 1:"));
		JTextField ta1 = new JTextField();
		ta1.setPreferredSize(new Dimension(100,20));
		top.add(ta1);
		
		JPanel mid = new JPanel();
		mid.setLayout(new FlowLayout(FlowLayout.LEFT));
		mid.add(new JLabel("Nombre Jugador 2:"));
		JTextField ta2 = new JTextField();
		ta2.setPreferredSize(new Dimension(100,20));
		mid.add(ta2);
		
		
		names.add(top);
		names.add(mid);
		this.add(names, BorderLayout.CENTER);
		
		JPanel bottom = new JPanel();
		
//		this.add(new JLabel("Inserta tu nombre de jugador"));
//		JTextField ta1 = new JTextField();
//		this.add(ta1);
//			
//		this.add(new JLabel("Inserta tu nombre de jugador"));
//		JTextField ta2 = new JTextField();
//		this.add(ta2);
//			
		JButton AceptButton = createButton("Aceptar","","Confirma los cambios y programa el evento");
		AceptButton.setAlignmentX(CENTER_ALIGNMENT);
		AceptButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				Random r = new Random();
				String nombre1, nombre2;
				int random = r.nextInt(2)+1;

				if(random == 1) {
					nombre1 = ta1.getText();
					nombre2 = ta2.getText();
				}
				else {
					nombre1 = ta2.getText();
					nombre2 = ta1.getText();
				}
				
				if(nombre1 == "") {
					nombre1 = "Jugador 1";
				}
				if(nombre2 == "") {
					nombre2 = "Jugador 2";
				}
				c.addPlayers(nombre1, nombre2);
				
				JOptionPane.showMessageDialog(null, nombre1 + ", eres el jugador BLANCO. " + nombre2 + ", eres el jugador NEGRO", "Jugador Blanco", JOptionPane.INFORMATION_MESSAGE);
				dispose();
			}
		});
		bottom.add(AceptButton);
		this.add(bottom, BorderLayout.PAGE_END);
		this.pack();
	}
	
	private JButton createButton(String caption, String imageName, String tooltip) {
		JButton button = new JButton(caption);
		button.setToolTipText(tooltip);
		return button;
	}
}
