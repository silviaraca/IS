package vista;

import java.awt.Color;
import java.awt.Dimension;
import java.util.HashMap;
import java.util.Map;
import java.util.Iterator;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import controlador.Controlador;
import controlador.Controlador.turnoActual;
import modelo.interfaces.Acciones;

public class HistorialVista extends JPanel implements Acciones{
	JLabel turno, error;
	Map<String, Integer> banquillo = new HashMap<String, Integer>();
	JTextArea historial, b_text, n_text;
	JPanel b_panel;
	Controlador ctrl;


	
	public HistorialVista(Controlador ctrl) {
		this.ctrl = ctrl;
		ctrl.addObserver(this);
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		
		this.add(Box.createRigidArea(new Dimension(200,10)));
		
		turno = new JLabel("TURNO: " + ctrl.getJBlanco().getNombre());
		turno.setOpaque(true);
		turno.setBackground(Color.WHITE);
		turno.setForeground(Color.BLACK);
		turno.setAlignmentX(CENTER_ALIGNMENT);
		this.add(turno);
		
		this.add(Box.createRigidArea(new Dimension(200,10)));
		
		historial = new JTextArea();
		historial.setEditable(false);
		JScrollPane area = new JScrollPane(historial, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		area.setPreferredSize(new Dimension(200,400));
		area.setMaximumSize(new Dimension(200,400));
		this.add(area);
		
		this.add(Box.createRigidArea(new Dimension(200,10)));
		
		b_panel = new JPanel();
		b_panel.setPreferredSize(new Dimension(200, 120));
		b_panel.setMaximumSize(new Dimension(200, 120));
		b_panel.setAlignmentX(CENTER_ALIGNMENT);
		
		b_text = new JTextArea("Banquillo blanco: ");
		b_text.setOpaque(false);
		b_text.setWrapStyleWord(true);
		b_text.setLineWrap(true);
		b_text.setEditable(false);
		b_text.setPreferredSize(new Dimension(200, 60));
		b_text.setAlignmentX(CENTER_ALIGNMENT);
		b_panel.add(b_text);
		
		n_text = new JTextArea("Banquillo negro: ");
		n_text.setOpaque(false);
		n_text.setWrapStyleWord(true);
		n_text.setLineWrap(true);
		n_text.setEditable(false);
		n_text.setPreferredSize(new Dimension(200, 60));
		n_text.setAlignmentX(CENTER_ALIGNMENT);
		b_panel.add(n_text);
		
		this.add(b_panel);
		
		this.add(Box.createRigidArea(new Dimension(200,10)));
		
		error = new JLabel();
		error.setForeground(Color.RED);
		error.setAlignmentX(CENTER_ALIGNMENT);
		this.add(error);
		
		
	}

	@Override
	public void updateImage(String s) {}
	@Override
	public void setEnableDisable(boolean b) {}

	@Override
	public void updateTurno(Controlador.turnoActual s) {		
		turno.setOpaque(true);		
		//J1 blancas
		if(s == turnoActual.J1) {
			turno.setText("TURNO: " + ctrl.getJBlanco().getNombre());
			turno.setBackground(Color.WHITE);
			turno.setForeground(Color.BLACK);
		}
		//J2 negras
		else {
			turno.setText("TURNO: " + ctrl.getJNegro().getNombre());
			turno.setBackground(Color.BLACK);
			turno.setForeground(Color.WHITE);
		}
	}

	@Override
	public void updateHistorial(String s) {
		historial.setText(historial.getText() + '\n' + s);
	}
	
	@Override
	public void updateError(String s) {
		error.setText(s);
	}

	

	@Override
	public void reset() {
		historial.setText("");
		turno.setText("");
		error.setText("");
	}

	@Override
	public void updateBanquillo(String s) {
		int valor;
		String printBlancas = "Banquillo blanco: ", printNegras = "Banquillo negro: ";
		if(!banquillo.containsKey(s)) banquillo.put(s, 0);
	
		valor = banquillo.get(s) + 1;
		banquillo.put(s, valor);
		
		Iterator<String> it = banquillo.keySet().iterator();
		while(it.hasNext()) {
			String key = it.next();
			if(key.indexOf("Blanc") != -1) printBlancas += key + "=" + banquillo.get(key) + " ";
			else printNegras += key + "=" + banquillo.get(key) + " ";
			
			b_text.setText(printBlancas);
			n_text.setText(printNegras);
		}
	}
	
	
	public String getHistorial() { return historial.getText(); }
	
	public String getBanquilloBlanco() { return b_text.getText(); }
	
	public String getBanquilloNegro() { return n_text.getText(); }

}
