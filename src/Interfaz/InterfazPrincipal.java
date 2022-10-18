package Interfaz;

import java.awt.*;

import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.*;
import java.util.*;

import javax.swing.*;

import Planner.*;
import Console.Console;
import Console.Logic_Console;


public class InterfazPrincipal extends JFrame  {




	// -----------------------------------------------------------------
	// Constantes
	// -----------------------------------------------------------------

	private static Dimension tamMAX= new Dimension(866,750);


	// -----------------------------------------------------------------
	// Atributos --referencias
	// -----------------------------------------------------------------

	private PanelSuperior panelsup;

	private PanelInferior panelinf;
	private PlanDeEstudios plan = new PlanDeEstudios(); 
	
	private Logic_Console Logica = new Logic_Console(plan); 

	Console c;
	//private PanelCentral panelInicio;

	// -----------------------------------------------------------------
	// Atributos --Clase
	// -----------------------------------------------------------------

	//CardLayout cards; 
	JPanel cards;
	// -----------------------------------------------------------------
	// Constructor
	// -----------------------------------------------------------------



	public void addComponentToPane(Container panel) {

		//++++++++++++++++++++++++++++++++++++++++
		//Banner superior e inferior, no cambian durante la ejecucion
		panelsup = new PanelSuperior();
		panel.add(panelsup, BorderLayout.NORTH);

		panelinf = new PanelInferior();
		panel.add(panelinf, BorderLayout.SOUTH);
		
		
		cards = new JPanel();
		cards.setLayout(new CardLayout());
		//cards = new CardLayout();
		//cardsPanel=new JPanel();
		
		//++++++++++++++++++++++++++++++++++++++++++


		//++++++++++++++++++++++++++++++++++++++++++
		
		//++++++++++++++++++++++++++++++++++++++++++
		
		
		
		//panel central que contienen las cards 
		//se debe hacer .add a cada card creada
		
		
		cards.add(new PanelCentral(cards, this),"Inicio");
		
		//cards estudiante
		cards.add(new EstudianteLogin(cards, Logica, this ), "estLogin");
		/*
		cards.add(new EstudianteMenu(cards, Logica, this), "estMenu");
		cards.add(new EstudiantePlan(cards, Logica, this ),"estPlan");
		cards.add(new EstudianteInscribir(cards, this),"estInscribir");
		cards.add(new EstudianteInfo(cards, this),"estInfo");
		cards.add(new EstudianteEditarPlan(cards, this),"estEditarPlan");
		cards.add(new EstudianteBuscarInscripcion(cards, this), "estBuscarInscripcion");
		*/
		//cards Admin
		
		cards.add(new AdminLogin(plan, c, cards, this), "adminLogin");
		cards.add(new AdminBuscarEstudiante(plan,c,Logica, cards, this), "adminBuscarest");
		cards.add(new AdminHistorial(cards, this), "adminHistorial");
		cards.add(new AdminMenuMain(plan,c,cards, this), "adminMenuMain");
		//cards.add(new AdminMenuEstudiante(cards, this), "adminMenuEstudiante");
		//cards.add(new AdminInscritas(cards, this), "adminInscritas");
		
		panel.add(cards, BorderLayout.CENTER);



		pack();
		setVisible(true);
	}


	public void switchPanel(Container container, String Nombre) {
	    CardLayout cl = (CardLayout) (container.getLayout());
	    cl.show(container, Nombre);
	}
	
	
	// -----------------------------------------------------------------
	// Metodos
	// -----------------------------------------------------------------

	public Logic_Console darLogic_Console() {
		return Logica;
	}
	
	private static void crearFrame(){
		JFrame frame = new JFrame("CardLayoutDemo");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle( "WIDTH=850  HALF:425    HEIGHT=550  HALF:275" );
		frame.setPreferredSize(tamMAX);
		frame.setSize(tamMAX);
		frame.setResizable(false);
		frame.setVisible(true);


		InterfazPrincipal demo = new InterfazPrincipal();
		demo.addComponentToPane(frame.getContentPane());

	}



	// -----------------------------------------------------------------
	// Meain
	// -----------------------------------------------------------------



	public static void main( String[] args )
	{
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				crearFrame();
			}
		});
	}
}
