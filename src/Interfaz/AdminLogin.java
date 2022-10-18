package Interfaz;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.*;

import Console.*;
import Planner.*;


public class AdminLogin extends JPanel{

	JPanel panel;
	InterfazPrincipal main;

	String idEstudiante;
	JTextField inputtxt;

	Console c;
	PlanDeEstudios p= new PlanDeEstudios(); 
	ImageIcon iconBack = new ImageIcon("data/Botones/btnBack.png");



	public AdminLogin(PlanDeEstudios p,Console c ,JPanel pnel, InterfazPrincipal pInterfazPrincipal) {

		main = pInterfazPrincipal;

		panel= pnel;
		setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();





		JLabel bienvenido = new JLabel();
		bienvenido.setText("Ingrese su Login");
		bienvenido.setFont(new Font("Verdana", Font.PLAIN, 25));
		bienvenido.setSize(800, 000);
		gbc.insets=new Insets(-200,0,0,0);
		//gbc.anchor = GridBagConstraints.NORTH;
		add(bienvenido,gbc);


		inputtxt = new JTextField(20);
		gbc.insets=new Insets(0,0,0,0);
		gbc.gridy=350;
		inputtxt.setFont(new Font("Verdana", Font.PLAIN, 20));
		add (inputtxt,gbc);


		JButton siguiente = new JButton();
		siguiente.setText("NEXT");
		siguiente.setFont(new Font("Verdana", Font.PLAIN, 14));
		siguiente.setSize(50, 20);
		gbc.insets=new Insets(0,0,0,0);
		//gbc.gridy=400;

		siguiente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Boolean approved = validarID();
				
				if (approved == true) {
				abrirMenuAdmin(e);	}
				
				else if (approved==false)
			JOptionPane.showMessageDialog(null, "Codigo incorrecto, revise informacion ingresada", "InfoBox: " + "Error", JOptionPane.INFORMATION_MESSAGE);
					
			}
		});

		add(siguiente,gbc);
		
		
		
		
		JLabel note = new JLabel();
		note.setText("Nota: para pruebas se sugiere utilizar un login de admin valido 'Admin'o 'Juan' ");
		note.setFont(new Font("Verdana", Font.PLAIN, 12));
		note.setSize(800, 000);
		gbc.gridy=400;
		gbc.insets=new Insets(50,0,50,0);
		add(note,gbc);
		
		JButton back = new JButton();
		back.setOpaque(false);
		back.setContentAreaFilled(false);
		back.setBorderPainted(false);
		back.setIcon(iconBack);
		back.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				
				main.switchPanel(panel, "Inicio");
			}
			
		});
		add(back);
	}

	//--------------------------------
	//transition methods
	//------------------------------

	public void abrirMenuAdmin(ActionEvent e) {

		//InterfazPrincipal main = new InterfazPrincipal();
		main.switchPanel(panel, "adminMenuMain");

	}

	public boolean validarID() {
		boolean approved=false;

		String adm= inputtxt.getText();
		ArrayList<Administrativos> validar = p.getAdministrativosList();


		for (int i = 0; i < validar.size(); i++) {
			Administrativos ac = validar.get(i);
			System.out.println(ac.darNombre());
			if (ac.darNombre().equals(adm)) {
				//System.out.println(ac.darNombre());
				//System.out.println(adm);
				approved=true;
				System.out.println("Login aprobado: "  +approved);
			}


		}
		return approved;

	}


}





