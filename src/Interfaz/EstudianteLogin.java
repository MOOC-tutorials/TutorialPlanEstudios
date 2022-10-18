package Interfaz;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

import Console.Console;
import Console.Logic_Console; 


public class EstudianteLogin extends JPanel{

	JPanel panel;
	InterfazPrincipal main;
	
	Logic_Console Logica; 
	
	String idEstudiante;
	JTextField inputtxt;
	ImageIcon iconSend = new ImageIcon("data/Botones/btnSend.png");
	
	
	public EstudianteLogin(JPanel pnel, Logic_Console pConsola, InterfazPrincipal pInterfazPrincipal) {
		
	main = 	pInterfazPrincipal;
	
	Logica = pConsola;
	
	panel= pnel;
	setLayout(new GridBagLayout());
	GridBagConstraints gbc = new GridBagConstraints();

	
	
	

	JLabel bienvenido = new JLabel();
	bienvenido.setText("Ingrese su codigo de Estudiante");
	bienvenido.setFont(new Font("Verdana", Font.PLAIN, 25));
	bienvenido.setSize(800, 000);
	gbc.insets=new Insets(50,0,0,0);
	//gbc.anchor = GridBagConstraints.NORTH;
	add(bienvenido,gbc);
	

	inputtxt = new JTextField(20);
	gbc.insets=new Insets(0,0,0,0);
	gbc.gridy=350;
	inputtxt.setFont(new Font("Verdana", Font.PLAIN, 20));
	inputtxt.addActionListener(new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {
			abrirMenu(e);
			
		}
		
	});
	add (inputtxt,gbc);
	
	
	JButton siguiente = new JButton();
	siguiente.setOpaque(false);
	siguiente.setContentAreaFilled(false);
	siguiente.setBorderPainted(false);
	siguiente.setIcon(iconSend);
	//gbc.gridy=400;
	
	siguiente.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	abrirMenu(e);	
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
	back.setText("Back");
	gbc.insets=new Insets(100,0,0,0);
	gbc.gridy=450;
	back.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	Menuanterior(e);	
            }
	});
	
	add(back,gbc);
	
	}
	
	//--------------------------------
	//transition methods
	//------------------------------
	
	public void abrirMenu(ActionEvent e) {
		
		validarID();
		
		
		
		//InterfazPrincipal main = new InterfazPrincipal();
		
	}	
	
	public void validarID() {
		idEstudiante= inputtxt.getText();
		if (idEstudiante.isBlank() ||idEstudiante.isEmpty() ) {
			JOptionPane.showMessageDialog(this, "El campo esta vacio");
		}else{
			int codigo = Integer.parseInt(idEstudiante);
			if (Logica.buscarEstudiantePorCodigo(codigo)!= null) {
				Logica.setEstudiante(Logica.buscarEstudiantePorCodigo(codigo));
				System.out.println("Se cargo id:  " + Logica.darEstudiante().darNombre());
				System.out.println("NumPlanes :  " + Logica.darEstudiante().darPlanes().size());
				
				//Se cargan todos los paneles con la informacion del estudiante
				main.cards.add(new EstudianteMenu(main.cards, Logica, main), "estMenu");
				main.cards.add(new EstudiantePlan(main.cards, Logica, main ),"estPlan");
				main.cards.add(new EstudianteInscribir(main.cards, main),"estInscribir");
				main.cards.add(new EstudianteInfo(main.cards, main),"estInfo");
				//main.cards.add(new EstudianteEditarPlan(main.cards, main),"estEditarPlan");
				main.cards.add(new EstudianteBuscarInscripcion(main.cards, main), "estBuscarInscripcion");
				
				
				main.switchPanel(panel, "estMenu");
			}else {
				JOptionPane.showMessageDialog(this, "Estudiante No encontrado ");
				//Opcion de Registrar Estudiante.

			}
	}}
	
	
	
	
	public void Menuanterior(ActionEvent e) {
		Logica.setEstudiante(null);
		main.switchPanel(panel, "Inicio");
	}
}
