package Interfaz;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

import Console.Logic_Console;
import Planner.*;




public class AdminInscritas extends JPanel{

	JPanel panel;
	InterfazPrincipal main;
	private Logic_Console Logica; 
	
	
	public AdminInscritas(JPanel p, InterfazPrincipal pInterfazPrincipal) {
		
		main = pInterfazPrincipal;
		panel= p;
		
		Logica = main.darLogic_Console(); 
		
		
		setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();


		JLabel bienvenido = new JLabel();
		bienvenido.setText("Inscritas");
		bienvenido.setFont(new Font("Verdana", Font.PLAIN, 30));
		bienvenido.setSize(800, 000);
		gbc.insets=new Insets(-200,0,0,0);
		//gbc.anchor = GridBagConstraints.NORTH;
		add(bienvenido,gbc);
		
		
		gbc.gridy=100;
		gbc.insets=new Insets(10,150,200,0);
        JTextArea field = new JTextArea();
    
        field.setEditable(false);
        field.setText(darInfoEstudiante());
        add(field,gbc);

		
		JButton siguiente = new JButton();
		siguiente.setText("Regresar");
		siguiente.setFont(new Font("Verdana", Font.PLAIN, 20));
		siguiente.setSize(800, 200);
		gbc.insets=new Insets(50,0,0,0);
		gbc.gridy=400;
		
		siguiente.addActionListener(new ActionListener() {
	            public void actionPerformed(ActionEvent e) {
	            	abrirmain(e);	
	            }
		});
		
		add(siguiente,gbc);
		
	}
	
	
	
	
	
	private String darInfoEstudiante() {
		String r ="";
		Estudiantes e1 = Logica.darEstudiante();
		r =  r+"\n"+"Nombre: " +e1.darNombre();
		r =  r+"\n"+"Codigo: " +String.valueOf(e1.darCodigo());
		r =  r+" \n";
		for (int i = 0; i < e1.darCursosAprobados().size(); i++) {
			
			Curso c = e1.darCursosAprobados().get(i);
			
			String curso = (c.darCodigo()+ ",  "+ c.darNombre()+ ",  "+ c.darTipo()+"\n"); 			
		// System.out.println(curso);
		
		 r=r+curso;
		}
		return r;
	}

	
	
	
	
	
	
	//--------------------------------
	//transition methods
	//------------------------------
	
	public void abrirmain(ActionEvent e) {
		
		main.switchPanel(panel, "estMenu");
	
	}	
	
	
}
