package Interfaz;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;


public class AdminHistorial extends JPanel{
	
	JPanel panel;
	InterfazPrincipal main;

	public AdminHistorial(JPanel p, InterfazPrincipal pInterfazPrincipal) {
		main =pInterfazPrincipal;
		panel= p;
		setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();


		JLabel bienvenido = new JLabel();
		bienvenido.setText("Historial Admin");
		bienvenido.setFont(new Font("Verdana", Font.PLAIN, 30));
		bienvenido.setSize(800, 000);
		gbc.insets=new Insets(-200,0,0,0);
		//gbc.anchor = GridBagConstraints.NORTH;
		add(bienvenido,gbc);
		
		
		JButton siguiente = new JButton();
		siguiente.setText("Main");
		siguiente.setFont(new Font("Verdana", Font.PLAIN, 30));
		siguiente.setSize(800, 200);
		gbc.insets=new Insets(50,0,0,0);
		gbc.gridy=400;
		
		siguiente.addActionListener(new ActionListener() {
	            public void actionPerformed(ActionEvent e) {
	            	abrirMain(e);	
	            }
		});
		
		add(siguiente,gbc);
		
		
	}
	
	//--------------------------------
	//transition methods
	//------------------------------
	
	public void abrirMain(ActionEvent e) {
		
		main.switchPanel(panel, "adminMenuEstudiante");
	
	}	

}
