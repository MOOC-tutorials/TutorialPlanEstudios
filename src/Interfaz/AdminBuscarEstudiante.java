package Interfaz;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.*;
import Console.Console;
import Console.Logic_Console;
import Planner.Administrativos;
import Planner.Curso;
import Planner.PlanDeEstudios;





public class AdminBuscarEstudiante extends JPanel{

	JPanel panel;
	InterfazPrincipal main;
	String idEstudiante;
	JTextField inputtxt;
	PlanDeEstudios pln; 
	Logic_Console Logica; 
	ImageIcon iconBack = new ImageIcon("data/Botones/btnBack.png");

	public AdminBuscarEstudiante(PlanDeEstudios pp, Console  c,Logic_Console lc, JPanel p, InterfazPrincipal pInterfazPrincipal) {

		main = pInterfazPrincipal;

		Logica=lc;
		pln= pp;
		panel= p;
		setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();





		JLabel bienvenido = new JLabel();
		bienvenido.setText("Ingrese Codigo de estudiante ");
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
					abrirMenuEstudiante(e);	}

				else if (approved==false)
					JOptionPane.showMessageDialog(null, "Codigo no encontrado, revise informacion ingresada", "InfoBox: " + "Error", JOptionPane.INFORMATION_MESSAGE);

			}
		});

		add(siguiente,gbc);

		
		
		JLabel note = new JLabel();
		note.setText("Nota: para pruebas se sugiere utilizar el codigo de estudiante '201923986' ");
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
				main.switchPanel(panel, "adminMenuMain");
				
			}
			
		});
		add(back);
		
	}


	public boolean validarID() {
		boolean approved=false;

		String estStr= inputtxt.getText();

		 int est = Integer.parseInt(estStr);


		if (Logica.buscarEstudiantePorCodigo(est)!= null) {
			
			Logica.setEstudiante(Logica.buscarEstudiantePorCodigo(est));
			System.out.println("Se cargo id:  " + Logica.darEstudiante().darNombre());
			System.out.println("NumPlanes :  " + Logica.darEstudiante().darPlanes().size());

			//Se cargan todos los paneles con la informacion del estudiante
			main.cards.add(new AdminMenuEstudiante(main.cards, Logica, main), "estMenu");
			main.cards.add(new AdminInscritas(main.cards, main), "adminInscritas");
			main.cards.add(new EstudiantePlan(main.cards, Logica, main ),"estPlan");
			main.cards.add(new EstudianteInscribir(main.cards, main),"estInscribir");
			main.cards.add(new EstudianteInfo(main.cards, main),"estInfo");
			//main.cards.add(new EstudianteEditarPlan(main.cards, main),"estEditarPlan");
			main.cards.add(new EstudianteBuscarInscripcion(main.cards, main), "estBuscarInscripcion");

			approved=true;
			main.switchPanel(panel, "estMenu");
		}else {
			System.out.println("El codigo " + "no se encuentra registrado en el sistema");
		}

	
	return approved;

}

//--------------------------------
//transition methods
//------------------------------

public void abrirMenuEstudiante(ActionEvent e) {

	main.switchPanel(panel, "adminMenuEstudiante");

}	


}
