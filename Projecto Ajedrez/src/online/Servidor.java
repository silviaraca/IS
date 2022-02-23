package online;

import javax.swing.*;

import org.json.JSONObject;
import org.json.JSONTokener;

import java.awt.*;
import java.io.*;
import java.net.*;

public class Servidor  {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		MarcoServidor mimarco=new MarcoServidor();
		
		mimarco.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			
	}	
}

class MarcoServidor extends JFrame implements Runnable {
	
	public MarcoServidor(){
		
		setBounds(1200,300,280,350);				
			
		JPanel milamina= new JPanel();
		
		milamina.setLayout(new BorderLayout());
		
		areatexto=new JTextArea();
		areatexto.setEditable(false);
		JScrollPane aux = new  JScrollPane(areatexto, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		
		milamina.add(aux,BorderLayout.CENTER);
		
		add(milamina);
		
		setVisible(true);
		
		Thread hilo = new Thread(this);
		hilo.start();
		}
	
	private	JTextArea areatexto;

	@Override
	public void run() {
		try {
			//Crea una conexion con el puerto 9999
			ServerSocket servidor = new ServerSocket(9999);
			//Se mantiene a la escucha
			while(true) {
				
				//Crea una conexion con el ordenador entrante
				Socket recibe = servidor.accept();
				ObjectInputStream in = new ObjectInputStream(recibe.getInputStream());
				
				String aux = (String) in.readObject();
				JSONObject jo = new JSONObject(new JSONTokener(aux));
				areatexto.setText("INI X: " + jo.get("Xini") + " INI Y: " + jo.get("Yini") + " FIN X: " + jo.get("Xfin") + " FIN Y: " + jo.get("Yfin") + " IP: " + jo.getString("ip"));
				
				recibe.close();
				
				Socket envia = new Socket(jo.getString("ip"), 9090);
				
				ObjectOutputStream out = new ObjectOutputStream(envia.getOutputStream());
				
				out.writeObject(jo.toString(3));
				
				envia.close();
				
			}			
		} catch (IOException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			System.err.println(e.getMessage());
		}
	}
}
